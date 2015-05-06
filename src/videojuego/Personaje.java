/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Danny
 */
public class Personaje {
    public enum Estados{avanzarIzquierda, avanzarDerecha, avanzarArriba, avanzarAbajo,movimiento}
    
    Image imgNormal = DiccionarioImagenes.Singleton().imagen("prota_mini.png");
    Image imgNorArr = DiccionarioImagenes.Singleton().imagen("ProtaArr.png");
    Image imgNorDer = DiccionarioImagenes.Singleton().imagen("ProtaDer.png");
    Image imgNorIzq = DiccionarioImagenes.Singleton().imagen("ProtaIzq.png");
    
    Image imgMovDer = DiccionarioImagenes.Singleton().imagen("PrincipalDerecha.gif");
    Image imgMovIzq = DiccionarioImagenes.Singleton().imagen("PrincipalIzquierda.gif");
    Image imgMovAb = DiccionarioImagenes.Singleton().imagen("PrincipalAbajo.gif");
    Image imgMovArr = DiccionarioImagenes.Singleton().imagen("PrincipalArriba.gif");
    Image imgActual=null;
    Estados estado = Estados.avanzarDerecha;
    Estados direccion= Estados.avanzarDerecha;
    
    Vec posicion;
    final static int Vel=5;
    double escalarFronteras=1;
    static Personaje instancia = null;
    int alto=VentanaJuego.Singleton().getHeight();
    int ancho=VentanaJuego.Singleton().getWidth();
    boolean evitarSig=false;
    public int ataque=24;
    public int defensa=10;
    public int exp=0;
    public int lvl=1;
    
    static Personaje Singleton(){
        if(instancia == null){
            instancia = new Personaje();
            instancia.setPosicion(VentanaJuego.Singleton().getWidth()/2,VentanaJuego.Singleton().getHeight()/2);
        }
        return instancia;
    }

    void setEstado(Estados nuevoEstado){
        estado = nuevoEstado;
    }
    
    public void dibujarPersonaje(Graphics g){
        switch(estado){
            case avanzarIzquierda:
                imgActual=imgNorIzq;
                break;
            case avanzarDerecha:
                imgActual=imgNorDer;
                break;
            case avanzarArriba:
                imgActual=imgNorArr;
                break;
            case avanzarAbajo:
                imgActual=imgNormal;
                break;
            case movimiento:
                break;
        }
        g.drawImage(imgActual, posicion.getIntX(), posicion.getIntY(), null );
        //dibujaBarrera(g);
        
    }
    
    void setPosicion(int x, int y){
        posicion=new Vec(x,y);
    }
    
    public void mueve(int dx, int dy){
        Vec desplaza=new Vec(Vel*dx,Vel*dy);
        posicion.add(desplaza);
        double maxX=ancho;
        if(posicion.getIntX()>maxX){
            posicion.setX(maxX);
        }
        if(posicion.getIntX()<0){
            posicion.setX(0);
        }
        double maxY=alto;
        if(posicion.getIntY()>maxY){
            posicion.setY(maxY);
        }
        if(posicion.getIntY()<0){
            posicion.setY(0);
        }
    }
    
    public int getX(){
       return posicion.getIntX();
    }
    
    public int getY(){
       return posicion.getIntY();
    }
    public int getPersonajeWidth(){
        switch(estado){
            case avanzarIzquierda:
                return imgNorIzq.getWidth(null);
               
            case avanzarDerecha:
                return imgNorDer.getWidth(null);
            
            case avanzarArriba:
                return imgNorArr.getWidth(null);
             
            case avanzarAbajo:
                return imgNormal.getWidth(null);
            
            case movimiento:
                return imgActual.getWidth(null);
            default:
                return 0;
        }
    }
    
    public int getPersonajeHeight(){
        switch(estado){
            case avanzarIzquierda:
                return imgNorIzq.getHeight(null);
               
            case avanzarDerecha:
                return imgNorDer.getHeight(null);
            
            case avanzarArriba:
                return imgNorArr.getHeight(null);
             
            case avanzarAbajo:
                return imgNormal.getHeight(null);
            
            case movimiento:
                return imgActual.getHeight(null);
            default:
                return 0;
        }
    }
    
    public void movimiento(Escenario.col cambio,boolean mov){
        if(mov==false){
            switch(cambio){
                case L:
                    imgActual=imgNorIzq;
                    direccion=Estados.avanzarIzquierda;
                    break;

                case R:
                    imgActual=imgNorDer;
                    direccion=Estados.avanzarDerecha;
                    break;

                case U:
                    imgActual=imgNorArr;
                    direccion=Estados.avanzarArriba;
                    break;

                case D:
                    imgActual=imgNormal;
                    direccion=Estados.avanzarAbajo;
                    break;    
            }
            return;
        }
        switch(cambio){
            case L:
                imgActual=imgMovIzq;
                direccion=Estados.avanzarIzquierda;
                break;
               
            case R:
                imgActual=imgMovDer;
                direccion=Estados.avanzarDerecha;
                break;
            
            case U:
                imgActual=imgMovArr;
                direccion=Estados.avanzarArriba;
                break;
             
            case D:
                imgActual=imgMovAb;
                direccion=Estados.avanzarAbajo;
                break;
            default:
                break;
        }
        estado=estado.movimiento;
    }
    
    public Image getImagen(boolean derecha){
        if(derecha){
            return imgNorDer;
        }
        return imgNorIzq;
    }
    
    public Rectangle fronteras(){
            return new Rectangle(getX(),getY(),Singleton().getPersonajeWidth(),Singleton().getPersonajeHeight());
        }
    
    public boolean colisiona(Rectangle otro){
        if(otro.intersectsLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY(),
                             Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,Personaje.Singleton().getY()))
            return true;
        if(otro.intersectsLine(Personaje.Singleton().getX()+10,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                              Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-10
                              ,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()))
            return true;
        if(otro.intersectsLine(Personaje.Singleton().getX(),Personaje.Singleton().getY()+10,
                                Personaje.Singleton().getX(),Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-10))
            return true;
        if(otro.intersectsLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),Personaje.Singleton().getY()+10,
                                Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-10))
            return true;
        
        return false;
    }
    
    public void dibujaBarrera (Graphics g){
            g.drawLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY(),
                             Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,Personaje.Singleton().getY());
                g.drawLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                              Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5
                              ,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight());
                g.drawLine(Personaje.Singleton().getX(),Personaje.Singleton().getY()+5,
                                Personaje.Singleton().getX(),Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5);
                g.drawLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),Personaje.Singleton().getY()+5,
                                Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5);
                
                g.drawLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY(),
                             Personaje.Singleton().getX(),Personaje.Singleton().getY()+5);
                g.drawLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,Personaje.Singleton().getY(),
                             Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),Personaje.Singleton().getY()+5);
                g.drawLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                             Personaje.Singleton().getX(),Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5);
                g.drawLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                                Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5);
        }
    
    public void ataqueCuerpoACuerpo(){
        
    }
    
    public void iniciarConversacion(){
        
    }

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp += exp;
		if(exp>=100){
			lvl++;
			ataque*=1.5;
			defensa*=1.2;
			exp-=100;
		}
	}
    
    
}
