package Thread;

public class ThreadCreationWays {
    public static void main(String[] args) {

        System.out.println("Current Thread : "+ Thread.currentThread().getName());

        MyTread thread1=new MyTread();
        //thread1.run(); bu run metodu çağırılarak thread başlatılmaz
        thread1.start(); // start metodu ile thread ile çalıştırılır --> RUNNABLE state geçer

        MyRunnable runnable=new MyRunnable();
        Thread thread2=new Thread(runnable);
        thread2.start();

        Thread thread3=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Current Thread : "+ Thread.currentThread().getName());
                System.out.println("It is annonymous structure way");

            }
        });

        Thread thread4=new Thread(()-> System.out.println("ıt is lambda way"));

        thread3.setName("Thread3");
        thread3.start();

        System.out.println("Main method is ending");



    }
}

//1.way to create
class MyTread extends Thread{
    public void run(){
        System.out.println("It is custom thread is running");
    }

}

