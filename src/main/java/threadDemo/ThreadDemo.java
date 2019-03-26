package threadDemo;

/**
 * 多线程demo
 */
public class ThreadDemo {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0 ; i < 100 ; i ++){
            new Thread(() -> {
                for(int j = 0 ; j < 100 ; j ++){
                    synchronized (ThreadDemo.class){
                        count++;
                    }
                    System.out.println(count);
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("final:"+count);
    }
}
