package service;

import dbService.DBException;
import dbService.DBService;
import dbService.dao.ContactDAO;
import dbService.dao.DaoFactory;
import dbService.entity.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;

public class ContactServiceImpl implements ContactService {

    @Override
    public Contact getContact(Long id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ContactDAO dao = DaoFactory.getContactDaoInstance();
            Contact dataSet = dao.getContact(id);

            transaction.commit();
            return dataSet;
        } catch (HibernateException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public long addContact(Contact contact) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ContactDAO dao = DaoFactory.getContactDaoInstance();
            long id = dao.addContact(contact);

            transaction.commit();
            return id;
        } catch (HibernateException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public void updateContact(Contact contact) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ContactDAO dao = DaoFactory.getContactDaoInstance();
            dao.updateContact(contact);

            transaction.commit();
        } catch (HibernateException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public void deleteContact(Long id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ContactDAO dao = DaoFactory.getContactDaoInstance();
            dao.deleteContact(id);

            transaction.commit();
        } catch (HibernateException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public List<Contact> findContacts() throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ContactDAO dao = DaoFactory.getContactDaoInstance();
            List<Contact> dataSet = dao.findContacts();

            transaction.commit();
            return dataSet;
        } catch (HibernateException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }
}
