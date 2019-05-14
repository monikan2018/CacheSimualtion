
public class IFID_Pipeline {
	private int IncrPCWrite;
	private int InstrWrite;
	private int IncrPCRead;
	private int InstrRead;
	
	public IFID_Pipeline() {
		IncrPCWrite =0;
		InstrWrite = 0;
		IncrPCRead = 0;
		InstrRead=  0;
	}
	
	
	public int getIncrPCWrite() {
		return IncrPCWrite;
	}

	public void setIncrPCWrite(int incrPCWrite) {
		IncrPCWrite = incrPCWrite;
	}

	public int getInstrWrite() {
		return InstrWrite;
	}

	public void setInstrWrite(int instrWrite) {
		InstrWrite = instrWrite;
	}

	public int getIncrPCRead() {
		return IncrPCRead;
	}

	public void setIncrPCRead(int incrPCRead) {
		IncrPCRead = incrPCRead;
	}

	public int getInstrRead() {
		return InstrRead;
	}

	public void setInstrRead(int instrRead) {
		InstrRead = instrRead;
	}

}