package ru.job4j.parsersql;

import java.sql.SQLException;

/**
     * @author Alex Arkashev (arkasandr@gmail.com)
     * @version $Id$
     * @since 0.1
     */

    public class MainParser {

        public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Config conf = new Config();
            WorkParser workParser = new WorkParser(conf);
            workParser.connectDB();
            workParser.getPageLinks("http://www.sql.ru/forum/job-offers");
            workParser.getVacancies();
            System.out.println("Ok!");
        }
}
