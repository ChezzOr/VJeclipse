/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videojuego;


import javax.sound.sampled.*;
/**
 *
 * @author Danny
 */
public class Musica {
    
    private static Musica instancia = null;
    private static AudioInputStream musica;
    private static Clip clip;
    //private HashMap <String,AudioInputStream> wav = new HashMap<String,AudioInputStream>();
    
    public static Musica Singleton(){
        
        if(instancia == null){
            instancia = new Musica();
        }
        return instancia;
        
    }
    
    public void empiezaMusica(String nombre){
        try {
                musica = AudioSystem.getAudioInputStream(getClass().getResource(nombre));
                AudioFormat baseFormat = musica.getFormat();
		AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                                    baseFormat.getSampleRate(),
                                                    16,baseFormat.getChannels(),
                                                    baseFormat.getChannels() * 2,
                                                    baseFormat.getSampleRate(),
                                                    false);
                AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, musica);
                clip = AudioSystem.getClip();
                clip.open(dais);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void correrMusica(String nombre){
        if(clip.isRunning()){
            clip.stop();
            try {
                musica = AudioSystem.getAudioInputStream(getClass().getResource(nombre));
                clip = AudioSystem.getClip();
                clip.open(musica);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {}
        } else {
            try {
                musica = AudioSystem.getAudioInputStream(getClass().getResource(nombre));
                clip = AudioSystem.getClip();
                clip.open(musica);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {}
        }
    }
    
    public void detenerMusica(){
        if(clip.isRunning()){
            clip.stop();
        }
    }
    
    public void seguircorriendo(){
        if(!clip.isRunning()){
            clip.start();
        }
    }
}
