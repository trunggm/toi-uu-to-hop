package com.toiuutohop;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import kiennv.bee.BeeForTsp;
import kiennv.bee.Result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "beeendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")
public class BeeEndpoint {
	@ApiMethod(name = "bee", path="bee",
			httpMethod = HttpMethod.GET)
	public Result qhd(@Named("input") String input) {
		String[] lines = input.split("\n");
		
//		int numberRows = lines.length;
//		
//		double[][] data = new double[numberRows][];
//		for(int i=0; i<numberRows; i++){
//			String[] coor = lines[i].split(" ");
//			int numberCols = coor.length;
//			data[i] = new double[numberCols];
//			for(int j=0; j<numberCols; j++){
//				data[i][j] = Double.parseDouble(coor[j]);
//			}
//		}
		
		
		long startTime = System.currentTimeMillis();
		BeeForTsp bft = new BeeForTsp();
		Result rs = new Result(bft.progress(input));
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		String time = "<h3>Time to run: " + Long.toString(totalTime) + " ms</h3>";
		rs.result = rs.result; 
		return rs;
	}
}
