import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormularioLibros extends JFrame {
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
    private JComboBox<Object> comboSelect;
    private JTextField textBuscar;
    private JButton botonMenu;
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

                LibrosActions.actualizarAction(textNombre, textAutor, textFechaPublicacion, textEditorial, lista);
                LibrosActions.llenarListaAction(lista);
                LibrosActions.limpiarAction(textNombre, textAutor, textFechaPublicacion, textEditorial);
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
                LibrosActions.limpiarAction(textNombre, textAutor, textFechaPublicacion, textEditorial);
            }
        });
    }

    private void botonGuardar() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LibrosActions.guardarAction(textNombre, textAutor, textFechaPublicacion, textEditorial);
                LibrosActions.llenarListaAction(lista);
                LibrosActions.limpiarAction(textNombre, textAutor, textFechaPublicacion, textEditorial);
            }
        });
    }
}
