package servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Registro;

public class Registro_servicio {
   private final String tabla = "registros";
   
   /*El método guardar(), que recibirá como parámetro una instancia de conexión y un objeto Registro. Si el objeto tiene el 
valor de id nulo, va a hacer un insert, de lo contrario un update en el registro con dicho id.*/
   
   public void guardar(Connection conexion, Registro registro) {
       try {
           PreparedStatement consulta;
           consulta = conexion.prepareStatement("INSERT INTO registro(id_registro,nombre, fecha, medida, stock) VALUES( ?, ?, ?, ?,?)");
           consulta.setInt(1, registro.getId_registro());
           consulta.setString(2, registro.getNombre());
           consulta.setString(3, registro.getFecha());
           consulta.setString(4, registro.getMedida());
           consulta.setInt(5, registro.getStock());
           consulta.executeUpdate();
           
       } catch (SQLException ex) {
           Logger.getLogger(Registro_servicio.class.getName()).log(Level.SEVERE, null, ex);
       }

   }
    /*El método eliminar() recibirá un una instancia de conexión y el objeto Registro que debe eliminar.*/
   
   public void eliminar(Connection conexion, int id_registro){
      try{
         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM registro WHERE id_registro = ?");
         consulta.setInt(1, id_registro);
         consulta.execute();
      }catch(SQLException ex){
          Logger.getLogger(Registro_servicio.class.getName()).log(Level.SEVERE, null, ex);;
      }
   }
   
}