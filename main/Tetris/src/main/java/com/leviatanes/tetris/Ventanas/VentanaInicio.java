/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.leviatanes.tetris.Ventanas;
import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author maria
 */
public class VentanaInicio extends javax.swing.JFrame {
    int mouseX, mouseY;
    private ImageIcon imagen;
    private Icon icono;
    Color blueL;
    
    public VentanaInicio() {
        initComponents();
        this.setLocationRelativeTo(this);//Para centrar el Frame
        this.printImage(titulo, "src/main/java/imag/TetrisT.png");
        this.printImage(fondo1, "src/main/java/imag/FondoT.png");
        this.printImage(fondo2, "src/main/java/imag/FondoT.png");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        backG = new javax.swing.JPanel();
        fondo2 = new javax.swing.JLabel();
        fondo1 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        movVentana = new javax.swing.JLabel();
        fondoX = new javax.swing.JPanel();
        labelX = new javax.swing.JLabel();
        playP = new javax.swing.JPanel();
        playBtn = new javax.swing.JLabel();
        scoreP = new javax.swing.JPanel();
        scoreBbtn = new javax.swing.JLabel();
        configP = new javax.swing.JPanel();
        configBtn = new javax.swing.JLabel();
        exitP = new javax.swing.JPanel();
        exitBtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        backG.setBackground(new java.awt.Color(142, 76, 238));
        backG.setPreferredSize(new java.awt.Dimension(720, 600));
        backG.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fondo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        backG.add(fondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 150, 540));

        fondo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        backG.add(fondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 150, 540));

        titulo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        backG.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 400, 110));

        movVentana.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                movVentanaMouseDragged(evt);
            }
        });
        movVentana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                movVentanaMousePressed(evt);
            }
        });
        backG.add(movVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(-60, 0, 750, 30));

        fondoX.setBackground(backG.getBackground());

        labelX.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        labelX.setForeground(new java.awt.Color(255, 255, 255));
        labelX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelX.setText("X");
        labelX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelXMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelXMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelXMouseExited(evt);
            }
        });

        javax.swing.GroupLayout fondoXLayout = new javax.swing.GroupLayout(fondoX);
        fondoX.setLayout(fondoXLayout);
        fondoXLayout.setHorizontalGroup(
            fondoXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoXLayout.createSequentialGroup()
                .addComponent(labelX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        fondoXLayout.setVerticalGroup(
            fondoXLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoXLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        backG.add(fondoX, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 30, 30));

        playP.setBackground(new java.awt.Color(42, 183, 190));

        playBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        playBtn.setForeground(new java.awt.Color(255, 255, 255));
        playBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playBtn.setText("PLAY");
        playBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playBtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout playPLayout = new javax.swing.GroupLayout(playP);
        playP.setLayout(playPLayout);
        playPLayout.setHorizontalGroup(
            playPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        playPLayout.setVerticalGroup(
            playPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(playBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        backG.add(playP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 200, 60));

        scoreP.setBackground(new java.awt.Color(42, 183, 190));

        scoreBbtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        scoreBbtn.setForeground(new java.awt.Color(255, 255, 255));
        scoreBbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreBbtn.setText("SCORE");
        scoreBbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                scoreBbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                scoreBbtnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout scorePLayout = new javax.swing.GroupLayout(scoreP);
        scoreP.setLayout(scorePLayout);
        scorePLayout.setHorizontalGroup(
            scorePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scoreBbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        scorePLayout.setVerticalGroup(
            scorePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scoreBbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        backG.add(scoreP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        configP.setBackground(new java.awt.Color(42, 183, 190));

        configBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        configBtn.setForeground(new java.awt.Color(255, 255, 255));
        configBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        configBtn.setText("CONFIG");

        javax.swing.GroupLayout configPLayout = new javax.swing.GroupLayout(configP);
        configP.setLayout(configPLayout);
        configPLayout.setHorizontalGroup(
            configPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(configBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        configPLayout.setVerticalGroup(
            configPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(configBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        backG.add(configP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 320, -1, -1));

        exitP.setBackground(new java.awt.Color(42, 183, 190));

        exitBtn.setFont(new java.awt.Font("Impact", 0, 36)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(255, 255, 255));
        exitBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitBtn.setText("EXIT");

        javax.swing.GroupLayout exitPLayout = new javax.swing.GroupLayout(exitP);
        exitP.setLayout(exitPLayout);
        exitPLayout.setHorizontalGroup(
            exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        exitPLayout.setVerticalGroup(
            exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        backG.add(exitP, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void movVentanaMousePressed(java.awt.event.MouseEvent evt) {                                        
        // Función para obtener la localización del mouse al presionar
        mouseX= evt.getX();
        mouseY= evt.getY();
    }                                       

    private void movVentanaMouseDragged(java.awt.event.MouseEvent evt) {                                        
        //Obteniendo la posición del mouse en la pantalla
        int x= evt.getXOnScreen();
        int y= evt.getYOnScreen();
        //Moviendo la ventana según su posición en la pantalla y ventana
        this.setLocation(x-mouseX, y-mouseY);
    }                                       

    private void labelXMouseExited(java.awt.event.MouseEvent evt) {                                   
        // Establece el color cuando no es seleccionado
        fondoX.setBackground(backG.getBackground());
    }                                  

    private void labelXMouseEntered(java.awt.event.MouseEvent evt) {                                    
        // Cambia de color al ser seleccionado
        fondoX.setBackground(Color.red);
    }                                   

    private void labelXMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // Sale del programa al presionar el botón
        System.exit(0);
    }                                   

    private void playBtnMouseEntered(java.awt.event.MouseEvent evt) {                                     
        // Cambia de color el botón
        playP.setBackground(Color.BLUE);
    }                                    

    private void playBtnMouseExited(java.awt.event.MouseEvent evt) {                                    
        //Regresa a su color normal
        playP.setBackground(scoreP.getBackground());
    }                                   

    private void scoreBbtnMouseEntered(java.awt.event.MouseEvent evt) {                                       
        // Cambia de color el botón
        playP.setBackground(Color.BLUE);
    }                                      

    private void scoreBbtnMouseExited(java.awt.event.MouseEvent evt) {                                      
        //Regresa a su color normal
        playP.setBackground(scoreP.getBackground());
    }                                     


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicio().setVisible(true);
            }
        });
    }
    
    private void printImage(JLabel label, String ruta){//Para ajusstar la imagen al JLabel
        this.imagen= new ImageIcon(ruta);
        this.icono= new ImageIcon(this.imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(this.icono);
        this.repaint();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel backG;
    private javax.swing.JLabel configBtn;
    private javax.swing.JPanel configP;
    private javax.swing.JLabel exitBtn;
    private javax.swing.JPanel exitP;
    private javax.swing.JLabel fondo1;
    private javax.swing.JLabel fondo2;
    private javax.swing.JPanel fondoX;
    private javax.swing.JLabel labelX;
    private javax.swing.JLabel movVentana;
    private javax.swing.JLabel playBtn;
    private javax.swing.JPanel playP;
    private javax.swing.JLabel scoreBbtn;
    private javax.swing.JPanel scoreP;
    private javax.swing.JLabel titulo;
    // End of variables declaration                   
}
