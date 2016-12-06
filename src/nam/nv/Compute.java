package nam.nv;

public class Compute {
	public Compute () {
		
	}
	
	public String compute(String data) {
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
		nhanhCan nc = new nhanhCan(citys);
		rs += nc.khoiTao();
		return rs;
	}
}
