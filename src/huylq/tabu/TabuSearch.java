package huylq.tabu;

public class TabuSearch {
	public static int[] getBestNeighbour(TabuList tabuList,
            TSPEnvironment tspEnviromnet,
            int[] initSolution) {

        int[] bestSol = new int[initSolution.length]; //this is the best Solution So Far
        System.arraycopy(initSolution, 0, bestSol, 0, bestSol.length);
        int bestCost = tspEnviromnet.getObjectiveFunctionValue(initSolution);
        int city1 = 0;
        int city2 = 0;
        boolean firstNeighbor = true;

        for (int i = 1; i < bestSol.length - 1; i++) {
            for (int j = 2; j < bestSol.length - 1; j++) {
                if (i == j) {
                    continue;
                }

                int[] newBestSol = new int[bestSol.length]; //this is the best Solution So Far
                System.arraycopy(bestSol, 0, newBestSol, 0, newBestSol.length);

                newBestSol = swapOperator(i, j, initSolution); //Try swapping cities i and j
                // , maybe we get a bettersolution
                int newBestCost = tspEnviromnet.getObjectiveFunctionValue(newBestSol);

                if ((newBestCost < bestCost || firstNeighbor) && tabuList.tabuList[i][j] == 0) { //if better move found, store it
                    firstNeighbor = false;
                    city1 = i;
                    city2 = j;
                    System.arraycopy(newBestSol, 0, bestSol, 0, newBestSol.length);
                    bestCost = newBestCost;
                }

            }
        }

        if (city1 != 0) {
            tabuList.decrementTabu();
            tabuList.tabuMove(city1, city2);
        }
        return bestSol;

    }

    //swaps two cities
    public static int[] swapOperator(int city1, int city2, int[] solution) {
        int temp = solution[city1];
        solution[city1] = solution[city2];
        solution[city2] = temp;
        return solution;
    }

    public static String progress(int[][] data) {
    	String rs = "<ul style=\"list-style:none;\">";
        TSPEnvironment tspEnvironment = new TSPEnvironment();

        tspEnvironment.distances = data;
                
        for(int i=0; i<11;i++){
        	for(int j=0; j<11;j++){
        		System.out.print(tspEnvironment.distances[i][j]+" ");
        	}
        	System.out.print("\n");
        }
        //Between cities. 0,1 represents distance between cities 0 and 1, and so on.
        int t = 0;
        int bestCost = 0;
        int[] currSolution = new int[data.length+1];   //initial solution
        for(int i=0; i<data.length+1; i++){
        	if(i<data.length)	
        		currSolution[i] = i;
        	else
        		currSolution[i] = 0;
        }
        	
        //city numbers start from 0
        // the first and last cities' positions do not change
        int[] bestSol = new int[currSolution.length];
        
        int[] flag = new int[currSolution.length];
        do {

            t = bestCost;
            int numberOfIterations = 10000;
            int tabuLength = 20;
            TabuList tabuList = new TabuList(tabuLength);

            //int[] bestSol = new int[currSolution.length]; //this is the best Solution So Far
            System.arraycopy(currSolution, 0, bestSol, 0, bestSol.length);
            bestCost = tspEnvironment.getObjectiveFunctionValue(bestSol);
            int currCost;
            for (int SLL = 0; SLL < numberOfIterations; SLL++) { // perform iterations here

                currSolution = TabuSearch.getBestNeighbour(tabuList, tspEnvironment, currSolution);
                //printSolution(currSolution);
                currCost = tspEnvironment.getObjectiveFunctionValue(currSolution);

                //System.out.println("Current best cost = " + tspEnvironment.getObjectiveFunctionValue(currSolution));
                if (currCost < bestCost) {
                    System.arraycopy(currSolution, 0, bestSol, 0, bestSol.length);
                    bestCost = currCost;
                    System.arraycopy(bestSol, 0, flag, 0, flag.length);
                }
            }

//            System.out.println("Search done! \nBest Solution cost found = " + bestCost + "\nBest Solution :");
//           printSolution(flag);
            currSolution = bestSol;
        } while (t != bestCost);
        rs += "<li>Ket qua tot nhat = " + Integer.toString(t) + "</li><li>Giai phap tot nhat: </li>";
        rs += printSolution(flag) + "</ul>";
        return rs;

    }

    public static String printSolution(int[] solution) {
    	String rs = "<li>";
        for (int i = 0; i < solution.length; i++) {
            rs+=Integer.toString(solution[i]) + " ";
        }
        rs+="</li>";
        return rs;
    }
    
    public TabuSearch(){
    	
    }
}
