package com.memory.base.logging;

import org.fusesource.jansi.AnsiConsole;

import java.io.OutputStream;

import ch.qos.logback.core.ConsoleAppender;

/**
 * @author memoryaxis@gmail.com
 * @date 2015/11/17 11:20
 */
public class AnsiConsoleAppender<E> extends ConsoleAppender<E> {

    @Override
    public void setOutputStream(OutputStream outputStream) {
        super.setOutputStream(AnsiConsole.wrapOutputStream(outputStream));
    }
}
