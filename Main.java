package pl.edu.pg.student.testowy;
import javax.lang.model.type.ArrayType;
import java.util.*;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String arg = args[0];
        int threads_count = Integer.parseInt(arg);
        int begin_task = 8;
        Resource all_resources = new Resource();
        Resource_saver saver = new Resource_saver();
        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < threads_count; i++) {
            workers.add(new Worker(all_resources, saver, i));
        }

        List<Thread> thread_workers = new ArrayList<>();
        for (Worker my_worker: workers){
            thread_workers.add(new Thread(my_worker));
        }

        for (Thread tred: thread_workers){
            tred.start();
        }
        Integer job_id = 0;
        boolean exit = false;
        String input;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0;i<begin_task;i++){
            all_resources.put(job_id++, i%5);
        }
        while (!exit) {
            input = scan.next();
            if (input.equals("quit")) {
                exit = true;
            } else {
                all_resources.put(job_id++, Integer.parseInt(input));
            }
        }

        for (Thread thread : thread_workers) {
            thread.interrupt();
        }
        for (Thread thread : thread_workers) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                System.out.println("There has been an error closing the thread");
            }
        }
        saver.PrintAllResults();
        //now exit
    }
}
