package phuc.nn;

public class Compute {
	public Compute() {
		
	}
	
	public static String MatrixChainMultiplication(String data) {
		// TODO Auto-generated method stub
		//String data = "4,10,3,12,20,7";
		String rs = "";
		String[] strArray = data.split(",");
        int arr[] = new int[strArray.length];
        for(int i=0; i<strArray.length; i++)
        	arr[i] = Integer.parseInt(strArray[i]);
		MatrixChainMultiplication mmc = new MatrixChainMultiplication(arr);
		rs += mmc.showResult();
		return rs;
	}
	
	public static String LCS(String data) {
		String rs = "";
		String[] arr = data.split(" ");
		LCS lcs = new LCS();
		rs += lcs.find(arr[0].toCharArray(), arr[1].toCharArray());
		
		return rs;
		
	}
	
	public static String EditDistance(String data) {
		String rs = "";
		String[] arr = data.split(" ");
		EditDistance ed = new EditDistance(arr[0], arr[1], 1, 1, 1);
		rs += ed.traceBack();
		return rs;
	}
	
	public static String ChessBoard(String data) {
		String rs = "";
		String[] lines = data.split("\n");
		int num = lines.length;
		
		int[][] citys = new int[num][];
		for(int i=0; i<num; i++){
			String[] coor = lines[i].split(" ");
			citys[i] = new int[coor.length];
			for(int j=0; j<coor.length; j++){
				citys[i][j] = Integer.parseInt(coor[j]);
			}
		}
		
		ChessBoard ch = new ChessBoard();
		rs += ch.findPath(citys);
		return rs;
		
	}

}
