/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 *
 * @author Danny
 */
public class VentanaJuego extends JFrame implements KeyListener, MouseListener, MouseMotionListener{
    public static VentanaJuego instancia = null;
    private static MenuPrincipal menuPrincipal = new MenuPrincipal();
    private static Cronometro repintado=new Cronometro(1);
    private static EstadoPantalla pantalla= new EstadoPantalla();
    private static CargaPartida cargaPartida= new CargaPartida();
    private static IniciarPartida iniciaPartida = new IniciarPartida();
    private static Escenario escenario= new Escenario();
    private static Datos verDatos = new Datos();
    private static Opciones opciones = new Opciones();
    private static Menu menu = new Menu();
    private static Inventario inventario = new Inventario();
    
    private static Partida partida1 = new Partida();
    private static Partida partida2 = new Partida();
    private static Partida partida3 = new Partida();
    Font fuente_1 = new Font("Lucida Console", Font.BOLD, 28);
    
    public static VentanaJuego Singleton(){
        if(instancia == null){
            instancia = new VentanaJuego();
            instancia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            instancia.setBounds(100, 100, 800, 600);
            instancia.setVisible(true);
            instancia.setLocationRelativeTo(null);
            instancia.createBufferStrategy(2);
            instancia.addKeyListener(instancia);
            instancia.addMouseListener(instancia);
            instancia.addMouseMotionListener(instancia);
            instancia.setResizable(false);
        }
        return instancia;
    }
    
    public void cambiaPantalla(EstadoPantalla.Pantallas nuevo){
        pantalla.cambiar(nuevo);
    }
    
    
    @Override
    public void paint(Graphics g){
        if(repintado.esTiempo()){
            BufferStrategy buffer = this.getBufferStrategy();
            if(buffer != null){
                Graphics segundo = buffer.getDrawGraphics();
                switch(pantalla.actual()){
                    case MenuPrincipal:
                       menuPrincipal.dibujarMenuPrincipal(segundo);
                       
                       break;
                    case Splash:
                        segundo.fillOval(100,100,100,100);
                        break;
                    case CargaPartida:
                        cargaPartida.dibujarPartidas(segundo);
                        break;
                        
                    case IniciarPartida:
                        iniciaPartida.dibujarPartidas(segundo);
                        break;
                        
                    case VerDatos:
                        verDatos.dibujarDatos(segundo);
                        break;
                        
                    case Opciones:
                        opciones.dibujarOpciones(segundo);
                        break;
                    
                    case Partida1:
                    case Partida2:
                    case Partida3:
                        escenario.dibujaEscenario(segundo);
                        if(pantalla.anterior() == EstadoPantalla.Pantallas.Opciones){
                            pantalla.cambiar(EstadoPantalla.Pantallas.MenuPausa);
                        }
                        break;
                        
                    case MenuPausa:
                        menu.dibujarMenu(segundo);
                        if(menu.getRegresar()){
                            menu.setRegresar(false);
                            pantalla.cambiar(EstadoPantalla.Pantallas.Partida1);
                        }
                        break;
                    case Batalla:
                        Escenario.Singleton().dibujaBatalla(segundo);
                        break;
                    case Perder:
                    	segundo.setColor(Color.black);
                    	segundo.fillRect(0, 0,Singleton().getWidth(), Singleton().getHeight());
                    	segundo.setColor(Color.GRAY);
                    	segundo.setFont(fuente_1);
                    	String aux="Tu leyenda ha terminado";
                    	segundo.drawString(aux,Singleton().getWidth()/2-aux.length()*9 , Singleton().getHeight()/2);
                    	aux="Presiona Enter";
                    	segundo.drawString(aux,Singleton().getWidth()/2-aux.length()*9 , Singleton().getHeight()/2+30);
                    	break;
                    default:
                        menu.dibujarMenu(segundo);
                        break;
                }
                buffer.show();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    
        switch(pantalla.actual()){
            case MenuPrincipal:
                menuPrincipal.comando(e);
                break;
                
            case Splash:
                break;
                
            case CargaPartida:
                cargaPartida.comando(e);
                break;
                
            case IniciarPartida:
                iniciaPartida.comando(e);
                break;
                
            case Opciones:
                opciones.comando(e);
                break;
                
            case Partida1:
                if(KeyEvent.VK_ESCAPE == e.getKeyCode()){
                    pantalla.cambiar(EstadoPantalla.Pantallas.MenuPausa);
                }
                escenario.comandoTecla(e);
                break;
                
            case MenuPausa:
                if(menu.getNext()){
                    switch(menu.getOpcion()){
                        case Salir:
                            menu.comandoSalir(e);
                            
                        case guardarPartida:
                            menu.comandoGuardar(e);
                        break;
                            
                        case consultarAyuda:
                        case verCaracteristicas:
                        case consultarInventario:
                            menu.comandoRegresarMenuPausa(e);
                            break;
                            
                        case Opciones:
                            pantalla.cambiar(EstadoPantalla.Pantallas.Opciones);
                        break;
                    }
                }else{
                    menu.comando(e);
                }
                
                break;
            case Batalla:
            	escenario.Singleton().actualB().comando(e);
                break;
            case Perder:
            	if(e.getKeyCode()==KeyEvent.VK_ENTER){
            		pantalla.cambiar(EstadoPantalla.Pantallas.MenuPrincipal);
            	}
            	break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	
    	int x = e.getX();
    	int y = e.getY();
    	
    	
        if(pantalla.actual() == EstadoPantalla.Pantallas.MenuPrincipal){
            menuPrincipal.Siguiente = true;
        }else if(pantalla.actual() == EstadoPantalla.Pantallas.IniciarPartida){
            iniciaPartida.siguiente = true;
        }else if(pantalla.actual() == EstadoPantalla.Pantallas.CargaPartida){
            cargaPartida.Siguiente = true;
        }else if(pantalla.actual() == EstadoPantalla.Pantallas.VerDatos){
            verDatos.siguiente = true;
        }else if(pantalla.actual() == EstadoPantalla.Pantallas.Opciones){
            opciones.Siguiente = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
        if(pantalla.estado == EstadoPantalla.Pantallas.MenuPrincipal){
            menuPrincipal.detectarMouse(me);
        }else if(pantalla.estado == EstadoPantalla.Pantallas.IniciarPartida){
            iniciaPartida.detectarMouse(me);
        }else if(pantalla.estado == EstadoPantalla.Pantallas.CargaPartida){
            cargaPartida.detectarMouse(me);
        }else if(pantalla.estado == EstadoPantalla.Pantallas.VerDatos){
            verDatos.detectaMouse(me);
        }else if(pantalla.estado == EstadoPantalla.Pantallas.Opciones){
            opciones.detectaMouse(me);
        }
    }


    public static MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public static EstadoPantalla getPantalla() {
        return pantalla;
    }

    public static CargaPartida getCargaPartida() {
        return cargaPartida;
    }

    public static IniciarPartida getIniciaPartida() {
        return iniciaPartida;
    }

    public static Escenario getEscenario() {
        return escenario;
    }

    public static Datos getVerDatos() {
        return verDatos;
    }

    public static Opciones getOpciones() {
        return opciones;
    }

    public static Menu getMenu() {
        return menu;
    }
    
    
    public Partida PartidaActual(){
        Partida nueva = new Partida();
        
        switch(pantalla.getPartida()){
            case Partida1:
                nueva = partida1;
                break;
                
            case Partida2:
                nueva = partida2;
                break;
                
            case Partida3:
                nueva = partida3;
                break;
        }
        
        return nueva;
    }

    
    public static Inventario getInventario(){
    	return inventario;
    }
    
    public static void setInventario(Inventario inv){
    	inventario = inv;
    }
    
}