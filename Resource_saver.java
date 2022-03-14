package pl.edu.pg.student.testowy;
import java.util.HashMap;
import java.util.Map;

public class Resource_saver {
    private static final Object lock = new Object();
    private Map<Integer, Boolean> results = new HashMap<>();
    public void put(Integer my_result, Boolean is_true) {
        synchronized (lock) {
            results.put(my_result, is_true);
        }
    }
    public void PrintAllResults(){
        for (Integer result: results.keySet()){
            System.out.println(result.toString() + " is " + results.get(result).toString() + " prime");
        }
    }
}
