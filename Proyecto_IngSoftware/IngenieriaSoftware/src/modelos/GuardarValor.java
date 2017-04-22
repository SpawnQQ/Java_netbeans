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
public class GuardarValor {
    public static Integer id_registro;
    
    GuardarValor(){
        id_registro=null;
    }
    GuardarValor(Integer id_registro){
        this.id_registro=id_registro;
    }
    public static Integer getGuardarValor(){
        return id_registro;
    }
}
