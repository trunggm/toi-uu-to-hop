package com.toiuutohop;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import huylq.tabu.TabuSearch;
import huylq.tabu.Result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "tabusearchendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")
public class TabuSearchEndpoint {
	@ApiMethod(name = "tbs", path="tbs",
			httpMethod = HttpMethod.GET)
	public Result tbs(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		String[] lines = input.split("\n");
		
		int numberCity = lines.length;
		
		int[][] citys = new int[numberCity][];
		for(int i=0; i<numberCity; i++){
			String[] coor = lines[i].split(" ");
			citys[i] = new int[numberCity];
			for(int j=0; j<numberCity; j++)
				citys[i][j] = Integer.parseInt(coor[j]);
		}
		
		TabuSearch tbs = new TabuSearch();
		Result rs = new Result(tbs.progress(citys));
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		rs.result = "<h3>Time to run: " + Long.toString(totalTime) + " ms</h3>" + rs.result; 
		return rs;
	}
}
