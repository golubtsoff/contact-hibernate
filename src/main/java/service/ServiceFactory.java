package service;

public abstract class ServiceFactory {
    private static volatile ContactService contactService;

    public static ContactService getContactServiceInstance() {
        ContactService instance = contactService;
        if (instance == null) {
            synchronized (ContactServiceImpl.class) {
                instance = contactService;
                if (instance == null) {
                    contactService = instance = new ContactServiceImpl();
                }
            }
        }
        return instance;
    }
}
