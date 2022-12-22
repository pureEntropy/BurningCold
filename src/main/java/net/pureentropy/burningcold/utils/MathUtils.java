package net.pureentropy.burningcold.utils;

public class MathUtils {
    public static double roundCoordinate(double n) {
        return Math.round(n * 100) / 100d;  // Round to 1/100th
    }
}
