package pl.edu.pg.student.testowy;
import java.util.Vector;
public class Resource {
    private Vector<Integer> queue = new Vector<Integer>();
    public synchronized Integer take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();//Wait, maybe someone will put sth here.
        }
        int ret = queue.remove(queue.size()-1);
        return ret;
    }
    public synchronized void put(Integer value) {
        this.queue.add(value);
        notifyAll();
    }
}
