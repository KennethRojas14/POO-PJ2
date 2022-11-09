/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package defentthefort;

import BaseDeDatos.BDUsuarios;
import BaseDeDatos.Usuario;
import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author JPablix
 */
public class RegistroJugador extends javax.swing.JFrame implements Serializable{

    BDUsuarios registroUsuarios;
    GUIPartida partida;
    
    
    public RegistroJugador() {
        initComponents();
        this.setResizable(false);
        registroUsuarios = new BDUsuarios();
        //registroUsuarios.restaurar();
        
    }

    @Override
    public void paint(Graphics g){
        Image fondo = new ImageIcon("src\\Imagenes\\BGRegistro.png").getImage();
        g.drawImage(fondo,0,0,getWidth(),getHeight(),pnlRegistro);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistro = new javax.swing.JPanel();
        txfUsuario = new javax.swing.JTextField();
        lblIngreseUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txfUsuario.setFont(new java.awt.Font("Heavitas", 0, 12)); // NOI18N
        txfUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txfUsuarioKeyTyped(evt);
            }
        });

        lblIngreseUsuario.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        lblIngreseUsuario.setForeground(new java.awt.Color(51, 255, 0));
        lblIngreseUsuario.setText("Inserte su nombre de usuario:");

        javax.swing.GroupLayout pnlRegistroLayout = new javax.swing.GroupLayout(pnlRegistro);
        pnlRegistro.setLayout(pnlRegistroLayout);
        pnlRegistroLayout.setHorizontalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIngreseUsuario)
                    .addComponent(txfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        pnlRegistroLayout.setVerticalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIngreseUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Función para cargar la partida de cada usuario
    private void txfUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txfUsuarioKeyTyped
        if (((int)evt.getKeyChar())==10){
            Usuario user = registroUsuarios.buscarUsuario(txfUsuario.getText());
            if (user == null){
                int option = JOptionPane.showConfirmDialog(rootPane, "¿Desea crea un usuario nuevo con el nombre "+txfUsuario.getText()+"?", "Usuario no encontrado", JOptionPane.YES_NO_OPTION);
                if (option == 0){
                    user = new Usuario(txfUsuario.getText());
                    registroUsuarios.signIn(user);
                    System.out.println("Se ha registrado: "+txfUsuario.getText());
                }
            }
            else {
                System.out.println("Cargando Partida");
                partida = new GUIPartida(user.getUsername(), user.getPartida(), registroUsuarios); //Se manda el nombre del usuario y la partida
                partida.setVisible(true);
            }
        }
    }//GEN-LAST:event_txfUsuarioKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIngreseUsuario;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JTextField txfUsuario;
    // End of variables declaration//GEN-END:variables
}
