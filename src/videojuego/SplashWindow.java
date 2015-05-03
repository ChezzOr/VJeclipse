/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.Timer;

/**
 *
 * @author Danny
 */
public class SplashWindow extends JFrame{
    private int x, y;
    Image imgPortada = DiccionarioImagenes.Singleton().imagen("portada.jpg");

    public SplashWindow() {
        super();
        setUndecorated(true);
        setBounds(100, 100, 400, 586);
        setVisible(true);
        setLocationRelativeTo(null);
        x = 0;
        y = 0;
        
    }
    
    
    
    @Override
    public void paint(Graphics g){
        g.drawImage(imgPortada, 0, 0, null);
        
    }
}
