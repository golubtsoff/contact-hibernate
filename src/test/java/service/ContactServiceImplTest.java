package service;

import dbService.DBException;
import dbService.entity.Contact;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static data.TestData.*;
import static org.junit.Assert.*;

public class ContactServiceImplTest {

    private final ContactService service = ServiceFactory.getContactServiceInstance();
    private List<Long> listId;

    @Before
    public void setUp() throws Exception {
        service.deleteAll();
        listId = new ArrayList<>();
        listId.add(service.addContact(contact1));
        listId.add(service.addContact(contact2));
        listId.add(service.addContact(contact3));
    }

    @Test
    public void testGetContact() throws DBException {
        Contact contact = service.getContact(listId.get(0));
        assertEquals("Wrong contact", contact1, contact);
    }

    @Test
    public void testGetNotExistContact() throws DBException {
        service.deleteContact(listId.get(0));
        Contact contact = service.getContact(listId.get(0));
        assertNull("Contact exist", contact);
    }

    @Test
    public void testAddContact() throws DBException {
        Long id = service.addContact(contact4);
        Contact contact = service.getContact(id);
        assertEquals("Error added contact", contact4, contact);
    }

    @Test
    public void testUpdateContact() throws DBException {
        Contact contact = service.getContact(listId.get(0));
        String newPhone = "123-456-78-90";
        assertNotEquals("Phone is equals", contact1.getPhone(), newPhone);
        contact.setPhone(newPhone);
        service.updateContact(contact);
        Contact c2 = service.getContact(contact.getId());
        assertEquals("Error updated contact", contact, c2);
    }

    @Test(expected = DBException.class)
    public void testUpdateNotExistContact() throws DBException {
        Contact contact = service.getContact(listId.get(0));
        service.deleteContact(listId.get(0));
        service.updateContact(contact);
    }

    @Test
    public void testDeleteContact() throws DBException {
        Contact contact = service.getContact(listId.get(0));
        assertEquals("Wrong contact", contact1, contact);
        service.deleteContact(listId.get(0));
        contact = service.getContact(listId.get(0));
        assertNull("Contact not deleted", contact);
    }

    @Test(expected = DBException.class)
    public void testDeleteNotExistContact() throws DBException{
        service.deleteContact(listId.get(0));
        service.deleteContact(listId.get(0));
    }

    @Test
    public void testDeleteAll() throws DBException {
        assertEquals("Contact's list is empty", listId.size(), service.findContacts().size());
        service.deleteAll();
        assertEquals("Contact's list isn't empty", 0, service.findContacts().size());
    }

    @Test
    public void testFindContacts() throws DBException {
        assertEquals("Contact's list is empty", listId.size(), service.findContacts().size());
    }
}