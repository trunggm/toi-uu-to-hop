package com.toiuutohop;

public class City {
    int x;
    int y;
    
    // xây dựng ngẫu nhiên thành phố
    public City(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }
    
    // xây dựng thành phố tại x và y
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    // Khoảng cách đến thành phố
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    @Override
    public String toString(){
        return getX()+", "+getY();
    }
}