package MultiThreading01;

public class MultiThreading {
    public static void main(String[] args) throws InterruptedException {
        long startTime=System.currentTimeMillis();
        CounterWithoutMultiThread counter1=new CounterWithoutMultiThread(1);
        CounterWithoutMultiThread counter2=new CounterWithoutMultiThread(2);

        counter1.countMe();

        System.out.println("----------------------------");

        counter2.countMe();

        long endTime=System.currentTimeMillis();

        System.out.println("WithoutMiltiThread Elapsed Time"+(endTime-startTime));

        System.out.println("******************************************");

        long startTime2=System.currentTimeMillis();
        CounterWitMultiThread counter3=new CounterWitMultiThread(1);
        CounterWitMultiThread counter4=new CounterWitMultiThread(2);

        counter3.start();

        System.out.println("----------------------------");

        counter4.start();

        // n adet threadin olduğu yapıda join() kullanmak mantıklı olmaz Threadleri sıraya sokmak için mantıklı değildir
        counter3.join();
        counter4.join();

        long endTime2=System.currentTimeMillis();

        System.out.println("WitMultiThread Elapsed Time"+(endTime2-startTime2));

    }
}
class CounterWithoutMultiThread{
    public CounterWithoutMultiThread(int threadNum) {
        this.threadNum = threadNum;
    }

    private int threadNum;
    // synchronized bu metodu kullannan threadları sıraya sokar.
    public synchronized void countMe() throws InterruptedException{
        for (int i=1; i<=10;i++){
            Thread.sleep(500);

            System.out.println("i"+i+"Tread number"+threadNum);
        }
    }
}

class CounterWitMultiThread extends Thread{
    public void run(){
        try {
            countMe();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public CounterWitMultiThread(int threadNum) {
        this.threadNum = threadNum;
    }

    private int threadNum;
    public void countMe() throws InterruptedException{
        for (int i=1; i<=10;i++){
            Thread.sleep(500);

            System.out.println("i"+i+"Tread number"+threadNum);
        }
    }
}


