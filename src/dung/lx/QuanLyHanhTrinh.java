package dung.lx;

import java.util.ArrayList;

public class QuanLyHanhTrinh {
    
    //destinationCities: Mảng các Thành phố dự định
    private static ArrayList<ThanhPho> destinationCities = new ArrayList<ThanhPho>();

    // Thêm 1 thành phố dự định
    public static void addThanhPho(ThanhPho city) {
        destinationCities.add(city);
    }
    
    // Lấy 1 thành phố = cách dựa theo giá trị của biến index
    
    public static ThanhPho getThanhPho(int index){
        return (ThanhPho)destinationCities.get(index);
    }
    
    // Tính số lượng thành phố dự định
    public static int numberOfCities(){
        return destinationCities.size();
    } 
}
