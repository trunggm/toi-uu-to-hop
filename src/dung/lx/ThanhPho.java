package dung.lx;

public class ThanhPho {
    int x;
    int y;
    
    // Tạo 1 thành phố ngẫu nhiên có tọa độ từ [0.0->1.0]*200
    public ThanhPho(){
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }
    
    // Tạo 1 thành phố đã chọn tọa độ x, y cố định
    public ThanhPho(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Lấy tọa độ x
    public int getX(){
        return this.x;
    }
    
    // Lấy tọa độ y
    public int getY(){
        return this.y;
    }
    
    // Tính khoảng cách
    public double distanceTo(ThanhPho city){
        int xDistance = Math.abs(getX() - city.getX());// abs (absoluted): Trị tuyệt đối
        int yDistance = Math.abs(getY() - city.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        
        return distance;
    }
    
    //ghi đè
    @Override
    public String toString(){
        return getX()+", "+getY();
    }    
}
