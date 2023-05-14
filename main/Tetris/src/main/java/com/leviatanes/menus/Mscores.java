package com.leviatanes.menus;


import java.util.List;//Para poder usar clases genericas
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.leviatanes.tetris.Main;
import com.leviatanes.tetris.tetrisGame.game.gameOver.*;

public class Mscores extends JPanel{
    private Main main;
    private JLabel titulo;
    private JLabel tituloCol;//titulo de las columnas
    private Score[] scores;
    private List<JLabel> filas=new ArrayList<>();//filas de los scores
    private JLabel mensaje;//mensaje de que no hay scores
    private JLabel btnbBack;//Botón para regresar al menú principal
    
    //Offset btnBack
   
    private final int btnBackWidht=10;
    private final int btnBackHeight=10;
    //Offset para el titulo
    private final int tituloXoffset=33;
    private final int tituloYoffset=5;
    private final int tituloWidht=24;
    private final int tituloHeight=8;

    //Offset para el titulo de las columnas
    private final int tituloColXoffset=15;
    private final int tituloColYoffset=18;
    private final int tituloColWidht=60;
    private final int tituloColHeight=6;

    //Offset para las filas
    private final int filaXoffset=15;
    private int filaYoffset=27;//Se va sumando 5
    private final int filaWidht=60;
    private final int filaHeight=5;

    public Mscores(int width, int height, int multiplier, Main main){
        this.main = main;
        setFilas();
        initPanel( width,  height,  multiplier);
    }

    public void setFilas(){
        String fil="fila";
        scores=new ScoreReader().getScores();
        filas.clear();//limpia el contenido de las filas de scores
        try{
        if(scores.length!=0){
            for(int i = 0; i < scores.length; i++){
                //Añadiendo filas según el número de scores registrados
                filas.add( new JLabel(" "+scores[i].getName()+"\t"+scores[i].getScore()));
            }
        }
        }catch(Exception e){
           // System.out.println("Error al leer los scores");
            mensaje = new JLabel("Empty Scores");
        }
        
    }

    public void initPanel(int width, int height, int multiplier){
        this.setLayout(null);
        this.setBounds(0, 0, width, height);
        setBackground(new java.awt.Color(142, 76, 236));

        int x,y,w,h;

        // Agregando los componentes
        titulo = new JLabel("SCORES");
        x = tituloXoffset * multiplier;
        y = tituloYoffset * multiplier;
        w = tituloWidht * multiplier;
        h = tituloHeight * multiplier;
        titulo.setBounds(x,y,w,h);
        this.adjustFontSize(titulo, "Impact", w, h);
        this.add(titulo);
        btnbBack=new JLabel();
        btnbBack.setText("Back");

        
        w=btnBackWidht*multiplier;
        h=btnBackHeight*multiplier;
        btnbBack.setBounds(0,0,w,h);
        this.adjustFontSize(btnbBack, "Impact", w, h);
        btnbBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main.MenuInicio();
            }
           
        });
       
        this.adjustFontSize(btnbBack, "Impact", w, h);
        this.add(btnbBack);
        tituloCol=new JLabel("Rank\tName\tScore ");
        x=tituloColXoffset*multiplier;
        y=tituloColYoffset*multiplier;
        w=tituloColWidht*multiplier;
        h=tituloColHeight*multiplier;
        tituloCol.setBounds(x,y,w,h);
        //SetBounds de las filas
        x=filaXoffset*multiplier;
        y=filaYoffset*multiplier;
        w=filaWidht*multiplier;
        h=filaHeight*multiplier;
        
        if(scores==null){//Imprime un mensaje si no hay scores
            mensaje=new JLabel("Empty Scores");
            mensaje.setBounds(x,y,w,h);
            this.adjustFontSize(mensaje, "Impact", w, h);
            this.add(mensaje);
        }
        else{
            for(int i = 0; i < scores.length; i++){
                //Añadiendo filas según el número de scores registrados
                filas.get(i).setBounds(x,y,w,h);
                this.adjustFontSize(filas.get(i), "Impact", w, h);
                this.add(filas.get(i));
                y+=5;
            }
        }

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    /**Ajusta la fuetne del JLabel */
    private void adjustFontSize(JLabel label, String font, int w, int h){
        for (int i = 1;; i++) {
            label.setFont(new java.awt.Font(font, 1, i));
            int fontW = label.getFontMetrics(label.getFont()).stringWidth(label.getText());
            int fontH = label.getFontMetrics(label.getFont()).getHeight();
            if (fontW > w || fontH > h) {
                label.setFont(new java.awt.Font(font, 1, i - 1));
                break;
            }
        }
    }

}
