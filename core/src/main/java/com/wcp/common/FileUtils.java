package com.wcp.common;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {}


    public static String readFileToString(File file) {
        return readFileToString(file, Charset.defaultCharset());
    }

    public static String readFileToString(File file, Charset encoding) {

        FileInputStream in = null;
        in = openInputStream(file);
        try {
            return org.apache.commons.io.IOUtils.toString(in, Charsets.toCharset(encoding));
        } catch (Throwable t) {
            log.error("Failed to read file.", t);
            return null;
        } finally {
            Closer.close(in);
        }
    }


    /**
     * 대상 파일을 FileInputStream으로 반환한다.<br/>
     *
     * @param file
     *            대상 파일
     * @return 대상 파일에 대한 FileInputStream
     */
    public static FileInputStream openInputStream(File file) {

        if (file.exists()) {

            if (file.isDirectory()) {
                log.error("FileUtils-openInputStream ERROR: 이 존재하나 디렉토리입니다.",file, FileUtils.class);
                return null;
            }

            if (!file.canRead()) {
                log.error("FileUtils-openInputStream ERROR: {}은 읽을수 없습니다.",file,  FileUtils.class);
                return null;
            }

        } else {
            log.error("FileUtils-openInputStream ERROR:  {} 이 존재하지 않습니다." , file , FileUtils.class);
            return null;
        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
        } catch (Exception e) {
            log.error("FileUtils-openInputStream ERROR: {}", e.getMessage(), FileUtils.class);
        }

        return fis;
    }

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


    public static boolean unZip(File zip) {
        File dir = new File(zip.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        ZipInputStream zis = null;
        ZipEntry ze = null;
        List<String> fileList = new ArrayList<>();
        try {
            fis = new FileInputStream(zip);
            zis = new ZipInputStream(fis);
            ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(dir + File.separator + fileName);
                fileList.add(fileName);
                if (ze.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (Throwable t) {
            log.error("error",t);
            return false;
        }finally {
            if (fis != null) {
                try { fis.close(); } catch (Throwable t){log.error("error",t); return false;}
            }
        }
        return true;
    }
}
