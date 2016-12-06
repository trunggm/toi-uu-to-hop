package phuc.nn;

/**
 * package phucnguyen
 * Created by nohat
 * on 11/30/2016.
 */
public class LCS {
	public LCS () {
		
	}

    public static String find(char[] A, char[] B) {
    	String rs = "<ul>";
        int[][] LCS = new int[A.length + 1][B.length + 1];
        String[][] solution = new String[A.length + 1][B.length + 1];
        // if A is null then LCS of A, B =0
        for (int i = 0; i <= B.length; i++) {
            LCS[0][i] = 0;
            solution[0][i] = "0";
        }

        // if B is null then LCS of A, B =0
        for (int i = 0; i <= A.length; i++) {
            LCS[i][0] = 0;
            solution[i][0] = "0";
        }

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                    solution[i][j] = "diagonal";
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                    if (LCS[i][j] == LCS[i - 1][j]) {
                        solution[i][j] = "top";
                    } else {
                        solution[i][j] = "left";
                    }
                }
            }
        }
        // below code is to just print the result
        String x = solution[A.length][B.length];
        String answer = "";
        int a = A.length;
        int b = B.length;
        while (!x.equals("0")) {
            if (solution[a][b] == "diagonal") {
                answer = A[a - 1] + answer;
                a--;
                b--;
            } else if (solution[a][b] == "left") {
                b--;
            } else if (solution[a][b] == "top") {
                a--;
            }
            x = solution[a][b];
        }
        rs += ("<li>Day con chung lon nhat la: " + answer + "</li>");

        //for (int i = 0; i <= A.length; i++) {
        //   for (int j = 0; j <= B.length; j++) {
        //      System.out.print(" " + LCS[i][j]);
        //    }
        //   System.out.println();
        //}
        rs +=  "<li>LCS ="+LCS[A.length][B.length]+"</li>";
        rs += "</ul>";
        return rs;
    }

    /* public static void main(String[] args) {
         String A = "ACBDEA";
         String B = "ABCDA";
         System.out.println("LCS =" + find(A.toCharArray(), B.toCharArray()));
     }
     */
    public static void main(String[] argv) {

        String A = "ABDEHK";
        String B = "AADEKH";
        System.out.println(LCS.find(A.toCharArray(), B.toCharArray()));
    }

}