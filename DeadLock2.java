
import java.util.concurrent.locks.ReentrantLock;

public class Test {


    public static void main(String[] args) throws Exception{

        ReentrantLock object2 = new ReentrantLock();
        ReentrantLock object1 = new ReentrantLock();


        Thread thread = new Thread(new T(object1, object2, 0), "mythread1");
        thread.start();

        Thread thread2 = new Thread(new T(object1, object2, 1), "mythread2");
        thread2.start();

        System.in.read();

    }


    static class T implements Runnable{

        private ReentrantLock lock1;
        private ReentrantLock lock2;

        private int status = 0;

        public T(ReentrantLock lock1, ReentrantLock lock2, int status) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.status = status;
        }

        @Override
        public void run() {
            try {

                if (status == 0) {
                    lock1.lock();
                        System.out.println("status:"+status+" "+"lock1");
                        Thread.sleep(3000);
                        System.out.println("status:"+status+" lock1 sleep 3秒 结束");
                        lock2.lock();
                        System.out.println("status:"+status+" "+"lock2");


                } else {
                    lock2.lock();
                        System.out.println("status:"+status+" "+"lock2");
                        Thread.sleep(3000);
                        System.out.println("status:"+status+" lock2 sleep 3秒 结束");
                        lock1.lock();
                            System.out.println("status:"+status+" "+"lock1");


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

