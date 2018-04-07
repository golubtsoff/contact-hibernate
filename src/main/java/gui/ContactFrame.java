package gui;

import dbService.DBException;
import dbService.entity.Contact;
import service.ContactService;
import service.ServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContactFrame extends JFrame implements ActionListener
{
    private static final String FRAME = "frame";
    private static final String C_REFRESH = "refresh";
    private static final String C_ADD = "add";
    private static final String C_UPDATE = "update";
    private static final String C_DELETE = "delete";

    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private final ContactService contactService = ServiceFactory.getContactServiceInstance();
    private final JTable contactTable = new JTable();

    public ContactFrame(){
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(gridbag);
        btnPanel.add(createButton(gridbag, gbc, GuiResource.getLabel(FRAME, C_REFRESH), LOAD));
        btnPanel.add(createButton(gridbag, gbc, GuiResource.getLabel(FRAME, C_ADD), ADD));
        btnPanel.add(createButton(gridbag, gbc, GuiResource.getLabel(FRAME, C_UPDATE), EDIT));
        btnPanel.add(createButton(gridbag, gbc, GuiResource.getLabel(FRAME, C_DELETE), DELETE));

        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(btnPanel, BorderLayout.NORTH);
        add(left, BorderLayout.WEST);

        add(new JScrollPane(contactTable), BorderLayout.CENTER);

        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            loadContact();
        } catch (DBException ex) {
            ex.printStackTrace();
        }
    }

    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        JButton button = new JButton(title);
        button.setActionCommand(action);
        button.addActionListener(this);
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        try {
            switch (action) {
                case LOAD:
                    loadContact();
                    break;
                case ADD:
                    addContact();
                    break;
                case EDIT:
                    editContact();
                    break;
                case DELETE:
                    deleteContact();
                    break;
            }
        } catch (DBException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void loadContact() throws DBException {
        List<Contact> contacts = contactService.findContacts();
        ContactModel cm = new ContactModel(contacts);
        contactTable.setModel(cm);
    }

    private void addContact() throws DBException {
        EditContactDialog ecd = new EditContactDialog();
        saveContact(ecd);
    }

    private void editContact() throws DBException {
        int sr = contactTable.getSelectedRow();
        if (sr != -1) {
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            Contact cnt = contactService.getContact(id);
            EditContactDialog ecd = new EditContactDialog(contactService.getContact(id));
            saveContact(ecd);
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }

    private void deleteContact() throws DBException {
        int sr = contactTable.getSelectedRow();
        if (sr != -1) {
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            contactService.deleteContact(id);
            loadContact();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }

    private void saveContact(EditContactDialog ecd) throws DBException {
        if (ecd.isSave()) {
            Contact cnt = ecd.getContact();
            if (cnt.getId() != null) {
                contactService.updateContact(cnt);
            } else {
                contactService.addContact(cnt);
            }
            loadContact();
        }
    }
}