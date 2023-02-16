import javax.swing.*;
import java.awt.event.KeyEvent;

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

    public static void validarAño(String textAnno, KeyEvent evt) {
        char c = evt.getKeyChar();
        if (textAnno.length() == 0) {
            if (c < 49 || c > 50) {
                evt.consume();
            }
        } else if (textAnno.length() == 1) {
            if (textAnno.equals("2") && c != 48) {
                evt.consume();
            }
        } else if (textAnno.length() == 2) {
            if (textAnno.equals("20") && c > 50) {
                evt.consume();
            }
        } else if (textAnno.length() == 3) {
            if (textAnno.equals("202") && c > 51) {
                evt.consume();
            }
        } else if (textAnno.length() == 4) {
            evt.consume();
        }
    }

    public static void validarMes(String textMes, KeyEvent evt) {
        char c = evt.getKeyChar();
        if (textMes.length() == 1) {
            if (Integer.parseInt(textMes) > 1) {
                evt.consume();
            } else if (Integer.parseInt(textMes) == 1 && c > 50) {
                evt.consume();
            }
        } else if (textMes.length() == 2) {
            evt.consume();
        }
    }

    public static void validarDia(String textDia, String textMes, KeyEvent evt) {
        char c = evt.getKeyChar();
        if (textMes.length() == 0) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Rellena primero el mes, para calcular cuántos días tiene");
        } else if (textDia.length() == 1) {
            if (Integer.parseInt(textDia) > 3) {
                if (textMes.equals("2") && Integer.parseInt(textDia) > 2) {
                    evt.consume();
                } else {
                    evt.consume();
                }
            } else if ((Integer.parseInt(textMes) == 2) && ((textDia.equals("2") && c > 56) || (textDia.equals("3")))) {
                evt.consume();
            } else if ((Integer.parseInt(textMes) == 1 || Integer.parseInt(textMes) == 3 || Integer.parseInt(textMes) == 5
                    || Integer.parseInt(textMes) == 7 || Integer.parseInt(textMes) == 8 || Integer.parseInt(textMes) == 10
                    || Integer.parseInt(textMes) == 12) && (textDia.equals("3") && c > 49)) {
                evt.consume();
            } else if ((Integer.parseInt(textMes) == 4 || Integer.parseInt(textMes) == 6 || Integer.parseInt(textMes) == 9
                    || Integer.parseInt(textMes) == 11) && (textDia.equals("3") && c > 48)) {
                evt.consume();
            }
        } else if (textDia.length() == 2) {
            evt.consume();
        }
    }

    public static void validarLongitudDni(String textDni, KeyEvent evt) {
        if (textDni.length() >= 8) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "No existen DNI con más de 8 números.");
        }
    }

    public static boolean validarFecha(String textAnno1, String textAnno2, String textMes1, String textMes2,
                                    String textDia1, String textDia2) {
        if (Integer.parseInt(textAnno2) < Integer.parseInt(textAnno1)) {
            JOptionPane.showMessageDialog(null, "La fecha de devolución no puede ser anterior a la fecha de inicio " +
                    "de préstamo");
            return false;
        } else if (Integer.parseInt(textAnno2) == Integer.parseInt(textAnno1)) {
            if (Integer.parseInt(textMes2) < Integer.parseInt(textMes1)) {
                JOptionPane.showMessageDialog(null, "La fecha de devolución no puede ser anterior a la fecha de inicio " +
                        "de préstamo");
                return false;
            } else if (Integer.parseInt(textMes2) == Integer.parseInt(textMes1)) {
                if (Integer.parseInt(textDia2) < Integer.parseInt(textDia1)) {
                    JOptionPane.showMessageDialog(null, "La fecha de devolución no puede ser anterior a la fecha de inicio " +
                            "de préstamo");
                    return false;
                } else if (Integer.parseInt(textDia2) == Integer.parseInt(textDia1)) {
                    JOptionPane.showMessageDialog(null, "La fecha de devolución y la de préstamo no puede ser la " +
                            "misma");
                    return false;
                }
            }
        }
        return true;
    }

}
