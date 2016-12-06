package duong.km;

import java.util.*;

/**
 *
 * @author minhd
 */
public class Programming extends ILP0_1{
    public Programming() {}
    
    public Programming(int _maxWeight) {
        setMaxFunction(_maxWeight);
    }
 
    public Programming(List<Item> _itemList) {
        setItemList(_itemList);
    }
 
    public Programming(List<Item> _itemList, int _max_func) {
        setItemList(_itemList);
        setMaxFunction(_max_func);
    }
 
    @Override
    public List<Item> calcSolution() {
        int n = itemList.size();
 
        // them bien vao danh sach khi can > 1
        for (int i = 0; i < n; i++) {
            Item item = itemList.get(i);
            if (item.getBounding() > 1) {
                for (int j = 1; j < item.getBounding(); j++) {
                    add(item.getName(), item.getWeight(), item.getValue());
                }
            }
        }
 
        super.calcSolution();
 
        while (itemList.size() > n) {
            Item lastItem = itemList.get(itemList.size() - 1);
            if (lastItem.getInILP() == 1) {
                for (int i = 0; i < n; i++) {
                    Item iH = itemList.get(i);
                    if (lastItem.getName().equals(iH.getName())) {
                        iH.setInILP(1 + iH.getInILP());
                        break;
                    }
                }
            }
            itemList.remove(itemList.size() - 1);
        }
 
        return itemList;
    }
 
    // them bien vao danh sach
    public void add(String name, int weight, int value, int bounding) {
        if (name.equals(""))
            name = "" + (itemList.size() + 1);
        itemList.add(new Item(name, weight, value));
        Khoi_tao();
    }
}
