import javax.swing.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class PrestamosActions {
    static Prestamos formPrestamos;

    public static void guardarAction(JTextField textAnno1, JTextField textMes1,
                                     JTextField textDia1, JTextField textAnno2,JTextField textMes2,
                                     JTextField textDia2, String idLibro, String idCliente) {
        String anno1 = Validations.validateNotBlank(textAnno1);
        String mes1 = Validations.validateNotBlank(textMes1);
        String dia1 = Validations.validateNotBlank(textDia1);
        String anno2 = Validations.validateNotBlank(textAnno2);
        String mes2 = Validations.validateNotBlank(textMes2);
        String dia2 = Validations.validateNotBlank(textDia2);
        String fechaInicio = (anno1 + "-" + mes1 + "-" +dia1);
        String fechaFin = (anno2 + "-" + mes2 + "-" +dia2);
        int idClienteNum = Integer.parseInt(idCliente);
        int idLibroNum = Integer.parseInt(idLibro);
        if (anno1.equals("") || mes1.equals("") || dia1.equals("") || anno2.equals("") || mes2.equals("") || dia2.equals("")) {
            JOptionPane.showMessageDialog(null, "Algún campo está vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("INSERT INTO prestamos"
                    + " (fechaInicio, fechaDevolucion, id_cliente, id_libro) VALUES (?,?,?,?)");
            setStringsForColumns(fechaInicio, fechaFin, idClienteNum, idLibroNum, ps);
            JOptionPane.showMessageDialog(null, "Datos introducidos correctamente");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Error al introducir los datos","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void llenarLista(JList<Object> lista) {
        DefaultListModel<Object> modeloLista = new DefaultListModel<>();
        lista.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("select p.id, c.nombre," +
                    " c.apellidos, l.nombre as nombreLibro, p.fechaInicio, p.fechaDevolucion from prestamos" +
                    " p join clientes c on p.id_cliente = c.id join libros l on p.id_libro = l.id");
            llenarListaCommonAction(modeloLista, ps);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al llenar la lista");
        }
        lista.setModel(modeloLista);
    }

    private static void llenarListaCommonAction(DefaultListModel<Object> modeloLista, PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombreCliente = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String nombreLibro = rs.getString("nombreLibro");
            String fechaInicio = rs.getString("fechaInicio");
            String fechaDevolucion = rs.getString("fechaDevolucion");
            modeloLista.addElement(id + ", " + nombreCliente + ", " + apellidos + ", " + nombreLibro
            + ", " + fechaInicio + ", " + fechaDevolucion);
        }
    }
    public static void createFormularioPrestamosAction() {
        formPrestamos = new Prestamos();
        formPrestamos.setExtendedState(formPrestamos.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        formPrestamos.setVisible(true);
        formPrestamos.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void botonMenuAction() {
        Menu.menu.setVisible(true);
        formPrestamos.setVisible(false);
    }

    private static void setStringsForColumns(String fechaInicio, String fechaFin, PreparedStatement ps) throws SQLException {
        ps.setString(1, fechaInicio);
        ps.setString(2, fechaFin);
        ps.executeUpdate();
    }
    private static void setStringsForColumns(String fechaInicio, String fechaFin, int idCliente,
                                             int idLibro, PreparedStatement ps) throws SQLException {
        ps.setString(1, fechaInicio);
        ps.setString(2, fechaFin);
        ps.setInt(3, idCliente);
        ps.setInt(4, idLibro);
        ps.executeUpdate();
    }
    public static void limpiar(JTextField textAnno1, JTextField textMes1, JTextField textDia1,
                               JTextField textAnno2, JTextField textMes2, JTextField textDia2) {
        textAnno1.setText("");
        textMes1.setText("");
        textDia1.setText("");
        textAnno2.setText("");
        textMes2.setText("");
        textDia2.setText("");
    }
    public static void actualizarAction(JList<Object> lista, JTextField textAnno1,
                                        JTextField textMes1, JTextField textDia1,
                                        JTextField textAnno2, JTextField textMes2,
                                        JTextField textDia2) {
        String anno1 = Validations.validateNotBlank(textAnno1);
        String mes1 = Validations.validateNotBlank(textMes1);
        String dia1 = Validations.validateNotBlank(textDia1);
        String anno2 = Validations.validateNotBlank(textAnno2);
        String mes2 = Validations.validateNotBlank(textMes2);
        String dia2 = Validations.validateNotBlank(textDia2);
        String fechaInicio = (anno1 + "-" + mes1 + "-" +dia1);
        String fechaFin = (anno2 + "-" + mes2 + "-" +dia2);
        if (anno1.equals("") || mes1.equals("") || dia1.equals("") || anno2.equals("") || mes2.equals("") || dia2.equals("")) {
            JOptionPane.showMessageDialog(null, "Algún campo está vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("UPDATE " + "prestamos SET fechaInicio=?, fechaDevolucion=? WHERE id = ?");
            String campo = String.valueOf(lista.getSelectedValue());
            String[] id = campo.split(",", -1);
            ps.setString(3, id[0]);
            setStringsForColumns(fechaInicio, fechaFin, ps);
            JOptionPane.showMessageDialog(null, "Datos introducidos correctamente", "Exito!", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar el campo.");
        }

    }
    public static void borrarAction(JList<Object> lista) {
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("DELETE FROM " + "prestamos WHERE id = ?");
            String campo = String.valueOf(lista.getSelectedValue());
            String[] id = campo.split(",", -1);
            ps.setString(1, id[0]);
            ps.execute();
            llenarLista(lista);
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar el campo.","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
