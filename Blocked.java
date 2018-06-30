public class Blocked {


    public static void main(String[] args) throws Exception{

        Object object = new Object();


        Thread thread = new Thread(new T(object), "mythread1");
        thread.start();

        Thread.sleep(1000);

        Thread thread2 = new Thread(new T(object), "mythread2");
        thread2.start();

        System.in.read();

    }


    static class T implements Runnable{

        private Object lock;

        public T(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    Thread.sleep(10000000);
                } catch (Exception e) {

                }
            }
        }
    }
}
