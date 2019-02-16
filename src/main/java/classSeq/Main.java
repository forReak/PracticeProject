package classSeq;

public class Main {

    public static void main(String[] args) {
        //运行：
        Parent child = new Child();
        //结果：
        //父类静态字段执行
        //父类静态块
        //子类静态块
        //父类构造块（实例块、初始化块）
        //父类构造方法
        //子类构造块（实例块、初始化块）
        //子类构造方法

        //运行
        Parent.parentStaticMethod();
        //结果：
        //父类静态字段执行
        //父类静态块
        //父类静态方法

    }
}
