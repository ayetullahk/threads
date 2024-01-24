package MultiThreading01;

public class Volatile {


    // eğer threadler bir değere bağlı ise kod yapısına bağı olarak sonsuz döngüye girebilir
    //buna bağlı olarak volatile kullanılır
    volatile public static int flag =0;

    public static void main(String[] args) {
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (flag ==0){
                        System.out.println("Thread1 running");
                    }else break;
                }
            }
        });
        thread1.start();

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                flag =1;
                System.out.println("the value of flag is updated");
            }
        });
        thread2.start();

    }
}
