package cz.cvut.fel.omo.SmartHome.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static double getRandomDoubleInRange(double min, double max){
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static double getRandomDouble(){
        return ThreadLocalRandom.current().nextDouble();
    }

    public static boolean getRandomBoolean(){
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static int getRandomIntInRange(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
