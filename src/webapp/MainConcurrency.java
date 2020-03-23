package webapp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainConcurrency {

    public static final int THREADS_NUMBERS = 10000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();
        System.out.println(thread0.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBERS);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREADS_NUMBERS; i++) {
            executorService.submit(() -> {
                //Thread thread = new Thread(() -> {
                for (int k = 0; k < 100; k++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
            });
            //   thread.start();
            //    threads.add(thread);
        }
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(counter);
    }

    private synchronized void inc() {
        double i = Math.sin(13.);
        counter++;
    }

}
