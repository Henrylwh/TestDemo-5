package cn.com.gf.demo.base.utils;

/**
 * Created by Administrator on 2017/4/23.
 */
import java.util.Random;
public class Util {
    public int randomNo(){
        Random random = new Random();
        return  (int) (random.nextDouble() * (9999999 - 1000000 + 1)) + 1000000;
    }
    public int randomYear(){
        Random random = new Random();
        return  (int) (random.nextDouble() * (2999 - 1900 + 1)) + 1900;
    }
}
