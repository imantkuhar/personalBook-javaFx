package service;

import model.Contact;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Imant on 16.11.16.
 */
public interface ContactService {

    void addContact(Contact contact);

    void deleteContactById(int id);

    List<Contact> getAllContacts() throws SQLException;

    void updateContact(Contact contact);
}
