package im.cleGrasp;

import java.util.List;

public class result {
	public double costs;
	public long time;
	public List<Integer> path;
	
	public result () {
		
	}
	
	public result (double cost, List<Integer> path, long time) {
		this.costs = cost;
		this.path = path;
		this.time = time;
	}
}
