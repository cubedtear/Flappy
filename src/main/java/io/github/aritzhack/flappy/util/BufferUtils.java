package io.github.aritzhack.flappy.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * @author Aritz Lopez
 */
public class BufferUtils {

    public static ByteBuffer toByteBuffer(byte[] array) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
        buffer.put(array).flip();
        return buffer;
    }

    public static FloatBuffer toFloatBuffer(float[] array) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(array).flip();
        return buffer;
    }

    public static IntBuffer toIntBuffer(int[] array) {
        IntBuffer buffer = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(array).flip();
        return buffer;
    }
}
