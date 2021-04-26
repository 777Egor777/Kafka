package ru.yandex.cloud.kafka.train.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class for load properties
 * from resource-file
 * into Properties object.
 *
 * @author Geraskin Egor(geraskin@yandex-team.ru)
 * @since 25.04.2021
 */
public class PropertiesLoader {
    private final static Logger LOG = LoggerFactory.getLogger(PropertiesLoader.class.getName());

    public static Properties load(String resourceFileName) {
        Properties result = new Properties();
        try (InputStream in = PropertiesLoader.class.getResourceAsStream(resourceFileName)) {
            result.load(in);
        } catch (IOException e) {
            LOG.error("Exception when loading properties from resource file", e);
        }
        return result;
    }
}
