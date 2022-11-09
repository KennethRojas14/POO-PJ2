/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDeDatos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author JPablix
 */
public class BDUsuarios implements Serializable{
    private final String FILE_PATH = "Jugadores.dat";
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    
    public BDUsuarios(){
    }
    
    public void signIn(Usuario usuario){
        usuarios.add(usuario);
        guardar();
    }
    
    public void guardar(){
        FileManager.writeObject(this, FILE_PATH);
    }
    
    public void restaurar(){
        System.out.println("Leyendo usuarios de "+FILE_PATH);
        BDUsuarios bd = (BDUsuarios)FileManager.readObject(FILE_PATH);
        this.usuarios = bd.usuarios;
    }
    
    public Usuario buscarUsuario(String user){  //Metodo que busca usuario
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(user))
                return usuario;
        }
        return null;
    }
    
    
    
}
