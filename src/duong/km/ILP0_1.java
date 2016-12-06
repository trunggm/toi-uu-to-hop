package duong.km;

import java.util.*;
/**
 *
 * @author minhd
 */
public class ILP0_1 {
    protected List<Item> itemList = new ArrayList<Item>();
    protected int max_func = 0;
    protected int kq = 0;
    protected int profit = 0;
    protected boolean calculate = false;
    
    public ILP0_1() {}
    
    public ILP0_1 (int _max_func){
        setMaxFunction(_max_func);
    }
    
    public ILP0_1 (List<Item> _itemList){
        setItemList(_itemList);
    }
    
    public ILP0_1(List<Item> _itemList, int _max_func) {
        setItemList(_itemList);
        setMaxFunction(_max_func);
    }
    
    public List<Item> calcSolution() {
        int n = itemList.size();
 
        Khoi_tao();
        if (n > 0 && max_func > 0) {
            List< List<Integer> > c = new ArrayList< List<Integer> >();
            List<Integer> curr = new ArrayList<Integer>();
 
            c.add(curr);
            for (int j = 0; j <= max_func; j++)
                curr.add(0);
            for (int i = 1; i <= n; i++) {
                List<Integer> prev = curr;
                c.add(curr = new ArrayList<Integer>());
                for (int j = 0; j <= max_func; j++) {
                    if (j > 0) {
                        int wH = itemList.get(i-1).getWeight();
                        curr.add(
                            (wH > j)
                            ?
                            prev.get(j)
                            :
                            Math.max(
                                prev.get(j),
                                itemList.get(i-1).getValue() + prev.get(j-wH)
                            )
                        );
                    } else {
                        curr.add(0);
                    }
                } 
            }
            profit = curr.get(max_func);
 
            for (int i = n, j = max_func; i > 0  &&  j >= 0; i--) {
                int tempI   = c.get(i).get(j);
                int tempI_1 = c.get(i-1).get(j);
                if ((i == 0  &&  tempI > 0) || (i > 0  &&  tempI != tempI_1))
                {
                    Item iH = itemList.get(i-1);
                    int  wH = iH.getWeight();
                    iH.setInILP(1);
                    j -= wH;
                    kq += wH;
                }
            } 
            calculate = true;
        }
        return itemList;
    }
    
    // thêm biến vào danh sách
    public void add(String name, int weight, int value) {
        if (name.equals(""))
            name = "" + (itemList.size() + 1);
        itemList.add(new Item(name, weight, value));
        Khoi_tao();
    }
 
    public void add(int weight, int value) {
        add("", weight, value); 
    }
 
    public void remove(String name) {
        for (Iterator<Item> it = itemList.iterator(); it.hasNext(); ) {
            if (name.equals(it.next().getName())) {
                it.remove();
            }
        }
        Khoi_tao();
    }
 
    public void removeAllItems() {
        itemList.clear();
        Khoi_tao();
    }
 
    public int getProfit() {
        if (!calculate)
            calcSolution();
        return profit;
    }
 
    public int getSolutionWeight() {return kq;}
    public boolean isCalculated() {return calculate;}
    public int getMaxWeight() {return max_func;}
 
    public void setMaxFunction(int _maxWeight) {
        max_func = Math.max(_maxWeight, 0);
    }
 
    public void setItemList(List<Item> _itemList) {
        if (_itemList != null) {
            itemList = _itemList;
            for (Item item : _itemList) {
                item.checkMembers();
            }
        }
    }
 
    private void setILPByAll(int inILP) {
        for (Item item : itemList)
            if (inILP > 0)
                item.setInILP(1);
            else
                item.setInILP(0);
    }
 
    protected void Khoi_tao() {
        setILPByAll(0);
        calculate     = false;
        profit         = 0;
        kq = 0;
    }
 
}
