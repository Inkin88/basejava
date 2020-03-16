package webapp;

public class DeadLock {

    private final static Object one = new Object();
    private final static Object two = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            synchronized (one) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (two) {
                    System.out.println(Thread.currentThread().getName() + " Hello");
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (two) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (one) {
                    System.out.println(Thread.currentThread().getName() + " Hello2");
                }
            }
        });
        thread.start();
        thread1.start();
    }

}
