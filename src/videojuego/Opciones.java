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
import java.awt.event.MouseEvent;

/**
 *
 * @author Christopher
 */
public class Opciones {
    public enum estado{musica,efectos,inactivo,regresar}
    public enum musica{MusicaOn,MusicaOff, Ninguno}
    public enum efectos{EfectosSonidoOn,EfectosSonidoOff, Ninguno}
    
    private int x=0, y=0;
    Image imgPortada = DiccionarioImagenes.Singleton().imagen("principal.jpg");
    Font fuente = new Font("TimesRoman", Font.BOLD, 23);
    musica Music=musica.MusicaOn;
    musica musicaSelec = musica.MusicaOn;
    efectos Efectos=efectos.EfectosSonidoOn;
    efectos efectosSelec = efectos.EfectosSonidoOn;
    estado actual=estado.inactivo;
    public int alto=VentanaJuego.Singleton().getHeight();
    public int ancho=VentanaJuego.Singleton().getWidth();
    public boolean Siguiente=false;
    
    Rectangle rectRegresar = new Rectangle(15, alto - 45, 112, 32);
    Rectangle rectMusicaOn = new Rectangle(ancho / 2 - 55, alto / 2 - 25, 41, 32);
    Rectangle rectMusicaOff = new Rectangle(ancho / 2 + 45, alto / 2 - 25, 44, 32);
    Rectangle rectEfectosOn = new Rectangle(ancho / 2 - 55, alto / 2 + 75, 41, 32);
    Rectangle rectEfectosOff = new Rectangle(ancho / 2 + 45, alto / 2 + 75, 44, 32);
    
    private static Opciones instancia = null; 
    
    public static Opciones Singleton(){
        if(instancia == null){
            instancia = new Opciones();
        }
        return instancia;
    }
    
    public void dibujarOpciones(Graphics g){
        g.setFont(fuente);
        g.setColor(Color.WHITE);
        g.drawImage(imgPortada, x, y, null);
        g.drawString("Música", ancho/2-(5*6), alto/2-50);
        g.drawString("On", ancho/2-50 , alto/2);
        g.drawString("Off", ancho/2+50 , alto/2);
        g.drawString("Efectos de sonido", ancho/2-(5*17), alto/2+50);
        g.drawString("On", ancho/2-50 , alto/2+100);
        g.drawString("Off", ancho/2+50 , alto/2+100);
        g.drawString("Regresar", 20, alto-20);

        switch(actual){
            case inactivo:
                g.setColor(Color.WHITE);
                g.drawString("Musica", ancho/2-(5*6), alto/2-50);
                g.drawString("Efectos de sonido", ancho/2-(5*17), alto/2+50);
                break;
            case musica:
                g.setColor(Color.GREEN);
                g.drawString("Musica", ancho/2-(5*6), alto/2-50);
                break;
            case efectos:
                g.setColor(Color.GREEN);
                g.drawString("Efectos de sonido", ancho/2-(5*17), alto/2+50);
                break;
            case regresar:
                g.setColor(Color.GREEN);
                g.drawString("Regresar", 20, alto-20);
                if(Siguiente){
                    Siguiente = false;
                    if(VentanaJuego.getPantalla().anterior() == EstadoPantalla.Pantallas.MenuPausa){
                        VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Partida1);
                        //VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.MenuPausa);
                    }else{
                        VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.MenuPrincipal);
                    }
                    
                }
            default:
                break;
        }
        switch(Music){
            case MusicaOn:
                g.setColor(Color.GREEN);
                g.drawString("On", ancho/2-50 , alto/2);
                if(musicaSelec == musica.MusicaOff){
                    g.setColor(Color.yellow);
                    g.drawString("Off", ancho/2+50 , alto/2);
                    if(Siguiente){
                        Siguiente = false;
                        Music = musica.MusicaOff;
                        Musica.Singleton().detenerMusica();
                    }
                }
                break;
            case MusicaOff:
                g.setColor(Color.GREEN);
                g.drawString("Off", ancho/2+50 , alto/2);
                if(musicaSelec == musica.MusicaOn){
                    g.setColor(Color.yellow);
                    g.drawString("On", ancho/2-50 , alto/2);
                    if(Siguiente){
                        Siguiente = false;
                        Music = musica.MusicaOn;
                        Musica.Singleton().seguircorriendo();
                    }
                }
                break;
            default:
                break;
        }
        switch(Efectos){
            case EfectosSonidoOn:
                g.setColor(Color.GREEN);
                g.drawString("On", ancho/2-50 , alto/2+100);
                if(efectosSelec == efectos.EfectosSonidoOff){
                    g.setColor(Color.yellow);
                    g.drawString("Off", ancho/2+50 , alto/2+100);
                    if(Siguiente){
                        Siguiente = false;
                        Efectos = efectos.EfectosSonidoOff;
                    }
                }
                break;
            case EfectosSonidoOff:
                g.setColor(Color.GREEN);
                g.drawString("Off", ancho/2+50 , alto/2+100);
                if(efectosSelec == efectos.EfectosSonidoOn){
                    g.setColor(Color.yellow);
                    g.drawString("On", ancho/2-50 , alto/2+100);
                    if(Siguiente){
                        Siguiente = false;
                        Efectos = efectos.EfectosSonidoOn;
                    }
                }
                break;
            default:
                break;
        }
        Siguiente=false;
    }

    public musica getMusica() {
        return Music;
    }
    
    public void comando(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            switch(actual){
                case inactivo:
                    actual=estado.musica;
                    break;
                case musica:
                    actual=estado.efectos;
                    break;
                case efectos:
                    actual=estado.regresar;
                    break;
                case regresar:
                    actual=estado.musica;
                    break;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            switch(actual){
                case inactivo:
                    actual=estado.regresar;
                    break;
                case musica:
                    actual=estado.regresar;
                    break;
                case efectos:
                    actual=estado.musica;
                    break;
                case regresar:
                    actual=estado.efectos;
                    break;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            switch(actual){
                case musica:
                    switch(Music){
                        case MusicaOn:
                            Music=musica.MusicaOff;
                            break;
                        default:
                            break;
                    }
                    break;
                case efectos:
                    switch(Efectos){
                        case EfectosSonidoOn:
                            Efectos=efectos.EfectosSonidoOff;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }   
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            switch(actual){
                case musica:
                    switch(Music){
                        case MusicaOff:
                            Music=musica.MusicaOn;
                            break;
                        default:
                            break;
                    }
                    break;
                case efectos:
                    switch(Efectos){
                        case EfectosSonidoOff:
                            Efectos=efectos.EfectosSonidoOn;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }   
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            Siguiente=true;
        }
    }
    
    public void detectaMouse(MouseEvent e){
        int posX = e.getX();
        int posY = e.getY();
        
        if(rectRegresar.contains(posX, posY)){
            actual = estado.regresar;
        }else if(rectMusicaOn.contains(posX, posY)){
            musicaSelec = musica.MusicaOn;
        }else if(rectMusicaOff.contains(posX, posY)){
            musicaSelec = musica.MusicaOff;
        }else if(rectEfectosOn.contains(posX, posY)){
            efectosSelec = efectos.EfectosSonidoOn;
        }else if(rectEfectosOff.contains(posX, posY)){
            efectosSelec = efectos.EfectosSonidoOff;
        }else{
            actual = estado.inactivo;
            musicaSelec = musica.Ninguno;
            efectosSelec = efectos.Ninguno;
        }
    }
    
}
