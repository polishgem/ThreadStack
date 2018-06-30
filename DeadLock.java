public class DeadLock {


    public static void main(String[] args) throws Exception{

        Object object2 = new Object();
        Object object1 = new Object();


        Thread thread = new Thread(new T(object1, object2, 0), "mythread1");
        thread.start();

        Thread thread2 = new Thread(new T(object1, object2, 1), "mythread2");
        thread2.start();

        System.in.read();

    }


    static class T implements Runnable{

        private Object lock1;
        private Object lock2;

        private int status = 0;

        public T(Object lock1, Object lock2, int status) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.status = status;
        }

        @Override
        public void run() {
            try {

                if (status == 0) {
                    synchronized (lock1) {
                        System.out.println("status:"+status+" "+"lock1");
                        Thread.sleep(3000);
                        System.out.println("status:"+status+" lock1 sleep 3秒 结束");

                        synchronized (lock2) {
                            System.out.println("status:"+status+" "+"lock2");
                        }
                    }
                } else {
                    synchronized (lock2) {
                        System.out.println("status:"+status+" "+"lock2");
                        Thread.sleep(3000);
                        System.out.println("status:"+status+" lock2 sleep 3秒 结束");

                        synchronized (lock1) {
                            System.out.println("status:"+status+" "+"lock1");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

