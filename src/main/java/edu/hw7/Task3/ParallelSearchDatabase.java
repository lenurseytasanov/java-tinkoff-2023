package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ParallelSearchDatabase implements PersonDatabase {

    private final List<Person> data = new ArrayList<>();

    @Override
    public synchronized void add(@NotNull Person person) {
        data.add(person);
    }

    @Override
    public synchronized void delete(int id) {
        data.removeIf(p -> p.id() == id);
    }

    @Override
    public List<Person> findByName(@NotNull String name) {
        return data.parallelStream()
            .filter(p -> p.name().equals(name))
            .toList();
    }

    @Override
    public List<Person> findByAddress(@NotNull String address) {
        return data.parallelStream()
            .filter(p -> p.address().equals(address))
            .toList();
    }

    @Override
    public List<Person> findByPhone(@NotNull String phone) {
        return data.parallelStream()
            .filter(p -> p.phoneNumber().equals(phone))
            .toList();
    }
}
