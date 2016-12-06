package tronganh.tuth;

import java.util.Map;
import java.util.Map.Entry;
	
	public class MainClass {

  

		public static void main(String[] args) { 
                    /*int kichthuocmieng[];
                    int soluong[];
                    int kichthuocnguyenlieu;
                     int n;
                    Scanner input =new Scanner(System.in);
                      System.out.println("nhap kich thuoc nguyen lieu ");
                    kichthuocnguyenlieu=input.nextInt();
                   System.out.println("nhap so don hang n= ");
                        n=input.nextInt();
                  kichthuocmieng = new int[n];
                        soluong = new int[n]; 
                          for (int j = 1; j < n+1; j++) {
                       System.out.println("nhap kich thuoc thu[" +j+"] =");
                   kichthuocmieng[j-1]=input.nextInt();
                      System.out.println("nhap so luong thu[" +j+"] =");
                        soluong[j-1]=input.nextInt();
}*/
                    String gopchuoi = "3000,700,500,250,380,400,900,4,3,6,7,9,10";
                    String[] stringArray0 = gopchuoi.split(",");
                    String numberAsStrin =  stringArray0[0]; 
                    int kichthuocnguyenlieu = Integer.parseInt(numberAsStrin);
                   int n = (stringArray0.length -1)/2 ;
                   int[] kichthuocmiengs = new int[n];
                     int[] soluongs = new int[n];
                   
                   for (int j = 1; j < n+1; j++) {
                     
         String numberAsString = stringArray0[j];
         
         kichthuocmiengs[j-1] = Integer.parseInt(numberAsString);}
                   for (int j = n+1; j < stringArray0.length; j++){
                       String numberAsString0 = stringArray0[j];
                       soluongs[j-(n+1)] = Integer.parseInt(numberAsString0);}
                   
       
                    
                    /*String ktnguyenlieu = "3000";
                    String ktmieng = "700,500,250,380,400,900";
                    String soluongmieng = "4,3,6,7,9,10";
                    
                    int kichthuocnguyenlieu = Integer.parseInt(ktnguyenlieu);
                    
       
      String[] stringArray1 = ktmieng.split(",");
      int[] kichthuocmiengs = new int[stringArray1.length];
      for (int j = 0; j < stringArray1.length; j++) {
         String numberAsString1 = stringArray1[j];
         kichthuocmiengs[j] = Integer.parseInt(numberAsString1);
      }
         
         
      String[] stringArray2 = soluongmieng.split(",");
      int[] soluongs = new int[stringArray2.length];
      for (int k = 0; k < stringArray2.length; k++) {
         String numberAsString2 = stringArray2[k];
         soluongs[k] = Integer.parseInt(numberAsString2);
      }*/
			/*int kichthuocmiengs[]={700,500,250,380,400,900};
			int soluongs[]={4,3,6,7,9,10};
                        kichthuocnguyenlieu=2500;*/
			int i=0;
			Map<Integer, Integer> map;
		    CuttingStock cuttingStock= new CuttingStock(kichthuocnguyenlieu,kichthuocmiengs,soluongs);
			while(cuttingStock.hasMoreCThanhNguyenLieus())
			{
				System.out.println("\nCThanhNguyenLieu so "+(++i));
				map=cuttingStock.nextCThanhNguyenLieu();
				for (Entry<Integer, Integer> entry : map.entrySet()) 
				{
					  Integer key = entry.getKey();
					  Integer value = entry.getValue();
					  System.out.println(key+"  *  "+value);
				}
			}
                }}                     
      
	

