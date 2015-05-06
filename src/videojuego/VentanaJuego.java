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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    private static Batalla batalla= new Batalla();
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
            cargarPartidas();
        }
        return instancia;
    }
    
    public void cambiaPantalla(EstadoPantalla.Pantallas nuevo){
        pantalla.cambiar(nuevo);
    }
    
    public Batalla batallaActual(){
        return batalla;
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
                        batalla.dibujaBatalla(segundo);
                        break;
                    case Perder:
                    	segundo.setColor(Color.black);
                    	segundo.fillRect(0, 0,Singleton().getWidth(), Singleton().getHeight());
                    	
                    	segundo.setColor(Color.GRAY);
                    	segundo.setFont(fuente_1);
                    	String aux="Tu leyenda ha terminado";
                    	segundo.drawString(aux,Singleton().getWidth()/2-aux.length()*8 , Singleton().getHeight()/2);
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
                batalla.comando(e);
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
    
    // Método para guardar las partidas...
    public void salvarPartida(Object partida, String archivo) throws IOException{
        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo));
        salida.writeObject(partida);
        salida.flush();
        salida.close();
    }
    
    public static Object leerPartida(String archivo) throws IOException, ClassNotFoundException{
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo));
        Object partidaGuardada = entrada.readObject();
        entrada.close();
        return partidaGuardada;
    }
    
    public void guardarPartidas(){
        
        partida1.setExperiencia(partida1.getExperiencia() + 15);
        partida1.setNivel(2);
        partida1.setVida(95);
        
        //partida1.setExperiencia(0);
        //partida1.setNivel(1);
        //partida1.setVida(100);
        
        try{
            switch(pantalla.getPartida()){
                case Partida1:
                    salvarPartida(partida1, "Partida1.obj");
                    break;
                    
                case Partida2:
                    salvarPartida(partida2, "Partida2.obj");
                    break;
                    
                case Partida3:
                    salvarPartida(partida3, "Partida3.obj");
                    break;
            }
        }catch(Exception ex){}
    }
    
    public static void cargarPartidas(){
        try{
            partida1 = (Partida)leerPartida("Partida1.obj");
            partida2 = (Partida)leerPartida("Partida2.obj");
            partida3 = (Partida)leerPartida("Partida3.obj");
        }
        catch(Exception ex){
        }
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
    
    public void nuevaBatalla(){
    	batalla=new Batalla();
    }
    
    public static Inventario getInventario(){
    	return inventario;
    }
    
}