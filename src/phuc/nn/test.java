package phuc.nn;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Compute cp = new Compute();
		String data = "4,10,3,12,20,7";
		String rs = cp.MatrixChainMultiplication(data);
		System.out.println(rs);
		
		String data2 = "ABDEHK AADEKH";
		String rs2 = cp.LCS(data2);
		System.out.println(rs2);
		
		String data3 = "CBADE ABCD";
		String rs3 = cp.EditDistance(data3);
		System.out.println(rs3);
		
		String data4 = "1 2 6 7 9\n7 6 5 6 7\n1 2 3 4 2\n4 7 8 7 6";
		String rs4 = cp.ChessBoard(data4);
		System.out.println(rs4);
	}

}
