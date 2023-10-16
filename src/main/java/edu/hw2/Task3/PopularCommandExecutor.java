package edu.hw2.Task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {
        var isSuccess = true;
        for (var attempt = 0; attempt < maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                isSuccess = true;
                connection.execute(command);
            } catch (Exception exception) {
                isSuccess = false;
                if (attempt == maxAttempts - 1) {
                    throw new ConnectionException("message", exception.getCause());
                }
            }
            if (isSuccess) {
                return;
            }
        }
    }
}
