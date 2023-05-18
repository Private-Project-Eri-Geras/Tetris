package com.leviatanes.menus;

import java.util.List;//Para poder usar clases genericas
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.leviatanes.tetris.Main;
import com.leviatanes.tetris.tetrisGame.game.gameOver.*;

public class Mscores extends JPanel {
    private Main main;
    private JLabel titulo;
    private JLabel tituloCol;// titulo de las columnas
    private String cadena;
    private Score[] scores;
    private List<JLabel> filas = new ArrayList<>();// filas de los scores
    private JLabel mensaje;// mensaje de que no hay scores
    private JLabel btnbBack;// Botón para regresar al menú principal
    private JLabel btnReset;// Botón para resetear los scores
    private JPanel fondoPanel;
    private escalaImag escal = new escalaImag();

    // Offset para el titulo
    private final int tituloXoffset = 33;
    private final int tituloYoffset = 3;
    private final int tituloWidht = 24;
    private final int tituloHeight = 8;

    // Offset para el fondoPanel
    private final int fondoXoffset = 15;
    private final int fondoYoffset = 13;
    private final int fondoWidht = 60;
    private final int fondoHeight = 52;

    // Offset para el titulo de las columnas
    private final int tituloColXoffset = 0;
    private final int tituloColYoffset = 2;
    private final int tituloColWidht = 60;
    private final int tituloColHeight = 5;

    // Offset para las filas
    private final int filaXoffset = 0;
    private final int filaYoffset = 8;// Se va sumando 5 para cada fila
    private final int filaWidht = 60;
    private final int filaHeight = 4;

    // Offset btnBack
    private final int btnBackXoffset = 28;
    private final int btnBackYoffset = 66;
    private final int btnBackWidht = 10;
    private final int btnBackHeight = 10;
    // Offset btnReset
    private final int btnResetXoffset = 50;
    // private final int btnResetYoffset = 65;
    // private final int btnResetWidht = 9;
    // private final int btnResetHeight = 8;

    public Mscores(int width, int height, int multiplier, Main main) {
        this.main = main;
        setFilas();
        initPanel(width, height, multiplier);
    }

    public void setFilas() {// llena las columnas de rank, name y score
        scores = new ScoreReader().getScores();
        try {
            if (scores.length != 0) {
                for (int i = 0; i < scores.length; i++) {
                    // Añadiendo filas según el número de scores registrados
                    cadena = String.format("%2s %5s %5s", Integer.toString(i + 1), scores[i].getName(),
                            Integer.toString(scores[i].getScore()));
                    // obtener el mayor tamaño de fuente que quepa en la columna
                    JLabel label = new JLabel(cadena);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    filas.add(label);
                }
            }
        } catch (Exception e) {
            mensaje = new JLabel("Empty Scores");
        }

    }

    public void initPanel(int width, int height, int multiplier) {
        this.setLayout(null);
        this.setBounds(0, 0, width, height);
        setBackground(new Color(0, 0, 164).darker());
        int x, y, w, h;

        // Agregando los componentes
        // Titulo //
        titulo = new JLabel("SCORES");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setForeground(new Color(200, 63, 49));
        x = tituloXoffset * multiplier;
        y = tituloYoffset * multiplier;
        w = tituloWidht * multiplier;
        h = tituloHeight * multiplier;
        titulo.setBounds(x, y, w, h);
        this.adjustFontSize(titulo, "Impact", w, h);
        this.add(titulo);

        // Agregandole un fondo
        fondoPanel = new JPanel();
        fondoPanel.setLayout(null);
        x = fondoXoffset * multiplier;
        y = fondoYoffset * multiplier;
        w = fondoWidht * multiplier;
        h = fondoHeight * multiplier;
        fondoPanel.setBounds(x, y, w, h);
        fondoPanel.setBackground(new Color(0, 143, 150));

        // Filas de los scores//
        x = tituloColXoffset * multiplier;
        y = tituloColYoffset * multiplier;
        w = tituloColWidht * multiplier;
        h = tituloColHeight * multiplier;

        if (scores == null) {// Imprime un mensaje si no hay scores
            emptyMessage(x, y, w, h);
        } else {// Si hay Scores registrados
            cadena = String.format("%s %7s %11s", "Rank", " Name", "Score");
            tituloCol = new JLabel(cadena);
            tituloCol.setForeground(Color.white);
            tituloCol.setHorizontalAlignment(SwingConstants.CENTER);
            tituloCol.setBounds(x, y, w, h);
            this.adjustFontSize(tituloCol, "Impact", w, h);
            fondoPanel.add(tituloCol);

            x = filaXoffset * multiplier;
            y = filaYoffset * multiplier;
            w = filaWidht * multiplier;
            h = filaHeight * multiplier;
            for (int i = 0; i < scores.length; i++) {
                // Añadiendo filas según el número de scores registrados
                filas.get(i).setBounds(x, y, w, h);
                filas.get(i).setForeground(Color.white);
                filas.get(i).setOpaque(true);
                if (i % 2 == 0) {
                    filas.get(i).setBackground(new Color(20, 20, 20));
                } else {
                    filas.get(i).setBackground(new Color(40, 40, 40));
                }
                this.adjustFontSize(filas.get(i), "Lucida Console", w, h);
                fondoPanel.add(filas.get(i));
                y += 5 * multiplier;
            }
        } // Fin else scores existentes

        this.add(fondoPanel);// agrega el fondo
        // Boton Back to Menu//
        btnbBack = new JLabel();
        x = btnBackXoffset * multiplier;
        y = btnBackYoffset * multiplier;
        w = btnBackWidht * multiplier;
        h = btnBackHeight * multiplier;
        btnbBack.setBounds(x, y, w, h);
        escal.escalarLabel(btnbBack, "/com/leviatanes/images/home.png", multiplier);
        btnbBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main.MenuInicio();
            }

        });
        this.add(btnbBack);
        // Boton Reset//
        btnReset = new JLabel();
        x = btnResetXoffset * multiplier;
        btnReset.setBounds(x, y, w, h);
        escal.escalarLabel(btnReset, "/com/leviatanes/images/return.png", multiplier);// escala la imagen del JLabel
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    // Se especifica la ruta del archivo de puntajes.
                    String fileName = "/com/leviatanes/tetris/tetrisGame/game/gameOver/highScores.txt";
                    // Se crea un objeto File para el archivo de puntajes. Usando la ruta completa
                    // del archivo
                    File file = new File(ScoreReader.class.getResource(fileName).getFile());
                    FileWriter writer = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(writer);
                    bw.write("");// Se borra el contenido del archivo
                    bw.close();// Se cierra el buffer de escritura
                    writer.close();// Se cerrra el archivo de escritura
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fondoPanel.removeAll();// Remueven todos los scores del panel
                // Escribiendo el mensaje de scores vacios
                emptyMessage(tituloColXoffset * multiplier, tituloColYoffset * multiplier,
                        tituloColWidht * multiplier, tituloColHeight * multiplier);
                revalidate();// se revalida los componentes del panel
                repaint();// lo repinta
            }

        });
        this.add(btnReset);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    public void emptyMessage(int x, int y, int w, int h) {
        mensaje = new JLabel("\t Empty Scores");
        mensaje.setBounds(x, y, w, h);
        mensaje.setForeground(Color.white);
        mensaje.setOpaque(true);
        mensaje.setBackground(new Color(20, 20, 20));
        this.adjustFontSize(mensaje, "Impact", w, h);
        fondoPanel.add(mensaje);
    }

    /** Ajusta la fuetne del JLabel */
    private void adjustFontSize(JLabel label, String font, int w, int h) {
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
