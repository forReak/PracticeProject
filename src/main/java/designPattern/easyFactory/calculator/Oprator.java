package designPattern.easyFactory.calculator;

/**
 * 抽象类 计算器类 通过抽象出参数和运算符进行编程
 * 此方法不能写作接口 因为接口不能定义未赋值参数
 */
public abstract class Oprator {
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public abstract int getResult(int a,int b);
}
