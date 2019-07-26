package ru.job4j.parsersql;

import ru.job4j.parsersql.Config;

import java.io.IOException;
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
         // workParser.generate(10);
            workParser.getPageLinks("http://www.sql.ru/forum/job-offers");
//            workParser.getPageLinks("http://www.mkyong.com");
            workParser.getVacancies();
            System.out.println("Ok!");
        }
}
