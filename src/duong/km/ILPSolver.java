package duong.km;

import java.io.*;
import java.util.*;
import java.text.*;
/**
 *
 * @author minhd
 */
public class ILPSolver {
    public ILPSolver() {
    }
    
    public String compute(String data){
    	String rs = "<ul style=\"list-style:none;\">";
        Programming var = new Programming(14);
        
        int max = 0;
        
        //String test = "x1 5 8\nx2 7 11\nx3 4 6\nx4 3 4";
        String[] arr = data.split("\n");
        for(int i=0; i<arr.length; i++){
        	String[] arri = arr[i].split(" ");
        	var.add(arri[0], Integer.parseInt(arri[1]), Integer.parseInt(arri[2]));
        }
        
//        var.add("x1", 2, 6);
//        var.add("x2", 2, 2);
//        var.add("x3", 3, 3);
//        var.add("x4", 2, 4);
//        var.add("x5", 1, 2);
//        var.add("x6", 2, 3);
//        var.add("x7", 2, 3);
//        var.add("x8", 3, 5);
//        var.add("x9", 4, 7);
//        var.add("x10", 3, 8);
//        var.add("x11", 1, 1);
//        var.add("x12", 3, 4);
//        var.add("x13", 4, 9);
//        var.add("x14", 4, 3);
//        var.add("x15", 4, 4);
//        var.add("x16", 2, 1);
//        var.add("x17", 3, 6);
//        var.add("x18", 2, 1);
//        var.add("x19", 2, 5);
//        var.add("x20", 1, 2);
//        var.add("x21", 3, 8);
//        var.add("x22", 3, 2);
        
        List<Item> itemList = var.calcSolution();
 
        if (var.isCalculated()) {
            NumberFormat nf  = NumberFormat.getInstance();
            
           rs += ("<li>He so ham muc tieu: </li><li>");
            for (Item item : itemList) {
                rs += (item.value + "\t");
            }
            rs += "</li>";
            rs += ("<li>He so ham dieu kien: </li><li>");
            for (Item item : itemList) {
                rs += (item.weight + "\t");
            }
            rs += "</li>";
            rs += ("<li>OUTPUT</li>");
            
            for (Item item : itemList){
                if (item.getInILP() > 0){
                    rs += ("<li>"+ item.getName() + " = "+ item.getInILP() + "</li>");
                    
                    max += item.value * item.getInILP();
                }
            }
        } else {
            rs += ("<li>Co gi do khong on</li>");            
        } 
        rs+=("<li>Max f(x) = " + max+"</li>");
        rs+="</ul>";
        return rs;
    }
 
    public static void main(String[] args) {
    	ILPSolver ilp = new ILPSolver();
    	String test = "x1 5 8\nx2 7 11\nx3 4 6\nx4 3 4";
    	String rs = ilp.compute(test);
    	System.out.println(rs);
    }
}
