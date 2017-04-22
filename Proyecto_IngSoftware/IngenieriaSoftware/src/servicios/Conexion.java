package servicios;
import java.sql.*;
public class Conexion {
   private static Connection cnx = null;
   public Connection obtenerConexion() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx= DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bodega","nimal","changos12");
         } catch (ClassNotFoundException |SQLException e) {
             System.out.printf("error de conexion");
      }
      return cnx;
   }
 
}
