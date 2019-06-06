package ru.job4j.trackersql;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Predicate;

public class TrackerSQL implements ITracker, AutoCloseable {


    private Connection connection;
    private static final String TABLE = "items";
    private static final Random RN = new Random();

//    public TrackerSQL(Connection connection) {
//        this.connection = connection;
//    }

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
                Properties config = new Properties();
                config.load(in);
           // Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
//            if(checkDatabases()) {
                if (!checkSchema()) {
                    createSchema();
//                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }



    /**
     * Метод определяет, создана ли необходимая схема в базе данных
     * @param
     * @return true
     * @throws SQLException
     */
    private boolean checkSchema() {
        boolean result = false;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "%", null);
                while (rs.next()) {
                    if (rs.getString("TABLE_NAME").equals(TABLE)) {
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
    private boolean createSchema() {
        boolean result = false;
        String createSchema = "CREATE TABLE IF NOT EXISTS " + TABLE + " ("
                + "item_id VARCHAR(50) NOT NULL, "
                + "item_name VARCHAR(50) NOT NULL, "
                + "item_description VARCHAR(100) NOT NULL, "
                + "create_date TIMESTAMP "
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

//    /**
//     * Метод определяет, существует ли необходимая база данных
//     * @param
//     * @return
//     * @throws
//     */
//    private boolean checkDatabases() {
//        boolean result = false;
//        String updateDatabase = "SELECT datname FROM pg_database WHERE datistemplate = false;";
//        try {
//            PreparedStatement ps = connection.prepareStatement(updateDatabase);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                if(rs.getString(1).equals(DATABASE)) {
//                    System.out.println("DATABASE is exists!");
//                    result = true;
//                }
//            }
//            rs.close();
//            ps.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

//    /**
//     * Метод создает необходимую базу данных
//     * @param
//     * @return
//     * @throws
//     */
//    public boolean createDatabase() {
//        boolean result = false;
//        String createDatabase = "CREATE DATABASE " + DATABASE;
//
//        try (InputStream indb = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties");
//             PreparedStatement ps = connection.prepareStatement(createDatabase)) {
//            Properties configdb = new Properties();
//            configdb.load(indb);
//
//        this.connection = DriverManager.getConnection(
//                DATABASE,
//                configdb.getProperty("username"),
//                configdb.getProperty("password")
//        );
//            ps.executeUpdate();
//            result = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
//        return result;
//    }

    @Override
    public Item add(Item item) {
        item.setId(this.generateId(this.RN));
        String insertTableSQL = "INSERT INTO " + TABLE + " (item_id, item_name, item_description, create_date) VALUES" + "(?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertTableSQL)) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getDescription());
            ps.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void replace(String id, Item item) {
        String replaceItemSQL = "UPDATE " + TABLE + " SET item_name = ?, item_description = ?, create_date = ? WHERE item_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(replaceItemSQL)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String deleteItemSQL = "DELETE FROM items WHERE item_id = " + "(?)";
        try (PreparedStatement ps = connection.prepareStatement(deleteItemSQL)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        String selectTableSQL = "SELECT item_id, item_name, item_description, create_date FROM " + TABLE;
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(selectTableSQL)) {
            while (rs.next()) {
                Item item = new Item(rs.getString("item_name"), rs.getString("item_description"), rs.getTimestamp("create_date").getTime());
                result.add(item);
            }
            int size = result.size();
            System.out.println(size);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(Predicate<String> predicate) {
        Item item = null;
        List<Item> result = new ArrayList<>();
        String findByNameSQL = "SELECT * FROM " + TABLE;
        try (Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery(findByNameSQL)
        ) {
            while (rs.next()) {
                if (predicate.test(String.valueOf(rs.getString(2)))) {
                    item = new Item(rs.getString("item_name"), rs.getString("item_description"), rs.getTimestamp("create_date").getTime());
                    item.setId(String.valueOf(rs.getString(1)));
                    result.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(Predicate<String> predicate) {
        Item result = new Item();
        String findByIdSQL = "SELECT * FROM " + TABLE;
        try (Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery(findByIdSQL)
            ) {
            while (rs.next()) {
                if (predicate.test(String.valueOf(rs.getString(1)))) {
                    result = new Item(rs.getString("item_name"), rs.getString("item_description"), rs.getTimestamp("create_date").getTime());
                    result.setId(String.valueOf(rs.getString(1)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
            if (this.connection != null) {
                this.connection.close();
            }
        }
}
