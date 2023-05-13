package com.leviatanes.menus;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

public class escalaImag {//Clase para escalar la imagen de un JLabel
    private ImageIcon imagen;
    private Icon icono;
    
    public void escalarLabel(JLabel label, String ruta, int multiplier){
        label.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage()
                      .getScaledInstance(label.getWidth() , label.getHeight(), Image.SCALE_DEFAULT)
        ));
        System.out.println("label.getWidth(): "+label.getWidth()+", label.getHeight()"+label.getHeight());
    }
}
