package im.cleGrasp;

import java.util.Vector;

import com.sun.org.apache.regexp.internal.recompile;

import sun.security.util.DisabledAlgorithmConstraints;
import sun.security.util.Length;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Compute {
	
	public Compute () {
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public List<Integer> twoOpt(List<Integer> perm, int i, int j){
        ArrayList<Integer> newPerm = new ArrayList<>(perm.subList(0, i));
        ArrayList<Integer> reversedPortion =  new ArrayList<>(perm.subList(i, j + 1));
        Collections.reverse(reversedPortion);
        newPerm.addAll(reversedPortion);
        newPerm.addAll(perm.subList(j + 1, perm.size()));

        return newPerm;
    }
	
	public double euc2d(double[] c1, double[] c2){
        return round(Math.sqrt(Math.pow(c1[0] - c2[0], 2.0) + Math.pow(c1[1] - c2[1], 2.0)), 0);
    }
	
	public double cost(List<Integer> permutation, double[][] cities){
        double distance = 0;
        for (int i = 0; i < permutation.size(); i++) {
            int c1 = i;
            int c2 = (i == permutation.size()-1)? permutation.get(0) : permutation.get(i+1);
            distance += euc2d(cities[c1], cities[c2]);
        }
        return round(distance, 4);
    }
	
	public List<Integer> stochasticTwoOpt(List<Integer> perm){
        Random rand = new Random();
		int c1 = rand.nextInt(perm.size());
        while (c1 == 0)
            c1 = rand.nextInt(perm.size());
        int c2 = rand.nextInt(perm.size());
        ArrayList<Integer> exclude = new ArrayList<>(Arrays.asList(c1, 0));
        exclude.add((c1==0) ? perm.size()-1 : c1-1);
        exclude.add((c1 == (perm.size()-1)) ? 0 : c1+1);
        while (exclude.contains(c2))
            c2 = rand.nextInt(perm.size());
        if (c2 < c1){
            int temp = c1;
            c1 = c2;
            c2 = temp;
        }
        return twoOpt(perm, c1, c2);
	}
	
	public Candidate localSearch(Candidate best, double[][] cities, int max_no_improv){
		int count = 0;
		while(count < max_no_improv) {
			Candidate candidate = new Candidate();
			candidate.vector = stochasticTwoOpt(best.vector);
			candidate.cost = cost(candidate.vector, cities);
			count = (candidate.cost < best.cost) ? 0:count+1;
			if(best.cost > candidate.cost){
				best = candidate;
			}
		}
		return best;
	}
	
	public Candidate contructRandomizedGreedySolution(double[][] cities, float alpha) {
		Random rand = new Random();
		Candidate cadidate = new Candidate();
		cadidate.vector.add(rand.nextInt(cities.length)); 
		
		List<Integer> allCities = new ArrayList<Integer>();
		for(int i = 0; i < cities.length; i++){
			allCities.add(i);
		}
		while(cadidate.vector.size() < cities.length) {
			List<Integer> candidates = new ArrayList<Integer>();
			for(int i=0; i <allCities.size(); i++){
				if(!cadidate.vector.contains(allCities.get(i))){
					candidates.add(allCities.get(i));
				}
			}
			List<Double> costs = new ArrayList<Double>();
			for(int i=0; i<candidates.size(); i++){
				double temp = euc2d(cities[cadidate.vector.size()-1], cities[candidates.get(i)]);
				costs.add(temp);
			}
			List<Integer> rcl = new ArrayList<Integer>();
			double min = Collections.min(costs);
			double max = Collections.max(costs);
			
			for(int i=0; i < costs.size(); i++){
				if(costs.get(i) <= (min + alpha*(max - min))){
					rcl.add(candidates.get(i));
				}
			}
			cadidate.vector.add(rcl.get(rand.nextInt(rcl.size())));
		}
		cadidate.cost = cost(cadidate.vector, cities);
		return cadidate;
	}
	
	public Candidate search(double[][] cities, int max_iter, int max_no_improve, float alpha) {
		Candidate best = new Candidate();
		best.cost = Double.MAX_VALUE;
		for(int i = 0; i < max_iter; i++){
			Candidate cadidate = contructRandomizedGreedySolution(cities, alpha);
			cadidate = localSearch(cadidate, cities, max_no_improve);
			if(best.cost > cadidate.cost){
				best = cadidate;
			}
		}
		
		return best;
	}

	public double[][] parseString2Data(String input) {
		// TODO Auto-generated method stub
		return null;
	}
	
}