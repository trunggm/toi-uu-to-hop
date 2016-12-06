package com.toiuutohop;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import im.cleGrasp.Candidate;
import im.cleGrasp.Compute;
import im.cleGrasp.result;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "graspendpoint", version = "v1",
	scopes = {Constants.EMAIL_SCOPE},
	clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	description = "Api for grasp")
public class GraspEndpoint {

	@ApiMethod(name = "grasp", path="grasp",
			httpMethod = HttpMethod.GET)
	public result grasp(@Named("input") String input) {
		long startTime = System.currentTimeMillis();
		String[] lines = input.split("\n");
		
		int numberCity = Integer.parseInt(lines[0]);
		
		double[][] citys = new double[numberCity][];
		for(int i=0; i<numberCity; i++){
			String[] coor = lines[i+1].split(" ");
			citys[i] = new double[2];
			citys[i][0] = Double.parseDouble(coor[0]);
			citys[i][1] = Double.parseDouble(coor[1]);
		}
		
		
		double[][] berlin52 = {{565,575},{25,185},{345,750},{945,685},{845,655},
	            {880,660},{25,230},{525,1000},{580,1175},{650,1130},{1605,620},
	            {1220,580},{1465,200},{1530,5},{845,680},{725,370},{145,665},
	            {415,635},{510,875},{560,365},{300,465},{520,585},{480,415},
	            {835,625},{975,580},{1215,245},{1320,315},{1250,400},{660,180},
	            {410,250},{420,555},{575,665},{1150,1160},{700,580},{685,595},
	            {685,610},{770,610},{795,645},{720,635},{760,650},{475,960},
	            {95,260},{875,920},{700,500},{555,815},{830,485},{1170,65},
	            {830,610},{605,625},{595,360},{1340,725},{1740,245}};
		double[][] cities = berlin52;
		
		int max_iter = 1000;
		int max_no_improve = 50;
		float greediness_factor = (float) 0.3;
		
		Compute com = new Compute();
		
		Candidate best = com.search(citys, max_iter, max_no_improve, greediness_factor);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
		return new result(best.cost, best.vector, totalTime);
	}
	
}
