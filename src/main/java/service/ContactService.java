package service;

import model.Contact;

import java.sql.SQLException;

/**
 * Created by Imant on 16.11.16.
 */
public interface ContactService {

    void addContact(Contact contact);

    void deleteContact(Contact contact);

    void getAllContacts() throws SQLException;

    void updateContact(Contact contact);
}
