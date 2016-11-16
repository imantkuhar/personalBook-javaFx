package main;

import model.Contact;
import service.ContactService;

import java.sql.SQLException;

/**
 * Created by Imant on 14.11.16.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Contact contact1 = new Contact("Ivan", "067-684-2475", "Lviv", "Friend");
        Contact contact2 = new Contact("Dima", "093-243-5861", "Odessa", "Job");
        Contact contact3 = new Contact("Roma", "097-385-8713", "Kiev", "Genesis Group");
        ContactService contactService = new ContactService();

        contactService.addContact(contact1);
        contactService.addContact(contact2);
        contactService.addContact(contact3);

        contactService.deleteContact(contact1);

        contactService.getAllContacts();
    }
}
