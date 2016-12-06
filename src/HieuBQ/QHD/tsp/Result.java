package HieuBQ.QHD.tsp;

public class Result {
	public String path;
	public Integer min;
	public long time;
	public Result() {
		
	}
	
	public Result(String path, Integer min) {
		this.path = path;
		this.min = min;
	}
	
	public Result(String path, Integer min, long time) {
		this.path = path;
		this.min = min;
		this.time = time;
	}
}
