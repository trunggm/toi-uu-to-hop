package com.toiuutohop;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import taip.aco.Aco;
import taip.aco.Result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "acoendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")

public class acoEndpoint {
	@ApiMethod(name = "aco", path="aco",
			httpMethod = HttpMethod.GET)
	public Result qhd(@Named("input") String input) {
		String[] lines = input.split("\n");
		
		int numberRows = lines.length;
		
		double[][] data = new double[numberRows][];
		for(int i=0; i<numberRows; i++){
			String[] coor = lines[i].split(" ");
			int numberCols = coor.length;
			data[i] = new double[numberCols];
			for(int j=0; j<numberCols; j++){
				data[i][j] = Double.parseDouble(coor[j]);
				System.out.println(data[i][j]);
			}
		}
		
		Aco aco = new Aco();
		Result rs = new Result(aco.process(data));
		rs.result = rs.result; 
		return rs;
	}
}
