package dung.lx;

import java.util.ArrayList;
import java.util.Collections;


public class HanhTrinh {


    // Holds our tour of cities
    private ArrayList<ThanhPho> tour = new ArrayList<ThanhPho>();
    // Cache
    private int distance = 0;
    
    // Tạo 1 hành trình trống: tất cả các vị trí thành phố của biến tour đều là null
    public HanhTrinh(){
        for (int i = 0; i < QuanLyHanhTrinh.numberOfCities(); i++) {
            tour.add(null);
        }
    }
 
    // Tạo 1 hành trình "LÀ BẢN SAO" từ 1 hành trình
    @SuppressWarnings("unchecked")
	public HanhTrinh(ArrayList<ThanhPho> tour){
        this.tour = (ArrayList<ThanhPho>) tour.clone();
    }

    
    // Trả lại thông tin hành trình: dùng cho hàm main khi cần thiết lập phương án hiện tại coi như là best (tốt nhất) & pan moi
    public ArrayList<ThanhPho> getHanhTrinh(){
        return tour;
    }
    
    
    // Thiết lập một thành phố ở một vị trí nhất định trong một hành trình
    //Sau đổi chỗ 2 tp trong hàm main
    public void setThanhPho(int tourPosition, ThanhPho city) {
        tour.set(tourPosition, city);
        //distance = 0;
          
    }
    
    
     // Xác định thành phố tương ứng với vị trí 1 thành phố của hành trình
    //Sau cũng dùng trong hàm main để lấy vị trí để đổi chỗ
    public ThanhPho getThanhPho(int tourPosition) {
        return (ThanhPho)tour.get(tourPosition);
    }

    // Tạo 1 cá thể ngẫu nhiên
    public void taoCaThe() {
        // lẶP QUA TẤT CẢ THÀNH PHỐ DỰ ĐỊNH VÀ THÊM CHÚNG VÀO HÀNH TRÌNH
        for (int cityIndex = 0; cityIndex < QuanLyHanhTrinh.numberOfCities(); cityIndex++) {
          setThanhPho(cityIndex, QuanLyHanhTrinh.getThanhPho(cityIndex));
        }
        // sắp xếp lại ngẫu nhiên hành trình
        Collections.shuffle(tour);//shuffle(): trộn
    }

    // Tính số lượng thành phố của hành trình
    public int tourSize() {
        return tour.size();
    }

    
    
    // Tính tổng khoảng cách của hành trình
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Lặp hết các thành phố của Hành TRình
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // Get city we're traveling from
                ThanhPho fromThanhPho = getThanhPho(cityIndex);
                // ThanhPho we're traveling to
                ThanhPho destinationThanhPho;
                // Kiểm tra xem nếu đang không ở thành phố cuối cùng của Hành trình, 
                // nếu chúng ta thiết lập thành phố dự định cuối cùng của hành trình tới thành phố bắt đầu
                if(cityIndex+1 < tourSize()){
                    destinationThanhPho = getThanhPho(cityIndex+1);// Tính tiếp
                }
                else{
                    destinationThanhPho = getThanhPho(0);// Dừng: Thành phố bắt đầu
                }
                // Tính khoảng cách giữa 2 thành phố
                tourDistance += fromThanhPho.distanceTo(destinationThanhPho);
            }
            distance = tourDistance;
        }
        return distance;
    }

   
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getThanhPho(i)+"|";
        }
        return geneString;
    }    
}
