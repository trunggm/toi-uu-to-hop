package anh.ht;

import java.util.Random;

/**
 *
 * @author samir
 */
public class GiaiPhap {

    int[] cacmuchang; // kich thuoc cua cac muc ma chung phai duoc dat trong cac hop
    int kichthuoc; // cong suat cua moi hop
    int nNguyenLieus = 1; // so luong cac hop duoc yeu cau cho giai phap nay

    /** tao ra mot giap phap nhe nhang hon*/
    public GiaiPhap(int[] cacmuchang, int kichthuoc) {
        this.cacmuchang = cacmuchang; //ban se nhan duoc cac tai lieu tham khao, can than khong su dung no trong chuong trinh
        this.kichthuoc = kichthuoc;
        int soma = 0;
        for (int item: cacmuchang) {
            if ((soma + item) <= kichthuoc)
                soma += item;
            else {
                soma = item;
                nNguyenLieus++;
            }
        }
    }

    public GiaiPhap casa(GiaiPhap s2, int probDotBien) {
        int[] Concais = new int[this.cacmuchang.length];
        int[] nguoncosan = this.cacmuchang.clone();
        Random r = Main.r;
        int diem = r.nextInt(Concais.length);

        // sao chep phan nay den the he sau va rut ra nhung cai co san
        for (int i = 0; i < diem; i++) {
            Concais[i] = this.cacmuchang[i];
            TimKiem(cacmuchang[i], nguoncosan);
        }

        //sao chep phan thu hai cua s2 cho the he sau khi co san, neu khong danh so 0
        for (int i = diem; i < Concais.length; i++) {
            if (TimKiem(s2.cacmuchang[i], nguoncosan))
                Concais[i] = s2.cacmuchang[i];
            else
                Concais[i] = 0;
        }

        //Thay the so 0 voi nhung ca the con sot lai
        for (int i = 0; i < Concais.length; i++) {
            if (Concais[i] == 0)
                Concais[i] = Nguoncosantieptheo(nguoncosan);
        }

        //dot bien, neu no xay ra
        if (r.nextInt(100) < probDotBien) {
            int pos1 = r.nextInt(Concais.length);
            int pos2 = r.nextInt(Concais.length);
            int aux = Concais[pos1];
            Concais[pos1] = Concais[pos2];
            Concais[pos2] = aux;
        }
        return new GiaiPhap(Concais, this.kichthuoc);
    }

    private boolean TimKiem(int n, int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == n) {
                arr[i] = 0;
                return true;
            }
        return false;
    }

    private int Nguoncosantieptheo(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] != 0) {
                int n = arr[i];
                arr[i] = 0;
                return n;
            }
        return 0;
    }

    public String mostraGiaiPhap() {
        StringBuffer str = new StringBuffer("");
        String rs = "";
        rs += ("Doan gen tot nhat: " + this + "\n");
        rs += ("Phuong an tot nhat can " + this.nNguyenLieus + " nguyen lieu");
        rs += ("\n[ Nguyen lieu so 1 ] : ");
        int soma = 0;
        nNguyenLieus = 1;
        for (int item: cacmuchang) {
            if ((soma + item) <= kichthuoc) {
                soma += item;
                rs += (item + " ");
            } else {
                soma = item;
                nNguyenLieus++;
                rs += ("\n[ Nguyen lieu so  " + nNguyenLieus + " ] : " + item + " ");
            }
        }
        int chatthai;
        chatthai = Main.NguyenLieu * nNguyenLieus - Main.tong;
        rs += ("\n so luong chat thai la : " + chatthai + " ");
        return rs;
    }

    public String toString() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < cacmuchang.length; i++) {
            str.append(" " + cacmuchang[i]);
        }
        return str.toString();
    }

}