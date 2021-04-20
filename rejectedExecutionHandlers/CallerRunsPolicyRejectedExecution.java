import java.util.concurrent.*;

public class CallerRunsPolicyRejectedExecution {

    public static final byte NUMBER_OF_TASK = 4;

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
     * Create a Pool using a queue with just one slot, so if we send more than one task
     * at the same time we will have rejections in this case that rejection will be
     * handler by CallerRunsPolicy class which in case of the queue is full and there is
     * no theread to run the received task, will ask to the parent thread (who create the pool)
     * to execute this task.
     *
     * @param args
     */
    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        ExecutorService es = new ThreadPoolExecutor(1
                , 1
                , 120
                , TimeUnit.SECONDS
                , new ArrayBlockingQueue<>(1)
                , new ThreadPoolExecutor.CallerRunsPolicy());
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
