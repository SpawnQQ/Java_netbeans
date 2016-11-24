package servicios;
import java.sql.*;
public class Conexion {
   private static Connection cnx = null;
   public Connection obtenerConexion() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:mysql://localhost/bodega_farmacia_inc","root","");
         } catch (ClassNotFoundException |SQLException e) {
             System.out.printf("error de conexion");
      }
      return cnx;
   }
 
}
