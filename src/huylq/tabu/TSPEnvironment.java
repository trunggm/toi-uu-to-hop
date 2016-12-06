package huylq.tabu;

public class TSPEnvironment {
	public int[][] distances;

    public TSPEnvironment() {

    }

    public int getObjectiveFunctionValue(int solution[]) { //returns the path cost
        //the first and the last cities'
        //  positions do not change.
        // example solution : {0, 1, 3, 4, 2, 0}

        int cost = 0;

        for (int i = 0; i < solution.length - 1; i++) {
            cost += distances[solution[i]][solution[i + 1]];
        }

        return cost;

    }
}
