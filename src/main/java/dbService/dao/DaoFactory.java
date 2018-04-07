package dbService.dao;

public abstract class DaoFactory {

    private static volatile ContactDAO contactDAO;

    public static ContactDAO getContactDaoInstance() {
        ContactDAO instance = contactDAO;
        if (instance == null) {
            synchronized (ContactDAOImpl.class) {
                instance = contactDAO;
                if (instance == null) {
                    contactDAO = instance = new ContactDAOImpl();
                }
            }
        }
        return instance;
    }
}
