import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormularioClientes extends JFrame {
    public JTextField textNombre;
    private JLabel dni;
    private JTextField textDni;
    private JLabel nombre;
    private JLabel apellidos;
    private JTextField textApellidos;
    private JButton botonGuardar;
    private JButton botonLimpiar;
    private JPanel panelFormulario;
    private JList<Object> lista;
    private JButton borrar;
    private JLabel baseDeDatos;
    private JButton botonActualizar;
    private JLabel buscarPor;
    private JComboBox<Object> comboSelect;
    private JTextField textBuscar;
    private JButton botonMenu;

    public FormularioClientes() {
        setContentPane(panelFormulario);
        comboBoxSetItems();
        botonGuardar();
        botonLimpiar();
        ClientesActions.llenarLista(lista);
        botonBorrar();
        botonActualizar();
        textoBuscar();
        botonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientesActions.botonMenuAction();
            }
        });
    }

    private void textoBuscar() {
        textBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                ClientesActions.buscarTextAction(comboSelect, textBuscar, lista);
            }
        });
    }

    private static void setStringsForColumns(String dni, String nombre, String apellidos, PreparedStatement ps) throws SQLException {
        ps.setString(1, dni);
        ps.setString(2, nombre);
        ps.setString(3, apellidos);
        ps.executeUpdate();
    }

    public static void createFormulario() {
        FormularioClientes form = new FormularioClientes();
        form.setExtendedState(form.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        form.setVisible(true);
        form.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void botonActualizar() {
        botonActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientesActions.actualizarAction(lista, textDni, textNombre, textApellidos);
                ClientesActions.llenarLista(lista);
                ClientesActions.limpiar(textDni, textNombre, textApellidos);
            }
        });
    }

    private void botonBorrar() {
        borrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientesActions.borrarAction(lista);
            }
        });
    }

    private void botonLimpiar() {
        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientesActions.limpiar(textDni, textNombre, textApellidos);
            }
        });
    }

    private void botonGuardar() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientesActions.guardarAction(textDni, textNombre, textApellidos);
                ClientesActions.llenarLista(lista);
                ClientesActions.limpiar(textDni, textNombre, textApellidos);

            }
        });
    }

    private void comboBoxSetItems() {
        DefaultComboBoxModel<Object> comboModel = new DefaultComboBoxModel<>();
        comboModel.addElement("DNI");
        comboModel.addElement("Nombre");
        comboModel.addElement("Apellido");
        comboSelect.setModel(comboModel);
    }
}
