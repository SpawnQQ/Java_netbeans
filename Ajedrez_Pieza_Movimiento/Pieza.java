package ajedrezporconsola;

/**
 *
 * @author elise
 */

/*Clase Pieza tiene atributos color y tipo de pieza, basicamente
En esta clase se implementa las siguientes metodos:
-Modificar color y tipo de pieza (revisar si es necesario)
-Conseguir el color y tipo de cada pieza (para mani)*/

public class Pieza {
    private int color;
    private Tipo tipo;
    private boolean tocadoPorSegundaVez;
    /*Metodo Get */
    
    public int getColor(){
        return color;
    }
    public Tipo getTipo(){
        return tipo;
    }
    public boolean getTocadoPorSegundaVez(){
        return tocadoPorSegundaVez;
    }
    /*Metodo Set nose si son necesarios*/
    
    public void setColor(int c){
        this.color=c;
    }
    public void setTipo(Tipo t){
        this.tipo=new Tipo(t);
    }    
    public void setTocadoPorSegundaVez(){
        this.tocadoPorSegundaVez=true;
    }
    public Pieza(){
        color=0;
        tipo=new Tipo();
        tocadoPorSegundaVez=false;
        
    }    
    public Pieza(Pieza p){
        color=p.color;
        tipo=p.tipo;
    }
    /*Listar Pieza*/
    
    public String toString(){
       String s=color+","+tipo.toString();
       return s;
    }    
  
}
