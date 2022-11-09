/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package defentthefort;

import Arma.Arma;
import Zombie.Zombie;
import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author JPablo
 */
public class Espacio implements Serializable{
    int ID;
    boolean hasZombie;
    boolean hasArma;
    JButton boton;
    Zombie zombie;
    Arma arma;
    

    public Espacio(int ID) {
        this.hasZombie = false;
        this.hasArma = false;
        this.ID = ID;
    }

    public void setBoton(JButton boton) {
        this.boton = boton;
    }
    public JButton getBoton() {
        return boton;
    }
    
    
    public boolean isHasZombie() {
        return hasZombie;
    }

    public void setHasZombie(boolean hasZombie) {
        this.hasZombie = hasZombie;
    }

    public boolean isHasArma() {
        return hasArma;
    }

    public void setHasArma(boolean hasArma) {
        this.hasArma = hasArma;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int getID() {
        return ID;
    }
    
    public int getPosition(char coord){
        int  X = ID%25;
        int Y = ID/25;
        
        if (coord == 'x' || coord == 'X'){
            return (int)X;
        }
        else if (coord == 'y' || coord == 'Y'){
            return Y;
        }else 
            return -1;
    }
    
    
}
