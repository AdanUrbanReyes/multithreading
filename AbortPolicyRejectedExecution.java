import java.util.concurrent.*;

public class AbortPolicyRejectedExecution {

    public static final byte NUMBER_OF_TASK = 3;

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

    static class RejectionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }

    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        ExecutorService es = new ThreadPoolExecutor(1
                , 1
                , 120
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(1)
                , new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < NUMBER_OF_TASK; i++) {
            try {
                es.execute(new Task());
            } catch (RejectedExecutionException ree) {
                System.out.println(ree);
            }
        }
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

}
