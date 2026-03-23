package HW5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenLock {
    private static int value = 1;

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread oddThread = new Thread(new PrintTask(true), "OddThread");
        Thread evenThread = new Thread(new PrintTask(false), "EvenThread");

        oddThread.start();
        evenThread.start();
    }

    static class PrintTask implements Runnable {
        private final boolean isOdd;

        public PrintTask(boolean isOdd) {
            this.isOdd = isOdd;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    // wait until it's this thread's turn
                    while (value <= 10 && (value % 2 == 0) == isOdd) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (value > 10) {
                        condition.signalAll();
                        break;
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + value++);
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }

    }
}
