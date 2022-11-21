import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton botonALibros;
    private JButton botonAClientes;
    private JPanel panelMenu;
    static Menu menu;
    public Menu() {
        setContentPane(panelMenu);
        botonALibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LibrosActions.createFormularioLibroAction();
                menu.setVisible(false);
            }
        });
        botonAClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientesActions.createFormularioClienteAction();
                menu.setVisible(false);
            }
        });
    }

    public static void createMenuAction() {
        menu = new Menu();
        menu.setExtendedState(menu.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
