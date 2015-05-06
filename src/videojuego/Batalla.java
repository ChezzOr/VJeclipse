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
    public Font fuente_1 = new Font("Lucida Console", Font.BOLD, 28);
    public Image combate=DiccionarioImagenes.Singleton().imagen("ProtaDer.png");;
    public boolean Derecha=true;
    public Vec posicionP=new Vec();
    public Vec posicionE=new Vec();
    public MenuBatalla estado=MenuBatalla.Atacar;
    public boolean Siguiente=false;
    public boolean Posicion=true;
    public boolean destruido=false;
    public int anchoProta = 100;
    public int anchoEnemigo = 100;
    public int xProta = 150;
    public int ataqueEnemigo=0;
    public int defensaEnemigo=0;
    public Variables var=new Variables();
    public boolean ataque=false;
    public long inicioPersonaje;
    public long limitePersonaje=1000;
    public long inicioEnemigo;
    public long limiteEnemigo=950;
    public boolean Habilitado=false;
    public boolean jefe=false;
    public boolean isEnemigo=false;
    
    public void inicia(){
        setPosicion(VentanaJuego.Singleton().getWidth()/2, VentanaJuego.Singleton().getHeight()/2);
        inicioPersonaje=System.currentTimeMillis();
        inicioEnemigo=inicioPersonaje;
        System.out.println(Escenario.Singleton().isEnemigo()+"-"+Escenario.Singleton().isJefe());
        if(Escenario.Singleton().isEnemigo()){
        	anchoEnemigo=Escenario.Singleton().batalla().getVida();
        	ataqueEnemigo=Escenario.Singleton().batalla().getAtaque();
        	defensaEnemigo=Escenario.Singleton().batalla().getDefensa();
        	System.out.println(anchoEnemigo+"-"+ataqueEnemigo+"-"+defensaEnemigo);
        }
        if(Escenario.Singleton().isJefe()){
        	anchoEnemigo=Escenario.Singleton().bossBattle().getVida();
        	ataqueEnemigo=Escenario.Singleton().bossBattle().getAtk();
        	defensaEnemigo=Escenario.Singleton().bossBattle().getDef();
        	System.out.println(anchoEnemigo+"-"+ataqueEnemigo+"-"+defensaEnemigo);
        }
    }
    
    public void setEnemigo(Enemigos N){
    	anchoEnemigo=N.getVida();
    }
    
    public void setJefe(Jefe j){
    	anchoEnemigo=j.getVida();
    }
    
    public void setPosicion(int x, int y){
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
                    if(Escenario.Singleton().isEnemigo()){
                    	System.out.println(Escenario.Singleton().enBatalla);
                    	Escenario.Singleton().destruyeEnemigo(Escenario.Singleton().enBatalla);
                    }
                    if(Escenario.Singleton().isJefe()){
                    	Escenario.Singleton().destruyeJefe();
                    }
                    jefe=false;
                	isEnemigo=false;
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Partida1);
                }
                break;
            case Perder:
            	VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Perder);
                break;
        }
        if(Siguiente&&Habilitado){
        	Habilitado=false;
            switch(estado){
                case Atacar:
                    anchoEnemigo -= Personaje.Singleton().ataque-defensaEnemigo;
                    if(anchoEnemigo <= 0){
                        anchoEnemigo=0;
                        estado = MenuBatalla.Ganar;
                        Habilitado=true;
                    }
                    break;
                case Item:
                    break;
                case Huir:
                	jefe=false;
                	isEnemigo=false;
                	anchoProta=100;
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
        	if(jefe){
        		if(Escenario.Singleton().boss.ataque()){
        			anchoProta-=(ataqueEnemigo-Personaje.Singleton().defensa);
        		}
        		
        	}else{
        		if(ataqueEnemigo-Personaje.Singleton().defensa>0){
        			anchoProta-=(ataqueEnemigo-Personaje.Singleton().defensa);
        		}
        	}
        	
        	inicioEnemigo=System.currentTimeMillis();
        	if(anchoProta<=0){
        		estado=MenuBatalla.Perder;
        		Habilitado=true;
        	}
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
    
    public void isJefe(){
    	jefe=true;
    }
    
    public void isEnemigo(){
    	isEnemigo=true;
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
