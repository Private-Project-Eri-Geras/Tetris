/*
Clase para escalar las imagnes de los JLabels
*/
package Ventanas;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

public class Escalar {
    private ImageIcon imagen;
    private Icon icono;
    
    public void escalarLabel(JLabel label, String ruta){
        label.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage()
                      .getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT)
        ));
        label.repaint();
        /*Image img= new ImageIcon(ruta).getImage();
        ImageIcon img2=new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        label.setIcon(img2);*/
    }
}
