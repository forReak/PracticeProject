package threadDemo;

/**
 * 多线程demo,同步count字段。利用锁当前类的方式进行同步
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
