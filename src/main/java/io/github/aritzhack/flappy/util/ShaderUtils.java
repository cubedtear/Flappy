package io.github.aritzhack.flappy.util;

import io.github.aritzhack.flappy.Main;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

/**
 * @author Aritz Lopez
 */
public class ShaderUtils {

    private ShaderUtils() {
    }

    public static int load(String vertPath, String fragPath) {
        Main.LOG.t("Loading shaders from:");
        Main.LOG.t("\tVertex: {}", vertPath);
        Main.LOG.t("\tFragment: {}", fragPath);

        String vert = FileUtils.loadAsString(vertPath);
        String frag = FileUtils.loadAsString(fragPath);
        return create(vert, frag);
    }

    public static int create(String vert, String frag) {
        Main.LOG.t("Creating shader program");
        int program = glCreateProgram();
        int vertId = glCreateShader(GL_VERTEX_SHADER);
        int fragId = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertId, vert);
        glShaderSource(fragId, frag);

        glCompileShader(vertId);
        if (glGetShaderi(vertId, GL_COMPILE_STATUS) == GL_FALSE) {
            Main.LOG.e("Failed to compile vertex shader!");
            Main.LOG.e(glGetShaderInfoLog(vertId, 2048));
        }

        glCompileShader(fragId);
        if (glGetShaderi(fragId, GL_COMPILE_STATUS) == GL_FALSE) {
            Main.LOG.e("Failed to compile fragment shader!");
            Main.LOG.e(glGetShaderInfoLog(fragId, 2048));
        }

        glAttachShader(program, vertId);
        glAttachShader(program, fragId);

        glLinkProgram(program);
        glValidateProgram(program);

        return program;
    }
}
