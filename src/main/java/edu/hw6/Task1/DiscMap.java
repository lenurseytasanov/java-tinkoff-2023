package edu.hw6.Task1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DiscMap implements Map<String, String> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final File file;

    private final Map<String, String> map;

    public DiscMap(@NotNull String path) {
        try {
            file = new File(path);
            if (!file.exists()) {
                Files.createFile(Paths.get(path));
            }
        } catch (IOException e) {
            LOGGER.error("", e);
            throw new RuntimeException(e);
        }
        map = new HashMap<>();
    }

    private void writeMap() {
        try (var bw = new BufferedWriter(new FileWriter(file))) {
            for (var entry : map.entrySet()) {
                bw.append(entry.getKey()).append(String.valueOf(':')).append(entry.getValue());
                bw.newLine();
            }
        } catch (IOException exception) {
            LOGGER.error("", exception);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        var result = map.put(key, value);
        writeMap();
        return result;
    }

    @Override
    public String remove(Object key) {
        var result = map.remove(key);
        writeMap();
        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        map.putAll(m);
        writeMap();
    }

    @Override
    public void clear() {
        map.clear();
        writeMap();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }
}
