package com.toiuutohop;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import anh.ht.Main;
import anh.ht.Result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "cuttingstockendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")

public class CuttingStockEndpoint {
	@ApiMethod(name = "cts", path="cts",
			httpMethod = HttpMethod.GET)
	public Result qhd(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		Main compute = new Main();
		String rs = compute.compute(input);
		Result result = new Result(rs);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(totalTime) + "</h3>" + result.result; 
		return result;
	}
	
}
