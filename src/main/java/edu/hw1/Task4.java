package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String broken) {
        var result = broken.toCharArray();
        for (var i = 0; i < result.length / 2; i++) {
            var temp = result[i * 2];
            result[i * 2] = result[i * 2 + 1];
            result[i * 2 + 1] = temp;
        }
        return new String(result);
    }
}
