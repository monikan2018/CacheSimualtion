
public class IDEX_Pipeline {
	
	private int regDstWrite;
	private int regDstRead;
	private int ALUSrcWrite;
	private int ALUSrcRead;
	private int ALUOp1Write;
	private int ALUOp1Read;
	private int ALUOp0Write;
	private int ALUOp0Read;
	private int MemReadWrite;
	private int MemReadRead;
	private int MemWriteWrite;
	private int MemWriteRead;
	private int MemToRegWrite;
	private int MemToRegRead;
	private int RegWriteWrite;
	private int RegWriteRead;
	private int IncrPCWrite;
	private int IncrPCRead;
	private int ReadReg1ValueWrite;
	private int ReadReg1ValueRead;
	private int ReadReg2ValueWrite;
	private int ReadReg2ValueRead;
	private int SEOffsetWrite;
	private int SEOffsetRead;
	private int WriteReg_20_16Write;
	private int WriteReg_20_16Read;
	private int WriteReg_15_11Write;
	private int WriteReg_15_11Read;
	private int FunctionWrite;
	private int FunctionRead;
	
	
	public IDEX_Pipeline() {
		regDstWrite = 0;
		regDstRead = 0;
		ALUSrcWrite = 0;
		ALUSrcRead = 0;
		ALUOp1Write = 0;
		ALUOp1Read = 0;
		ALUOp0Write = 0;
		ALUOp0Read = 0;
		MemReadWrite = 0;
		MemReadRead = 0;
		MemWriteWrite = 0;
		MemWriteRead = 0;
	    MemToRegWrite = 0;
		MemToRegRead = 0;
		RegWriteWrite = 0;
		RegWriteRead = 0;
		IncrPCWrite = 0;
		IncrPCRead = 0;
		ReadReg1ValueWrite = 0;
		ReadReg1ValueRead = 0;
		ReadReg2ValueWrite = 0;
		ReadReg2ValueRead = 0;
		SEOffsetWrite = 0;
		SEOffsetRead = 0;
		WriteReg_20_16Write = 0;
		WriteReg_20_16Read = 0;
		WriteReg_15_11Write = 0;
		WriteReg_15_11Read = 0;
		FunctionWrite = 0;
		FunctionRead = 0;
	}



	public int getALUOp1Write() {
		return ALUOp1Write;
	}



	public void setALUOp1Write(int aLUOp1Write) {
		ALUOp1Write = aLUOp1Write;
	}



	public int getALUOp1Read() {
		return ALUOp1Read;
	}



	public void setALUOp1Read(int aLUOp1Read) {
		ALUOp1Read = aLUOp1Read;
	}



	public int getALUOp0Write() {
		return ALUOp0Write;
	}



	public void setALUOp0Write(int aLUOp0Write) {
		ALUOp0Write = aLUOp0Write;
	}



	public int getALUOp0Read() {
		return ALUOp0Read;
	}



	public void setALUOp0Read(int aLUOp0Read) {
		ALUOp0Read = aLUOp0Read;
	}



	public int getRegDstWrite() {
		return regDstWrite;
	}



	public void setRegDstWrite(int regDstWrite) {
		this.regDstWrite = regDstWrite;
	}



	public int getRegDstRead() {
		return regDstRead;
	}



	public void setRegDstRead(int regDstRead) {
		this.regDstRead = regDstRead;
	}



	public int getALUSrcWrite() {
		return ALUSrcWrite;
	}



	public void setALUSrcWrite(int aLUSrcWrite) {
		ALUSrcWrite = aLUSrcWrite;
	}



	public int getALUSrcRead() {
		return ALUSrcRead;
	}



	public void setALUSrcRead(int aLUSrcRead) {
		ALUSrcRead = aLUSrcRead;
	}



	public int getMemReadWrite() {
		return MemReadWrite;
	}



	public void setMemReadWrite(int memReadWrite) {
		MemReadWrite = memReadWrite;
	}



	public int getMemReadRead() {
		return MemReadRead;
	}



	public void setMemReadRead(int memReadRead) {
		MemReadRead = memReadRead;
	}



	public int getMemWriteWrite() {
		return MemWriteWrite;
	}



	public void setMemWriteWrite(int memWriteWrite) {
		MemWriteWrite = memWriteWrite;
	}



	public int getMemWriteRead() {
		return MemWriteRead;
	}



	public void setMemWriteRead(int memWriteRead) {
		MemWriteRead = memWriteRead;
	}



	public int getMemToRegWrite() {
		return MemToRegWrite;
	}



	public void setMemToRegWrite(int memToRegWrite) {
		MemToRegWrite = memToRegWrite;
	}



	public int getMemToRegRead() {
		return MemToRegRead;
	}



	public void setMemToRegRead(int memToRegRead) {
		MemToRegRead = memToRegRead;
	}



	public int getRegWriteWrite() {
		return RegWriteWrite;
	}



	public void setRegWriteWrite(int regWriteWrite) {
		RegWriteWrite = regWriteWrite;
	}



	public int getRegWriteRead() {
		return RegWriteRead;
	}



	public void setRegWriteRead(int regWriteRead) {
		RegWriteRead = regWriteRead;
	}



	public int getIncrPCWrite() {
		return IncrPCWrite;
	}



	public void setIncrPCWrite(int incrPCWrite) {
		IncrPCWrite = incrPCWrite;
	}



	public int getIncrPCRead() {
		return IncrPCRead;
	}



	public void setIncrPCRead(int incrPCRead) {
		IncrPCRead = incrPCRead;
	}



	public int getReadReg1ValueWrite() {
		return ReadReg1ValueWrite;
	}



	public void setReadReg1ValueWrite(int readReg1ValueWrite) {
		ReadReg1ValueWrite = readReg1ValueWrite;
	}



	public int getReadReg1ValueRead() {
		return ReadReg1ValueRead;
	}



	public void setReadReg1ValueRead(int readReg1ValueRead) {
		ReadReg1ValueRead = readReg1ValueRead;
	}



	public int getReadReg2ValueWrite() {
		return ReadReg2ValueWrite;
	}



	public void setReadReg2ValueWrite(int readReg2ValueWrite) {
		ReadReg2ValueWrite = readReg2ValueWrite;
	}



	public int getReadReg2ValueRead() {
		return ReadReg2ValueRead;
	}



	public void setReadReg2ValueRead(int readReg2ValueRead) {
		ReadReg2ValueRead = readReg2ValueRead;
	}



	public int getSEOffsetWrite() {
		return SEOffsetWrite;
	}



	public void setSEOffsetWrite(int sEOffsetWrite) {
		SEOffsetWrite = sEOffsetWrite;
	}



	public int getSEOffsetRead() {
		return SEOffsetRead;
	}



	public void setSEOffsetRead(int sEOffsetRead) {
		SEOffsetRead = sEOffsetRead;
	}



	public int getWriteReg_20_16Write() {
		return WriteReg_20_16Write;
	}



	public void setWriteReg_20_16Write(int writeReg_20_16Write) {
		WriteReg_20_16Write = writeReg_20_16Write;
	}



	public int getWriteReg_20_16Read() {
		return WriteReg_20_16Read;
	}



	public void setWriteReg_20_16Read(int writeReg_20_16Read) {
		WriteReg_20_16Read = writeReg_20_16Read;
	}



	public int getWriteReg_15_11Write() {
		return WriteReg_15_11Write;
	}



	public void setWriteReg_15_11Write(int writeReg_15_11Write) {
		WriteReg_15_11Write = writeReg_15_11Write;
	}



	public int getWriteReg_15_11Read() {
		return WriteReg_15_11Read;
	}



	public void setWriteReg_15_11Read(int writeReg_15_11Read) {
		WriteReg_15_11Read = writeReg_15_11Read;
	}



	public int getFunctionWrite() {
		return FunctionWrite;
	}



	public void setFunctionWrite(int functionWrite) {
		FunctionWrite = functionWrite;
	}



	public int getFunctionRead() {
		return FunctionRead;
	}



	public void setFunctionRead(int functionRead) {
		FunctionRead = functionRead;
	}


}
