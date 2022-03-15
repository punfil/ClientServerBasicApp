package pl.edu.pg.student.testowy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resource_saver {
    private static final Object lock = new Object();
    private List<Integer> jobs_ids = new ArrayList<>();
    private List<Integer> precisions = new ArrayList<>();
    private List<Double> pi_values = new ArrayList<>();
    private List<Double> error_values = new ArrayList<>();
    private List<Integer> iterations = new ArrayList<>();
    public void put(Integer job_id, Integer precision, Double pi_value, Double error, Integer interations) {
        synchronized (lock) {
            jobs_ids.add(job_id);
            precisions.add(precision);
            pi_values.add(pi_value);
            error_values.add(error);
            iterations.add(interations);
        }
    }
    public void PrintAllResults(){
        for (int i=0;i<jobs_ids.size();i++){
            System.out.printf("JobID %d calculated with precision %d value of PI %f with error %f with iterations %d\n",
            jobs_ids.get(i), precisions.get(i), pi_values.get(i), error_values.get(i), iterations.get(i));
        }
    }
}
