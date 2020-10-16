import com.demo.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class Dictionary <K,V>{
    private K key;
    private V value;

    public Dictionary(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public void setKey(K key){
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}


public class TraTuDien{

    public static String Nhap(){
        String res ="";
        Scanner scanner = new Scanner(System.in);
        res = scanner.nextLine();

        return res;
    }
    public static void main (String args[]){

        List<Dictionary<String,String>> d = new ArrayList<>();

        d.add(new Dictionary<>("study","hoc"));
        d.add(new Dictionary<>("eat","an"));
        d.add(new Dictionary<>("fly", "bay"));
        d.add(new Dictionary<>("run","chay"));
        d.add(new Dictionary<>("time","thoi gian"));
        d.add(new Dictionary<>("computer", "may tinh"));

        String input = Nhap();

        d.stream().filter(t-> Objects.equals(t.getKey(), input)).forEach(t->System.out.print(t.getValue()));



    }
}
