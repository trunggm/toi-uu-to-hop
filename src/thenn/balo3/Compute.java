package thenn.balo3;

import java.io.IOException;

public class Compute {
	public Compute (){
		
	}
	
	public Result compute(int num, int weigh, String numArr, String numWeight) throws IOException {
		Balo3 bl3 = new Balo3();
		String rs = bl3.progress(num, weigh, numArr, numWeight);
		return new Result(rs);
	}
}
