package ludogassull.ludoMain;

import javax.swing.JFrame;
import ludogassull.vista.*;
import ludogassull.controlador.*;

public class LudoMain {
    
    public static void main(String[] args){
        
        VistaPrincipal vistaL = new VistaPrincipal();
        ControladorPrincipal controladorL = new ControladorPrincipal(vistaL);
        vistaL.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vistaL.setVisible(true);
        vistaL.jButtonAlertaRoja.setVisible(false);
        vistaL.jButtonDado.setVisible(false);
        vistaL.jLabelInfo.setVisible(false);
    }
    
}
