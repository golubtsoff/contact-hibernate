package service;

import dbService.DBException;
import dbService.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact getContact(Long id) throws DBException;
    long addContact(Contact contact) throws DBException;
    void updateContact(Contact contact) throws DBException;
    void deleteContact(Long id) throws DBException;
    List<Contact> findContacts() throws DBException;
}
