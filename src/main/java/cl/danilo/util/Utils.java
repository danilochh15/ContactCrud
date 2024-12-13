package cl.danilo.util;

import javax.swing.*;
import java.awt.*;

public class Utils {
    //funcion para crear un boton con icono redimensionado
    public static JButton createButtonWithIcon(String text, String iconPath, int width, int height) {
        ImageIcon img = getResizedIcon(iconPath, 25, 25);
        JButton button = new JButton(text, img);//crear el boton con el icono y el texto
        button.setPreferredSize(new Dimension(width , height ));//ajusta el tamano del boton
        return button;
    }

    //funcion para cargar y redimensionar un icono
    public static ImageIcon getResizedIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(Utils.class.getResource(path));//cargar el icono desde la ruta
        Image image = icon.getImage(); //obtener la imagen
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); //redimensionar imagen
        return new ImageIcon(resizedImage);
    }

    //funcion para validar formato del email
    public static boolean isValidEmail(String email) {
        //expresion regular para formato de email basico
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    //funcion para validar el formato del telefono (9 digitos)
    public static boolean isValidPhone(String phone) {
        return phone.matches("^[0-9]{9}$");
    }
}
