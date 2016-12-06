package nam.nv;

public class nhanhCan {
    static int[][] Cost = {
        {
            0, 53, 5, 73, 8, 7, 100, 96, 40, 80, 70, 33, 20, 55, 89, 77
        }, {
            53, 0, 93, 93, 36, 4, 16, 35, 61, 7, 84, 13, 33, 47, 23, 18
        }, {
            5, 93, 0, 69, 46, 42, 93, 69, 100, 2, 5, 48, 85, 42, 2, 35
        }, {
            73, 93, 69, 0, 21, 93, 40, 86, 85, 79, 26, 5, 69, 53, 40, 48
        }, {
            8, 36, 46, 21, 0, 67, 90, 30, 35, 71, 92, 45, 73, 80, 31, 3
        }, {
            7, 4, 42, 93, 67, 0, 8, 28, 43, 19, 79, 61, 97, 63, 14, 100
        }, {
            100, 16, 93, 40, 90, 8, 0, 40, 87, 63, 46, 57, 39, 50, 25, 83
        }, {
            96, 35, 69, 86, 30, 28, 40, 0, 17, 16, 8, 38, 8, 77, 28, 93
        }, {
            40, 61, 100, 85, 35, 43, 87, 17, 0, 1, 52, 96, 35, 44, 36, 9
        }, {
            80, 7, 2, 79, 71, 19, 63, 16, 1, 0, 75, 71, 88, 67, 79, 51
        }, {
            70, 84, 5, 26, 92, 79, 46, 8, 52, 75, 0, 29, 23, 31, 72, 84
        }, {
            33, 13, 48, 5, 45, 61, 57, 38, 96, 71, 29, 0, 99, 24, 84, 47
        }, {
            20, 33, 85, 69, 73, 97, 39, 8, 35, 88, 23, 99, 0, 23, 35, 68
        }, {
            55, 47, 42, 53, 80, 63, 50, 77, 44, 67, 31, 24, 23, 0, 20, 66
        }, {
            89, 23, 2, 40, 31, 14, 25, 28, 36, 79, 72, 84, 35, 20, 0, 12
        }, {
            77, 18, 35, 48, 3, 100, 83, 93, 9, 51, 84, 47, 68, 66, 12, 0
        }
    };
    static int n = Cost.length;
    static int max = 0;
    static int[] X = new int[n + 1];
    static int[] _chiPhi = new int[n + 1];
    static int[] _danhDau = new int[n];
    static int[][] C = new int[n][n];
    static int[] _ketQua = new int[n + 1];
    static int min;
    
    public nhanhCan () {
		
	}
    
    public nhanhCan (int[][] data) {
		this.Cost = data;
	}
    
    public static String khoiTao() {
        String rs = "";

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = max + Cost[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
            	System.out.print(Cost[i][j] + " ");
                if ((i != j) && (Cost[i][j] == 0)) {
                    C[i][j] = max;
                } else {
                    C[i][j] = Cost[i][j];
                }
            }
            System.out.print("\n");
        }
        X[0] = 0;
        _danhDau[0] = 1;
        _chiPhi[0] = 0;
        min = max;
        TRY(1);
        rs += KetQua();
        return rs;
    }
    static void TRY(int i) {
        for (int j = 1; j < n; j++) {
            if (_danhDau[j] == 0) {
                X[i] = j;
                _chiPhi[i] = _chiPhi[i - 1] + C[X[i - 1]][j];
                if (_chiPhi[i] < min) {
                    if (i < n - 1) {
                        _danhDau[j] = 1;
                        TRY(i + 1);
                        _danhDau[j] = 0;
                    } else {
                        if (_chiPhi[n - 1] + C[X[n - 1]][0] < min) {
                            for (int k = 0; k < n + 1; k++) {
                                _ketQua[k] = X[k];
                            }
                            min = _chiPhi[n - 1] + C[X[n - 1]][0];
                        }

                    }
                }

            }
        }
    }
    static String KetQua() {
    	String rs = "<ul style=\"list-style:none;\">";
        if (min == max) {
            rs += ("<li>khong co loi giai</li>");
        } else {
        	rs += "<li>Best path:</li><li>";
            for (int i = 0; i < n + 1; i++) {
                rs += ("->" + _ketQua[i]);
            }
            rs += "</li>";
            rs +=("<li>Best value: " + min+"</li>");
        }
        rs += "</ul>";
        return rs;
    }
}