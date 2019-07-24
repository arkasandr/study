package ru.job4j.parsersql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class WorkParser implements AutoCloseable {


    private final Config config;
    private Connection connection;
    private static final String TABLE = "vacancy";
    private static final String DATABASE = "vacancies.db";

    private HashSet<String> links;
    private List<List<String>> articles;

    public WorkParser(Config config) {
        this.config = config;
        this.config.init();
        links = new HashSet<>();
        articles = new ArrayList<>();
    }

    /**
     * Метод осущствляет подключение к базе данных
     * @param
     * @return
     * @throws
     */
    public boolean connectDB() throws ClassNotFoundException, SQLException {
        Class.forName(config.get("driver-class-name"));
        this.connection = DriverManager.getConnection(
                config.get("url"),
                config.get("username"),
                config.get("password")
        );
        checkDatabaseExist();
        if (!checkSchemaExist()) {
            System.out.println("Table doesn't exist");
            createSchema();
        }
        return this.connection != null;
    }


    public void generate(int size) {
        String insertIntoSchema = "INSERT INTO " + TABLE
                +
                " (vacancy_name)" + " VALUES (?)";
        try (
                PreparedStatement ps = connection.prepareStatement(insertIntoSchema)) {
            connection.setAutoCommit(false);
            for (int i = 1; i <= size; i++) {
                ps.setInt(1, i);
                ps.addBatch();
             //   showSchema();
             //   System.out.println(i);
            }
            ps.executeBatch();
            showSchema();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод определяет, существует ли необходимая база данных
     * @param
     * @return
     * @throws
     */
    private boolean checkDatabaseExist() {
        boolean result = false;
        File file = new File(DATABASE);
        if (file.exists()) {
            System.out.println("DATABASE is exists!");
            result = true;
        } else {
            System.out.println("You need create DATABASE!");
            createNewDatabase();
        }
        return result;
    }

    /**
     * Метод создает необходимую базу данных
     * @param
     * @return
     * @throws
     */
    private static void createNewDatabase() {
        String url = "jdbc:sqlite:" + DATABASE;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Метод проверяет наличие необходимой схемы в базе данных
     * @param
     * @return
     * @throws
     */
    private boolean checkSchemaExist() {
        boolean result = false;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "%", null);
            while (rs.next()) {
                if (rs.getString("TABLE_NAME").equals(TABLE)) {
                    System.out.println("Table exists!");
                    result = true;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод создает необходимую схему в базе данных
     * @param
     * @return true
     * @throws SQLException
     */
    private boolean clearSchema() {
        boolean result = false;
        String clearSchema = "DELETE FROM " + TABLE;
        try (
                PreparedStatement ps = connection.prepareStatement(clearSchema)) {
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод создает необходимую схему в базе данных
     * @param
     * @return true
     * @throws SQLException
     */
    private boolean showSchema() {
        boolean result = false;
        String showSchema = "SELECT * FROM " + TABLE;
        List<Integer> fields = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(showSchema)) {
            while (rs.next()) {
                int field = rs.getInt("vacancy_name");
                System.out.println("vacancy name =" + field);
                fields.add(field);
                result = true;
            }
            int size = fields.size();
            System.out.println(size);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод очищает необходимую схему в базе данных
     * @param
     * @return true
     * @throws SQLException
     */
    private boolean createSchema() {
        boolean result = false;
        String createSchema = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
                + "vacancy_name INTEGER NOT NULL "
                + ")";
        try (
                PreparedStatement ps = connection.prepareStatement(createSchema)) {
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    public void getPageLinks(String URL) {
        if (!links.contains(URL)) {
            try {
                Document document = Jsoup.connect(URL).get();
                Elements otherLinks = document.select("a[href^=\"https://www.sql.ru/forum/\"]");
                //      Elements otherLinks = document.select("java");
                //               Elements otherLinks = document.select("a[href]");
                for (Element page : otherLinks) {
                    if (links.add(URL)) {
//                otherLinks.stream().map((link) -> link.attr("abs:href")).forEachOrdered((this_url) -> {
//                            boolean add = links.add(this_url);
//                            if (add && this_url.contains(URL)) {
//                                System.out.println(this_url);
//                                getPageLinks(this_url);
//                            }
//                        });
                    //Remove the comment from the line below if you want to see it running on your editor
                    System.out.println(URL);
//                        System.out.println("Link: " + page.attr("href"));
//                        System.out.println("Text: " + page.text());
                }
                getPageLinks(page.attr("abs:href"));
            }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }


//    public void getPageLinks(String URL) {
//        if (!links.contains(URL)) {
//            try {
//                Document document = Jsoup.connect(URL).get();
//          //      Elements otherLinks = document.select("a[href^=\"http://www.mkyong.com/page/\"]");
//                Elements otherLinks = document.select("a[href]");
//
//                for (Element page : otherLinks) {
//                    if (links.add(URL)) {
//                        //Remove the comment from the line below if you want to see it running on your editor
//                        System.out.println(URL);
//                    }
//                    getPageLinks(page.attr("abs:href"));
//                    System.out.println(URL);
//                }
//            } catch (IOException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//    }



    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
