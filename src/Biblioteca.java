import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Biblioteca {

    public static Connection getCon() {
        return con;
    }
    private static Connection con;

    public static void main(String[] args) {
        //Si conecta arranca, si no no se ejecuta nada.
        if (conectar()){
            Menu.createMenuAction();
        }
    }
    //Método para conectar con metodo de Driver Manager y la ruta de mi BBDD
    private static boolean conectar() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            JOptionPane.showMessageDialog(null, "Conexion establecida");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}