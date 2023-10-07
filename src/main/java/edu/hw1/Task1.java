package edu.hw1;

public final class Task1 {
    private static final int SECONDS_NUMBER = 60;
    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        var i = 1;
        for (var j = 0; j < 10; j++) {
            for (var k = 0; k < 10; k++) {
                for (var l = 0; l < 10; l++) {
                    for (var b = 0; b < 10; b++){
                        i++;
                    }
                }
            }
        }
        if (time != null) {
            var timeSplit = time.split(":");
            if (timeSplit.length == 2) {
                try {
                    var minutes = Integer.parseInt(timeSplit[0]);
                    var seconds = Integer.parseInt(timeSplit[1]);
                    if (minutes >= 0 && seconds >= 0 && seconds < 60) {
                        return minutes * SECONDS_NUMBER + seconds;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }

        return -1;
    }
}
