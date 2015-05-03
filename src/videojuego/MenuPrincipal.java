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
 * @author Danny
 */
public class MenuPrincipal {
    public enum Opciones{StandBy, Iniciar,Cargar,Datos,Opciones}
    
    private int x=0, y=0;
    Image imgPortada = DiccionarioImagenes.Singleton().imagen("principal.jpg");
    Font fuente = new Font("TimesRoman", Font.BOLD, 23);
    Opciones estado=Opciones.StandBy;
    public int alto=VentanaJuego.Singleton().getHeight();
    public int ancho=VentanaJuego.Singleton().getWidth();
    public boolean Siguiente=false;
    Rectangle rectIniciar = new Rectangle(ancho / 2 - 85, alto / 2 - 25, 164, 32); // Rectángulo de Iniciar Partida
    Rectangle rectCargar = new Rectangle(ancho / 2 - 87, alto / 2 + 15, 169, 32);
    Rectangle rectDatos = new Rectangle(ancho / 2 - 155, alto / 2 + 55, 317, 32);
    Rectangle rectOpciones = new Rectangle(ancho / 2 - 65, alto / 2 + 95, 116, 32);
    
    public void dibujarMenuPrincipal(Graphics g){
        g.setFont(fuente);
         g.setColor(Color.WHITE);
        g.drawImage(imgPortada, x, y, null);
        g.drawString("Iniciar Partida", ancho/2 - 80, alto/2);
        g.drawString("Cargar Partida", ancho/2 - 82, alto/2 + 40);
        g.drawString("Ver datos de los personajes", ancho/2 - 150, alto/2 + 80);
        g.drawString("Opciones", ancho/2 - 60, alto/2 + 120);
        
        switch(estado){
            case Iniciar:
                g.setColor(Color.GREEN);
                g.drawString("Iniciar Partida", ancho/2 - 80, alto/2);
                if(Siguiente){
                    iniciarPartida(g);
                }
                break;
            case Cargar:
                g.setColor(Color.GREEN);
                g.drawString("Cargar Partida", ancho/2 - 82, alto/2 + 40);
                if(Siguiente){
                    cargarPartida(g);            
                }
                break;
            case Datos:
                g.setColor(Color.GREEN);
                g.drawString("Ver datos de los personajes", ancho/2 - 150, alto/2 + 80);
                if(Siguiente){
                    verDatosPersonajes(g);
                }
                break;
            case Opciones:
                g.setColor(Color.GREEN);
                g.drawString("Opciones", ancho/2 - 60, alto/2 + 120);
                if(Siguiente){
                    opciones(g);
                }
                break;
            default:
                break;
        }
        Siguiente=false;
    }
    
    
    public void iniciarPartida(Graphics g){
        Siguiente=false;
        estado = Opciones.StandBy;
        VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.IniciarPartida);
        VentanaJuego.Singleton().paint(g);
    }
    
    public void cargarPartida(Graphics g){
        Siguiente=false;
        estado = Opciones.StandBy;
        VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.CargaPartida);
        VentanaJuego.Singleton().paint(g);
    }
    
    public void verDatosPersonajes(Graphics g){
        Siguiente=false;
        estado = Opciones.StandBy;
        VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.VerDatos);
        VentanaJuego.Singleton().paint(g);
    }
    
    
    public void opciones(Graphics g){
        Siguiente=false;
        estado=Opciones.StandBy;
        VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Opciones);
        VentanaJuego.Singleton().paint(g);
    }
    
    // Método para ver si el mouse está sobre alguna de las opciones...
    public void detectarMouse(MouseEvent e){
        int posX = e.getX();
        int posY = e.getY();
        
        if(rectIniciar.contains(posX, posY)){
            estado = Opciones.Iniciar;
        }else if(rectCargar.contains(posX, posY)){
            estado = Opciones.Cargar;
        }else if(rectDatos.contains(posX, posY)){
            estado = Opciones.Datos;
        }else if(rectOpciones.contains(posX, posY)){
            estado = Opciones.Opciones;
        }else{
            estado = Opciones.StandBy;
        }
        
    }
    
    public void comando(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            switch (estado){
                case Iniciar: 
                    estado=Opciones.Cargar;
                    break;
                case Cargar: 
                    estado=Opciones.Datos;
                    break;
                case Datos: 
                    estado=Opciones.Opciones;
                    break;
                case Opciones:
                case StandBy:
                    estado=Opciones.Iniciar;
                    break;
                
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            switch (estado){
                case Iniciar:
                case StandBy:
                    estado=Opciones.Opciones;
                    break;
                case Cargar: 
                    estado=Opciones.Iniciar;
                    break;
                case Datos: 
                    estado=Opciones.Cargar;
                    break;
                case Opciones:
                    estado=Opciones.Datos;
                    break;
                
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            Siguiente=true;
        }
    }
}
