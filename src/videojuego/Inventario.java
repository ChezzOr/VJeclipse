package videojuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Inventario {
	
	public enum Status{Consulta, UsoBatalla};
	private Status estado = Status.Consulta;
	
	private Image imgInventario = DiccionarioImagenes.Singleton().imagen("Inventario.png");
	private Image imgCasillasInventario = DiccionarioImagenes.Singleton().imagen("Casillas_inventario.png");
	private boolean next = false;
	private ArrayList inventario = new ArrayList();
	
	private Font fuente_1 = new Font("Lucida Console", Font.BOLD, 28);
    private Font fuente_2 = new Font("Lucida Console", Font.BOLD, 26);
    
    private Image pocion = DiccionarioImagenes.Singleton().imagen("Inventario/pocion_salud.png");
	
    
	private int x = 90, y = 68;
	
	public Inventario(){
		guardarItem();
	}
	
	public void dibujaConsulta(Graphics g){
		 g.drawImage(imgInventario, x, y, null);
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
	        g.drawString("Presiona 'Enter' para regresar", 150, 490);

	}
	
	public void guardarItem(){
		//inventario.add(pocion);
	}
	
	public Status getEstado(){
		return estado;
	}
	
	public void dibujarItems(Graphics g){
		//g.drawImage((Image)inventario.get(0), 267, 175, null);
	}
}
