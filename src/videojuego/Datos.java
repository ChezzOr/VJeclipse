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
import java.awt.event.MouseEvent;

/**
 *
 * @author Azu B
 */
public class Datos {
    public enum Personajes{StandBy,Protagonista, Madre, Iris, Richard, Elizabeth, Vik, regresar};
    
    int alto = VentanaJuego.Singleton().getHeight();
    int ancho = VentanaJuego.Singleton().getWidth();// dimensiones pantalla
    
    private int x = 0, y = 0;
    
    Font fuente_1 = new Font("TimesRoman", Font.BOLD, 48);
    Font fuente_2 = new Font("TimesRoman", Font.BOLD, 24);
    Image imgPortada = DiccionarioImagenes.Singleton().imagen("datos.jpg");
    
    // Imágenes de los personajes
    Image imgProtagonista = DiccionarioImagenes.Singleton().imagen("prota_mini.png");
    Image imgRichard = DiccionarioImagenes.Singleton().imagen("richard_mini.png");
    Image imgVik = DiccionarioImagenes.Singleton().imagen("vik_mini.png");
    Image imgIris = DiccionarioImagenes.Singleton().imagen("iris_mini.png");
    Image imgElizabeth = DiccionarioImagenes.Singleton().imagen("elizabeth_mini.png");
    Image imgMadre = DiccionarioImagenes.Singleton().imagen("mama_mini.png");
    Rectangle rectRegresar = new Rectangle(15, alto - 45, 112, 32);
    Rectangle rectProtagonista = new Rectangle(ancho / 2 - 180, 145, 50, 70);
    Rectangle rectElizabeth = new Rectangle(ancho / 2 - 30, 145, 50, 70);
    Rectangle rectRichard = new Rectangle(ancho / 2 + 120, 145, 50, 70);
    Rectangle rectIris = new Rectangle(ancho / 2 - 180, 270, 50, 70);
    Rectangle rectVik = new Rectangle(ancho / 2 - 30, 270, 50, 70);
    Rectangle rectMadre = new Rectangle(ancho / 2 + 120, 270, 50, 70);
    String nombre = "";
    String historia = "";
    
    public Personajes personaje = Personajes.StandBy;
    boolean siguiente = false;
    
    public void dibujarDatos(Graphics g){
        g.setFont(fuente_1);
        g.drawImage(imgPortada, x, y, null);
        g.setColor(Color.white);
        g.drawString("Personajes", ancho/2-120, 80);
        g.setFont(fuente_2);
        g.drawString("Regresar", 20, alto-20);
        
        nombre = "";
        //Dibujar las imágenes
        g.drawImage(imgProtagonista, ancho / 2 - 175, 150, null);
        g.drawImage(imgElizabeth, ancho / 2 - 25, 150, null);
        g.drawImage(imgRichard, ancho / 2 + 125, 150, null);
        g.drawImage(imgIris, ancho / 2 - 175, 275, null);
        g.drawImage(imgVik, ancho / 2 - 25, 275, null);
        g.drawImage(imgMadre, ancho / 2 + 125, 275, null);
        
        // g.drawString(nombre, ancho / 2 - 225, 400);
        
        switch(personaje){
            case Protagonista:
                nombre = "Tú";
                g.drawString(nombre, ancho / 2 - 225, 400);
                nombre = "Chico con pocas habilidades pero grandes";
                g.drawString(nombre, ancho / 2 - 225, 440);
                nombre = "capacidades que te ayudaran en tu travesia";
                g.drawString(nombre, ancho / 2 - 225, 470);
                break;
                
            case Elizabeth:
                nombre = "Elizabeth";
                g.drawString(nombre, ancho / 2 - 225, 400);
                nombre = "Alegre y gentil, te acomapañaria";
                g.drawString(nombre, ancho / 2 - 225, 440);
                nombre = "hasta el final";
                g.drawString(nombre, ancho / 2 - 225, 470);
                break;
                
            case Richard:
                nombre = "Richard";
                g.drawString(nombre, ancho / 2 - 225, 400);
                nombre = "Rudo y fuerte, muy dificil de derrotar";
                g.drawString(nombre, ancho / 2 - 225, 440);
                break;
                
            case Iris:
                nombre = "Iris";
                g.drawString(nombre, ancho / 2 - 225, 400);
                nombre = "Inteligente y valiente, no querras estar";
                g.drawString(nombre, ancho / 2 - 225, 440);
                nombre = "en su camino";
                g.drawString(nombre, ancho / 2 - 225, 470);
                break;
                
            case Vik:
                nombre = "Vik";
                g.drawString(nombre, ancho / 2 - 225, 400);
                nombre = "Ermitaño gruñon, pero muy buen maestro";
                g.drawString(nombre, ancho / 2 - 225, 440);
                break;
                
            case regresar:
                g.setColor(Color.cyan);
                g.drawString("Regresar", 20, alto-20);
                if(siguiente){
                    siguiente = false;
                    VentanaJuego.Singleton().cambiaPantalla(EstadoPantalla.Pantallas.MenuPrincipal);
                }
                break;
                
            case Madre:
                nombre = "Mamá";
                g.drawString(nombre, ancho / 2 - 225, 400);
                nombre = "Daria la vida por ti";
                g.drawString(nombre, ancho / 2 - 225, 440);
                break;
        }
    }
    
    public void detectaMouse(MouseEvent e){
        int posX = e.getX();
        int posY = e.getY();
        
        if(rectRegresar.contains(posX, posY)){
            personaje = Personajes.regresar;
        }else if(rectProtagonista.contains(posX, posY)){
            personaje = Personajes.Protagonista;
        }else if(rectElizabeth.contains(posX, posY)){
            personaje = Personajes.Elizabeth;
        }else if(rectRichard.contains(posX, posY)){
            personaje = Personajes.Richard;
        }else if(rectIris.contains(posX, posY)){
            personaje = Personajes.Iris;
        }else if(rectVik.contains(posX, posY)){
            personaje = Personajes.Vik;
        }else if(rectMadre.contains(posX, posY)){
            personaje = Personajes.Madre;
        }else{
            personaje = Personajes.StandBy;
        }
    }
    
}
