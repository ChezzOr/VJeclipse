/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;


/**
 *
 * @author Danny
 */
public class Escenario {

    
    public enum Mapa{Mastrum, Bosque}
    static Escenario instancia=null;
    public static int alto=VentanaJuego.Singleton().getHeight();
    public static int ancho=VentanaJuego.Singleton().getWidth();
    public static Image mapaMastrum=DiccionarioImagenes.Singleton().imagen("Mastrum.png");
    public static Image mapaBosque=DiccionarioImagenes.Singleton().imagen("Bosque.png");
    public static Rectangle Map= new Rectangle(-alto,-ancho,ancho*2,alto*2);
    public static Personaje principal= new Personaje();
    public static Mapa actual= Mapa.Mastrum;
    public static Bounds limites= new Bounds();
    public static boolean Colisionadores=true;
    public static boolean fueraCamaraX=false;
    public static boolean fueraCamaraY=false;
    public static int desp=5;
    public static int camaraX=-ancho/2;
    public static int camaraY=-alto/2;
    boolean fueraL=false;
    boolean fueraR=false;
    boolean fueraU=false;
    boolean fueraD=false;
    boolean activo;
    boolean colisionEnemigo=false;
    private enum mov{cam,per,none}
    public enum dir{X,Y,none}
    public enum col{U,D,L,R,none}
    public static dir tempdir=dir.none;
    public static col dirCol=col.none;
    public static Vec pasada=new Vec();
    public static int posicionEnemigo=0;
    public static Enemigos enemigos[]= new Enemigos[10];
    public static Item item;
    public static Batalla batalla=VentanaJuego.Singleton().batallaActual();
    public static int enBatalla;
    
    public static Escenario Singleton(){
        if(instancia==null){
            instancia= new Escenario();
            instancia.inicia();
        }
        return instancia;
    }
    
    public void inicia(){
        Personaje.Singleton().setPosicion(VentanaJuego.Singleton().getWidth()/2,
        VentanaJuego.Singleton().getHeight()/2);
        Personaje.Singleton().setEstado(Personaje.Estados.avanzarAbajo);
        actual=Mapa.Mastrum;
        item = new Item(VentanaJuego.Singleton().getWidth()/2+100, VentanaJuego.Singleton().getHeight()/2+100, "");
        
        if(actual == Mapa.Mastrum){
            Musica.Singleton().correrMusica("Mastrum.wav");
        }
    }
    
    public Mapa Actual(){
        return actual;
    }
    
    public void camara(int x, int y){
        camaraX+=x;
        camaraY+=y;
        /*System.out.println(mapaMastrum.getWidth(null));
        System.out.println(mapaMastrum.getHeight(null));
        System.out.println(Personaje.Singleton().getX());
        System.out.println(Personaje.Singleton().getY());*/
    }
    
    boolean isMostrarColisionadores() {
        return Colisionadores;
    }
    
    private boolean inicio=true;
    
    public void dibujaEscenario(Graphics g){
        
        if(inicio){
        	int a =300;
        	int b= 200;
            for(int i=0;i<4;i++){
            	enemigos[i]= new Enemigos();
                enemigos[i].creaEnemigo(a, b, 100, 100);
                a+=100;
                b+=250;
            } 
            inicio=false;
        }
        activo=false;
        switch(actual){
            case Mastrum:
                g.setColor(Color.BLUE);
                g.fillRect(-800,-600,2000,2000);
                g.setColor(Color.GRAY);
                g.drawImage(mapaMastrum, camaraX, camaraY, mapaMastrum.getWidth(null), mapaMastrum.getHeight(null), null);
                Personaje.Singleton().dibujarPersonaje(g);
                limites.dibujaLimites(g,camaraX,camaraY);
                //item.dibujarItem();
                Personaje.Singleton().movimiento(dirCol,false);
                for(int i=0;i<4;i++){
                	if(enemigos[i].isVivo()){
                		enemigos[i].dibujaEnemigo(g, camaraX, camaraY);
                		colisionEnemigos(enemigos[i],i);
                	}
                }
                Rectangle irBosque = new Rectangle(mapaMastrum.getWidth(null) - 300 + camaraX, 320 + camaraY, 200, 5);
                if(Personaje.Singleton().colisiona(irBosque)){
                	limites.cambiaLimite();
                	actual = Mapa.Bosque;
                }
                break;
            case Bosque:
            	g.setColor(Color.BLUE);
                g.fillRect(-800,-600,2000,2000);
                g.drawImage(mapaBosque, camaraX, camaraY, mapaMastrum.getWidth(null), mapaMastrum.getHeight(null), null);
                limites.dibujaLimites(g, camaraX, camaraY);
                Personaje.Singleton().dibujarPersonaje(g);
                Personaje.Singleton().movimiento(dirCol,false);
                break;
        }
        
    }
    
    public void colisionEnemigos(Enemigos N,int a){
    	if(N.colision()){
    		colisionEnemigo=true;
    		enBatalla=a;
    	}
    	if(colisionEnemigo){
            switch(Personaje.Singleton().direccion){
                case avanzarIzquierda:
                    Personaje.Singleton().mueve(2,0);
                    break;
                case avanzarDerecha:
                    Personaje.Singleton().mueve(-2,0);
                    break;
                case avanzarArriba:
                    Personaje.Singleton().mueve(0,2);
                    break;
                case avanzarAbajo:
                    Personaje.Singleton().mueve(0,-2);
                    break;
                default:
                    break;
            }
            colisionEnemigo=false;
            VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Batalla);
        }
    }
    
    public Enemigos batalla(){
    	return enemigos[enBatalla];
    }
    
    public void destruyeEnemigo(){
    	System.out.println(enBatalla);
    	enemigos[3].setVivo(false);
    }
    
    public void comandoTecla(KeyEvent e){
        mov temp=mov.none;
        int dist=0;
        tempdir=dir.none;
        dirCol=col.none;
        activo=false;
        switch(e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                activo=true;
                if(fueraCamaraX){
                    dist=2;
                    temp=mov.per;
                    tempdir=dir.X;
                    dirCol=col.R;
                    if(fueraL && Personaje.Singleton().getX()>=ancho/2){
                        fueraCamaraX=false;
                        fueraL=false;
                    }
                    break;
                }else if(camaraX>-(mapaMastrum.getWidth(null)-ancho)){
                    temp=mov.cam;
                    tempdir=dir.X;
                    dirCol=col.R;
                    dist=-2;
                }else{
                    fueraCamaraX=true;
                    fueraR=true;
                }
                break;
            case KeyEvent.VK_LEFT:
                activo=true;
                if(fueraCamaraX){
                    dist=-2;
                    temp=mov.per;
                    tempdir=dir.X;
                    dirCol=col.L;
                    if(fueraR && Personaje.Singleton().getX()<ancho/2){                        
                        fueraCamaraX=false;
                        fueraR=false;
                    }
                    break;
                }else if(camaraX<0){
                    temp=mov.cam;
                    tempdir=dir.X;
                    dirCol=col.L;
                    dist=2;
                }else{
                    fueraCamaraX=true;
                    fueraL=true;
                }
            break;

                
            case KeyEvent.VK_UP:
                activo=true;
                if(fueraCamaraY){
                    dist=-2;
                    temp=mov.per;
                    tempdir=dir.Y;
                    dirCol=col.U;
                    if(fueraD && Personaje.Singleton().getY()<=alto/2){
                        fueraCamaraY=false;
                        fueraD=false;
                    }
                    break;
                }else if(camaraY<10){
                    temp=mov.cam;
                    tempdir=dir.Y;
                    dirCol=col.U;
                    dist=2;
                }else{
                    fueraCamaraY=true;
                    fueraU=true;
                }
                break;

            case KeyEvent.VK_DOWN:
                activo=true;
                if(fueraCamaraY){
                    Personaje.Singleton().mueve(0,2);
                    dist=2;
                    temp=mov.per;
                    tempdir=dir.Y;
                    dirCol=col.D;
                    if(fueraU && Personaje.Singleton().getY()>=alto/2){
                        fueraCamaraY=false;
                        fueraU=false;
                    }
                    break;
                }else if(camaraY>-(mapaMastrum.getHeight(null)-alto )){
                    temp=mov.cam;
                    tempdir=dir.Y;
                    dirCol=col.D;
                    dist=-2;
                }else{
                    fueraCamaraY=true;
                    fueraD=true;
                }
                break;
                
            case KeyEvent.VK_F10:
            Colisionadores=!Colisionadores;
            break;
                
            default:
                break;
        }
        if(limites.colisionLimites(dirCol)){
            dist=-dist;
        }
        movimiento(dist,temp,tempdir,dirCol);
    }
    
    public void movimiento(int dist,mov temp,dir tempdir,col dirCol){
        if(activo==false){
            Personaje.Singleton().movimiento(dirCol,false);
            return;
        }
        activo=false;
        switch(temp){
            case cam:
                if(tempdir==dir.X){
                    camara(dist*desp,0);
                }else{
                    camara(0,dist*desp);
                }
                limites.actualizaLimites(camaraX,camaraY);
                Personaje.Singleton().movimiento(dirCol,true);
                if(limites.colisionLimites(dirCol)){
                    if(tempdir==dir.X){
                        camara(-dist*desp,0);
                    }else{
                        camara(0,-dist*desp);
                    }
                    Personaje.Singleton().movimiento(dirCol,false);
                }
                break;
            case per:
                if(tempdir==dir.X){
                    Personaje.Singleton().mueve(dist,0);
                }else{
                    Personaje.Singleton().mueve(0,dist);
                }
                limites.actualizaLimites(camaraX,camaraY);
                Personaje.Singleton().movimiento(dirCol,true);
                if(limites.colisionLimites(dirCol)){
                    if(tempdir==dir.X){
                            Personaje.Singleton().mueve(-dist,0);
                    }else{
                        Personaje.Singleton().mueve(0,-dist);
                    }
                    Personaje.Singleton().movimiento(dirCol,false);
                }
                break;
            default:
                break;
        }
        
    }

}