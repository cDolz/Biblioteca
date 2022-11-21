import javax.swing.*;

import static java.lang.String.valueOf;

public class Validations {
    public static String validateNotBlank(JTextField textField) {
        String textFill;
        if (!textField.getText().isBlank()) {
            textFill = textField.getText();
            return textFill;
        } else {
            JOptionPane.showMessageDialog(null, "Algun campo está vacío");
            return "";
        }
    }
    public static String validateDni(JTextField textDni) {
        String dni;
        String[] asignarLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z",
                "S", "Q", "V", "H", "L", "C", "K", "E"};
        validateNotBlank(textDni);
        dni = textDni.getText();
        if (dni.length() == 8) {
            int x = 0;
            try {
                x = Integer.parseInt(dni);
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Solo se admiten valores numéricos");
                return "";
            }
            int resto = x % 23;
            dni = (x + asignarLetra[resto]);
            return dni;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Algun campo está vacío o en el formato incorrecto", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }
}
