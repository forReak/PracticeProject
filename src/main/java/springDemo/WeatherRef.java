package springDemo;

import springDemo.customAnnotation.Autowired;
import springDemo.customAnnotation.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类描述了spring的反射注入
 */
public class WeatherRef {

    //模拟spring容器
    List<Object> objectList;

    public WeatherRef(){
        objectList = new ArrayList<>();
    }


    public List<Object> returnObjectList(){
        return this.objectList;
    }


    /**
     * 此方法是模拟spring容器对weather及rain两个类是如何管理的
     * 其中标注为核心的地方是springIOC如何通过反射进行依赖注入的
     *
     * @author furao
     * @date 2019/2/18 16:36
     * @param weather
     * @return
     * @throws
     */
    public void getRef(Object weather) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = weather.getClass();

        //判断是否带有service注解
        if(clazz.isAnnotationPresent(Service.class)){
            //获取当前类中的所有声明的字段
            Field[] declaredFields = clazz.getDeclaredFields();
            //循环当前类中的所有字段
            for (Field fieldRain : declaredFields) {

                //判断字段是否加有autowired注解
                if(fieldRain.isAnnotationPresent(Autowired.class)){

                    //以下为IOC核心：
                    //将用autowired注解的当前类进行加载
                    Class<?> rainClass=Class.forName(fieldRain.getType().getName(),false,Thread.currentThread().getContextClassLoader());

                    //为object中的加注解的字段（field）声明了一个实例
                    fieldRain.set(weather,rainClass.newInstance());

                    //将已被赋值的weather放入spring容器内
                    objectList.add(weather);
                }

            }
        }
    }
}
