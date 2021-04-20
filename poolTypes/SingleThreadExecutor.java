import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

    public static final byte NUMBER_OF_TASK = (byte) Runtime.getRuntime().availableProcessors();

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
     * Create just one thread will be attending for each task on the bloking queue
     *
     * @param args
     */
    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (byte t = 0; t < NUMBER_OF_TASK; t++) {
            es.execute(new Task());
        }
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

}