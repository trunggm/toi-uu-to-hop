package com.toiuutohop;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import dung.lx.Result;
import dung.lx.Tester;
import dung.lx.SimulatedAnnealing;
/**
 * Defines endpoint functions APIs.
 */
@Api(name = "dunglxendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")
public class dunglxEndpoint {
	@ApiMethod(name = "luyenkim", path="luyenkim",
			httpMethod = HttpMethod.GET)
	public Result tspga(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		Tester ts = new Tester();
		//String test = "20833 17100\n20900 17066\n21300 13016\n21600 14150\n21600 14966\n21600 16500\n22183 13133\n22583 14300\n22683 12716\n23616 15866\n23700 15933\n23883 14533\n24166 13250\n25149 12365\n26133 14500\n26150 10550\n26283 12766\n26433 13433\n26550 13850\n26733 11683\n27026 13051\n27096 13415\n27153 13203\n27166 9833\n27233 10450\n27233 11783\n27266 10383\n27433 12400\n27462 12992";
		String rs = "";//TSP_GA.compute(test);
		System.out.println(rs);
		Result result = new Result(ts.result);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(ts.time) + "ms</h3>" + result.result; 
		
		return result;
	}
}
