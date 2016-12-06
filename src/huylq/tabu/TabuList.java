package huylq.tabu;

public class TabuList {
	int [][] tabuList ;
    public TabuList(int numCities){
        tabuList = new int[numCities][numCities]; //city 0 is not used here, but left for simplicity
    }
    
    public void tabuMove(int city1, int city2){ //tabus the swap operation
        tabuList[city1][city2] += 18;
        tabuList[city2][city1]+= 18;
        
    }
    
    public void decrementTabu(){
        for(int i = 0; i<tabuList.length; i++){
           for(int j = 0; j<tabuList.length; j++){
            tabuList[i][j]-=tabuList[i][j]<=0?0:1;
         } 
        }
    }
}
