package pl.edu.pg.student.testowy;
import javax.lang.model.type.ArrayType;
import java.util.*;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String arg = args[0];
        int threads_count = Integer.parseInt(arg);

        Resource_saver saver = new Resource_saver();
        Resource all_resources = new Resource();

        List<Worker> workers = new ArrayList<>();
        for (int i = 0; i < threads_count; i++) {
            workers.add(new Worker(all_resources, saver));
        }

        List<Thread> thread_workers = new ArrayList<>();
        for (Worker my_worker: workers){
            thread_workers.add(new Thread(my_worker));
        }

        for (Thread tred: thread_workers){
            tred.start();
        }

        boolean exit = false;
        String input;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!exit) {
            input = scan.next();
            if (input.equals("quit")) {
                exit = true;
            } else {
                all_resources.put(Integer.parseInt(input));
            }
        }

        //Konczenie
        for (Thread thread : thread_workers) {
            thread.interrupt();
        }
        for (Thread thread : thread_workers) {
            try {
                thread.join();
            } catch (InterruptedException ex) {

            }
        }
        saver.PrintAllResults();
        //now exit
    }
}
