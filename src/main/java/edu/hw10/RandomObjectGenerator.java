package edu.hw10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import static java.lang.Math.random;

public class RandomObjectGenerator {

    private static final Logger LOGGER = LogManager.getLogger();

    public Object nextObject(@NotNull Class<?> cls, String methodName) {
        try {
            Object result;
            if (methodName == null) {
                Constructor<?> constructor = cls.getConstructors()[0];
                Object[] args = getArgs(constructor);
                result = constructor.newInstance(args);
            } else {
                Method method = getMethod(cls, methodName);
                Object[] args = getArgs(method);
                result = method.invoke(null, args);
            }
            return result;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("error", e);
            throw new RuntimeException(e);
        }
    }

    private Object[] getArgs(@NotNull Executable method) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < parameters.length; i++) {
            Double min = null;
            Double max = null;
            for (Annotation annotation : annotations[i]) {
                if (annotation instanceof Min) {
                    min = ((Min) annotation).value();
                }
                if (annotation instanceof Max) {
                    max = ((Max) annotation).value();
                }
            }
            args[i] = nextArg(parameters[i].getType(), min, max);
        }
        return args;
    }

    private Method getMethod(@NotNull Class<?> cls, @NotNull String name) throws NoSuchMethodException {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        throw new NoSuchMethodException();
    }

    private Object nextArg(@NotNull Class<?> numberClass, Double origin, Double bound) {
        switch (numberClass.getSimpleName()) {
            case "Integer", "int":
                origin = origin == null ? (double) Integer.MIN_VALUE : origin;
                bound = bound == null ? (double) Integer.MAX_VALUE : bound;
                break;
            case "Long", "long":
                origin = origin == null ? (double) Long.MIN_VALUE : origin;
                bound = bound == null ? (double) Long.MAX_VALUE : bound;
                break;
            case "Float", "float":
                origin = origin == null ? (double) Float.MIN_VALUE : origin;
                bound = bound == null ? (double) Float.MAX_VALUE : bound;
                break;
            case "Double", "double":
                origin = origin == null ? Double.MIN_VALUE : origin;
                bound = bound == null ? Double.MAX_VALUE : bound;
                break;
        }
        return switch (numberClass.getSimpleName()) {
            case "Integer", "int" -> ThreadLocalRandom.current().nextInt(origin.intValue(), bound.intValue());
            case "Long", "long" -> ThreadLocalRandom.current().nextLong(origin.longValue(), bound.longValue());
            case "Float", "float" -> ThreadLocalRandom.current().nextFloat(origin.floatValue(), bound.floatValue());
            case "Double", "double" -> ThreadLocalRandom.current().nextDouble(origin, bound);
            case "Boolean", "boolean" -> ThreadLocalRandom.current().nextBoolean();
            case "String" -> Long.toHexString(ThreadLocalRandom.current().nextLong());
            default -> throw new IllegalArgumentException();
        };
    }
}
