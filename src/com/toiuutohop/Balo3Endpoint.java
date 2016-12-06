package com.toiuutohop;

import java.io.IOException;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

import phuong.hungarian.compute;

import com.google.api.server.spi.config.Named;

import thenn.balo3.Compute;
import thenn.balo3.Result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "balo3endpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")

public class Balo3Endpoint {
	@ApiMethod(name = "bl3", path="bl3",
			httpMethod = HttpMethod.GET)
	public Result bl3(@Named("input") String input) throws IOException {
		String[] lines = input.split("\n");
		
		int n = Integer.parseInt(lines[0]);
		int m = Integer.parseInt(lines[1]);
		
		
		long startTime = System.currentTimeMillis();
		Compute bl3 = new Compute();
		Result rs = bl3.compute(n, m, lines[2], lines[3]);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		rs.result = "<h3>Time to run: " + Long.toString(totalTime) + " ms</h3>" + rs.result; 
		return rs;
	}
}
