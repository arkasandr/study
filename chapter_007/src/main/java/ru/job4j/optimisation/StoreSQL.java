package ru.job4j.optimisation;

import ru.job4j.trackersql.TrackerSQL;

import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connection;
    private static final String TABLE = "entry";
    private static final String DATABASE = "items.db";

    public StoreSQL(Config config) {
        this.config = config;
        this.config.init();
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
        clearSchema();
        return this.connection != null;
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
                int field = rs.getInt("field");
                System.out.println("field =" + field);
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
                + "field INTEGER NOT NULL "
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

    /**
     * Метод генерирует и заносит в таблицу значения
     * @param
     * @return true
     * @throws SQLException
     */
    public void generate(int size) {
        String insertIntoSchema = "INSERT INTO " + TABLE +
                                  " (field)" + " VALUES (?)";
        try (
                PreparedStatement ps = connection.prepareStatement(insertIntoSchema)) {
            connection.setAutoCommit(false);
            for(int i = 1; i < size; i++) {
                ps.setInt(1, i);
                ps.addBatch();
                System.out.println(i);
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Config conf = new Config();
        StoreSQL st = new StoreSQL(conf);
        st.connectDB();
        st.generate(10);
        StoreXML str = new StoreXML(new File("/home/arkaleks/IdeaProjects/target.xml"));
        ConvertXSQT xsl = new ConvertXSQT();
                File target = new File("/home/arkaleks/IdeaProjects/target.xml");
                File dest = new File("/home/arkaleks/IdeaProjects/dest.xml");
                File schema = new File("/home/arkaleks/IdeaProjects/schema.xsl");
                //File schema = new File("schema.xsl");

        List<Field> en = st.getEntries();
        str.save(en);
        xsl.convert(target, dest, schema);
    }


    public List<Field> getEntries() {
        List<Field> result = new ArrayList<>();
        String getEntries = "SELECT * FROM " + TABLE;
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(getEntries)) {
            while (rs.next()) {
                Field f = new Field(rs.getInt("field"));
                int field = rs.getInt("field");
                System.out.println("field =" + field);
                result.add(f);
            }
            int size = result.size();
            System.out.println(size);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
