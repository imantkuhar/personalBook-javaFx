package service;

import db.ContactDao;
import model.Contact;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactService {

    private ContactDao contactDao = new ContactDao();

    public void createDB(){
        contactDao.createConnection();
        contactDao.createContactTable();
    }

    public void addContact(Contact contact) {
        contactDao.addContact(contact);
    }

    public void deleteContact(Contact contact, int contactId) {
        contactDao.deleteContact(contact, contactId);
    }

    public void getAllContacts() {
        contactDao.getAllContacts();
    }

    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }
}
