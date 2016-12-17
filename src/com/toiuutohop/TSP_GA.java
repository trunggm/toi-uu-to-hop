package com.toiuutohop;

public class TSP_GA {
	public TSP_GA () {
		
	}

    public static void main(String[] args) {

        // tạo thành phố
		City city = new City(20833, 17100);
        TourManager.addCity(city);
        City city2 = new City(20900, 17066);
        TourManager.addCity(city2);
        City city3 = new City(21300, 13016);
        TourManager.addCity(city3);
        City city4 = new City(21600, 14150);
        TourManager.addCity(city4);
        City city5 = new City(21600, 14966);
        TourManager.addCity(city5);
        City city6 = new City( 21600, 16500);
        TourManager.addCity(city6);
        City city7 = new City(22183, 13133);
        TourManager.addCity(city7);
        City city8 = new City(22583, 14300);
        TourManager.addCity(city8);
        City city9 = new City(22683, 12716);
        TourManager.addCity(city9);
        City city10 = new City(23616, 15866);
        TourManager.addCity(city10);
        City city11 = new City(23700, 15933);
        TourManager.addCity(city11);
        City city12 = new City(23883, 14533);
        TourManager.addCity(city12);
        City city13 = new City( 24166, 13250);
        TourManager.addCity(city13);
        City city14 = new City(25149,12365);
        TourManager.addCity(city14);
        City city15 = new City(26133, 14500);
        TourManager.addCity(city15);
        City city16 = new City(26150, 10550);
        TourManager.addCity(city16);
        City city17 = new City(26283, 12766);
        TourManager.addCity(city17);
        City city18 = new City(26433, 13433);
        TourManager.addCity(city18);
        City city19 = new City(26550, 13850);
        TourManager.addCity(city19);
        City city20 = new City(26733, 11683);
        TourManager.addCity(city20);
        City city21 = new City(27026, 13051);
        TourManager.addCity(city21);
        City city22 = new City( 27096, 13415);
        TourManager.addCity(city22);
        City city23 = new City(  27153, 13203);
        TourManager.addCity(city23);
        City city24 = new City(27166, 9833);
        TourManager.addCity(city24);
        City city25 = new City(27233, 10450);
        TourManager.addCity(city25);
        City city26 = new City(27233, 11783);
        TourManager.addCity(city26);
        City city27 = new City(27266, 10383);
        TourManager.addCity(city27);
        City city28 = new City(27433, 12400);
        TourManager.addCity(city28);
        City city29 = new City( 27462, 12992);
        TourManager.addCity(city29); 	
    	
        // Khởi tạo population
        Population pop = new Population(1000, true);
        System.out.println("Chi phí trước: " + pop.getFittest().getDistance());

        // 2000 thế hệ
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 2000; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // In kết quả
        System.out.println("Chi phí sau: " + pop.getFittest().getDistance());
        System.out.println("Hành trình:");
        System.out.println( pop.getFittest ());
    }
    
    public static String compute(String data) {
    	String rs = "<ul style=\"list-style: none;\">";
    	// tạo thành phố
    	String[] arr = data.split("\n");
    	for(int i=0; i< arr.length; i++){
    		String[] arri = arr[i].split(" ");
    		City city = new City(Integer.parseInt(arri[0]), Integer.parseInt(arri[1]));
    		TourManager.addCity(city);
    	}
        
        // Khởi tạo population
        Population pop = new Population(1000, true);
        rs += ("<li>Chi phí trước: " + pop.getFittest().getDistance()+"</li>");

        // 2000 thế hệ
        pop = GA.evolvePopulation(pop);
        for (int i = 0; i < 2000; i++) {
            pop = GA.evolvePopulation(pop);
        }

        // In kết quả
        rs += ("<li>Chi phí sau: " + pop.getFittest().getDistance()+"</li>");
        rs += ("<li>Hành trình:</li>");
        rs +=("<li>" + pop.getFittest ()+"</li>");
    	return rs+"</ul>";
	}
}

