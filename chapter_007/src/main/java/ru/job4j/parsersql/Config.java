package ru.job4j.parsersql;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = ru.job4j.parsersql.Config.class.getClassLoader().getResourceAsStream("parserprop/app.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
