package thenn.balo3;

import java.io.*;
import java.util.*;
/**
 *
 * @author TheNN
 */
public class Balo3 {
    public static int n;
    public static float m, cannang, giatriht, giatritu;
    public static float[] v = new float[100];
    public static float[] w = new float[100];
    public static int[] vat = new int[100];
    public static int[] vattu = new int[100];
    public static int[] id = new int[100];
    public static int[] vat_max = new int[100];
    /**
     * @param args the command line arguments
     */
    public static void batdau() {
        int i, j, tg;
        float t;
        giatritu = 0;
        cannang = 0;
        for (i = 1; i <= n; i++) {
            id[i] = i;
            vat[i] = 0;
            vattu[i] = 0;
        }
        for (i = 1; i <= n; i++) {
            for (j = i; j <= n; j++) {
                if (v[i] / w[i] < v[j] / w[j]) {
                    t = v[i];
                    v[i] = v[j];
                    v[j] = t;
                    t = w[i];
                    w[i] = w[j];
                    w[j] = t;
                    tg = id[i];
                    id[i] = id[j];
                    id[j] = tg;
                }
            }
        }

    }
    public static void Docdulieu(int num, int weigh, String numArr, String numWeight)
    throws IOException {
        n = num;
        System.out.println("So luong do vat la: " + n);
        m = weigh;
        System.out.println("Trong luong toi da cua ba lo la: " + m);

        String[] g = new String(numArr).split("\\s");
        if (g.length == n) {
            System.out.println("Gia tri cac vat la: ");
            for (int i = 1; i <= n; i++) {
                v[i] = Float.parseFloat(g[i - 1]);
                System.out.print(v[i] + "\t ");
            }
        } else {
            System.out.println("Du lieu sai");
        }
        String[] g2 = new String(numWeight).split("\\s");
        if (g2.length == n) {
            System.out.println("\nTrong luong cac vat la: ");
            for (int i = 1; i <= g2.length; i++) {
                w[i] = Float.parseFloat(g2[i - 1]);
                System.out.print(w[i] + "\t ");
            }
        } else {
            System.out.println("Du lieu sai");
        }
        //     System.out.println("\nDieu kien cac vat la: ");
        //     String[] g3=dong[4].split("\\s");
        //      if(g3.length==n){
        //    for (int i = 1; i <= n; i++) {
        //           vat_max[i]=Integer.parseInt(g3[i-1]);
        //       
        //           System.out.print(vat_max[i]+"\t ");
        //        }
        //      }
        //        else
        //        {
        //            System.out.println("Du lieu sai");}
        //   
        //    

    }

    public static void capnhat() {
        int i;
        if (giatriht > giatritu) {
            for (i = 1; i <= n; i++) vattu[i] = vat[i];
            giatritu = giatriht;
        }
    }
    public static void nhanhcan0_n(int i) {
        int j, t;
        t = (int)((m - cannang) / w[i]);
        for (j = t; j >= 0; j--) {
            vat[i] = j;
            cannang = cannang + w[i] * vat[i];
            giatriht = giatriht + v[i] * vat[i];
            //chon
            if (i == n || cannang >= m) capnhat();
            else if (giatriht + v[i + 1] * (m - cannang) / w[i + 1] > giatritu)
                nhanhcan0_n(i + 1);
            cannang = cannang - w[i] * vat[i];
            giatriht = giatriht - v[i] * vat[i];
            // khong khong

        }
    }
    public static void nhanhcan0_1(int i)

    {
        int j, t;
        t = (int)((m - cannang) / w[i]);
        for (j = t; j >= 0; j--) {
            vat[i] = j;
            if (vat[i] <= 1) {
                cannang = cannang + w[i] * vat[i];
                giatriht = giatriht + v[i] * vat[i];
                //chon
                if (i == n || cannang >= m) capnhat();
                else if (giatriht + v[i + 1] * (m - cannang) / w[i + 1] > giatritu)
                    nhanhcan0_1(i + 1);
                cannang = cannang - w[i] * vat[i];
                giatriht = giatriht - v[i] * vat[i];
                // khong chon
            }

        }

    }

    public static void nhanhcanbichan(int i) {
        int j, t;
        t = (int)((m - cannang) / w[i]);
        for (j = t; j >= 0; j--) {
            vat[i] = j;
            if (vat[i] <= vat_max[i]) {
                cannang = cannang + w[i] * vat[i];
                giatriht = giatriht + v[i] * vat[i];
                //chon
                if (i == n || cannang >= m) capnhat();
                else if (giatriht + v[i + 1] * (m - cannang) / w[i + 1] > giatritu)
                    nhanhcanbichan(i + 1);
                cannang = cannang - w[i] * vat[i];
                giatriht = giatriht - v[i] * vat[i];
                // khong chon
            }

        }
    }

    public static String inkq() {
        int i;
        String rs = "<ul style=\"list-style:none;\">";
        rs+="***Ket qua tinh toan***";
        rs+="<li>Tong gia tri cua cac do vat la: " + Float.toString(giatritu)+"</li>";
        rs+="<li>Phuong an toi uu la: </li>";
        for (i = 1; i <= n; i++) 
        	rs+="<li>So luong do vat " + Integer.toString(id[i]) + " la " + Integer.toString(vattu[i]) + "</li>";
        rs+="</ul>";
        return rs;
    }
    
    public String progress(int num, int weigh, String numArr, String numWeight) throws IOException{
    	Docdulieu(num, weigh, numArr, numWeight);
        batdau();
        System.out.println("\n balo 0_n");
        nhanhcan0_1(1);
        //        System.out.println("ba lo 0_n");
        //        nhanhcan0_n(1);
        //        System.out.println("Ba lo bi chan");
        //	nhanhcanbichan(1);
        return inkq();
    }
    
    public Balo3 () {
		
	}
//    public static void main(String[] args) throws IOException {
//        // TODO code application logic here
//        Docdulieu();
//        batdau();
//        System.out.println("\n balo 0_n");
//        nhanhcan0_1(1);
//        //        System.out.println("ba lo 0_n");
//        //        nhanhcan0_n(1);
//        //        System.out.println("Ba lo bi chan");
//        //	nhanhcanbichan(1);
//        inkq();
//    }

}