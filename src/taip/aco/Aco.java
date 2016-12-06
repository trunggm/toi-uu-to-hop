package taip.aco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Aco {

    private double alpha = 1;            //hệ số điều chỉnh ảnh hưởng của nồng độ mùi trên mỗi cạnh
    private double beta = 5;             //hệ số điều chỉnh ảnh hưởng của nghịch đảo khoảng cách thành phố i đến thành phố j
    private double e = 0.5;              //tốc độ bay hơi pheronome
    private static int m = 100;                 //số lượng kiến
    private int numLoop = 2000;          //tổng số vòng lặp

    public static int n = 0;                    //số thành phố
    private double p = 0.01;             //xác suất chọn thành phố
    public double map[][] = null;       //bản đồ
    private static double pheronome[][] = null; //nồng độ mùi pheronome
    private static Ant ants[] = null;
    private Random rand = new Random();
    private static double prob[] = null;        //xác suất chọn đến thành phố tiếp theo
    private int currentIndex = 0;        //chỉ số hiện tại

    public int[] bestTour;
    public double bestTourLength;

    private class Ant {

        public int tour[] = new int[map.length];
        public boolean visited[] = new boolean[map.length];

        public void visitTown(int town) {
            tour[currentIndex + 1] = town;
            visited[town] = true;
        }

        public boolean Visited(int i) {
            return visited[i];
        }

        public double tourLength() {
            double length = map[tour[n - 1]][tour[0]];
            for (int i = 0; i < n - 1; i++) {
                length += map[tour[i]][tour[i + 1]];
            }
            return length;
        }

        public void Clear() {
            for (int i = 0; i < n; i++) {
                visited[i] = false;
            }
        }
    }

    public void readMap(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader buf = new BufferedReader(fr);
        System.out.println(buf);
        String line;
        int i = 0;
        while ((line = buf.readLine()) != null) {
        	System.out.println(line);
            String splitA[] = line.split(" ");
            LinkedList<String> split = new LinkedList<String>();
            for (String s : splitA) {
                if (!s.isEmpty()) {
                    split.add(s);
                }
            }
            
            if (map == null) {
                map = new double[split.size()][split.size()];
                System.out.println(split.size());
            }
            int j = 0;
            for (String s : split) {
                if (!s.isEmpty()) {
                    map[i][j++] = Double.parseDouble(s);
                }
            }
            i++;
        }

        n = map.length;
        pheronome = new double[n][n];
        prob = new double[n];
        ants = new Ant[m];
        for (int k = 0; k < m; k++) {
            ants[k] = new Ant();
        }
    }
    
    public void readMap(double[][] data){
        map = data;
        n = map.length;
        pheronome = new double[n][n];
        prob = new double[n];
        ants = new Ant[m];
        for (int k = 0; k < m; k++) {
            ants[k] = new Ant();
        }
    }

    private void Probs(Ant ant) {
        int i = ant.tour[currentIndex];
        double mauso = 0.0;
        for (int k = 0; k < n; k++) {
            if (!ant.Visited(k)) {
                mauso += Math.pow(pheronome[i][k], alpha) * Math.pow(1.0 / map[i][k], beta);
            }
        }

        for (int j = 0; j < n; j++) {
            if (ant.Visited(j)) {
                prob[j] = 0.0;
            } else {
                double tuso = Math.pow(pheronome[i][j], alpha) * Math.pow(1.0 / map[i][j], beta);
                prob[j] = tuso / mauso;
            }
        }

    }

    private int selectNextTown(Ant ant) {
        if (rand.nextDouble() < p) {
            int t = rand.nextInt(n - currentIndex);
            int j = -1;
            for (int i = 0; i < n; i++) {
                if (!ant.Visited(i)) {
                    j++;
                }
                if (j == t) {
                    return i;
                }
            }
        }

        Probs(ant);
        double r = rand.nextDouble();
        double tot = 0;
        for (int i = 0; i < n; i++) {
            tot += prob[i];
            if (tot >= r) {
                return i;
            }
        }
        throw new RuntimeException("Not supposed to get here");
    }

    private void updatePheronome() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pheronome[i][j] *= e;
            }
        }

        for (Ant a : ants) {
            double Q = 500;
            double contribution = Q / a.tourLength();
            for (int i = 0; i < n - 1; i++) {
                pheronome[a.tour[i]][a.tour[i + 1]] += contribution;
            }
            pheronome[a.tour[n - 1]][a.tour[0]] += contribution;
        }
    }

    private void moveAnts() {
        while (currentIndex < n - 1) {
            for (Ant a : ants) {
                a.visitTown(selectNextTown(a));
            }
            currentIndex++;
        }
    }

    private void setupAnts() {
        currentIndex = -1;
        for (int i = 0; i < m; i++) {
            ants[i].Clear();
            ants[i].visitTown(rand.nextInt(n));
        }
        currentIndex++;

    }

    private void updateBest() {
        if (bestTour == null) {
            bestTour = ants[0].tour;
            bestTourLength = ants[0].tourLength();
        }
        for (Ant a : ants) {
            if (a.tourLength() < bestTourLength) {
                bestTourLength = a.tourLength();
                bestTour = a.tour.clone();
            }
        }
    }

    public static String tourConvertToString(int tour[]) {
        String t = new String();
        for (int i : tour) {
            t = t + " " + (i + 1);
        }
        return t;
    }

    public String Solve() {
    	String rs = "<ul style=\"list-style:none;\">";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pheronome[i][j] = 1;
            }
        }
        int loop = 0;
        while (loop < numLoop) {
            setupAnts();
            moveAnts();
            updatePheronome();
            updateBest();
            loop++;
        }
        rs += ("<li>Best tour:" + tourConvertToString(bestTour)+"</li>");
        rs += ("<li>Best tour length: " + Double.toString(bestTourLength) + "</li></ul>");
        return rs;
    }

    public static String process(double[][] data) {
    	String rs = "";
        Aco a = new Aco();
        a.readMap(data);
        System.out.println("Running...");
        long startTime = System.currentTimeMillis();
        rs += a.Solve();
        long endTime = System.currentTimeMillis();
        rs = "<h3>Time to run: " + Long.toString(endTime - startTime) + " ms</h3>" + rs;
        return rs;
    }
}
