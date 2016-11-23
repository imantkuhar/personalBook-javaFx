package db;

import model.Contact;
import utils.PropertiesHolder;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactDao {

    private Connection connection = null;
    private Statement statement = null;
    private static ContactDao instance;

    static String DB_URL = PropertiesHolder.getProperty("DB_URL");

    public static ContactDao getInstance() {
        if (instance == null)
            instance = new ContactDao();
        return instance;
    }

    private ContactDao() {
        try {
            createConnection();
            createContactTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void createContactTable() throws SQLException {
        try {
            statement = connection.createStatement();
            String sqlRequest = "CREATE TABLE IF NOT EXISTS CONTACT " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name VARCHAR(30), " +
                    " phoneNumber VARCHAR(10), " +
                    " address VARCHAR(15), " +
                    " groups VARCHAR(15), " +
                    " date VARCHAR (25))";
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void addContact(Contact contact) throws SQLException {
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        String address = contact.getAddress();
        String group = contact.getGroup();

        String DATE_TIME_FORMAT = PropertiesHolder.getProperty("DATE_TIME_FORMAT");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter);
        try {
            statement = connection.createStatement();
            statement.execute("INSERT INTO  'CONTACT' ('name', 'phoneNumber', 'address', 'groups', 'date') " +
                    "VALUES ('" + name + "','" + phoneNumber + "','" + address + "','" + group + "','" + formattedDateTime + "')");

            String getContactId = "SELECT id FROM CONTACT ORDER BY id DESC LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(getContactId);
            String contactId = resultSet.getString("id");
            contact.setId(Integer.parseInt(contactId));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void deleteContact(int id) throws SQLException {
        try {
            statement = connection.createStatement();
            statement.execute("DELETE FROM CONTACT WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public List<Contact> getAllContacts() {
        String request = "SELECT * FROM CONTACT;";
        List contactList = new ArrayList<Contact>();
        try {
            contactList = convertRequestToContactList(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public void updateContact(Contact contact) throws SQLException {
        statement = connection.createStatement();
        int id = contact.getId();
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        String address = contact.getAddress();
        String group = contact.getGroup();
        try {
            statement.execute("UPDATE CONTACT SET " +
                    "name = '" + name + "', phoneNumber = '" + phoneNumber + "', address = '" + address + "', groups = '" + group + "' " +
                    "WHERE id = '" + id + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public List<Contact> getAllContactByName(String stringForSearch) {
        String fullStringForSearch = "%" + stringForSearch + "%";
        String request = "SELECT * FROM 'CONTACT' WHERE name LIKE'" + fullStringForSearch + "';";
        List<Contact> contactListByName = null;
        try {
            contactListByName = convertRequestToContactList(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactListByName;
    }


    public List<Contact> getAllContactByPhoneNumber(String stringForSearch) {
        String fullStringForSearch = "%" + stringForSearch + "%";
        String request = "SELECT * FROM 'CONTACT' WHERE phoneNumber LIKE'" + fullStringForSearch + "';";
        List<Contact> contactListByPhoneNumber = null;
        try {
            contactListByPhoneNumber = convertRequestToContactList(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactListByPhoneNumber;
    }

    public List<Contact> getAllContactByString(String stringForSearch) {
        String fullStringForSearch = "%" + stringForSearch + "%";
        String request = "SELECT * FROM 'CONTACT' WHERE name LIKE'" + fullStringForSearch +
                "' OR phoneNumber LIKE'" + fullStringForSearch +
                "' OR address LIKE'" + fullStringForSearch +
                "' OR groups LIKE'" + fullStringForSearch + "';";
        List<Contact> contactListByPhoneNumber = null;
        try {
            contactListByPhoneNumber = convertRequestToContactList(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactListByPhoneNumber;
    }

    public List<Contact> convertRequestToContactList(String request) throws SQLException {
        List<Contact> contactList = new ArrayList<>();

        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(request);
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String group = resultSet.getString("groups");

                Contact contact = new Contact();
                contact.setId(id);
                contact.setName(name);
                contact.setPhoneNumber(phoneNumber);
                contact.setAddress(address);
                contact.setGroup(group);

                try {
                    String DATE_TIME_FORMAT = PropertiesHolder.getProperty("DATE_TIME_FORMAT");
                    DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
                    String dateFromDB = resultSet.getString("date");
                    Date formattedDate = dateFormat.parse(dateFromDB);
                    contact.setDate(formattedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                contactList.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return contactList;
    }
}
