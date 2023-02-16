import javax.swing.*;
import java.awt.event.*;

public class Prestamos extends JFrame{
    private JList listaClientes;
    private JList listaPrestamos;
    private JList listaLibros;
    private JTextField textDia1;
    private JTextField textDia2;
    private JButton botonGuardar;
    private JButton botonLimpiar;
    private JButton botonActualizar;
    private JTextField textAnno1;
    private JTextField textMes2;
    private JTextField textAnno2;
    private JTextField textMes1;
    private JButton volverAlMenuButton;
    private JPanel contenidoPanel;
    private JButton botonBorrar;
    private JLabel clienteLabel;
    private JLabel libroLabel;
    private JLabel labelPrestamo;
    private JLabel labelAnno1;
    private JLabel labelMes1;
    private JLabel labelDia1;
    private JLabel labelAnno2;
    private JLabel labelMes2;
    private JLabel labelDia2;
    private JLabel labelFechaFin;
    private JLabel labelFechaInicio;

    public Prestamos (){
        setContentPane(contenidoPanel);
        ClientesActions.llenarLista(listaClientes);
        LibrosActions.llenarListaAction(listaLibros);
        PrestamosActions.llenarLista(listaPrestamos);
        textAnno1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarAño(textAnno1.getText(), evt);
                }
            }
        });
        textMes1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarMes(textMes1.getText(), evt);
                }
            }
        });
        textDia1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarDia(textDia1.getText(), textMes1.getText(), evt);
                }
            }
        });
        textAnno2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarAño(textAnno2.getText(), evt);
                }
            }
        });
        textMes2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarMes(textMes2.getText(), evt);
                }
            }
        });
        textDia2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                } else {
                    Validations.validarDia(textDia2.getText(), textMes2.getText(), evt);
                }
            }
        });
        textAnno1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textAnno1.getText().length() != 4 && !textAnno1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "El año debe ser mayor que 1000");
                    textAnno1.requestFocus();
                }
            }
        });
        textMes1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textMes1.getText().equals("0") || textMes1.getText().equals("00")){
                    JOptionPane.showMessageDialog(null, "El mes no puede ser 0.");
                    textMes1.requestFocus();
                }
            }
        });
        textDia1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textDia1.getText().equals("0") || textDia1.getText().equals("00")){
                    JOptionPane.showMessageDialog(null, "El día no puede ser 0.");
                    textDia1.requestFocus();
                }
            }
        });
        textAnno2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textAnno2.getText().length() != 4 && !textAnno2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "El año debe ser mayor que 1000");
                    textAnno2.requestFocus();
                }
            }
        });
        textMes2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textMes2.getText().equals("0") || textMes2.getText().equals("00")){
                    JOptionPane.showMessageDialog(null, "El mes no puede ser 0.");
                    textMes2.requestFocus();
                }
            }
        });
        textDia2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(textDia2.getText().equals("0") || textDia2.getText().equals("00")){
                    JOptionPane.showMessageDialog(null, "El día no puede ser 0.");
                    textDia2.requestFocus();
                }
            }
        });
        volverAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrestamosActions.botonMenuAction();
            }
        });
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionCliente;
                String seleccionLibros;
                String[] idCliente = new String[0];
                String[] idLibros = new String[0];
                try {
                    seleccionCliente = String.valueOf(listaClientes.getSelectedValue());
                    seleccionLibros = String.valueOf(listaLibros.getSelectedValue());
                    idCliente = seleccionCliente.split(",", -1);
                    idLibros = seleccionLibros.split(",", -1);
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null,
                            "No has seleccionado algún registro");
                }
                if(!Validations.validarFecha(textAnno1.getText(), textAnno2.getText(), textMes1.getText(),
                        textMes2.getText(), textDia1.getText(), textDia2.getText())){
                    PrestamosActions.limpiar(textAnno1,textMes1,textDia1,textAnno2,textMes2,textDia2);
                    return;
                }
                PrestamosActions.guardarAction(textAnno1,textDia1,textMes1,textAnno2
                        ,textMes2,textDia2,idCliente[0],idLibros[0]);
            }
        });
        botonActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!Validations.validarFecha(textAnno1.getText(), textAnno2.getText(), textMes1.getText(),
                        textMes2.getText(), textDia1.getText(), textDia2.getText())){
                    PrestamosActions.limpiar(textAnno1,textMes1,textDia1,textAnno2,textMes2,textDia2);
                    return;
                }
                PrestamosActions.actualizarAction(listaPrestamos,textAnno1,textMes1,textDia1, textAnno2
                , textMes2, textDia2);
                PrestamosActions.llenarLista(listaPrestamos);
                PrestamosActions.limpiar(textAnno1,textMes1,textDia1,textAnno2,textMes2,textDia2);
            }
        });
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrestamosActions.limpiar(textAnno1,textMes1,textDia1,textAnno2,textMes2,textDia2);
            }
        });
        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrestamosActions.borrarAction(listaPrestamos);
            }
        });
    }


}
