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
    private int vida=0;
    private int ataque=0;
    private int defensa=0;
    private int exp=0;
    private Rectangle Imagen=null;
    public int x,y,ancho,alto;
    private boolean vivo;
    public static boolean colision=false;
    
    public void  creaEnemigo(int x, int y, int ancho, int alto){
        this.x=x;
        this.y=y;
        this.ancho=ancho;
        this.alto=alto;
        Imagen=new Rectangle(x,y,ancho,alto);
        vida=100;
        ataque=17;
        defensa=10;
        exp=100;
        vivo=true;
        System.out.println(x+"-"+y+"-"+ancho+"-"+alto);
    }
    
    public void modificaEnemigo( int CamX, int CamY){
        Imagen= new Rectangle(x+CamX,y+CamY,ancho,alto);
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

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public Rectangle getImagen() {
		return Imagen;
	}

	public void setImagen(Rectangle imagen) {
		Imagen = imagen;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
   
}
