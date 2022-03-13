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
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Integer resource = all_resources.take();
                System.out.println(String.format("%s will carry on epic %s quest.", "Liczba:", resource));
                Thread.sleep(5000);
                System.out.println(String.format("%s has finished the epic quest.", resource));
                all_saved_resources.put(resource);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
