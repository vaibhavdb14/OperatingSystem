import java.util.concurrent.Semaphore;

class ReaderWriter {
   
    static Semaphore mutex = new Semaphore(1);
    static Semaphore wrt = new Semaphore(1);

    static int readCount = 0; 

    static class Reader implements Runnable {
        public void run() {
            try {
               
                mutex.acquire();
                readCount++;
                if (readCount == 1) wrt.acquire(); 
                mutex.release();

                
                System.out.println(Thread.currentThread().getName() + " is READING");
                Thread.sleep(500);

                mutex.acquire();
                readCount--;
                if (readCount == 0) wrt.release(); 
                mutex.release();
                System.out.println(Thread.currentThread().getName() + " finished READING");

            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    static class Writer implements Runnable {
        public void run() {
            try {
            
                wrt.acquire(); 
                System.out.println(Thread.currentThread().getName() + " is WRITING");
                Thread.sleep(1000);

                wrt.release();
                System.out.println(Thread.currentThread().getName() + " finished WRITING");

            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        
        Thread r1 = new Thread(new Reader(), "Reader1");
        Thread r2 = new Thread(new Reader(), "Reader2");
        Thread w1 = new Thread(new Writer(), "Writer1");
        Thread r3 = new Thread(new Reader(), "Reader3");
        Thread w2 = new Thread(new Writer(), "Writer2");

        r1.start();
        w2.start();
        r2.start();
        w1.start();
        r3.start();
    }
}
