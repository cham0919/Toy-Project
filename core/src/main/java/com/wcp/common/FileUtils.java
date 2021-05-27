package com.wcp.common;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public final class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {}



    public static File resourceDirToday(String prefix) {
        try {
            return new File(resourceDirPathToday(prefix)).getCanonicalFile();
        } catch (IOException e) {
            log.warn("Failed to resourceDirPathToday. {}", e.getLocalizedMessage());
        }
        return null;
    }

    public static String resourceDirPathToday(String prefix) {
        String path = resourceRootOf(prefix) + DateUtils.now("yyyy/MM/dd");
        File dir = new File(path);
        if (!dir.exists()) { dir.mkdirs(); }
        return path;
    }


    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        } else {
            int index = indexOfExtension(filename);
            return index == -1 ? "" : filename.substring(index + 1);
        }
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int extensionPos = filename.lastIndexOf(46);
            int lastSeparator = indexOfLastSeparator(filename);
            return lastSeparator > extensionPos ? -1 : extensionPos;
        }
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        } else {
            int lastUnixPos = filename.lastIndexOf(47);
            int lastWindowsPos = filename.lastIndexOf(92);
            return Math.max(lastUnixPos, lastWindowsPos);
        }
    }

    private static String resourceRootOf(String prefix) {
        return (StringUtils.isEmpty(prefix) ? "" : prefix + File.separator);
    }
}
