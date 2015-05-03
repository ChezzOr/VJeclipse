/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

/**
 *
 * @author Danny
 */
public class Cronometro {
    
    long retardo;
    long arranque;

    public Cronometro(int frames) {
       retardo=(1000/30)*frames;
       arranque=System.currentTimeMillis();
    }

    public boolean esTiempo() {
        long ahora = System.currentTimeMillis();
        if((ahora-arranque) > retardo)
        {
            arranque = ahora;
            return true;

        }
        return false;
    }
}
