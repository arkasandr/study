package ru.job4j.parsersql;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.Month;

/**
 * @author Alex Arkashev (arkasandr@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class WorkParser implements AutoCloseable {


    private final Config config;
    private Connection connection;
    private static final String TABLE = "vacancies";
    private static final String DATABASE = "sqlvacancies.db";

    private HashSet<String> links;
   // private List<Vacancy> vacancies;
    private HashSet<Vacancy> vacancies;
    private final Map<String, Month> months = new HashMap<>();

    public WorkParser(Config config) {
        this.config = config;
        this.config.init();
        links = new HashSet<>();
        //vacancies = new ArrayList<>();
        vacancies = new HashSet<>();
    }


    /**
     * Метод осущствляет подключение к базе данных
     *
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


    public void loadDB(Vacancy vacancy) {
        String insertIntoSchema = "INSERT INTO " + TABLE
                +
                " (id, title, text, link, createDate)" + " VALUES (?,?,?,?,?)";
        try (
                PreparedStatement ps = connection.prepareStatement(insertIntoSchema)) {
            ps.setString(1, vacancy.getId());
            ps.setString(2, vacancy.getTitle());
            ps.setString(3, vacancy.getText());
            ps.setString(4, vacancy.getLink());
            ps.setTimestamp(5, Timestamp.valueOf(vacancy.getCreateDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Метод определяет, существует ли необходимая база данных
     *
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
     *
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
     *
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

//    /**
//     * Метод очищает необходимую схему в базе данных
//     * @param
//     * @return true
//     * @throws SQLException
//     */
//    private boolean clearSchema() {
//        boolean result = false;
//        String clearSchema = "DELETE FROM " + TABLE;
//        try (
//                PreparedStatement ps = connection.prepareStatement(clearSchema)) {
//            ps.executeUpdate();
//            result = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

//    /**
//     * Метод создает необходимую схему в базе данных
//     * @param
//     * @return true
//     * @throws SQLException
//     */
//    private boolean showSchema() {
//        boolean result = false;
//        String showSchema = "SELECT * FROM " + TABLE;
//        List<Integer> fields = new ArrayList<>();
//        try (Statement st = connection.createStatement();
//             ResultSet rs = st.executeQuery(showSchema)) {
//            while (rs.next()) {
//                int field = rs.getInt("vacancy_name");
//                System.out.println("vacancy name =" + field);
//                fields.add(field);
//                result = true;
//            }
//            int size = fields.size();
//            System.out.println(size);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    /**
     * Метод создает необходимую схему в базе данных
     *
     * @param
     * @return true
     * @throws SQLException
     */
    private boolean createSchema() {
        boolean result = false;
        String createSchema = "CREATE TABLE " + TABLE + " ("
                + "vacancy_id INTEGER NOT NULL, "
                + "vacancy_title VARCHAR(1000), "
                + "vacancy_link VARCHAR(1000), "
                + "vacancy_text VARCHAR(1000), "
                + "vacancy_date TIMESTAMP"

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
                Elements otherLinks = document.select("a[href^=\"https://www.sql.ru/forum/job-offers/1\"]");
                for (Element page : otherLinks) {
                    if (links.add(URL)) {
                                            // System.out.println(URL);
                    }
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    public void getVacancies() {
        links.forEach(x -> {
            try {
                Document document = Jsoup.connect(x).get();
                Elements vacancyElement = document.select("a[href^=\"https://www.sql.ru/forum/\"]");
                    for (Element vacancy : vacancyElement) {
                        if(vacancy != null) {
                            if (vacancy.text().matches("^.*?(Java|java|JAVA).*$")) {
                                 if (!vacancy.text().matches("^.*?(Script|script|SCRIPT).*$")) {
                                     if (!vacancy.attr("abs:href").contains("memberinfo")) {
                                         monthToMap();
                                         getUrlData(vacancy.attr("abs:href"));
                                         Vacancy vac = new Vacancy(getUrlId(vacancy.attr("abs:href")), vacancy.text(),
                                             vacancy.attr("abs:href"), getUrlText(vacancy.attr("abs:href")), getUrlData(vacancy.attr("abs:href")));
                            String insertVacancy = "INSERT INTO " + TABLE + " (vacancy_id, vacancy_title, vacancy_link, vacancy_text, vacancy_date) VALUES" + "(?, ?, ?, ?, ?)";
                            try (PreparedStatement ps = connection.prepareStatement(insertVacancy)) {
                                ps.setString(1, vac.getId());
                                ps.setString(2, vac.getTitle());
                                ps.setString(3, vac.getText());
                                ps.setString(4, vac.getLink());
                                ps.setTimestamp(5, Timestamp.valueOf(vac.getCreateDate()));
                                ps.executeUpdate();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                                          vacancies.add(vac);
                                     }
                                 }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            for(Vacancy v:vacancies) {
                System.out.println(v);
            }
        });
    }

    /**
     * Метод определяет id вакансии
     *
     * @param
     * @return String
     * @throws
     */
    public String getUrlId (String href) {
        String numberId = null;
        Pattern p = Pattern.compile("(\\d+)");
        Matcher m = p.matcher(href);
        if (m.find()) {
            numberId = m.group(0);
        }
        return numberId;
    }


    public Month parseMonth(String str) {
        return this.months.get(str);
    }

    /**
     * Метод определяет описание вакансии
     *
     * @param
     * @return String
     * @throws
     */
    public String getUrlText (String URL) {
        String description = null;
        try {
            Document doc = Jsoup.connect(URL).get();
            Element table = doc.select("table").get(1); //select the first table.
            Elements rows = table.select("tr");
            Element row = table.select("tr").get(1);
            Elements cols = row.select("td");
            description = cols.get(1).text();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return description;
    }

    public LocalDateTime getUrlData (String URL) {
        String str = null;
        String str1 = null;
        String description = null;
        LocalTime timeCreate = null;
        LocalDate ld = LocalDate.now();

        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements table = null; //select the first table.
        if (doc != null) {
            table = doc.getElementsByClass("msgFooter");
        }
        if(table.size() != 0) {
            Element el = table.last();
           if (el.children().size() > 0) {
                str = el.text().substring(0, el.text().indexOf("["));
               String time = str.substring(str.indexOf(",") + 2);
                int timeHour = Integer.valueOf(time.split(":")[0].trim());
                int timeMinute = Integer.valueOf(time.split(":")[1].trim());
                timeCreate = LocalTime.of(timeHour, timeMinute);
                str1 = str.substring(0, str.indexOf(",")).trim();
                if (str1.contains("вчера")) {
                    ld.minusDays(1);
                } else if (!str1.contains("сегодня") && !str1.contains("вчера")) {
                    int timeYear = Integer.valueOf("20" + str1.substring(str1.length() - 2));
                    String timeMonth = str1.substring(2, 6).trim();
                    int timeDay = Integer.valueOf(str1.substring(0, 2).trim());
                    ld = LocalDate.of(timeYear, parseMonth(timeMonth), timeDay);
                }
            }
        }


        return LocalDateTime.of(ld, timeCreate);
    }


    public void monthToMap() {
        this.months.put("янв", Month.JANUARY);
        this.months.put("фев", Month.FEBRUARY);
        this.months.put("мар", Month.MARCH);
        this.months.put("апр", Month.APRIL);
        this.months.put("май", Month.MAY);
        this.months.put("июн", Month.JUNE);
        this.months.put("июл", Month.JULY);
        this.months.put("авг", Month.AUGUST);
        this.months.put("сен", Month.SEPTEMBER);
        this.months.put("окт", Month.OCTOBER);
        this.months.put("ноя", Month.NOVEMBER);
        this.months.put("дек", Month.DECEMBER);
    }



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
