/*
 * Copyright (c) 2014 Aritzh (Aritz Lopez)
 *
 * This file is part of AritzhUtil
 *
 * AritzhUtil is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * AritzhUtil is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with AritzhUtil.
 * If not, see http://www.gnu.org/licenses/.
 */

package io.github.aritzhack.flappy;

import io.github.aritzhack.aritzh.logging.ILogger;
import io.github.aritzhack.aritzh.logging.SLF4JLogger;
import io.github.aritzhack.aritzh.util.OSUtil;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL11.*;

/**
 * @author Aritz Lopez
 */
public class Main implements Runnable {

    public static final ILogger LOG = new SLF4JLogger("MainLogger");

    private final int WIDTH = 1280, HEIGHT = 720;
    private final String TITLE = "Flappy";

    private boolean running = false;
    private Thread thread;

    public void start() {
        running = true;
        this.thread = new Thread(this, "Display");
        thread.start();
    }

    public static void main(String[] args) {
        new Main().start();
    }

    @Override
    public void run() {
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle(TITLE);

            // Due to a bug in LWJGL
            ContextAttribs context;
            if (OSUtil.getOs() == OSUtil.EnumOS.MACOS) context = new ContextAttribs(3, 2);
            else context = new ContextAttribs(3, 3);

            Display.create(new PixelFormat(), context.withProfileCore(true));
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        init();

        while (running && !Display.isCloseRequested()) {
            render();

            Display.update();
        }
        Display.destroy();
    }

    private void init() {
        LOG.i("OpenGL {}", glGetString(GL_VERSION));

        glClearColor(1f, 1f, 1f, 1f);
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
