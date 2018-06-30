
import java.util.concurrent.locks.ReentrantLock;

public class Blocked2 {


    public static void main(String[] args) throws Exception{

        ReentrantLock object = new ReentrantLock();


        Thread thread = new Thread(new T(object), "mythread1");
        thread.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread(new T(object), "mythread2");
        thread2.start();

        System.in.read();

    }


    static class T implements Runnable{

        private ReentrantLock lock;

        public T(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
                try {
                    Thread.sleep(10000000);
                } catch (Exception e) {

                }
            }
        }


}

