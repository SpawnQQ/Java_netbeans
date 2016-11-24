/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrezporconsola;

import java.util.Scanner;

/**
 *
 * @author elise
 */
/*color=1 es blanco
 color=0 es negro

 */
public class Movimiento {
    /*Falta poner los casos en que se encuentre con
     una pieza al realizar el mov y comer otra pieza en diagonal
     */

    public static boolean jaqueReyOP = false;

    public static int restriccionJaqueReyNegro(String s, int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        //Solo puedo mover piezas que me saquen de jaque
        String[] cadena, cadena2;
        if (jaqueReyOP == true) {
            //Salir de la amenaza moviendo al rey
            if (s.equals("K")) {
                if (AmenazaReyNegroMR(destinoFila, destinoColumna, m) == true) {
                    return -1;
                } else {
                    jaqueReyOP = false;
                    return 0;
                }
            } else {
                cadena = BusquedaCoordenadasReyNegro(m).split("");
                if (ContadorDeAmenazasReyNegro(Integer.parseInt(cadena[0]), Integer.parseInt(cadena[1]), m) < 2) {
                    //Me retorna n direccionJaqueReyNegro y contadorDeAmenazasReyNegro
                    cadena2 = DireccionJaqueReyNegro(Integer.parseInt(cadena[0]), Integer.parseInt(cadena[1]), m).split("");
                    //Comer la pieza amenazante 
                    if (!(cadena2[0].equals("c")) && (destinoFila == Integer.parseInt(cadena2[2]) && destinoColumna == Integer.parseInt(cadena2[3])) && elegirVTPieza(s, destinoFila, destinoColumna, p, m) == true) {
                        jaqueReyOP = false;
                        return 0;
                    } else {
                        if (cadena2[0].equals("c") && (destinoFila == Integer.parseInt(cadena2[2]) && destinoColumna == Integer.parseInt(cadena2[3])) && elegirVTPieza(s, destinoFila, destinoColumna, p, m) == true) {
                            jaqueReyOP = false;
                            return 0;
                        } else {
                            //Bloquiar trayectoria pieza amenazante NO SE PUEDE BLOQUEAR UN CABALLO
                            //if () {
                            //return 0;
                            //} else {
                            return -1;
                            //}
                        }
                    }
                } else {
                    return -1;
                }
            }
        } else {
            return 0;
        }
    }

    //Moviendo al rey = MR
    //Moviendo cualquier pieza = MP
    public static String BusquedaCoordenadasReyNegro(Pieza[][] m) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (m[i][j].getTipo().getNombre().equals("Kb")) {
                    return m[i][j].getTipo().getPosicion()[0] + "" + m[i][j].getTipo().getPosicion()[1];
                }
            }
        }
        return "";
    }

    public static int ContadorDeAmenazasReyNegro(int inicioFila, int inicioColumna, Pieza[][] m) {
        int contador, contador2 = 0;

        //Verificando arriba
        for (int i = inicioFila - 1; i >= 0; i--) {
            if (m[i][inicioColumna].getTipo().getNombre().equals("Qw") || m[i][inicioColumna].getTipo().getNombre().equals("Rw")) {
                contador2++;
            } else {
                if (!(m[i][inicioColumna].getTipo().getNombre().equals("VV"))) {
                    i = -1;
                }
            }
        }
        //Verificando abajo
        for (int i = inicioFila + 1; i < 8; i++) {
            System.out.println(m[i][inicioColumna].getTipo().getNombre());
            if (m[i][inicioColumna].getTipo().getNombre().equals("Qw") || m[i][inicioColumna].getTipo().getNombre().equals("Rw")) {
                contador2++;
            } else {
                if (!(m[i][inicioColumna].getTipo().getNombre().equals("VV"))) {
                    i = 8;
                }
            }
        }
        //Verificando izquierda
        for (int j = inicioColumna - 1; j >= 0; j--) {
            if (m[inicioFila][j].getTipo().getNombre().equals("Qw") || m[inicioFila][j].getTipo().getNombre().equals("Rw")) {
                contador2++;
            } else {
                if (!(m[inicioFila][j].getTipo().getNombre().equals("VV"))) {
                    j = -1;
                }
            }
        }
        //Verificando derecha
        for (int j = inicioColumna + 1; j < 8; j++) {
            if (m[inicioFila][j].getTipo().getNombre().equals("Qw") || m[inicioFila][j].getTipo().getNombre().equals("Rw")) {
                contador2++;
            } else {
                if (!(m[inicioFila][j].getTipo().getNombre().equals("VV"))) {
                    j = 8;
                }
            }
        }
        //Verificando derecha arriba
        contador = 1;
        while (inicioFila - contador >= 0 && inicioColumna + contador < 8) {
            if (m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("Bw") || m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("Qw") || (m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("Pw") && contador == 1)) {
                contador2++;
            } else {
                if (!(m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando derecha abajo
        contador = 1;
        while (inicioFila + contador < 8 && inicioColumna + contador < 8) {
            if (m[inicioFila + contador][inicioColumna + contador].getTipo().getNombre().equals("Bw") || m[inicioFila + contador][inicioColumna + contador].getTipo().getNombre().equals("Qw")) {
                contador2++;
            } else {
                if (!(m[inicioFila + contador][inicioColumna + contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando izquierda arriba
        contador = 1;
        while (inicioFila - contador >= 0 && inicioColumna - contador >= 0) {
            if (m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("Bw") || m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("Qw") || (m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("Pw") && contador == 1)) {
                contador2++;
            } else {
                if (!(m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando izquierda abajo
        contador = 1;
        while (inicioFila + contador < 8 && inicioColumna - contador >= 0) {
            if (m[inicioFila + contador][inicioColumna - contador].getTipo().getNombre().equals("Bw") || m[inicioFila + contador][inicioColumna - contador].getTipo().getNombre().equals("Qw")) {
                contador2++;
            } else {
                if (!(m[inicioFila + contador][inicioColumna - contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Caballo1 arriba izquierda
        if (inicioFila - 2 >= 0 && inicioColumna - 1 >= 0) {
            if (m[inicioFila - 2][inicioColumna - 1].getTipo().getNombre().equals("Hw")) {
                contador2++;
            }
        }
        //Caballo2 arriba derecha
        if (inicioFila - 2 >= 0 && inicioColumna + 1 < 8) {
            if (m[inicioFila - 2][inicioColumna + 1].getTipo().getNombre().equals("Hw")) {
                contador2++;
            }
        }
        //Caballo3 abajo izquierda
        if (inicioFila + 2 < 8 && inicioColumna - 1 >= 0) {
            if (m[inicioFila + 2][inicioColumna - 1].getTipo().getNombre().equals("Hw")) {
                contador2++;
            }
        }
        //Caballo4 abajo derecha
        if (inicioFila + 2 < 8 && inicioColumna + 1 < 8) {
            if (m[inicioFila + 2][inicioColumna + 1].getTipo().getNombre().equals("Hw")) {
                contador2++;
            }
        }
        //Caballo5 izquierda arriba
        if (inicioFila - 1 >= 0 && inicioColumna - 2 >= 0) {
            if (m[inicioFila - 1][inicioColumna - 2].getTipo().getNombre().equals("Hw")) {
                contador2++;
            }
        }
        //Caballo6 izquierda abajo
        if (inicioFila + 1 < 8 && inicioColumna - 2 >= 0) {
            if (m[inicioFila + 1][inicioColumna - 2].getTipo().getNombre().equals("Hw")) {
                contador2++;
            }
        }
        //Caballo7 derecha arriba
        if (inicioFila - 1 >= 0 && inicioColumna + 2 < 8) {
            if (m[inicioFila - 1][inicioColumna + 2].getTipo().getNombre().equals("Hw")) {
                contador2++;

            }
        }
        //Caballo8 derecha abajo
        if (inicioFila + 1 < 8 && inicioColumna + 2 < 8) {
            if (m[inicioFila + 1][inicioColumna + 2].getTipo().getNombre().equals("Hw")) {
                contador2++;

            }
        }
        return contador2;
    }

    public static boolean AmenazaReyBlancoMR(int destinoFila, int destinoColumna, Pieza[][] m) {
        //Verificando arriba
        int contador;
        for (int i = destinoFila; i >= 0; i--) {
            if (m[i][destinoColumna].getTipo().getNombre().equals("Qb") || m[i][destinoColumna].getTipo().getNombre().equals("Rb") || (m[i][destinoColumna].getTipo().getNombre().equals("Kb") && destinoFila == i + 1)) {
                return true;
            }
        }
        //Verificando abajo
        for (int i = destinoFila; i < 8; i++) {
            if (m[i][destinoColumna].getTipo().getNombre().equals("Qb") || m[i][destinoColumna].getTipo().getNombre().equals("Rb") || (m[i][destinoColumna].getTipo().getNombre().equals("Kb") && destinoFila == i - 1)) {
                return true;
            }
        }
        //Verificando izquierda
        for (int j = destinoColumna; j >= 0; j--) {
            if (m[destinoFila][j].getTipo().getNombre().equals("Qb") || m[destinoFila][j].getTipo().getNombre().equals("Rb") || (m[destinoFila][j].getTipo().getNombre().equals("Kb") && destinoColumna == j - 1)) {
                return true;
            }
        }
        //Verificando derecha
        for (int j = destinoColumna; j < 8; j++) {
            if (m[destinoFila][j].getTipo().getNombre().equals("Qb") || m[destinoFila][j].getTipo().getNombre().equals("Rb") || (m[destinoFila][j].getTipo().getNombre().equals("Kb") && destinoColumna == j - 1)) {
                return true;
            }
        }
        //Verificando derecha arriba
        contador = 1;
        while (destinoFila - contador >= 0 && destinoColumna + contador < 8) {
            if (m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Bb") || m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Qb") || (m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Kb") && contador == 1)) {
                return true;
            }
            contador++;
        }
        //Verificando derecha abajo
        contador = 1;
        while (destinoFila + contador < 8 && destinoColumna + contador < 8) {
            if (m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Bb") || m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Qb") || (m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Pb") && contador == 1) || (m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Kb") && contador == 1)) {
                return true;
            }
            contador++;
        }
        //Verificando izquierda arriba
        contador = 1;
        while (destinoFila - contador >= 0 && destinoColumna - contador >= 0) {
            if (m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Bb") || m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Qb") || (m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Kb") && contador == 1)) {
                return true;
            }
            contador++;
        }
        //Verificando izquierda abajo
        contador = 1;
        while (destinoFila + contador < 8 && destinoColumna - contador >= 0) {
            if (m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Bb") || m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Qb") || (m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Pb") && contador == 1) || (m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Kb") && contador == 1)) {
                return true;
            }
            contador++;
        }
        //Caballo1 arriba izquierda
        if (destinoFila - 2 >= 0 && destinoColumna - 1 >= 0) {
            if (m[destinoFila - 2][destinoColumna - 1].getTipo().getNombre().equals("Hb")) {
                return true;
            }
        }
        //Caballo2 arriba derecha
        if (destinoFila - 2 >= 0 && destinoColumna + 1 < 8) {
            if (m[destinoFila - 2][destinoColumna + 1].getTipo().getNombre().equals("Hb")) {
                return true;
            }
        }
        //Caballo3 abajo izquierda
        if (destinoFila + 2 < 8 && destinoColumna - 1 >= 0) {
            if (m[destinoFila + 2][destinoColumna - 1].getTipo().getNombre().equals("Hb")) {
                return true;
            }
        }
        //Caballo4 abajo derecha
        if (destinoFila + 2 < 8 && destinoColumna + 1 < 8) {
            if (m[destinoFila + 2][destinoColumna + 1].getTipo().getNombre().equals("Hb")) {
                return true;
            }
        }
        //Caballo5 izquierda arriba
        if (destinoFila - 1 >= 0 && destinoColumna - 2 >= 0) {
            if (m[destinoFila - 1][destinoColumna - 2].getTipo().getNombre().equals("Hb")) {
                return true;
            }
        }
        //Caballo6 izquierda abajo
        if (destinoFila + 1 < 8 && destinoColumna - 2 >= 0) {
            if (m[destinoFila + 1][destinoColumna - 2].getTipo().getNombre().equals("Hb")) {
                return true;
            }
        }
        //Caballo7 derecha arriba
        if (destinoFila - 1 >= 0 && destinoColumna + 2 < 8) {
            if (m[destinoFila - 1][destinoColumna + 2].getTipo().getNombre().equals("Hb")) {
                return true;

            }
        }
        //Caballo8 derecha abajo
        if (destinoFila + 1 < 8 && destinoColumna + 2 < 8) {
            if (m[destinoFila + 1][destinoColumna + 2].getTipo().getNombre().equals("Hb")) {
                return true;

            }
        }
        return false;
    }

    public static boolean AmenazaReyNegroMR(int destinoFila, int destinoColumna, Pieza[][] m) {

        //Verificando arriba
        int contador;
        for (int i = destinoFila; i >= 0; i--) {
            if (m[i][destinoColumna].getTipo().getNombre().equals("Qw") || m[i][destinoColumna].getTipo().getNombre().equals("Rw") || (m[i][destinoColumna].getTipo().getNombre().equals("Kw") && destinoFila == i + 1)) {
                return true;
            } else {
                if (!(m[i][destinoColumna].getTipo().getNombre().equals("VV"))) {
                    i = -1;
                }
            }
        }
        //Verificando abajo
        for (int i = destinoFila; i < 8; i++) {
            if (m[i][destinoColumna].getTipo().getNombre().equals("Qw") || m[i][destinoColumna].getTipo().getNombre().equals("Rw") || (m[i][destinoColumna].getTipo().getNombre().equals("Kw") && destinoFila == i - 1)) {
                return true;
            } else {
                if (!(m[i][destinoColumna].getTipo().getNombre().equals("VV"))) {
                    i = 8;
                }
            }
        }
        //Verificando izquierda
        for (int j = destinoColumna; j >= 0; j--) {
            if (m[destinoFila][j].getTipo().getNombre().equals("Qw") || m[destinoFila][j].getTipo().getNombre().equals("Rw") || (m[destinoFila][j].getTipo().getNombre().equals("Kw") && destinoColumna == j - 1)) {
                return true;
            } else {
                if (!(m[destinoFila][j].getTipo().getNombre().equals("VV"))) {
                    j = -1;
                }
            }
        }
        //Verificando derecha
        for (int j = destinoColumna; j < 8; j++) {
            if (m[destinoFila][j].getTipo().getNombre().equals("Qw") || m[destinoFila][j].getTipo().getNombre().equals("Rw") || (m[destinoFila][j].getTipo().getNombre().equals("Kw") && destinoColumna == j - 1)) {
                return true;
            } else {
                if (!(m[destinoFila][j].getTipo().getNombre().equals("VV"))) {
                    j = 8;
                }
            }
        }
        //Verificando derecha arriba
        contador = 1;
        while (destinoFila - contador >= 0 && destinoColumna + contador < 8) {
            if (m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Bw") || m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Qw") || (m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Pw") && contador == 1) || (m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Kw") && contador == 1)) {
                return true;
            } else {
                if (!(m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando derecha abajo
        contador = 1;
        while (destinoFila + contador < 8 && destinoColumna + contador < 8) {
            if (m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Bw") || m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Qw") || (m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Kw") && contador == 1)) {
                return true;
            } else {
                if (!(m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando izquierda arriba
        contador = 1;
        while (destinoFila - contador >= 0 && destinoColumna - contador >= 0) {
            if (m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Bw") || m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Qw") || (m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Pw") && contador == 1) || (m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Kw") && contador == 1)) {
                return true;
            } else {
                if (!(m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando izquierda abajo
        contador = 1;
        while (destinoFila + contador < 8 && destinoColumna - contador >= 0) {
            if (m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Bw") || m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Qw") || (m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Kw") && contador == 1)) {
                return true;
            } else {
                if (!(m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Caballo1 arriba izquierda
        if (destinoFila - 2 >= 0 && destinoColumna - 1 >= 0) {
            if (m[destinoFila - 2][destinoColumna - 1].getTipo().getNombre().equals("Hw")) {
                return true;
            }
        }
        //Caballo2 arriba derecha
        if (destinoFila - 2 >= 0 && destinoColumna + 1 < 8) {
            if (m[destinoFila - 2][destinoColumna + 1].getTipo().getNombre().equals("Hw")) {
                return true;
            }
        }
        //Caballo3 abajo izquierda
        if (destinoFila + 2 < 8 && destinoColumna - 1 >= 0) {
            if (m[destinoFila + 2][destinoColumna - 1].getTipo().getNombre().equals("Hw")) {
                return true;
            }
        }
        //Caballo4 abajo derecha
        if (destinoFila + 2 < 8 && destinoColumna + 1 < 8) {
            if (m[destinoFila + 2][destinoColumna + 1].getTipo().getNombre().equals("Hw")) {
                return true;
            }
        }
        //Caballo5 izquierda arriba
        if (destinoFila - 1 >= 0 && destinoColumna - 2 >= 0) {
            if (m[destinoFila - 1][destinoColumna - 2].getTipo().getNombre().equals("Hw")) {
                return true;
            }
        }
        //Caballo6 izquierda abajo
        if (destinoFila + 1 < 8 && destinoColumna - 2 >= 0) {
            if (m[destinoFila + 1][destinoColumna - 2].getTipo().getNombre().equals("Hw")) {
                return true;
            }
        }
        //Caballo7 derecha arriba
        if (destinoFila - 1 >= 0 && destinoColumna + 2 < 8) {
            if (m[destinoFila - 1][destinoColumna + 2].getTipo().getNombre().equals("Hw")) {
                return true;

            }
        }
        //Caballo8 derecha abajo
        if (destinoFila + 1 < 8 && destinoColumna + 2 < 8) {
            if (m[destinoFila + 1][destinoColumna + 2].getTipo().getNombre().equals("Hw")) {
                return true;

            }
        }
        return false;
    }

    //Arreglado verificar que si no hay una casilla obtaculizando la trayectoria al jaque
    public static boolean JaqueReyNegroMP(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        if (p.getTipo().getNombre().equals("Qw")) {
            int contador;
            //Verificando arriba
            for (int i = destinoFila; i >= 0; i--) {
                if (m[i][destinoColumna].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[i][destinoColumna].getTipo().getNombre().equals("VV"))) {
                        i = -1;
                    }
                }
            }
            //Verificando abajo
            for (int i = destinoFila; i < 8; i++) {
                if (m[i][destinoColumna].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[i][destinoColumna].getTipo().getNombre().equals("VV"))) {
                        i = 8;
                    }
                }
            }
            //Verificando izquierda
            for (int j = destinoColumna; j >= 0; j--) {
                if (m[destinoFila][j].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[destinoFila][j].getTipo().getNombre().equals("VV"))) {
                        j = -1;
                    }
                }
            }
            //Verificando derecha
            for (int j = destinoColumna; j < 8; j++) {
                if (m[destinoFila][j].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[destinoFila][j].getTipo().getNombre().equals("VV"))) {
                        j = 8;
                    }
                }
            }
            //Verificando derecha arriba
            contador = 1;
            while (destinoFila - contador >= 0 && destinoColumna + contador < 8) {
                if (m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[destinoFila - contador][destinoColumna + contador].getTipo().getNombre().equals("VV"))) {
                        contador = 8;
                    }
                }
                contador++;
            }
            //Verificando derecha abajo
            contador = 1;
            while (destinoFila + contador < 8 && destinoColumna + contador < 8) {
                if (m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[destinoFila + contador][destinoColumna + contador].getTipo().getNombre().equals("VV"))) {
                        contador = 8;
                    }
                }
                contador++;
            }
            //Verificando izquierda arriba
            contador = 1;
            while (destinoFila - contador >= 0 && destinoColumna - contador >= 0) {
                if (m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[destinoFila - contador][destinoColumna - contador].getTipo().getNombre().equals("VV"))) {
                        contador = 8;
                    }
                }
                contador++;
            }
            //Verificando izquierda abajo
            contador = 1;
            while (destinoFila + contador < 8 && destinoColumna - contador >= 0) {
                if (m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("Kb")) {
                    System.out.println("Jaque");
                    return true;
                } else {
                    if (!(m[destinoFila + contador][destinoColumna - contador].getTipo().getNombre().equals("VV"))) {
                        contador = 8;
                    }
                }
                contador++;
            }
        }
        return false;
    }

    public static String DireccionJaqueReyNegro(int inicioFila, int inicioColumna, Pieza[][] m) {
        //Verificando arriba
        int contador;
        for (int i = inicioFila - 1; i >= 0; i--) {
            if (m[i][inicioColumna].getTipo().getNombre().equals("Qw") || m[i][inicioColumna].getTipo().getNombre().equals("Rw")) {
                return "up" + i + "" + inicioColumna;
            } else {
                if (!(m[i][inicioColumna].getTipo().getNombre().equals("VV"))) {
                    i = -1;
                }
            }
        }
        //Verificando abajo
        for (int i = inicioFila + 1; i < 8; i++) {
            if (m[i][inicioColumna].getTipo().getNombre().equals("Qw") || m[i][inicioColumna].getTipo().getNombre().equals("Rw")) {
                return "do" + i + "" + inicioColumna;
            } else {
                if (!(m[i][inicioColumna].getTipo().getNombre().equals("VV"))) {
                    i = 8;
                }
            }
        }
        //Verificando izquierda
        for (int j = inicioColumna - 1; j >= 0; j--) {
            if (m[inicioFila][j].getTipo().getNombre().equals("Qw") || m[inicioFila][j].getTipo().getNombre().equals("Rw")) {
                return "le" + inicioFila + "" + j;
            } else {
                if (!(m[inicioFila][j].getTipo().getNombre().equals("VV"))) {
                    j = -1;
                }
            }
        }
        //Verificando derecha
        for (int j = inicioColumna + 1; j < 8; j++) {
            if (m[inicioFila][j].getTipo().getNombre().equals("Qw") || m[inicioFila][j].getTipo().getNombre().equals("Rw")) {
                return "ri" + inicioFila + "" + j;
            } else {
                if (!(m[inicioFila][j].getTipo().getNombre().equals("VV"))) {
                    j = 8;
                }
            }
        }
        //Verificando derecha arriba
        contador = 1;
        while (inicioFila - contador >= 0 && inicioColumna + contador < 8) {
            if (m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("Bw") || m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("Qw") || (m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("Pw") && contador == 1)) {
                return "ru" + (inicioFila - contador) + "" + (inicioColumna + contador);
            } else {
                if (!(m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando derecha abajo
        contador = 1;
        while (inicioFila + contador < 8 && inicioColumna + contador < 8) {
            if (m[inicioFila + contador][inicioColumna + contador].getTipo().getNombre().equals("Bw") || m[inicioFila + contador][inicioColumna + contador].getTipo().getNombre().equals("Qw")) {
                return "rd" + (inicioFila + contador) + "" + (inicioColumna + contador);
            } else {
                if (!(m[inicioFila + contador][inicioColumna + contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando izquierda arriba
        contador = 1;
        while (inicioFila - contador >= 0 && inicioColumna - contador >= 0) {
            if (m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("Bw") || m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("Qw") || (m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("Pw") && contador == 1)) {
                return "lu" + (inicioFila - contador) + "" + (inicioColumna - contador);
            } else {
                if (!(m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Verificando izquierda abajo
        contador = 1;
        while (inicioFila + contador < 8 && inicioColumna - contador >= 0) {
            if (m[inicioFila + contador][inicioColumna - contador].getTipo().getNombre().equals("Bw") || m[inicioFila + contador][inicioColumna - contador].getTipo().getNombre().equals("Qw")) {
                return "ld" + (inicioFila + contador) + "" + (inicioColumna - contador);
            } else {
                if (!(m[inicioFila + contador][inicioColumna - contador].getTipo().getNombre().equals("VV"))) {
                    contador = 8;
                }
            }
            contador++;
        }
        //Caballo1 arriba izquierda
        if (inicioFila - 2 >= 0 && inicioColumna - 1 >= 0) {
            if (m[inicioFila - 2][inicioColumna - 1].getTipo().getNombre().equals("Hw")) {
                return "cul" + (inicioFila - 2) + "" + (inicioColumna - 1);
            }
        }
        //Caballo2 arriba derecha
        if (inicioFila - 2 >= 0 && inicioColumna + 1 < 8) {
            if (m[inicioFila - 2][inicioColumna + 1].getTipo().getNombre().equals("Hw")) {
                return "cur" + (inicioFila - 2) + "" + (inicioColumna + 1);
            }
        }
        //Caballo3 abajo izquierda
        if (inicioFila + 2 < 8 && inicioColumna - 1 >= 0) {
            if (m[inicioFila + 2][inicioColumna - 1].getTipo().getNombre().equals("Hw")) {
                return "cdl" + (inicioFila + 2) + "" + (inicioColumna - 1);
            }
        }
        //Caballo4 abajo derecha
        if (inicioFila + 2 < 8 && inicioColumna + 1 < 8) {
            if (m[inicioFila + 2][inicioColumna + 1].getTipo().getNombre().equals("Hw")) {
                return "cdr" + (inicioFila + 2) + "" + (inicioColumna + 1);
            }
        }
        //Caballo5 izquierda arriba
        if (inicioFila - 1 >= 0 && inicioColumna - 2 >= 0) {
            if (m[inicioFila - 1][inicioColumna - 2].getTipo().getNombre().equals("Hw")) {
                return "clu" + (inicioFila - 1) + "" + (inicioColumna - 2);
            }
        }
        //Caballo6 izquierda abajo
        if (inicioFila + 1 < 8 && inicioColumna - 2 >= 0) {
            if (m[inicioFila + 1][inicioColumna - 2].getTipo().getNombre().equals("Hw")) {
                return "cld" + (inicioFila + 1) + "" + (inicioColumna - 2);
            }
        }
        //Caballo7 derecha arriba
        if (inicioFila - 1 >= 0 && inicioColumna + 2 < 8) {
            if (m[inicioFila - 1][inicioColumna + 2].getTipo().getNombre().equals("Hw")) {
                return "cru" + (inicioFila - 1) + "" + (inicioColumna + 2);

            }
        }
        //Caballo8 derecha abajo
        if (inicioFila + 1 < 8 && inicioColumna + 2 < 8) {
            if (m[inicioFila + 1][inicioColumna + 2].getTipo().getNombre().equals("Hw")) {
                return "crd" + (inicioFila + 1) + "" + (inicioColumna + 2);

            }
        }
        return "n";
    }

    public static void elegirMovPieza(String s, int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        if (s.equals("P")) {
            movPeon(destinoFila, destinoColumna, p, m);
        } else {
            if (s.equals("H")) {
                movCaballo(destinoFila, destinoColumna, p, m);
            } else {
                if (s.equals("R")) {
                    movTorre(destinoFila, destinoColumna, p, m);
                } else {
                    if (s.equals("B")) {
                        movAlfil(destinoFila, destinoColumna, p, m);
                    } else {
                        if (s.equals("Q")) {
                            movReina(destinoFila, destinoColumna, p, m);
                        } else {
                            if (s.equals("K")) {
                                movRey(destinoFila, destinoColumna, p, m);
                            }
                        }
                    }
                }
            }
        }
    }

    public static int TransformarCoordenadasFila(String s) {
        if (Integer.parseInt(s) == 1) {
            return 7;
        }
        if (Integer.parseInt(s) == 2) {
            return 6;
        }
        if (Integer.parseInt(s) == 3) {
            return 5;
        }
        if (Integer.parseInt(s) == 4) {
            return 4;
        }
        if (Integer.parseInt(s) == 5) {
            return 3;
        }
        if (Integer.parseInt(s) == 6) {
            return 2;
        }
        if (Integer.parseInt(s) == 7) {
            return 1;
        }
        if (Integer.parseInt(s) == 8) {
            return 0;
        }
        return -1;
    }

    public static int TransformarCoordenadasColumna(String s) {
        if (s.equals("a")) {
            return 0;
        }
        if (s.equals("b")) {
            return 1;
        }
        if (s.equals("c")) {
            return 2;
        }
        if (s.equals("d")) {
            return 3;
        }
        if (s.equals("e")) {
            return 4;
        }
        if (s.equals("f")) {
            return 5;
        }
        if (s.equals("g")) {
            return 6;
        }
        if (s.equals("h")) {
            return 7;
        }
        return -1;
    }

    public static void movPeon(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        Tipo t = new Tipo();
        /*Peon Negro*/
        if (p.getColor() == 1) {
            if (p.getTipo().getPosicion()[0] == 1) {
                //Peon al paso
                if (p.getTipo().getPosicion()[0] + 2 == destinoFila && p.getTipo().getPosicion()[1] == destinoColumna && m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV") && m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV")) {
                    m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1]].setColor(1);
                    t.setNombre("Pb");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1]].setTipo(t);
                    //Ahora cambiamos la casilla que avanzo el peon y la dejamos vacia
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(destinoFila - 2, destinoColumna);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Movimiento simple peon
                    if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] == destinoColumna && m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV")) {
                        m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1]].setColor(1);
                        t.setNombre("Pb");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1]].setTipo(t);
                        //Ahora cambiamos la casilla que avanzo el peon y la dejamos vacia
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(destinoFila - 1, destinoColumna);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Devorar pieza negra al lado diagonal izquierdo
                        if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 1].getColor() == 0) {
                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 1].setColor(1);
                            t.setNombre("Pb");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(destinoFila - 1, destinoColumna + 1);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            System.out.println("Ñam Ñam");

                        } else {
                            //Devorar pieza negra al lado diagonal derecho
                            if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 1].getColor() == 0) {
                                m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 1].setColor(1);
                                t.setNombre("Pb");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(destinoFila - 1, destinoColumna - 1);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                System.out.println("Ñam Ñam");

                            } else {
                                System.out.println("Movimiento no permitido");
                                Scanner leerMov = new Scanner(System.in);
                                System.out.println("Ejecute un movimiento valido, jugador 1 (Pasar turno presione 9)");
                                String posicionDestino = leerMov.nextLine();
                                String[] cadenaSeparada2 = posicionDestino.split("");
                                if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                    movPeon(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                }
                            }
                        }
                    }
                }

            } else {
                //Movimiento simple peon
                if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] == destinoColumna && m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV")) {
                    m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1]].setColor(1);
                    t.setNombre("Pb");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1]].setTipo(t);
                    //Ahora cambiamos la casilla que avanzo el peon y la dejamos vacia
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(destinoFila - 1, destinoColumna);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                } else {
                    //Devorar pieza negra al lado diagonal izquierdo
                    if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 1].getColor() == 0) {
                        m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 1].setColor(1);
                        t.setNombre("Pb");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(destinoFila - 1, destinoColumna + 1);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        System.out.println("Ñam Ñam");
                    } else {
                        //Devorar pieza negra al lado diagonal derecho
                        if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 1].getColor() == 0) {
                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 1].setColor(1);
                            t.setNombre("Pb");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(destinoFila - 1, destinoColumna - 1);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            System.out.println("Ñam Ñam");
                        } else {
                            //Aca no deberia avanzar el peon, ya que se encontro con una pieza negra de frente
                            System.out.println("Movimiento no permitido");
                            Scanner leerMov = new Scanner(System.in);
                            System.out.println("Ejecute un movimiento valido, jugador 1(Pasar turno presione 9)");
                            String posicionDestino = leerMov.nextLine();
                            String[] cadenaSeparada2 = posicionDestino.split("");
                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                movPeon(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                            }
                        }
                    }
                }
            }

        } else {
            /*Peon Blanco*/
            if (p.getTipo().getPosicion()[0] == 6) {
                //Peon al pazo
                if (p.getTipo().getPosicion()[0] - 2 == destinoFila && p.getTipo().getPosicion()[1] == destinoColumna && m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV") && m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV")) {
                    m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1]].setColor(0);
                    t.setNombre("Pw");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1]].setTipo(t);
                    //Ahora cambiamos la casilla que avanzo el peon y la dejamos vacia
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(destinoFila + 2, destinoColumna);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Movimiento simple peon
                    if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] == destinoColumna && m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV")) {
                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1]].setColor(0);
                        t.setNombre("Pw");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1]].setTipo(t);
                        //Ahora cambiamos la casilla que avanzo el peon y la dejamos vacia
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(destinoFila + 1, destinoColumna);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Devorar pieza blanca al lado diagonal izquierdo
                        if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 1].getColor() == 1) {
                            m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 1].setColor(0);
                            t.setNombre("Pw");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(destinoFila + 1, destinoColumna + 1);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            System.out.println("Ñam Ñam");

                        } else {
                            //Devorar pieza blanca al lado diagonal derecho
                            if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 1].getColor() == 1) {
                                m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 1].setColor(0);
                                t.setNombre("Pw");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(destinoFila + 1, destinoColumna - 1);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                System.out.println("Ñam Ñam");

                            } else {
                                System.out.println("Movimiento no permitido");
                                Scanner leerMov = new Scanner(System.in);
                                System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                                String posicionDestino = leerMov.nextLine();
                                String[] cadenaSeparada2 = posicionDestino.split("");
                                if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                    movPeon(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                }
                            }
                        }
                    }
                }

            } else {
                //Movimiento simple peon
                if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] == destinoColumna && m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1]].getTipo().getNombre().equals("VV")) {
                    m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1]].setColor(0);
                    t.setNombre("Pw");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1]].setTipo(t);
                    //Ahora cambiamos la casilla que avanzo el peon y la dejamos vacia
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(destinoFila + 1, destinoColumna);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                } else {
                    //Devorar pieza blanca al lado diagonal izquierdo
                    if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 1].getColor() == 1) {
                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 1].setColor(0);
                        t.setNombre("Pw");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(destinoFila + 1, destinoColumna + 1);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        System.out.println("Ñam Ñam");
                    } else {
                        //Devorar pieza blanca al lado diagonal derecho
                        if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 1].getColor() == 1) {
                            m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 1].setColor(0);
                            t.setNombre("Pw");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(destinoFila + 1, destinoColumna - 1);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            System.out.println("Ñam Ñam");
                        } else {
                            //Aca no deberia avanzar el peon, ya que se encontro con una pieza negra de frente
                            System.out.println("Movimiento no permitido");
                            Scanner leerMov = new Scanner(System.in);
                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                            String posicionDestino = leerMov.nextLine();
                            String[] cadenaSeparada2 = posicionDestino.split("");
                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                movPeon(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                            }
                        }
                    }
                }
            }

        }
    }

    public static void movCaballo(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        Tipo t = new Tipo();
        if (p.getColor() == 0) {
            //Mov arriba, izquierda
            if (p.getTipo().getPosicion()[0] - 2 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].getColor() == 1)) {
                if (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].getColor() == 1) {
                    System.out.println("Ñam Ñam");
                }
                m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].setColor(0);
                t.setNombre("Hw");
                t.setPosicion(destinoFila, destinoColumna);
                m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(destinoFila + 2, destinoColumna + 1);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov arriba, derecha
                if (p.getTipo().getPosicion()[0] - 2 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].getColor() == 1)) {
                    if (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].getColor() == 1) {
                        System.out.println("Ñam Ñam");
                    }
                    m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].setColor(0);
                    t.setNombre("Hw");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(destinoFila + 2, destinoColumna - 1);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov abajo, izquierda
                    if (p.getTipo().getPosicion()[0] + 2 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].getColor() == 1)) {
                        if (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].getColor() == 1) {
                            System.out.println("Ñam Ñam");
                        }
                        m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].setColor(0);
                        t.setNombre("Hw");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(destinoFila - 2, destinoColumna + 1);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov abajo, derecha
                        if (p.getTipo().getPosicion()[0] + 2 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].getColor() == 1)) {
                            if (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].getColor() == 1) {
                                System.out.println("Ñam Ñam");
                            }
                            m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].setColor(0);
                            t.setNombre("Hw");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(destinoFila - 2, destinoColumna - 1);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            //Mov izquierda, arriba
                            if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] - 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].getColor() == 1)) {
                                if (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].getColor() == 1) {
                                    System.out.println("Ñam Ñam");
                                }
                                m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].setColor(0);
                                t.setNombre("Hw");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(destinoFila + 1, destinoColumna + 2);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                            } else {
                                //Mov izquierda, abajo
                                if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] - 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].getColor() == 1)) {
                                    if (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].getColor() == 1) {
                                        System.out.println("Ñam Ñam");
                                    }
                                    m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].setColor(0);
                                    t.setNombre("Hw");
                                    t.setPosicion(destinoFila, destinoColumna);
                                    m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].setTipo(t);
                                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                    //No se puede comer al rey poner condicion despues!!!!!!
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                    t.setNombre("VV");
                                    t.setPosicion(destinoFila - 1, destinoColumna + 2);
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                                } else {
                                    //Mov derecha, arriba
                                    if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] + 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].getColor() == 1)) {
                                        if (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].getColor() == 1) {
                                            System.out.println("Ñam Ñam");
                                        }
                                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].setColor(0);
                                        t.setNombre("Hw");
                                        t.setPosicion(destinoFila, destinoColumna);
                                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].setTipo(t);
                                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                        //No se puede comer al rey poner condicion despues!!!!!!
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                        t.setNombre("VV");
                                        t.setPosicion(destinoFila + 1, destinoColumna - 2);
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                                    } else {
                                        //Mov derecha, abajo
                                        if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] + 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].getColor() == 1)) {
                                            if (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].getColor() == 1) {
                                                System.out.println("Ñam Ñam");
                                            }
                                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].setColor(0);
                                            t.setNombre("Hw");
                                            t.setPosicion(destinoFila, destinoColumna);
                                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].setTipo(t);
                                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                            //No se puede comer al rey poner condicion despues!!!!!!
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                            t.setNombre("VV");
                                            t.setPosicion(destinoFila - 1, destinoColumna - 2);
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                                        } else {
                                            System.out.println("Movimiento no permitido");
                                            Scanner leerMov = new Scanner(System.in);
                                            System.out.println("Ejecute un movimiento valido, jugador 1 (Pasar turno presione 9)");
                                            String posicionDestino = leerMov.nextLine();
                                            String[] cadenaSeparada2 = posicionDestino.split("");
                                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                                movCaballo(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            //Piezas negras
            //Mov arriba, izquierda
            if (p.getTipo().getPosicion()[0] - 2 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].getColor() == 0)) {
                if (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].getColor() == 0) {
                    System.out.println("Ñam Ñam");
                }
                m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].setColor(1);
                t.setNombre("Hb");
                t.setPosicion(destinoFila, destinoColumna);
                m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(destinoFila + 2, destinoColumna + 1);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov arriba, derecha
                if (p.getTipo().getPosicion()[0] - 2 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].getColor() == 0)) {
                    if (m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].getColor() == 0) {
                        System.out.println("Ñam Ñam");
                    }
                    m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].setColor(1);
                    t.setNombre("Hb");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[p.getTipo().getPosicion()[0] - 2][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(destinoFila + 2, destinoColumna - 1);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov abajo, izquierda
                    if (p.getTipo().getPosicion()[0] + 2 == destinoFila && p.getTipo().getPosicion()[1] - 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].getColor() == 0)) {
                        if (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].getColor() == 0) {
                            System.out.println("Ñam Ñam");
                        }
                        m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].setColor(1);
                        t.setNombre("Hb");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] - 1].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(destinoFila - 2, destinoColumna + 1);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov abajo, derecha
                        if (p.getTipo().getPosicion()[0] + 2 == destinoFila && p.getTipo().getPosicion()[1] + 1 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].getColor() == 0)) {
                            if (m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].getColor() == 0) {
                                System.out.println("Ñam Ñam");
                            }
                            m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].setColor(1);
                            t.setNombre("Hb");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[p.getTipo().getPosicion()[0] + 2][p.getTipo().getPosicion()[1] + 1].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(destinoFila - 2, destinoColumna - 1);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            //Mov izquierda, arriba
                            if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] - 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].getColor() == 0)) {
                                if (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].getColor() == 0) {
                                    System.out.println("Ñam Ñam");
                                }
                                m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].setColor(1);
                                t.setNombre("Hb");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] - 2].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(destinoFila + 1, destinoColumna + 2);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                            } else {
                                //Mov izquierda, abajo
                                if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] - 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].getColor() == 0)) {
                                    if (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].getColor() == 0) {
                                        System.out.println("Ñam Ñam");
                                    }
                                    m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].setColor(1);
                                    t.setNombre("Hb");
                                    t.setPosicion(destinoFila, destinoColumna);
                                    m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] - 2].setTipo(t);
                                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                    //No se puede comer al rey poner condicion despues!!!!!!
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                    t.setNombre("VV");
                                    t.setPosicion(destinoFila - 1, destinoColumna + 2);
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                                } else {
                                    //Mov derecha, arriba
                                    if (p.getTipo().getPosicion()[0] - 1 == destinoFila && p.getTipo().getPosicion()[1] + 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].getColor() == 0)) {
                                        if (m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].getColor() == 0) {
                                            System.out.println("Ñam Ñam");
                                        }
                                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].setColor(1);
                                        t.setNombre("Hb");
                                        t.setPosicion(destinoFila, destinoColumna);
                                        m[p.getTipo().getPosicion()[0] - 1][p.getTipo().getPosicion()[1] + 2].setTipo(t);
                                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                        //No se puede comer al rey poner condicion despues!!!!!!
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                        t.setNombre("VV");
                                        t.setPosicion(destinoFila + 1, destinoColumna - 2);
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                                    } else {
                                        //Mov derecha, abajo
                                        if (p.getTipo().getPosicion()[0] + 1 == destinoFila && p.getTipo().getPosicion()[1] + 2 == destinoColumna && (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].getTipo().getNombre().equals("VV") || m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].getColor() == 0)) {
                                            if (m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].getColor() == 0) {
                                                System.out.println("Ñam Ñam");
                                            }
                                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].setColor(1);
                                            t.setNombre("Hb");
                                            t.setPosicion(destinoFila, destinoColumna);
                                            m[p.getTipo().getPosicion()[0] + 1][p.getTipo().getPosicion()[1] + 2].setTipo(t);
                                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                            //No se puede comer al rey poner condicion despues!!!!!!
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                            t.setNombre("VV");
                                            t.setPosicion(destinoFila - 1, destinoColumna - 2);
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);

                                        } else {
                                            System.out.println("Movimiento no permitido");
                                            Scanner leerMov = new Scanner(System.in);
                                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                                            String posicionDestino = leerMov.nextLine();
                                            String[] cadenaSeparada2 = posicionDestino.split("");
                                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                                movCaballo(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //Si hay una interrupcion, retornamos true (1) 
    public static int interrupcionTrayectoriaTorreArriba(int inicioFila, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = inicioFila - 1;
        while (aux > destinoFila) {
            if (m[aux][destinoColumna].getTipo().getNombre().equals("VV")) {
                aux--;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static int interrupcionTrayectoriaTorreAbajo(int inicioFila, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = inicioFila + 1;
        while (aux < destinoFila) {
            if (m[aux][destinoColumna].getTipo().getNombre().equals("VV")) {
                aux++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static int interrupcionTrayectoriaTorreIzquierda(int inicioColumna, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = inicioColumna - 1;
        while (aux > destinoColumna) {
            if (m[destinoFila][aux].getTipo().getNombre().equals("VV")) {
                aux--;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static int interrupcionTrayectoriaTorreDerecha(int inicioColumna, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = inicioColumna + 1;
        while (aux < destinoColumna) {
            if (m[destinoFila][aux].getTipo().getNombre().equals("VV")) {
                aux++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static void movTorre(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        Tipo t = new Tipo();
        if (p.getColor() == 0) {
            //Mov arriba
            if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] > destinoFila && interrupcionTrayectoriaTorreArriba(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                if (m[destinoFila][destinoColumna].getColor() == 1) {
                    System.out.println("Ñam Ñam");
                }
                m[destinoFila][destinoColumna].setColor(0);
                t.setNombre("Rw");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov abajo
                if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] < destinoFila && interrupcionTrayectoriaTorreAbajo(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                    if (m[destinoFila][destinoColumna].getColor() == 1) {
                        System.out.println("Ñam Ñam");
                    }
                    m[destinoFila][destinoColumna].setColor(0);
                    t.setNombre("Rw");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov izquierda
                    if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] > destinoColumna && interrupcionTrayectoriaTorreIzquierda(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                        if (m[destinoFila][destinoColumna].getColor() == 1) {
                            System.out.println("Ñam Ñam");
                        }
                        m[destinoFila][destinoColumna].setColor(0);
                        t.setNombre("Rw");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov derecha
                        if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] < destinoColumna && interrupcionTrayectoriaTorreDerecha(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                            if (m[destinoFila][destinoColumna].getColor() == 1) {
                                System.out.println("Ñam Ñam");
                            }
                            m[destinoFila][destinoColumna].setColor(0);
                            t.setNombre("Rw");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            System.out.println("Movimiento no permitido");
                            Scanner leerMov = new Scanner(System.in);
                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                            String posicionDestino = leerMov.nextLine();
                            String[] cadenaSeparada2 = posicionDestino.split("");
                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                movTorre(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                            }
                        }
                    }
                }
            }
        } else {
            //Torre negra
            //Mov arriba
            if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] > destinoFila && interrupcionTrayectoriaTorreArriba(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                if (m[destinoFila][destinoColumna].getColor() == 0) {
                    System.out.println("Ñam Ñam");
                }
                m[destinoFila][destinoColumna].setColor(1);
                t.setNombre("Rb");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov abajo
                if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] < destinoFila && interrupcionTrayectoriaTorreAbajo(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                    if (m[destinoFila][destinoColumna].getColor() == 0) {
                        System.out.println("Ñam Ñam");
                    }
                    m[destinoFila][destinoColumna].setColor(1);
                    t.setNombre("Rb");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov izquierda
                    if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] > destinoColumna && interrupcionTrayectoriaTorreIzquierda(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                        if (m[destinoFila][destinoColumna].getColor() == 0) {
                            System.out.println("Ñam Ñam");
                        }
                        m[destinoFila][destinoColumna].setColor(1);
                        t.setNombre("Rb");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov derecha
                        if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] < destinoColumna && interrupcionTrayectoriaTorreDerecha(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                            if (m[destinoFila][destinoColumna].getColor() == 0) {
                                System.out.println("Ñam Ñam");
                            }
                            m[destinoFila][destinoColumna].setColor(1);
                            t.setNombre("Rb");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            System.out.println("Movimiento no permitido");
                            Scanner leerMov = new Scanner(System.in);
                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                            String posicionDestino = leerMov.nextLine();
                            String[] cadenaSeparada2 = posicionDestino.split("");
                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                movTorre(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                            }
                        }
                    }
                }
            }
        }
    }

    public static int interrupcionTrayectoriaAlfilDerechaAbajo(int inicioFila, int inicioColumna, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = destinoFila - inicioFila, contador = 1;
        while (contador < aux) {
            if (m[inicioFila + contador][inicioColumna + contador].getTipo().getNombre().equals("VV")) {
                contador++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static int interrupcionTrayectoriaAlfilDerechaArriba(int inicioFila, int inicioColumna, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = inicioFila - destinoFila, contador = 1;
        while (contador < aux) {
            if (m[inicioFila - contador][inicioColumna + contador].getTipo().getNombre().equals("VV")) {
                contador++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static int interrupcionTrayectoriaAlfilIzquierdaAbajo(int inicioFila, int inicioColumna, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = destinoFila - inicioFila, contador = 1;
        while (contador < aux) {
            if (m[inicioFila + contador][inicioColumna - contador].getTipo().getNombre().equals("VV")) {
                contador++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static int interrupcionTrayectoriaAlfilIzquierdaArriba(int inicioFila, int inicioColumna, int destinoFila, int destinoColumna, Pieza[][] m) {
        int aux = inicioFila - destinoFila, contador = 1;
        while (contador < aux) {
            if (m[inicioFila - contador][inicioColumna - contador].getTipo().getNombre().equals("VV")) {
                contador++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static void movAlfil(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        Tipo t = new Tipo();
        //Alfil blanco
        if (p.getColor() == 0) {
            //Mov alfil derecha-abajo
            if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                if (m[destinoFila][destinoColumna].getColor() == 1) {
                    System.out.println("Ñam Ñam");
                }
                m[destinoFila][destinoColumna].setColor(0);
                t.setNombre("Bw");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov alfil derecha-arriba
                if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                    if (m[destinoFila][destinoColumna].getColor() == 1) {
                        System.out.println("Ñam Ñam");
                    }
                    m[destinoFila][destinoColumna].setColor(0);
                    t.setNombre("Bw");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov alfil izquierda-abajo
                    if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                        if (m[destinoFila][destinoColumna].getColor() == 1) {
                            System.out.println("Ñam Ñam");
                        }
                        m[destinoFila][destinoColumna].setColor(0);
                        t.setNombre("Bw");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov izquierda-arriba
                        if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                            if (m[destinoFila][destinoColumna].getColor() == 1) {
                                System.out.println("Ñam Ñam");
                            }
                            m[destinoFila][destinoColumna].setColor(0);
                            t.setNombre("Bw");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            System.out.println("Movimiento no permitido");
                            Scanner leerMov = new Scanner(System.in);
                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                            String posicionDestino = leerMov.nextLine();
                            String[] cadenaSeparada2 = posicionDestino.split("");
                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                movAlfil(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                            }
                        }
                    }
                }
            }
        } else {
            //Alfil negro
            //Mov alfil derecha-abajo
            if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                if (m[destinoFila][destinoColumna].getColor() == 0) {
                    System.out.println("Ñam Ñam");
                }
                m[destinoFila][destinoColumna].setColor(1);
                t.setNombre("Bb");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov alfil derecha-arriba
                if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                    if (m[destinoFila][destinoColumna].getColor() == 0) {
                        System.out.println("Ñam Ñam");
                    }
                    m[destinoFila][destinoColumna].setColor(1);
                    t.setNombre("Bb");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov alfil izquierda-abajo
                    if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                        if (m[destinoFila][destinoColumna].getColor() == 0) {
                            System.out.println("Ñam Ñam");
                        }
                        m[destinoFila][destinoColumna].setColor(1);
                        t.setNombre("Bb");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov izquierda-arriba
                        if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                            if (m[destinoFila][destinoColumna].getColor() == 0) {
                                System.out.println("Ñam Ñam");
                            }
                            m[destinoFila][destinoColumna].setColor(1);
                            t.setNombre("Bb");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            System.out.println("Movimiento no permitido");
                            Scanner leerMov = new Scanner(System.in);
                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                            String posicionDestino = leerMov.nextLine();
                            String[] cadenaSeparada2 = posicionDestino.split("");
                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                movAlfil(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                            }
                        }
                    }
                }
            }
        }
    }

    public static void movReina(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        Tipo t = new Tipo();
        if (p.getColor() == 0) {
            //Pieza blanca
            if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] > destinoFila && interrupcionTrayectoriaTorreArriba(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                if (m[destinoFila][destinoColumna].getColor() == 1) {
                    System.out.println("Ñam Ñam");
                }
                if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                    jaqueReyOP = true;
                }
                m[destinoFila][destinoColumna].setColor(0);
                t.setNombre("Qw");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov abajo
                if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] < destinoFila && interrupcionTrayectoriaTorreAbajo(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                    if (m[destinoFila][destinoColumna].getColor() == 1) {
                        System.out.println("Ñam Ñam");
                    }
                    if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                        jaqueReyOP = true;
                    }
                    m[destinoFila][destinoColumna].setColor(0);
                    t.setNombre("Qw");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov izquierda
                    if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] > destinoColumna && interrupcionTrayectoriaTorreIzquierda(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                        if (m[destinoFila][destinoColumna].getColor() == 1) {
                            System.out.println("Ñam Ñam");
                        }
                        if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                            jaqueReyOP = true;
                        }
                        m[destinoFila][destinoColumna].setColor(0);
                        t.setNombre("Qw");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov derecha
                        if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] < destinoColumna && interrupcionTrayectoriaTorreDerecha(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                            if (m[destinoFila][destinoColumna].getColor() == 1) {
                                System.out.println("Ñam Ñam");
                            }
                            if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                                jaqueReyOP = true;
                            }
                            m[destinoFila][destinoColumna].setColor(0);
                            t.setNombre("Qw");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                if (m[destinoFila][destinoColumna].getColor() == 1) {
                                    System.out.println("Ñam Ñam");
                                }
                                if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                                    jaqueReyOP = true;
                                }
                                m[destinoFila][destinoColumna].setColor(0);
                                t.setNombre("Qw");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[destinoFila][destinoColumna].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            } else {
                                //Mov alfil derecha-arriba
                                if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                    if (m[destinoFila][destinoColumna].getColor() == 1) {
                                        System.out.println("Ñam Ñam");
                                    }
                                    if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                                        jaqueReyOP = true;
                                    }
                                    m[destinoFila][destinoColumna].setColor(0);
                                    t.setNombre("Qw");
                                    t.setPosicion(destinoFila, destinoColumna);
                                    m[destinoFila][destinoColumna].setTipo(t);
                                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                    //No se puede comer al rey poner condicion despues!!!!!!
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                    t.setNombre("VV");
                                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                } else {
                                    //Mov alfil izquierda-abajo
                                    if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                        if (m[destinoFila][destinoColumna].getColor() == 1) {
                                            System.out.println("Ñam Ñam");
                                        }
                                        if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                                            jaqueReyOP = true;
                                        }
                                        m[destinoFila][destinoColumna].setColor(0);
                                        t.setNombre("Qw");
                                        t.setPosicion(destinoFila, destinoColumna);
                                        m[destinoFila][destinoColumna].setTipo(t);
                                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                        //No se puede comer al rey poner condicion despues!!!!!!
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                        t.setNombre("VV");
                                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                    } else {
                                        //Mov izquierda-arriba
                                        if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                            if (m[destinoFila][destinoColumna].getColor() == 1) {
                                                System.out.println("Ñam Ñam");
                                            }
                                            if (JaqueReyNegroMP(destinoFila, destinoColumna, p, m) == true) {
                                                jaqueReyOP = true;
                                            }
                                            m[destinoFila][destinoColumna].setColor(0);
                                            t.setNombre("Qw");
                                            t.setPosicion(destinoFila, destinoColumna);
                                            m[destinoFila][destinoColumna].setTipo(t);
                                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                            //No se puede comer al rey poner condicion despues!!!!!!
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                            t.setNombre("VV");
                                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                        } else {
                                            System.out.println("Movimiento no permitido");
                                            Scanner leerMov = new Scanner(System.in);
                                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                                            String posicionDestino = leerMov.nextLine();
                                            String[] cadenaSeparada2 = posicionDestino.split("");
                                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                                movReina(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            //Pieza negra
            if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] > destinoFila && interrupcionTrayectoriaTorreArriba(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                if (m[destinoFila][destinoColumna].getColor() == 0) {
                    System.out.println("Ñam Ñam");
                }
                m[destinoFila][destinoColumna].setColor(1);
                t.setNombre("Qb");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov abajo
                if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] < destinoFila && interrupcionTrayectoriaTorreAbajo(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                    if (m[destinoFila][destinoColumna].getColor() == 0) {
                        System.out.println("Ñam Ñam");
                    }
                    m[destinoFila][destinoColumna].setColor(1);
                    t.setNombre("Qb");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov izquierda
                    if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] > destinoColumna && interrupcionTrayectoriaTorreIzquierda(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                        if (m[destinoFila][destinoColumna].getColor() == 0) {
                            System.out.println("Ñam Ñam");
                        }
                        m[destinoFila][destinoColumna].setColor(1);
                        t.setNombre("Qb");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov derecha
                        if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] < destinoColumna && interrupcionTrayectoriaTorreDerecha(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                            if (m[destinoFila][destinoColumna].getColor() == 0) {
                                System.out.println("Ñam Ñam");
                            }
                            m[destinoFila][destinoColumna].setColor(1);
                            t.setNombre("Qb");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                if (m[destinoFila][destinoColumna].getColor() == 0) {
                                    System.out.println("Ñam Ñam");
                                }
                                m[destinoFila][destinoColumna].setColor(1);
                                t.setNombre("Qb");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[destinoFila][destinoColumna].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            } else {
                                //Mov alfil derecha-arriba
                                if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                    if (m[destinoFila][destinoColumna].getColor() == 0) {
                                        System.out.println("Ñam Ñam");
                                    }
                                    m[destinoFila][destinoColumna].setColor(1);
                                    t.setNombre("Qb");
                                    t.setPosicion(destinoFila, destinoColumna);
                                    m[destinoFila][destinoColumna].setTipo(t);
                                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                    //No se puede comer al rey poner condicion despues!!!!!!
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                    t.setNombre("VV");
                                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                } else {
                                    //Mov alfil izquierda-abajo
                                    if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                        if (m[destinoFila][destinoColumna].getColor() == 0) {
                                            System.out.println("Ñam Ñam");
                                        }
                                        m[destinoFila][destinoColumna].setColor(1);
                                        t.setNombre("Qb");
                                        t.setPosicion(destinoFila, destinoColumna);
                                        m[destinoFila][destinoColumna].setTipo(t);
                                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                        //No se puede comer al rey poner condicion despues!!!!!!
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                        t.setNombre("VV");
                                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                    } else {
                                        //Mov izquierda-arriba
                                        if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                            if (m[destinoFila][destinoColumna].getColor() == 0) {
                                                System.out.println("Ñam Ñam");
                                            }
                                            m[destinoFila][destinoColumna].setColor(1);
                                            t.setNombre("Qb");
                                            t.setPosicion(destinoFila, destinoColumna);
                                            m[destinoFila][destinoColumna].setTipo(t);
                                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                            //No se puede comer al rey poner condicion despues!!!!!!
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                            t.setNombre("VV");
                                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                        } else {
                                            System.out.println("Movimiento no permitido");
                                            Scanner leerMov = new Scanner(System.in);
                                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                                            String posicionDestino = leerMov.nextLine();
                                            String[] cadenaSeparada2 = posicionDestino.split("");
                                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                                movReina(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void movRey(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        Tipo t = new Tipo();
        if (p.getColor() == 0) {
            //Rey blanco
            //Mov arriba
            if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] > destinoFila && p.getTipo().getPosicion()[0] == destinoFila + 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                m[destinoFila][destinoColumna].setColor(0);
                t.setNombre("Kw");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov abajo
                if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] < destinoFila && p.getTipo().getPosicion()[0] == destinoFila - 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                    m[destinoFila][destinoColumna].setColor(0);
                    t.setNombre("Kw");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov izquierda
                    if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] > destinoColumna && p.getTipo().getPosicion()[1] == destinoColumna + 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                        m[destinoFila][destinoColumna].setColor(0);
                        t.setNombre("Kw");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov derecha
                        if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] < destinoColumna && p.getTipo().getPosicion()[1] == destinoColumna - 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                            m[destinoFila][destinoColumna].setColor(0);
                            t.setNombre("Kw");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            //Mov derecha arriba
                            if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == destinoColumna - p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] == destinoFila + 1 && p.getTipo().getPosicion()[1] == destinoColumna - 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                m[destinoFila][destinoColumna].setColor(0);
                                t.setNombre("Kw");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[destinoFila][destinoColumna].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            } else {
                                //Mov derecha abajo
                                if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == destinoColumna - p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] == destinoFila - 1 && p.getTipo().getPosicion()[1] == destinoColumna - 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                    m[destinoFila][destinoColumna].setColor(0);
                                    t.setNombre("Kw");
                                    t.setPosicion(destinoFila, destinoColumna);
                                    m[destinoFila][destinoColumna].setTipo(t);
                                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                    //No se puede comer al rey poner condicion despues!!!!!!
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                    t.setNombre("VV");
                                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                } else {
                                    //Mov izquierda arriba
                                    if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == p.getTipo().getPosicion()[1] - destinoColumna && p.getTipo().getPosicion()[0] == destinoFila + 1 && p.getTipo().getPosicion()[1] == destinoColumna + 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                        m[destinoFila][destinoColumna].setColor(0);
                                        t.setNombre("Kw");
                                        t.setPosicion(destinoFila, destinoColumna);
                                        m[destinoFila][destinoColumna].setTipo(t);
                                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                        //No se puede comer al rey poner condicion despues!!!!!!
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                        t.setNombre("VV");
                                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                    } else {
                                        //Mov izquierda abajo
                                        if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == p.getTipo().getPosicion()[1] - destinoColumna && p.getTipo().getPosicion()[0] == destinoFila - 1 && p.getTipo().getPosicion()[1] == destinoColumna + 1 && AmenazaReyBlancoMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 1)) {
                                            m[destinoFila][destinoColumna].setColor(0);
                                            t.setNombre("Kw");
                                            t.setPosicion(destinoFila, destinoColumna);
                                            m[destinoFila][destinoColumna].setTipo(t);
                                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                            //No se puede comer al rey poner condicion despues!!!!!!
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                            t.setNombre("VV");
                                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                        } else {
                                            System.out.println("Movimiento no permitido");
                                            Scanner leerMov = new Scanner(System.in);
                                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                                            String posicionDestino = leerMov.nextLine();
                                            String[] cadenaSeparada2 = posicionDestino.split("");
                                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                                movRey(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            //Rey negro
            //Mov arriba
            if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] > destinoFila && p.getTipo().getPosicion()[0] == destinoFila + 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                m[destinoFila][destinoColumna].setColor(1);
                t.setNombre("Kb");
                t.setPosicion(destinoFila, destinoColumna);
                m[destinoFila][destinoColumna].setTipo(t);
                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                //No se puede comer al rey poner condicion despues!!!!!!
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                t.setNombre("VV");
                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
            } else {
                //Mov abajo
                if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] < destinoFila && p.getTipo().getPosicion()[0] == destinoFila - 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                    m[destinoFila][destinoColumna].setColor(1);
                    t.setNombre("Kb");
                    t.setPosicion(destinoFila, destinoColumna);
                    m[destinoFila][destinoColumna].setTipo(t);
                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                    //No se puede comer al rey poner condicion despues!!!!!!
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                    t.setNombre("VV");
                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                } else {
                    //Mov izquierda
                    if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] > destinoColumna && p.getTipo().getPosicion()[1] == destinoColumna + 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                        m[destinoFila][destinoColumna].setColor(1);
                        t.setNombre("Kb");
                        t.setPosicion(destinoFila, destinoColumna);
                        m[destinoFila][destinoColumna].setTipo(t);
                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                        //No se puede comer al rey poner condicion despues!!!!!!
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                        t.setNombre("VV");
                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                    } else {
                        //Mov derecha
                        if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] < destinoColumna && p.getTipo().getPosicion()[1] == destinoColumna - 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                            m[destinoFila][destinoColumna].setColor(1);
                            t.setNombre("Kb");
                            t.setPosicion(destinoFila, destinoColumna);
                            m[destinoFila][destinoColumna].setTipo(t);
                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                            //No se puede comer al rey poner condicion despues!!!!!!
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                            t.setNombre("VV");
                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                        } else {
                            //Mov derecha arriba
                            if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == destinoColumna - p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] == destinoFila + 1 && p.getTipo().getPosicion()[1] == destinoColumna - 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                m[destinoFila][destinoColumna].setColor(1);
                                t.setNombre("Kb");
                                t.setPosicion(destinoFila, destinoColumna);
                                m[destinoFila][destinoColumna].setTipo(t);
                                //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                //No se puede comer al rey poner condicion despues!!!!!!
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                t.setNombre("VV");
                                t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                            } else {
                                //Mov derecha abajo
                                if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == destinoColumna - p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] == destinoFila - 1 && p.getTipo().getPosicion()[1] == destinoColumna - 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                    m[destinoFila][destinoColumna].setColor(1);
                                    t.setNombre("Kb");
                                    t.setPosicion(destinoFila, destinoColumna);
                                    m[destinoFila][destinoColumna].setTipo(t);
                                    //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                    //No se puede comer al rey poner condicion despues!!!!!!
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                    t.setNombre("VV");
                                    t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                    m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                } else {
                                    //Mov izquierda arriba
                                    if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == p.getTipo().getPosicion()[1] - destinoColumna && p.getTipo().getPosicion()[0] == destinoFila + 1 && p.getTipo().getPosicion()[1] == destinoColumna + 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                        m[destinoFila][destinoColumna].setColor(1);
                                        t.setNombre("Kb");
                                        t.setPosicion(destinoFila, destinoColumna);
                                        m[destinoFila][destinoColumna].setTipo(t);
                                        //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                        //No se puede comer al rey poner condicion despues!!!!!!
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                        t.setNombre("VV");
                                        t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                        m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                    } else {
                                        //Mov izquierda abajo
                                        if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == p.getTipo().getPosicion()[1] - destinoColumna && p.getTipo().getPosicion()[0] == destinoFila - 1 && p.getTipo().getPosicion()[1] == destinoColumna + 1 && AmenazaReyNegroMR(destinoFila, destinoColumna, m) == false && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                            m[destinoFila][destinoColumna].setColor(1);
                                            t.setNombre("Kb");
                                            t.setPosicion(destinoFila, destinoColumna);
                                            m[destinoFila][destinoColumna].setTipo(t);
                                            //Ahora comemos a la pieza negra, ojo que no se puede comer al rey!!!!!!
                                            //No se puede comer al rey poner condicion despues!!!!!!
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setColor(-1);
                                            t.setNombre("VV");
                                            t.setPosicion(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[0]);
                                            m[p.getTipo().getPosicion()[0]][p.getTipo().getPosicion()[1]].setTipo(t);
                                        } else {
                                            System.out.println("Movimiento no permitido");
                                            Scanner leerMov = new Scanner(System.in);
                                            System.out.println("Ejecute un movimiento valido, jugador 2 (Pasar turno presione 9)");
                                            String posicionDestino = leerMov.nextLine();
                                            String[] cadenaSeparada2 = posicionDestino.split("");
                                            if (Integer.parseInt(cadenaSeparada2[0]) != 9) {
                                                movRey(TransformarCoordenadasFila(cadenaSeparada2[0]), TransformarCoordenadasColumna(cadenaSeparada2[1]), p, m);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean elegirVTPieza(String s, int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        if (s.equals("Q")) {
            if (verReina(destinoFila, destinoColumna, p, m) == true) {
                return true;
            }
        }
        return false;
    }

    public static boolean verReina(int destinoFila, int destinoColumna, Pieza p, Pieza[][] m) {
        Tipo t = new Tipo();
        if (p.getColor() == 0) {

        } else {
            //Pieza negra
            if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] > destinoFila && interrupcionTrayectoriaTorreArriba(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                return true;
            } else {
                //Mov abajo
                if (p.getTipo().getPosicion()[1] == destinoColumna && p.getTipo().getPosicion()[0] < destinoFila && interrupcionTrayectoriaTorreAbajo(p.getTipo().getPosicion()[0], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                    return true;
                } else {
                    //Mov izquierda
                    if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] > destinoColumna && interrupcionTrayectoriaTorreIzquierda(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                        return true;
                    } else {
                        //Mov derecha
                        if (p.getTipo().getPosicion()[0] == destinoFila && p.getTipo().getPosicion()[1] < destinoColumna && interrupcionTrayectoriaTorreDerecha(p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                            return true;
                        } else {
                            if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                return true;
                            } else {
                                //Mov alfil derecha-arriba
                                if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna > p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == destinoColumna - p.getTipo().getPosicion()[1] && interrupcionTrayectoriaAlfilDerechaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                    return true;
                                } else {
                                    //Mov alfil izquierda-abajo
                                    if (destinoFila > p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && destinoFila - p.getTipo().getPosicion()[0] == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaAbajo(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                        return true;
                                    } else {
                                        //Mov izquierda-arriba
                                        if (destinoFila < p.getTipo().getPosicion()[0] && destinoColumna < p.getTipo().getPosicion()[1] && p.getTipo().getPosicion()[0] - destinoFila == p.getTipo().getPosicion()[1] - destinoColumna && interrupcionTrayectoriaAlfilIzquierdaArriba(p.getTipo().getPosicion()[0], p.getTipo().getPosicion()[1], destinoFila, destinoColumna, m) == 0 && (m[destinoFila][destinoColumna].getTipo().getNombre().equals("VV") || m[destinoFila][destinoColumna].getColor() == 0)) {
                                            return true;
                                        } else {
                                            System.out.println("no pertenece a su trayectoria");
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
