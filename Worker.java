package pl.edu.pg.student.testowy;


public class Worker implements Runnable {
    int result = 0;
    public Worker(int saver) {
        this.result = saver;
    }
    @Override
    public void run() {
        result *= 2;
        //Epic computations;
    }

    public int getResult() {
        return result;
    }
}
