package service;

import db.ContactDao;
import model.Contact;

import java.sql.SQLException;
import java.util.List;

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

    public void deleteContactById(int id) {
        contactDao.deleteContact(id);
    }

    public List<Contact> getAllContacts() throws SQLException {
        return contactDao.getAllContacts();
    }

    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }
}
