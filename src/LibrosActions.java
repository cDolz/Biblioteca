import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LibrosActions {
    static FormularioLibros formLibros;
    /*metodo para guardar los campos en la base de datos con su consulta sql*/
    public static void guardarAction(JTextField textNombre, JTextField textAutor,
                                     JTextField textAnno, JTextField textMes,JTextField textDia,
                                     JTextField textEditorial) {
        String nombre = Validations.validateNotBlank(textNombre);
        String autor = Validations.validateNotBlank(textAutor);
        String anno = Validations.validateNotBlank(textAnno);
        String mes = Validations.validateNotBlank(textMes);
        String dia = Validations.validateNotBlank(textDia);
        String editorial = Validations.validateNotBlank(textEditorial);
        String fechaPublicacion = (anno + "-" + mes + "-" +dia);
        if (nombre.equals("") || autor.equals("") || anno.equals("") || mes.equals("") || dia.equals("") || editorial.equals("")) {
            JOptionPane.showMessageDialog(null, "Algún campo está vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("INSERT INTO libros" + " (nombre, autor, fechaPublicacion, editorial) VALUES (?,?,?,?)");
            setStringsForColumns(nombre, autor, fechaPublicacion, editorial, ps);
            JOptionPane.showMessageDialog(null, "Datos introducidos correctamente");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Error al introducir los datos","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    /*metodo comun que setea valores tanto para guardar nuevos campos como actualizarlos*/
    private static void setStringsForColumns(String nombre, String autor, String fechaPublicacion, String editorial, PreparedStatement ps) throws SQLException {
        ps.setString(1, nombre);
        ps.setString(2, autor);
        ps.setString(3, fechaPublicacion);
        ps.setString(4, editorial);
        ps.executeUpdate();
    }
    /*metodo que limpia los campos, tanto en un boton como despues de cada consulta o error en consulta*/
    public static void limpiarAction(JTextField textNombre, JTextField textAutor,
                                     JTextField textAnno, JTextField textMes,JTextField textDia,
                                     JTextField textEditorial) {
        textNombre.setText("");
        textAutor.setText("");
        textAnno.setText("");
        textMes.setText("");
        textDia.setText("");
        textEditorial.setText("");
    }
    /*metodo que llena la JList con todos los parametros de la base de datos mediante Result Set*/
    public static void llenarListaAction(JList<Object> lista) {
        DefaultListModel<Object> modeloLista = new DefaultListModel<>();
        lista.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("SELECT * FROM libros");
            llenarListaCommonAction(modeloLista, ps);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la lista");
        }
        lista.setModel(modeloLista);
    }
    /*accion comun de result set tanto para llenar la lista con todos los campos de la
     base de datos como para llenarla con los resultados de la busqueda*/
    private static void llenarListaCommonAction(DefaultListModel<Object> modeloLista, PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String autor = rs.getString("autor");
            String fechaPublicacion = rs.getString("fechaPublicacion");
            String editorial = rs.getString("editorial");
            modeloLista.addElement(id + ", " + nombre + ", " + autor + ", " + fechaPublicacion + ", " + editorial);
        }
    }
    /*metodo que recoge la id de el campo seleccionado en la lista y lo borra por id
    en la base de datos*/
    public static void borrarAction(JList<Object> lista) {
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("DELETE FROM " + "libros WHERE id = ?");
            String campo = String.valueOf(lista.getSelectedValue());
            String[] id = campo.split(",", -1);
            ps.setInt(1, Integer.parseInt(id[0]));
            ps.execute();
            llenarListaAction(lista);
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar el campo.","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    /*metodo que actualiza los campos indicados una vez estos han sido validados, tomando la id del
    campo seleccionado y cambiando los valores por los introducidos en los JTextField*/
    public static void actualizarAction(JTextField textNombre, JTextField textAutor,JTextField textAnno,
                                        JTextField textMes,JTextField textDia,
                                         JTextField textEditorial,
                                        JList lista) {
        String nombre = Validations.validateNotBlank(textNombre);
        String autor = Validations.validateNotBlank(textAutor);
        String anno = Validations.validateNotBlank(textAnno);
        String mes = Validations.validateNotBlank(textMes);
        String dia = Validations.validateNotBlank(textDia);
        String editorial = Validations.validateNotBlank(textEditorial);
        String fechaPublicacion = (anno + "-" + mes + "-" +dia);
        if (nombre.equals("") || autor.equals("") || anno.equals("") || mes.equals("") || dia.equals("") || editorial.equals("")) {
            JOptionPane.showMessageDialog(null, "Algún campo está vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("UPDATE " + "libros SET nombre=?, autor=?, fechaPublicacion=?, editorial=? " + "WHERE id =?");
            String campo = String.valueOf(lista.getSelectedValue());
            String[] id = campo.split(",", -1);
            ps.setInt(5, Integer.parseInt(id[0]));
            setStringsForColumns(nombre, autor, fechaPublicacion, editorial, ps);
            JOptionPane.showMessageDialog(null, "Datos introducidos correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el campo");
        }
    }
    //metodo que hace una busqueda recogiendo el indice del comboBox que hace cambiar la sentencia
    //despues se llena la Jlist con la consulta seleccionada.
    public static void buscarTextAction(JComboBox comboSelect, JTextField textBuscar, JList lista) {
        int indice = comboSelect.getSelectedIndex();
        String sqlCombo = "";
        switch (indice) {
            case 0 -> sqlCombo = "SELECT * FROM libros WHERE nombre LIKE ?";
            case 1 -> sqlCombo = "SELECT * FROM libros WHERE autor LIKE ?";
            case 2 -> sqlCombo = "SELECT * FROM libros WHERE fechaPublicacion LIKE ?";
            case 3 -> sqlCombo = "SELECT * FROM libros WHERE editorial LIKE ?";
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
            LibrosActions.llenarListaAction(lista);
        }
    }
    /*metodo que crea un formulario de libros y lo hace visible*/
    public static void createFormularioLibroAction() {
        formLibros = new FormularioLibros();
        formLibros.setExtendedState(formLibros.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        formLibros.setVisible(true);
        formLibros.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /*metodo que devuelve al menu, hace un formulario invisible y el otr visible*/
    public static void botonMenuAction() {
        Menu.menu.setVisible(true);
        formLibros.setVisible(false);
    }
}
