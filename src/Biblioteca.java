import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Biblioteca {

    public static Connection getCon() {
        return con;
    }

    private static Connection con;

    public static void main(String[] args) {
        conectar();
        Menu.createMenuAction();
    }
    private static void conectar() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            JOptionPane.showMessageDialog(null, "Conexion establecida");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fallo en la conexion");
        }
    }
}