import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton botonALibros;
    private JButton botonAClientes;
    private JPanel panelMenu;
    static Menu menu;
    public Menu() {
        //indicamos los componentes de mi panel
        setContentPane(panelMenu);
        /*boton que llama el metodo de crear formulario para libros, cuando esto pasa el menu
        no se hace visible*/
        botonALibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibrosActions.createFormularioLibroAction();
                menu.setVisible(false);
            }
        });
        /*boton que llama el metodo de crear formulario para clientes, cuando esto pasa el menu
        no se hace visible*/
        botonAClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientesActions.createFormularioClienteAction();
                menu.setVisible(false);
            }
        });
    }
    //metodo para crear un nuevo menú, ponerlo maximizado y hacerlo visible
    public static void createMenuAction() {
        menu = new Menu();
        menu.setExtendedState(menu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
