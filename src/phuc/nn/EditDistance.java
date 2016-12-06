package phuc.nn;

/**
 * package phucnguyen
 * Created by nohat
 * on 11/22/2016.
 */
class EditDistance {
    private final int lengthOfSrc;
    private final int lengthOfDes;
    private char[] sourceString;
    private char[] destinationString;
    private final int deletionCost;
    private final int insertionCost;
    private final int substitutionCost;
    private int[][] levenshteinMatrix;

    EditDistance(String src, String des, int deCost, int inCost, int subCost) {
        sourceString = src.toCharArray();
        destinationString = des.toCharArray();
        deletionCost = deCost;
        insertionCost = inCost;
        substitutionCost = subCost;
        lengthOfSrc = src.length();
        lengthOfDes = des.length();
        makeTheMatrix();
    }

    private void makeTheMatrix() {
        levenshteinMatrix = new int[lengthOfSrc + 1][lengthOfDes + 1];
        for (int i = 0; i <= lengthOfSrc; ++i) levenshteinMatrix[i][0] = i * deletionCost;
        for (int j = 0; j <= lengthOfDes; ++j) levenshteinMatrix[0][j] = j * insertionCost;
        for (int i = 1; i <= lengthOfSrc; ++i)
            for (int j = 1; j <= lengthOfDes; ++j) {
                if (sourceString[i-1] == destinationString[j-1])
                    levenshteinMatrix[i][j] = levenshteinMatrix[i - 1][j - 1];
                else {
                    levenshteinMatrix[i][j] = minOfThreeNumber(levenshteinMatrix[i - 1][j - 1] + substitutionCost, levenshteinMatrix[i - 1][j] + deletionCost, levenshteinMatrix[i][j - 1] + insertionCost);
                }
            }
    }

    void showTheMatrix() {
        for (int i = 1; i <= lengthOfSrc; i++) {
            for (int j = 1; j <= lengthOfDes; ++j) {
                System.out.printf("%5d", levenshteinMatrix[i][j]);
            }
            System.out.print("\n");
        }
    }

    public String traceBack() {
    	String rs = "<ul>";
        rs += ("<li>Khoảng cách Levenshtein giữa chuỗi 1 và chuỗi 2 là " + levenshteinMatrix[lengthOfSrc][lengthOfDes] + "</li>");
        int i = lengthOfSrc, j = lengthOfDes;

        while (i != 0 || j != 0) { // dừng khi truy về đến [0][0]
        	rs += "<li>";
            if(i==0){
                rs += (" Insert ( " + i + ", " + destinationString[j-1] + " )");
                String temp = new String(sourceString);
                temp = temp.substring(0,i) + destinationString[j-1] + temp.substring(i);
                sourceString = temp.toCharArray();
                --j;
            } else if( j==0){
                rs += (" Delete ( " + i + " )");
                String temp = new String(sourceString);
                temp = temp.substring(0, i-1) + temp.substring(i);
                sourceString = temp.toCharArray();
                --i;
            } else {
                if (sourceString[i - 1] == destinationString[j - 1]) {
                    --i;
                    --j;
                } else {
                    rs += (new String(sourceString) + " --> ");
                    if (levenshteinMatrix[i][j] == levenshteinMatrix[i - 1][j - 1] + substitutionCost) {
                        rs += (" Replace ( " + i + ", " + destinationString[j - 1] + " )");
                        sourceString[i - 1] = destinationString[j - 1];
                        --i; --j;
                    } else if (levenshteinMatrix[i][j] == levenshteinMatrix[i - 1][j] + deletionCost) {
                        rs += (" Delete ( " + i + " )");
                        String temp = new String(sourceString);
                        temp = temp.substring(0, i-1) + temp.substring(i);
                        sourceString = temp.toCharArray();
                        --i;
                    } else {
                        rs += (" Insert ( " + i + ", " + destinationString[j-1] + " )");
                        String temp = new String(sourceString);
                        temp = temp.substring(0,i) + destinationString[j-1] + temp.substring(i);
                        sourceString = temp.toCharArray();
                        --j;
                    }
                    rs += ( " --> " + new String(sourceString) + "\n");
                }
            }
            rs += "</li>";
        }
        rs += "</ul>";
        return rs;
    }

    private static int minOfThreeNumber(int first, int second, int third) {
        return Math.min(third, Math.min(first, second));
    }
    public static void main(String[] argv) {
        String st1 = "CBADE";
        String st2 = "ABCD";
        EditDistance myED = new EditDistance(st1, st2, 1, 1, 1);
        myED.showTheMatrix();
        myED.traceBack();
    }

}