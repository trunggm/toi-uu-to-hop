package phuc.nn;

public class ChessBoard {
	public ChessBoard(){
		
	}
    public static String findPath(int[][] costBoard) {
    	String rs = "";
        String path;
        int columns = costBoard[0].length;
        int rows = costBoard.length;
        int[][] minimumPath = new int[rows][columns + 2]; // add 2 extra column for banned path to avoid going out of the board
        for (int i = 0; i < rows; ++i) { // hàm phạt cho 2 cột ngoài cùng bên trái và phải của bảng minimumpath.
            minimumPath[i][0] = Integer.MAX_VALUE;
            minimumPath[i][columns + 1] = Integer.MAX_VALUE;
        }
        for (int j = 1; j < columns + 1; ++j)
            minimumPath[0][j] = costBoard[0][j - 1]; // khởi tạo dòng đầu tiên của bảng minimumpath
        for (int i = 1; i < rows; ++i) { // tính toán toàn bộ bảng.
            for (int j = 1; j < columns + 1; ++j)
                minimumPath[i][j] = Math.min(minimumPath[i - 1][j - 1], Math.min(minimumPath[i - 1][j], minimumPath[i - 1][j + 1])) + costBoard[i][j - 1];
        }
        // tìm phương án tối ưu, chi phí tối thiểu.
        // tìm chi phí nhỏ nhất ở hàng dưới cùng
        int k = 1, min = minimumPath[rows - 1][1];
        for (int j = 2; j < columns + 1; ++j) {
            if (minimumPath[rows - 1][j] < min) {
                k = j;
                min = minimumPath[rows - 1][j];
            }
        }
        // print the table
        for(int i =0; i < rows; ++i){
            for(int j =1; j<columns +1;++j)
                System.out.printf("%4d", minimumPath[i][j]);
                System.out.println();
        }

        path = "[" + Integer.toString(rows - 1) + ", " + Integer.toString(k - 1) + "]"; // đây là điểm cuối trong hành trình. Để tìm hành trình full, chỉ cần lần ngược lên
        for(int i=rows -1; i >=1; --i){
            int minAbove = Integer.MAX_VALUE;//minimumPath[i-1][k-1];
            int currentkey = k+1;
            for(int j = k-1 ; j <= currentkey; ++j){
                if(minimumPath[i-1][j] < minAbove){
                    k = j;
                    minAbove = minimumPath[i-1][j];
                }
                //if(minAbove == minimumPath[i-1][currentkey -2]) k = currentkey - 2;
            }
            path = "[" + Integer.toString(i-1) + ", " + Integer.toString(k-1) + "] => " + path ;
        }
        rs += ("<ul><li>Chi phi nho nhat la " + min+"</li>");
        rs +=("<li>Hành trình " + path + "</li></ul>");
        return rs;
    }
    public static void main(String[] argv) {
        int[][] costboard= {{1, 2, 6, 7, 9},
                            {7, 6, 5, 6, 7},
                            {1, 2, 3, 4, 2},
                            {4, 7, 8, 7, 6}
                            };
        String rs = ChessBoard.findPath(costboard);
        System.out.println(rs);
        //String A = "ABDEHK";
        //String B = "AADEKH";
        //System.out.println("LCS =" + LCS.find(A.toCharArray(), B.toCharArray()));
    }
}
