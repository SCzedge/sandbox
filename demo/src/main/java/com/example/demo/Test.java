package com.example.demo;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class Test {


    public void test() {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Map<Integer, String> map1 = Map.of(
                1, "test1",
                2, "test2",
                3, "test3",
                4, "test4"
        );

        set.forEach((K) -> {
        });
    }

    public void fileTest(String targetDir) {

        File dir = new File(targetDir);

        File[] files = dir.listFiles();

        try (ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(targetDir + "2020" + ".zip"));) {

            for (File file : files) {

                try (FileInputStream fis = new FileInputStream(file)) {
                    zouts.putNextEntry(new ZipEntry(file.getName()));

                    int len;
                    byte[] buffer = new byte[1024];

                    while ((len = fis.read(buffer)) > 0) {
                        zouts.write(buffer, 0, len);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    zouts.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("isDirectory : {}", dir.isDirectory());
        log.info("list : {}", Arrays.deepToString(dir.list()));
    }


}
