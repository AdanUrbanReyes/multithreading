import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    public static final byte NUMBER_OF_TASK = (byte) Runtime.getRuntime().availableProcessors();

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Starting to run the task on thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(7000);
                System.out.println("Ending of run the task on thread " + Thread.currentThread().getName());
            } catch (InterruptedException ie) {
                System.out.println("Ending; with exception of run the task on thread " + Thread.currentThread().getName());
            }
        }

    }

    /**
     * Create a pool according if there is a not attended task on the queue
     * ; this queue can hold just one only task, and if there is not avaiable
     * thread to take this one, a new thread will be created.
     * If thread is idle (not busy, or no task to execute ) for 60 seconds
     * , then kill the thread.
     *
     * @param args
     */
    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        ExecutorService es = Executors.newCachedThreadPool();
        for (byte t = 0; t < NUMBER_OF_TASK; t++) {
            es.execute(new Task());
        }
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

}