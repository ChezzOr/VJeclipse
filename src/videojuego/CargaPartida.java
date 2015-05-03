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
public class CargaPartida {
    public enum Estados{standBy, euno,edos,etres,regresar}//estados de la pantalla de carga partida
    int alto=VentanaJuego.Singleton().getHeight();
    int ancho=VentanaJuego.Singleton().getWidth();// dimensiones pantalla
    private int x=0, y=0;
    Font fuente = new Font("TimesRoman", Font.BOLD, 48);
    Font fuente2 = new Font("TimesRoman", Font.BOLD, 24);
    Image imgPortada = DiccionarioImagenes.Singleton().imagen("principal.jpg");
    public Estados partida=Estados.euno;
    boolean Siguiente=false;
    Rectangle rectFile_1 = new Rectangle(ancho/2-200, alto/2-200, ancho/2+100, alto/4);
    Rectangle rectFile_2 = new Rectangle(ancho/2-200, alto/2-40, ancho/2+100, alto/4);
    Rectangle rectFile_3 = new Rectangle(ancho/2-200, alto/2+120, ancho/2+100, alto/4);
    Rectangle rectRegresar = new Rectangle(15, alto - 45, 112, 32);
    
     public void dibujarPartidas(Graphics g){
        g.setFont(fuente);
        g.drawImage(imgPortada, x, y, null);
        g.setColor(Color.white);
        g.drawString("Seleccionar Partida", ancho/2-200, 80);
        g.setFont(fuente2);
        g.drawString("Regresar", 20, alto-20);
        g.setColor(Color.ORANGE);
        g.fillRoundRect(ancho/2-200, alto/2-200, ancho/2+100, alto/4,10,10);
        g.fillRoundRect(ancho/2-200, alto/2-40, ancho/2+100, alto/4,10,10);
        g.fillRoundRect(ancho/2-200, alto/2+120, ancho/2+100, alto/4,10,10);
        switch(partida){
            case euno:
                g.setColor(Color.CYAN);
                g.fillRoundRect(ancho/2-200, alto/2-200, ancho/2+100, alto/4,10,10);
                break;
            case edos:
                g.setColor(Color.CYAN);
                g.fillRoundRect(ancho/2-200, alto/2-40, ancho/2+100, alto/4,10,10);
                break;
            case etres:
                g.setColor(Color.CYAN);
                g.fillRoundRect(ancho/2-200, alto/2+120, ancho/2+100, alto/4,10,10);
                break;
            case regresar:
                g.setColor(Color.GREEN);
                g.drawString("Regresar", 20, alto-20);
                if(Siguiente){
                    Siguiente=false;
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.MenuPrincipal);
                }
                break;
            default:
                break;
        }
    }
     
    public void comando(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            switch (partida){
                case euno:
                    partida=Estados.edos;
                    break;
                case edos: 
                    partida=Estados.etres;
                    break;
                case etres: 
                    partida=Estados.regresar;
                    break;
                case regresar:
                case standBy:
                    partida=Estados.euno;
                    break;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            switch (partida){
                case euno:
                case standBy:
                    partida=Estados.regresar;
                    break;
                case edos: 
                    partida=Estados.euno;
                    break;
                case etres: 
                    partida=Estados.edos;
                    break;
                case regresar:
                    partida=Estados.etres;
                    break;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            Siguiente=true;
        }
    }
    
    public void detectarMouse(MouseEvent e){
        int posX = e.getX();
        int posY = e.getY();
        
        if(rectRegresar.contains(posX, posY)){
            partida = Estados.regresar;
        }else if(rectFile_1.contains(posX, posY)){
            partida = Estados.euno;
        }else if(rectFile_2.contains(posX, posY)){
            partida = Estados.edos;
        }else if(rectFile_3.contains(posX, posY)){
            partida = Estados.etres;
        }else{
            partida = Estados.standBy;
        }
    }
     
    
}
