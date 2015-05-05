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
	public enum atributo{vida, poder, especial}
	atributo tipo = null;
	Image imagenVida = DiccionarioImagenes.Singleton().imagen("hola.jpg");
	Image imagenPoder = DiccionarioImagenes.Singleton().imagen("hola.jpg");
	Image imagenEspecial = DiccionarioImagenes.Singleton().imagen("hola.jpg");
    Vec pos = null;
    public static Rectangle Imagen=null;
    int ancho = VentanaJuego.Singleton().getWidth();
    int alto = VentanaJuego.Singleton().getHeight();
    boolean recogido;
    
    public Item(int x, int y, atributo tipoAtributo) {
        pos = new Vec(x, y);
        tipo = tipoAtributo;
        recogido = false;
    }
    
    public void modificaItem( int CamX, int CamY){
        Imagen= new Rectangle(pos.getIntX()+CamX, pos.getIntY()+CamY, 100, 100);
    }
    
    
    public void dibujarItem(Graphics g, int CamX, int CamY){
        int x = pos.getIntX();
        int y = pos.getIntY();
        switch(tipo){
	        case vida:
	        	g.setColor(Color.ORANGE);
	    		break;
	    	case poder:
	    		g.setColor(Color.BLUE);
	        	break;
        	case especial:
        		g.setColor(Color.YELLOW);
            	break;
        }
    	modificaItem(CamX, CamY);
        g.fillRect(Imagen.x, Imagen.y, Imagen.width, Imagen.height);
    	//g.drawImage(imagen, x, y, null);
    }
    
    /*public Rectangle getBounds(){
        return new Rectangle(pos.getIntX(), pos.getIntY(), imagen.getWidth(null), imagen.getHeight(null));
    }*/
}
