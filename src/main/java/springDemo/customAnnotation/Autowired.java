package springDemo.customAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明一个自定义注解，模拟spring中的service
 */

@Target(ElementType.FIELD)//声明这个自定义注解是描述字段的
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
