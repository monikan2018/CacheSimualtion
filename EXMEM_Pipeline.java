
public class EXMEM_Pipeline {

	private int MemReadWrite;
	private int MemReadRead;
	private int MemWriteWrite;
	private int MemWriteRead;
	private int MemToRegWrite;
	private int MemToRegRead;
	private int RegWriteWrite;
	private int RegWriteRead;
	private int ALUResultWrite;
	private int ALUResultRead;
	private int SWValueWrite;
	private int SWValueRead;
	private int WriteRegNumRead;
	private int WriteRegNumWrite;
	
	public EXMEM_Pipeline() {
		MemReadWrite = 0;
		MemReadRead = 0;
		MemWriteWrite = 0;
		MemWriteRead =0;;
		MemToRegWrite= 0;
		MemToRegRead = 0;
		RegWriteWrite =0;
		RegWriteRead =0;
		ALUResultWrite =0;
		ALUResultRead =0;
		SWValueWrite =0;
		SWValueRead =0;
		WriteRegNumRead =0;
		WriteRegNumWrite =0;
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

	public int getSWValueWrite() {
		return SWValueWrite;
	}

	public void setSWValueWrite(int sWValueWrite) {
		SWValueWrite = sWValueWrite;
	}

	public int getSWValueRead() {
		return SWValueRead;
	}

	public void setSWValueRead(int sWValueRead) {
		SWValueRead = sWValueRead;
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
