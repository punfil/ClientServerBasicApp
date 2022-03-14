package pl.edu.pg.student.testowy;

import java.util.ResourceBundle;

import static java.lang.Thread.currentThread;

public class Worker implements Runnable {
    private Resource all_resources;
    private Resource_saver all_saved_resources;
    public Worker(Resource resources, Resource_saver saver){
        this.all_saved_resources = saver;
        this.all_resources = resources;
    }
    private boolean isPrime(Integer number) {
        if (number==2){
            return true;
        }
        if (number < 2 || number%2 == 0)
            return false;

        for (int i = 3; i < number; i+=2)
        {
            if (number % i == 0)
                return false;
        }
        return true;
    }
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Integer resource = all_resources.take();
                System.out.println(String.format("The thread is going to check if the number %d is prime", resource));
                Thread.sleep(5000);
                System.out.println(String.format("The thread has finished to check whether the number %d is prime.", resource));
                all_saved_resources.put(resource, isPrime(resource));
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
