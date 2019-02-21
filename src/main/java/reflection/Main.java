package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("reflection.Cat");
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println("获取声明的字段：");
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        Field[] fields = clazz.getFields();
        System.out.println("获取字段：");
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        System.out.println("获取声明的方法：");
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }

        Method[] methods = clazz.getMethods();
        System.out.println("获取方法：");
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader.toString());


    }
}
