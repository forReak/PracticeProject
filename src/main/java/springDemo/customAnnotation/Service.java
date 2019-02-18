package springDemo.customAnnotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明一个自定义注解，模拟spring中的service
 */

@Target(ElementType.TYPE) //类
@Retention(RetentionPolicy.RUNTIME)//仅运行时保留
public @interface Service {
}
