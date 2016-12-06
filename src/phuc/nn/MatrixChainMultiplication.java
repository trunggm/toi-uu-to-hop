package phuc.nn;

import im.cleGrasp.result;

class MatrixChainMultiplication {
    int[] dism;
    int numberOfMatrix;
    int[][] calMatrix;
    int[][] traceMatrix;

    public MatrixChainMultiplication(int[] inputArray) {
        dism = inputArray;
        numberOfMatrix = dism.length - 1;
        calMatrix = new int[100][100];
        makeTheTwoMatrix();

    }

    public void makeTheTwoMatrix() {
        traceMatrix = new int[100][100];
        int i, j, k, len;
        int x, p, q, r;
        for (i = 1; i <= numberOfMatrix; ++i)
            for (j = i; j <= numberOfMatrix; ++j)
                if (i == j) calMatrix[i][j] = 0;
                else calMatrix[i][j] = Integer.MAX_VALUE;
        for (len = 2; len <= numberOfMatrix; ++len)
            for (i = 1; i <= numberOfMatrix - len + 1; ++i) {
                j = i + len - 1;
                for (k = i; k <= j - 1; ++k) {
                    p = dism[i - 1];
                    q = dism[k];
                    r = dism[j];
                    x = calMatrix[i][k] + calMatrix[k + 1][j] + p * q * r;
                    if (x < calMatrix[i][j]) {
                        calMatrix[i][j] = x;
                        traceMatrix[i][j] = k;
                    }
                }
            }
    }
    public String traceBack(int i, int j){
    	String rs = "";
        int k;
        if(i==j) 
        	rs += ("matrix" + i);
        else {
            rs += (" (");
            k = traceMatrix[i][j];
            rs += traceBack(i, k);
            rs += (".");
            rs += traceBack(k+1,j);
            rs += (" )");
        }
        return rs;
    }
    public String showResult(){
    	String rs = "<ul>";
        rs+=("<li>So phep nhan toi thieu can thuc hien la " + calMatrix[1][numberOfMatrix]+"</li>");
        rs += "<li>"+traceBack(1, numberOfMatrix)+"</li></ul>";
        return rs;
    }
    public static void main(String[] argv) {
 
        int arr[] = {4, 10, 3, 12, 20, 7};
        MatrixChainMultiplication mmc = new MatrixChainMultiplication(arr);
        mmc.showResult();
    }
}