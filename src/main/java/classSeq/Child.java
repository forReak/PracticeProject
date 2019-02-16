package classSeq;

public class Child extends Parent{


    {
        System.out.println("子类构造块（实例块、初始化块）");
    }

    static{
        System.out.println("子类静态块");
    }

    public Child(){
        System.out.println("子类构造方法");
    }

    public static void parentStaticMethod(){
        System.out.println("子类静态方法");
    }

    public void childMethod(){
        System.out.println("子类普通方法");
    }

}
