/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zombie;

import defentthefort.Espacio;
import defentthefort.GUIPartida;
import defentthefort.Partida;
import java.awt.Color;
import java.awt.Image;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author 167069
 */
public class Zombie2 extends Thread implements Serializable{
    private int vida;
    private int ataque;
    private int campos;
    private int rango;
    private int posicion;
    private String nombre;
    private Espacio espacio;
    private ImageIcon imagen;
    private Partida partida;
    private ArrayList<String> ataqueEjercido;
    private ArrayList<String> ataqueRecibido;
    private Espacio matriz[];
    
    
    public Zombie2(int vida, int ataque, int campos, int rango ,String nombre, Partida partida, int posicion) {
        this.vida = vida;
        this.ataque = ataque;
        this.campos = campos;
        this.rango = rango;
        this.nombre = nombre;
        this.matriz = partida.getEspacios();
        this.espacio = matriz[posicion];
        this.matriz = partida.getEspacios();
        this.ataqueEjercido = new ArrayList<String>();
        this.ataqueRecibido = new ArrayList<String>();
        this.partida = partida;
    }
      
    
    public void aparecerZombie(){ //Pone la imagen
        this.espacio.getBoton().setIcon(imagen);
        this.espacio.setHasZombie(true);
        //this.espacio.setZombie(this);
    }
    
    public void desaparecerZombie(){ //Quita la imagen
        this.espacio.getBoton().setIcon(null);
        this.espacio.setHasZombie(false);
        this.espacio.setZombie(null);
        
    }
    
    @Override
    public void run(){
        movimiento(matriz[this.getPosicion()], this.getPosicion());//Se produce el movimiento del zombie
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    public void verificarArma(int posicion){
        if(matriz[posicion].isHasArma() == true){
            atacar(matriz[posicion]);
        }
    }
    
    public void movimiento(Espacio espacio, int posicion){
        
        int cuadrante = validar(posicion);
        while(posicion+1 != 312){
                try{
                  sleep(1000); 
                    switch (cuadrante) {
                        case 1:
                            
                            if(movimientoLineal(posicion).equals("baja")){
                                this.setPosicion(posicion+25);
                            }
                            else if(movimientoLineal(posicion).equals("sube")){
                                this.setPosicion(posicion-25);
                            }
                            else if(movimientoLineal(posicion).equals("derecha")){
                                this.setPosicion(posicion+1);
                            }
                            else if(movimientoLineal(posicion).equals("izquierda")){
                                this.setPosicion(posicion-1);
                            }
                            else{
                                this.setPosicion(posicion+24);
                            }
                            
                            //COLOCA EL ZOMBIE
                            this.espacio = matriz[posicion];
                            this.aparecerZombie();
                            break;
                            
                        case 2:
                             if(movimientoLineal(posicion).equals("baja")){
                                this.setPosicion(posicion+25);
                            }
                            else if(movimientoLineal(posicion).equals("sube")){
                                this.setPosicion(posicion-25);
                            }
                            else if(movimientoLineal(posicion).equals("derecha")){
                                this.setPosicion(posicion+1);
                            }
                            else if(movimientoLineal(posicion).equals("izquierda")){
                                this.setPosicion(posicion-1);
                            }
                            else{
                                verificarArma(posicion+26);
                                this.setPosicion(posicion+26);
                            }
                            //COLOCA EL ZOMBIE
                            this.espacio = matriz[posicion];
                            this.aparecerZombie();
                            break;
                            
                        case 3: 
                            if(movimientoLineal(posicion).equals("baja")){
                                this.setPosicion(posicion+25);
                            }
                            else if(movimientoLineal(posicion).equals("sube")){
                                this.setPosicion(posicion-25);
                            }
                            else if(movimientoLineal(posicion).equals("derecha")){
                                this.setPosicion(posicion+1);
                            }
                            else if(movimientoLineal(posicion).equals("izquierda")){
                                this.setPosicion(posicion-1);
                            }
                            else{
                                this.setPosicion(posicion-24);
                            }
                            
                            //COLOCA EL ZOMBIE
                            this.espacio = matriz[posicion];
                            this.aparecerZombie();
                            break;
                        default:        
                            if(movimientoLineal(this.getPosicion()).equals("baja")){
                                this.setPosicion(posicion+25);
                            }
                            else if(movimientoLineal(this.getPosicion()).equals("sube")){
                                this.setPosicion(posicion-25);
                            }
                            else if(movimientoLineal(this.getPosicion()).equals("derecha")){
                                this.setPosicion(posicion+1);
                            }
                            else if(movimientoLineal(this.getPosicion()).equals("izquierda")){
                                this.setPosicion(posicion-1);
                            }
                            else{
                                this.setPosicion(posicion-26);
                            }
                            //COLOCA EL ZOMBIE
                            this.espacio = matriz[posicion];
                            this.aparecerZombie();
                            break;
                    }
                }
                catch( InterruptedException ex){ 
                }
        }
   
    }
    private String movimientoLineal(int pPosicion){
        double number = (double)pPosicion/25;
        long iPart = (long) number;
        double fPart = number - iPart;
        int x = ((int) (fPart*25))+1;
        int y = (pPosicion/25) + 1;
        
        if(x == 13 && y >= 0 && y <= 12){
            return "baja";
        }
        else if(y == 13 && x >= 0 && x < 13){
            return "derecha";
        }
        else if(x == 13 && y >= 12 && y <= 25){
            return "sube";
        }
        else if(y == 13 && x >= 12 && x <= 25){
            return "izquierda";
        }
        return "ninguno";
    }
    
    private void verificarArea(JButton btn, int posicion){
        btn = matriz[posicion].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion-26].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion-25].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion-24].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion-1].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion+1].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion+24].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion+25].getBoton();
        btn.setBackground(Color.red);
        
        btn = matriz[posicion+26].getBoton();
        btn.setBackground(Color.red);
    }
    private int validar(int pPosicion){
        double number = (double)pPosicion/25;
        long iPart = (long) number;
        double fPart = number - iPart;
        int x = ((int) (fPart*25))+1;
        int y = (pPosicion/25) + 1;
                
        if(x >= 0 && x < 13 && y >= 0 && y < 13){
            return 2;
        }
        else if(x >= 12 && x <= 25 && y >= 0 && y <= 12){
            return 1;
        }
        else if(x >= 0 && x < 13 && y >= 12 && y <= 25 ){
            return 3;
        }   
        else{
            return 4; 
        }          
    }
    
    private void atacar(Espacio espacio){
        while(espacio.isHasArma()){
            espacio.getArma().setVida(vida-ataque);//
            ataqueEjercido.add(espacio.getArma().getNombre());
            espacio.getArma().getAtaquesRecibidos().add(this.getNombre());
            System.out.println("ATACANDO" + ataque+" DE VIDA");
            if(espacio.getArma().getVida() <= 0){
                espacio.getArma().desaparecer();
            }
        }
    }
    
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

    public ArrayList<String> getAtaqueEjercido() {
        return ataqueEjercido;
    }

    public void setAtaqueEjercido(ArrayList<String> ataqueEjercido) {
        this.ataqueEjercido = ataqueEjercido;
    }

    public ArrayList<String> getAtaqueRecibido() {
        return ataqueRecibido;
    }

    public void setAtaqueRecibido(ArrayList<String> ataqueRecibido) {
        this.ataqueRecibido = ataqueRecibido;
    }
}
