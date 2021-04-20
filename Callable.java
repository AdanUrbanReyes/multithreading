import java.util.ArrayList;
import java.util.concurrent.*;

public class Callable {

    static class Task implements java.util.concurrent.Callable<Integer> {

        private int target;

        public Task(int target) {
            this.target = target;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Starting to run the task on thread " + Thread.currentThread().getName());
            int c = 0;
            while (c != target) {
                try {
                    System.out.println(String.format("Task on thread %s wating for one second", Thread.currentThread().getName()));
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    System.out.println(String.format("Thread %s can't wait 1 second before increment the count", Thread.currentThread().getName()));
                }
                c++;
            }
            System.out.println("Ending of run the task on thread " + Thread.currentThread().getName());
            return c;
        }

    }

    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        java.util.List<Future<Integer>> fs = new ArrayList<>();
        for (int i = 1; i != 4; i++) {
            fs.add(es.submit(new Task(i)));
        }
        for (int i = 0; i < 3; i++) {
            try {
                Integer c = fs.get(i).get(1, TimeUnit.SECONDS);
                System.out.println(String.format("Task %d return %d", (i + 1), c));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println(String.format("Unable to get the count of %d task", (i + 1)));
            }
        }
        es.shutdown();
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

}