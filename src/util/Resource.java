
package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Resource {
    
    public static ImageIcon loadIcon(String path) {
        try {
            ImageIcon icon = new ImageIcon(
                    ImageIO.read(ClassLoader.getSystemResourceAsStream(path))
            );
            
            return icon;
            
        } catch (IOException ex) {
            Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ImageIcon loadIcon(String path, int w, int h) {
        try {
            Image image = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH));
            return icon;
            
        } catch (IOException ex) {
            Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
