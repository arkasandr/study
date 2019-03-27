package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class WorkZip {

    /**
     * Метод, запускающий архивацию.
     *
     * @return
     */
    public void pack(Args args) throws IOException {
        String zipName = args.output();
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName));
        String parentpath = args.directory();
        File par = new File(parentpath);
        List<String> exclude = args.exts();
        this.addInZip(zout, par, exclude);

    }

    /**
     * Метод, выполняющий основные этапы архивации (определение списка файлов для
     * архивации, с учетом исключенных расширений, и сжатие этих файлов.
     *
     * @return
     */
    private void addInZip(ZipOutputStream out, File parent, List<String> exts) throws IOException {
        Queue<File> data = new LinkedList<>();
        Collections.addAll(data, parent.listFiles());
        byte[] buffer = new byte[1024];
        try {
            while (!data.isEmpty()) {
                File file = data.remove();
                if (file.isDirectory()) {
                    Collections.addAll(data, file.listFiles());
                } else {
                    for (String str : exts) {
                        if (!file.getName().endsWith(str)) {
                            System.out.println("File Added : " + file.getName());
                            ZipEntry ze = new ZipEntry(file.getPath());
                            out.putNextEntry(ze);
                            FileInputStream input = new FileInputStream(file);
                            int len;
                            while ((len = input.read(buffer)) > 0) {
                                out.write(buffer, 0, len);
                            }
                            input.close();
                        }
                    }
                }
            }
                    out.closeEntry();
                    out.close();
                    System.out.println("Done");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
}

