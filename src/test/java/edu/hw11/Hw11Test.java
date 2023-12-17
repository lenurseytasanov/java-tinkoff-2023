package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Hw11Test {

    @Test
    void task1() {
        // Arrange
        Class<?> newClass = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
        Object obj;

        // Act
        try {
            obj = newClass.getConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // Assert
        assertEquals("Hello ByteBuddy!", obj.toString());
    }

    @Test
    void task2() {
        // Arrange
        TypeDescription arithmeticBase = TypePool.Default.ofSystemLoader()
            .describe("edu.hw11.ArithmeticUtils")
            .resolve();
        Class<?> newClass = new ByteBuddy()
            .redefine(arithmeticBase, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(MultiplyInterceptor.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
            .getLoaded();
        int actual;

        // Act
        try {
            Method method = newClass.getDeclaredMethod("sum", int.class, int.class);
            actual = (int) method.invoke(null, 5, 5);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // Assert
        assertEquals(25, actual);
    }
}
