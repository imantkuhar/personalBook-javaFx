package db;

import model.Contact;
import utils.PropertiesHolder;

import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactDao {

    private Connection connection = null;
    private Statement statement = null;
    private static ContactDao instance;

    static final String DB_URL = "jdbc:sqlite:/home/roma/Рабочий стол/contactDataBase.db";

    public static ContactDao getInstance() {
        if (instance == null)
            instance = new ContactDao();
        return instance;
    }

    private ContactDao() {
        createConnection();
        createContactTable();
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
            String sqlRequest = "CREATE TABLE IF NOT EXISTS CONTACT " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name VARCHAR(30), " +
                    " phoneNumber VARCHAR(10), " +
                    " address VARCHAR(15), " +
                    " groups VARCHAR(15), " +
                    " date VARCHAR (25))";
            statement.executeUpdate(sqlRequest);
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

            String DATE_TIME_FORMAT = PropertiesHolder.getProperty("DATE_TIME_FORMAT");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            LocalDateTime dateTime = LocalDateTime.now();
            String formattedDateTime = dateTime.format(formatter);

            statement.execute("INSERT INTO  'CONTACT' ('name', 'phoneNumber', 'address', 'groups', 'date') " +
                    "VALUES ('" + name + "','" + phoneNumber + "','" + address + "','" + group + "','" + formattedDateTime + "')");

            String getContactId = "SELECT id FROM CONTACT ORDER BY id DESC LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(getContactId);
            String contactId = resultSet.getString("id");
            contact.setId(Integer.parseInt(contactId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(Contact contact) {
        int id = contact.getId();
        System.out.println(id);
        try {
            statement.execute("DELETE FROM CONTACT WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> getAllContacts() throws SQLException {
        Statement statement = null;
        String query = "SELECT * FROM CONTACT;";
        List contactList = new ArrayList<Contact>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String groups = resultSet.getString("groups");

                Contact contact = new Contact();
                contact.setId(id); contact.setName(name); contact.setPhoneNumber(phoneNumber); contact.setAddress(address); contact.setGroup(groups);

                try {
                    String DATE_TIME_FORMAT = PropertiesHolder.getProperty("DATE_TIME_FORMAT");
                    DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
                    String dateFromDB = resultSet.getString("date");
                    Date formattedDate = dateFormat.parse(dateFromDB);
                    contact.setDate(formattedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                System.out.println(contact.toString());
                contactList.add(contact);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return contactList;
    }

    public void updateContact(Contact contact) {
    }
}
