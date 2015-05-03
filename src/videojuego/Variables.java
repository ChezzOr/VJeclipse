/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

import java.awt.Rectangle;
import java.util.Stack;

/**
 *
 * @author Christopher
 */
public class Variables {
    public Stack <Rectangle> enemigos=new Stack();
    public Stack <Boolean> Vivos=new Stack();
    public boolean destruir=false;

    public boolean isDestruir() {
        return destruir;
    }

    public void setDestruir(boolean destruir) {
        this.destruir = destruir;
    }
    
    public Stack<Rectangle> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(Stack<Rectangle> enemigos) {
        this.enemigos = enemigos;
    }

    public Stack<Boolean> getVivos() {
        return Vivos;
    }

    public void setVivos(Stack<Boolean> Vivos) {
        this.Vivos = Vivos;
    }
    
    
}
