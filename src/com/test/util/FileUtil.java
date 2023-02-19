package com.test.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    public static String getSize(File file) {
        long filesize = file.length();
        if (filesize < 1024) {
            return filesize + " 字节";
        }
        if (filesize >= 1024 && filesize < 1024 * 1024) {
            return String.format("%.2f", ((double) filesize / 1024)) + " KB";
        }
        if (filesize >= 1024 * 1024 && filesize < 1024 * 1024 * 1024) {
            return String.format("%.2f", ((double) filesize / (1024 * 1024))) + " MB";
        }
        return String.format("%.2f", ((double) filesize / (1024 * 1024 * 1024))) + " GB";
    }

    public static String getDate(File file) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(file.lastModified());
        return simpleDateFormat.format(date);
    }
}
