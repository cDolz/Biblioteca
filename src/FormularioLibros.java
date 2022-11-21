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

    public FormularioLibros() {
        setContentPane(panelFormulario);
        comboBoxSetItems();
        LibrosActions.llenarListaAction(lista);
        botonGuardar();
        botonLimpiar();
        botonBorrar();
        botonActualizar();
        textoBuscar();
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
                LibrosActions.limpiarAction(textNombre, textAutor, textFechaPublicacion, textEditorial);
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
