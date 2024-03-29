import javax.swing.*;
import java.awt.event.*;
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
        botonMenu();
        textNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) {
                    evt.consume();
                } else if (c<=64  || c>122 || (c>90 && c<97)) {
                    if (c != 32) {
                        evt.consume();
                    }
                }
            }
        });
        textApellidos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (Character.isDigit(c)) {
                    evt.consume();
                } else if (c<=64  || c>122 || (c>90 && c<97)) {
                    if (c != 32) {
                        evt.consume();
                    }
                }
            }
        });
        textDni.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarLongitudDni(textDni.getText(), evt);
                }
            }
        });
        textDni.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textDni.getText().length() != 8 && !textDni.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "no Existen DNI de menos de 8 números.");
                    textDni.requestFocus();
                }
            }
        });
    }

    private void botonMenu() {
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
