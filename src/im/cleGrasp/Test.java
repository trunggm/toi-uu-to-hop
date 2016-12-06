package im.cleGrasp;

import java.util.Random;

public class Test {
	public static double[][] berlin52 = {{565,575},{25,185},{345,750},{945,685},{845,655},
            {880,660},{25,230},{525,1000},{580,1175},{650,1130},{1605,620},
            {1220,580},{1465,200},{1530,5},{845,680},{725,370},{145,665},
            {415,635},{510,875},{560,365},{300,465},{520,585},{480,415},
            {835,625},{975,580},{1215,245},{1320,315},{1250,400},{660,180},
            {410,250},{420,555},{575,665},{1150,1160},{700,580},{685,595},
            {685,610},{770,610},{795,645},{720,635},{760,650},{475,960},
            {95,260},{875,920},{700,500},{555,815},{830,485},{1170,65},
            {830,610},{605,625},{595,360},{1340,725},{1740,245}};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 5;
		int b = a << 2;
		int[] c = {1, 2};
		int[] d = c;
		Random rand = new Random();
		int  n = rand.nextInt(50) + 1;
		//System.out.println(round(200.23123, 0));
		
		int[][] f = {{1, 2}, {1, 2}};
		int[][] f1 = f;
		System.out.println(f1[1][1]);
		
		double[][] cities = berlin52;
		
		int max_iter = 100000;
		int max_no_improve = 50;
		float greediness_factor = (float) 0.3;
		
		Compute com = new Compute();
		
		Candidate best = com.search(cities, max_iter, max_no_improve, greediness_factor);
		
		System.out.println(best.cost);
		for(int i=0; i<best.vector.size();i++){
			System.out.println(best.vector.get(i));
		}
		
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	    
	    
	}
	
}
