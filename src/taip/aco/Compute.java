package taip.aco;

public class Compute {
	public Compute () {
		
	}
	
	public String compute(double[][] data) {
		String rs = "";
        Aco a = new Aco();
        a.map = data;
        System.out.println("Running...");
        long startTime = System.currentTimeMillis();
        rs+=a.Solve();
        long endTime = System.currentTimeMillis();
        rs = "<h3>Time to run: " + Long.toString(endTime - startTime) + " ms</h3>" + rs;
        return rs;
	}
}
