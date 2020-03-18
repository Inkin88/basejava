package webapp;

public class DeadLock {

    private final static Object one = new Object();
    private final static Object two = new Object();

    public static void main(String[] args) {
        lock(one, two);
        lock(two, one);
    }

    private static void lock(Object o1, Object o2) {
        new Thread(() -> {
            synchronized (o1) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " Hello");
                }
            }
        }).start();
    }
}
