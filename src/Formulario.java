import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Formulario extends JFrame {
    PreparedStatement ps;
    private JTextField textNombre;
    private JLabel nombre;
    private JLabel autor;
    private JTextField textAutor;
    private JTextField textFechaPublicacion;
    private JLabel editorial;
    private JTextField textEditorial;
    private JLabel fechaPublicacion;
    private JButton botonGuardar;
    private JButton botonLimpiar;
    private JPanel panelFormulario;

    public Formulario() {
        setContentPane(panelFormulario);
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = null;
                if (!textNombre.getText().isBlank()) {
                    nombre = textNombre.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String autor = null;
                if (!textAutor.getText().isBlank()) {
                    autor = textAutor.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String fechaPublicacion = null;
                if (!textFechaPublicacion.getText().isBlank()) {
                    fechaPublicacion = textFechaPublicacion.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String editorial = null;
                if (!textEditorial.getText().isBlank()) {
                    editorial = textEditorial.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Algun campo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String sql = "INSERT INTO libros (nombre, autor, fechaPublicacion, editorial) VALUES (?,?,?,?)";
                try {
                    PreparedStatement ps = Biblioteca.getCon().prepareStatement(sql);
                    ps.setString(1, nombre);
                    ps.setString(2, autor);
                    ps.setString(3, fechaPublicacion);
                    ps.setString(4, editorial);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Datos introducidos correctamente", "Exito!", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(null, "Error al introducir los datos", "Error al insertar", JOptionPane.ERROR_MESSAGE);
                }
                limpiar();
            }
        });
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
    }

    private void limpiar() {
        textNombre.setText("");
        textAutor.setText("");
        textFechaPublicacion.setText("");
        textEditorial.setText("");
    }

    public static void createFormulario() {
        Formulario form = new Formulario();
        form.setSize(397, 334);
        form.setVisible(true);
        form.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
