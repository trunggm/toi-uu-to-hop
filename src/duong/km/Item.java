package duong.km;

/**
*
* @author minhd
*/
public class Item {
   protected String name    = "";
   protected int weight     = 0;
   protected int value      = 0;
   protected int bounding   = 1000; // giá trị lớn nhất của cận bằng vô cùng
   protected int inILP = 0; // giá trị của biến

   public Item() {}

   public Item(Item item) {
       setName(item.name);
       setWeight(item.weight);
       setValue(item.value);
       setBounding(item.bounding);
   }

   public Item(int _weight, int _value) {
       setWeight(_weight);
       setValue(_value);
   }

   public Item(int _weight, int _value, int _bounding) {
       setWeight(_weight);
       setValue(_value);
       setBounding(_bounding);
   }

   public Item(String _name, int _weight, int _value) {
       setName(_name);
       setWeight(_weight);
       setValue(_value);
   }

   public Item(String _name, int _weight, int _value, int _bounding) {
       setName(_name);
       setWeight(_weight);
       setValue(_value);
       setBounding(_bounding);
   }

   public void setName(String _name) {name = _name;}
   public void setWeight(int _weight) {weight = Math.max(_weight, 0);}
   public void setValue(int _value) {value = Math.max(_value, 0);}

   public void setInILP(int _inILP) {
       inILP = Math.min(getBounding(), Math.max(_inILP, 0));
   }

   public void setBounding(int _bounding) {
       bounding = Math.max(_bounding, 0);
       if (bounding == 0)
           inILP = 0;
   }

   public void checkMembers() {
       setWeight(weight);
       setValue(value);
       setBounding(bounding);
       setInILP(inILP);
   }

   public String getName() {return name;}
   public int getWeight() {return weight;}
   public int getValue() {return value;}
   public int getInILP() {return inILP;}
   public int getBounding() {return bounding;}
}
