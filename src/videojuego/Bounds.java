/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Stack;

/**
 *
 * @author Christopher
 */
public class Bounds {
        public enum limitesMapas{Mastrum, Bosque, Gremio}
        public limitesMapas actuales=limitesMapas.Mastrum;
        public Stack <Rectangle> limites=new Stack();
        public Rectangle limite=null;
        public boolean cambioEstado=false;
        
        public void dibujaLimites(Graphics g, int CamX, int CamY){
            switch(actuales){
                case Mastrum:
                    if(cambioEstado){
                        limites.removeAllElements();
                        cambioEstado = false;
                    }
                    g.setColor(Color.black);
                    
                    limite= new Rectangle(CamX, CamY, 1673, 51);
                    limites.push(limite);
                    g.fillRect(CamX, CamY, 1673, 51);

                    limite= new Rectangle(CamX, CamY, 60, 1500);
                    limites.push(limite);
                    g.fillRect(CamX, CamY, 40, 1500);

                    limite= new Rectangle(CamX, CamY+270, 365, 503);
                    limites.push(limite);
                    g.fillRect(CamX, CamY+270, 365, 503);
                    
                    limite= new Rectangle(CamX + 40, CamY + 900, 50, 200);
                    limites.push(limite);
                    g.fillRect(CamX + 40, CamY + 900, 20, 200);
                    
                    limite=new Rectangle(CamX+1300, CamY, 501, 301);
                    limites.push(limite);
                    g.fillRect(CamX+1300, CamY, 501, 301);
                    
                    limite=new Rectangle(CamX + 60, CamY + 950, 1270, 300);
                    limites.push(limite);
                    g.fillRect(CamX + 60, CamY + 950, 1270, 300);
                    
                    limite=new Rectangle(CamX + 650, CamY + 900, 100, 300);
                    limites.push(limite);
                    g.fillRect(CamX + 650, CamY + 900, 100, 300);
                    
                    limite=new Rectangle(CamX + 1300, CamY + 1010, 1000, 300);
                    limites.push(limite);
                    g.fillRect(CamX + 1300, CamY + 1010, 1000, 300);
                    
                    limite=new Rectangle(CamX + 1620, CamY, 1000, 1200);
                    limites.push(limite);
                    g.fillRect(CamX + 1620, CamY, 1000, 1200);
                    break;
                case Bosque:
                	if(cambioEstado){
                        limites.removeAllElements();
                        cambioEstado = false;
                    }
                	g.setColor(Color.BLACK);
                	limite=new Rectangle(CamX + 1620, CamY, 1000, 1200);
                    limites.push(limite);
                    g.fillRect(CamX + 1620, CamY, 1000, 1200);
                    
                    limite=new Rectangle(CamX, CamY + 1050, 5000, 300);
                    limites.push(limite);
                    g.fillRect(CamX, CamY + 1050, 5000, 300);
                    
                    break;
                case Gremio:
                	if(cambioEstado){
                        limites.removeAllElements();
                        cambioEstado = false;
                    }
                	g.setColor(Color.BLACK);
                	limite=new Rectangle(CamX, CamY + 1060, 5000, 300);
                    limites.push(limite);
                    g.fillRect(CamX, CamY + 1060, 5000, 300);
                    limite=new Rectangle(CamX + 1680, CamY, 300, 1500);
                    limites.push(limite);
                    g.fillRect(CamX + 1680, CamY, 300, 1500);
                    
                    break;
                default:
                    break;
            }
        }
        
        public void actualizaLimites(int CamX,int CamY){
        	switch(actuales){
        		case Mastrum:
        			limites.removeAllElements();
                    
                    limite= new Rectangle(CamX, CamY, 1673, 51);
                    limites.push(limite);

                    limite= new Rectangle(CamX, CamY, 60, 1500);
                    limites.push(limite);

                    limite= new Rectangle(CamX, CamY+270, 365, 503);
                    limites.push(limite);
                    
                    limite= new Rectangle(CamX + 40, CamY + 900, 50, 200);
                    limites.push(limite);
                    
                    limite=new Rectangle(CamX+1300, CamY, 501, 301);
                    limites.push(limite);
                    
                    limite=new Rectangle(CamX + 60, CamY + 950, 1270, 300);
                    limites.push(limite);
                    
                    limite=new Rectangle(CamX + 650, CamY + 900, 100, 300);
                    limites.push(limite);
                    
                    limite=new Rectangle(CamX + 1300, CamY + 1010, 1000, 300);
                    limites.push(limite);
                    
                    limite=new Rectangle(CamX + 1620, CamY, 1000, 1200);
                    limites.push(limite);
                    break;
        		case Bosque:
        			limites.removeAllElements();
        			limite=new Rectangle(CamX + 1620, CamY, 1000, 1200);
                    limites.push(limite);
                    
                    limite=new Rectangle(CamX, CamY + 1050, 5000, 300);
                    limites.push(limite);
                    
        			break;
        		case Gremio:
        			limites.removeAllElements();
        			limite=new Rectangle(CamX, CamY + 1060, 5000, 300);
                    limites.push(limite);
                    
                    limite=new Rectangle(CamX + 1680, CamY, 300, 1500);
                    limites.push(limite);
                    
        			break;
        	}
        }
        
        
        
        public boolean colisionLimites(Escenario.col axis){
            boolean colision=false;
            Stack <Rectangle> aux=limites;
            while(!limites.isEmpty()){
                Rectangle comp=limites.pop();
                switch(axis){
                    case U:
                        if(comp.intersectsLine(Personaje.Singleton().getX()+10,Personaje.Singleton().getY(),
                             Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-10,Personaje.Singleton().getY())){
                            colision=true;
                            break;
                        }
                        if(comp.intersectsLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY(),//topl
                             Personaje.Singleton().getX(),Personaje.Singleton().getY()+5)
                             &&
                             comp.intersectsLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,Personaje.Singleton().getY(),
                             Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),Personaje.Singleton().getY()+5)){
                            colision=true;
                            break;
                        }
                        break;
                    case D:
                        if(comp.intersectsLine(Personaje.Singleton().getX()+10,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                              Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-10
                              ,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight())){
                            colision=true;
                            break;
                        }
                        if(comp.intersectsLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                             Personaje.Singleton().getX(),Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5)
                             &&
                             comp.intersectsLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                                Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5)){
                            colision=true;//BotL
                            break;
                        }
                        break;
                    case L:
                        if(comp.intersectsLine(Personaje.Singleton().getX(),Personaje.Singleton().getY()+10,
                                Personaje.Singleton().getX(),Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-10)){
                            colision=true;
                            break;
                        }
                        
                        
                        if(comp.intersectsLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                             Personaje.Singleton().getX(),Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5)
                                &&
                                comp.intersectsLine(Personaje.Singleton().getX()+5,Personaje.Singleton().getY(),//topl
                             Personaje.Singleton().getX(),Personaje.Singleton().getY()+5)){
                            colision=true;//BotL
                            break;
                        }
                        break;
                    case R:
                        if(comp.intersectsLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),Personaje.Singleton().getY()+10,
                                Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-10)){
                            colision=true;
                            break;
                        }
                        if(comp.intersectsLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight(),
                                Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),
                                Personaje.Singleton().getY()+Personaje.Singleton().getPersonajeHeight()-5)
                                &&
                                comp.intersectsLine(Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth()-5,Personaje.Singleton().getY(),
                                Personaje.Singleton().getX()+Personaje.Singleton().getPersonajeWidth(),Personaje.Singleton().getY()+5)){
                            colision=true;//BotR
                            break;
                        }
                        break;
                    default:
                        break;
                }
            }
            limites=aux;
            return colision;
        }
        
        public void cambiaLimite(int x){
        	if(actuales == limitesMapas.Mastrum){
        		actuales = limitesMapas.Bosque;
        		cambioEstado = true;
        	} else if(actuales == limitesMapas.Bosque && x == 0){
        		actuales = limitesMapas.Gremio;
        		cambioEstado = true;
        	} else if(actuales == limitesMapas.Bosque && x == 1){
        		actuales = limitesMapas.Mastrum;
        		cambioEstado = true;
        	} else {
        		actuales = limitesMapas.Bosque;
        		cambioEstado = true;
        	}
        }
}
