package com.toiuutohop;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import HieuBQ.QHD.tsp.Held_Karp;
import HieuBQ.QHD.tsp.Result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "qhdendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")
public class QHDEndpoint {

	@ApiMethod(name = "qhd", path="qhd",
			httpMethod = HttpMethod.GET)
	public Result qhd(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		String[] lines = input.split("\n");
		
		int numberCity = Integer.parseInt(lines[0]);
		
		int[][] citys = new int[numberCity][];
		for(int i=0; i<numberCity; i++){
			citys[i] = new int[numberCity];
			String[] coor = lines[i+1].split(" ");
			for(int j=0; j<numberCity; j++){
				citys[i][j] = Integer.parseInt(coor[j]);
			}
		}
		Held_Karp hk = new Held_Karp();
		Result rs = hk.HK(citys);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		return new Result(rs.path, rs.min, totalTime);
	}
	
}
