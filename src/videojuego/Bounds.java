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
        public enum limitesMapas{Mastrum}
        public limitesMapas actuales=limitesMapas.Mastrum;
        public Stack <Rectangle> limites=new Stack();
        public Rectangle limite=null;
        public boolean cambioEstado=false;
        
        public void dibujaLimites(Graphics g, int CamX, int CamY){
            switch(actuales){
                case Mastrum:
                    if(cambioEstado){
                        limites.removeAllElements();
                    }
                    g.setColor(Color.black);
                    
                    limite= new Rectangle(CamX, CamY, 1673, 51);
                    limites.push(limite);
                    g.fillRect(CamX, CamY, 1673, 51);

                    limite= new Rectangle(CamX, CamY+270, 365, 503);
                    limites.push(limite);
                    g.fillRect(CamX, CamY+270, 365, 503);
                    
                    limite= new Rectangle(CamX+900, CamY+400, 100, 100);
                    limites.push(limite);
                    g.fillRect(CamX+900, CamY+400, 100, 100);
                    
                    limite=new Rectangle(CamX+1300, CamY, 501, 301);
                    limites.push(limite);
                    g.fillRect(CamX+1300, CamY, 501, 301);
                    
                    break;
                default:
                    break;
            }
        }
        
        public void actualizaLimites(int CamX,int CamY){
            limites.removeAllElements();
            limite= new Rectangle(CamX, CamY, 1673, 51);
            limites.push(limite);

            limite= new Rectangle(CamX, CamY+270, 365, 503);
            limites.push(limite);

            limite= new Rectangle(CamX+900, CamY+400, 100, 100);
            limites.push(limite);

            limite=new Rectangle(CamX+1300, CamY, 501, 301);
            limites.push(limite);
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
}
