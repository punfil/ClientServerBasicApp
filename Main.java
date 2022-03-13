package pl.edu.pg.student.testowy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String arg = args[0];
        int threads_count = Integer.parseInt(arg);
        List<Thread> thread_workers = new ArrayList<>();
        Resource_saver saver = new Resource_saver();
        for (int i=0;i<threads_count;i++) {
            thread_workers.add(new Thread(new Worker(saver)));
        }
        Resource resource = new Resource();
        resource.put(3);


    }
}
