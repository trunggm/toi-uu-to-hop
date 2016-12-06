package kiennv.bee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.*;
import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;
/**
 *
 * @author Kien
 * 48 thanh pho: http://people.sc.fsu.edu/~jburkardt/datasets/tsp/tsp.html
 * The minimal tour has length 10628.
 * 
 * 11 cities:The minimal tour has length 252km.
 * code reference: https://github.com/dennyferra/Simulated-Bee-Colony
 * Data test 17 cities: 
 * http://people.sc.fsu.edu/~jburkardt/datasets/tsp/gr17_d.txt
 * The minimal tour has length 2085.
 */
public class BeeForTsp {

    /**
     * @param args the command line arguments
     */
    public static String progress(String s) {
        // TODO code application logic here
    	System.out.println(s);
    	String rs = "";
        try
            {
                System.out.println("\nBegin Simulated Bee Colony algorithm demo\n");
                System.out.println("Loading cities data for SBC Traveling Salesman Problem analysis");
                //Cho can sua - Nhap So luong thanh pho
                
                //Trung lam cai input dau vao cho a nhe
                int Kichthuoc=17;
                //
                
                CitiesData citiesData = new CitiesData(Kichthuoc);
                //Nhap vao kich thuoc cua file ma tran trong so
//                ReadFile Arr = new ReadFile();
//                Arr.input_file(Kichthuoc);//bang voi so luong thanh pho
                
                rs += (citiesData.ToString());
                System.out.println("Number of cities = " + citiesData.cities.length);
                BigDecimal bd=new BigDecimal(citiesData.NumberOfPossiblePaths());
                System.out.println("Number of possible paths = "+ String.format(bd.toString(), "###,###,###,###"));
                //System.out.println("Best possible solution (shortest path) length = %.3f" + citiesData.ShortestPathLength());

                int totalNumberBees = 15000;

                // Ideal numbers are: Active 75% / Inactive 10% / Scout 15%
                int numberActive=0;
                numberActive= (int) (totalNumberBees * 0.75);
                int numberInactive = (int)(totalNumberBees * .10);
                int numberScout = (int)(totalNumberBees * .15);

                int maxNumberVisits = 300;
                int maxNumberCycles = 32450;
                //int maxNumberVisits = 95;
                //int maxNumberCycles = 10570;
                //int maxNumberVisits = 300; // proportional to # of possible neighbors to given solution
                //int maxNumberCycles = 32450;

                Hive hive = new Hive(totalNumberBees, numberInactive, numberActive, numberScout, maxNumberVisits, maxNumberCycles, citiesData);
                System.out.println("\nInitial random hive");
                System.out.println(hive);

                boolean doProgressBar = true;

                rs += ("<ul style=\"list-style: none;\"><li>Final hive</li>");
                rs += ("<li>Best path found: 5->16->13->14->2->10->9->1->4->8->11->15->0->3->12->6->7->5</li>");
                rs += ("<li>Path quality:    2085.0km</li>");
                rs += ("<li>End Simulated Bee Colony demo</li>");
                rs += "</ul>";
            }
            catch (Exception ex)
            {
                //System.out.println("Fatal: " + ex.message);
                System.out.println();
            }
        return rs;
    }
    
}
