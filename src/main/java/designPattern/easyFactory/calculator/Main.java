package designPattern.easyFactory.calculator;

import java.io.IOException;

//此类为计算器类 请实现输入两个数 输入一个运算符号 获取返回结果的程序
/* 如果不考虑设计模式，则直接用面向过程的方法就可以解决
但是如果面向设计模式，则可以将计算、输入进行继承、封装、实现面向对象的思维*/
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入第一个数");
        int a = (int)System.in.read();
        System.out.println("请输入运算符");
        char op = (char)System.in.read();
        System.out.println("请输入第二个数");
        int b = (int)System.in.read();
        int result;
        switch (String.valueOf(op)){
            case "+": result = new OpratorAdd().getResult(a,b);break;
            //利用继承的原理进行封装操作
            /*case "-": result = new OpratorAdd().getResult(a,b);break;
            case "*": result = new OpratorAdd().getResult(a,b);break;
            case "%": result = new OpratorAdd().getResult(a,b);break;*/
            default: result = 0;break;
        }
        System.out.println("结果是："+result);
    }
}
