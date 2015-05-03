/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videojuego;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

/**
 *
 * @author Azu B
 */
public class Partida implements Serializable{
    private String nombreProtagonista;
    private int nivel;
    private int experiencia;
    private ArrayList<Item> items;
    private String escenarioActual;
    private int vida;
    
    public Partida(){
        nombreProtagonista = "";
        nivel = 1;
        experiencia = 0;
        items = null;
        escenarioActual = "Mastrum";
        vida = 100;
    }
    
    public Partida(String nombre){
        nombreProtagonista = nombre;
    }

    public String getNombreProtagonista() {
        return nombreProtagonista;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getEscenarioActual() {
        return escenarioActual;
    }

    public void setNombreProtagonista(String nombreProtagonista) {
        this.nombreProtagonista = nombreProtagonista;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public void setItems(ArrayList<Item> Items) {
        this.items = Items;
    }

    public void setEscenarioActual(String escenarioActual) {
        this.escenarioActual = escenarioActual;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    
}
