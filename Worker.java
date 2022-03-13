package pl.edu.pg.student.testowy;

import static java.lang.Thread.currentThread;

public class Worker implements Runnable {
    Integer result = 0;
    public Worker(Integer saver) {
        this.result = saver;
    }
    @Override
    public void run() {
        System.out.println("Hello from worker");
        result *= 2;
        //Epic computations;
    }

    public Integer getResult() {
        return result;
    }
}
