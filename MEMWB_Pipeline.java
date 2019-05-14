
public class MEMWB_Pipeline {

	private int MemToRegWrite;
	private int MemToRegRead;
	private int RegWriteWrite;
	private int RegWriteRead;
	private int LWDataValueWrite;
	private int LWDataValueRead;
	private int ALUResultWrite;
	private int ALUResultRead;
	private int WriteRegNumRead;
	private int WriteRegNumWrite;
	
	public MEMWB_Pipeline() {
		MemToRegWrite = 0;
		MemToRegRead = 0;
		RegWriteWrite = 0;
		RegWriteRead = 0;
		LWDataValueWrite = 0;
		LWDataValueRead = 0;
		ALUResultWrite = 0;
		ALUResultRead = 0;
		WriteRegNumRead = 0;
		WriteRegNumWrite = 0;
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

	public int getLWDataValueWrite() {
		return LWDataValueWrite;
	}

	public void setLWDataValueWrite(int lWDataValueWrite) {
		LWDataValueWrite = lWDataValueWrite;
	}

	public int getLWDataValueRead() {
		return LWDataValueRead;
	}

	public void setLWDataValueRead(int lWDataValueRead) {
		LWDataValueRead = lWDataValueRead;
	}

	public int getALUResultWrite() {
		return ALUResultWrite;
	}

	public void setALUResultWrite(int aLUResultWrite) {
		ALUResultWrite = aLUResultWrite;
	}

	public int getALUResultRead() {
		return ALUResultRead;
	}

	public void setALUResultRead(int aLUResultRead) {
		ALUResultRead = aLUResultRead;
	}

	public int getWriteRegNumRead() {
		return WriteRegNumRead;
	}

	public void setWriteRegNumRead(int writeRegNumRead) {
		WriteRegNumRead = writeRegNumRead;
	}

	public int getWriteRegNumWrite() {
		return WriteRegNumWrite;
	}

	public void setWriteRegNumWrite(int writeRegNumWrite) {
		WriteRegNumWrite = writeRegNumWrite;
	}

}
