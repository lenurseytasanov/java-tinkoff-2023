package edu.hw4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static edu.hw4.Main.task1;
import static edu.hw4.Main.task10;
import static edu.hw4.Main.task11;
import static edu.hw4.Main.task12;
import static edu.hw4.Main.task13;
import static edu.hw4.Main.task14;
import static edu.hw4.Main.task15;
import static edu.hw4.Main.task16;
import static edu.hw4.Main.task17;
import static edu.hw4.Main.task18;
import static edu.hw4.Main.task19;
import static edu.hw4.Main.task2;
import static edu.hw4.Main.task20;
import static edu.hw4.Main.task3;
import static edu.hw4.Main.task4;
import static edu.hw4.Main.task5;
import static edu.hw4.Main.task6;
import static edu.hw4.Main.task7;
import static edu.hw4.Main.task8;
import static edu.hw4.Main.task9;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    @Test
    void task1Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = List.of("Parrot", "Diana", "Lucy");

        // Act
        var actual = task1(animals).stream()
            .map(Animal::name)
            .toList();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void task2Test() {
        // Arrange
        var k = 2;
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = List.of("Lucy", "Parrot");

        // Act
        var actual = task2(animals, k).stream()
            .map(Animal::name)
            .toList();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void task3Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = Map.of(Type.BIRD, 2, Type.CAT, 1);

        // Act
        var actual = task3(animals);

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    void task4Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );

        // Act
        var actual = task4(animals).name();

        // Assert
        assertEquals("Parrot", actual);
    }

    @Test
    void task5Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );

        // Assert
        assertEquals(Sex.F, task5(animals));
    }

    @Test
    void task6Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = Map.of(
            Type.BIRD, animals.get(1), Type.CAT, animals.get(2));

        // Assert
        assertEquals(expected, task6(animals));
    }

    @Test
    void task7Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 4, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 5, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 5, 10, 10, true),
            new Animal("Max", Type.BIRD, Sex.M, 3, 4, 4, true),
            new Animal("Mark", Type.CAT, Sex.M, 5, 10, 10, true)
        );

        // Assert
        assertEquals("Parrot", task7(animals, 1).name());
        assertEquals("Mark", task7(animals, 3).name());
        assertEquals("Diana", task7(animals, 4).name());
    }

    @Test
    void task8Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );

        // Act
        var actual = task8(animals, 6).orElseThrow(NoSuchElementException::new).name();

        // Assert
        assertEquals("Parrot", actual);
        assertFalse(task8(animals, 2).isPresent());
    }

    @Test
    void task9Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );

        // Assert
        assertEquals(8, task9(animals));
    }

    @Test
    void task10Test() {
        // Arrange
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );

        // Act
        var actual = task10(animals).stream().map(Animal::name).toList();

        // Assert
        assertEquals(List.of("Diana", "Lucy"), actual);
    }

    @Test
    void task11Test() {
        // Arrange
        var animals = List.of(
            new Animal("1", Type.DOG, Sex.F, 10, 120, 50, true),
            new Animal("2", Type.CAT, Sex.F, 10, 30, 10, true),
            new Animal("3", Type.DOG, Sex.F, 10, 110, 50, false)
        );

        // Assert
        assertEquals(List.of(animals.get(0)), task11(animals));
    }

    @Test
    void task12Test() {
        // Arrange
        var animals = List.of(
            new Animal("1", Type.DOG, Sex.F, 10, 50, 60, true),
            new Animal("2", Type.CAT, Sex.F, 10, 20, 10, true),
            new Animal("3", Type.FISH, Sex.F, 1, 1, 2, false)
        );

        // Assert
        assertEquals(2, task12(animals));
    }

    @Test
    void task13Test() {
        // Arrange
        var animals = List.of(
            new Animal("name", Type.DOG, Sex.F, 10, 50, 60, true),
            new Animal("name name", Type.CAT, Sex.F, 10, 20, 10, true),
            new Animal("name name name", Type.FISH, Sex.F, 1, 1, 2, false)
        );

        // Assert
        assertEquals(animals.subList(1, 3), task13(animals));
    }

    @Test
    void task14Test() {
        // Arrange
        var animals = List.of(
            new Animal("name", Type.DOG, Sex.F, 10, 50, 60, true),
            new Animal("name name", Type.CAT, Sex.F, 10, 20, 10, true),
            new Animal("name name name", Type.FISH, Sex.F, 1, 1, 2, false)
        );

        // Assert
        assertTrue(task14(animals, 40));
    }

    @Test
    void task15Test() {
        // Arrange
        var animals = List.of(
            new Animal("name", Type.DOG, Sex.F, 3, 50, 60, true),
            new Animal("name name", Type.CAT, Sex.F, 5, 20, 10, true),
            new Animal("name name name", Type.FISH, Sex.F, 7, 1, 2, false),
            new Animal("1", Type.DOG, Sex.F, 9, 120, 50, true),
            new Animal("2", Type.CAT, Sex.F, 11, 30, 10, true),
            new Animal("3", Type.DOG, Sex.F, 13, 110, 50, false)
        );

        // Assert
        assertEquals(62, task15(animals, 5, 10));
    }

    @Test
    void task16Test() {
        // Arrange
        var animals = List.of(
            new Animal("d", Type.FISH, Sex.F, 1, 1, 2, false),
            new Animal("c", Type.DOG, Sex.F, 10, 20, 10, true),
            new Animal("b", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true)
        );

        // Act
        var actual = task16(animals).stream().map(Animal::name).toList();

        // Assert
        assertEquals(List.of("a", "b", "c", "d"), actual);
    }

    @Test
    void task17Test() {
        // Arrange
        var animals = List.of(
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("b", Type.DOG, Sex.M, 10, 50, 60, false),
            new Animal("c", Type.SPIDER, Sex.F, 10, 20, 10, true),
            new Animal("d", Type.SPIDER, Sex.F, 1, 1, 2, true)
        );

        // Assert
        assertTrue(task17(animals));
    }

    @Test
    void task18Test() {
        // Arrange
        var animals1 = List.of(
            new Animal("a1", Type.FISH, Sex.M, 10, 50, 60, true),
            new Animal("b1", Type.FISH, Sex.M, 10, 50, 60, true),
            new Animal("c1", Type.DOG, Sex.F, 10, 20, 10, true),
            new Animal("d1", Type.FISH, Sex.F, 1, 1, 2, false)
        );
        var animals2 = List.of(
            new Animal("a2", Type.FISH, Sex.M, 10, 50, 70, true),
            new Animal("b2", Type.FISH, Sex.M, 10, 50, 60, true)
        );

        // Assert
        assertEquals("a2", task18(animals1, animals2).name());
    }

    @Test
    void task19Test() {
        // Arrange
        var animals = List.of(
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("b".repeat(50), Type.DOG, Sex.M, -10, 50, 60, true),
            new Animal("c", Type.DOG, Sex.F, 10, 20, 100, true),
            new Animal("", Type.FISH, Sex.F, 1000, 1000, 1000, false)
        );
        var expected = Map.of(
            "a", 1,
            "b".repeat(50), 2,
            "c", 0,
            "", 4
        );

        // Act
        var actual = task19(animals).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, o -> o.getValue().size()));

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void task20Test() {
        // Arrange
        var animals = List.of(
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("b".repeat(50), Type.DOG, Sex.M, -10, 50, 60, true),
            new Animal("c", Type.DOG, Sex.F, 10, 20, 10, true),
            new Animal("", Type.FISH, Sex.F, 1000, 1000, 1000, false)
        );
        var expected = Map.of(
            "a", new String[] {"Name is repeating"},
            "b".repeat(50), new String[] {
                "Illegal name: %s...".formatted("b".repeat(30)),
                "Illegal age: %d".formatted(-10)
            },
            "c", new String[0],
            "", new String[] {
                "Empty name", "Illegal age: %d".formatted(1000),
                "Illegal height: %d".formatted(1000),
                "Illegal weight: %d".formatted(1000)
            }
        );

        // Act
        var actual = task20(animals).entrySet().stream().allMatch(o1 -> Arrays.stream(expected.get(o1.getKey()))
            .allMatch(o2 -> o1.getValue().contains(o2)));

        // Assert
        assertTrue(actual);
    }
}
