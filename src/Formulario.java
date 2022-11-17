import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Formulario extends JFrame {
    private JTextField textNombre;
    private JLabel nombre;
    private JLabel autor;
    public JTextField textAutor;
    private JTextField textFechaPublicacion;
    private JLabel editorial;
    private JTextField textEditorial;
    private JLabel fechaPublicacion;
    private JButton botonGuardar;
    private JButton botonLimpiar;
    private JPanel panelFormulario;
    private JList<Object> lista;
    private JButton borrar;
    private JLabel baseDeDatos;
    private JButton botonActualizar;
    private JLabel buscarPor;
    private JComboBox comboBox1;
    private JTextField textField1;

    public Formulario() {
        setContentPane(panelFormulario);
        botonGuardar();
        botonLimpiar();
        llenarLista();
        borrar();
        actualizar();
    }

    private void actualizar() {
        botonActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre;
                if (!textNombre.getText().isBlank()) {
                    nombre = textNombre.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String autor;
                if (!textAutor.getText().isBlank()) {
                    autor = textAutor.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String fechaPublicacion;
                if (!textFechaPublicacion.getText().isBlank()) {
                    fechaPublicacion = textFechaPublicacion.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String editorial;
                if (!textEditorial.getText().isBlank()) {
                    editorial = textEditorial.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    PreparedStatement ps = Biblioteca.getCon().prepareStatement("UPDATE " +
                            "libros SET nombre=?, autor=?, fechaPublicacion=?, editorial=? " +
                            "WHERE id =?");
                    String campo = String.valueOf(lista.getSelectedValue());
                    String[] id = campo.split(",", -1);
                    ps.setInt(5, Integer.parseInt(id[0]));
                    ps.setString(1, nombre);
                    ps.setString(2, autor);
                    ps.setString(3, fechaPublicacion);
                    ps.setString(4, editorial);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Datos introducidos correctamente",
                            "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    llenarLista();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al introducir los datos",
                            "Error al insertar", JOptionPane.ERROR_MESSAGE);
                }
                limpiar();
            }
        });
    }

    private void borrar() {
        borrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps = Biblioteca.getCon().prepareStatement("DELETE FROM " +
                            "libros WHERE id = ?");
                    String campo = String.valueOf(lista.getSelectedValue());
                    String[] id = campo.split(",", -1);
                    ps.setInt(1, Integer.parseInt(id[0]));
                    ps.execute();
                    llenarLista();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al borrar");
                }
                limpiar();
            }
        });
    }

    private void llenarLista() {
        DefaultListModel<Object> modeloLista = new DefaultListModel<>();
        lista.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        try {
            PreparedStatement ps = Biblioteca.getCon().prepareStatement("SELECT * FROM libros");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String autor = rs.getString("autor");
                String fechaPublicacion = rs.getString("fechaPublicacion");
                String editorial = rs.getString("editorial");
                modeloLista.addElement(id + ", " + nombre + ", " + autor + ", " +
                        fechaPublicacion + ", " + editorial);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al llenar la lista");
        }
        lista.setModel(modeloLista);
    }

    private void botonLimpiar() {
        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
    }

    private void botonGuardar() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre;
                if (!textNombre.getText().isBlank()) {
                    nombre = textNombre.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String autor;
                if (!textAutor.getText().isBlank()) {
                    autor = textAutor.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String fechaPublicacion;
                if (!textFechaPublicacion.getText().isBlank()) {
                    fechaPublicacion = textFechaPublicacion.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String editorial;
                if (!textEditorial.getText().isBlank()) {
                    editorial = textEditorial.getText();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    PreparedStatement ps = Biblioteca.getCon().prepareStatement("INSERT INTO libros" +
                            " (nombre, autor, fechaPublicacion, editorial) VALUES (?,?,?,?)");
                    setStringsForColumns(nombre, autor, fechaPublicacion, editorial, ps);
                    JOptionPane.showMessageDialog(null, "Datos introducidos correctamente",
                            "Exito!", JOptionPane.INFORMATION_MESSAGE);
                    llenarLista();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "Error al introducir los datos",
                            "Error al insertar", JOptionPane.ERROR_MESSAGE);
                }
                limpiar();
            }
        });
    }

    private static void setStringsForColumns(String nombre, String autor, String fechaPublicacion, String
            editorial, PreparedStatement ps) throws SQLException {
        ps.setString(1, nombre);
        ps.setString(2, autor);
        ps.setString(3, fechaPublicacion);
        ps.setString(4, editorial);
        ps.executeUpdate();
    }

    private void limpiar() {
        textNombre.setText("");
        textAutor.setText("");
        textFechaPublicacion.setText("");
        textEditorial.setText("");
    }

    public static void createFormulario() {
        Formulario form = new Formulario();
        form.setExtendedState(form.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        form.setVisible(true);
        form.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
