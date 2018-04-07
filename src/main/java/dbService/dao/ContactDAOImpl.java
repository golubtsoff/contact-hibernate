package dbService.dao;

import dbService.DBService;
import dbService.entity.Contact;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;

import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    ContactDAOImpl() {
    }

    @Override
    public Contact getContact(Long id) throws HibernateException {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(Contact.class, id, LockMode.PESSIMISTIC_READ);
    }

    @Override
    public Long addContact(Contact contact) throws HibernateException {
        return (Long) DBService.getSessionFactory()
                .getCurrentSession()
                .save(contact);
    }

    @Override
    public void updateContact(Contact contact) throws HibernateException {
        DBService.getSessionFactory()
                .getCurrentSession()
                .update(contact);
    }

    @Override
    public void deleteContact(Long id) throws HibernateException {
        Session session = DBService.getSessionFactory()
                .getCurrentSession();
        Contact contact = session
                .byId(Contact.class)
                .getReference(id);
        session.delete(contact);
    }

    @Override
    public List<Contact> findContacts() throws HibernateException {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from Contact")
                .list();
    }
}
