package mthread;

class SharedResource {
    synchronized void waitMethod() {
        System.out.println(Thread.currentThread().getName() + " is going to wait.");
        try {
            wait(); // Releases the lock and waits
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " resumed after wait.");
    }

    synchronized void notifyMethod() {
        System.out.println(Thread.currentThread().getName() + " is going to notify.");
        notify(); // Wakes up one waiting thread
    }
}

class Worker1 extends Thread {
    SharedResource resource;

    Worker1(SharedResource r) {
        this.resource = r;
    }

    public void run() {
        resource.waitMethod();
    }
}

class Worker2 extends Thread {
    SharedResource resource;

    Worker2(SharedResource r) {
        this.resource = r;
    }

    public void run() {
        try {
            Thread.sleep(2000); // Delay to ensure wait happens first
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resource.notifyMethod();
    }
}

public class MultiThreadingEx {
    public static void main(String[] args) {
        SharedResource res = new SharedResource();

        Worker1 t1 = new Worker1(res);
        Worker2 t2 = new Worker2(res);

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join(); // Wait for t1 to finish
            t2.join(); // Wait for t2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread ends.");
    }
}
