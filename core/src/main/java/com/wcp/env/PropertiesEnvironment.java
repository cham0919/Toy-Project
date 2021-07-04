package com.wcp.env;

import com.wcp.common.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesEnvironment implements Environment{

    private final Logger log = LoggerFactory.getLogger(PropertiesEnvironment.class);

    public Properties props = new Properties();
    private final String config = "/config";


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
        Arrays.stream(propFiles)
                .forEach(this::loadProp);
    }

    private void loadProp(File propFile){
        try (
            FileInputStream is = FileUtils.openInputStream(propFile);
        ){
            props.load(is);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private File[] fetchPropertiesFiles() {
        File propDir = new File(getClass().getResource(config).getFile());
        return propDir.listFiles(FileUtils::isPropertiesFile);
    }


    public void addProperties(Properties properties) {
        if (properties == null || properties.isEmpty()) { return; }
        log.trace("Adding Properties: {}", properties);
        props.putAll(properties);
    }


    @Override
    public Properties getAllProperties() {
        return props;
    }

}
