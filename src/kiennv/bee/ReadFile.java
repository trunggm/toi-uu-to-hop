package kiennv.bee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
/**
 *
 * @author Kien
 */
public class ReadFile
{
   static final String fileName = "D:\\Java\\Kien\\NetBeansProjects\\BeeForTsp\\data\\48cities.txt";
   static String count[][]=new String[1000][1000];
   static double dist[][]=new double[1000][1000];;
   int linenumber=0;
   public void input_file(int cot) throws FileNotFoundException, IOException
    {
        
        FileInputStream fis = new FileInputStream(new File(fileName));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line="";
        StringTokenizer itr;
        while ((line = br.readLine()) != null)
        {               
            itr= new StringTokenizer(line);
            for(int l=0;l<cot;l++)
            {
                count[linenumber][l]=itr.nextToken();
            }
            //count[linenumber][0]=itr.nextToken();
            int i=linenumber;
            linenumber++;
        }
        for(int l=0;l<cot;l++)
        {
            count[linenumber][l]=null;
        }
        for (int i=0;i< linenumber;i++)
        {
            for(int j=0;j < linenumber;j++)
            {
                dist[i][j]=Double.parseDouble(count[i][j]);
                //System.out.print(" "+dist[i][j]);
            }
            //System.out.println("\n");
        }
        fis.close();
       
    }
    
}
//Test Doc File
//class Array
//{
//    public static void main(String[] args) throws IOException
//    {
//        ReadFile Arr = new ReadFile();
//        Arr.input_file(42); //nhap tu file
//        for (int i=0;i< Arr.linenumber;i++)
//        {
//            for(int j=0;j < Arr.linenumber;j++)
//            {
//                Arr.dist[i][j]=Double.parseDouble(ReadFile.count[i][j]);
//                System.out.print(" "+Arr.dist[i][j]);
//            }
//            System.out.println("\n");
//        }
//    }
//}