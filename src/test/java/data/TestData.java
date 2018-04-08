package data;

import dbService.entity.Contact;

/**
 * Created by Evgeniy Golubtsov on 08.04.2018.
 */
public class TestData {

    public static final Contact contact1;
    public static final Contact contact2;
    public static final Contact contact3;
    public static final Contact contact4;

    static {
        contact1 = new Contact("Иван", "Соловьёв", "+7(900)800-000-11-22", "solovei@pisem.net");
        contact2 = new Contact("Ольга", "", "(812)7555599", "");
        contact3 = new Contact("Настя", "Косичкина", "", "nastuha@gmail.com");
        contact4 = new Contact("Сергей", "Чижов", "345-67-89", "s.chijov@mail.ru");
    }
}
