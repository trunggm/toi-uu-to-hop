package HieuBQ.QHD.tsp;

import java.util.*;
public class Held_Karp {
	public Held_Karp() {
		// TODO Auto-generated constructor stub
	}
   
    public Result HK(int[][] input)
    {
        int H=0;
        int X=0;
        int Z=0;
        int min;
        int[][] _luu=new int[65000][100];
        int[][] D=new int[65000][100];
        int[] _danhDau=new int[100];
        int d=0;
        int t;
        int[][] _tapHop=new int[65000][100];
        int m=input.length;
        
        int[][] C = input;
        
        System.out.println("Ma tan trong so:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(C[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        
        for (int i = 1; i < m; i++) {
            D[0][i]=C[0][i];
        }
        
       for (int s = 1; s < m-1; s++) {
            _tapHop=sinhTapHop.Sinh(m-1, s);
            for (int i = 0; i < sinhTapHop.h; i++) {
                for (int j = 0; j < s; j++) {
                    _danhDau[j]=_tapHop[i][j];
                }
                t=sinhTapHop.setID(_danhDau, s);
                
                int[] b=new int[m-1];
                for (int j = 1; j < m; j++) {
                    d=0;
                    while(d<s){
                        if (j==_danhDau[d]) {
                            b[j-1]=1;break;
                        }
                        d++;
                    }
                }
                
                for (int j = 1; j < m; j++) {
                    if (b[j-1]==0) {
                        int l=1;
                        min=5000;
                        Z=0;
                        while(l<m){
                            
                                if ((b[l-1]==1)&&(min>D[t-sinhTapHop.mux(l)][l]+C[l][j])) {
                                   min=D[t-sinhTapHop.mux(l)][l]+C[l][j];
                                   Z=l;
                                }
                            l++;
                        }
                        D[t][j]=min;
                        _luu[t][j]=Z;
                    }
                }
                
            }
            _tapHop=sinhTapHop.KhoiTaoLai();
        }
    
        _tapHop=sinhTapHop.Sinh(m-1, m-1);
        for (int i = 0; i < sinhTapHop.h; i++) {
                for (int j = 0; j < m-1; j++) {
                    _danhDau[j]=_tapHop[i][j];
                }}
            int u=sinhTapHop.setID(_danhDau, m-1);
        
            int j=1;
            min=500;
            while(j<m){
                if (min>D[u-sinhTapHop.mux(j)][j]+C[j][0]) {
                    min=D[u-sinhTapHop.mux(j)][j]+C[j][0];
                    H=j;
                }
                j=j+1;
            }
            
            int y=m-2;
            int[] _duongDi=new int[100];
            while(y>=0){
                _duongDi[y]=H;
                X=_luu[u-sinhTapHop.mux(H)][H];
                u=u-sinhTapHop.mux(H);
                H=X;
                y=y-1;
            }
            String path = "0->";
            for (int i = m-2; i >=0; i--) {
            	path += (_duongDi[i]+"->");
            }
            path += "0";
            return new Result(path, min);
    }
}
