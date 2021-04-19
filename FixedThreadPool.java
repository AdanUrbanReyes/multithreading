import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

    public static final byte NUMBER_OF_TASK = 11;

    static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("Starting to run the task on thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
                System.out.println("Ending of run the task on thread " + Thread.currentThread().getName());
            } catch (InterruptedException ie) {
                System.out.println("Ending; with exception of run the task on thread " + Thread.currentThread().getName());
            }
        }

    }

    /**
     * Create a thread pool according to the number of cores the host machine has
     * , and send <code>NUMBER_OF_TASK</code> task to be atteded by that threads.
     * This work using a bloking queue where will be store all the tasks and where each
     * thread will be pull task one by one until they finish all tasks.
     *
     * @param args
     */
    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        byte ap = (byte) Runtime.getRuntime().availableProcessors();
        System.out.println(String.format("Creating a pool of %s threads", ap));
        ExecutorService es = Executors.newFixedThreadPool(ap);
        for (byte t = 0; t < NUMBER_OF_TASK; t++) {
            es.execute(new Task());
        }
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

}
