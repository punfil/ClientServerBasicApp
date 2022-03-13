package pl.edu.pg.student.testowy;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String arg = args[0];
        int threads_count = Integer.parseInt(arg);
        List<Thread> thread_workers = new LinkedList<>();
        Resource_saver saver = new Resource_saver();
        for (int i=0;i<threads_count;i++) {
            thread_workers.add(new Thread(new Worker(3)));
            thread_workers.get(i).start();
        }
        Resource resource = new Resource();
        boolean exit = false;
        String input;
        while (!exit){
            input = scan.next();
            if (input.equals("quit")){
                exit = true;
            }
        }


    }
}
