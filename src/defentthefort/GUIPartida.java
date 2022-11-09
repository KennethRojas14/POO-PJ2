/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package defentthefort;

import Arma.Arma;
import Arma.ArmaAerea;
import Arma.ArmaBloque;
import Arma.ArmaContacto;
import Arma.ArmaDistancia;
import Arma.ArmaImpacto;
import Arma.ArmaMultiple;
import BaseDeDatos.BDUsuarios;
import Zombie.Zombie;
import Zombie.ZombieChoque;
import Zombie.ZombieContacto;
import Zombie.ZombieDistancia;
import Zombie.ZombieVolador;
//import Zombie.ZombiePrueba;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class GUIPartida extends javax.swing.JFrame implements Serializable{
    int SIZE = 30;
    Partida partida;
    BDUsuarios registroUsuarios;
    ImageIcon tempImageArma;
    
    public GUIPartida(String jugador, Partida partida, BDUsuarios registroUsuarios) {
        initComponents();
        this.partida = partida;
        this.registroUsuarios = registroUsuarios;
        
        this.setTitle("Defent The Fort: Zombie Attack \t"+jugador);
        lblNivel.setText("NIVEL: "+partida.getNivel());
        
        if (partida.isHasPartida()){
            //---------CARGAR LA PARTIDA DEL USUARIO
            colocarEspacios();
            System.out.println("PARTIDA CARGADA");
        }else{
           //----------Crear Partida Nueva
           partida.setHasPartida(true);
           System.out.println("PARTIDA CREADA");
            generarMatriz();
            partida.espacios[312].boton.setIcon(new ImageIcon("src\\Imagenes\\Arbol.png")); //Colocación del árbol de Salvación
        }
        
        
        

//-------------------PRUEBA ZOMBIE---------------------BORRAR
        //ZombiePrueba zombie;
        //zombie = new ZombiePrueba(100, 10, 2, 1, "ZOMBIE", partida.espacios[420], partida);
        partida.espacios[420].boton.setIcon(new ImageIcon("src\\Imagenes\\ZombieContacto.png"));
        System.out.println("Zombie: ("+partida.espacios[420].getPosition('X')+","+partida.espacios[420].getPosition('Y')+")");
    }
    
private void colocarEspacios(){
    for(int i = 0; i < 625; i++){
        JButton btn = partida.espacios[i].getBoton();
        pnlAreaJuego.add(btn);
        btn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {jButtonMatrizActionPerformed(evt,btn);}
       });
    }
}
    
private void generarMatriz(){   //Generar botones
   int posX = 0;
   int posY = 0;
   //------------------------------------------
   for(int i = 0; i < 625; i++){
       JButton btn = new JButton("");
       if((i%2) == 0){
           btn.setBackground(new Color(0,153,0));
       }else{
          btn.setBackground(new Color(0,102,0));
       }          
       btn.setSize(SIZE, SIZE);
    //-------------------------------
       if(i%25 == 0 && i>0){
           posX = 0;
           posY += SIZE;
       }
    //------------------------------
       btn.setLocation((SIZE*posX++), posY);
       pnlAreaJuego.add(btn);
       partida.espacios[i].setBoton(btn);

       btn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {jButtonMatrizActionPerformed(evt,btn);}
       });
   }
}

private void jButtonMatrizActionPerformed(ActionEvent evt, JButton btn) {
    //-----------------------RESTRICCIONES AL PONER ARMAS
    boolean mostrarMenu = false;
    if (btn.getIcon() != null){                         //Restricción al poner arma
        mostrarMenu = true;
        //---------------Mostrar Elementos Atacados
//        System.out.print("Objetivos Atacados:");
//        partida.espacios[i].
//        
//        
//        System.out.println("POR IMAGEN SUPERPUESTA");   //Para quitar imagen debe esta seleccionado algo
//        JOptionPane.showMessageDialog(null, "Espacio ya ocupado por un arma");
//        System.out.println(btn.getIcon()+"");           //Y se deben recuperar lo puntos
        //return;
    }      

    btn.setText("NG");                              //Se registras un boton diferente de todos

    //---------------------BUSCAR POSICION DE ARMA RESGISTRADA-------------------------
    for (int i = 0; i < 625; i++){                  //Se busca el boton seleccionado en el array
        if (partida.espacios[i].boton.getText().equals("NG")){
            Espacio espacio = partida.espacios[i];  //Definimos el espacio
            espacio.boton.setText("");              //Se quita la bandera
            
            //-----------MOSTRAR INFO----------------------
            if (mostrarMenu){
                if (espacio.hasArma){
                    if (btn.getIcon() != null){
                        String str = espacio.getArma().getNombre()+"\n";

                        str += "Objetivos Atacados: ";
                        
                        for (String ataque : espacio.getArma().getAtaquesEjercidos()) {
                            str += (" "+ataque);
                            System.out.println(ataque+"");
                        }
                        str += "\n";
                        
                        str += "Ataques Recibidos:";
                        
                        for (String ataque : espacio.getArma().getAtaquesRecibidos()) {
                            str += (" "+ataque);
                        }
                        str += "\n";
                        
                        
                        txaInfo.setText(str);
                    }
                    
                if (espacio.hasZombie){
                   txaInfo.setText("Zombie");
                }
                break;
                }
                break;
            }
        if (tempImageArma == null){                         //Restricción al poner arma por que no se presionó un botón
        System.out.println("POR FALTA DE SELECCIÓN");
        JOptionPane.showMessageDialog(null, "Primero debe seleccionar un arma a colocar");
        break;
        }
            /*
            TO DO:
             -Cambiar los numeros por variables constantes
             -Aplicar las variables ataque de manera correcta
             -Aplicar los threats correctos a las armas
        
             -Aplicar el mostrar ataques a los Zombies
             -
            */
            //-----------COMPARACIÓN ARMA COLOCADA-----------------------------------
            if (tempImageArma.getDescription().contains("ArmaAerea")){       //crear ArmaAerea Normal
                if (partida.getEspaciosArmas() < 3)
                    JOptionPane.showMessageDialog(null, "No se tiene suficientes espacios. Disponibles: "+partida.getEspaciosArmas());
                else{
                    partida.setEspaciosArmas(partida.getEspaciosArmas()-3);          //Restamos el coste por Torreta
                    ArmaAerea Aere = new ArmaAerea(100, 20, 3, 5, "Dron", tempImageArma, espacio, partida.getEspacios(), partida);
                }
            }if (tempImageArma.getDescription().contains("ArmaMuro")){      //crear ArmaBloque Normal
                if (partida.getEspaciosArmas() < 3)
                    JOptionPane.showMessageDialog(null, "No se tiene suficientes espacios. Disponibles: "+partida.getEspaciosArmas());
                else{
                    partida.setEspaciosArmas(partida.getEspaciosArmas()-3);          //Restamos el coste por Torreta
                    ArmaBloque Bloq = new ArmaBloque(100, 20, 3, 5, "Muro", tempImageArma, espacio, partida.getEspacios(), partida);
                }
            }if (tempImageArma.getDescription().contains("ArmaContacto")){  //crear Armacontacto Normal
                if (partida.getEspaciosArmas() < 3)
                    JOptionPane.showMessageDialog(null, "No se tiene suficientes espacios. Disponibles: "+partida.getEspaciosArmas());
                else{
                    partida.setEspaciosArmas(partida.getEspaciosArmas()-3);          //Restamos el coste por Torreta
                    ArmaContacto Cont = new ArmaContacto(100, 20, 3, 5, "Bate", tempImageArma, espacio, partida.getEspacios(), partida);
                }
            }if (tempImageArma.getDescription().contains("ArmaAlcance")){   //crear ArmaAlcance Normal
                if (partida.getEspaciosArmas() < 3)
                    JOptionPane.showMessageDialog(null, "No se tiene suficientes espacios. Disponibles: "+partida.getEspaciosArmas());
                else{
                    partida.setEspaciosArmas(partida.getEspaciosArmas()-3);          //Restamos el coste por Torreta
                    ArmaDistancia Alca = new ArmaDistancia(100, 20, 3, 5, "Torreta", tempImageArma, espacio, partida.getEspacios(), partida);
                }
            }if (tempImageArma.getDescription().contains("ArmaImpacto")){  //crear Armacontacto Normal
                if (partida.getEspaciosArmas() < 3)
                    JOptionPane.showMessageDialog(null, "No se tiene suficientes espacios. Disponibles: "+partida.getEspaciosArmas());
                else{
                    partida.setEspaciosArmas(partida.getEspaciosArmas()-3);          //Restamos el coste por Torreta
                    ArmaImpacto Impa = new ArmaImpacto(100, 20, 3, 5, "Mina", tempImageArma, espacio, partida.getEspacios(),partida);
                }
            }if (tempImageArma.getDescription().contains("ArmaMultiple")){  //crear Armacontacto Normal
                if (partida.getEspaciosArmas() < 3)
                    JOptionPane.showMessageDialog(null, "No se tiene suficientes espacios. Disponibles: "+partida.getEspaciosArmas());
                else{
                    partida.setEspaciosArmas(partida.getEspaciosArmas()-3);          //Restamos el coste por Torreta
                    ArmaMultiple Mult = new ArmaMultiple(100, 20, 3, 5, "Metralleta", tempImageArma, espacio, partida.getEspacios(), partida);
                }
            }
            break;
        }
    }
}

    private void generarZombies(){
        int contCampos = 0;
        while(contCampos < partida.getEspaciosZombies()){
            
            
            int aleatorio = (new Random()).nextInt(5);
            switch (aleatorio) {
                case 1:
                    ZombieContacto zombieContacto = new ZombieContacto(100, 20, 2, 1,"Infectado", "ZombieContacto.png",  partida.getEspacios(), partida);
                    contCampos += zombieContacto.getCampos();
                    zombieContacto.start();
                    break;
                case 2:
                    ZombieChoque zombieChoque = new ZombieChoque(60, 150, 5, 1,"Explosivo", "ZombieChoque.png",  partida.getEspacios(), partida);
                    contCampos += zombieChoque.getCampos();
                    zombieChoque.start();
                    break;
                case 3:
                    ZombieDistancia zombieDistancia = new ZombieDistancia(100, 20, 4, 3,"Soldado", "ZombieDistancia.png",  partida.getEspacios(), partida);
                    contCampos += zombieDistancia.getCampos();
                    zombieDistancia.start();
                    break;
                default:
                    ZombieVolador zombieVolador = new ZombieVolador(80, 15, 3, 1,"Niño", "ZombieVolador.png",  partida.getEspacios(), partida);
                    contCampos += zombieVolador.getCampos();
                    zombieVolador.start();
                    break;
            }
        }
        
        
    }
    
    public void siguienteNivel(){
        partida.setNivel(partida.getNivel()+1);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked");
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAreaJuego = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        lblArmas = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        pnlArmas = new javax.swing.JPanel();
        btnArmaContacto = new javax.swing.JButton();
        btnArmaMuro = new javax.swing.JButton();
        btnArmaImpacto = new javax.swing.JButton();
        btnArmaAerea = new javax.swing.JButton();
        btnArmaDistancia = new javax.swing.JButton();
        btnArmaMultiple = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaInfo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));
        setPreferredSize(new java.awt.Dimension(1110, 750));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        pnlAreaJuego.setBackground(new java.awt.Color(153, 153, 153));

        jButton1.setBackground(new java.awt.Color(102, 255, 51));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblArmas.setBackground(new java.awt.Color(204, 204, 204));
        lblArmas.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblArmas.setForeground(new java.awt.Color(255, 255, 255));
        lblArmas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArmas.setText("Armas");
        lblArmas.setOpaque(true);

        lblNivel.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblNivel.setForeground(new java.awt.Color(255, 255, 255));
        lblNivel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNivel.setText("NIVEL: ");

        btnStart.setBackground(new java.awt.Color(0, 153, 51));
        btnStart.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 255, 255));
        btnStart.setText("START");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        pnlArmas.setBackground(new java.awt.Color(102, 102, 102));
        pnlArmas.setPreferredSize(new java.awt.Dimension(160, 400));

        btnArmaContacto.setBackground(new java.awt.Color(204, 204, 204));
        btnArmaContacto.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnArmaContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ArmaContacto.png"))); // NOI18N
        btnArmaContacto.setText("Contacto");
        btnArmaContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArmaContactoActionPerformed(evt);
            }
        });

        btnArmaMuro.setBackground(new java.awt.Color(204, 204, 204));
        btnArmaMuro.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnArmaMuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ArmaMuro.png"))); // NOI18N
        btnArmaMuro.setText("Muro");
        btnArmaMuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArmaMuroActionPerformed(evt);
            }
        });

        btnArmaImpacto.setBackground(new java.awt.Color(204, 204, 204));
        btnArmaImpacto.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnArmaImpacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ArmaImpacto.png"))); // NOI18N
        btnArmaImpacto.setText("Impacto");
        btnArmaImpacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArmaImpactoActionPerformed(evt);
            }
        });

        btnArmaAerea.setBackground(new java.awt.Color(204, 204, 204));
        btnArmaAerea.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnArmaAerea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ArmaAerea.png"))); // NOI18N
        btnArmaAerea.setText("Aérea");
        btnArmaAerea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArmaAereaActionPerformed(evt);
            }
        });

        btnArmaDistancia.setBackground(new java.awt.Color(204, 204, 204));
        btnArmaDistancia.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnArmaDistancia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ArmaAlcance.png"))); // NOI18N
        btnArmaDistancia.setText("Distancia");
        btnArmaDistancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArmaDistanciaActionPerformed(evt);
            }
        });

        btnArmaMultiple.setBackground(new java.awt.Color(204, 204, 204));
        btnArmaMultiple.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        btnArmaMultiple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ArmaMultiple.png"))); // NOI18N
        btnArmaMultiple.setText("Múltiple");
        btnArmaMultiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArmaMultipleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlArmasLayout = new javax.swing.GroupLayout(pnlArmas);
        pnlArmas.setLayout(pnlArmasLayout);
        pnlArmasLayout.setHorizontalGroup(
            pnlArmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArmasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlArmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnArmaImpacto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArmaDistancia)
                    .addComponent(btnArmaContacto)
                    .addComponent(btnArmaMuro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArmaAerea, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArmaMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlArmasLayout.setVerticalGroup(
            pnlArmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArmasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnArmaContacto)
                .addGap(18, 18, 18)
                .addComponent(btnArmaMuro)
                .addGap(18, 18, 18)
                .addComponent(btnArmaImpacto)
                .addGap(18, 18, 18)
                .addComponent(btnArmaDistancia)
                .addGap(18, 18, 18)
                .addComponent(btnArmaAerea)
                .addGap(18, 18, 18)
                .addComponent(btnArmaMultiple)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnSave.setBackground(new java.awt.Color(51, 102, 255));
        btnSave.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txaInfo.setColumns(20);
        txaInfo.setRows(5);
        jScrollPane1.setViewportView(txaInfo);

        javax.swing.GroupLayout pnlAreaJuegoLayout = new javax.swing.GroupLayout(pnlAreaJuego);
        pnlAreaJuego.setLayout(pnlAreaJuegoLayout);
        pnlAreaJuegoLayout.setHorizontalGroup(
            pnlAreaJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAreaJuegoLayout.createSequentialGroup()
                .addContainerGap(789, Short.MAX_VALUE)
                .addGroup(pnlAreaJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlAreaJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAreaJuegoLayout.createSequentialGroup()
                            .addComponent(lblArmas, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(19, 19, 19))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAreaJuegoLayout.createSequentialGroup()
                            .addComponent(pnlArmas, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlAreaJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(52, 52, 52)))))
        );
        pnlAreaJuegoLayout.setVerticalGroup(
            pnlAreaJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAreaJuegoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlAreaJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArmas))
                .addGroup(pnlAreaJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAreaJuegoLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(pnlArmas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAreaJuegoLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(305, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAreaJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAreaJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if (((int)evt.getKeyChar())==27){
            this.setVisible(false);
        }
    }//GEN-LAST:event_formKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    partida.subirNivel();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        partida.setActivate(!partida.isActivate());
        generarZombies();
        if (partida.isActivate()){
            for (int i = 0; i < 624; i++){
                if (partida.espacios[i].hasArma){
                   partida.espacios[i].getArma().start();
                }
            }
            
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnArmaContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArmaContactoActionPerformed
        tempImageArma = (ImageIcon) btnArmaContacto.getIcon();
    }//GEN-LAST:event_btnArmaContactoActionPerformed

    private void btnArmaMuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArmaMuroActionPerformed
        tempImageArma = (ImageIcon) btnArmaMuro.getIcon();
    }//GEN-LAST:event_btnArmaMuroActionPerformed

    private void btnArmaImpactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArmaImpactoActionPerformed
        tempImageArma = (ImageIcon) btnArmaImpacto.getIcon();
    }//GEN-LAST:event_btnArmaImpactoActionPerformed

    private void btnArmaDistanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArmaDistanciaActionPerformed
        tempImageArma = (ImageIcon) btnArmaDistancia.getIcon();
    }//GEN-LAST:event_btnArmaDistanciaActionPerformed

    private void btnArmaAereaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArmaAereaActionPerformed
        tempImageArma = (ImageIcon) btnArmaAerea.getIcon();
    }//GEN-LAST:event_btnArmaAereaActionPerformed

    private void btnArmaMultipleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArmaMultipleActionPerformed
        tempImageArma = (ImageIcon) btnArmaMultiple.getIcon();
    }//GEN-LAST:event_btnArmaMultipleActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        registroUsuarios.guardar();
    }//GEN-LAST:event_btnSaveActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArmaAerea;
    private javax.swing.JButton btnArmaContacto;
    private javax.swing.JButton btnArmaDistancia;
    private javax.swing.JButton btnArmaImpacto;
    private javax.swing.JButton btnArmaMultiple;
    private javax.swing.JButton btnArmaMuro;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArmas;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JPanel pnlAreaJuego;
    private javax.swing.JPanel pnlArmas;
    private javax.swing.JTextArea txaInfo;
    // End of variables declaration//GEN-END:variables
}
