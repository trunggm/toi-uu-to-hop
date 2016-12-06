package phuong.hungarian;

public class compute {
	public Result Compute(double[][] data) {
    	Hungarian hbm = new Hungarian(data);
        int[] result = hbm.execute();
        String rs = "<ul style=\"list-style: none;\">";
        Double chiphi = new Double(0);
        for(int i = 1; i <= result.length; i++)
        {	
        	chiphi += data[i-1][result[i-1]];
            rs += "<li>Người "+ i + " làm công việc thứ " + Integer.toString(result[i-1]+1)+"</li>";
        }
        rs += "</ul>";
        rs = "<h3>Chi phí: "+Double.toString(chiphi)+"</h3>" + rs;
        return new Result(rs);
	}
}
