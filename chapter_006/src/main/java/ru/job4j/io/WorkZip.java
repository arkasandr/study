package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WorkZip {

    public void pack(Args args) throws IOException {
        String zipName = args.output();
        System.out.println(zipName);
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName));
        String parentpath = args.directory();
        File par = new File(parentpath);
        System.out.println("dir= " + par.getName());
        List<String> exclude = args.exts();
        for (String str : exclude) {
            System.out.println(str);
        }
        this.addInZip(zout, par, exclude);

    }

    private void addInZip(ZipOutputStream out, File parent, List<String> exts) throws IOException {
        Queue<File> data = new LinkedList<>();
        Collections.addAll(data, parent.listFiles());
        try {
//        File[] src = parent.listFiles();
        for (File fl : data) {
            System.out.println(fl.getName());
        }
        for (File file : data) {
            if (file.isDirectory()) {
                addInZip(out, file, exts);
                continue;
            }

            if (exclude(file, exts)) {

                byte[] buffer = new byte[1024];
                System.out.println("File Added : " + file.getName());
                ZipEntry ze = new ZipEntry(file.getPath());
                out.putNextEntry(ze);

                FileInputStream input = new FileInputStream(File.separator + file);

                //out.putNextEntry(new ZipEntry(file.getPath()));
                int len;
                while ((len = input.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }

//                while (input.read(buffer) != -1) {
//                    out.write(buffer);
//                    out.closeEntry();
                input.close();

            }
        }
            out.closeEntry();
            out.close();

            System.out.println("Done");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    private boolean exclude(File file, List<String> exts) {
        boolean result = true;
        if (exts != null) {
            if (file.isFile()) {
                for (String ext : exts) {
                    if (file.getName().endsWith(ext)) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
