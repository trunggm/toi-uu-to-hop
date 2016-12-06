package tronganh.tuth;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class CuttingStock {
	
	private int kichthuocmieng[],soluongmieng[],comb[],TamThoicomb[],GioiHan[];
	@SuppressWarnings("unused")
	private int kichthuocnguyenlieu,TongSo,counter=0,phethai=0;
	private List<Map<Integer,Integer>> mapList=new ArrayList<Map<Integer, Integer>>();
	private List<Integer> CacPhuongAn=new ArrayList<Integer>();
	private int count=0;
	
	public boolean hasMoreCThanhNguyenLieus()
	{
		if(count<counter)
			return true;
		else
			return false;
	}
	public synchronized Map<Integer, Integer> nextCThanhNguyenLieu()
	{
		Map<Integer, Integer> map=mapList.get(count);
		count++;
		return map;
	}
	
	public CuttingStock(int kichthuocnguyenlieu,int kichthuocmieng[],int quantity[]) throws ChieuDaiMiengException,ThuThongSoMangException
	  {    
	    for(int i=0;i<kichthuocmieng.length;i++)
	    {
	    	if(kichthuocmieng[i]>kichthuocnguyenlieu)
	    	{
	    		throw new ChieuDaiMiengException();
	    	}
	    }
	    if(kichthuocmieng.length!=quantity.length)
	    {
	    	throw new ChieuDaiMiengException();
	    }
		this.TongSo=kichthuocmieng.length;
	    this.kichthuocnguyenlieu=kichthuocnguyenlieu;
	    this.kichthuocmieng=kichthuocmieng;
		this.soluongmieng=quantity;
		this.doIt();
	  }
	private void doIt()
	    {
	      this.KhoiTao();
	      /*for(int i=0;i<stock.size();i++)
			{
	    	  for(int j=0;j<stock.get(i).comb.length;j++)
	    	  {
	    		if(stock.get(i).comb[j]>0)
				System.out.println(kichthuocmieng[j]+"  *  "+this.stock.get(i).comb[j]);
	    	  }
			}*/
	    }
	private void KhoiTao()
	  {
	    CacPhuongAn=new ArrayList<Integer>();
	    phethai=0;
	    counter=0;
	    this.sort();
	    this.TinhToanCachCat(CacPhuongAn);
	    /*wast_array=CacPhuongAn.toArray();
	    if(wast_array.length>0)
	    {
	      System.out.println("Consider reusing the following remains");    
	      for(int i=wast_array.length-1;i>=0;i--)
	      {
	        System.out.println((this.counter+i-wast_array.length+1)+" "+wast_array[i]);
	      }
	      //out.println("</table><br><br>");
	    }
	    System.out.println("No of pieces req = "+this.counter);    
	    System.out.println("phethai = "+this.phethai);*/
	  }
	  private void sort()
	  {
	    int tmp;
	    boolean swap;
	    do
	    {
	      swap=false;
	      for(int j=0;j<TongSo-1;j++)
	      {
	        if(kichthuocmieng[j+1]>kichthuocmieng[j])
	        {
	          tmp=kichthuocmieng[j];
	          kichthuocmieng[j]=kichthuocmieng[j+1];
	          kichthuocmieng[j+1]=tmp;

	          tmp=soluongmieng[j];
	          soluongmieng[j]=soluongmieng[j+1];
	          soluongmieng[j+1]=tmp;
	          swap=true;
	        }
	      }
	    }while(swap);
	  }
	  private void TinhToanCachCat(List<Integer> CacPhuongAn)
	  {
	    initGioiHan();
	    boolean start=true,chaloo=true;
	    int PhuongAnTotNhat=0,sum=0;
	    comb=new int[TongSo];
	    while(start)
	    {
	      ////out.println("At start again");                            // DELETE IT
	      this.cThanhNguyenLieus();
	      
	     /* for(int i=0;i<TongSo;i++)                                            //CHECK.......
	      {
	        //out.println(kichthuocmieng[i]+"&nbsp;"+comb[i]);
	      }*/
	      sum=0;
	      for(int i=0;i<TongSo;i++)
	      {
	        sum+=kichthuocmieng[i]*comb[i];
	        if(sum>kichthuocnguyenlieu)
	        {
	          sum=0;
	          break;
	        }
	      }

	      ////out.println("sum = "+sum);                                    // CHECK............

	      if(sum>0) //if a comb suited
	      {
	        if(sum==kichthuocnguyenlieu) // if PhuongAnTotNhat comb found
	        {
	          ////out.println("Sum = "+sum);                                  //  DELETEc IT
	          this.showComb(0,CacPhuongAn);  //print comb
	          ThietLapLaiComb();
			  updateGioiHan();
	          PhuongAnTotNhat=0;
	          sum=0;
	        }
	        else
	          if(sum>PhuongAnTotNhat)
	          {
	            PhuongAnTotNhat=sum;
	            TamThoicomb =new int[TongSo];
	            for(int i=0;i<TongSo;i++) // storing PhuongAnTotNhat comb in TamThoiComb[]
	              TamThoicomb[i]=comb[i];
	            sum=0;
	          }
	      }
	      for(int i=0;i<TongSo;i++)  //to check whether all comb done
	      {
	        if(comb[i]!=GioiHan[i])
	        {
	          chaloo=true;
	          break;
	        }
	        chaloo=false;
	      }
	      if(!chaloo) // when all comb completed
	      {
	        //for(int i=0;i<TongSo;i++) // storing PhuongAnTotNhat comb in TamThoiComb[] ...Testing
	          //    //out.print(TamThoicomb[i]);
	        //for(int i=0;i<TongSo;i++) // storing PhuongAnTotNhat comb in TamThoiComb[] ...Testing
	          //    //out.print(comb[i]);
	        this.showComb(PhuongAnTotNhat,CacPhuongAn);
	        updateGioiHan();
	        ThietLapLaiComb();
	        PhuongAnTotNhat=0;
	      }////out.println("B4 start loop");                            // DELETE IT
	      for(int i=0;i<TongSo;i++) // To end while loop when no more pieces left
	      {
	        if(soluongmieng[i]==0 && i!=TongSo-1)
	          continue;
	        else if(i==TongSo-1 && soluongmieng[i]==0)
	          start=false;
	        break;
	      }/*//out.println("After start loop");                            // DELETE IT
	     for(int i=0;i<TongSo;i++)                                         ////////
	          //out.print(soluongmieng[i]);                                 /////////
	     //out.println(); */                                            //////////
	    }
	  }
	  private void showComb(int a, List<Integer>CacPhuongAn )
	  {
		counter++;
		
	    boolean flag=false;
	    //out.println("<font color=\"brown\">=====================================</font><br>Piece no "+counter+"<br>----------<br>");
	    if(a==0)
	    {
	    	Map<Integer, Integer> TamThoiMap = new HashMap<Integer, Integer>();
	      for(int i=0;i<TongSo;i++)
	        if(comb[i]!=0)
	        {
	            TamThoiMap.put(new Integer(kichthuocmieng[i]), new Integer(comb[i]));
	        	//System.out.println(kichthuocmieng[i]+"  *  "+comb[i]);
	        	soluongmieng[i]=soluongmieng[i]-comb[i]; //  deduct samples from stock(soluongmieng) which are already printed
	        	if((soluongmieng[i]-comb[i])<0)
	        	{
	        		flag=true; // to return and not recursively call.
	        	}
	        }
	      
	            
			  if(flag)
			  {
				  mapList.add(TamThoiMap);
				  return;
			  }
			  showComb(0,CacPhuongAn);
	    }
	    else
	    {
	    	Map<Integer, Integer> TamThoiMap = new HashMap<Integer, Integer>();
	      for(int i=0;i<TongSo;i++)
	        if(TamThoicomb[i]!=0)
	        {
		         TamThoiMap.put(new Integer(kichthuocmieng[i]), new Integer(TamThoicomb[i]));
	        	 //System.out.println(kichthuocmieng[i]+"  ggg  "+TamThoicomb[i]);
	        }
	      mapList.add(TamThoiMap); 
	      //out.println("----------");
		  //System.out.println("\nThis piece remains = "+(kichthuocnguyenlieu-a));
		  phethai+=kichthuocnguyenlieu-a;
	      CacPhuongAn.add(kichthuocnguyenlieu-a);
	        for(int i=0;i<TongSo;i++)
	          soluongmieng[i]=soluongmieng[i]-TamThoicomb[i];
			 
	        for(int i=0;i<TongSo;i++)
			  {
			    if((soluongmieng[i]-comb[i])<0)
				{
			    	return;			
				}
			  }
			  showComb(a,CacPhuongAn);
	    }
	  }
	  private void cThanhNguyenLieus()
	  {
	      for(int i=TongSo-1;;)
	      {
	        if(comb[i]!=GioiHan[i])
	        {
	          comb[i]++;
	          break;
	        }
	        else
	        {
	          if(i==0 && comb[0]!=GioiHan[0])
	            i=TongSo-1;
	          else
	          {
	            comb[i]=0;
	            i--;
	          }
	        }
	      }
	  }
	  private void initGioiHan()
	  {
	    int div;
		GioiHan=new int[TongSo];
	    for(int i=0;i<TongSo;i++)
		{
		  div=kichthuocnguyenlieu/kichthuocmieng[i];
		  if(soluongmieng[i]>div)
		    GioiHan[i]=div;
		  else
		    GioiHan[i]=soluongmieng[i];
	    }
	  }
	  private void updateGioiHan()
	  {
	    for(int i=0;i<TongSo;i++)
		{
		  if(soluongmieng[i]<GioiHan[i])
		    GioiHan[i]=soluongmieng[i];
		}
	  }
	  private void ThietLapLaiComb()
	  {
	    for(int i=0;i<TongSo;i++) // ThietLapLai comb[]
	      comb[i]=0;
	  }

}
