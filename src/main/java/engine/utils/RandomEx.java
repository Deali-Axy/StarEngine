package engine.utils;

import java.util.Random;

/**
 * 随机数产生类
 */
public class RandomEx {
    public static double nextDouble() {
        return Math.random();
    }

    public static double nextDouble(double value) {
        Random random = new Random(System.currentTimeMillis() + (long) (Math.random() * 1000));
        return random.nextDouble() * value;
    }

    public static double nextDouble(double v1, double v2) {
        Random random = new Random(System.currentTimeMillis() + (long) (Math.random() * 1000));
        return random.nextDouble() * v2 + v1;
    }

    public static int nextInt() {
        Random random = new Random(System.currentTimeMillis() + (long) (Math.random() * 1000));
        return random.nextInt();
    }

    public static int nextInt(int value) {
        Random random = new Random(System.currentTimeMillis() + (long) (Math.random() * 1000));
        return random.nextInt(value);
    }

    public static int nextInt(int v1, int v2) {
        Random random = new Random(System.currentTimeMillis() + (long) (Math.random() * 1000));
        return random.nextInt(v2) + v1;
    }
}
