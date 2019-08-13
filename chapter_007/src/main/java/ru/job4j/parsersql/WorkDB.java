//package ru.job4j.parsersql;
//
//import java.io.File;
//import java.sql.*;
//
//public class WorkDB implements AutoCloseable{
//
//    private final Config config;
//    private Connection connection;
//    private static final String TABLE = "vacancy";
//    private static final String DATABASE = "vacancies.db";
//
//
//
//    public WorkDB(Config config) {
//        this.config = config;
//        this.config.init();
//    }
//
//    /**
//     * Метод осущствляет подключение к базе данных
//     *
//     * @param
//     * @return
//     * @throws
//     */
//    public boolean connectDB() throws ClassNotFoundException, SQLException {
//        Class.forName(config.get("driver-class-name"));
//        this.connection = DriverManager.getConnection(
//                config.get("url"),
//                config.get("username"),
//                config.get("password")
//        );
//        checkDatabaseExist();
//        if (!checkSchemaExist()) {
//            System.out.println("Table doesn't exist");
//            createSchema();
//        }
//        return this.connection != null;
//    }
//
//
//    public void loadDB(Vacancy vacancy) {
//        String insertIntoSchema = "INSERT INTO " + TABLE
//                +
//                " (id, title, text, link, createDate)" + " VALUES (?,?,?,?,?)";
//        try (
//                PreparedStatement ps = connection.prepareStatement(insertIntoSchema)) {
//            ps.setInt(1, vacancy.getId());
//            ps.setString(2, vacancy.getTitle());
//            ps.setString(3, vacancy.getText());
//            ps.setString(4, vacancy.getLink());
//            ps.setTimestamp(5, Timestamp.valueOf(vacancy.getCreateDate()));
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * Метод определяет, существует ли необходимая база данных
//     *
//     * @param
//     * @return
//     * @throws
//     */
//    private boolean checkDatabaseExist() {
//        boolean result = false;
//        File file = new File(DATABASE);
//        if (file.exists()) {
//            System.out.println("DATABASE is exists!");
//            result = true;
//        } else {
//            System.out.println("You need create DATABASE!");
//            createNewDatabase();
//        }
//        return result;
//    }
//
//    /**
//     * Метод создает необходимую базу данных
//     *
//     * @param
//     * @return
//     * @throws
//     */
//    private static void createNewDatabase() {
//        String url = "jdbc:sqlite:" + DATABASE;
//        try (Connection conn = DriverManager.getConnection(url)) {
//            if (conn != null) {
//                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("The driver name is " + meta.getDriverName());
//                System.out.println("A new database has been created.");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    /**
//     * Метод проверяет наличие необходимой схемы в базе данных
//     *
//     * @param
//     * @return
//     * @throws
//     */
//    private boolean checkSchemaExist() {
//        boolean result = false;
//        try {
//            DatabaseMetaData metaData = connection.getMetaData();
//            ResultSet rs = metaData.getTables(null, null, "%", null);
//            while (rs.next()) {
//                if (rs.getString("TABLE_NAME").equals(TABLE)) {
//                    System.out.println("Table exists!");
//                    result = true;
//                }
//            }
//            rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
////    /**
////     * Метод очищает необходимую схему в базе данных
////     * @param
////     * @return true
////     * @throws SQLException
////     */
////    private boolean clearSchema() {
////        boolean result = false;
////        String clearSchema = "DELETE FROM " + TABLE;
////        try (
////                PreparedStatement ps = connection.prepareStatement(clearSchema)) {
////            ps.executeUpdate();
////            result = true;
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return result;
////    }
//
////    /**
////     * Метод создает необходимую схему в базе данных
////     * @param
////     * @return true
////     * @throws SQLException
////     */
////    private boolean showSchema() {
////        boolean result = false;
////        String showSchema = "SELECT * FROM " + TABLE;
////        List<Integer> fields = new ArrayList<>();
////        try (Statement st = connection.createStatement();
////             ResultSet rs = st.executeQuery(showSchema)) {
////            while (rs.next()) {
////                int field = rs.getInt("vacancy_name");
////                System.out.println("vacancy name =" + field);
////                fields.add(field);
////                result = true;
////            }
////            int size = fields.size();
////            System.out.println(size);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return result;
////    }
//
//    /**
//     * Метод создает необходимую схему в базе данных
//     *
//     * @param
//     * @return true
//     * @throws SQLException
//     */
//    private boolean createSchema() {
//        boolean result = false;
//        String createSchema = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
//                + "vacancy_name INTEGER NOT NULL "
//                + ")";
//        try (
//                PreparedStatement ps = connection.prepareStatement(createSchema)) {
//            ps.executeUpdate();
//            result = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public void close() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
