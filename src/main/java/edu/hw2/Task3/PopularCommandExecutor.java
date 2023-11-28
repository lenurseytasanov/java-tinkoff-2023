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
        for (var attempt = 0; attempt < maxAttempts; attempt++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (Exception exception) {
                if (attempt == maxAttempts - 1) {
                    throw new ConnectionException("message", exception.getCause());
                }
            }
        }
    }
}
