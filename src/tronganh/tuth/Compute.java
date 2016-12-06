package tronganh.tuth;

import java.util.Map;
import java.util.Map.Entry;

public class Compute {
    public Compute() {

    }

    public Result ComputeCuttingStock(String data) {
        String gopchuoi = new String(data);
        String[] stringArray0 = gopchuoi.split(",");
        String numberAsStrin = stringArray0[0];
        int kichthuocnguyenlieu = Integer.parseInt(numberAsStrin);
        int n = (stringArray0.length - 1) / 2;
        int[] kichthuocmiengs = new int[n];
        int[] soluongs = new int[n];

        for (int j = 1; j < n + 1; j++) {

            String numberAsString = stringArray0[j];

            kichthuocmiengs[j - 1] = Integer.parseInt(numberAsString);
        }
        for (int j = n + 1; j < stringArray0.length; j++) {
            String numberAsString0 = stringArray0[j];
            soluongs[j - (n + 1)] = Integer.parseInt(numberAsString0);
        }
        int i = 0;
        Map < Integer, Integer > map;
        CuttingStock cuttingStock = new CuttingStock(kichthuocnguyenlieu, kichthuocmiengs, soluongs);
        String result = "<ul style=\"list-style:none;\">";
        while (cuttingStock.hasMoreCThanhNguyenLieus()) {
            result += "<li><b>CThanhNguyenLieu so " + Integer.toString(i)+":</b><br> ";
            map = cuttingStock.nextCThanhNguyenLieu();
            for (Entry < Integer, Integer > entry: map.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                result += Integer.toString(key) + "  *  " + Integer.toString(value);
            }
            result += "</li>";
            i++;
        }
        result += "</ul>";
        return new Result(result);
    }
}