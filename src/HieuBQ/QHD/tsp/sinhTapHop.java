package HieuBQ.QHD.tsp;

public class sinhTapHop {
    static int h;
    static int[] A=new int[50];
    static int n,k;
    static int[][] C=new int[65000][100];
    public static int[][] KhoiTaoLai(){
        int[][] B=new int[65000][100];
        return B;
    }
    static void TRY(int i){
        int j;
        for ( j = A[i-1]+1; j <=n-k+i ; j++) {
            A[i]=j;
            if(i==k){
                for (int l = 0; l <k; l++) {
                    C[h][l]=A[l+1];
                }
                h=h+1;
            }
            else {TRY(i+1);}
        }
    }
    public static int[][] Sinh(int N,int K){
        n=N;
        k=K;
        A[0]=0;
        h=0;
        TRY(1);
        return C;
    }
    public static int setID(int[] A,int k){
        int id=0;
        int t;
        for (int i = 0; i <k ; i++) {
            t=1;
            for (int j = 0; j < A[i]; j++) {
                t=2*t;
            }
            id=id+t;
        }
        return id/2;
    }
    public static int mux(int n){
        if (n==0) {
            return 1;
        }
        else{
        int t=1;
        for (int i = 0; i < n; i++) {
            t=t*2;
        }
        return t/2;
    }
    }
}
