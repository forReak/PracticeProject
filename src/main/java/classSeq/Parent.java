package classSeq;

public class Parent {

    //注意，此处虽然没有直接进行输出，但是此处进行了声明，进行了赋值
    static int t = parentStaticMethod2();

    {
        System.out.println("父类构造块（实例块、初始化块）");
    }

    static{
        System.out.println("父类静态块");
    }

    public Parent(){
        System.out.println("父类构造方法");
    }

    public static void parentStaticMethod(){
        System.out.println("父类静态方法");
    }

    public static int parentStaticMethod2(){
        System.out.println("父类静态字段执行");
        return 1;
    }

}
