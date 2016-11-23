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

    public void addContact(Contact contact) throws SQLException {
        contactDao.addContact(contact);
    }

    public void deleteContactById(int id) throws SQLException {
        contactDao.deleteContact(id);
    }

    public void updateContact(Contact contact) throws SQLException {
        contactDao.updateContact(contact);
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public List<Contact> getAllContactByName(String stringForSearch) {
        return contactDao.getAllContactByName(stringForSearch);
    }

    public List<Contact> getAllContactByPhoneNumber(String stringForSearch) {
        return contactDao.getAllContactByPhoneNumber(stringForSearch);
    }

    public List<Contact> getAllContactByString(String stringForSearch) {
        return contactDao.getAllContactByString(stringForSearch);
    }
}
