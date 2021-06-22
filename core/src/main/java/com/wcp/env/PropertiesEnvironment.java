package com.wcp.env;

import com.wcp.common.Closer;
import com.wcp.common.file.FileExtension;
import com.wcp.common.file.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesEnvironment implements Environment{

    private final Logger log = LoggerFactory.getLogger(PropertiesEnvironment.class);

    public Properties props = new Properties();
    private String basePath = "/src/main/resources/config";

    private static PropertiesEnvironment instance;

    private PropertiesEnvironment() {
        init();
    }

    public static PropertiesEnvironment getInstance(){
        if (instance == null) {
            synchronized (PropertiesEnvironment.class) {
                if (instance == null) {
                    instance = new PropertiesEnvironment();
                }
            }
        }
        return instance;
    }

    private void init() {
        File[] propFiles = fetchPropertiesFiles();

        FileInputStream is = null;
        for (File propFile : propFiles) {
            try {
                is = FileUtils.openInputStream(propFile);
                props.load(is);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } finally {
                Closer.close(is);
            }
        }
    }
    

    public void addProperties(Properties properties) {
        if (properties == null || properties.isEmpty()) { return; }
        log.trace("Adding Properties: {}", properties);
        props.putAll(properties);
    }

    private File[] fetchPropertiesFiles() {
        File propDir = new File(getClass().getResource("/config").getFile());
        return propDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String extension = FilenameUtils.getExtension(name);
                return FileExtension.PROPERTIES.equalsIgnoreValue(extension);
            }});
    }

    @Override
    public Properties getAllProperties() {
        return props;
    }

    @Override
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    @Override
    public int getInt(String key) {
        String value = props.getProperty(key);
        Integer intValue = Integer.valueOf(value);
        return intValue;
    }

    @Override
    public boolean getBoolean(String key) {
        String value = props.getProperty(key);
        Boolean boolValue = Boolean.valueOf(value);
        return boolValue;
    }

    @Override
    public byte getByte(String key) {
        String value = props.getProperty(key);
        byte byteValue = Byte.valueOf(value);
        return byteValue;
    }

    @Override
    public short getShort(String key) { return Short.valueOf(props.getProperty(key)); }

    @Override
    public long getLong(String key) {
        String value = props.getProperty(key);
        long longValue = Long.valueOf(value);
        return longValue;
    }

    @Override
    public float getFloat(String key) {
        String value = props.getProperty(key);
        float floatValue = Float.valueOf(value);
        return floatValue;
    }

    @Override
    public double getDouble(String key) {
        String value = props.getProperty(key);
        double doubleValue = Double.valueOf(value);
        return doubleValue;
    }
}
