package cl.danilo.ui;

import cl.danilo.controller.ContactController;
import cl.danilo.controller.MaxLengthDocument;
import cl.danilo.model.Contact;
import cl.danilo.util.Utils;
import org.jdesktop.swingx.JXTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ContactFormDialog extends JDialog {
    private JXTextField nameField, lastnameField, emailField, phoneField;
    private JButton btnSave, btnCancel;
    private ContactController controller;
    private Contact existingContact;
    private int contactIndex;

    public ContactFormDialog(JFrame parent, ContactController controller, Contact contact, int index) {
        super(parent, true);
        this.controller = controller;
        this.existingContact = contact;
        this.contactIndex = index;

        setTitle(contact == null ? "Nuevo Contacto" : "Modificar Contacto");
        setSize(400, 250);
        setLocationRelativeTo(parent);

        //campos del formulario
        nameField = new JXTextField("Juan");
        nameField.setToolTipText("Escribe tu nombre aquí (máximo 30 caracteres)");

        lastnameField = new JXTextField("Perez");
        lastnameField.setToolTipText("Escribe tu apellido aquí (máximo 30 caracteres)");

        emailField = new JXTextField("jperez@gmail.com");
        emailField.setToolTipText("Escribe tu email aquí (máximo 30 caracteres)");

        phoneField = new JXTextField("123456789");
        phoneField.setToolTipText("Escribe tu fono aquí (opcional)");

        nameField.setDocument(new MaxLengthDocument(30));
        lastnameField.setDocument(new MaxLengthDocument(30));
        lastnameField.setDocument(new MaxLengthDocument(30));
        phoneField.setDocument(new MaxLengthDocument(9));

        setTextFieldStyle(nameField);
        setTextFieldStyle(lastnameField);
        setTextFieldStyle(emailField);
        setTextFieldStyle(phoneField);

        if (contact != null) {
            nameField.setText(contact.getName());
            lastnameField.setText(contact.getLastname());
            emailField.setText(contact.getEmail());
            phoneField.setText(contact.getPhone());
        }

        //boton guardar
        btnSave = new JButton("Guardar");
        btnSave.setToolTipText("Guardar los datos");
        btnSave.addActionListener(e -> {
            String name = nameField.getText().trim();
            String lastname = lastnameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            ImageIcon icon = Utils.getResizedIcon("/icons/mark.png", 30, 30);
            resetFieldBorders();
            boolean valid = true;

            if(name.isEmpty()){
                nameField.setBorder(new LineBorder(Color.RED, 1));
                valid = false;
            }
            if (lastname.isEmpty()) {
                lastnameField.setBorder(new LineBorder(Color.RED, 1));
                valid = false;
            }

            if (email.isEmpty()) {
                emailField.setBorder(new LineBorder(Color.RED, 1));
                valid = false;
            }

            if (!Utils.isValidEmail(emailField.getText())) {
                emailField.setBorder(new LineBorder(Color.RED, 1));
                valid = false;
                JOptionPane.showMessageDialog(ContactFormDialog.this, "Por favor, ingrese un email válido.", "Error", JOptionPane.INFORMATION_MESSAGE, icon);

            } else if(!phoneField.getText().isEmpty() && !Utils.isValidPhone(phoneField.getText())) {//validar fono solo si no esta vacio el campo
                phoneField.setBorder(new LineBorder(Color.RED, 1));
                valid = false;
                JOptionPane.showMessageDialog(ContactFormDialog.this, "El número de teléfono debe tener 9 dígitos.", "Error", JOptionPane.INFORMATION_MESSAGE, icon);
            }
            if(valid){
                if (existingContact == null) {
                    controller.addContact(name, lastname, email, phone);
                } else {
                    controller.modifyContact(contactIndex, name, lastname, email, phone);
                }
                dispose();
            }else{
                JOptionPane.showMessageDialog(
                        this,
                        "Por favor, complete los campos requeridos.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE,
                        icon
                );
            }

        });

        //boton cancelar
        btnCancel = new JButton("Cancelar");
        btnCancel.addActionListener(e -> dispose());

        //panel que cntiene el formulario
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Nombre:"));
        panel.add(nameField);

        panel.add(new JLabel("Apellido:"));
        panel.add(lastnameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Fono:"));
        panel.add(phoneField);

        panel.add(btnSave);
        panel.add(btnCancel);

        add(panel);
        setVisible(true);
    }

    //aplica fondo blanco a los campos del formulario
    public static void setTextFieldStyle(JXTextField textField) {
        textField.setOpaque(true);
        textField.setBackground(Color.WHITE);
    }



    //restablece el borde de todos los campos a su valor original
    private void resetFieldBorders() {
        nameField.setBorder(UIManager.getBorder("TextField.border"));
        lastnameField.setBorder(UIManager.getBorder("TextField.border"));
        emailField.setBorder(UIManager.getBorder("TextField.border"));
        phoneField.setBorder(UIManager.getBorder("TextField.border"));
    }
}
