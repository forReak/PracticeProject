package springDemo;

import springDemo.customAnnotation.Autowired;
import springDemo.customAnnotation.Service;

@Service
public class Weather {

    //weather类需要用到rain类的一个方法，需要将rain注入进来
    @Autowired
    Rain rain;

    //weather类中的方法用到了rain中的方法
    public void weatherWantRain(){
        rain.raining();
    }

}
