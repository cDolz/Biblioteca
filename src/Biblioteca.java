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
        conectar();
        Formulario.createFormulario();
        /*String insert = "INSERT INTO libros VALUES (null,?,?,?)";
        String select = "SELECT * FROM libros";
        String delete = "DELETE FROM libros WHERE id = ?";
        PreparedStatement psi, pss, psd;
        ResultSet rs;
        int campo;
        boolean flag;
        int count;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
            JOptionPane.showMessageDialog(null, "La conexión si funciona :)");
            //INSERT
            psi = con.prepareStatement(insert);
            String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre del libro");
            psi.setString(1, nombre);
            String autor = JOptionPane.showInputDialog(null, "Introduzca el nombre del autor");
            psi.setString(2, autor);
            String fechapublicacion = JOptionPane.showInputDialog(null, "Introduzca la fecha de publicación");
            psi.setString(3, fechapublicacion);
            psi.execute();
            //SELECT
            pss = con.prepareStatement(select);
            rs = pss.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre2 = rs.getString("nombre");
                String autor2 = rs.getString("autor");
                String fechapublicacion2 = rs.getString("fecha_publicacion");
                System.out.print(" | " + id);
                System.out.print(" | " + nombre2);
                System.out.print(" | " + autor2);
                System.out.print(" | " + fechapublicacion2);
                System.out.println();
            }
            //DELETE
            psd = con.prepareStatement(delete);
            do {
                try {
                    String x = JOptionPane.showInputDialog(null, "Introduce el número del libro que quieras eliminar");
                    campo = Integer.parseInt(x);
                    psd.setInt(1, campo);
                    count = psd.executeUpdate();
                    if (count > 0) {
                        psd.execute();
                        flag = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Esa ID no corresponde a ningún libro");
                        flag = false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "El valor introducido es incorrecto");
                    flag = false;
                }
            } while (!flag);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La conexión no funciona :(");
        }*/
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