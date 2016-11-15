package main;

import db.ContactDao;
import model.Contact;
import service.ContactService;

import java.io.File;
import java.io.FilenameFilter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Imant on 14.11.16.
 */
public class Main {
    public static void main(String[] args) {
        Contact contact1 = new Contact("Ivan", "fas", "asdasd","asdas");
        Contact contact2 = new Contact("Ivan2", "fas", "asdasd","asdas");
        ContactService contactService = new ContactService();
        contactService.createDB();

        contactService.addContact(contact1);
        contactService.addContact(contact2);


    }
}
