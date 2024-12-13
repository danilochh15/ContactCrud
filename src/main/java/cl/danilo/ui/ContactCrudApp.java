package cl.danilo.ui;

import cl.danilo.controller.ContactController;
import cl.danilo.util.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactCrudApp extends JFrame {
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnModify, btnDelete;
    private ContactController controller;

    public ContactCrudApp() {
        setTitle("Gestión de contactos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //crear modelo de la tabla
        String[] columnName = {"Nombre", "Apellido", "Email", "Fono"};
        tableModel = new DefaultTableModel(columnName, 0);
        contactTable = new JTable(tableModel);

        //inicializar el controlador
        controller = new ContactController(tableModel);

        // Botones
        btnAdd = Utils.createButtonWithIcon("Nuevo", "/icons/plus.png", 100, 40);
        btnAdd.setToolTipText("Agregar un nuevo contacto");

        btnModify = Utils.createButtonWithIcon("Modificar", "/icons/pen.png", 100, 40);
        btnModify.setToolTipText("Modificar un contacto");

        btnDelete = Utils.createButtonWithIcon("Eliminar", "/icons/bin.png", 100, 40);
        btnDelete.setToolTipText("Eliminar un contacto");

        //panel para los botones
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);
        btnPanel.add(btnModify);
        btnPanel.add(btnDelete);

        //accion del boton agregar
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactFormDialog(ContactCrudApp.this, controller, null, -1);
            }
        });

        //funcionalidad del boton modificar
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = contactTable.getSelectedRow();
                if(isTableEmpty(contactTable)){
                    showErrorMessage(ContactCrudApp.this,
                            "No hay contactos para modificar.","Error");
                }
                else if (selectedRow != -1) {
                    ContactFormDialog dialog = new ContactFormDialog(
                            ContactCrudApp.this,
                            controller,
                            controller.getContact(selectedRow),
                            selectedRow
                    );
                } else {
                    showErrorMessage(ContactCrudApp.this,
                            "Seleccione un contacto para modificar.","Error");
                }
            }
        });

        //funcionalidad boton eliminar
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = contactTable.getSelectedRow();

                if(isTableEmpty(contactTable)){
                    showErrorMessage(ContactCrudApp.this,
                            "No hay contactos para eliminar.","Error");
                }
                else if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            ContactCrudApp.this,
                            "¿Estás seguro de que deseas eliminar este contacto?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        controller.deleteContact(selectedRow);
                    }
                } else {
                    showErrorMessage(ContactCrudApp.this,
                            "Seleccione un contacto para eliminar.",
                            "Error");
                }
            }
        });

        //configurar la tabla
        JScrollPane tableScroll = new JScrollPane(contactTable);

        //configurar layout principal
        setLayout(new BorderLayout());
        add(tableScroll, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
    }


    public boolean isTableEmpty(JTable table) {
        return table.getRowCount() == 0;
    }

    public static void showErrorMessage(JFrame parent, String msg, String title){
        ImageIcon errorIcon = Utils.getResizedIcon("/icons/mark.png", 30, 30);
        JOptionPane.showMessageDialog(
                parent,
                msg,
                title,
                JOptionPane.ERROR_MESSAGE,
                errorIcon
        );
    }
}
