package edu.hw2;

import edu.hw2.Task3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {
    @Test
    public void successConnectionTest() {
        var executor1 = new PopularCommandExecutor(new DefaultConnectionManager(1), 1);
        var executor2 = new PopularCommandExecutor(new DefaultConnectionManager(0.5), 100);
        assertDoesNotThrow(executor1::updatePackages);
        assertDoesNotThrow(executor2::updatePackages);
    }

    private void tryGetException(ConnectionManager manager) {
        var executor = new PopularCommandExecutor(manager, 1);
        for (var i = 0; i < 100; i++) {
            executor.updatePackages();
        }
    }


    @Test
    public void failureConnectionTest() {
        assertThrows(ConnectionException.class, () -> tryGetException(new FaultyConnectionManager()));
        assertThrows(ConnectionException.class, () -> tryGetException(new DefaultConnectionManager(0)));
    }
}
