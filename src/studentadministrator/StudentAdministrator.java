
package studentadministrator;

import view.TelaPrincipal;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import util.Resource;

public class StudentAdministrator {

    public static void main(String[] args) { 
        
        // Inicia a tela principal em Thread separado.
        ArrayList<Image> icons = new ArrayList<>();
        icons.add(Resource.loadIcon("img/24.png").getImage());
        icons.add(Resource.loadIcon("img/64.png").getImage());

        
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal("Administrador");
            tela.setIconImages(icons);
            tela.pack();
            tela.setVisible(true);
        });
    }
    
}
