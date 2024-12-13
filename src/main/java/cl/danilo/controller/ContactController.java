package cl.danilo.controller;

import cl.danilo.model.Contact;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ContactController {
    private List<Contact> contacts;
    private DefaultTableModel tableModel;

    public ContactController(DefaultTableModel tableModel) {
        this.contacts = new ArrayList<>();
        this.tableModel = tableModel;
    }

    public void addContact(String name, String lastname, String email, String phone) {
        Contact contact = new Contact(name, lastname, email, phone);
        contacts.add(contact);
        tableModel.addRow(new Object[]{name, lastname, email, phone});
    }

    public void modifyContact(int rowIndex, String name, String lastname, String email, String phone) {
        if (rowIndex >= 0 && rowIndex < contacts.size()) {
            Contact contact = contacts.get(rowIndex);
            contact.setName(name);
            contact.setLastname(lastname);
            contact.setEmail(email);
            contact.setPhone(phone);

            tableModel.setValueAt(name, rowIndex, 0);
            tableModel.setValueAt(lastname, rowIndex, 1);
            tableModel.setValueAt(email, rowIndex, 2);
            tableModel.setValueAt(phone, rowIndex, 3);
        }
    }

    public void deleteContact(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < contacts.size()) {
            contacts.remove(rowIndex);
            tableModel.removeRow(rowIndex);
        }
    }

    public Contact getContact(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < contacts.size()) {
            return contacts.get(rowIndex);
        }
        return null;
    }

}

