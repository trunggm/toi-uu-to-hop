package com.toiuutohop;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import phuc.nn.Compute;
import phuc.nn.Result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "phucnnendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")

public class PhucNNEnpoint {
	@ApiMethod(name = "lcs", path="lcs",
			httpMethod = HttpMethod.GET)
	public Result lcs(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		Compute cp=new Compute();
		String rs = cp.LCS(input);
		Result result = new Result(rs);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(totalTime) + "ms</h3>" + result.result; 
		return result;
	}
	
	@ApiMethod(name = "editdistance", path="editdistance",
			httpMethod = HttpMethod.GET)
	public Result editDistance(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		Compute cp=new Compute();
		String rs = cp.EditDistance(input);
		Result result = new Result(rs);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(totalTime) + "ms</h3>" + result.result; 
		return result;
	}
	
	@ApiMethod(name = "multimatrix", path="multimatrix",
			httpMethod = HttpMethod.GET)
	public Result multimatrix(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		Compute cp=new Compute();
		String rs = cp.MatrixChainMultiplication(input);
		Result result = new Result(rs);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(totalTime) + "ms</h3>" + result.result; 
		return result;
	}
	
	@ApiMethod(name = "chessboard", path="chessboard",
			httpMethod = HttpMethod.GET)
	public Result chessBoard(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		Compute cp=new Compute();
		String rs = cp.ChessBoard(input);
		Result result = new Result(rs);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		result.result = "<h3>Time to run: " + Long.toString(totalTime) + "ms</h3>" + result.result; 
		return result;
	}
}
