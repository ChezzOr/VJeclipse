/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

/**
 *
 * @author Danny
 */
public class Videojuego {
    /**
     * @param args the command line arguments
     */
    int i;
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        DiccionarioImagenes.Singleton().cargaCarpeta("Imagenes");
        DiccionarioImagenes.Singleton().cargaCarpeta("Imagenes/Escenarios");
        SplashWindow splash = new SplashWindow();
        Thread.sleep(3000);
        splash.setVisible(false);
        Musica.Singleton().empiezaMusica("MenuPrincipal.wav");
        while(true){
            try{    
                VentanaJuego.Singleton().repaint();
            }catch(Exception e){}
        }
    }
    
}
