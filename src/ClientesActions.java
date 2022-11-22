import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ClientesActions {
    static FormularioClientes formClientes;
    /*las actions realizadas en esta clase son exactamente iguales que las de la clase LibrosActions
    y pueden ser consultadas en la misma*/
    public static void guardarAction(JTextField textDni, JTextField textNombre, JTextField textApellidos) {
        String dni = Validations.validateDni(textDni);
        String nombre = Validations.validateNotBlank(textNombre);
        String apellidos = Validations.validateNotBlank(textApellidos);
        if (dni.equals("") || nombre.equals("") || apellidos.equals("")) {
            JOptionPane.showMessageDialog(null, "Algún campo está vacío o en un formato incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("INSERT INTO clientes" + "(dni, nombre, apellidos) VALUES (?,?,?)");
            setStringsForColumns(dni, nombre, apellidos, ps);
            JOptionPane.showMessageDialog(null, "Datos introducidos correctamente");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "Error al introducir los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void setStringsForColumns(String dni, String nombre, String apellidos, PreparedStatement ps) throws SQLException {
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
            llenarListaCommonAction(modeloLista, ps);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la lista");
        }
        lista.setModel(modeloLista);
    }

    private static void llenarListaCommonAction(DefaultListModel<Object> modeloLista, PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            modeloLista.addElement(dni + ", " + nombre + ", " + apellidos);
        }
    }

    public static void createFormularioClienteAction() {
        formClientes = new FormularioClientes();
        formClientes.setExtendedState(formClientes.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        formClientes.setVisible(true);
        formClientes.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void limpiar(JTextField textDni, JTextField textNombre, JTextField textApellidos) {
        textDni.setText("");
        textNombre.setText("");
        textApellidos.setText("");
    }

    public static void borrarAction(JList<Object> lista) {
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("DELETE FROM " + "clientes WHERE dni = ?");
            String campo = String.valueOf(lista.getSelectedValue());
            String[] dni = campo.split(",", -1);
            ps.setString(1, dni[0]);
            ps.execute();
            llenarLista(lista);
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar el campo.","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void actualizarAction(JList<Object> lista, JTextField textDni, JTextField textNombre, JTextField textApellidos) {
        String dni = Validations.validateDni(textDni);
        String nombre = Validations.validateNotBlank(textNombre);
        String apellidos = Validations.validateNotBlank(textApellidos);
        if (dni.equals("") || nombre.equals("") || apellidos.equals("")) {
            JOptionPane.showMessageDialog(null, "Algún campo está vacio o en formato incorrecto", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("UPDATE " + "clientes SET dni=?, nombre=?, apellidos=? WHERE dni = ?");
            String campo = String.valueOf(lista.getSelectedValue());
            String[] dniActual = campo.split(",", -1);
            ps.setString(4, dniActual[0]);
            setStringsForColumns(dni, nombre, apellidos, ps);
            JOptionPane.showMessageDialog(null, "Datos introducidos correctamente", "Exito!", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar el campo.");
        }

    }

    public static void buscarTextAction(JComboBox comboSelect, JTextField textBuscar, JList lista) {
        int indice = comboSelect.getSelectedIndex();
        String sqlCombo = "";
        switch (indice) {
            case 0 -> sqlCombo = "SELECT * FROM clientes WHERE dni LIKE ?";
            case 1 -> sqlCombo = "SELECT * FROM clientes WHERE nombre LIKE ?";
            case 2 -> sqlCombo = "SELECT * FROM clientes WHERE apellidos LIKE ?";
        }
        String likeBusqueda = textBuscar.getText();
        if (likeBusqueda.length() >= 2) {
            DefaultListModel<Object> modeloLista = new DefaultListModel<>();
            lista.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
            try {
                PreparedStatement ps = Biblioteca.getCon().prepareStatement(sqlCombo);
                ps.setString(1, "%" + likeBusqueda + "%");
                llenarListaCommonAction(modeloLista, ps);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al llenar la lista");
            }
            lista.setModel(modeloLista);
        } else {
            ClientesActions.llenarLista(lista);
        }
    }

    public static void botonMenuAction() {
        Menu.menu.setVisible(true);
        formClientes.setVisible(false);
    }
}
