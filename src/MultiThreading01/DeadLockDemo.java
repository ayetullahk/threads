package MultiThreading01;

public class DeadLockDemo {
    public static void main(String[] args) {
        final String lock1="lock1";
        final String lock2="lock2";

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    System.out.println("Thread1: locked the lock1");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                synchronized (lock2){
                    System.out.println("Thread1: locked the lock2");
                }
            }
        });
        thread1.start();

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                //locklar yukarıdaki thread ile ters sıraya alınmaya calışınca deadlock meydana geliyor
                synchronized (lock2){
                    System.out.println("Thread2: locked the lock2");

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                synchronized (lock1){
                    System.out.println("Thread2: locked the lock1");
                }
            }
        });
        thread2.start();

    }
}
