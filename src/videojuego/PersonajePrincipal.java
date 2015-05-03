/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

import java.awt.Graphics;

/**
 *
 * @author Danny
 */
public class PersonajePrincipal {
    
    private int x, y;
    private String url;
    
    public PersonajePrincipal(int x, int y, String url) {
        this.x = x;
        this.y = y;
        this.url = url;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public void dibujarPersonaje(Graphics g){
        g.drawImage(DiccionarioImagenes.Singleton().imagen(url), x, y, null);
    }
    
    public void avanzar(int direccion){
        switch(direccion){
            case 0:
            break;
            case 1:
                setX(getX()+4);
            break;
            case 2:
                setY(getY()-4);
            break;
            case 3:
                setX(getX()-4);
            break;
            case 4:
                setY(getY()+4);
            break;
        }
    }
    
    public void ataqueCuerpoACuerpo(){
        
    }
    
    public void iniciarConversacion(){
        
    }
    
}
