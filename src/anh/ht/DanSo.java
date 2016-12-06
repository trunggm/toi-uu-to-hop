package anh.ht;

import com.sun.org.apache.bcel.internal.generic.POP;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author samir
 */
public class DanSo {

    public GiaiPhap[] CaThe;

    /** tao mot dan  */
    public DanSo() {}

    public DanSo(int[] cacmuchang, int nCaThe, int kichtuocNguyenLieu, boolean SuDungFFD) //
        {
            this.CaThe = new GiaiPhap[nCaThe];
            //dang tao lap di lap lai cho den khi du so luong ca the
            /*
            //  giai phap samir
            for(int k=0; k<nCaThe;k++)
            {
            //Tao ra mot giai phap ngau nhien
            int[] temp = cacmuchang.clone();
            for(int i=0; i<temp.length; i++)
                {
                Random rand = Main.r;
                int r1, r2, aux;
                r1 = rand.nextInt(temp.length);
                r2 = rand.nextInt(temp.length);
                aux = temp[r1];
                temp[r1] = temp[r2];
                temp[r2] = aux;
                }
            this.CaThe[k] = new GiaiPhap(temp, kichtuocNguyenLieu);
                //System.out.print("Indiv?duo "+k+" NguyenLieuS:"+this.CaThe[k].nNguyenLieus+" Cromossomo:");
                //for(int i=0; i<temp.length;i++){
                //    System.out.print(" "+temp[i]);
                //}
                //System.out.println();
            }
             */

            //giai phap Claudinei:
            int[] temp = new int[cacmuchang.length];
            int soma = 0;
            int plus = 0;
            // Neu su dung giap phap dau tien
            if (SuDungFFD) {
                temp = ApDungFFD(cacmuchang, kichtuocNguyenLieu);
                this.CaThe[0] = new GiaiPhap(temp, kichtuocNguyenLieu);
                plus = 1; // gia tri bat dau bang 1
            }

            for (int k = (0 + plus); k < nCaThe; k++) {
                temp = new int[cacmuchang.length];
                for (int i = 0; i < temp.length; i++) temp[i] = 0;
                for (int i = 0; i < temp.length; i++) {
                    Random rand = Main.r;
                    int r;
                    r = rand.nextInt(temp.length);
                    while (temp[r] != 0) r = (r + 1) % temp.length;
                    temp[r] = cacmuchang[i];
                }
                this.CaThe[k] = new GiaiPhap(temp, kichtuocNguyenLieu);
            }
        }

    // sap xep ca giap phap tu tot nhat den xau nhat
    public void SapXep() {
        // sap xep cac giap phap theo thu tu so nguyen lieu
        Arrays.sort(CaThe, new Comparator < GiaiPhap > () {
            public int compare(GiaiPhap s1, GiaiPhap s2) {
                return s1.nNguyenLieus - s2.nNguyenLieus;
            }
        });
    }

    public DanSo TienHoa(int PhanTramDotBien) {
        // lai tao, tien hoa, tao ra cac giong moi
        DanSo quanTheMoi = new DanSo();
        quanTheMoi.CaThe = new GiaiPhap[this.CaThe.length];
        //Tao cung 1 so yeu to
        int ChieuDai = quanTheMoi.CaThe.length;
        // giu cac ca the tot
        quanTheMoi.CaThe[0] = CaThe[0];
        for (int i = 1; i < ChieuDai; i++) {
            int pai1 = TaoRandom(ChieuDai);
            int pai2;
            do {
                pai2 = TaoRandom(ChieuDai);
            } while (pai2 == pai1);
            quanTheMoi.CaThe[i] = CaThe[pai1].casa(CaThe[pai2], PhanTramDotBien);
        }
        quanTheMoi.SapXep();
        return quanTheMoi;
    }

    //tao 80 % co hoi ngau nhien la it hon s voi 20 phamn tram toi da
    private int TaoRandom(int max) {
        max--;
        Random r = Main.r;
        Double rand = r.nextDouble();
        if (rand < 0.8f) {
            rand = rand / 4;
            rand *= (double) max;
            return (int) Math.round(rand);
        }
        rand = ((rand - 0.8) * 5);
        rand *= (double) max;
        /*if(rand>=(max-1)){
            //System.out.println(rand); 
            rand = (double) (max-1);
        }
        */
        return (int) Math.round(rand);
    }

    public String toString() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < CaThe.length; i++) {
            System.out.println("[Ca the " + (i + 1) + ": " + CaThe[i].nNguyenLieus + " nquyen lieu  ] " + CaThe[i].toString() + "\n");
        }
        return str.toString();
    }

    /*
     * mot phuong phap ma phai mat mot mang cac mat hang va gia tri cua thanh va tra lai cho don hang sau
     * ap dung thuat toan  First Fit Decreasing.
     */
    public int[] ApDungFFD(int[] cacmuchang, int kichtuocNguyenLieu) {
        int NguyenLieus[][] = new int[cacmuchang.length][cacmuchang.length]; // Matriz sendo o vetor da NguyenLieus que possui v?rios cacmuchang associados
        int somas[] = new int[cacmuchang.length]; // Vetor com a soma dos cacmuchang e cada uma das NguyenLieus
        int b = 0; // dem cac thanh nguyen lieu
        int j = 0; //Cac muc dem o trong nguyen lieu
        boolean achouNguyenLieu = false;
        for (int i = 0; i < cacmuchang.length; i++) {
            // voi nhu cau phuong an dau tien phu hop
            while (!achouNguyenLieu) {
                if (somas[b] + cacmuchang[i] <= kichtuocNguyenLieu) {
                    j = 0;
                    while (NguyenLieus[b][j] != 0) j++; //Toi nghi diem cua ma tran ma ban nen viet
                    somas[b] = somas[b] + cacmuchang[i];
                    NguyenLieus[b][j] = cacmuchang[i];
                    achouNguyenLieu = true;
                } else {
                    b++;
                    if (somas[b] == 0) { // neu ban o day co nghia la khong tim thay phong trong bat ky hop
                        somas[b] = cacmuchang[i];
                        NguyenLieus[b][0] = cacmuchang[i];
                        achouNguyenLieu = true;
                    }
                }
            }
            b = 0;
            achouNguyenLieu = false;
        }

        //hop ma tran in
        //for(int u=0;u<cacmuchang.length;u++) {
        //    for(int v=0;v<cacmuchang.length;v++) {
        //        System.out.print(NguyenLieus[u][v] + " ");
        //    }
        //    System.out.println();
        //}

        // bien doi ma tran thanh mot mang de quay tro lai
        b = 0;
        j = 0;
        int i = 0;
        int[] cacmuchangFFD = new int[cacmuchang.length];
        while (NguyenLieus[b][j] != 0) {
            while (NguyenLieus[b][j] != 0) {
                cacmuchangFFD[i] = NguyenLieus[b][j];
                i++;
                j++;
            }
            j = 0;
            b++;
        }
        //System.out.println("Phuong Phap vec to:");
        //for(i=0;i<cacmuchangFFD.length;i++){
        //    System.out.print(cacmuchangFFD[i]+" ");
        //}

        return cacmuchangFFD;
    }

}