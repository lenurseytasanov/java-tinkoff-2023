package edu.hw10;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class RandomObjectGenerator {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String INT_LABEL_0 = "integer";
    private static final String INT_LABEL_1 = "int";
    private static final String LONG_LABEL = "long";
    private static final String FLOAT_LABEL = "float";
    private static final String DOUBLE_LABEL = "double";
    private static final String BOOLEAN_LABEL = "boolean";
    private static final String STRING_LABEL = "string";

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
        } catch (NoSuchMethodException | InstantiationException
                 | IllegalAccessException | InvocationTargetException e) {
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
        List<Double> bounds = getBounds(numberClass, origin, bound);
        Double min = bounds.get(0);
        Double max = bounds.get(1);
        return switch (numberClass.getSimpleName().toLowerCase()) {
            case INT_LABEL_0, INT_LABEL_1 -> ThreadLocalRandom.current().nextInt(min.intValue(), max.intValue());
            case LONG_LABEL -> ThreadLocalRandom.current().nextLong(min.longValue(), max.longValue());
            case FLOAT_LABEL -> ThreadLocalRandom.current().nextFloat(min.floatValue(), max.floatValue());
            case DOUBLE_LABEL -> ThreadLocalRandom.current().nextDouble(min, max);
            case BOOLEAN_LABEL -> ThreadLocalRandom.current().nextBoolean();
            case STRING_LABEL -> Long.toHexString(ThreadLocalRandom.current().nextLong());
            default -> throw new IllegalArgumentException();
        };
    }

    private List<Double> getBounds(Class<?> numberClass, Double min, Double max) {
        return switch (numberClass.getSimpleName().toLowerCase()) {
            case INT_LABEL_0, INT_LABEL_1 -> List.of(
                min == null ? (double) Integer.MIN_VALUE : min,
                max == null ? (double) Integer.MAX_VALUE : max
            );
            case LONG_LABEL -> List.of(
                min == null ? (double) Long.MIN_VALUE : min,
                max == null ? (double) Long.MAX_VALUE : max
            );
            case FLOAT_LABEL -> List.of(
                min == null ? (double) Float.MIN_VALUE : min,
                max == null ? (double) Float.MAX_VALUE : max
            );
            case DOUBLE_LABEL -> List.of(
                min == null ? Double.MIN_VALUE : min,
                max == null ? Double.MAX_VALUE : max
            );
            default -> List.of(0.0, 0.0);
        };
    }
}
