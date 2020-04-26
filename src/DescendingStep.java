import java.util.Comparator;
import java.util.Map;

public class DescendingStep implements Comparator<Map.Entry<char[][][],Integer>> {
    public int compare(final Map.Entry<char[][][], Integer> l, final Map.Entry<char[][][], Integer> r){
        if(l.getValue() > r.getValue()){
            return +1;
        }
        else if(l.getValue() < r.getValue()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
