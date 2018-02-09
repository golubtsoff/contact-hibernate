package dbService.dao;

import dbService.entity.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class ContactsDAO {

    private Session session;

    public ContactsDAO(Session session) {
        this.session = session;
    }

    public Contact getContact(Long id) throws HibernateException {
        return (Contact) session.get(Contact.class, id);
    }

    public Long addContact(Contact contact) throws HibernateException {
        return (Long) session.save(contact);
    }

    public void updateContact(Contact contact) throws HibernateException{
        session.update(contact);
    }

    public void deleteContact(Long id) throws HibernateException{
        Contact contact = (Contact) session.byId(Contact.class).getReference(id);
        session.delete(contact);
    }

    public List<Contact> findContacts() throws HibernateException{
       return session.createQuery("from Contact").list();
    }
}
