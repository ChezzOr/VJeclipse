/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

/**
 *
 * @author Christopher
 */
public class Enemigos {
    public static Rectangle enemigo=null;
    public static boolean batalla=false;
    private static int vida=0;
    public static int ataque=0;
    public static int defensa=0;
    public static Rectangle Imagen=null;
    public int x,y,ancho,alto;
    private boolean vivo;
    public static boolean colision=false;
    public static String enCombate;
    public static Batalla batallaP= VentanaJuego.Singleton().batallaActual();
    
    public void  creaEnemigo(int x, int y, int ancho, int alto){
        this.x=x;
        this.y=y;
        this.ancho=ancho;
        this.alto=alto;
        Imagen=new Rectangle(x,y,ancho,alto);
        setVida(100);
        ataque=10;
        defensa=5;
        vivo=true;
        System.out.println(x+"-"+y+"-"+ancho+"-"+alto);
    }
    
    public void modificaEnemigo( int CamX, int CamY){
        Imagen= new Rectangle(x+CamX,y+CamY,100,100);
    } 
    
    public void dibujaEnemigo(Graphics g,int CamX, int CamY){
        //modificaEnemigos(actual, CamX, CamY);
    	if(!vivo){
    		return;
    	}
    	modificaEnemigo(CamX,CamY);
        g.setColor(Color.red);
        g.fillRect(Imagen.x, Imagen.y, Imagen.width,Imagen.height);
        //g.fillRect(R.Imagen.x,R.Imagen.y,R.Imagen.width,R.Imagen.height);
    }
    
    public boolean colision(){
        if(Imagen.intersects(Personaje.Singleton().fronteras())){
            return true;
        }
        return false;
    }
    
    public boolean Batalla(){
        return batalla;
    }
    
    public void finBatalla(){
        vivo=false;
        batalla=false;
    }

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public static int getVida() {
		return vida;
	}

	public static void setVida(int vida) {
		Enemigos.vida = vida;
	}
    
    
   
}
