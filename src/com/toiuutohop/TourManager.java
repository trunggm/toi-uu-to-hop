package com.toiuutohop;

import java.util.ArrayList;

public class TourManager {
	public TourManager () {
		
	}

    // List thành phố
    public static ArrayList <City> destinationCities = new ArrayList<City>();

    // thêm 1 thành phố điểm đến
    public static void addCity(City city) {
        destinationCities.add(city);
    }
    
    // lấy 1 thành phố
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }
    
    // lấy số của thành phố điểm đến
    public static int numberOfCities(){
        return destinationCities.size();
    }
}