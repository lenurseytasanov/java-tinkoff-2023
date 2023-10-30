package edu.hw4;

import edu.hw4.Animal.Sex;
import edu.hw4.Animal.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class Main {
    private Main() { }

    public static List<Animal> task1(@NotNull List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> task2(@NotNull List<Animal> animals, @NotNull Integer k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k).toList();
    }

    public static Map<Type, Integer> task3(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, o -> o.getValue().intValue()));
    }

    public static Animal task4(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(o -> o.name().length()))
            .orElseThrow(NoSuchElementException::new);
    }

    public static Sex task5(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue))
            .orElseThrow(NoSuchElementException::new).getKey();
    }

    public static Map<Type, Animal> task6(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type))
            .entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, o -> o.getValue()
                .stream().max(Comparator.comparingInt(Animal::weight))
                .orElseThrow(NoSuchElementException::new)));
    }

    public static Animal task7(@NotNull List<Animal> animals, @NotNull Integer k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::age).reversed().thenComparing(animals::indexOf))
            .toList().get(k - 1);
    }

    public static Optional<Animal> task8(@NotNull List<Animal> animals, @NotNull Integer k) {
        return animals.stream()
            .filter(o -> o.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9(@NotNull List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> task10(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(o -> o.age() != o.paws())
            .toList();
    }

    private final static int HEIGHT_LIMIT = 100;

    public static List<Animal> task11(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(Animal::bites)
            .filter(o -> o.height() > HEIGHT_LIMIT)
            .toList();
    }

    public static Integer task12(@NotNull List<Animal> animals) {
        return Long.valueOf(animals.stream()
            .filter(o -> o.weight() > o.height())
            .count()).intValue();
    }

    public static List<Animal> task13(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(o -> o.name().split(" ").length >= 2)
            .toList();
    }

    public static Boolean task14(@NotNull List<Animal> animals, @NotNull Integer k) {
        return animals.stream()
            .anyMatch(o -> o.type().equals(Type.DOG) && o.height() > k);
    }

    public static Integer task15(@NotNull List<Animal> animals, @NotNull Integer k, @NotNull Integer l) {
        return animals.stream()
            .filter(o -> o.age() >= k && o.age() <= l)
            .mapToInt(Animal::weight).sum();
    }

    public static List<Animal> task16(@NotNull List<Animal> animals) {
        Comparator<Animal> comparator = Comparator.comparingInt(o -> o.type().ordinal());
        comparator = comparator.thenComparingInt(o -> o.sex().ordinal());
        comparator = comparator.thenComparing(Animal::name);
        return animals.stream()
            .sorted(comparator)
            .toList();
    }

    public static Boolean task17(@NotNull List<Animal> animals) {
        var spiders = animals.stream()
            .filter(o -> o.type().equals(Type.SPIDER))
            .count();
        var dogs = animals.stream()
            .filter(o -> o.type().equals(Type.DOG))
            .count();
        if (spiders == 0 || dogs == 0) {
            return false;
        }
        return (double) animals.stream()
            .filter(o -> o.type().equals(Type.SPIDER) && o.bites())
            .count() / spiders > (double) animals.stream()
            .filter(o -> o.type().equals(Type.DOG) && o.bites())
            .count() / dogs;
    }

    @SafeVarargs public static Animal task18(@NotNull List<Animal>... animals) {
        return Arrays.stream(animals).flatMap(Collection::stream)
            .filter(o -> o.type().equals(Type.FISH))
            .max(Comparator.comparingInt(Animal::weight))
            .orElseThrow(NoSuchElementException::new);
    }

    private final static int NAME_LENGTH_CONSTRAINT = 30;
    private final static int AGE_UPPER_BOUND = 20;
    private final static int HEIGHT_UPPER_BOUND = 150;
    private final static int WEIGHT_UPPER_BOUND = 150;

    public static Map<String, Set<ValidationError>> task19(@NotNull List<Animal> animals) {
        Map<String, Set<ValidationError>> result = new HashMap<>();
        for (var animal : animals) {
            if (result.containsKey(animal.name())) {
                result.get(animal.name()).add(new ValidationError("Name is repeating "));
                continue;
            }
            result.put(animal.name(), new HashSet<>());
            if (animal.name().length() > NAME_LENGTH_CONSTRAINT) {
                result.get(animal.name()).add(new ValidationError("Illegal name: %s... "
                    .formatted(animal.name().substring(0, NAME_LENGTH_CONSTRAINT))));
            } else if (animal.name().isEmpty()) {
                result.get(animal.name()).add(new ValidationError("Empty name "));
            }
            if (animal.age() > AGE_UPPER_BOUND || animal.age() < 0) {
                result.get(animal.name()).add(new ValidationError("Illegal age: %d ".formatted(animal.age())));
            }
            if (animal.height() > HEIGHT_UPPER_BOUND || animal.height() < 0) {
                result.get(animal.name()).add(new ValidationError("Illegal height: %d ".formatted(animal.height())));
            }
            if (animal.weight() > WEIGHT_UPPER_BOUND || animal.weight() < 0) {
                result.get(animal.name()).add(new ValidationError("Illegal weight: %d ".formatted(animal.weight())));
            }
        }
        return result;
    }

    public static Map<String, String> task20(@NotNull List<Animal> animals) {
        return task19(animals).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, o -> o.getValue().stream()
                .map(ValidationError::getMessage)
                .collect(Collectors.joining(" "))));
    }
}
