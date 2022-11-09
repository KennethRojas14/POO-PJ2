/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDeDatos;

import defentthefort.GUIPartida;
import defentthefort.Partida;
import java.io.Serializable;

/**
 *
 * @author JPablix
 */
public class Usuario implements Serializable{
    private String username;        //Nombre de usuario
    private Partida partida;     //Deben tener una partida
    
    public Usuario(String username) {
        this.username = username;
        this.partida = new Partida();
    }

    public String getUsername() {
        return username;
    }

    public Partida getPartida() {
        return partida;
    }
    
    
    
}
