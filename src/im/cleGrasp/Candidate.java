package im.cleGrasp;

import java.util.ArrayList;
import java.util.List;

public class Candidate {
	public double cost;
    public List<Integer> vector;

    public Candidate(List<Integer> vector, double cost){
        this.vector = vector;
        this.cost = cost;
    }

    public Candidate(){
        vector = new ArrayList<>();
        cost = 0.0;
    }

    public Candidate(Candidate candidate){
        this.vector = new ArrayList<>(candidate.vector);
        this.cost = candidate.cost;
    }
}
