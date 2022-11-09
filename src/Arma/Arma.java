/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arma;

import Zombie.Zombie;
import defentthefort.Espacio;
import defentthefort.Partida;
import java.awt.Image;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author JPablix
 */
public abstract class Arma extends Thread implements Serializable  {
    private int vida;
    private int ataque;
    private int campos;
    private int rango;
    private String nombre;
    private Espacio espacio;
    private ImageIcon imagen;
    private ArrayList<String> ataquesEjercidos;
    private ArrayList<String> ataquesRecibidos;
    private Espacio matriz[];
    private Partida partida;

    public  Arma(int vida, int ataque, int campos, int rango, String nombre, Espacio espacio, Partida partida) {
        this.vida = vida;
        this.ataque = ataque;
        this.campos = campos;
        this.rango = rango;
        this.nombre = nombre;
        this.espacio = espacio;
        this.matriz = partida.getEspacios();
        this.ataquesEjercidos = new ArrayList<String>();
        this.ataquesRecibidos = new ArrayList<String>();
        this.partida = partida;
    }
    
   @Override
    public void run(){
        while (partida.isActivate()){
            try {
                sleep(1000);
                if (buscarObjetivo()){
                    System.out.println(this.getNombre()+" ataco");
                }
            } catch (Exception e) {}
        }
    }
    
    public boolean buscarObjetivo() {
        for (int i = 0; i < matriz.length; i++) {
            if (Math.abs(matriz[i].getPosition('x') - espacio.getPosition('x')) <= rango && 
                Math.abs(matriz[i].getPosition('y') - espacio.getPosition('y')) <= rango){
                
                if (matriz[i].isHasZombie()){          //Si el espacio encontrado tiene zombie se procede con el ataque 
                    atacar(matriz[i]);                 //Atacar
                    return true;                       //Retornar Booleano
                }
            }
        }
        return false;                                  //Retornar Booleano
    }
    
    public  void atacar(Espacio zombieEncontrado){
        Zombie zombie = zombieEncontrado.getZombie();
        zombie.setVida(zombie.getVida()-this.getAtaque());
        //zombie.getAtaquesRecibidos().add(this.getNombre());
        this.getAtaquesEjercidos().add(zombie.getNombre());
        if (zombie.getVida() <= 0){
            //zombie.desaparecer();
        }
    }
        
    public void aparecer(){ //Pone la imagen
        this.espacio.getBoton().setIcon(imagen);
        this.espacio.setHasArma(true);
        this.espacio.setArma(this);
        System.out.println("("+this.espacio.getPosition('x')+","+ this.espacio.getPosition('y')+") " + nombre +" Buscando objetivo...");

    }
    
    public void desaparecer(){ //Quita la imagen
        this.espacio.getBoton().setIcon(null);
        this.espacio.setHasArma(false);
        this.espacio.setArma(null);
    } 
    
    //-------------GETTERS Y SETTERS
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        this.vida = vida;
    }
    public int getCampos() {
        return campos;
    }
    public void setCampos(int campos) {
        this.campos = campos;
    }
    public ImageIcon getImagen() {
        return imagen;
    }
    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Espacio getEspacio() {
        return espacio;
    }
    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }
    public void recibirDaño(int daño){
        this.vida = this.vida - daño;
    }
    public ArrayList<String> getAtaquesEjercidos() {
        return ataquesEjercidos;
    }
    public void setAtaquesEjercidos(ArrayList<String> ataquesEjercidos) {
        this.ataquesEjercidos = ataquesEjercidos;
    }
    public ArrayList<String> getAtaquesRecibidos() {
        return ataquesRecibidos;
    }
    public void setAtaquesRecibidos(ArrayList<String> ataquesRecibidos) {
        this.ataquesRecibidos = ataquesRecibidos;
    }
    
    
    
    
    
}
