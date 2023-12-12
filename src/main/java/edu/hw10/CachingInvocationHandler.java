package edu.hw10;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CachingInvocationHandler implements InvocationHandler {

    private static final Path CACHE_PATH = Paths.get("src/test/java/edu/hw10/caching.txt");

    private final Object obj;

    public CachingInvocationHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj, args);

        Cache annotation = method.getAnnotation(Cache.class);
        if (annotation != null && annotation.persist()) {
            try (PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(Files.newOutputStream(CACHE_PATH)))) {
                printWriter.println(result);
            }
        }

        return result;
    }
}
