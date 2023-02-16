import javax.swing.*;
import java.awt.event.*;

public class FormularioLibros extends JFrame {
    private JTextField textNombre;
    private JLabel nombre;
    private JLabel autor;
    public JTextField textAutor;
    private JTextField textAnno;
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
    private JComboBox<Object> comboSelect;
    private JTextField textBuscar;
    private JButton botonMenu;
    private JTextField textMes;
    private JTextField textDia;
    private JLabel labelAnno;
    private JLabel labelMes;
    private JLabel diaLabel;

    public FormularioLibros() {
        /*todos metodos salvo algun que este especificado son actions Listeners de botones
         o campos de texto*/
        /*seteamos el contenido del formulario*/
        setContentPane(panelFormulario);
        /*seteamos el contenido del comboBox comboSelect*/
        comboBoxSetItems();
        LibrosActions.llenarListaAction(lista);
        botonGuardar();
        botonLimpiar();
        botonBorrar();
        botonActualizar();
        /*este action listener en concreto es un key listener que se activa cuando la
         longitud del campo es mayor a 3, de esta manera se puede actualizar la lista en directo*/
        textoBuscar();
        botonMenu();
        textAnno.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarAño(textAnno.getText(), evt);
                }
            }
        });
        textMes.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarMes(textMes.getText(), evt);
                }
            }
        });
        textDia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarDia(textDia.getText(), textMes.getText(), evt);
                }
            }
        });
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
        textAutor.addKeyListener(new KeyAdapter() {
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
        textEditorial.addKeyListener(new KeyAdapter() {
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
        textAnno.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textAnno.getText().length() != 4 && !textAnno.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "El año debe ser mayor que 1000");
                    textAnno.requestFocus();
                }
            }
        });
        textMes.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textMes.getText().equals("0") || textMes.getText().equals("00")){
                    JOptionPane.showMessageDialog(null, "El mes no puede ser 0.");
                    textMes.requestFocus();
                }
            }
        });
        textDia.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textDia.getText().equals("0") || textDia.getText().equals("00")){
                    JOptionPane.showMessageDialog(null, "El dia no puede ser 0.");
                    textDia.requestFocus();
                }
            }
        });
    }

    private void botonMenu() {
        botonMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibrosActions.botonMenuAction();
            }
        });
    }

    private void textoBuscar() {
        textBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                LibrosActions.buscarTextAction(comboSelect, textBuscar, lista);
            }
        });
    }

    private void comboBoxSetItems() {
        DefaultComboBoxModel<Object> comboModel = new DefaultComboBoxModel<>();
        comboModel.addElement("Nombre");
        comboModel.addElement("Autor");
        comboModel.addElement("Fecha de Publicacion");
        comboModel.addElement("Editorial");
        comboSelect.setModel(comboModel);
    }

    private void botonActualizar() {
        botonActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                LibrosActions.actualizarAction(textNombre, textAutor, textAnno,textMes,textDia, textEditorial, lista);
                LibrosActions.llenarListaAction(lista);
                LibrosActions.limpiarAction(textNombre, textAutor, textAnno,textMes,textDia, textEditorial);
            }
        });
    }

    private void botonBorrar() {
        borrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibrosActions.borrarAction(lista);
            }
        });
    }

    private void botonLimpiar() {
        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibrosActions.limpiarAction(textNombre, textAutor, textAnno,textMes,textDia, textEditorial);
            }
        });
    }

    private void botonGuardar() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibrosActions.guardarAction(textNombre, textAutor, textAnno ,textMes
                        ,textDia, textEditorial);
                LibrosActions.llenarListaAction(lista);
                LibrosActions.limpiarAction(textNombre, textAutor, textAnno,textMes,textDia, textEditorial);
            }
        });
    }
}
