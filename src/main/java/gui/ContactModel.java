package gui;

import dbService.entity.Contact;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContactModel extends AbstractTableModel
{
    private static final String MODEL = "model";
    
    private static final String[] HEADERS = {
        "id", "givenname", "surname", "phone", "email"
    };
    
    private final List<Contact> contacts;

    public ContactModel(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    @Override
    public int getRowCount() {
        return contacts.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int col) {
        return GuiResource.getLabel(MODEL, HEADERS[col]);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Contact contact = contacts.get(row);
        switch (col) {
            case 0:
                return contact.getId().toString();
            case 1:
                return contact.getFirstName();
            case 2:
                return contact.getLastName();
            case 3:
                return contact.getPhone();
            case 4:
                return contact.getEmail();
            default:
                return "";
        }
    }
}
