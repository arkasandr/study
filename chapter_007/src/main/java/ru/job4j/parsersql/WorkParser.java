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
    private static final String TABLE = "vacancy";
    private static final String DATABASE = "vacancies.db";

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


//    public void loadDB(Vacancy vacancy) {
//        String insertIntoSchema = "INSERT INTO " + TABLE
//                +
//                " (id, title, text, link, createDate)" + " VALUES (?,?,?,?,?)";
//        try (
//                PreparedStatement ps = connection.prepareStatement(insertIntoSchema)) {
//            ps.setString(1, vacancy.getId());
//            ps.setString(2, vacancy.getTitle());
//            ps.setString(3, vacancy.getText());
//            ps.setString(4, vacancy.getLink());
//            ps.setTimestamp(5, Timestamp.valueOf(vacancy.getCreateDate()));
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


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
        String createSchema = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
                + "vacancy_id INTEGER NOT NULL "
                + "vacancy_title VARCHAR(1000) "
                + "vacancy_link VARCHAR(1000) "
                + "vacancy_text VARCHAR(1000)"
                + "vacancy_date TIMESTAMP "

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
                        //                     System.out.println(URL);
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
                    if (vacancy.text().matches("^.*?(Java|java|JAVA).*$")) {
                        if (!vacancy.text().matches("^.*?(Script|script|SCRIPT).*$")) {
                           //getData(vacancy.attr("abs:href"));
                           // Vacancy vac = new Vacancy(getId(vacancy.attr("abs:href")), vacancy.text(),
                                    //vacancy.attr("abs:href"), getText(vacancy.attr("abs:href")), getData(vacancy.attr("abs:href")));
                            fillMap();
                             Vacancy vac = new Vacancy(null, null,
                            null, null, getData(vacancy.attr("abs:href")));
//                            String insertVacancy = "INSERT INTO " + TABLE + " (vacancy_id, vacancy_title, vacancy_link, vacancy_text, vacancy_date) VALUES" + "(?, ?, ?, ?, ?)";
//                            try (PreparedStatement ps = connection.prepareStatement(insertVacancy)) {
//                                ps.setString(1, va.getId());
//                                ps.setString(2, item.getName());
//                                ps.setString(3, item.getDescription());
//                                ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
//                                ps.executeUpdate();
//                            } catch (SQLException e) {
//                                e.printStackTrace();
//                            }
                           // System.out.println(vac);
                            vacancies.add(vac);


//                            System.out.println(vacancy.attr("abs:href"));
//                            System.out.println(vacancy.text());
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
    public String getId (String href) {
        String numberId = null;
       // Pattern p = Pattern.compile("\\+[0-9]{12}");
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
    public String getText (String URL) {
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

    /**
     * Метод определяет описание вакансии
     *
     * @param
     * @return String
     * @throws
     */

    public LocalDateTime getData(String URL) {
  //  public void getData(String URL) {
        String str = null;
        String str1 = null;
        String description = null;
        LocalTime localTime = null;
//        try {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements els = null;
        Element el = null;
        if (doc != null) {
            //el = doc.getElementsByClass("msgFooter").last();
            els = doc.getElementsByClass("msgFooter");
            el = els.get(els.size() - 1);

        }
        str = el.text().substring(0, el.text().indexOf("["));
       // System.out.println(str);
            String time = str.substring(str.indexOf(",") + 2);
            int hour = Integer.valueOf(time.split(":")[0].trim());
            int min = Integer.valueOf(time.split(":")[1].trim());
            localTime = LocalTime.of(hour, min);

            str1 = str.substring(0, str.indexOf(",")).trim();
            LocalDate localDate = LocalDate.now();

            if (str1.contains("вчера")) {
                localDate.minusDays(1);
            } else if (!str1.contains("сегодня") && !str1.contains("вчера")) {
                int year = Integer.valueOf("20" + str1.substring(str1.length() - 2));
                String strMonth = str1.substring(2, 6).trim();
                int day = Integer.valueOf(str1.substring(0, 2).trim());
                localDate = LocalDate.of(year, parseMonth(strMonth), day);
            }

        return LocalDateTime.of(localDate, localTime);
    }
//    public LocalDateTime getDate (String URL) {
//        String date = null;
//        LocalDateTime dateTime = null;
//        LocalDate localDate = LocalDate.now();
//        try {
//            Document doc = Jsoup.connect(URL).get();
//            Element table = doc.select("table").get(1); //select the first table.
//            Elements rows = table.select("tr");
//            Element row = table.select("tr").get(2);
//            Elements cols = row.select("td");
//            date = cols.get(0).text();
//            String newDate = date.substring(0, 9);
//            System.out.println(newDate);
//            int year = new Integer("20" + date.substring(date.length() - 2));
//            String strMonth = date.substring(2, 6).trim();
//            int day = new Integer(date.substring(0, 2).trim());
//            localDate = LocalDate.of(year, parseMonth(strMonth), day);
//
////            String [] arr = date.split("\\s+");
////            int n = 3; // NUMBER OF WORDS THAT YOU NEED
//            String nWords = null;
////
////            // concatenating number of words that you required
////            for(int i = 0; i < n ; i++){
////                nWords = nWords + " " + arr[i] ;
////                System.out.println(nWords);
////            }
////            Pattern pattern = Pattern.compile("\\d{2} \\d{3} \\d{2}");
////            Matcher matcher = pattern.matcher(date);
////            if (matcher.find()) {
////                System.out.println(matcher.group());
////            }
////            nWords = matcher.group();
//
//
//            Map<Long, String> map = new HashMap<>();
//            map.put(1L, "янв");
//            map.put(2L, "фев");
//            map.put(3L, "мар");
//            map.put(4L, "апр");
//            map.put(5L, "май");
//            map.put(6L, "июн");
//            map.put(7L, "июл");
//            map.put(8L, "авг");
//            map.put(9L, "сен");
//            map.put(10L, "окт");
//            map.put(11L, "ноя");
//            map.put(12L, "дек");
//            DateTimeFormatter fmt = new DateTimeFormatterBuilder()
//                    .appendPattern("dd ")
//                    .appendText(ChronoField.MONTH_OF_YEAR, map)
//                    .appendPattern(" yy")
//                    //.appendLiteral(",")
//                   // .appendPattern(" HH:mm")
//                    .toFormatter(new Locale("ru"));
//            dateTime = LocalDateTime.parse(newDate, fmt);
//
//            System.out.println(LocalDate.parse(date, fmt)); // 2018-09-12
//           // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm");
////            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yy, HH:mm").withLocale(Locale.forLanguageTag("ru"));
////            dateTime = LocalDateTime.parse(date, formatter);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        return dateTime;
//    }

    public void fillMap() {
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
