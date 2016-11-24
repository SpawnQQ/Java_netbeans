package ajedrezporconsola;

/**
 *
 * @author elise
 */
/*La clase Tipo tiene como atributos nombre y posicion, tiene metodos
para conseguir el nombre y posicion y para modificar la posicion
*/

public class Tipo {
    private String nombre;
    private int[] posicion={0,1};

    public String getNombre(){
        return nombre;
    }
    public int[] getPosicion(){
        return posicion;
    }    
       
    public void setPosicion(int i, int j){
        this.posicion[0]=i;
        this.posicion[1]=j;
    }
    public void setNombre(String s){
        this.nombre=s;
    }
    
    public Tipo(Tipo t){
        this.nombre=t.nombre;
        this.posicion[0]=t.posicion[0];
        this.posicion[1]=t.posicion[1];
    }    
    
    public Tipo(){
        nombre= new String();
        this.posicion[0]=0;
        this.posicion[1]=0;
        
    }
    public String toString(){
       String s=nombre+","+posicion[0]+posicion[1];
       return s;
    } 
    
}
    
