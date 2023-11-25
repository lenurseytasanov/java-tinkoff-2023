package edu.hw7;

import edu.hw7.Task3.ParallelSearchDatabase;
import edu.hw7.Task3.ParallelSearchDatabaseLock;
import edu.hw7.Task3.Person;
import edu.hw7.Task3.PersonDatabase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private final static PersonDatabase DATABASE_1 = new ParallelSearchDatabase();
    private final static PersonDatabase DATABASE_2 = new ParallelSearchDatabaseLock();
    private final static List<Person> PERSON_TO_ADD = List.of(
        new Person(1, "John", "address1", "123"),
        new Person(2, "Mark", "address2", "124"),
        new Person(3, "John2", "address1", "126"),
        new Person(4, "John3", "address3", "123"),
        new Person(5, "John", "address4", "129"),
        new Person(6, "X", "address5", "127")
    );

    private static void fillDatabase(PersonDatabase database) {
        var threadPool = new ArrayList<Thread>();
        for (var person : PERSON_TO_ADD) {
            threadPool.add(new Thread(() -> database.add(person)));
        }
        threadPool.add(new Thread(() -> database.delete(5)));
        threadPool.add(new Thread(() -> database.delete(6)));

        threadPool.forEach(Thread::start);

        threadPool.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @BeforeAll
    static void fillDatabases() {
        fillDatabase(DATABASE_1);
        fillDatabase(DATABASE_2);
    }

    static Stream<PersonDatabase> provideDatabases() {
        return Stream.of(DATABASE_1, DATABASE_2);
    }

    @ParameterizedTest
    @MethodSource("provideDatabases")
    void findTest(PersonDatabase database) {
        assertEquals(List.of(PERSON_TO_ADD.get(0)), database.findByName("John"));

        assertEquals(List.of(PERSON_TO_ADD.get(0), PERSON_TO_ADD.get(2)), database.findByAddress("address1"));

        assertEquals(List.of(PERSON_TO_ADD.get(0), PERSON_TO_ADD.get(3)), database.findByPhone("123"));
    }
}
