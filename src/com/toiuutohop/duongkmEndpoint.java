package com.toiuutohop;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import duong.km.ILPSolver;
import duong.km.Result;
/**
 * Defines endpoint functions APIs.
 */
@Api(name = "duongkmendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")

public class duongkmEndpoint {
	@ApiMethod(name = "ilp", path="ilp",
			httpMethod = HttpMethod.GET)
	public Result nhanhcan(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		ILPSolver ilp=new ILPSolver();
		String rs = ilp.compute(input);
		Result result = new Result(rs);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(totalTime) + "ms</h3>" + result.result; 
		return result;
	}
}
