/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defentthefort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author JPablix
 */
public class Partida implements Serializable{
    private int nivel;
    private int espaciosZombies;
    private int espaciosArmas;
    private boolean Activate;
    private boolean hasPartida;
    private float dificultad;                 //Variable que define los valores 
    Espacio espacios[] = new Espacio[625];

    public Partida() {
        init();
        this.nivel = 1;
        this.espaciosZombies = 20;
        this.espaciosArmas = 20;
        this.Activate = false;
        this.dificultad = 1;
    }
    
    public void subirNivel(){
        nivel++;
        espaciosArmas += 5;
        espaciosZombies += 5;
        
        float aumentoDif = ((new Random()).nextFloat((float) 0.21)); //Random de 0-20
        dificultad += aumentoDif;
        System.out.println("La dificultad ahora es: "+ dificultad);
    }
    
    private void init(){
        for (int i = 0; i < espacios.length; i++) {
            espacios[i] = new Espacio(i);
        }
    }

    public Espacio[] getEspacios() {
        return espacios;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getEspaciosZombies() {
        return espaciosZombies;
    }

    public int getEspaciosArmas() {
        return espaciosArmas;
    }

    public void setEspaciosZombies(int espaciosZombies) {
        this.espaciosZombies = espaciosZombies;
    }

    public void setEspaciosArmas(int espaciosArmas) {
        this.espaciosArmas = espaciosArmas;
    }

    public boolean isActivate() {
        return Activate;
    }

    public void setActivate(boolean Activate) {
        this.Activate = Activate;
    }

    public float getDificultad() {
        return dificultad;
    }

    public void setDificultad(float dificultad) {
        this.dificultad = dificultad;
    }

    public boolean isHasPartida() {
        return hasPartida;
    }

    public void setHasPartida(boolean hasPartida) {
        this.hasPartida = hasPartida;
    }
    

    
    
    
    
    
    
}
                                     