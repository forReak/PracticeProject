package springDemo;

import java.util.List;

/**
 * 模拟controller调用service
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        //模拟spring启动的过程
        Main main = new Main();

        Object o = main.whatSpringDo();

        Weather weather =  (Weather)o;

        weather.weatherWantRain();

    }

    /**
     * 模拟spring启动的过程，实例化Weather，然后通过内部反射进行实例化Weather的依赖:Rain
     */
    private Object whatSpringDo() throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        //spring启动时需要加载的(spring内部启动时加载的必要组件)
        WeatherRef weatherRef = new WeatherRef();
        //实例化Weather
        Weather weather = new Weather();

        //扫描类注解（这一步，spring就会进行实例化Weather需要的对象，并把weather放入自己的容器中以便使用）
        weatherRef.getRef(weather);

        //返回当前需要的对象。为了方便，本demo中只有一个实例，直接取0位置的object
        List<Object> objects = weatherRef.returnObjectList();
        return objects.get(0);
    }
}
