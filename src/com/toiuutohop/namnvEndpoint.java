package com.toiuutohop;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import nam.nv.Compute;
import nam.nv.Result;
/**
 * Defines endpoint functions APIs.
 */
@Api(name = "namnvendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")

public class namnvEndpoint {
	@ApiMethod(name = "nhanhcan", path="nhanhcan",
			httpMethod = HttpMethod.GET)
	public Result nhanhcan(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		Compute cp=new Compute();
		String rs = cp.compute(input);
		Result result = new Result(rs);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(totalTime) + "ms</h3>" + result.result; 
		return result;
	}
}
