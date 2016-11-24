package db;

import model.Contact;
import utils.PropertiesHolder;
import utils.converter.ResultSetConverter;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void createContactTable(){
        String sqlRequest = "CREATE TABLE IF NOT EXISTS CONTACT " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name VARCHAR(30), " +
                " phoneNumber VARCHAR(10), " +
                " address VARCHAR(15), " +
                " groups VARCHAR(15), " +
                " date VARCHAR (25))";
        try {
            statement = connection.createStatement();
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addContact(Contact contact){
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

            String getContactIdResuest = "SELECT id FROM CONTACT ORDER BY id DESC LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(getContactIdResuest);
            String contactId = resultSet.getString("id");
            contact.setId(Integer.parseInt(contactId));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteContact(int id) {
        String request = "DELETE FROM CONTACT WHERE id = " + id + ";";
        try {
            statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateContact(Contact contact) {
        int id = contact.getId();
        String name = contact.getName();
        String phoneNumber = contact.getPhoneNumber();
        String address = contact.getAddress();
        String group = contact.getGroup();
        String request = "UPDATE CONTACT SET " +
                "name = '" + name + "', phoneNumber = '" + phoneNumber + "'," +
                " address = '" + address + "', groups = '" + group + "' " +
                "WHERE id = '" + id + "';";
        try {
            statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Contact> getAllContacts() {
        String request = "SELECT * FROM CONTACT;";
        List<Contact> contactList = getContactListByRequest(request);
        return contactList;
    }

    public List<Contact> getAllContactByName(String stringForSearch) {
        String fullStringForSearch = "%" + stringForSearch + "%";
        String request = "SELECT * FROM 'CONTACT' WHERE name LIKE'" + fullStringForSearch + "';";
        List<Contact> contactListByName = getContactListByRequest(request);
        return contactListByName;
    }

    public List<Contact> getAllContactByPhoneNumber(String stringForSearch) {
        String fullStringForSearch = "%" + stringForSearch + "%";
        String request = "SELECT * FROM 'CONTACT' WHERE phoneNumber LIKE'" + fullStringForSearch + "';";
        List<Contact> contactListByPhoneNumber = getContactListByRequest(request);
        return contactListByPhoneNumber;
    }

    public List<Contact> getAllContactByString(String stringForSearch) {
        String fullStringForSearch = "%" + stringForSearch + "%";
        String request = "SELECT * FROM 'CONTACT' WHERE name LIKE'" + fullStringForSearch +
                "' OR phoneNumber LIKE'" + fullStringForSearch +
                "' OR address LIKE'" + fullStringForSearch +
                "' OR groups LIKE'" + fullStringForSearch + "';";
        List<Contact> contactListByString = getContactListByRequest(request);
        return contactListByString;
    }

    public List<Contact> getContactListByRequest(String request) {
        List<Contact> contactList = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            contactList = ResultSetConverter.convertResultSetToContactList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return contactList;
    }
}
