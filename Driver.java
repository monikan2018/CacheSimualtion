import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
			
		Driver me  = new Driver();
			me.doIt();
	}
	public void doIt() throws FileNotFoundException {
		PipelineSimu run = new PipelineSimu();
				run.Pipeline();
		
	}
}
