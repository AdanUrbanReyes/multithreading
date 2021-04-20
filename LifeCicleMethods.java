import java.util.concurrent.*;

public class LifeCicleMethods {

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

    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        ExecutorService es = Executors.newFixedThreadPool(1);
        for (int i = 0; i < NUMBER_OF_TASK; i++) {
            es.execute(new Task());
        }
        es.shutdown();//initiate the shutdown
        try {
            es.execute(new Task());//throw an execption case the shutdown signal to the pool was sended
        } catch (RejectedExecutionException ree) {
            System.out.println(String.format("The pool is shutdown (%b); so you can not send more task", es.isShutdown()));
        }
        System.out.println("Is the pool terminated? " + es.isTerminated());// will return false if there are some task on the queue to be executed; true if the pool is empty
        try {
            es.awaitTermination(10, TimeUnit.SECONDS);// main tread wait for 10 secods to continue
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
        System.out.println("Is the pool terminated? " + es.isTerminated());
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

}