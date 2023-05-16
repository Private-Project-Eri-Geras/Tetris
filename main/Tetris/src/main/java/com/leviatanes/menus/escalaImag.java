package com.leviatanes.menus;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class escalaImag {// Clase para escalar la imagen de un JLabel

    public void escalarLabel(JLabel label, String ruta, int multiplier) {
        label.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage()
                .getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)));
        System.out.println("label.getWidth(): " + label.getWidth() + ", label.getHeight()" + label.getHeight());
    }
}
