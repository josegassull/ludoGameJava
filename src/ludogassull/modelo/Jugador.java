package ludogassull.modelo;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import ludogassull.controlador.ControladorPrincipal;
import ludogassull.vista.VistaPrincipal;

public class Jugador {
    
    private Color color;
    private int resultadoDado;
    private JLabel dadoColor;
    private int id;
    private int coronas=0;

    public Jugador(Color color, JLabel dadoColor, int id) {
        this.color = color;
        this.dadoColor = dadoColor;
        this.id = id;
    }
    
    //MÉTODOS
    
    public void tirarDado(){
        this.setResultadoDado((int)(Math.random()*(6-1+1)+1));
    }
    
    public void moverFicha(JButton casilla, Jugador jugadorEnTurno, JButton[] caminoRojo, JButton[] caminoAzul
    ,JButton[] caminoVerde, JButton[] caminoOcre, Cola cola, ControladorPrincipal controlador){

        JButton camino[] = new JButton[62];

        switch (jugadorEnTurno.getId()) {

            case 1:
                camino = caminoRojo;
                break;

            case 2:
                camino = caminoAzul;
                break;

            case 3:
                camino = caminoVerde;
                break;

            case 4:
                camino = caminoOcre;
                break;
        }

        //Lo que deja atrás en la casilla de partida
        if ("♠".equals(casilla.getText())) {
            casilla.setText("");
        }
        if ("2".equals(casilla.getText())) {
            casilla.setText("♠");
        }
        if ("3".equals(casilla.getText())) {
            casilla.setText("2");
        }
        if ("4".equals(casilla.getText())) {
            casilla.setText("3");
        }

        for (int i = 0; i < camino.length; i++) {
            if (camino[i] == casilla) {/*CUANDO ENCUENTRA LA CASILLA QUE SE PRESIONÓ 
                        CALCULA A CUAL DEBE CAER*/

                //SEGUN LO QUE HAY DONDE CAE:

                //SI DONDE CAE NO HAY NADA
                if ("".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())) {
                    camino[i + jugadorEnTurno.getResultadoDado()].setText("♠");
                    camino[i + jugadorEnTurno.getResultadoDado()].setForeground(jugadorEnTurno.getColor());
                    if (jugadorEnTurno.getResultadoDado() != 6) {
                        controlador.avanzarCola(cola);
                        controlador.nuevoTurno(cola);
                        return;
                    } else {
                        controlador.nuevoTurno(cola);
                        return;
                    }
                }

                //SI DONDE CAE HAY 1 FICHA DE SU MISMO COLOR
                if ("♠".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())
                        && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(jugadorEnTurno.getColor())) {
                    camino[i + jugadorEnTurno.getResultadoDado()].setText("2");
                    camino[i + jugadorEnTurno.getResultadoDado()].setForeground(jugadorEnTurno.getColor());
                    
                    //evaluamos si coronó
                    if(camino[i + jugadorEnTurno.getResultadoDado()]==caminoRojo[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoAzul[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoVerde[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoOcre[56]){
                        if(!controlador.getJugadorEnTurno().coronar(jugadorEnTurno, controlador.getPaleta(), cola,
                            controlador.getArchivoRegistro(), controlador.getVistaLudo())){
                            controlador.nuevoTurno(cola);
                            return;
                        }
                        else{
                            controlador.avanzarCola(cola);
                            controlador.nuevoTurno(cola);
                            return;
                        }
                    }
                    
                    if (jugadorEnTurno.getResultadoDado() != 6) {
                        controlador.avanzarCola(cola);
                        controlador.nuevoTurno(cola);
                        return;
                    } else {
                        controlador.nuevoTurno(cola);
                        return;
                    }
                }

                //SI DONDE CAE HAY 2 FICHAS DE SU MISMO COLOR
                if ("2".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())
                        && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(jugadorEnTurno.getColor())) {
                    camino[i + jugadorEnTurno.getResultadoDado()].setText("3");
                    camino[i + jugadorEnTurno.getResultadoDado()].setForeground(jugadorEnTurno.getColor());
                    
                    //evaluamos si coronó
                    if(camino[i + jugadorEnTurno.getResultadoDado()]==caminoRojo[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoAzul[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoVerde[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoOcre[56]){
                        if(!controlador.getJugadorEnTurno().coronar(jugadorEnTurno, controlador.getPaleta(), cola,
                            controlador.getArchivoRegistro(), controlador.getVistaLudo())){
                            controlador.nuevoTurno(cola);
                            return;
                        }
                        else{
                            controlador.avanzarCola(cola);
                            controlador.nuevoTurno(cola);
                            return;
                        }
                    }
                    
                    if (jugadorEnTurno.getResultadoDado() != 6) {
                        controlador.avanzarCola(cola);
                        controlador.nuevoTurno(cola);
                        return;
                    } else {
                        controlador.nuevoTurno(cola);
                        return;
                    }
                }

                //SI DONDE CAE HAY 3 FICHAS DE SU MISMO COLOR
                if ("3".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())
                        && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(jugadorEnTurno.getColor())) {
                    camino[i + jugadorEnTurno.getResultadoDado()].setText("4");
                    camino[i + jugadorEnTurno.getResultadoDado()].setForeground(jugadorEnTurno.getColor());
                    
                    //evaluamos si coronó
                    if(camino[i + jugadorEnTurno.getResultadoDado()]==caminoRojo[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoAzul[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoVerde[56]
                     ||camino[i + jugadorEnTurno.getResultadoDado()]==caminoOcre[56]){
                        if(!controlador.getJugadorEnTurno().coronar(jugadorEnTurno, controlador.getPaleta(), cola,
                            controlador.getArchivoRegistro(), controlador.getVistaLudo())){
                            controlador.nuevoTurno(cola);
                            return;
                        }
                        else{
                            controlador.avanzarCola(cola);
                            controlador.nuevoTurno(cola);
                            return;
                        }
                    }
                    
                    if (jugadorEnTurno.getResultadoDado() != 6) {
                        controlador.avanzarCola(cola);
                        controlador.nuevoTurno(cola);
                        return;
                    } else {
                        controlador.nuevoTurno(cola);
                        return;
                    }
                }

                //SI CAE EN LA CORONA CON 0
                if ("0".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())
                        && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(jugadorEnTurno.getColor())) {
                    camino[i + jugadorEnTurno.getResultadoDado()].setText("♠");
                    camino[i + jugadorEnTurno.getResultadoDado()].setForeground(jugadorEnTurno.getColor());
                    
                    if (!controlador.getJugadorEnTurno().coronar(jugadorEnTurno, controlador.getPaleta(), cola,
                            controlador.getArchivoRegistro(), controlador.getVistaLudo())) {
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    controlador.avanzarCola(cola);
                    controlador.nuevoTurno(cola);
                    return;
                }

                //SI DONDE CAE HAY FICHAS DE OTRO COLOR
                if (!camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(jugadorEnTurno.getColor())) {
                    if("♠".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())
                            ||"1".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())
                            ||"2".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())
                            ||"3".equals(camino[i + jugadorEnTurno.getResultadoDado()].getText())){
                        
                        controlador.getJugadorEnTurno().comerFichas(camino, i, jugadorEnTurno, controlador.getPaleta(), controlador.getVistaLudo()
                        , cola, controlador);
                    }
                }
            }
        }
    }
    
    public void comerFichas(JButton[] camino, int i, Jugador jugadorEnTurno, PaletaColores paleta, VistaPrincipal vistaLudo
    , Cola cola, ControladorPrincipal controlador){
        //rojo
        if (jugadorEnTurno.getId() != 1 && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(paleta.getColorRojo())) {
            int contador = 0;
            switch (camino[i + jugadorEnTurno.getResultadoDado()].getText()) {
                case "♠":
                    contador = 1;
                    break;
                case "2":
                    contador = 2;
                    break;
                case "3":
                    contador = 3;
                    break;
                case "4":
                    contador = 4;
                    break;
            }
            
            do {
                
                if ("".equals(vistaLudo.jButtonCasaRoja1.getText())) {
                    vistaLudo.jButtonCasaRoja1.setText("♠");
                    contador--;
                } else {
                    if ("".equals(vistaLudo.jButtonCasaRoja2.getText())) {
                        vistaLudo.jButtonCasaRoja2.setText("♠");
                        contador--;
                    } else {
                        if ("".equals(vistaLudo.jButtonCasaRoja3.getText())) {
                            vistaLudo.jButtonCasaRoja3.setText("♠");
                            contador--;
                        } else {
                            if ("".equals(vistaLudo.jButtonCasaRoja4.getText())) {
                                vistaLudo.jButtonCasaRoja4.setText("♠");
                                contador--;
                            }
                        }
                    }
                }
            } while (contador > 0);
        }

        //azul
        if (jugadorEnTurno.getId() != 2 && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(paleta.getColorAzul())) {
            int contador = 0;
            switch (camino[i + jugadorEnTurno.getResultadoDado()].getText()) {
                case "♠":
                    contador = 1;
                    break;
                case "2":
                    contador = 2;
                    break;
                case "3":
                    contador = 3;
                    break;
                case "4":
                    contador = 4;
                    break;
            }
            
            do {
                
                if ("".equals(vistaLudo.jButtonCasaAzul1.getText())) {
                    vistaLudo.jButtonCasaAzul1.setText("♠");
                    contador--;
                } else {
                    if ("".equals(vistaLudo.jButtonCasaAzul2.getText())) {
                        vistaLudo.jButtonCasaAzul2.setText("♠");
                        contador--;
                    } else {
                        if ("".equals(vistaLudo.jButtonCasaAzul3.getText())) {
                            vistaLudo.jButtonCasaAzul3.setText("♠");
                            contador--;
                        } else {
                            if ("".equals(vistaLudo.jButtonCasaAzul4.getText())) {
                                vistaLudo.jButtonCasaAzul4.setText("♠");
                                contador--;
                            }
                        }
                    }
                }
            } while (contador > 0);
        }

        //verde
        if (jugadorEnTurno.getId() != 3 && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(paleta.getColorVerde())) {
            int contador = 0;
            switch (camino[i + jugadorEnTurno.getResultadoDado()].getText()) {
                case "♠":
                    contador = 1;
                    break;
                case "2":
                    contador = 2;
                    break;
                case "3":
                    contador = 3;
                    break;
                case "4":
                    contador = 4;
                    break;
            }
            
            do {
                
                if ("".equals(vistaLudo.jButtonCasaVerde1.getText())) {
                    vistaLudo.jButtonCasaVerde1.setText("♠");
                    contador--;
                } else {
                    if ("".equals(vistaLudo.jButtonCasaVerde2.getText())) {
                        vistaLudo.jButtonCasaVerde2.setText("♠");
                        contador--;
                    } else {
                        if ("".equals(vistaLudo.jButtonCasaVerde3.getText())) {
                            vistaLudo.jButtonCasaVerde3.setText("♠");
                            contador--;
                        } else {
                            if ("".equals(vistaLudo.jButtonCasaVerde4.getText())) {
                                vistaLudo.jButtonCasaVerde4.setText("♠");
                                contador--;
                            }
                        }
                    }
                }
            } while (contador > 0);
        }

        //ocre
        if (jugadorEnTurno.getId() != 4 && camino[i + jugadorEnTurno.getResultadoDado()].getForeground().equals(paleta.getColorOcre())) {
            int contador = 0;
            switch (camino[i + jugadorEnTurno.getResultadoDado()].getText()) {
                case "♠":
                    contador = 1;
                    break;
                case "2":
                    contador = 2;
                    break;
                case "3":
                    contador = 3;
                    break;
                case "4":
                    contador = 4;
                    break;
            }
            
            do {
                
                if ("".equals(vistaLudo.jButtonCasaOcre1.getText())) {
                    vistaLudo.jButtonCasaOcre1.setText("♠");
                    contador--;
                } else {
                    if ("".equals(vistaLudo.jButtonCasaOcre2.getText())) {
                        vistaLudo.jButtonCasaOcre2.setText("♠");
                        contador--;
                    } else {
                        if ("".equals(vistaLudo.jButtonCasaOcre3.getText())) {
                            vistaLudo.jButtonCasaOcre3.setText("♠");
                            contador--;
                        } else {
                            if ("".equals(vistaLudo.jButtonCasaOcre4.getText())) {
                                vistaLudo.jButtonCasaOcre4.setText("♠");
                                contador--;
                            }
                        }
                    }
                }
            } while (contador > 0);
        }

        //PONE SU FICHA LUEGO DE MANDAR LAS DE OTRO COLOR QUE HABIAN A SU CASA
        camino[i + jugadorEnTurno.getResultadoDado()].setText("♠");
        camino[i + jugadorEnTurno.getResultadoDado()].setForeground(jugadorEnTurno.getColor());
        if (jugadorEnTurno.getResultadoDado() != 6) {
            controlador.avanzarCola(cola);
            controlador.nuevoTurno(cola);
            return;
        } else {
            controlador.nuevoTurno(cola);
            return;
        }
    }
    
    public void evaluarCasa(JButton boton, Jugador jugadorEnTurno, PaletaColores paleta, VistaPrincipal vistaLudo,
            Cola cola, ControladorPrincipal controlador) {

        //evaluamos casa roja
        if (boton.getForeground().equals(paleta.getColorRojo())) {
            if ("♠".equals(boton.getText()) && controlador.isMovimientoEnEspera() && jugadorEnTurno.getId() == 1) {
                if (jugadorEnTurno.getResultadoDado() == 1 || jugadorEnTurno.getResultadoDado() == 6) {
                    if ("♠".equals(vistaLudo.jButton1.getText()) && vistaLudo.jButton1.getForeground().equals(paleta.getColorRojo())) {
                        vistaLudo.jButton1.setText("2");
                        vistaLudo.jButton1.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("2".equals(vistaLudo.jButton1.getText()) && vistaLudo.jButton1.getForeground().equals(paleta.getColorRojo())) {
                        vistaLudo.jButton1.setText("3");
                        vistaLudo.jButton1.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                       controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("3".equals(vistaLudo.jButton1.getText())&& vistaLudo.jButton1.getForeground().equals(paleta.getColorRojo())) {
                        vistaLudo.jButton1.setText("4");
                        vistaLudo.jButton1.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("".equals(vistaLudo.jButton1.getText())) {
                        vistaLudo.jButton1.setText("♠");
                        vistaLudo.jButton1.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                    }
                    
                    if(!"".equals(vistaLudo.jButton1.getText()) && !vistaLudo.jButton1.getForeground().equals(paleta.getColorRojo())){
                        if(jugadorEnTurno.getResultadoDado()==1){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoRojo(), -1, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                        if(jugadorEnTurno.getResultadoDado()==6){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoRojo(), -6, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                    }
                    
                }
            }
        }

        //evaluamos casa azul
        if (boton.getForeground().equals(paleta.getColorAzul())) {
            if ("♠".equals(boton.getText()) && controlador.isMovimientoEnEspera() && jugadorEnTurno.getId() == 2) {
                if (jugadorEnTurno.getResultadoDado() == 1 || jugadorEnTurno.getResultadoDado() == 6) {
                    if ("♠".equals(vistaLudo.jButton40.getText())&& vistaLudo.jButton40.getForeground().equals(paleta.getColorAzul())) {
                        vistaLudo.jButton40.setText("2");
                        vistaLudo.jButton40.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("2".equals(vistaLudo.jButton40.getText())&& vistaLudo.jButton40.getForeground().equals(paleta.getColorAzul())) {
                        vistaLudo.jButton40.setText("3");
                        vistaLudo.jButton40.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("3".equals(vistaLudo.jButton40.getText())&& vistaLudo.jButton40.getForeground().equals(paleta.getColorAzul())) {
                        vistaLudo.jButton40.setText("4");
                        vistaLudo.jButton40.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("".equals(vistaLudo.jButton40.getText())) {
                        vistaLudo.jButton40.setText("♠");
                        vistaLudo.jButton40.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                    }
                    if(!"".equals(vistaLudo.jButton40.getText()) && !vistaLudo.jButton40.getForeground().equals(paleta.getColorAzul())){
                        if(jugadorEnTurno.getResultadoDado()==1){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoAzul(), -1, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                        if(jugadorEnTurno.getResultadoDado()==6){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoAzul(), -6, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                    }
                }
            }
        }
        
        //evaluamos casa verde
        if (boton.getForeground().equals(paleta.getColorVerde())) {
            if ("♠".equals(boton.getText()) && controlador.isMovimientoEnEspera() && jugadorEnTurno.getId() == 3) {
                if (jugadorEnTurno.getResultadoDado() == 1 || jugadorEnTurno.getResultadoDado() == 6) {
                    if ("♠".equals(vistaLudo.jButton14.getText())&& vistaLudo.jButton14.getForeground().equals(paleta.getColorVerde())) {
                        vistaLudo.jButton14.setText("2");
                        vistaLudo.jButton14.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("2".equals(vistaLudo.jButton14.getText())&& vistaLudo.jButton14.getForeground().equals(paleta.getColorVerde())) {
                        vistaLudo.jButton14.setText("3");
                        vistaLudo.jButton14.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("3".equals(vistaLudo.jButton14.getText())&& vistaLudo.jButton14.getForeground().equals(paleta.getColorVerde())) {
                        vistaLudo.jButton14.setText("4");
                        vistaLudo.jButton14.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("".equals(vistaLudo.jButton14.getText())) {
                        vistaLudo.jButton14.setText("♠");
                        vistaLudo.jButton14.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                    }
                    if(!"".equals(vistaLudo.jButton14.getText()) && !vistaLudo.jButton14.getForeground().equals(paleta.getColorVerde())){
                        if(jugadorEnTurno.getResultadoDado()==1){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoVerde(), -1, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                        if(jugadorEnTurno.getResultadoDado()==6){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoVerde(), -6, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                    }
                }
            }
        }
        
        //evaluamos casa ocre
        if (boton.getForeground().equals(paleta.getColorOcre())) {
            if ("♠".equals(boton.getText()) && controlador.isMovimientoEnEspera() && jugadorEnTurno.getId() == 4) {
                if (jugadorEnTurno.getResultadoDado() == 1 || jugadorEnTurno.getResultadoDado() == 6) {
                    if ("♠".equals(vistaLudo.jButton27.getText())&& vistaLudo.jButton27.getForeground().equals(paleta.getColorOcre())) {
                        vistaLudo.jButton27.setText("2");
                        vistaLudo.jButton27.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("2".equals(vistaLudo.jButton27.getText())&& vistaLudo.jButton27.getForeground().equals(paleta.getColorOcre())) {
                        vistaLudo.jButton27.setText("3");
                        vistaLudo.jButton27.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("3".equals(vistaLudo.jButton27.getText())&& vistaLudo.jButton27.getForeground().equals(paleta.getColorOcre())) {
                        vistaLudo.jButton27.setText("4");
                        vistaLudo.jButton27.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                        return;
                    }
                    if ("".equals(vistaLudo.jButton27.getText())) {
                        vistaLudo.jButton27.setText("♠");
                        vistaLudo.jButton27.setForeground(jugadorEnTurno.getColor());
                        boton.setText("");
                        controlador.setMovimientoEnEspera(false);
                        if (jugadorEnTurno.getResultadoDado() != 6) {
                            controlador.avanzarCola(cola);
                        }
                        controlador.nuevoTurno(cola);
                    }
                    if(!"".equals(vistaLudo.jButton27.getText()) && !vistaLudo.jButton27.getForeground().equals(paleta.getColorOcre())){
                        if(jugadorEnTurno.getResultadoDado()==1){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoOcre(), -1, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                        if(jugadorEnTurno.getResultadoDado()==6){
                            boton.setText("");
                            jugadorEnTurno.comerFichas(controlador.getCaminoOcre(), -6, jugadorEnTurno, paleta
                            ,controlador.getVistaLudo(),controlador.getCola(), controlador);
                        }
                    }
                }
            }
        }

    }
    
    public boolean coronar(Jugador jugadorEnTurno, PaletaColores paleta, Cola cola, Registro archivoRegistro,
            VistaPrincipal vistaLudo){
        
        jugadorEnTurno.setCoronas(jugadorEnTurno.getCoronas()+1);
        
        //ESCRIBIMOS EN EL ARCHIVO QUE SE HA CORONADO
        if(jugadorEnTurno.getColor().equals(paleta.getColorRojo())){
            archivoRegistro.escribirEnArchivo("+1 ROJA");
            archivoRegistro.leerArchivo();
        }
        if(jugadorEnTurno.getColor().equals(paleta.getColorAzul())){
            archivoRegistro.escribirEnArchivo("+1 AZUL");
            archivoRegistro.leerArchivo();
        }
        if(jugadorEnTurno.getColor().equals(paleta.getColorVerde())){
            archivoRegistro.escribirEnArchivo("+1 VERDE");
            archivoRegistro.leerArchivo();
        }
        if(jugadorEnTurno.getColor().equals(paleta.getColorOcre())){
            archivoRegistro.escribirEnArchivo("+1 OCRE");
            archivoRegistro.leerArchivo();
        }
        
        //PONEMOS FICHA BLANCA FALSA
        if(jugadorEnTurno.getColor().equals(paleta.getColorRojo())){
            if(vistaLudo.jButtonCasaRoja1.getText().equals("")){
                vistaLudo.jButtonCasaRoja1.setForeground(Color.WHITE);
                vistaLudo.jButtonCasaRoja1.setText("♠");
            }
            else{
                if (vistaLudo.jButtonCasaRoja2.getText().equals("")) {
                    vistaLudo.jButtonCasaRoja2.setForeground(Color.WHITE);
                    vistaLudo.jButtonCasaRoja2.setText("♠");
                }
                else{
                    if (vistaLudo.jButtonCasaRoja3.getText().equals("")) {
                        vistaLudo.jButtonCasaRoja3.setForeground(Color.WHITE);
                        vistaLudo.jButtonCasaRoja3.setText("♠");
                    }
                    else{
                        if (vistaLudo.jButtonCasaRoja4.getText().equals("")) {
                            vistaLudo.jButtonCasaRoja4.setForeground(Color.WHITE);
                            vistaLudo.jButtonCasaRoja4.setText("♠");
                        }
                    }
                }
            }
        }
        
        if(jugadorEnTurno.getColor().equals(paleta.getColorAzul())){
            if(vistaLudo.jButtonCasaAzul1.getText().equals("")){
                vistaLudo.jButtonCasaAzul1.setForeground(Color.WHITE);
                vistaLudo.jButtonCasaAzul1.setText("♠");
            }
            else{
                if (vistaLudo.jButtonCasaAzul2.getText().equals("")) {
                    vistaLudo.jButtonCasaAzul2.setForeground(Color.WHITE);
                    vistaLudo.jButtonCasaAzul2.setText("♠");
                }
                else{
                    if (vistaLudo.jButtonCasaAzul3.getText().equals("")) {
                        vistaLudo.jButtonCasaAzul3.setForeground(Color.WHITE);
                        vistaLudo.jButtonCasaAzul3.setText("♠");
                    }
                    else{
                        if (vistaLudo.jButtonCasaAzul4.getText().equals("")) {
                            vistaLudo.jButtonCasaAzul4.setForeground(Color.WHITE);
                            vistaLudo.jButtonCasaAzul4.setText("♠");
                        }
                    }
                }
            }
        }
        
        if(jugadorEnTurno.getColor().equals(paleta.getColorVerde())){
            if(vistaLudo.jButtonCasaVerde1.getText().equals("")){
                vistaLudo.jButtonCasaVerde1.setForeground(Color.WHITE);
                vistaLudo.jButtonCasaVerde1.setText("♠");
            }
            else{
                if (vistaLudo.jButtonCasaVerde2.getText().equals("")) {
                    vistaLudo.jButtonCasaVerde2.setForeground(Color.WHITE);
                    vistaLudo.jButtonCasaVerde2.setText("♠");
                }
                else{
                    if (vistaLudo.jButtonCasaVerde3.getText().equals("")) {
                        vistaLudo.jButtonCasaVerde3.setForeground(Color.WHITE);
                        vistaLudo.jButtonCasaVerde3.setText("♠");
                    }
                    else{
                        if (vistaLudo.jButtonCasaVerde4.getText().equals("")) {
                            vistaLudo.jButtonCasaVerde4.setForeground(Color.WHITE);
                            vistaLudo.jButtonCasaVerde4.setText("♠");
                        }
                    }
                }
            }
        }
        
        if(jugadorEnTurno.getColor().equals(paleta.getColorOcre())){
            if(vistaLudo.jButtonCasaOcre1.getText().equals("")){
                vistaLudo.jButtonCasaOcre1.setForeground(Color.WHITE);
                vistaLudo.jButtonCasaOcre1.setText("♠");
            }
            else{
                if (vistaLudo.jButtonCasaOcre2.getText().equals("")) {
                    vistaLudo.jButtonCasaOcre2.setForeground(Color.WHITE);
                    vistaLudo.jButtonCasaOcre2.setText("♠");
                }
                else{
                    if (vistaLudo.jButtonCasaOcre3.getText().equals("")) {
                        vistaLudo.jButtonCasaOcre3.setForeground(Color.WHITE);
                        vistaLudo.jButtonCasaOcre3.setText("♠");
                    }
                    else{
                        if (vistaLudo.jButtonCasaOcre4.getText().equals("")) {
                            vistaLudo.jButtonCasaOcre4.setForeground(Color.WHITE);
                            vistaLudo.jButtonCasaOcre4.setText("♠");
                        }
                    }
                }
            }
        }
        
        if(jugadorEnTurno.getCoronas()==4){
            cola.quitar();
            return false;
        }
        else{
            return true;
        }
    }
    
    //SETTERS Y GETTERS
    public JLabel getDadoColor() {
        return dadoColor;
    }
    public void setDadoColor(JLabel dadoColor) {
        this.dadoColor = dadoColor;
    }
    public Color getColor() {
        return color;
    }
    public int getResultadoDado() {
        return resultadoDado;
    }
    public void setResultadoDado(int resultadoDado) {
        this.resultadoDado = resultadoDado;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCoronas() {
        return coronas;
    }
    public void setCoronas(int coronas) {
        this.coronas = coronas;
    }    
}
