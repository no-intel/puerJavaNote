package deadlock;

public class Deadlock {
    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (LOCK_1) {
                System.out.println("1번 쓰레드 : Acquired LOCK 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                System.out.println("1번 쓰레드 : Waiting for LOCK 2");
                synchronized (LOCK_2) {
                    System.out.println("1번 쓰레드 : Acquired LOCK 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (LOCK_2) {
                System.out.println("2번 쓰레드 : Acquired LOCK 2");
                System.out.println("2번 쓰레드 : Waiting for LOCK 1");
                synchronized (LOCK_1) {
                    System.out.println("2번 쓰레드 : Acquired LOCK 1");
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(200);
                System.out.println("3번 쓰레드도 출발");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (LOCK_1) {
                System.out.println("3번 쓰레드 : Acquired LOCK 1");
            }
        });

        thread1.setName("1번 쓰레드");
        thread2.setName("2번 쓰레드");
        thread3.setName("3번 쓰레드");
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Main thread finished");
    }
}
