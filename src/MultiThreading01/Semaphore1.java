package MultiThreading01;

import java.util.concurrent.Semaphore;

public class Semaphore1 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(4);
        Thread.sleep(30000);

        System.out.println("total available car lot in the car park" + semaphore.availablePermits());

        Car carA = new Car("A", semaphore);
        carA.start();

        Car carb = new Car("B", semaphore);
        carb.start();

        Car carc = new Car("C", semaphore);
        carc.start();

        Car card = new Car("D", semaphore);
        card.start();

        Car care = new Car("E", semaphore);
        care.start();

        Car carf = new Car("F", semaphore);
        carf.start();

    }
}

class Car extends Thread {
    private String name;
    Semaphore semaphore;

    public Car(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            System.out.println(name + ": acquiring the lot...");
            semaphore.acquire();

            System.out.println(name + ": got the lot!");

            for (int i = 0; i < 4; i++) {
                System.out.println(name + ": waiting in the car park" + i + "hour");
                Thread.sleep(10000);

            }
            System.out.println(name + "... releasing the lot");
            semaphore.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
