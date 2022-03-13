package pl.edu.pg.student.testowy;


public class Worker implements Runnable {
    Integer result = 0;
    public Worker(Integer saver) {
        this.result = saver;
    }
    @Override
    public void run() {
        result *= 2;
        //Epic computations;
    }

    public Integer getResult() {
        return result;
    }
}
