package com.wcp.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

public class Closer {

    private static final Logger log = LoggerFactory.getLogger(Closer.class);

    public static void closeQuietly(Closeable... closeables) {
        close(false, closeables);
    }

    public static void close(Closeable... closeables) {
        close(true, closeables);
    }

    public static void close(boolean logOnException, Closeable...closeables) {
        if (closeables.length == 0) return;
        for (int i = 0; i < closeables.length; i++) {
            try {
                Closeable closeable = closeables[i];
                if(closeable != null) {
                    closeable.close();
                }
            } catch (Throwable e) {
                if(logOnException) log.warn("exception occurred", e);
            }
        }
    }
}
