package anh.ht;

import java.util.*;

public class Main {

    /** Creates a new instance of Main */
    public Main() {}

    public static int n; //=30;                         // so thanh phan
    public static int NguyenLieu; //=5600;                     // Kich thuoc nguyen lieu
    public static int[] DonDatHang; //={1820,1380,1380,1380,2200,2200,1520,1520,1710,1710,1820,2150,2150,2100,1820,1520,1930,1930,2000,1710,1820,1930,2050,2100,1560,1880,2150,2140,1880,2150};        // 1 danh sach theo thu tu tap tin da doc
    //public static DanSo[] DanSoAtual;  // danh sach dan so va giap phap hien tai
    public static int hatgiong = 232; // hat giong tao ra cac so ngau nhien
    public static Random r = new Random(hatgiong);
    public static int tong;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String gopchuoi = "5600,1820,1380,1380,1380,2200,2200,1520,1520,1710,1710,1820,2150,2150,2100,1820,1520,1930,1930,2000,1710,1820,1930,2050,2100,1560,1880,2150,2140,1880,2150";
        String[] stringArray0 = gopchuoi.split(",");
        String numberAsStrin = stringArray0[0];
        NguyenLieu = Integer.parseInt(numberAsStrin);
        n = (stringArray0.length - 1);
        DonDatHang = new int[n];

        for (int j = 1; j < n + 1; j++) {

            String numberAsString = stringArray0[j];

            DonDatHang[j - 1] = Integer.parseInt(numberAsString);
            tong = tong + DonDatHang[j - 1];
        }

        int SoLanTienHoa = 50;
        int SoLuongCaThe = 1000;
        int PhanTramDotBien = 5;
        boolean SuDungFFD = true;
        Main.r = new Random(Main.hatgiong);

        DanSo DanSoHienTai = new DanSo(Main.DonDatHang, SoLuongCaThe, Main.NguyenLieu, SuDungFFD); //list, soluongquanthe=1000,kich co nguyen lieu,khoi tao quan the dau tien hay khong
        // bo ghi chu dong nay cung cho thay dan so ban dau

        for (int i = 0; i < SoLanTienHoa; i++) {
            DanSoHienTai = DanSoHienTai.TienHoa(PhanTramDotBien);
            int Best = DanSoHienTai.CaThe[0].nNguyenLieus;
            System.out.println("Giai phap so " + (i + 1) + ": can  " + Best + " nguyen lieu\n");
        }
        DanSoHienTai.toString();
        String s = DanSoHienTai.CaThe[0].mostraGiaiPhap();
        System.out.println(s);
    }

    public static String compute(String data) {

        //String data = "5600,1820,1380,1380,1380,2200,2200,1520,1520,1710,1710,1820,2150,2150,2100,1820,1520,1930,1930,2000,1710,1820,1930,2050,2100,1560,1880,2150,2140,1880,2150";
        String rs = "";
    	String[] stringArray0 = data.split(",");
        String numberAsStrin = stringArray0[0];
        NguyenLieu = Integer.parseInt(numberAsStrin);
        n = (stringArray0.length - 1);
        DonDatHang = new int[n];

        for (int j = 1; j < n + 1; j++) {

            String numberAsString = stringArray0[j];

            DonDatHang[j - 1] = Integer.parseInt(numberAsString);
            tong = tong + DonDatHang[j - 1];
        }

        int SoLanTienHoa = 50;
        int SoLuongCaThe = 1000;
        int PhanTramDotBien = 5;
        boolean SuDungFFD = true;
        Main.r = new Random(Main.hatgiong);

        DanSo DanSoHienTai = new DanSo(Main.DonDatHang, SoLuongCaThe, Main.NguyenLieu, SuDungFFD); //list, soluongquanthe=1000,kich co nguyen lieu,khoi tao quan the dau tien hay khong
        // bo ghi chu dong nay cung cho thay dan so ban dau

        for (int i = 0; i < SoLanTienHoa; i++) {
            DanSoHienTai = DanSoHienTai.TienHoa(PhanTramDotBien);
            int Best = DanSoHienTai.CaThe[0].nNguyenLieus;
            System.out.println("Giai phap so " + (i + 1) + ": can  " + Best + " nguyen lieu\n");
        }
        DanSoHienTai.toString();
        String s = DanSoHienTai.CaThe[0].mostraGiaiPhap();
        rs += s;
        return rs;
    }

}