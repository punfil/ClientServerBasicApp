package pl.edu.pg.student.testowy;
import java.util.ArrayList;
import java.util.List;
public class Resource {
    private List<Integer> queue = new ArrayList<>();
    public synchronized Integer take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.remove(0);
    }

    public synchronized void put(Integer task_int) {
        this.queue.add(task_int);
        notifyAll();
    }
}
