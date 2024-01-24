package MultiThreading01;

public class WaitNotify {

    public static int balance =0;
    public static void main(String[] args) {
        WaitNotify obj= new WaitNotify();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                obj.withdraw(800);
            }
        });

        thread1.setName("Withdraw Thread");
        thread1.start();
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                obj.deposit(2000);
            }
        });

        thread2.setName("Deposit Thread");
        thread2.start();


    }

    public void withdraw(int amount){
        synchronized (this){
            if (balance<= 0 ||balance<amount){

                try {
                    System.out.println("Waiting for the amount to update");
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        balance=balance-amount;
        System.out.println("Withdrawal is successful. The current balance is"+balance);
    }
    public void deposit(int amount){
        balance=balance+amount;
        System.out.println("The amount is deposited. the current balance is "+balance);
        synchronized (this){
            notify();
        }
    }
}
