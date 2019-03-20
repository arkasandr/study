package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WorkZip {

    public void pack(Args args) throws IOException {
        String zipName = args.output();
        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName));
        File parent = new File(args.directory());
        List<String> exclude = args.exts();
        this.addInZip(zout, parent, exclude);

    }

    private void addInZip(ZipOutputStream out, File parent, List<String> exts) throws IOException {
        File[] src = parent.listFiles();
        for(File file : src) {
            if (file.isDirectory()) {
                addInZip(out, file, exts);
                continue;
            } if (exclude(file, exts)) {
                FileInputStream input = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getPath()));
                byte[] buffer = new byte[1024];
                while (input.read(buffer) != -1) {
                    out.write(buffer);
                    out.closeEntry();
                    input.close();
                }
            }
        }
    }

    private boolean exclude(File file, List<String> exts) {
        boolean result = true;
        if (exts != null) {
            if (file.isFile()) {
                for(String ext : exts) {
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
