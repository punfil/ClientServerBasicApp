package pl.edu.pg.student.testowy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resource {
    private List<Integer> resources_ids = new ArrayList<>();
    private List<Integer> precisions = new ArrayList<>();
    public synchronized List<Integer> take() throws InterruptedException {
        while (resources_ids.isEmpty()) {
            wait();
        }
        List<Integer> return_list = new ArrayList<>();
        return_list.add(resources_ids.remove(0));
        return_list.add(precisions.remove(0));
        return return_list;
    }

    public synchronized void put(Integer job_id, Integer task_int) {
        this.resources_ids.add(job_id);
        this.precisions.add(task_int);
        notifyAll();
    }
}
