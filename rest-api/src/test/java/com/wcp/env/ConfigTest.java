package com.wcp.env;

import com.wcp.common.file.FileExtension;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

public class ConfigTest{


    String basePath = "src/main/resources/config";

    @Test
    public void checkPropertiesList(){
        File propDir = new File(basePath);
        File[] files = propDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String extension = FilenameUtils.getExtension(name);
                return FileExtension.PROPERTIES.equalsIgnoreValue(extension);
            }});

        System.out.println(Arrays.toString(files));
    }

    @Test
    public void t(){
        System.out.println(Config.getProperty("test.key"));
    }

}




