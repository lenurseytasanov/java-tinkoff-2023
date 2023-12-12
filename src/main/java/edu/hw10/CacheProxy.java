package edu.hw10;

import java.lang.reflect.Proxy;

public final class CacheProxy {

    private CacheProxy() { }

    public static <T> T create(T obj, Class<?> cls) {
        return (T) Proxy.newProxyInstance(
            cls.getClassLoader(),
            new Class<?>[] {cls},
            new CachingInvocationHandler(obj)
        );
    }
}
