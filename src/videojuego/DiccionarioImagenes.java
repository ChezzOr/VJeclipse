/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Danny
 */
public class DiccionarioImagenes {
    private static DiccionarioImagenes instancia = null;
    private HashMap <String,ImageIcon> gifs = new HashMap<String,ImageIcon>();
    private HashMap <String,BufferedImage> imgs = new HashMap<String,BufferedImage>();
    
    public static DiccionarioImagenes Singleton(){
        
        if(instancia==null){
            instancia = new DiccionarioImagenes(); 
        }
        
        return instancia;   
    }
    
    public void cargaCarpeta(String carpeta){
        
        //cargar archivos de la carpeta
        File acceso = new File(carpeta);
        String archivos[] = acceso.list();
        
        //ciclo para recorrer la lista
        for(int i=0; i<archivos.length; i++){
            //procesar una imagen a la vez
            //recuperar la imagen
            String llave = archivos[i];
            
            //preguntar extension para tratar diferente
            if(llave.endsWith(".gif")){
                //agregar a los gifs
                ImageIcon nuevoGif = new ImageIcon(carpeta + "/" + llave);
                gifs.put(llave, nuevoGif);
            }
                else if(llave.endsWith(".jpg")|| llave.endsWith(".png")){
                BufferedImage nuevaImg=null;
                try {
                    //agregar los jpg y png
                    nuevaImg= ImageIO.read(new File(carpeta + "/" + llave));
                } catch (IOException ex) {
                    Logger.getLogger(DiccionarioImagenes.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                imgs.put(llave, nuevaImg);
                
            }
            
        }
    }
    
    public void eliminaCarpeta(String carpeta){
        File acceso = new File(carpeta);
        String archivos[] = acceso.list();
        
        //ciclo para recorrer la lista
        for(int i=0; i<archivos.length; i++){
            //procesar una imagen a la vez
            //recuperar la imagen
            String llave = archivos[i];
            
            //preguntar extension para tratar diferente
            if(llave.endsWith(".gif")){
                //agregar a los gifs
                gifs.remove(llave);
            }
            else if(llave.endsWith(".jpg")|| llave.endsWith(".png")){
                imgs.remove(llave);
                
            }
            
        }
        
    }
    
     Image imagen(String nombre){
        
        if(nombre.endsWith(".gif")){
            return gifs.get(nombre).getImage();
        }
        
        if(nombre.endsWith(".jpg") || nombre.endsWith(".png")){
            return imgs.get(nombre);
        }
        
        return null;
        
    }
  
    Image fragmento(String nombre, int xini , int yini, int ancho, int alto){
        if(nombre.endsWith(".gif")){
                //agregar a los gifs
                BufferedImage imagen = (BufferedImage)gifs.get(nombre).getImage();
                return imagen.getSubimage(xini, yini, ancho, alto);
            }
            else if(nombre.endsWith(".jpg")|| nombre.endsWith(".png")){
                imgs.get(nombre).getSubimage(xini, yini, ancho, alto);
                
            }
        return null;
    }
}
