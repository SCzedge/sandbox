package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Zip4jTest {
    private static DateTimeFormatter dateTimeFormatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ZipFile getZippedLogs(String targetDir) {
        try {
            File dir = new File(targetDir);

            if (!dir.isDirectory()) {
                return null;
            }
            if (!dir.canRead()) {
                return null;
            }

            String zipFileName = LocalDateTime.now().format(dateTimeFormatPattern) + ".zip";

//        List<File> fileList = Arrays.asList(dir.listFiles());
            List<File> fileList = Arrays.asList(dir.listFiles());

            if (fileList.isEmpty()) {
                return null;
            }
            log.info(fileList.toString());

            ZipFile zip = new ZipFile(zipFileName);

            zip.addFiles(fileList);

            log.info(Arrays.deepToString(zip.getFile().listFiles()));
            return zip;
        } catch (ZipException e) {
            e.printStackTrace();
            return null;
        }
    }
}
