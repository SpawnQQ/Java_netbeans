/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Ahri
 */
public class Usuario {
   private String nick_usuario;
   private String nombre;
   private String apellidos;
   private final Integer contraseña;
   private String cargo_administrativo;
   
   public Usuario() {
      this.nick_usuario = null;
      this.nombre = null;
      this.apellidos = null;
      this.contraseña = null;
      this.cargo_administrativo=null;
   }
   
   public Usuario(String nick_usuario,String nombre, String apellidos, Integer contraseña, String cargo_administrativo) {
      this.nick_usuario = nick_usuario;
      this.nombre = nombre;
      this.apellidos = apellidos;
      this.contraseña = contraseña;
      this.cargo_administrativo=cargo_administrativo;
   }
   
   public String getNick_usuario(){
       return nick_usuario;
   }
   public String getNombre(){
       return nombre;
   }
   public String getApellidos(){
       return apellidos;
   }
   public Integer getContraseña(){
       return contraseña;
   }
   public String getCargo_administrativo(){
       return cargo_administrativo;
   }
   
   public void setNick_usuario(String nick_usuario){
       this.nick_usuario=nick_usuario;
   }
   public void setNombre(String nombre){
       this.nombre=nombre;
   }
   public void setApellidos(String apellidos){
       this.apellidos=apellidos;
   }
   public void setCargo_administrativo(String cargo_administrativo){
       this.cargo_administrativo=cargo_administrativo;
   }
   
   public String toString(){
       return nick_usuario+"    "+nombre+"  "+apellidos+"   "+cargo_administrativo;
   }
}
