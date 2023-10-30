package edu.hw4;


import edu.hw4.Animal.*;
import static edu.hw4.Main.*;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    void task1Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = List.of("Parrot", "Diana", "Lucy");
        assertEquals(expected, task1(animals).stream().map(Animal::name).toList());
    }

    @Test
    void task2Test() {
        var k = 2;
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = List.of("Lucy", "Parrot");
        assertEquals(expected, task2(animals, k).stream().map(Animal::name).toList());
    }

    @Test
    void task3Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = Map.of(Type.BIRD, 2, Type.CAT, 1);
        assertEquals(expected, task3(animals));

    }

    @Test
    void task4Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        assertEquals("Parrot", task4(animals).name());
    }

    @Test
    void task5Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        assertEquals(Sex.F, task5(animals));
    }

    @Test
    void task6Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        var expected = Map.of(
            Type.BIRD, animals.get(1), Type.CAT, animals.get(2));
        assertEquals(expected, task6(animals));
    }

    @Test
    void task7Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 4, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 5, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 5, 10, 10, true),
            new Animal("Max", Type.BIRD, Sex.M, 3, 4, 4, true),
            new Animal("Mark", Type.CAT, Sex.M, 5, 10, 10, true)
        );
        assertEquals("Parrot", task7(animals, 1).name());
        assertEquals("Mark", task7(animals, 3).name());
        assertEquals("Diana", task7(animals, 4).name());
    }

    @Test
    void task8Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        assertEquals("Parrot", task8(animals, 6).orElseThrow(NoSuchElementException::new).name());
        assertFalse(task8(animals, 2).isPresent());
    }

    @Test
    void task9Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        assertEquals(8, task9(animals));
    }

    @Test
    void task10Test() {
        var animals = List.of(
            new Animal("Diana", Type.BIRD, Sex.F, 1, 5, 2, false),
            new Animal("Parrot", Type.BIRD, Sex.M, 2, 4, 4, true),
            new Animal("Lucy", Type.CAT, Sex.F, 3, 10, 10, true)
        );
        assertEquals(List.of("Diana", "Lucy"), task10(animals).stream().map(Animal::name).toList());
    }

    @Test
    void task11Test() {
        var animals = List.of(
            new Animal("1", Type.DOG, Sex.F, 10, 120, 50, true),
            new Animal("2", Type.CAT, Sex.F, 10, 30, 10, true),
            new Animal("3", Type.DOG, Sex.F, 10, 110, 50, false)
        );
        assertEquals(List.of(animals.get(0)), task11(animals));
    }

    @Test
    void task12Test() {
        var animals = List.of(
            new Animal("1", Type.DOG, Sex.F, 10, 50, 60, true),
            new Animal("2", Type.CAT, Sex.F, 10, 20, 10, true),
            new Animal("3", Type.FISH, Sex.F, 1, 1, 2, false)
        );
        assertEquals(2, task12(animals));
    }

    @Test
    void task13Test() {
        var animals = List.of(
            new Animal("name", Type.DOG, Sex.F, 10, 50, 60, true),
            new Animal("name name", Type.CAT, Sex.F, 10, 20, 10, true),
            new Animal("name name name", Type.FISH, Sex.F, 1, 1, 2, false)
        );
        assertEquals(animals.subList(1, 3), task13(animals));
    }

    @Test
    void task14Test() {
        var animals = List.of(
            new Animal("name", Type.DOG, Sex.F, 10, 50, 60, true),
            new Animal("name name", Type.CAT, Sex.F, 10, 20, 10, true),
            new Animal("name name name", Type.FISH, Sex.F, 1, 1, 2, false)
        );
        assertTrue(task14(animals, 40));
    }

    @Test
    void task15Test() {
        var animals = List.of(
            new Animal("name", Type.DOG, Sex.F, 3, 50, 60, true),
            new Animal("name name", Type.CAT, Sex.F, 5, 20, 10, true),
            new Animal("name name name", Type.FISH, Sex.F, 7, 1, 2, false),
            new Animal("1", Type.DOG, Sex.F, 9, 120, 50, true),
            new Animal("2", Type.CAT, Sex.F, 11, 30, 10, true),
            new Animal("3", Type.DOG, Sex.F, 13, 110, 50, false)
        );
        assertEquals(62, task15(animals, 5, 10));
    }

    @Test
    void task16Test() {
        var animals = List.of(
            new Animal("d", Type.FISH, Sex.F, 1, 1, 2, false),
            new Animal("c", Type.DOG, Sex.F, 10, 20, 10, true),
            new Animal("b", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true)
        );
        assertEquals(List.of("a", "b", "c", "d"), task16(animals).stream().map(Animal::name).toList());
    }

    @Test
    void task17Test() {
        var animals = List.of(
            new Animal("a", Type.DOG, Sex.M, 10, 50, 60, true),
            new Animal("b", Type.DOG, Sex.M, 10, 50, 60, false),
            new Animal("c", Type.SPIDER, Sex.F, 10, 20, 10, true),
            new Animal("d", Type.SPIDER, Sex.F, 1, 1, 2, true)
        );
        assertTrue(task17(animals));
    }

    @Test
    void task18Test() {
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
        assertEquals("a2", task18(animals1, animals2).name());
    }

    @Test
    void task19Test() {
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
        assertEquals(expected, task19(animals).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, o -> o.getValue().size())));
    }

    @Test
    void task20Test() {
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
        assertTrue(task20(animals).entrySet().stream().allMatch(o1 -> Arrays.stream(expected.get(o1.getKey()))
            .allMatch(o2 -> o1.getValue().contains(o2))));
    }
}
