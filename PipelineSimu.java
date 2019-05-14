/****************************************************************************************************************
 * This program simulates the pipelined datapath working. 
 * Author: Monika Nanda
 * Class: Computer Architecture CS472
 * Last Date Modified: 4/26/2019
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PipelineSimu {
	
int Main_Mem[][] = new int [1024][256];
int Regs[] = new int[32];
int[] Instructions = new int[13];
IFID_Pipeline pipeIFID; 
IDEX_Pipeline pipeIDEX;
EXMEM_Pipeline pipeEXMEM;
MEMWB_Pipeline pipeMEMWB;
int startAddr = 0x7A000;
int PC = startAddr;
int c;
	public void Pipeline() throws FileNotFoundException {     
		pipeIFID = new IFID_Pipeline();
		pipeIDEX = new IDEX_Pipeline();
		pipeEXMEM = new EXMEM_Pipeline();
		pipeMEMWB = new MEMWB_Pipeline();
		initialize_Main_Mem();								//initialize Main_mem	
		initialize_Regs();									//initialize Regs
		addInstructions();									//add instructions to array			
		for(c = 0; c < Instructions.length; c++) {			//main loop
			IF_stage(Instructions[c]);
			ID_stage();
			EX_stage();
			MEM_stage();
			WB_stage();
			Print_out_everything();
			Copy_write_to_read();
	   }
	}
	public void IF_stage(int instruction) {		
			pipeIFID.setInstrWrite(instruction);
			if(c == 0) {
				pipeIFID.setIncrPCWrite(PC+4);
			}else {
			pipeIFID.setIncrPCWrite(pipeIFID.getIncrPCRead()+4);	
			}
		}
		
	public void ID_stage() {							//setting registers based on operation code
		DecodeInstr instr = new DecodeInstr();
		instr.Decode(pipeIFID.getInstrRead());
		if (instr.getOpCode() == 0) {										//add,sub
			pipeIDEX.setRegDstWrite(1);
			pipeIDEX.setALUOp1Write(1);
			pipeIDEX.setALUOp0Write(0);
			pipeIDEX.setALUSrcWrite(0);
			pipeIDEX.setMemReadWrite(0);
			pipeIDEX.setMemWriteWrite(0);
			pipeIDEX.setRegWriteWrite(1);
			pipeIDEX.setMemToRegWrite(0);
			pipeIDEX.setIncrPCWrite(pipeIFID.getIncrPCRead());			
			pipeIDEX.setReadReg1ValueWrite(Regs[instr.getReg_RS()]);			
			pipeIDEX.setReadReg2ValueWrite(Regs[instr.getReg_RT()]);
			pipeIDEX.setWriteReg_20_16Write(instr.getReg_RT());			
			pipeIDEX.setWriteReg_15_11Write(instr.getReg_RD());			
			pipeIDEX.setFunctionWrite(instr.getReg_Funct());
		}
		if(instr.getOpCode() == 0x20 ) {					//Load
			pipeIDEX.setRegDstWrite(0);
			pipeIDEX.setALUOp1Write(0);
			pipeIDEX.setALUOp0Write(0);
			pipeIDEX.setALUSrcWrite(1);
			pipeIDEX.setMemReadWrite(1);
			pipeIDEX.setMemWriteWrite(0);
			pipeIDEX.setRegWriteWrite(1);
			pipeIDEX.setMemToRegWrite(1);
			pipeIDEX.setIncrPCWrite(pipeIFID.getIncrPCRead());
			pipeIDEX.setReadReg1ValueWrite(Regs[instr.getReg_RS()]);			
			pipeIDEX.setReadReg2ValueWrite(Regs[instr.getReg_RT()]);
			pipeIDEX.setSEOffsetWrite(instr.getReg_Immed());
			pipeIDEX.setWriteReg_20_16Write(instr.getReg_RT());			
			pipeIDEX.setWriteReg_15_11Write(instr.getReg_RD());
		}
		if(instr.getOpCode()== 0x28) {					//Store
			pipeIDEX.setALUOp1Write(0);
			pipeIDEX.setALUOp0Write(0);
			pipeIDEX.setALUSrcWrite(1);
			pipeIDEX.setMemReadWrite(0);
			pipeIDEX.setMemWriteWrite(1);
			pipeIDEX.setRegWriteWrite(0);
			pipeIDEX.setIncrPCWrite(pipeIFID.getIncrPCRead());
			pipeIDEX.setReadReg1ValueWrite(Regs[instr.getReg_RS()]);			
			pipeIDEX.setReadReg2ValueWrite(Regs[instr.getReg_RT()]);
			pipeIDEX.setSEOffsetWrite(instr.getReg_Immed());
			pipeIDEX.setWriteReg_20_16Write(instr.getReg_RT());			
			pipeIDEX.setWriteReg_15_11Write(instr.getReg_RD());	

		}		
	}
	public void EX_stage() {									//calculate the data
		
		pipeEXMEM.setMemReadWrite(pipeIDEX.getMemReadRead());
		pipeEXMEM.setMemWriteWrite(pipeIDEX.getMemWriteRead());
		pipeEXMEM.setMemToRegWrite(pipeIDEX.getMemToRegRead());
		pipeEXMEM.setRegWriteWrite(pipeIDEX.getRegWriteRead());	
		pipeEXMEM.setSWValueWrite(pipeIDEX.getReadReg2ValueRead());
		pipeEXMEM.setWriteRegNumWrite(pipeIDEX.getWriteReg_15_11Read());
		//R-format
		if ((pipeIDEX.getALUOp1Read() == 1) &&(pipeIDEX.getALUOp0Read() == 0)) 
		{
			if(pipeIDEX.getFunctionRead() == 0x20){
			{										//add
				pipeEXMEM.setALUResultWrite(pipeIDEX.getReadReg1ValueRead() + pipeIDEX.getReadReg2ValueRead());
			}				
			if (pipeIDEX.getFunctionRead() == 0x22)
			{										//sub
				pipeEXMEM.setALUResultWrite(pipeIDEX.getReadReg1ValueRead() - pipeIDEX.getReadReg2ValueRead());
			}			
			if (pipeIDEX.getFunctionRead() == 00)
			{										//nop
				pipeEXMEM.setALUResultWrite(0);
			}
			
		}
		//lw/sw format
		if((pipeIDEX.getALUOp1Read() == 0) &&(pipeIDEX.getALUOp0Read() == 0)) 
		{
			pipeEXMEM.setALUResultWrite(pipeIDEX.getReadReg1ValueRead() + pipeIDEX.getSEOffsetRead());
			pipeEXMEM.setWriteRegNumWrite(pipeIDEX.getWriteReg_20_16Read());
		}
	 }
		
	}
	public void MEM_stage() {			
		pipeMEMWB.setMemToRegWrite(pipeEXMEM.getMemToRegRead());
		pipeMEMWB.setRegWriteWrite(pipeEXMEM.getRegWriteRead());
		//R-format
		if ((pipeEXMEM.getMemWriteRead() ==0) && (pipeEXMEM.getMemToRegRead()==0)){
				pipeMEMWB.setALUResultWrite(pipeEXMEM.getALUResultRead());
				pipeMEMWB.setWriteRegNumWrite(pipeEXMEM.getWriteRegNumRead());
			}
			//Load
		if ((pipeEXMEM.getMemWriteRead() ==0) &&(pipeEXMEM.getMemToRegRead()==1)){
				pipeMEMWB.setLWDataValueWrite(trimLoadData(pipeEXMEM.getALUResultRead()));
				pipeMEMWB.setALUResultWrite(pipeEXMEM.getALUResultRead());
				pipeMEMWB.setWriteRegNumWrite(pipeEXMEM.getWriteRegNumRead());
			}
			//Store
		if (pipeEXMEM.getMemWriteRead() == 1){
			pipeMEMWB.setALUResultWrite(trimStoreData(pipeEXMEM.getALUResultRead(),pipeEXMEM.getSWValueRead()));
			pipeMEMWB.setWriteRegNumWrite(pipeEXMEM.getWriteRegNumRead());
		}		    
		
	}
	public void WB_stage() {

		if((pipeMEMWB.getRegWriteRead() == 1) && (pipeMEMWB.getMemToRegRead() == 0)) {
			 {
				 //R-format				
				 Regs[pipeMEMWB.getWriteRegNumRead()] =  pipeMEMWB.getALUResultRead();				
			}
			if((pipeMEMWB.getRegWriteRead() == 1)&& (pipeMEMWB.getMemToRegRead() == 1)) {
				//lw
				Regs[pipeMEMWB.getWriteRegNumRead()] = pipeMEMWB.getLWDataValueRead();
			
			}
		}
	}
	public void Print_out_everything() {
		//Display_Main_Mem();						//Display Main_memory
		Display_Regs();							//Display registers
		System.out.println("\n"+"Clock Cycle: " + c +"\n");
	//IFID pipline
		System.out.println("IF/ID Write(written to by the IF stage)");
		System.out.println("Inst = " + Integer.toHexString(pipeIFID.getInstrWrite())+ "        IncrPC = " + Integer.toHexString(pipeIFID.getIncrPCWrite()));
		System.out.println("IF/ID Read(read by the IF stage)");
		System.out.println("Inst = " + Integer.toHexString(pipeIFID.getInstrRead())+ "      IncrPC = " + Integer.toHexString(pipeIFID.getIncrPCRead())+ "\n");
		
	//IDEX pipeline
		//Write
		System.out.println("ID/EX Write(written to by the ID stage)");
		System.out.println("Control:  RegDst = " + pipeIDEX.getRegDstWrite() + ", ALUOp = " + pipeIDEX.getALUOp1Write() +pipeIDEX.getALUOp0Write()+"   ALUSrc = "
			+ pipeIDEX.getALUSrcWrite()	+ ",  MemRead ="+pipeIDEX.getMemReadWrite() + ", MemWrite = " + pipeIDEX.getMemWriteWrite());
		System.out.println("MemToReg = " + pipeIDEX.getMemToRegWrite() + ", RegWrite = " + pipeIDEX.getRegWriteWrite());
		System.out.println("IncrPC = " +  Integer.toHexString(pipeIDEX.getIncrPCWrite()) + "     ReadReg1Value = " +Integer.toHexString(pipeIDEX.getReadReg1ValueWrite()) + 
							                        "       ReadRegValue2 = " + Integer.toHexString(pipeIDEX.getReadReg2ValueWrite()));
		System.out.println("SEOffset = " + Integer.toHexString(pipeIDEX.getSEOffsetWrite()) + "    WriteReg_20_16 = " + pipeIDEX.getWriteReg_20_16Write() +"          " +
				 "WriteReg_15_11 = " +  pipeIDEX.getWriteReg_15_11Write() + "         Function = " + Integer.toHexString(pipeIDEX.getFunctionWrite()) + "\n");
		//Read
		System.out.println("ID/EX Read(read by the EX stage)");
		System.out.println("Control:  RegDst = " + pipeIDEX.getRegDstRead() + ", ALUOp = " + pipeIDEX.getALUOp1Read()+pipeIDEX.getALUOp0Write() + ", MemRead = "
											+ pipeIDEX.getMemReadRead() + ", MemWrite = " + pipeIDEX.getMemWriteRead());
		System.out.println("MemToReg = " + pipeIDEX.getMemToRegRead() + ", RegWrite = " + pipeIDEX.getRegWriteRead());
		System.out.println("IncrPC = " +Integer.toHexString(pipeIDEX.getIncrPCRead()) + "     ReadReg1Value = " + Integer.toHexString(pipeIDEX.getReadReg1ValueRead()) + 
							"       ReadRegValue2 = " + Integer.toHexString(pipeIDEX.getReadReg2ValueRead()));
		System.out.println("SEOffset = " + Integer.toHexString(pipeIDEX.getSEOffsetRead()) + "    WriteReg_20_16 = " + pipeIDEX.getWriteReg_20_16Read()
				+ "        WriteReg_15_11 = " +  pipeIDEX.getWriteReg_15_11Read() + "         Function = " + Integer.toHexString(pipeIDEX.getFunctionRead()) + "\n");
		
	//EXMEM pipleine
		//Write
		System.out.println("EX/MEM Write(written to by the EX stage)");
		System.out.println("Control: MemRead = " + pipeEXMEM.getMemReadWrite() + ", MemWrite = " + pipeEXMEM.getMemWriteWrite() + "      MemTOReg = "
										+ pipeEXMEM.getMemToRegWrite() + "       RegWrite = " + pipeEXMEM.getRegWriteWrite());
		System.out.println("ALUResult = " + Integer.toHexString(pipeEXMEM.getALUResultWrite()) + "     SWValue = " + Integer.toHexString(pipeEXMEM.getSWValueWrite()) + "      WriteRegNum = "
						+ pipeEXMEM.getWriteRegNumWrite() + "\n");
		
	   //Read		
		System.out.println("EX/MEM Read(read by the MEM stage)");
		System.out.println("Control: MemRead = " + pipeEXMEM.getMemReadRead() + ", MemWrite = " + pipeEXMEM.getMemWriteRead() + "      MemTOReg = "
												+ pipeEXMEM.getMemToRegRead() + "       RegWrite = " + pipeEXMEM.getRegWriteRead());
		System.out.println("ALUResult = " + Integer.toHexString(pipeEXMEM.getALUResultRead()) + "     SWValue = " + Integer.toHexString(pipeEXMEM.getSWValueRead()) + "      WriteRegNum = "
						+ pipeEXMEM.getWriteRegNumRead() + "\n");
		
	//MEMWB pipeline
		//Write
		System.out.println("MEM/WB Write(written to by the MEM stage)");
		System.out.println("Control: MemTOReg = " + pipeMEMWB.getMemToRegWrite() + "       RegWrite = " + pipeMEMWB.getRegWriteWrite());
		System.out.println("LWDataValue = " + Integer.toHexString(pipeMEMWB.getLWDataValueWrite()) + "       ALUResult = " + Integer.toHexString(pipeMEMWB.getALUResultWrite())  
				             + "         WriteRegNum = " + pipeMEMWB.getWriteRegNumWrite() + "\n");
		//Read
		System.out.println("MEM/WB Read(read by the MEM stage)");
		System.out.println("Control: MemTOReg = " + pipeMEMWB.getMemToRegWrite() + "       RegWrite = " + pipeMEMWB.getRegWriteWrite());
		System.out.println("LWDataValue = " + Integer.toHexString(pipeMEMWB.getLWDataValueWrite()) + "       ALUResult = " + Integer.toHexString(pipeMEMWB.getALUResultWrite())  
				             + "         WriteRegNum = " + pipeMEMWB.getWriteRegNumWrite() + "\n");
		
	}
	public void Copy_write_to_read() {
		
	//IFID pipeline Write to Read	
		pipeIFID.setInstrRead(pipeIFID.getInstrWrite());
		pipeIFID.setIncrPCRead(pipeIFID.getIncrPCWrite());
		
	//IDEX pipeline Write To Read
		pipeIDEX.setRegDstRead(pipeIDEX.getRegDstWrite());
		pipeIDEX.setALUOp1Read(pipeIDEX.getALUOp1Write());
		pipeIDEX.setALUOp0Read(pipeIDEX.getALUOp0Write());
		pipeIDEX.setALUSrcRead(pipeIDEX.getALUSrcWrite());
		pipeIDEX.setMemReadRead(pipeIDEX.getMemReadWrite());
		pipeIDEX.setMemWriteRead(pipeIDEX.getMemWriteWrite());
		pipeIDEX.setRegWriteRead(pipeIDEX.getRegWriteWrite());
		pipeIDEX.setMemToRegRead(pipeIDEX.getMemToRegWrite());
		pipeIDEX.setIncrPCRead(pipeIDEX.getIncrPCWrite());			
		pipeIDEX.setReadReg1ValueRead(pipeIDEX.getReadReg1ValueWrite());			
		pipeIDEX.setReadReg2ValueRead(pipeIDEX.getReadReg2ValueWrite());
		pipeIDEX.setWriteReg_20_16Read(pipeIDEX.getWriteReg_20_16Write());			
		pipeIDEX.setWriteReg_15_11Read(pipeIDEX.getWriteReg_15_11Write());			
		pipeIDEX.setFunctionRead(pipeIDEX.getFunctionWrite());
		pipeIDEX.setSEOffsetRead(pipeIDEX.getSEOffsetWrite());
		
	//EXMEM pipeline Write to Read
		pipeEXMEM.setMemReadRead(pipeEXMEM.getMemReadWrite());
		pipeEXMEM.setMemWriteRead(pipeEXMEM.getMemWriteWrite());
		pipeEXMEM.setRegWriteRead(pipeEXMEM.getRegWriteWrite());
		pipeEXMEM.setMemToRegRead(pipeEXMEM.getMemToRegWrite());
		pipeEXMEM.setALUResultRead(pipeEXMEM.getALUResultWrite());
		pipeEXMEM.setSWValueRead(pipeEXMEM.getSWValueWrite());
		pipeEXMEM.setWriteRegNumRead(pipeEXMEM.getWriteRegNumWrite());
		
	//MEMWB pipeline Write to Read	
		
		pipeMEMWB.setRegWriteRead(pipeMEMWB.getRegWriteWrite());
		pipeMEMWB.setMemToRegRead(pipeMEMWB.getMemToRegWrite());
		pipeMEMWB.setALUResultRead(pipeMEMWB.getALUResultWrite());
		pipeMEMWB.setLWDataValueRead(pipeMEMWB.getLWDataValueWrite());
		pipeMEMWB.setWriteRegNumRead(pipeMEMWB.getWriteRegNumWrite());	
		
		
		
	}
	public void initialize_Main_Mem() {
		for(int i=0; i < 1024;i++) {
			for(int j=0; j<256;j++) {
				Main_Mem [i][j]= (j);
			//	slotData +=Integer.toHexString(Main_Memory[i][j])+" ";
			}
		//	System.out.println(slotData.toUpperCase()+"\n");
		}
	}
	public void initialize_Regs() {
		for(int i=0; i < Regs.length;i++) {
			 if(i == 0)
			 {
				 Regs[i]= 0;				 
			 }
			 if(i != 0){
				Regs[i]= 0x100 + i;
			//	slotData +=Integer.toHexString(Regs[i])+" ";
			}
		//	System.out.println(slotData.toUpperCase()+"\n");
		}
	}
	public void addInstructions() {	
		Instructions[0] =0xa1020000;
		Instructions[1] =0x810afffc;
		Instructions[2] =0x00831820;
		Instructions[3] =0x01263820;
		Instructions[4] =0x01224820;
		Instructions[5] =0x81180000;
		Instructions[6] =0x81510010;
		Instructions[7] =0x81510010;
		Instructions[8] =0x00624022;
		Instructions[9] =0x00000000;
		Instructions[10] =0x00000000;
		Instructions[11] =0x00000000;
		Instructions[12] =0x00000000;
	}
	public int trimLoadData(int ALUResult) {
		int result =0;
		for(int i=0; i < 1024;i++) {
			for(int j=0; j<256;j++) {			
			if(	Main_Mem [i][j] ==  ALUResult) {
				int found = Main_Mem[i][j];
				int mask = getBits(0,7);
				result = found & mask;
			}
		}
	  }
		return result;	
	}
	public int trimStoreData(int ALUResult, int storeValue) {
		
		String value1= Integer.toHexString(ALUResult);
		String Value2 =Integer.toHexString(storeValue);		
		int i = Integer.parseInt(value1)/100;
		int j = Integer.parseInt(value1)%100;
		int adjStoreValue = Integer.parseInt(Value2)%100;
	//	System.out.println("i:" + i);
	//	System.out.println("j:" +j);
		System.out.println("MEM"+ Main_Mem[i][j]);
		Main_Mem[i][j] = adjStoreValue;
	//	System.out.println(Integer.toHexString(Main_Mem[i][j]));
		return j;	
	}
	 public int getBits(int bit_from, int bit_upto) {
		int mask = 0;
	    for (int i = bit_from; i <= bit_upto ;i++ ) {
					mask|=1 << i;
			}	
			return mask;
		}			
	
	public void Display_Main_Mem() {
		String slotData ="";
		for(int i=0; i< 2 ;i++) {
			for(int j=0; j<256;j++) {
			//	Main_Memory [i][j]= (j);
			slotData +=Integer.toHexString(Main_Mem[i][j])+" ";
			}
		slotData = slotData+"\n";
		System.out.println(slotData.toUpperCase());
		}
	}
	public void Display_Regs() {
		System.out.println("Registers");
		for(int i=0; i< 32;i++ ) { 
				String slotData = Integer.toHexString(Regs[i]);
				System.out.println("Reg $"+ i+"  "+slotData);
				slotData ="";
		}
			
	}		
}