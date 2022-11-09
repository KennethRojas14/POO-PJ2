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
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author 167069
 */
public class Zombie extends Thread implements Serializable{
    private int vida;
    private int ataque;
    private int campos;
    private int rango;
    private String nombre;
    private ImageIcon imagen;
    private Espacio espacios[];
    private Partida partida;
    private ArrayList<Integer> listaNumeros;
    private ArrayList<String> ataqueEjercido;
    private ArrayList<String> ataqueRecibido;
    private int posicion;
    
    
    public Zombie(int pVida, int pAtaque, int pCampo, int pRango, String pNombre, String pImagen, Espacio espacios[], Partida partida) {
        this.vida = pVida;
        this.ataque = pAtaque;
        this.campos = pCampo;
        this.rango = pRango;
        this.nombre = pNombre;
        this.imagen = new ImageIcon("src\\Imagenes\\" + pImagen);
        this.espacios = espacios;
        this.posicion = 0;
        this.ataqueEjercido = new ArrayList<String>();
        this.ataqueRecibido = new ArrayList<String>();
        this.partida = partida;
        
    }
    
    
    
    public void aparecerZombie(){ //Pone la imagen
        espacios[posicion].getBoton().setIcon(imagen);
        espacios[posicion].setHasZombie(true);
        espacios[posicion].setZombie(this);
    }
    
    public void desaparecerZombie(){ //Quita la imagen
        espacios[posicion].getBoton().setIcon(null);
        espacios[posicion].setHasZombie(false);
        espacios[posicion].setZombie(null);
        
    }
    
    @Override
    public void run(){
        listaNumeros = new ArrayList<Integer>();
        //--------------------APARECER ZOMBIES----------------------------------
        //----------------------GENERA NUMERO ALEATORIO-------------------------
        ImageIcon p = imagen;
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int aleatorio1  = tlr.nextInt(0, 25);
        
        ThreadLocalRandom tlr2 = ThreadLocalRandom.current();
        int aleatorio2 = tlr2.nextInt(600, 625);
        
        listaNumeros.add((int)aleatorio1);
        listaNumeros.add((int)aleatorio2);
        
        for(int i = 25; i < 626; i += 25){
           listaNumeros.add(i);
           listaNumeros.add(i-1);
           System.out.println("Numero i: " + i);
        }
        
        ThreadLocalRandom tlr3 = ThreadLocalRandom.current();
        int randomNum = tlr3.nextInt(0, listaNumeros.size());
        
        
        this.posicion = listaNumeros.get(randomNum);
        
        
        //---------------------------------------------------------------------
        
        aparecerZombie();
        
        //movimiento(espacios[posicion]); //Se produce el movimiento del zombie   
    }
    
    public void movimiento(Espacio espacio){
        JButton btn;
        int cuadrante = validar(posicion);
        while(posicion != 312){
                try{
                  sleep(500);
                  desaparecerZombie();
                    switch (cuadrante) {
                        case 1:
                            
                            btn = espacios[posicion].getBoton();
                            if(movimientoLineal(posicion).equals("baja")){
                                posicion += 25;
                            }
                            else if(movimientoLineal(posicion).equals("sube")){
                                posicion -= 25;
                            }
                            else if(movimientoLineal(posicion).equals("derecha")){
                                posicion += 1;
                            }
                            else if(movimientoLineal(posicion).equals("izquierda")){
                                posicion -= 1;
                            }
                            else{
                                posicion += 24;
                            }
                            aparecerZombie();
                            btn.setBackground(Color.red);
                            //verificarArea(btn, posicion);
                            break;
                        case 2:
                            
                            
                            btn = espacios[posicion].getBoton();
                            if(movimientoLineal(posicion).equals("baja")){
                                posicion += 25;
                            }
                            else if(movimientoLineal(posicion).equals("sube")){
                                posicion -= 25;
                            }
                            else if(movimientoLineal(posicion).equals("derecha")){
                                posicion += 1;
                            }
                            else if(movimientoLineal(posicion).equals("izquierda")){
                                posicion -= 1;
                            }
                            else
                                posicion += 26;
                           
                            aparecerZombie();
                            btn.setBackground(Color.red);
                            //verificarArea(btn, posicion);
                            break;
                        case 3:
                            //aparecerZombie(espacio);
                            
                            btn = espacios[posicion].getBoton();
                            if(movimientoLineal(posicion).equals("baja")){
                                posicion += 25;
                            }
                            else if(movimientoLineal(posicion).equals("sube")){
                                posicion -= 25;
                            }
                            else if(movimientoLineal(posicion).equals("derecha")){
                                posicion += 1;
                            }
                            else if(movimientoLineal(posicion).equals("izquierda")){
                                posicion -= 1;
                            }
                            else
                                posicion -= 24;
                            
                            
                            aparecerZombie();
                            btn.setBackground(Color.red);
                            //verificarArea(btn, posicion);
                            break;
                        default:
                            //aparecerZombie(espacio);
                            
                            btn = espacios[posicion].getBoton();
                            if(movimientoLineal(posicion).equals("baja")){
                                posicion += 25;
                            }
                            else if(movimientoLineal(posicion).equals("sube")){
                                posicion -= 25;
                            }
                            else if(movimientoLineal(posicion).equals("derecha")){
                                posicion += 1;
                            }
                            else if(movimientoLineal(posicion).equals("izquierda")){
                                posicion -= 1;
                            }
                            else
                                posicion -= 26;
                            
                            aparecerZombie();
                            btn.setBackground(Color.red);
                            //verificarArea(btn, posicion);
                            break;
                    }
                }
                catch( InterruptedException ex){ 
                }
        //contador++;
        }
   
    }
    private String movimientoLineal(int pPosicion){
        double number = (double)pPosicion/25;
        long iPart = (long) number;
        double fPart = number - iPart;
        int x = ((int) (fPart*25))+1;
        int y = (pPosicion/25) + 1;
        System.out.println("X: " + x);
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
        btn = espacios[posicion].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion-26].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion-25].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion-24].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion-1].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion+1].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion+24].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion+25].getBoton();
        btn.setBackground(Color.red);
        
        btn = espacios[posicion+26].getBoton();
        btn.setBackground(Color.red);
    }
    private int validar(int pPosicion){
        double number = (double)pPosicion/25;
        long iPart = (long) number;
        double fPart = number - iPart;
        int x = ((int) (fPart*25))+1;
        int y = (pPosicion/25) + 1;
        
        System.out.println("X = " + x + " Y = " + y);
        
        if(x >= 0 && x < 13 && y >= 0 && y < 13){
            System.out.println("Se encuentra en el segundo cuadrante");
            return 2;
        }
        else if(x >= 12 && x <= 25 && y >= 0 && y <= 12){
            System.out.println("Se encuentra en el primer cuadrante");
            return 1;
        }
        else if(x >= 0 && x < 13 && y >= 12 && y <= 25 ){
            System.out.println("Se encuentra en el tercer cuadrante");
            return 3;
        }   
        else{
           System.out.println("Se encuentra en el cuarto cuadrante");
            return 4; 
        }          
    }

    public Espacio[] getEspacios() {
        return espacios;
    }

    public void setEspacios(Espacio[] espacios) {
        this.espacios = espacios;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    public ArrayList<Integer> getListaNumeros() {
        return listaNumeros;
    }

    public void setListaNumeros(ArrayList<Integer> listaNumeros) {
        this.listaNumeros = listaNumeros;
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
//    public void setImagen(Image imagen) {
//        this.imagen = imagen;
//    }
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
//    public JButton getBtnPosicion() {
//        return btnPosicion;
//    }
//    public void setBtnPosicion(JButton txfPosicion) {
//        this.btnPosicion = txfPosicion;
//    }   
}