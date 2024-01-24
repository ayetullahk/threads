package MultiThreading01;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch1 {
    public static void main(String[] args) {

        //gerekli servislerin işini yapana kadar main task beklemiştir
        CountDownLatch latch=new CountDownLatch(4);

        WorkerThread worker1=new WorkerThread("Worker-1",15000,latch);
        WorkerThread worker2=new WorkerThread("Worker-2",15000,latch);
        WorkerThread worker3=new WorkerThread("Worker-3",15000,latch);
        WorkerThread worker4=new WorkerThread("Worker-4",15000,latch);

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName()+"has finished");

    }
}
class WorkerThread extends Thread{
    private int delay;
    private CountDownLatch latch;
    public WorkerThread(String name, int delay, CountDownLatch latch){
        super(name);
        this.delay=delay;
        this.latch=latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            System.out.println(Thread.currentThread().getName()+"Running a task");
            //worker threadin hangi taskı yapısını istiyorsanız onlar burada olacaktır
            latch.countDown();

            System.out.println(Thread.currentThread().getName()+"Finished");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
