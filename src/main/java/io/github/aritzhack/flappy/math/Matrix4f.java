package io.github.aritzhack.flappy.math;

/**
 * @author Aritz Lopez
 */
public class Matrix4f {

    public static final int SIZE = 4;

    public float[] matrix = new float[SIZE * SIZE];

    private Matrix4f() {

    }

    public void set(int x, int y, float value) {
        this.matrix[y + x * SIZE] = value;
    }

    public float get(int x, int y) {
        return this.matrix[y + x * SIZE];
    }

    public static Matrix4f identity() {
        Matrix4f mat = Matrix4f.zero();

        mat.set(0, 0, 1);
        mat.set(1, 1, 1);
        mat.set(2, 2, 1);
        mat.set(3, 3, 1);

        return mat;
    }

    public static Matrix4f zero() {
        return new Matrix4f();
    }

    public Matrix4f mult(Matrix4f other) {
        Matrix4f product = Matrix4f.zero();

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < 4; y++) {
                float sum = 0f;
                for (int e = 0; e < 4; e++) {
                    sum += this.get(x, e) * other.get(e, y);
                }
                product.set(x, y, sum);
            }
        }
        return product;
    }

    public Matrix4f translate(Vector3f vect) {
        Matrix4f result = Matrix4f.identity();

        result.set(3, 0, vect.x);
        result.set(3, 1, vect.y);
        result.set(3, 2, vect.z);

        return result;
    }

    public static Matrix4f rotateX(float angle) {
        Matrix4f result = Matrix4f.identity();

        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        result.set(1, 1, cos);
        result.set(2, 1, sin);

        result.set(1, 2, -sin);
        result.set(2, 2, cos);

        return result;
    }

    public static Matrix4f rotateY(float angle) {
        Matrix4f result = Matrix4f.identity();

        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        result.set(0, 0, cos);
        result.set(2, 0, -sin);

        result.set(0, 2, sin);
        result.set(2, 2, cos);

        return result;
    }

    public static Matrix4f rotateZ(float angle) {
        Matrix4f result = Matrix4f.identity();

        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        result.set(0, 0, cos);
        result.set(0, 1, sin);

        result.set(1, 0, -sin);
        result.set(1, 1, cos);

        return result;
    }

    public static Matrix4f rotate(float angleX, float angleY, float angleZ) {
        Matrix4f matX = Matrix4f.rotateX(angleX);
        Matrix4f matY = Matrix4f.rotateY(angleY);
        Matrix4f matZ = Matrix4f.rotateZ(angleZ);

        return matX.mult(matY).mult(matZ);
    }

    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f result = Matrix4f.identity();

        result.set(0, 0, 2f / (right - left));
        result.set(1, 1, 2f / (top - bottom));
        result.set(2, 2, 2f / (near - far));

        result.set(3, 0, (left + right) / (left - right));
        result.set(3, 1, (bottom + top) / (bottom - top));
        result.set(3, 2, (far + near) / (far - near));

        return result;
    }


}
