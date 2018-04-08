package main;

import dbService.entity.Contact;
import service.ContactService;
import service.ServiceFactory;

import java.util.List;


public class Test {
    public static void main(String[] args) throws Exception {
        try {
            ContactService service = ServiceFactory.getContactServiceInstance();
            Contact contact1 = new Contact("Вася", "Пупкин", "", "");
            Contact contact2 = new Contact("Петя", "Васечкин", "+71234567890", "petia@pisem.net");
            service.addContact(contact1);
            service.addContact(contact2);
            contact1.setPhone("80987654321");
            service.updateContact(contact1);
            service.deleteContact(contact1.getId());
            Contact contact3 = service.getContact(2L);
            System.out.println(contact3);
            List<Contact> contacts = service.findContacts();
            System.out.println("Контакты 1" + contacts);
            service.deleteAll();
            contacts = service.findContacts();
            System.out.println("Контакты 2" + contacts);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

