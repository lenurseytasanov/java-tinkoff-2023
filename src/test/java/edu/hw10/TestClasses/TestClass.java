package edu.hw10.TestClasses;

import edu.hw10.Max;
import edu.hw10.Min;
import org.jetbrains.annotations.NotNull;

public class TestClass {

    private final int field1;

    private String field2;

    private double field3;

    public TestClass(@Min(-1) @Max(3) int arg1, @NotNull String arg2, @Max(1e4) double arg3) {
        this.field1 = arg1;
        this.field2 = arg2;
        this.field3 = arg3;
    }

    public static TestClass create(@Min(-1) @Max(3) int arg1, @NotNull String arg2, @Max(1e4) double arg3) {
        return new TestClass(arg1, arg2, arg3);
    }

    public int getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public double getField3() {
        return field3;
    }
}
