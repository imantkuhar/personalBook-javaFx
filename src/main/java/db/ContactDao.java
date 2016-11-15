package db;

import model.Contact;

import java.io.File;
import java.io.FilenameFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactDao {

    private Connection connection = null;
    private Statement statement = null;
    private static ContactDao instance;

    static final String DB_URL = "jdbc:sqlite:/home/roma/Рабочий стол/contactDataBase.db";
    File filePath = new File("/home/roma/Рабочий стол/");

    public static ContactDao getInstance() {
        if (instance == null)
            instance = new ContactDao();
        return instance;
    }

    public ContactDao() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createContactTable() {
        try {
            statement = connection.createStatement();
            if (findDBByPath(filePath) != 1) {
                String sqlRequest = "CREATE TABLE CONTACT " +
                        "(id INTEGER IDENTITY, " +
                        " name VARCHAR(30), " +
                        " phoneNumber VARCHAR(10), " +
                        " address VARCHAR(15), " +
                        " groups VARCHAR(15), " +
                        " date NOT NULL DEFAULT NOW())";
                statement.executeUpdate(sqlRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addContact(Contact contact) {
        try {
            String name = contact.getName();
            String phoneNumber = contact.getPhoneNumber();
            String address = contact.getAddress();
            String group = contact.getGroup();
            statement.execute("INSERT INTO  'CONTACT' ('name', 'phoneNumber', 'address', 'groups') " +
                    "VALUES ('" + name + "''" + phoneNumber + "''" + address + "''" + group + "')");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }

    public void deleteContact(Contact contact, int contactId) {
        try {
            statement.execute("DELETE FROM CONTACT WHERE id = " + contactId + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllContacts() {
    }

    public void updateContact(Contact contact) {

    }

    public int findDBByPath(File filePath) {
        File[] matchingFiles = filePath.listFiles(new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.equals("contactDataBase.db");
            }
        });
        return matchingFiles.length;
    }
}
