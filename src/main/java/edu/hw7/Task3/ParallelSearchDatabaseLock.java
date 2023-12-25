package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.NotNull;

public class ParallelSearchDatabaseLock implements PersonDatabase {

    private final List<Person> data = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @Override
    public void add(@NotNull Person person) {
        writeLock.lock();
        try {
            data.add(person);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void delete(int id) {
        writeLock.lock();
        try {
            data.removeIf(p -> p.id() == id);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<Person> findByName(@NotNull String name) {
        readLock.lock();
        try {
            return data.parallelStream()
                .filter(p -> p.name().equals(name))
                .toList();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByAddress(@NotNull String address) {
        readLock.lock();
        try {
            return data.parallelStream()
                .filter(p -> p.address().equals(address))
                .toList();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByPhone(@NotNull String phone) {
        readLock.lock();
        try {
            return data.parallelStream()
                .filter(p -> p.phoneNumber().equals(phone))
                .toList();
        } finally {
            readLock.unlock();
        }
    }
}
