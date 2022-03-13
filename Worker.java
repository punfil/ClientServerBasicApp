package pl.edu.pg.student.testowy;


public class Worker implements Runnable {
    int result = 0;
    Resource_saver saver;
    public Worker(Resource_saver saver) {
        this.saver = saver;
    }
    public void GiveTask(int value){
        this.result = value;
    }
    @Override
    public void run() {
        result *= 2;
        //Epic computations.
        saver.put(result);
    }
}
