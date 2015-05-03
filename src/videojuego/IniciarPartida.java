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
 * @author Azu B
 */
public class IniciarPartida {
    public enum OpcionEstado{stand_by,file_1,file_2,file_3,regresar}// Estados de la pantalla de iniciar partida
    
    int alto = VentanaJuego.Singleton().getHeight();
    int ancho = VentanaJuego.Singleton().getWidth();// dimensiones pantalla
    
    private int x = 0, y = 0;
    
    Font fuente_1 = new Font("TimesRoman", Font.BOLD, 48);
    Font fuente_2 = new Font("TimesRoman", Font.BOLD, 24);
    Image imgPortada = DiccionarioImagenes.Singleton().imagen("archivos.jpg");
    public OpcionEstado iniciar = OpcionEstado.stand_by;
    Rectangle rectFile_1 = new Rectangle(ancho/2-200, alto/2-200, ancho/2+100, alto/4);
    Rectangle rectFile_2 = new Rectangle(ancho/2-200, alto/2-40, ancho/2+100, alto/4);
    Rectangle rectFile_3 = new Rectangle(ancho/2-200, alto/2+120, ancho/2+100, alto/4);
    Rectangle rectRegresar = new Rectangle(15, alto - 45, 112, 32);
    
    boolean siguiente = false;
    
    public void dibujarPartidas(Graphics g){
        g.setFont(fuente_1);
        g.drawImage(imgPortada, x, y, null);
        g.setColor(Color.white);
        g.drawString("Seleccionar archivo", ancho/2-200, 80);
        g.setFont(fuente_2);
        g.drawString("Regresar", 20, alto-20);
        g.setColor(Color.WHITE);
        g.fillRoundRect(ancho/2-200, alto/2-200, ancho/2+100, alto/4,10,10);
        g.fillRoundRect(ancho/2-200, alto/2-40, ancho/2+100, alto/4,10,10);
        g.fillRoundRect(ancho/2-200, alto/2+120, ancho/2+100, alto/4,10,10);
        
        switch(iniciar){
            case file_1:
                g.setColor(Color.CYAN);
                g.fillRoundRect(ancho/2-200, alto/2-200, ancho/2+100, alto/4,10,10);
                if(siguiente){
                    siguiente = false;
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Partida1);
                }
                break;
            case file_2:
                g.setColor(Color.CYAN);
                g.fillRoundRect(ancho/2-200, alto/2-40, ancho/2+100, alto/4,10,10);
                if(siguiente){
                    siguiente = false;
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Partida1);
                }
                break;
            case file_3:
                g.setColor(Color.CYAN);
                g.fillRoundRect(ancho/2-200, alto/2+120, ancho/2+100, alto/4,10,10);
                if(siguiente){
                    siguiente = false;
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Partida1);
                }
                break;
            case regresar:
                g.setColor(Color.cyan);
                g.drawString("Regresar", 20, alto-20);
                if(siguiente){
                    siguiente = false;
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.MenuPrincipal);
                }
                break;
            default:
                break;
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Partida 2", ancho / 2 - 50, alto/2 + 10);
        g.drawString("Partida 3", ancho / 2 - 50, alto/2 + 170);
        g.drawString("Partida 1", ancho / 2 - 50, alto/2 - 150);
    }
    
    public void comando(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            switch (iniciar){
                case file_1: 
                    iniciar = OpcionEstado.file_2;
                    break;
                case file_2: 
                    iniciar = OpcionEstado.file_3;
                    break;
                case file_3: 
                    iniciar = OpcionEstado.regresar;
                    break;
                case regresar:
                case stand_by:
                    iniciar = OpcionEstado.file_1;
                    break;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            switch (iniciar){
                case file_1:
                    iniciar = OpcionEstado.file_2;
                    break;
                case file_2: 
                    iniciar = OpcionEstado.file_3;
                    break;
                case file_3:
                case stand_by:
                    iniciar = OpcionEstado.regresar;
                    break;
                case regresar:
                    iniciar = OpcionEstado.file_1;
                    break;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            siguiente=true;
        }
        
    }
     
    public void detectarMouse(MouseEvent e){
        int posX = e.getX();
        int posY = e.getY();
        
        if(rectRegresar.contains(posX, posY)){
            iniciar = OpcionEstado.regresar;
        }else if(rectFile_1.contains(posX, posY)){
            iniciar = OpcionEstado.file_1;
        }else if(rectFile_2.contains(posX, posY)){
            iniciar = OpcionEstado.file_2;
        }else if(rectFile_3.contains(posX, posY)){
            iniciar = OpcionEstado.file_3;
        }else{
            iniciar = OpcionEstado.stand_by;
        }
    }
}
