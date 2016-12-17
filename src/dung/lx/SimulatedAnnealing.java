package dung.lx;

import com.toiuutohop.City;
import com.toiuutohop.TourManager;

public class SimulatedAnnealing {

   // Tính xác suất chấp nhận
    public static double XacSuatChapNhan(int energy, int newEnergy, double temperature) {
        // Nếu phương án mới tốt hơn, chấp nhận nó <=> trả lại xs chấp nhận =1
        if (newEnergy < energy) {
            return 1.0;
        }
        // Nếu phương án mới tồi, tính Xác suất chấp nhận
        return Math.exp((energy - newEnergy) / temperature);
    }

    public static void main(String[] args) {
        // Create and add our cities
        
        
        ThanhPho city1 = new ThanhPho(60, 200);
        QuanLyHanhTrinh.addThanhPho(city1);
        ThanhPho city2 = new ThanhPho(180, 200);
        QuanLyHanhTrinh.addThanhPho(city2);
        ThanhPho city3 = new ThanhPho(80, 180);
        QuanLyHanhTrinh.addThanhPho(city3);
        ThanhPho city4 = new ThanhPho(140, 180);
        QuanLyHanhTrinh.addThanhPho(city4);
        ThanhPho city5 = new ThanhPho(20, 160);
        QuanLyHanhTrinh.addThanhPho(city5);
        ThanhPho city6 = new ThanhPho(100, 160);
        QuanLyHanhTrinh.addThanhPho(city6);
        ThanhPho city7 = new ThanhPho(200, 160);
        QuanLyHanhTrinh.addThanhPho(city7);
        ThanhPho city8 = new ThanhPho(140, 140);
        QuanLyHanhTrinh.addThanhPho(city8);
        ThanhPho city9 = new ThanhPho(40, 120);
        QuanLyHanhTrinh.addThanhPho(city9);
        ThanhPho city10 = new ThanhPho(100, 120);
        QuanLyHanhTrinh.addThanhPho(city10);
        ThanhPho city11 = new ThanhPho(180, 100);
        QuanLyHanhTrinh.addThanhPho(city11);
        ThanhPho city12 = new ThanhPho(60, 80);
        QuanLyHanhTrinh.addThanhPho(city12);
        ThanhPho city13 = new ThanhPho(120, 80);
        QuanLyHanhTrinh.addThanhPho(city13);
        ThanhPho city14 = new ThanhPho(180, 60);
        QuanLyHanhTrinh.addThanhPho(city14);
        ThanhPho city15 = new ThanhPho(20, 40);
        QuanLyHanhTrinh.addThanhPho(city15);
        ThanhPho city16 = new ThanhPho(100, 40);
        QuanLyHanhTrinh.addThanhPho(city16);
        ThanhPho city17 = new ThanhPho(200, 40);
        QuanLyHanhTrinh.addThanhPho(city17);
        ThanhPho city18 = new ThanhPho(20, 20);
        QuanLyHanhTrinh.addThanhPho(city18);
        ThanhPho city19 = new ThanhPho(60, 20);
        QuanLyHanhTrinh.addThanhPho(city19);
        ThanhPho city20 = new ThanhPho(160, 20);
        QuanLyHanhTrinh.addThanhPho(city20);

        // Thiết lập nhiệt độ khởi đầu
        double temp = 10000;

        // Tỷ lệ làm mát
        double coolingRate = 0.997;
        
        int n = 0;

        // Bắt đầu phương án bắt đầu
        HanhTrinh PhuongAnHienTai = new HanhTrinh();
        PhuongAnHienTai.taoCaThe();
        
        System.out.println("Chi phí/khoảng cách của phương án bắt đầu: " + PhuongAnHienTai.getDistance());

        // Set as current best
        HanhTrinh best = new HanhTrinh(PhuongAnHienTai.getHanhTrinh());
        
        // Lặp khi nào nhiệt độ còn > 1
        while (temp > 1) {
            
            // Tạo hành trình lân cận/hành trình mới
            HanhTrinh PhuongAnMoi = new HanhTrinh(PhuongAnHienTai.getHanhTrinh());

            // Lấy ngẫu nhiên 2 vị trí của tour
            int tourPos1 = (int) (PhuongAnMoi.tourSize() * Math.random());
            int tourPos2 = (int) (PhuongAnMoi.tourSize() * Math.random());

            // Lấy 2 thành phố ở 2 vị trí được chọn trong hành trình
            ThanhPho citySwap1 = PhuongAnMoi.getThanhPho(tourPos1);
            ThanhPho citySwap2 = PhuongAnMoi.getThanhPho(tourPos2);

            // Đổi chỗ 2 thành phố
            PhuongAnMoi.setThanhPho(tourPos2, citySwap1);
            PhuongAnMoi.setThanhPho(tourPos1, citySwap2);
            
            // Tính năng lượng của phương án
            int currentEnergy = PhuongAnHienTai.getDistance();
            int neighbourEnergy = PhuongAnMoi.getDistance();

            // a thuộc [0,1] < xs thì chuyển
            if (Math.random() < XacSuatChapNhan(currentEnergy, neighbourEnergy, temp)) {
                PhuongAnHienTai = new HanhTrinh(PhuongAnMoi.getHanhTrinh());
            }

            // Keep track of the best solution found
            if (PhuongAnHienTai.getDistance() < best.getDistance()) {
                best = new HanhTrinh(PhuongAnHienTai.getHanhTrinh());
            }
            
            // Cool system
            temp *= coolingRate;
            n += 1;
        }

        System.out.println("Chi phí/khoảng cách của phương án cuối/tốt nhất tìm được: " + best.getDistance());
        System.out.println("Hành trình cuối tìm được: " + best);
        System.out.println("Số vòng lặp: " + n);
    }
    
    public static String Compute(String data) {
		String rs = "<ul style=\"list-style: none;\">";
    	// tạo thành phố
    	String[] arr = data.split("\n");
    	for(int i=0; i< arr.length; i++){
    		String[] arri = arr[i].split(" ");
    		ThanhPho city = new ThanhPho(Integer.parseInt(arri[0]), Integer.parseInt(arri[1]));
    		QuanLyHanhTrinh.addThanhPho(city);
    	}
		// Thiết lập nhiệt độ khởi đầu
        double temp = 10000;

        // Tỷ lệ làm mát
        double coolingRate = 0.997;
        
        int n = 0;

        // Bắt đầu phương án bắt đầu
        HanhTrinh PhuongAnHienTai = new HanhTrinh();
        PhuongAnHienTai.taoCaThe();
        
        System.out.println("Chi phí/khoảng cách của phương án bắt đầu: " + PhuongAnHienTai.getDistance());

        // Set as current best
        HanhTrinh best = new HanhTrinh(PhuongAnHienTai.getHanhTrinh());
        
        // Lặp khi nào nhiệt độ còn > 1
        while (temp > 1) {
            
            // Tạo hành trình lân cận/hành trình mới
            HanhTrinh PhuongAnMoi = new HanhTrinh(PhuongAnHienTai.getHanhTrinh());

            // Lấy ngẫu nhiên 2 vị trí của tour
            int tourPos1 = (int) (PhuongAnMoi.tourSize() * Math.random());
            int tourPos2 = (int) (PhuongAnMoi.tourSize() * Math.random());

            // Lấy 2 thành phố ở 2 vị trí được chọn trong hành trình
            ThanhPho citySwap1 = PhuongAnMoi.getThanhPho(tourPos1);
            ThanhPho citySwap2 = PhuongAnMoi.getThanhPho(tourPos2);

            // Đổi chỗ 2 thành phố
            PhuongAnMoi.setThanhPho(tourPos2, citySwap1);
            PhuongAnMoi.setThanhPho(tourPos1, citySwap2);
            
            // Tính năng lượng của phương án
            int currentEnergy = PhuongAnHienTai.getDistance();
            int neighbourEnergy = PhuongAnMoi.getDistance();

            // a thuộc [0,1] < xs thì chuyển
            if (Math.random() < XacSuatChapNhan(currentEnergy, neighbourEnergy, temp)) {
                PhuongAnHienTai = new HanhTrinh(PhuongAnMoi.getHanhTrinh());
            }

            // Keep track of the best solution found
            if (PhuongAnHienTai.getDistance() < best.getDistance()) {
                best = new HanhTrinh(PhuongAnHienTai.getHanhTrinh());
            }
            
            // Cool system
            temp *= coolingRate;
            n += 1;
        }

        rs += ("<li>Chi phí/khoảng cách của phương án cuối/tốt nhất tìm được: " + best.getDistance() +"</li>");
        rs += ("<li>Hành trình cuối tìm được: " + best + "</li>");
        rs += ("<li>Số vòng lặp: " + n + "</li>");
		return rs+"</ul>";
	}
    
}
