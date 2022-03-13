package pl.edu.pg.student.testowy;
import java.util.Vector;

public class Resource_saver {
    private static final Object lock = new Object();
    private Vector<Integer> results;
    public void put(Integer my_result) {
        synchronized (lock) {
            results.add(my_result);
        }
    }
}
