/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareasimulacion;

import java.awt.Graphics;
import java.awt.Color;

/**
 *
 * @author Ahri
 */
public class Dibujo {
    public static void hacerCuadrado(Graphics g,int x, int y, int ancho){
            g.drawRect(x,y,ancho,ancho);
    }
    public static void hacerCircunferencia(Graphics g, int x,int y,int r, int p ){
        g.drawOval (x, y, r,p);
    
    }
    public static void hacerPunto(Graphics g,int x, int y,int sinCorte){
         if(sinCorte==1){
        g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.BLUE);
        }
         g.drawRect(x,y,1,1);
    }
    public static void hacerRecta(Graphics g,int x1, int y1,int x2,int y2,int sinCorte){
        if(sinCorte==1){
        g.setColor(Color.RED);
        }
        else{
            g.setColor(Color.BLUE);
        }
        g.drawLine(x1,y1,x2,y2);
        
    
    }
    public static void hacerRectasParalelas(Graphics g,int x, int y, int largo){
            g.drawRect(x,y,largo,1);
    }
    public static int contarPuntosCirculo(int x, int y){
    int aux1,aux2,aux3;
    aux1=(x-200)*(x-200);
    aux2=(y-200)*(y-200);
    aux3=aux1+aux2;
    if(aux3<=40000){
        return 1;
    }
    return 0;
    
    }
            
}
