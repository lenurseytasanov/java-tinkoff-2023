package edu.hw2.Task4;

public final class Utility {
    private Utility() {
    }

    public static CallingInfo callingInfo() {
        var trace = new Exception().getStackTrace();
        var fullClassName = trace[1].getClassName().split("\\.");
        return new CallingInfo(fullClassName[fullClassName.length - 1], trace[1].getMethodName());
    }
}
