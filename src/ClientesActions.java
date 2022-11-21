import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ClientesActions {
    public static void guardarAction(JTextField textDni, JTextField textNombre, JTextField textApellidos) {
        String dni = Validations.validateDni(textDni);
        if (dni.equals("")){
            return;
        }
        String nombre = Validations.validateNotBlank(textNombre);
        String apellidos = Validations.validateNotBlank(textApellidos);
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("INSERT INTO clientes" +
                    "(dni, nombre, apellidos) VALUES (?,?,?)");
            setStringsForColumns(dni, nombre, apellidos, ps);
            JOptionPane.showMessageDialog(null, "Datos introducidos correctamente",
                    "Exito!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error al introducir los datos");
        }
    }

    private static void setStringsForColumns(String dni, String nombre, String apellidos,
                                             PreparedStatement ps) throws SQLException {
        ps.setString(1, dni);
        ps.setString(2, nombre);
        ps.setString(3, apellidos);
        ps.executeUpdate();
    }

    public static void llenarLista(JList<Object> lista) {
        DefaultListModel<Object> modeloLista = new DefaultListModel<>();
        lista.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                modeloLista.addElement(dni + ", " + nombre + ", " + apellidos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la lista");
        }
        lista.setModel(modeloLista);
    }
    public static void createFormularioClienteAction() {
        FormularioClientes formClientes = new FormularioClientes();
        formClientes.setExtendedState(formClientes.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        formClientes.setVisible(true);
        formClientes.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void limpiar(JTextField textDni, JTextField textNombre,JTextField textApellidos) {
        textDni.setText("");
        textNombre.setText("");
        textApellidos.setText("");
    }
}
