import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    static class Task implements Runnable {

        private String type;

        public Task(String type) {
            this.type = type;
        }

        @Override
        public void run() {
            System.out.println(String.format("Starting to run the task %s on thread %s", type, Thread.currentThread().getName()));
            try {
                Thread.sleep(7000);
                System.out.println(String.format("Ending of run the task %s on thread %s", type, Thread.currentThread().getName()));
            } catch (InterruptedException ie) {
                System.out.println(String.format("Ending; with execption of run the task %s on thread %s", type, Thread.currentThread().getName()));
            }
        }

    }

    /**
     * Create a schedule pool threads will be executed according to the schedule setted
     *
     * @param args
     */
    public static void main(String... args) {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.schedule(new Task("single"), 10, TimeUnit.SECONDS);//this task will be run after 10 seconds of delay
        ses.scheduleAtFixedRate(new Task("fixedRate"), 15, 10, TimeUnit.SECONDS);// task to run repeatedly every 10 seconds; the first task will have 15 seconds of delay
        ses.scheduleWithFixedDelay(new Task("fixedDelay"), 15, 10, TimeUnit.SECONDS); //task to run repeatedly every 10 seconds after previous task compleate; the first task will have 15 seconds of delay
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

}