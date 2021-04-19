public class WaysOfCreateThread {

    static class ByInheritance extends Thread {

        public ByInheritance(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(String.format("Running thread : %s; created by extended from Thread class", Thread.currentThread().getName()));
        }

    }

    static class ByInterface implements Runnable {

        @Override
        public void run() {
            System.out.println(String.format("Running thread : %s; created by implementing Runnable interface", Thread.currentThread().getName()));
        }

    }


    public static void newByInterface() {
        Thread bit = new Thread(new ByInterface(), "ByInteface0");
        bit.start();
    }

    public static void newByInheritance() {
        Thread bit = new ByInheritance("ByInheritance0");
        bit.start();
    }


    /**
     * You need Java 8 for be able to run this method
     */
    public static void newByLambda() {
        Thread blt = new Thread(() -> {
            System.out.println(String.format("Running thread : %s; created by using Lambda function", Thread.currentThread().getName()));
        }, "ByLambda0");
        blt.start();
    }

    public static void main(String... args) {
        System.out.println("Starting main method");
        newByInterface();
        newByInheritance();
        newByLambda();
        System.out.println("Ending main method");
    }

}
