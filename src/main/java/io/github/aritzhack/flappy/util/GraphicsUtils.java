package io.github.aritzhack.flappy.util;

/**
 * @author Aritz Lopez
 */
public class GraphicsUtils {

    public static int ARGBtoRGBA(int argb) {
        int a = (argb & 0xFF000000) >>> 24;
        System.out.println(Integer.toHexString(a));
        int ret = argb << 8;
        return ret | a;
    }
}
