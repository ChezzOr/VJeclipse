/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Danny
 */
public class Item {
    Image imagen = null;
    private String url;
    Vec pos = null;
    int ancho = VentanaJuego.Singleton().getWidth();
    int alto = VentanaJuego.Singleton().getHeight();
    boolean recogido;

    public Item(int x, int y, String url) {
        pos = new Vec(x, y);
        this.url = url;
        recogido = false;
        imagen = DiccionarioImagenes.Singleton().imagen(url);
    }
    
    public void dibujarItem(){
        //int x = pos.getIntX();
        //int y = pos.getIntY();
        System.out.println("ksdanfads");
        //g.drawImage(imagen, x, y, null);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(pos.getIntX(), pos.getIntY(), imagen.getWidth(null), imagen.getHeight(null));
    }
}
