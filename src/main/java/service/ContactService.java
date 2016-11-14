package service;

import db.ContactDao;
import model.Contact;

/**
 * Created by Imant on 14.11.16.
 */
public class ContactService {

    private ContactDao contactDao;

    public void addContact(Contact contact){
        contactDao.addContact(contact);
    }

    public void deleteContact(){

    }

    public void getAllContacts(){

    }

    public void updateContact(Contact contact){

    }
}
