package service;

import db.ContactDao;
import model.Contact;

import java.sql.SQLException;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;

    public ContactServiceImpl() {
        contactDao = ContactDao.getInstance();
    }

    public void addContact(Contact contact) {
        contactDao.addContact(contact);
    }

    public void deleteContact(Contact contact) {
        contactDao.deleteContact(contact);
    }

    public void getAllContacts() throws SQLException {
        contactDao.getAllContacts();
    }

    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }
}
