
public class DecodeInstr {

		private int opCode;
		private int reg_RS;
		private int reg_RT;
		private int reg_RD;
		private int reg_Funct;
		private int reg_Immed;
		
		public DecodeInstr(){
			opCode =0;
			reg_RS = 0;
			reg_RT = 0;
			reg_RD = 0;
			reg_Funct = 0;
			reg_Immed =0;
		}
		
	   public void Decode(int Instr) {
		int mask_opCode = getBits(26,31);
		opCode = (Instr & mask_opCode)>>>26;
		int mask_reg_RS = getBits(21,25);
		reg_RS = (Instr & mask_reg_RS)>>21;
		int mask_reg_RT = getBits(16,20);
		reg_RT = (Instr & mask_reg_RT)>>>16;	
		if(opCode == 0) {
			int mask_reg_RD = getBits(11,15);
			reg_RD = (Instr & mask_reg_RD)>>>11;
			int mask_reg_funct = getBits(0,5);
			reg_Funct = (Instr & mask_reg_funct)>>>0;
		}else {
			
			signExtend(Instr);
		}
	}

	   public int getBits(int bit_from, int bit_upto) {
			int mask = 0;
			for (int i = bit_from; i <= bit_upto ;i++ ) {
				mask|=1 << i;
			}	
			return mask;
		}
		public void signExtend(int instr) {					//sign-Extension
			int mask_reg_Immediate = getBits(0,15);
			int Immed = ((instr & mask_reg_Immediate)>>0);
			int mask_reg_sign = getBits(15,15);
			int reg_sign = (instr & mask_reg_sign);				
			int mask = reg_sign;
			for (int i = 16; i <= 31 ;i++ ) {
					mask|=1 << i;
			}
			if(reg_sign == 0) {
				reg_Immed = Immed;
			}else
			{
				reg_Immed = (Immed | mask);
			}
		}

		public int getOpCode() {
			return opCode;
		}

		public void setOpCode(int opCode) {
			this.opCode = opCode;
		}

		public int getReg_RS() {
			return reg_RS;
		}

		public void setReg_RS(int reg_RS) {
			this.reg_RS = reg_RS;
		}

		public int getReg_RT() {
			return reg_RT;
		}

		public void setReg_RT(int reg_RT) {
			this.reg_RT = reg_RT;
		}

		public int getReg_RD() {
			return reg_RD;
		}

		public void setReg_RD(int reg_RD) {
			this.reg_RD = reg_RD;
		}

		public int getReg_Funct() {
			return reg_Funct;
		}

		public void setReg_Funct(int reg_Funct) {
			this.reg_Funct = reg_Funct;
		}

		public int getReg_Immed() {
			return reg_Immed;
		}

		public void setReg_Immed(int reg_Immed) {
			this.reg_Immed = reg_Immed;
		}
}
