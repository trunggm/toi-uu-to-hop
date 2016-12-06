package Ga;

import java.util.ArrayList;
import java.util.Collections;

public class Tour{

    private ArrayList <City> tour = new ArrayList<City>();
    private double fitness = 0;
    private int distance = 0;
    
    // xây dựng 1 tour du lịch trống
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }
    
    public Tour(ArrayList <City> tour){
        this.tour = tour;
    }

    // tạo 1 cá thể ngẫu nhiên
    public void generateIndividual() {
        // quét tất cả các thành phố
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
          setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // Random các thành phố đã quét
        Collections.shuffle(tour);
    }

   // lấy 1 thành phố trong tour
    public City getCity(int tourPosition) {
        return (City)tour.get(tourPosition);
    }

    // giữ 1 thành phố đó cố định
    public void setCity(int tourPosition, City city) {
        tour.set(tourPosition, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }
    
  
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
        return fitness;
    }
    
    // Tính tổng khoảng cách của tour
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
          
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // Điểm đi
                City fromCity = getCity(cityIndex);
                // Điểm đến
                City destinationCity;
                // Kiểm tra thành phố có phải thành phố cuối không
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }
                // lấy khoẳng cách giữa 2 thành phố
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // lấy số thành phố của tour
    public int tourSize() {
        return tour.size();
    }
    
    // kiểm tra xem tour có chứa 1 thành phố
    public boolean containsCity(City city){
        return tour.contains(city);
    }
    
    @Override
    public String toString() {
        String geneString = " | ";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+" | ";
        }
        return geneString;
    }
}