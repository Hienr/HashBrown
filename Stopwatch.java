public class Stopwatch {
	
	final long TO_SECOND = 1000000000;	//nanoseconds in a second
	
	private long start;
	private long end;
	private double elapsed;
	
	Stopwatch(){
		end = 0;
		elapsed = 0;
		start();
	}
	
	public String toString() {
		return sec_elapsed();
	}
	
	public String sec_elapsed() {
		return "Time elapsed: " + elapsed + " seconds.";
	}
	
	public void start() {
		start = System.nanoTime();
	}
	
	public void end() {
		end = System.nanoTime() - start;
		elapsed = (double) end / TO_SECOND;
	}
	
	public void print() {
		System.out.println("Time elapsed: " + elapsed + " seconds.");
	}
}
