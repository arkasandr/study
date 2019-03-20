package ru.job4j.io;


import java.io.IOException;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class MainZip {

    public static void main(String[] args) throws IOException {
        WorkZip workZip = new WorkZip();
        workZip.pack(new Args(args));

    }

}
