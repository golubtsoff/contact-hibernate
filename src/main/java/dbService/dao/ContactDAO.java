package dbService.dao;

import dbService.entity.Contact;
import org.hibernate.HibernateException;

import java.util.List;

public interface ContactDAO {
    Contact getContact(Long id) throws HibernateException;
    Long addContact(Contact contact) throws HibernateException;
    void updateContact(Contact contact) throws HibernateException;
    void deleteContact(Long id) throws HibernateException;
    void deleteAll() throws HibernateException;
    List<Contact> findContacts() throws HibernateException;
}
