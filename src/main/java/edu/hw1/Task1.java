package edu.hw1;

public final class Task1 {
    private static final int SECONDS_NUMBER = 60;

    private Task1() {
    }

    public static int minutesToSeconds(String time) {
        if (time != null) {
            var timeSplit = time.split(":");
            if (timeSplit.length == 2) {
                try {
                    var minutes = Integer.parseInt(timeSplit[0]);
                    var seconds = Integer.parseInt(timeSplit[1]);
                    if (minutes >= 0 && seconds >= 0 && seconds < SECONDS_NUMBER) {
                        return minutes * SECONDS_NUMBER + seconds;
                    }
                } catch (NumberFormatException exception) {
                    return -1;
                }
            }
        }

        return -1;
    }
}
