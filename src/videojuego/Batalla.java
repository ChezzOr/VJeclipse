/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Christopher
 */

public class Batalla {
    public enum MenuBatalla{Atacar,Item,Huir,Reposo, Ganar, Perder}
    public enum Destruido{Verdadero,Falso}
    public Rectangle enemigo=null;
    public Personaje personaje=new Personaje();
    Font fuente_1 = new Font("Lucida Console", Font.BOLD, 28);
    Image combate=DiccionarioImagenes.Singleton().imagen("ProtaDer.png");;
    boolean Derecha=true;
    Vec posicionP=new Vec();
    Vec posicionE=new Vec();
    MenuBatalla estado=MenuBatalla.Atacar;
    boolean Siguiente=false;
    boolean Posicion=true;
    boolean destruido=false;
    int anchoProta = 100;
    int anchoEnemigo = 100;
    int xProta = 150;
    Variables var=new Variables();
    boolean ataque=false;
    long inicioPersonaje;
    long limitePersonaje=1000;
    long inicioEnemigo;
    long limiteEnemigo=950;
    boolean Habilitado=false;
    
    public void inicia(){
        setPosicion(VentanaJuego.Singleton().getWidth()/2, VentanaJuego.Singleton().getHeight()/2);
        inicioPersonaje=System.currentTimeMillis();
        inicioEnemigo=inicioPersonaje;
    }
    
    void setPosicion(int x, int y){
        posicionP = new Vec(x-200,y);
        posicionE = new Vec(x+200,y);
    }
    
    public MenuBatalla estado(){
        return estado;
    }
    
    public void menuBatalla(Graphics g){
        g.setColor(Color.yellow);
        g.fillRoundRect(20, 450, 750, 120, 20, 20);
        g.setColor(Color.GRAY);
        g.setFont(fuente_1);
        g.drawString("Objeto", 300, VentanaJuego.Singleton().getHeight()-80);
        g.drawString("Atacar",150,  VentanaJuego.Singleton().getHeight()-80);
        g.drawString("Huir",450,  VentanaJuego.Singleton().getHeight()-80);
        g.setColor(Color.cyan);
        switch(estado){
            case Atacar:
                g.drawString("Atacar",150,  VentanaJuego.Singleton().getHeight()-80);
                break;
            case Item: 
                g.drawString("Objeto", 300, VentanaJuego.Singleton().getHeight()-80);
                break;
            case Huir:
                g.drawString("Huir",450,  VentanaJuego.Singleton().getHeight()-80);
                break;
            case Reposo:

                break;
            case Ganar:
                g.setColor(Color.BLUE);
                g.fillRect(0, 0,VentanaJuego.Singleton().getWidth() , VentanaJuego.Singleton().getHeight());
                if(Siguiente){
                    estado=MenuBatalla.Reposo;
                    Escenario.Singleton().destruyeEnemigo(Escenario.Singleton().enBatalla);
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Partida1);
                }
                break;
            case Perder:
                break;
        }
        if(Siguiente&&Habilitado){
        	Habilitado=false;
            switch(estado){
                case Atacar://poner temporizador de ataque
                    anchoEnemigo -= 26;
                    if(anchoEnemigo <= 0){
                        anchoEnemigo=0;
                        estado = MenuBatalla.Ganar;
                        Habilitado=true;
                    }
                    break;
                case Item:
                    break;
                case Huir:
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Partida1);
                    break;
                case Reposo:
                    break;
            }
        }
        Siguiente=false;
    }
    
    public void dibujaBatalla(Graphics g){
        if(Posicion){
            inicia();
            Posicion=false;
            System.out.println(Escenario.Singleton().batalla().Imagen);
            anchoEnemigo= Escenario.Singleton().batalla().getVida();
        }
        g.setColor(Color.black);
        g.fillRect(0, 0, VentanaJuego.Singleton().getWidth(), VentanaJuego.Singleton().getHeight());
        //System.out.println(inicio+"-"+System.currentTimeMillis()+"-"+(System.currentTimeMillis()-inicio));
        if(Habilitado || System.currentTimeMillis()-inicioPersonaje>limitePersonaje){
        	Habilitado=true;
        	menuBatalla(g);
        	inicioPersonaje=System.currentTimeMillis();
        }
        //System.out.println(inicioEnemigo+"-"+System.currentTimeMillis()+"-"+(System.currentTimeMillis()-inicioEnemigo)+"-"+limiteEnemigo);
        if(System.currentTimeMillis()-inicioEnemigo > limiteEnemigo && anchoEnemigo>0){
        	//System.out.println("entra");
        	anchoProta-=(20-Personaje.Singleton().defensa);
        	inicioEnemigo=System.currentTimeMillis();
        }
        g.drawImage(combate, posicionP.getIntX(), posicionP.getIntY(), null);
        g.setColor(Color.cyan);
        g.fillRect(posicionE.getIntX(), posicionE.getIntY(), 100, 100);
        g.setColor(Color.red);
        g.fillRect(xProta, 275, anchoProta, 10);
        g.fillRect(posicionE.getIntX(), 275,anchoEnemigo, 10);
        g.setColor(Color.BLUE);
        g.fillRect(xProta, 285, (int)(System.currentTimeMillis()-inicioPersonaje)/10, 10);
        g.fillRect(posicionE.getIntX(), 285, (int)(System.currentTimeMillis()-inicioEnemigo)/10, 10);
    }
    
    public void comando(KeyEvent e){
    	if(Habilitado){
		    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
		        switch (estado){
		            case Atacar:
		                estado=MenuBatalla.Item;
		                break;
		            case Item: 
		                estado=MenuBatalla.Huir;
		                break;
		            case Huir: 
		                estado=MenuBatalla.Atacar;
		                break;
		            case Reposo:
		                estado=MenuBatalla.Atacar;
		                break;
		        }
		    }
		    if(e.getKeyCode()==KeyEvent.VK_LEFT){
		        switch (estado){
		            case Reposo:
		                estado=MenuBatalla.Huir;
		                break;
		            case Atacar: 
		                estado=MenuBatalla.Huir;
		                break;
		            case Item: 
		                estado=MenuBatalla.Atacar;
		                break;
		            case Huir:
		                estado=MenuBatalla.Item;
		                break;
		        }
		    }
		    if(e.getKeyCode()==KeyEvent.VK_ENTER){
		        Siguiente=true;
		    }
    	}
    }
    
}
