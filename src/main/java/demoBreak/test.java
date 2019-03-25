package demoBreak;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for(int i = 0;i<100;i++){
            a.add(i);
            b.add(i);
        }

        test:for(int i=0;i<a.size();i++){
            for(int j = 0 ; j < b.size(); j ++){
                System.out.println("i is :"+ i +". j is :"+ j);
                if(i + j > 100){
                    break test;
                }
            }
        }
    }
}
