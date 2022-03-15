package pl.edu.pg.student.testowy;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Thread.currentThread;

public class Worker implements Runnable {
    private Resource all_resources;
    private Resource_saver all_saved_resources;
    private Integer worker_no;
    private Integer interations;
    public Worker(Resource resources, Resource_saver saver, Integer worker_no){
        this.all_saved_resources = saver;
        this.all_resources = resources;
        this.worker_no = worker_no;
    }
    private double calculatePi (Integer precision) {
        double sum = 0.0;
        double current = 0.0;
        this.interations = 0;
        double left = Math.abs(4*sum-Math.PI);
        double right = Math.pow(10, -precision-1);
        for (int i=1;left > right;i++){
            current += Math.pow((-1),(i-1));
            current /= (2*i-1);
            sum += current;
            current=0;
            this.interations++;
            left = Math.abs(4*sum-Math.PI);
        }
        sum *=4;
        return sum;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                List<Integer> job = all_resources.take(); //0 is job_id, 1 is precision
                System.out.printf("The thread %d is going to calculate PI with %d precision%n", worker_no, job.get(1));
                Thread.sleep(5000);
                double pi_value = calculatePi(job.get(1));
                all_saved_resources.put(job.get(0), job.get(1), pi_value, Math.abs(pi_value-Math.PI), interations);
                System.out.printf("The thread %d has finished to calculate PI with %d precision%n", worker_no, job.get(1));
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
