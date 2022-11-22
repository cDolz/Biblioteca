import javax.swing.*;

public class Validations {
    /*Este metodo comprueba si el texto tiene algo escrito, retorna un string vacio que luego es
    * comprobado con un equals*/
    public static String validateNotBlank(JTextField textField) {
        String textFill;
        if (!textField.getText().isBlank()) {
            textFill = textField.getText();
            return textFill;
        } else {
            return "";
        }
    }
    /*metodo mediante el cual al dni se le asigne la letra automaticamente, evitando así
    * que el dni sea inventado*/
    public static String validateDni(JTextField textDni) {
        String dni;
        String[] asignarLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        validateNotBlank(textDni);
        dni = textDni.getText();
        if (dni.length() == 8) {
            int x;
            try {
                x = Integer.parseInt(dni);
            } catch (Exception e) {
                return "";
            }
            int resto = x % 23;
            dni = (x + asignarLetra[resto]);
            return dni;
        } else {
            return "";
        }
    }
}
