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
import java.awt.event.KeyEvent;

/**
 *
 * @author Danny
 */
public class Menu {
    
    private int x = 90, y = 68;
    
    public enum Options{standBy,guardarPartida, consultarInventario, verCaracteristicas, Opciones,
                        consultarAyuda, Regresar, Salir};
    public enum GuardarPartida{Si, No};
    public enum Salir{Si, No};
    Image imgMenu = DiccionarioImagenes.Singleton().imagen("Menu.png");
    Image imgDialogSalida = DiccionarioImagenes.Singleton().imagen("salir_en_partida.png");
    Image imgAyuda = DiccionarioImagenes.Singleton().imagen("Ayuda.png");
    Image imgCaracteristicas = DiccionarioImagenes.Singleton().imagen("Caracteristicas.png");
    Image imgInventario = DiccionarioImagenes.Singleton().imagen("Inventario.png");
    Image imgDialogGuardar = DiccionarioImagenes.Singleton().imagen("guardar_partida.png");
    Image imgCasillasInventario = DiccionarioImagenes.Singleton().imagen("Casillas_inventario.png");
    private boolean next = false;
    private boolean regresar = false;
    
    private Partida actual = new Partida();
    
    Options opcion = Options.standBy;
    GuardarPartida estadoPartida = GuardarPartida.No;
    Salir salir = Salir.No;
    
    Font fuente_1 = new Font("Lucida Console", Font.BOLD, 28);
    Font fuente_2 = new Font("Lucida Console", Font.BOLD, 26);

    public void dibujarMenu(Graphics g) {
        g.drawImage(imgMenu, x, y, null);
        g.setFont(fuente_1);
        
        // Dibujar la sombra
        g.setColor(Color.BLACK);
        g.drawString("Guardar partida", 233, 153);
        g.drawString("Consultar inventario", 233, 203);
        g.drawString("Ver caracteristicas", 233, 253);
        g.drawString("Consultar ayuda", 233, 303);
        g.drawString("Opciones", 233, 353);
        g.drawString("Regresar a partida", 233, 403);
        g.drawString("Salir", 233, 453);
        
        // Dibujar las letras blancas...
        g.setColor(Color.WHITE);
        g.drawString("Guardar partida", 230, 150);
        g.drawString("Consultar inventario", 230, 200);
        g.drawString("Ver caracteristicas", 230, 250);
        g.drawString("Consultar ayuda", 230, 300);
        g.drawString("Opciones", 230, 350);
        g.drawString("Regresar a partida", 230, 400);
        g.drawString("Salir", 230, 450);
        
        switch(opcion){
            case guardarPartida:
                g.setColor(Color.CYAN);
                g.drawString("Guardar partida", 230, 150);
                if(next){
                    guardarPartida(g);
                }
                break;
                
            case consultarInventario:
                g.setColor(Color.CYAN);
                g.drawString("Consultar inventario", 230, 200);
                if(next){
                    consultarInventario(g);
                }
                break;
                
            case verCaracteristicas:
                g.setColor(Color.CYAN);
                g.drawString("Ver caracteristicas", 230, 250);
                if(next){
                    verCaracteristicas(g);
                }
                break;
                
            case consultarAyuda:
                g.setColor(Color.CYAN);
                g.drawString("Consultar ayuda", 230, 300);
                if(next){
                    consultarAyuda(g);
                }
                break;
                
            case Opciones:
                g.setColor(Color.CYAN);
                g.drawString("Opciones", 230, 350);
                if(next){
                    next = false;
                    opciones(g);
                }
                break;
                
            case Regresar:
                g.setColor(Color.CYAN);
                g.drawString("Regresar a partida", 230, 400);
                if(next){
                    next = false;
                    opcion = Options.standBy;
                    regresar = true;
                }
                break;
                
            case Salir:
                g.setColor(Color.CYAN);
                g.drawString("Salir", 230, 450);
                if(next){
                    salir(g);
                }
                break;
               
        }
    }
    
    public void guardarPartida(Graphics g){
        g.drawImage(imgDialogGuardar, 190, 200, null);
        g.setFont(fuente_2);
        
        g.setColor(Color.BLACK);
        g.drawString("¿Guardar partida?", 253, 278);
        g.drawString("Sí", 303, 353);
        g.drawString("No", 453, 353);
        
        g.setColor(Color.WHITE);
        g.drawString("¿Guardar partida?", 250, 275);
        g.drawString("Sí", 300, 350);
        g.drawString("No", 450, 350);
        
        switch(estadoPartida){
            case Si:
                g.setColor(Color.YELLOW);
                g.drawString("Sí", 300, 350);
            break;
                
            case No:
                g.setColor(Color.YELLOW);
                g.drawString("No", 450, 350);
            break;
        }
        
    }
    
    public void consultarInventario(Graphics g){
    	VentanaJuego.getInventario().dibujaConsulta(g);
    	
        /*g.drawImage(imgInventario, x, y, null);
        g.drawImage(imgCasillasInventario, 255, 165, null);
        // Pintar las sombras
        g.setColor(Color.BLACK);
        g.setFont(fuente_1);
        g.drawString("Inventario", 308, 133);
        g.setFont(fuente_2);
        g.drawString("Presiona 'Enter' para regresar", 153, 493);
        
        // Pintar el color blanco
        g.setColor(Color.WHITE);
        g.setFont(fuente_1);
        g.drawString("Inventario", 305, 130);
        g.setFont(fuente_2);
        g.drawString("Presiona 'Enter' para regresar", 150, 490);*/
    }
    
    public void verCaracteristicas(Graphics g){
        actual = VentanaJuego.Singleton().PartidaActual();
        
    // Experiencia, nivel, vida, magia, cuánto le falta de experiencia para el siguiente nivel.
        g.drawImage(imgCaracteristicas, x, y, null);
        
        // Pintar lo negro...
        g.setColor(Color.BLACK);
        g.setFont(fuente_1);
        g.drawString("Caracteristicas", 248, 133);
        g.setFont(fuente_2);
        g.drawString("Nivel: " + actual.getNivel(), 183, 223);
        g.drawString("Experiencia: " + actual.getExperiencia(), 183, 263);
        g.drawString("Para el siguiente nivel: " + (250 - actual.getExperiencia()), 183, 303);
        g.drawString("Mapa actual: " + actual.getEscenarioActual(), 183, 343);
        g.drawString("Presiona 'Enter' para regresar", 153, 493);
        
        // Pintar lo blanco
        g.setColor(Color.WHITE);
        g.setFont(fuente_1);
        g.drawString("Caracteristicas", 245, 130);
        g.setFont(fuente_2);
        g.drawString("Nivel: " + actual.getNivel(), 180, 220);
        g.drawString("Experiencia: " + actual.getExperiencia(), 180, 260);
        g.drawString("Para el siguiente nivel: " + (250 - actual.getExperiencia()), 180, 300);
        g.drawString("Mapa actual: " + actual.getEscenarioActual(), 180, 340);
        g.drawString("Presiona 'Enter' para regresar", 150, 490);
    }
    
    public void consultarAyuda(Graphics g){
    // Controles de la cosa ésta
        g.drawImage(imgAyuda, x, y, null);
    }
    
    public void salir(Graphics g){
        g.drawImage(imgDialogSalida, 190, 200, null);
        g.setFont(fuente_2);
        
        g.setColor(Color.BLACK);
        g.drawString("¿Salir del juego?", 253, 278);
        g.drawString("Sí", 303, 353);
        g.drawString("No", 453, 353);
        
        g.setColor(Color.WHITE);
        g.drawString("¿Salir del juego?", 250, 275);
        g.drawString("Sí", 300, 350);
        g.drawString("No", 450, 350);
        
        switch(salir){
            case Si:
                g.setColor(Color.yellow);
                g.drawString("Sí", 300, 350);
            break;
                
            case No:
                g.setColor(Color.yellow);
                g.drawString("No", 450, 350);
            break;
        }
    }
    
    public void opciones(Graphics g){
        VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.Opciones);
    }

    
    public void comando(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                next = true;
            break;
                
            case KeyEvent.VK_DOWN:
                if(opcion == Options.standBy || opcion == Options.Salir){
                    opcion = Options.guardarPartida;
                }else if(opcion == Options.guardarPartida){
                    opcion = Options.consultarInventario;
                }else if(opcion == Options.consultarInventario){
                    opcion = Options.verCaracteristicas;
                }else if (opcion == Options.verCaracteristicas){
                    opcion = Options.consultarAyuda;
                }else if(opcion == Options.consultarAyuda){
                    opcion = Options.Opciones;
                }else if(opcion == Options.Opciones){
                    opcion = Options.Regresar;
                }else if(opcion == Options.Regresar){
                    opcion = Options.Salir;
                }
            break;
                
            case KeyEvent.VK_UP:
                if(opcion == Options.standBy || opcion == Options.guardarPartida){
                    opcion = Options.Salir;
                }else if(opcion == Options.Salir){
                    opcion = Options.Regresar;
                }else if(opcion == Options.Regresar){
                    opcion = Options.Opciones;
                }else if(opcion == Options.Opciones){
                    opcion = Options.consultarAyuda;
                }else if(opcion == Options.consultarAyuda){
                    opcion = Options.verCaracteristicas;
                }else if(opcion == Options.verCaracteristicas){
                    opcion = Options.consultarInventario;
                }else if(opcion == Options.consultarInventario){
                    opcion = Options.guardarPartida;
                }
            break;
                
        }
    }
    
    public void comandoSalir(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(salir == Salir.No){
                    salir = Salir.Si;
                }else if(salir == Salir.Si){
                    salir = Salir.No; 
                }
            break;
                
            case KeyEvent.VK_RIGHT:
                if(salir == Salir.No){
                    salir = Salir.Si;
                }else if(salir == Salir.Si){
                    salir = Salir.No; 
                }
            break;
                
            case KeyEvent.VK_ENTER:
                if(salir == Salir.Si){
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.MenuPrincipal);
                }else if(salir == Salir.No){
                    next = false;
                }
            break;
        }
    }
    
    public void comandoGuardar(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(estadoPartida == GuardarPartida.No){
                    estadoPartida = GuardarPartida.Si;
                }else if(estadoPartida == GuardarPartida.Si){
                    estadoPartida = GuardarPartida.No; 
                }
            break;
                
            case KeyEvent.VK_RIGHT:
                if(estadoPartida == GuardarPartida.No){
                    estadoPartida = GuardarPartida.Si;
                }else if(estadoPartida == GuardarPartida.Si){
                    estadoPartida = GuardarPartida.No; 
                }
            break;
                
            case KeyEvent.VK_ENTER:
                if(estadoPartida == GuardarPartida.Si){
                    VentanaJuego.Singleton().guardarPartidas();
                    next = false;
                    estadoPartida = GuardarPartida.No;
                }else if(estadoPartida == GuardarPartida.No){
                    next = false;
                }
            break;
        }
    }
    
    public void comandoRegresarMenuPausa(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            next = false;
        }
    }
    
    public boolean getNext(){
        return next;
    }
    
    public boolean getRegresar(){
        return regresar;
    }
    
    public void setRegresar(boolean regresa){
        regresar = regresa;
    }
    
    public void setNext(boolean sig){
        next = sig;
    }
    
    public Options getOpcion(){
        return opcion;
    }
}
