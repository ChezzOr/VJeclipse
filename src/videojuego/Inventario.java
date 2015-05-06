package videojuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventario {
	
	public enum Status{Consulta, UsoBatalla};
	private Status estado = Status.Consulta;
	
	private Image imgInventario = DiccionarioImagenes.Singleton().imagen("Inventario.png");
	private Image imgCasillasInventario = DiccionarioImagenes.Singleton().imagen("Casillas_inventario.png");
	private boolean next = false;
	private ArrayList inventario = new ArrayList();
	
	private Font fuente_1 = new Font("Lucida Console", Font.BOLD, 28);
    private Font fuente_2 = new Font("Lucida Console", Font.BOLD, 26);
	
    
	private int x = 90, y = 68;
	
	
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
	        
	        dibujarItems(g);

	}
	
	public void guardarItem(Item e){
		if(inventario.size() < 16){
			inventario.add(e);
		}
	}
	
	public Status getEstado(){
		return estado;
	}
	
	public void dibujarItems(Graphics g){
		int k = 1, x = 269, y = 176;
		Item aux;
		
		Iterator it = inventario.iterator();
		while(it.hasNext()){
			aux = (Item)it.next();
			
			switch(aux.getTipo()){
				case vida:
					g.drawImage(aux.getImagenVida(), x, y, null);
				break;
				
				case especial:
					g.drawImage(aux.getImagenEspecial(), x, y, null);
				break;
				
				case poder:
					g.drawImage(aux.getImagenPoder(), x, y, null);
				break;
			}
			
			if(k % 4 == 0){
				x = 269;
				y += 30;
			}else{
				x += 68;
			}
			
			k++;
			
		}
	}
}
