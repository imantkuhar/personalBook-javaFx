package service;

import model.Contact;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Imant on 16.11.16.
 */
public interface ContactService {

    void addContact(Contact contact) throws SQLException;

    void deleteContactById(int id) throws SQLException;

    void updateContact(Contact contact) throws SQLException;

    List<Contact> getAllContacts();

    List<Contact> getAllContactByName(String string);

    List<Contact> getAllContactByPhoneNumber(String string);

    List<Contact> getAllContactByString(String string);
}
