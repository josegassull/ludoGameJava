package ludogassull.controlador;

import java.awt.Color;
import java.util.Collections;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import ludogassull.modelo.Cola;
import ludogassull.vista.VistaPrincipal;
import ludogassull.modelo.Jugador;
import ludogassull.modelo.PaletaColores;
import ludogassull.modelo.Registro;

public class ControladorPrincipal implements ActionListener, KeyListener {
    
    private VistaPrincipal vistaLudo = new VistaPrincipal();//DEPENDENCIA y AGREGACION
    private Cola cola = new Cola();
    private Jugador jugadorRojo = null;
    private Jugador jugadorAzul = null;
    private Jugador jugadorVerde = null;
    private Jugador jugadorOcre = null;
    private int contadorDeJugadores=0;
    private PaletaColores paleta = new PaletaColores();
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private Jugador arregloJugadores[]=new Jugador[4];
    private Jugador jugadorEnTurno;
    private boolean jugadoresYaOrdenados=false;
    private boolean partidaIniciada=false;
    private boolean dadoEnEspera=false;
    private boolean movimientoEnEspera=false;
    private JButton caminoRojo[]=new JButton[62];//AGREGACION
    private JButton caminoAzul[]=new JButton[62];
    private JButton caminoVerde[]=new JButton[62];
    private JButton caminoOcre[]=new JButton[62];
    
    //MANEJO DE ARCHIVOS
    Registro archivoRegistro = new Registro();
    
    public ControladorPrincipal(VistaPrincipal vistaLudo) {
        this.vistaLudo = vistaLudo;
        
        //AGREGAMOS ACTION LISTENERS A CADA BOTON
        agegarListeners();
        
        //Asignamos botones a cada uno de los arreglos de los caminos
        llenarCaminos();
        
        //Iniciamos el registro
        this.archivoRegistro.crearArchivo();
        
        // ESCAPE PRESIONADO
        int escapeKey = KeyEvent.VK_ESCAPE;
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(escapeKey, 0);
        vistaLudo.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "escapeKeyPressed");
        vistaLudo.getRootPane().getActionMap().put("escapeKeyPressed", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                getArchivoRegistro().eliminarArchivo();
                System.exit(0);
            }
        });
        
    }
    
    @Override
    public void actionPerformed(ActionEvent  e) {
        
        //
        //CUANDO SE PRESIONA EL BOTON DEL DADO
        //
        if(e.getSource() == this.vistaLudo.jButtonDado){
            if(this.isDadoEnEspera()==true){
                this.jugadorEnTurno.tirarDado();
                String s=Integer.toString(this.jugadorEnTurno.getResultadoDado());
                this.getVistaLudo().jButtonDado.setText(s);
                this.jugadorEnTurno.getDadoColor().setText(s);
                
                //SI EL JUGADOR QUE TIRÓ EL DADO ESTÁ EN CASA COMPLETAMENTE O QUE PASA SI NO
                switch(this.jugadorEnTurno.getId()){
                    case 1:
                        if("♠".equals(this.vistaLudo.jButtonCasaRoja1.getText()) && "♠".equals(this.vistaLudo.jButtonCasaRoja2.getText()) && "♠".equals(this.vistaLudo.jButtonCasaRoja3.getText()) && "♠".equals(this.vistaLudo.jButtonCasaRoja4.getText())){
                            if(this.jugadorEnTurno.getResultadoDado()!=1 && this.jugadorEnTurno.getResultadoDado()!=6){
                                this.avanzarCola(cola);
                                this.nuevoTurno(cola);
                            }
                            else{
                                this.setMovimientoEnEspera(true);
                                this.setDadoEnEspera(false);
                            }
                        }
                        else{
                            this.setDadoEnEspera(false);
                            this.setMovimientoEnEspera(true);
                        }
                    break;
                    case 2:
                        if("♠".equals(this.vistaLudo.jButtonCasaAzul1.getText()) && "♠".equals(this.vistaLudo.jButtonCasaAzul2.getText()) && "♠".equals(this.vistaLudo.jButtonCasaAzul3.getText()) && "♠".equals(this.vistaLudo.jButtonCasaAzul4.getText())){
                            if(this.jugadorEnTurno.getResultadoDado()!=1 && this.jugadorEnTurno.getResultadoDado()!=6){
                                this.avanzarCola(cola);
                                this.nuevoTurno(cola);
                            }
                            else{
                                this.setMovimientoEnEspera(true);
                                this.setDadoEnEspera(false);
                            }
                        }
                        else{
                            this.setDadoEnEspera(false);
                            this.setMovimientoEnEspera(true);
                        }
                    break;
                    case 3:
                        if("♠".equals(this.vistaLudo.jButtonCasaVerde1.getText()) && "♠".equals(this.vistaLudo.jButtonCasaVerde2.getText()) && "♠".equals(this.vistaLudo.jButtonCasaVerde3.getText()) && "♠".equals(this.vistaLudo.jButtonCasaVerde4.getText())){
                            if(this.jugadorEnTurno.getResultadoDado()!=1 && this.jugadorEnTurno.getResultadoDado()!=6){
                                this.avanzarCola(cola);
                                this.nuevoTurno(cola);
                            }
                            else{
                                this.setMovimientoEnEspera(true);
                                this.setDadoEnEspera(false);
                            }
                        }
                        else{
                            this.setDadoEnEspera(false);
                            this.setMovimientoEnEspera(true);
                        }
                    break;
                    case 4:
                        if("♠".equals(this.vistaLudo.jButtonCasaOcre1.getText()) && "♠".equals(this.vistaLudo.jButtonCasaOcre2.getText()) && "♠".equals(this.vistaLudo.jButtonCasaOcre3.getText()) && "♠".equals(this.vistaLudo.jButtonCasaOcre4.getText())){
                            if(this.jugadorEnTurno.getResultadoDado()!=1 && this.jugadorEnTurno.getResultadoDado()!=6){
                                this.avanzarCola(cola);
                                this.nuevoTurno(cola);
                            }
                            else{
                                this.setMovimientoEnEspera(true);
                                this.setDadoEnEspera(false);
                            }
                        }
                        else{
                            this.setDadoEnEspera(false);
                            this.setMovimientoEnEspera(true);
                        }
                    break;
                }
            }
        }
        
        //
        //CUANDO SE PRESIONAN LOS BOTONES DE AGREGAR JUGADORES
        //
        if (e.getSource() == this.vistaLudo.jButtonAgregarJugadorRojo) {
            if (!this.vistaLudo.jButtonAgregarJugadorRojo.getBackground().equals(this.paleta.getColorRojo()) && isPartidaIniciada()==false) {
                this.vistaLudo.jButtonAgregarJugadorRojo.setBackground(this.paleta.getColorRojo());
                this.jugadorRojo= new Jugador(this.paleta.getColorRojo(), this.vistaLudo.jLabelDadoRojo, 1);
                this.arregloJugadores[0]=this.jugadorRojo;
                this.setContadorDeJugadores(getContadorDeJugadores()+1);
            } else {
                if (this.vistaLudo.jButtonAgregarJugadorRojo.getBackground().equals(this.paleta.getColorRojo()) && isPartidaIniciada()==false) {
                    this.vistaLudo.jButtonAgregarJugadorRojo.setBackground(Color.gray);
                    this.jugadorRojo= null;
                    this.arregloJugadores[0]=this.jugadorRojo;
                    setContadorDeJugadores(getContadorDeJugadores()-1);
                }
            }
        }
        
        if (e.getSource() == this.vistaLudo.jButtonAgregarJugadorAzul) {
            if (!this.vistaLudo.jButtonAgregarJugadorAzul.getBackground().equals(this.paleta.getColorAzul()) && isPartidaIniciada()==false) {
                this.vistaLudo.jButtonAgregarJugadorAzul.setBackground(this.paleta.getColorAzul());
                this.jugadorAzul= new Jugador(this.paleta.getColorAzul(), this.vistaLudo.jLabelDadoAzul, 2);
                this.arregloJugadores[1]=this.jugadorAzul;
                setContadorDeJugadores(getContadorDeJugadores()+1);
            } else {
                if (this.vistaLudo.jButtonAgregarJugadorAzul.getBackground().equals(this.paleta.getColorAzul()) && isPartidaIniciada()==false) {
                    this.vistaLudo.jButtonAgregarJugadorAzul.setBackground(Color.gray);
                    this.jugadorAzul= null;
                    this.arregloJugadores[1]=this.jugadorAzul;
                    setContadorDeJugadores(getContadorDeJugadores()-1);
                }
            }
        }
        
        if (e.getSource() == this.vistaLudo.jButtonAgregarJugadorVerde) {
            if (!this.vistaLudo.jButtonAgregarJugadorVerde.getBackground().equals(this.paleta.getColorVerde()) && isPartidaIniciada()==false) {
                this.vistaLudo.jButtonAgregarJugadorVerde.setBackground(this.paleta.getColorVerde());
                this.jugadorVerde= new Jugador(this.paleta.getColorVerde(), this.vistaLudo.jLabelDadoVerde, 3);
                this.arregloJugadores[2]=this.jugadorVerde;
                setContadorDeJugadores(getContadorDeJugadores()+1);
            } else {
                if (this.vistaLudo.jButtonAgregarJugadorVerde.getBackground().equals(this.paleta.getColorVerde()) && isPartidaIniciada()==false) {
                    this.vistaLudo.jButtonAgregarJugadorVerde.setBackground(Color.gray);
                    this.jugadorVerde=null;
                    this.arregloJugadores[2]=this.jugadorVerde;
                    setContadorDeJugadores(getContadorDeJugadores()-1);
                }
            }
        }
        
        if (e.getSource() == this.vistaLudo.jButtonAgregarJugadorOcre) {
            if (!this.vistaLudo.jButtonAgregarJugadorOcre.getBackground().equals(this.paleta.getColorOcre()) && isPartidaIniciada()==false) {
                this.vistaLudo.jButtonAgregarJugadorOcre.setBackground(this.paleta.getColorOcre());
                this.jugadorOcre= new Jugador(this.paleta.getColorOcre(), this.vistaLudo.jLabelDadoOcre, 4);
                this.arregloJugadores[3]=this.jugadorOcre;
                setContadorDeJugadores(getContadorDeJugadores()+1);
            } else {
                if (this.vistaLudo.jButtonAgregarJugadorOcre.getBackground().equals(this.paleta.getColorOcre()) && isPartidaIniciada()==false) {
                    this.vistaLudo.jButtonAgregarJugadorOcre.setBackground(Color.gray);
                    this.jugadorOcre=null;
                    this.arregloJugadores[3]=this.jugadorOcre;
                    this.setContadorDeJugadores(getContadorDeJugadores()-1);
                }
            }
        }
        
        //---------------------------------------------------------------
        //CUANDO SE PRECIONA EL BOTON DE INICIAR PARTIDA
        //---------------------------------------------------------------        
        if (e.getSource() == vistaLudo.jButtonIniciarPartida) {
            if(this.getContadorDeJugadores()>=2){
                if(getJugadorRojo()!=null){
                    this.vistaLudo.jButtonCasaRoja1.setText("♠");
                    this.vistaLudo.jButtonCasaRoja2.setText("♠");
                    this.vistaLudo.jButtonCasaRoja3.setText("♠");
                    this.vistaLudo.jButtonCasaRoja4.setText("♠");
                }
                if(getJugadorAzul()!=null){
                    this.vistaLudo.jButtonCasaAzul1.setText("♠");
                    this.vistaLudo.jButtonCasaAzul2.setText("♠");
                    this.vistaLudo.jButtonCasaAzul3.setText("♠");
                    this.vistaLudo.jButtonCasaAzul4.setText("♠");
                }
                if(getJugadorVerde()!=null){
                    this.vistaLudo.jButtonCasaVerde1.setText("♠");
                    this.vistaLudo.jButtonCasaVerde2.setText("♠");
                    this.vistaLudo.jButtonCasaVerde3.setText("♠");
                    this.vistaLudo.jButtonCasaVerde4.setText("♠");
                }
                if(getJugadorOcre()!=null){
                    this.vistaLudo.jButtonCasaOcre1.setText("♠");
                    this.vistaLudo.jButtonCasaOcre2.setText("♠");
                    this.vistaLudo.jButtonCasaOcre3.setText("♠");
                    this.vistaLudo.jButtonCasaOcre4.setText("♠");
                }
                //SI NO FALTAN JUGADORES SE INICIA LA PARTIDA
                setPartidaIniciada(true);
                this.vistaLudo.jButtonAlertaRoja.setVisible(false);
                this.vistaLudo.jButtonIniciarPartida.setVisible(false);
                this.vistaLudo.jButtonDado.setVisible(true);
                this.vistaLudo.jLabelInfo.setVisible(true);
                
                //Ordenamos aleatoriamente
                this.listaJugadores.add(this.arregloJugadores[0]);
                this.listaJugadores.add(this.arregloJugadores[1]);
                this.listaJugadores.add(this.arregloJugadores[2]);
                this.listaJugadores.add(this.arregloJugadores[3]);
                
                Collections.shuffle(this.listaJugadores);
                try{
                    this.arregloJugadores[0]=this.listaJugadores.get(0);
                }
                catch(Exception exception){
                }
                
                try{
                    this.arregloJugadores[1]=this.listaJugadores.get(1);
                }
                catch(Exception exception){
                }
                
                try{
                    this.arregloJugadores[2]=this.listaJugadores.get(2);
                }
                catch(Exception exception){
                }
                
                try{
                    this.arregloJugadores[3]=this.listaJugadores.get(3);
                }
                catch(Exception exception){
                }
                
                //Trasponemos esa lista aleatoriamente ordenada a la cola
                llenarCola(this.arregloJugadores, this.cola);
                
                //Iniciamos un nuevo turno
                nuevoTurno(this.cola);
                
            }
            //si faltan jugadores se muestra una alerta
            else{
                this.vistaLudo.jButtonAlertaRoja.setVisible(true);
            }
        }
        
        //---------------------------------------------------------------
        //CUANDO SE PRECIONA UNA CASILLA
        //---------------------------------------------------------------
        if (e.getSource()==this.vistaLudo.jButton0 || e.getSource()==this.vistaLudo.jButton1 ||
                e.getSource()==this.vistaLudo.jButton2 || e.getSource()==this.vistaLudo.jButton3 ||
                e.getSource()==this.vistaLudo.jButton4 || e.getSource()==this.vistaLudo.jButton5 || 
                e.getSource()==this.vistaLudo.jButton6 || e.getSource()==this.vistaLudo.jButton7 ||
                e.getSource()==this.vistaLudo.jButton8 || e.getSource()==this.vistaLudo.jButton9 ||
                e.getSource()==this.vistaLudo.jButton10 || e.getSource()==this.vistaLudo.jButton11 ||
                e.getSource()==this.vistaLudo.jButton12 || e.getSource()==this.vistaLudo.jButton13 ||
                e.getSource()==this.vistaLudo.jButton14 || e.getSource()==this.vistaLudo.jButton15 ||
                e.getSource()==this.vistaLudo.jButton16 || e.getSource()==this.vistaLudo.jButton17 ||
                e.getSource()==this.vistaLudo.jButton18 || e.getSource()==this.vistaLudo.jButton19 ||
                e.getSource()==this.vistaLudo.jButton20 || e.getSource()==this.vistaLudo.jButton21 ||
                e.getSource()==this.vistaLudo.jButton22 || e.getSource()==this.vistaLudo.jButton23 ||
                e.getSource()==this.vistaLudo.jButton24 || e.getSource()==this.vistaLudo.jButton25 ||
                e.getSource()==this.vistaLudo.jButton26 || e.getSource()==this.vistaLudo.jButton27 ||
                e.getSource()==this.vistaLudo.jButton28 || e.getSource()==this.vistaLudo.jButton29 ||
                e.getSource()==this.vistaLudo.jButton30 || e.getSource()==this.vistaLudo.jButton31 ||
                e.getSource()==this.vistaLudo.jButton32 || e.getSource()==this.vistaLudo.jButton33 ||
                e.getSource()==this.vistaLudo.jButton34 || e.getSource()==this.vistaLudo.jButton35 ||
                e.getSource()==this.vistaLudo.jButton36 || e.getSource()==this.vistaLudo.jButton37 ||
                e.getSource()==this.vistaLudo.jButton38 || e.getSource()==this.vistaLudo.jButton39 ||
                e.getSource()==this.vistaLudo.jButton40 || e.getSource()==this.vistaLudo.jButton41 ||
                e.getSource()==this.vistaLudo.jButton42 || e.getSource()==this.vistaLudo.jButton43 ||
                e.getSource()==this.vistaLudo.jButton44 || e.getSource()==this.vistaLudo.jButton45 ||
                e.getSource()==this.vistaLudo.jButton46 || e.getSource()==this.vistaLudo.jButton47 ||
                e.getSource()==this.vistaLudo.jButton48 || e.getSource()==this.vistaLudo.jButton49 ||
                e.getSource()==this.vistaLudo.jButton50 || e.getSource()==this.vistaLudo.jButton51 ||
                e.getSource()==this.vistaLudo.jButton52 || e.getSource()==this.vistaLudo.jButton53 ||
                e.getSource()==this.vistaLudo.jButton54 || e.getSource()==this.vistaLudo.jButton55 ||
                e.getSource()==this.vistaLudo.jButton56 || e.getSource()==this.vistaLudo.jButton58 ||
                e.getSource()==this.vistaLudo.jButton59 || e.getSource()==this.vistaLudo.jButton60 ||
                e.getSource()==this.vistaLudo.jButton61 || e.getSource()==this.vistaLudo.jButton62 ||
                e.getSource()==this.vistaLudo.jButton63 || e.getSource()==this.vistaLudo.jButton65 ||
                e.getSource()==this.vistaLudo.jButton66 || e.getSource()==this.vistaLudo.jButton67 ||
                e.getSource()==this.vistaLudo.jButton68 || e.getSource()==this.vistaLudo.jButton69 ||
                e.getSource()==this.vistaLudo.jButton70 || e.getSource()==this.vistaLudo.jButton71 ||
                e.getSource()==this.vistaLudo.jButton72 || e.getSource()==this.vistaLudo.jButton73 ||
                e.getSource()==this.vistaLudo.jButton74 || e.getSource()==this.vistaLudo.jButton75 ||
                e.getSource()==this.vistaLudo.jButton76)
        {
            //ANALIZAMOS LA CASILLA QUE SE HA PRESIONADO
            JButton boton =(JButton) e.getSource();
            if(this.isMovimientoEnEspera() && boton.getForeground().equals(this.jugadorEnTurno.getColor())){
                if ("♠".equals(boton.getText()) || "2".equals(boton.getText()) ||"3".equals(boton.getText()) || "4".equals(boton.getText())){
                    
                    this.jugadorEnTurno.moverFicha(boton, jugadorEnTurno, caminoRojo, caminoAzul, caminoVerde, caminoOcre, cola, this);
                    
                }
            }
        }
        
        //---------------------------------------------------------------
        //CUANDO SE PRESIONA UNA CASA
        //---------------------------------------------------------------
        if (e.getSource()==this.vistaLudo.jButtonCasaRoja1 || e.getSource()==this.vistaLudo.jButtonCasaRoja2
                ||e.getSource()==this.vistaLudo.jButtonCasaRoja3 ||e.getSource()==this.vistaLudo.jButtonCasaRoja4
                ||e.getSource()==this.vistaLudo.jButtonCasaAzul1 || e.getSource()==this.vistaLudo.jButtonCasaAzul2
                ||e.getSource()==this.vistaLudo.jButtonCasaAzul3 || e.getSource()==this.vistaLudo.jButtonCasaAzul4
                ||e.getSource()==this.vistaLudo.jButtonCasaVerde1 || e.getSource()==this.vistaLudo.jButtonCasaVerde2
                ||e.getSource()==this.vistaLudo.jButtonCasaVerde3 ||e.getSource()==this.vistaLudo.jButtonCasaVerde4
                ||e.getSource()==this.vistaLudo.jButtonCasaOcre1 || e.getSource()==this.vistaLudo.jButtonCasaOcre2
                ||e.getSource()==this.vistaLudo.jButtonCasaOcre3 ||e.getSource()==this.vistaLudo.jButtonCasaOcre4){
            Object origenEvento = e.getSource();
            JButton boton = (JButton) origenEvento;
            this.jugadorEnTurno.evaluarCasa(boton, this.getJugadorEnTurno(), this.getPaleta(), this.getVistaLudo(),
                    this.getCola(), this);
        }
        
        //---------------------------------------------------------------
        //CUANDO SE PRESIONA SALIR
        //---------------------------------------------------------------
        if(e.getSource()==this.vistaLudo.jButtonSalir){
            this.archivoRegistro.eliminarArchivo();
            System.exit(0);
        }
    }
    
    //FUNCIONES
    //---------------------------------------------------------------------
    
    public void nuevoTurno(Cola cola){
        
        try{
            if (this.cola.getFrente().getElemento() != null) {
                this.setDadoEnEspera(true);
                this.vistaLudo.jButtonDado.setText("CLICK");
                this.setMovimientoEnEspera(false);
                this.jugadorEnTurno = cola.getFrente().getElemento();
                this.vistaLudo.jLabelTurno.setForeground(this.jugadorEnTurno.getColor());
                this.vistaLudo.jLabelInfo.setForeground(this.jugadorEnTurno.getColor());
            }
        }
        catch(Exception excetion){
            this.setDadoEnEspera(false);
            this.setMovimientoEnEspera(false);
        }
    }
    
    public void llenarCola(Jugador arreglo[], Cola cola){
        if(arreglo[0]!=null){
            cola.insertar(arreglo[0]);
        }
        if(arreglo[1]!=null){
            cola.insertar(arreglo[1]);
        }
        if(arreglo[2]!=null){
            cola.insertar(arreglo[2]);
        }
        if(arreglo[3]!=null){
            cola.insertar(arreglo[3]);
        }
    }
    
    public void avanzarCola(Cola cola){
        Jugador aux;
        aux=cola.getFrente().getElemento();
        cola.quitar();
        cola.insertar(aux);
    }
    
    //AGREGAMOS LISTENERS A LOS BOTONES
    public final void agegarListeners(){
        this.vistaLudo.jButtonDado.addActionListener(this);
        this.vistaLudo.jButtonAgregarJugadorRojo.addActionListener(this);
        this.vistaLudo.jButtonAgregarJugadorAzul.addActionListener(this);
        this.vistaLudo.jButtonAgregarJugadorVerde.addActionListener(this);
        this.vistaLudo.jButtonAgregarJugadorOcre.addActionListener(this);
        this.vistaLudo.jButtonIniciarPartida.addActionListener(this);
        this.vistaLudo.jButtonCasaRoja1.addActionListener(this);
        this.vistaLudo.jButtonCasaRoja2.addActionListener(this);
        this.vistaLudo.jButtonCasaRoja3.addActionListener(this);
        this.vistaLudo.jButtonCasaRoja4.addActionListener(this);
        this.vistaLudo.jButtonCasaAzul1.addActionListener(this);
        this.vistaLudo.jButtonCasaAzul2.addActionListener(this);
        this.vistaLudo.jButtonCasaAzul3.addActionListener(this);
        this.vistaLudo.jButtonCasaAzul4.addActionListener(this);
        this.vistaLudo.jButtonCasaVerde1.addActionListener(this);
        this.vistaLudo.jButtonCasaVerde2.addActionListener(this);
        this.vistaLudo.jButtonCasaVerde3.addActionListener(this);
        this.vistaLudo.jButtonCasaVerde4.addActionListener(this);
        this.vistaLudo.jButtonCasaOcre1.addActionListener(this);
        this.vistaLudo.jButtonCasaOcre2.addActionListener(this);
        this.vistaLudo.jButtonCasaOcre3.addActionListener(this);
        this.vistaLudo.jButtonCasaOcre4.addActionListener(this);
        this.vistaLudo.jButtonSalir.addActionListener(this);
        this.vistaLudo.addKeyListener(this);
        this.vistaLudo.setFocusable(true);
        //GREGAMOS ACTION LISTENERS A CADA BOTON DE LAS CASILLAS
        this.vistaLudo.jButton0.addActionListener(this);this.vistaLudo.jButton1.addActionListener(this);        
        this.vistaLudo.jButton2.addActionListener(this);this.vistaLudo.jButton3.addActionListener(this);        
        this.vistaLudo.jButton4.addActionListener(this);this.vistaLudo.jButton5.addActionListener(this);        
        this.vistaLudo.jButton6.addActionListener(this);this.vistaLudo.jButton7.addActionListener(this);        
        this.vistaLudo.jButton8.addActionListener(this);this.vistaLudo.jButton9.addActionListener(this);        
        this.vistaLudo.jButton10.addActionListener(this);this.vistaLudo.jButton11.addActionListener(this);        
        this.vistaLudo.jButton12.addActionListener(this);this.vistaLudo.jButton13.addActionListener(this);        
        this.vistaLudo.jButton14.addActionListener(this);this.vistaLudo.jButton15.addActionListener(this);        
        this.vistaLudo.jButton16.addActionListener(this);this.vistaLudo.jButton17.addActionListener(this);        
        this.vistaLudo.jButton18.addActionListener(this);this.vistaLudo.jButton19.addActionListener(this);        
        this.vistaLudo.jButton20.addActionListener(this);this.vistaLudo.jButton21.addActionListener(this);        
        this.vistaLudo.jButton22.addActionListener(this);this.vistaLudo.jButton23.addActionListener(this);        
        this.vistaLudo.jButton24.addActionListener(this);this.vistaLudo.jButton25.addActionListener(this);        
        this.vistaLudo.jButton26.addActionListener(this);this.vistaLudo.jButton27.addActionListener(this);        
        this.vistaLudo.jButton28.addActionListener(this);this.vistaLudo.jButton29.addActionListener(this);        
        this.vistaLudo.jButton30.addActionListener(this);this.vistaLudo.jButton31.addActionListener(this);        
        this.vistaLudo.jButton32.addActionListener(this);this.vistaLudo.jButton33.addActionListener(this);        
        this.vistaLudo.jButton34.addActionListener(this);this.vistaLudo.jButton35.addActionListener(this);        
        this.vistaLudo.jButton36.addActionListener(this);this.vistaLudo.jButton37.addActionListener(this);        
        this.vistaLudo.jButton38.addActionListener(this);this.vistaLudo.jButton39.addActionListener(this);        
        this.vistaLudo.jButton40.addActionListener(this);this.vistaLudo.jButton41.addActionListener(this);        
        this.vistaLudo.jButton42.addActionListener(this);this.vistaLudo.jButton43.addActionListener(this);        
        this.vistaLudo.jButton44.addActionListener(this);this.vistaLudo.jButton45.addActionListener(this);        
        this.vistaLudo.jButton46.addActionListener(this);this.vistaLudo.jButton47.addActionListener(this);        
        this.vistaLudo.jButton48.addActionListener(this);this.vistaLudo.jButton49.addActionListener(this);        
        this.vistaLudo.jButton50.addActionListener(this);this.vistaLudo.jButton51.addActionListener(this);        
        this.vistaLudo.jButton52.addActionListener(this);this.vistaLudo.jButton53.addActionListener(this);        
        this.vistaLudo.jButton54.addActionListener(this);this.vistaLudo.jButton55.addActionListener(this);        
        this.vistaLudo.jButton56.addActionListener(this);this.vistaLudo.jButton57.addActionListener(this);        
        this.vistaLudo.jButton58.addActionListener(this);this.vistaLudo.jButton59.addActionListener(this);        
        this.vistaLudo.jButton60.addActionListener(this);this.vistaLudo.jButton61.addActionListener(this);        
        this.vistaLudo.jButton62.addActionListener(this);this.vistaLudo.jButton63.addActionListener(this);        
        this.vistaLudo.jButton65.addActionListener(this);this.vistaLudo.jButton66.addActionListener(this);        
        this.vistaLudo.jButton67.addActionListener(this);this.vistaLudo.jButton68.addActionListener(this);        
        this.vistaLudo.jButton69.addActionListener(this);this.vistaLudo.jButton70.addActionListener(this);        
        this.vistaLudo.jButton71.addActionListener(this);this.vistaLudo.jButton72.addActionListener(this);
        this.vistaLudo.jButton73.addActionListener(this);this.vistaLudo.jButton74.addActionListener(this);
        this.vistaLudo.jButton75.addActionListener(this);this.vistaLudo.jButton76.addActionListener(this);
    }
    
    //LLENAMOS LOS 4 CAMINOS CON BOTONES
    public final void llenarCaminos(){
        this.caminoRojo[0]=this.vistaLudo.jButton1;this.caminoRojo[1]=this.vistaLudo.jButton2;
        this.caminoRojo[2]=this.vistaLudo.jButton3;this.caminoRojo[3]=this.vistaLudo.jButton4;
        this.caminoRojo[4]=this.vistaLudo.jButton5;this.caminoRojo[5]=this.vistaLudo.jButton6;
        this.caminoRojo[6]=this.vistaLudo.jButton7;this.caminoRojo[7]=this.vistaLudo.jButton8;
        this.caminoRojo[8]=this.vistaLudo.jButton9;this.caminoRojo[9]=this.vistaLudo.jButton10;
        this.caminoRojo[10]=this.vistaLudo.jButton11;this.caminoRojo[11]=this.vistaLudo.jButton12;
        this.caminoRojo[12]=this.vistaLudo.jButton13;this.caminoRojo[13]=this.vistaLudo.jButton14;
        this.caminoRojo[14]=this.vistaLudo.jButton15;this.caminoRojo[15]=this.vistaLudo.jButton16;
        this.caminoRojo[16]=this.vistaLudo.jButton17;this.caminoRojo[17]=this.vistaLudo.jButton18;
        this.caminoRojo[18]=this.vistaLudo.jButton19;this.caminoRojo[19]=this.vistaLudo.jButton20;
        this.caminoRojo[20]=this.vistaLudo.jButton21;this.caminoRojo[21]=this.vistaLudo.jButton22;
        this.caminoRojo[22]=this.vistaLudo.jButton23;this.caminoRojo[23]=this.vistaLudo.jButton24;
        this.caminoRojo[24]=this.vistaLudo.jButton25;this.caminoRojo[25]=this.vistaLudo.jButton26;
        this.caminoRojo[26]=this.vistaLudo.jButton27;this.caminoRojo[27]=this.vistaLudo.jButton28;
        this.caminoRojo[28]=this.vistaLudo.jButton29;this.caminoRojo[29]=this.vistaLudo.jButton30;
        this.caminoRojo[30]=this.vistaLudo.jButton31;this.caminoRojo[31]=this.vistaLudo.jButton32;
        this.caminoRojo[32]=this.vistaLudo.jButton33;this.caminoRojo[33]=this.vistaLudo.jButton34;
        this.caminoRojo[34]=this.vistaLudo.jButton35;this.caminoRojo[35]=this.vistaLudo.jButton36;
        this.caminoRojo[36]=this.vistaLudo.jButton37;this.caminoRojo[37]=this.vistaLudo.jButton38;
        this.caminoRojo[38]=this.vistaLudo.jButton39;this.caminoRojo[39]=this.vistaLudo.jButton40;
        this.caminoRojo[40]=this.vistaLudo.jButton41;this.caminoRojo[41]=this.vistaLudo.jButton42;
        this.caminoRojo[42]=this.vistaLudo.jButton43;this.caminoRojo[43]=this.vistaLudo.jButton44;
        this.caminoRojo[44]=this.vistaLudo.jButton45;this.caminoRojo[45]=this.vistaLudo.jButton46;
        this.caminoRojo[46]=this.vistaLudo.jButton47;this.caminoRojo[47]=this.vistaLudo.jButton48;
        this.caminoRojo[48]=this.vistaLudo.jButton49;this.caminoRojo[49]=this.vistaLudo.jButton50;
        this.caminoRojo[50]=this.vistaLudo.jButton51;this.caminoRojo[51]=this.vistaLudo.jButton52;
        this.caminoRojo[52]=this.vistaLudo.jButton53;this.caminoRojo[53]=this.vistaLudo.jButton54;
        this.caminoRojo[54]=this.vistaLudo.jButton55;this.caminoRojo[55]=this.vistaLudo.jButton56;
        this.caminoRojo[56]=this.vistaLudo.jButton57;
        //REBOTE ROJO
        this.caminoRojo[57]=this.vistaLudo.jButton56;this.caminoRojo[58]=this.vistaLudo.jButton55;
        this.caminoRojo[59]=this.vistaLudo.jButton54;this.caminoRojo[60]=this.vistaLudo.jButton53;
        this.caminoRojo[61]=this.vistaLudo.jButton52;
        
        //CAMINO AZUL
        this.caminoAzul[0]=this.vistaLudo.jButton40;this.caminoAzul[1]=this.vistaLudo.jButton41;
        this.caminoAzul[2]=this.vistaLudo.jButton42;this.caminoAzul[3]=this.vistaLudo.jButton43;
        this.caminoAzul[4]=this.vistaLudo.jButton44;this.caminoAzul[5]=this.vistaLudo.jButton45;
        this.caminoAzul[6]=this.vistaLudo.jButton46;this.caminoAzul[7]=this.vistaLudo.jButton47;
        this.caminoAzul[8]=this.vistaLudo.jButton48;this.caminoAzul[9]=this.vistaLudo.jButton49;
        this.caminoAzul[10]=this.vistaLudo.jButton50;this.caminoAzul[11]=this.vistaLudo.jButton51;
        this.caminoAzul[12]=this.vistaLudo.jButton0;this.caminoAzul[13]=this.vistaLudo.jButton1;
        this.caminoAzul[14]=this.vistaLudo.jButton2;this.caminoAzul[15]=this.vistaLudo.jButton3;
        this.caminoAzul[16]=this.vistaLudo.jButton4;this.caminoAzul[17]=this.vistaLudo.jButton5;
        this.caminoAzul[18]=this.vistaLudo.jButton6;this.caminoAzul[19]=this.vistaLudo.jButton7;
        this.caminoAzul[20]=this.vistaLudo.jButton8;this.caminoAzul[21]=this.vistaLudo.jButton9;
        this.caminoAzul[22]=this.vistaLudo.jButton10;this.caminoAzul[23]=this.vistaLudo.jButton11;
        this.caminoAzul[24]=this.vistaLudo.jButton12;this.caminoAzul[25]=this.vistaLudo.jButton13;
        this.caminoAzul[26]=this.vistaLudo.jButton14;this.caminoAzul[27]=this.vistaLudo.jButton15;
        this.caminoAzul[28]=this.vistaLudo.jButton16;this.caminoAzul[29]=this.vistaLudo.jButton17;
        this.caminoAzul[30]=this.vistaLudo.jButton18;this.caminoAzul[31]=this.vistaLudo.jButton19;
        this.caminoAzul[32]=this.vistaLudo.jButton20;this.caminoAzul[33]=this.vistaLudo.jButton21;
        this.caminoAzul[34]=this.vistaLudo.jButton22;this.caminoAzul[35]=this.vistaLudo.jButton23;
        this.caminoAzul[36]=this.vistaLudo.jButton24;this.caminoAzul[37]=this.vistaLudo.jButton25;
        this.caminoAzul[38]=this.vistaLudo.jButton26;this.caminoAzul[39]=this.vistaLudo.jButton27;
        this.caminoAzul[40]=this.vistaLudo.jButton28;this.caminoAzul[41]=this.vistaLudo.jButton29;
        this.caminoAzul[42]=this.vistaLudo.jButton30;this.caminoAzul[43]=this.vistaLudo.jButton31;
        this.caminoAzul[44]=this.vistaLudo.jButton32;this.caminoAzul[45]=this.vistaLudo.jButton33;
        this.caminoAzul[46]=this.vistaLudo.jButton34;this.caminoAzul[47]=this.vistaLudo.jButton35;
        this.caminoAzul[48]=this.vistaLudo.jButton36;this.caminoAzul[49]=this.vistaLudo.jButton37;
        this.caminoAzul[50]=this.vistaLudo.jButton38;this.caminoAzul[51]=this.vistaLudo.jButton58;
        this.caminoAzul[52]=this.vistaLudo.jButton59;this.caminoAzul[53]=this.vistaLudo.jButton60;
        this.caminoAzul[54]=this.vistaLudo.jButton61;this.caminoAzul[55]=this.vistaLudo.jButton62;
        this.caminoAzul[56]=this.vistaLudo.jButton63;
        //REBOTE AZUL
        this.caminoAzul[57]=this.vistaLudo.jButton62;this.caminoAzul[58]=this.vistaLudo.jButton61;
        this.caminoAzul[59]=this.vistaLudo.jButton60;this.caminoAzul[60]=this.vistaLudo.jButton59;
        this.caminoAzul[61]=this.vistaLudo.jButton58;
        
        //CAMINO VERDE        
        this.caminoVerde[0]=this.vistaLudo.jButton14;this.caminoVerde[1]=this.vistaLudo.jButton15;
        this.caminoVerde[2]=this.vistaLudo.jButton16;this.caminoVerde[3]=this.vistaLudo.jButton17;
        this.caminoVerde[4]=this.vistaLudo.jButton18;this.caminoVerde[5]=this.vistaLudo.jButton19;
        this.caminoVerde[6]=this.vistaLudo.jButton20;this.caminoVerde[7]=this.vistaLudo.jButton21;
        this.caminoVerde[8]=this.vistaLudo.jButton22;this.caminoVerde[9]=this.vistaLudo.jButton23;
        this.caminoVerde[10]=this.vistaLudo.jButton24;this.caminoVerde[11]=this.vistaLudo.jButton25;
        this.caminoVerde[12]=this.vistaLudo.jButton26;this.caminoVerde[13]=this.vistaLudo.jButton27;
        this.caminoVerde[14]=this.vistaLudo.jButton28;this.caminoVerde[15]=this.vistaLudo.jButton29;
        this.caminoVerde[16]=this.vistaLudo.jButton30;this.caminoVerde[17]=this.vistaLudo.jButton31;
        this.caminoVerde[18]=this.vistaLudo.jButton32;this.caminoVerde[19]=this.vistaLudo.jButton33;
        this.caminoVerde[20]=this.vistaLudo.jButton34;this.caminoVerde[21]=this.vistaLudo.jButton35;
        this.caminoVerde[22]=this.vistaLudo.jButton36;this.caminoVerde[23]=this.vistaLudo.jButton37;
        this.caminoVerde[24]=this.vistaLudo.jButton38;this.caminoVerde[25]=this.vistaLudo.jButton39;
        this.caminoVerde[26]=this.vistaLudo.jButton40;this.caminoVerde[27]=this.vistaLudo.jButton41;
        this.caminoVerde[28]=this.vistaLudo.jButton42;this.caminoVerde[29]=this.vistaLudo.jButton43;
        this.caminoVerde[30]=this.vistaLudo.jButton44;this.caminoVerde[31]=this.vistaLudo.jButton45;
        this.caminoVerde[32]=this.vistaLudo.jButton46;this.caminoVerde[33]=this.vistaLudo.jButton47;
        this.caminoVerde[34]=this.vistaLudo.jButton48;this.caminoVerde[35]=this.vistaLudo.jButton49;
        this.caminoVerde[36]=this.vistaLudo.jButton50;this.caminoVerde[37]=this.vistaLudo.jButton51;
        this.caminoVerde[38]=this.vistaLudo.jButton0;this.caminoVerde[39]=this.vistaLudo.jButton1;
        this.caminoVerde[40]=this.vistaLudo.jButton2;this.caminoVerde[41]=this.vistaLudo.jButton3;
        this.caminoVerde[42]=this.vistaLudo.jButton4;this.caminoVerde[43]=this.vistaLudo.jButton5;
        this.caminoVerde[44]=this.vistaLudo.jButton6;this.caminoVerde[45]=this.vistaLudo.jButton7;
        this.caminoVerde[46]=this.vistaLudo.jButton8;this.caminoVerde[47]=this.vistaLudo.jButton9;
        this.caminoVerde[48]=this.vistaLudo.jButton10;this.caminoVerde[49]=this.vistaLudo.jButton11;
        this.caminoVerde[50]=this.vistaLudo.jButton12;this.caminoVerde[51]=this.vistaLudo.jButton65;
        this.caminoVerde[52]=this.vistaLudo.jButton66;this.caminoVerde[53]=this.vistaLudo.jButton67;
        this.caminoVerde[54]=this.vistaLudo.jButton68;this.caminoVerde[55]=this.vistaLudo.jButton69;
        this.caminoVerde[56]=this.vistaLudo.jButton70;
        //REBOTE VERDE
        this.caminoVerde[57]=this.vistaLudo.jButton69;this.caminoVerde[58]=this.vistaLudo.jButton68;
        this.caminoVerde[59]=this.vistaLudo.jButton67;this.caminoVerde[60]=this.vistaLudo.jButton66;
        this.caminoVerde[61]=this.vistaLudo.jButton65;
        
        //CAMINO OCRE      
        this.caminoOcre[0]=this.vistaLudo.jButton27;this.caminoOcre[1]=this.vistaLudo.jButton28;
        this.caminoOcre[2]=this.vistaLudo.jButton29;this.caminoOcre[3]=this.vistaLudo.jButton30;
        this.caminoOcre[4]=this.vistaLudo.jButton31;this.caminoOcre[5]=this.vistaLudo.jButton32;
        this.caminoOcre[6]=this.vistaLudo.jButton33;this.caminoOcre[7]=this.vistaLudo.jButton34;
        this.caminoOcre[8]=this.vistaLudo.jButton35;this.caminoOcre[9]=this.vistaLudo.jButton36;
        this.caminoOcre[10]=this.vistaLudo.jButton37;this.caminoOcre[11]=this.vistaLudo.jButton38;
        this.caminoOcre[12]=this.vistaLudo.jButton39;this.caminoOcre[13]=this.vistaLudo.jButton40;
        this.caminoOcre[14]=this.vistaLudo.jButton41;this.caminoOcre[15]=this.vistaLudo.jButton42;
        this.caminoOcre[16]=this.vistaLudo.jButton43;this.caminoOcre[17]=this.vistaLudo.jButton44;
        this.caminoOcre[18]=this.vistaLudo.jButton45;this.caminoOcre[19]=this.vistaLudo.jButton46;
        this.caminoOcre[20]=this.vistaLudo.jButton47;this.caminoOcre[21]=this.vistaLudo.jButton48;
        this.caminoOcre[22]=this.vistaLudo.jButton49;this.caminoOcre[23]=this.vistaLudo.jButton50;
        this.caminoOcre[24]=this.vistaLudo.jButton51;this.caminoOcre[25]=this.vistaLudo.jButton0;
        this.caminoOcre[26]=this.vistaLudo.jButton1;this.caminoOcre[27]=this.vistaLudo.jButton2;
        this.caminoOcre[28]=this.vistaLudo.jButton3;this.caminoOcre[29]=this.vistaLudo.jButton4;
        this.caminoOcre[30]=this.vistaLudo.jButton5;this.caminoOcre[31]=this.vistaLudo.jButton6;
        this.caminoOcre[32]=this.vistaLudo.jButton7;this.caminoOcre[33]=this.vistaLudo.jButton8;
        this.caminoOcre[34]=this.vistaLudo.jButton9;this.caminoOcre[35]=this.vistaLudo.jButton10;
        this.caminoOcre[36]=this.vistaLudo.jButton11;this.caminoOcre[37]=this.vistaLudo.jButton12;
        this.caminoOcre[38]=this.vistaLudo.jButton13;this.caminoOcre[39]=this.vistaLudo.jButton14;
        this.caminoOcre[40]=this.vistaLudo.jButton15;this.caminoOcre[41]=this.vistaLudo.jButton16;
        this.caminoOcre[42]=this.vistaLudo.jButton17;this.caminoOcre[43]=this.vistaLudo.jButton18;
        this.caminoOcre[44]=this.vistaLudo.jButton19;this.caminoOcre[45]=this.vistaLudo.jButton20;
        this.caminoOcre[46]=this.vistaLudo.jButton21;this.caminoOcre[47]=this.vistaLudo.jButton22;
        this.caminoOcre[48]=this.vistaLudo.jButton23;this.caminoOcre[49]=this.vistaLudo.jButton24;
        this.caminoOcre[50]=this.vistaLudo.jButton25;this.caminoOcre[51]=this.vistaLudo.jButton71;
        this.caminoOcre[52]=this.vistaLudo.jButton72;this.caminoOcre[53]=this.vistaLudo.jButton73;
        this.caminoOcre[54]=this.vistaLudo.jButton74;this.caminoOcre[55]=this.vistaLudo.jButton75;
        this.caminoOcre[56]=this.vistaLudo.jButton76;
        //REBOTE OCRE
        this.caminoOcre[57]=this.vistaLudo.jButton75;this.caminoOcre[58]=this.vistaLudo.jButton74;
        this.caminoOcre[59]=this.vistaLudo.jButton73;this.caminoOcre[60]=this.vistaLudo.jButton72;
        this.caminoOcre[61]=this.vistaLudo.jButton71;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //SETTERS Y GETTERS

    public Jugador getJugadorEnTurno() {
        return jugadorEnTurno;
    }
    public void setJugadorEnTurno(Jugador jugadorEnTurno) {
        this.jugadorEnTurno = jugadorEnTurno;
    }
    public boolean isDadoEnEspera() {
        return dadoEnEspera;
    }
    public void setDadoEnEspera(boolean dadoEnEspera) {
        this.dadoEnEspera = dadoEnEspera;
    }
    public void setVistaLudo(VistaPrincipal vistaLudo) {
        this.vistaLudo = vistaLudo;
    }    
    public VistaPrincipal getVistaLudo(){
        return vistaLudo;
    }
    public boolean isJugadoresYaOrdenados() {
        return jugadoresYaOrdenados;
    }
    public void setJugadoresYaOrdenados(boolean jugadoresYaOrdenados) {
        this.jugadoresYaOrdenados = jugadoresYaOrdenados;
    }
    public void setPartidaIniciada(boolean partidaIniciada) {
        this.partidaIniciada = partidaIniciada;
    }
    public void setJugadorRojo(Jugador JugadorRojo) {
        this.jugadorRojo = jugadorRojo;
    }
    public void setJugadorAzul(Jugador JugadorAzul) {
        this.jugadorAzul = jugadorAzul;
    }
    public void setJugadorVerde(Jugador JugadorVerde) {
        this.jugadorVerde = jugadorVerde;
    }
    public void setJugadorOcre(Jugador JugadorOcre) {
        this.jugadorOcre = jugadorOcre;
    }
    public Jugador getJugadorRojo() {
        return jugadorRojo;
    }
    public Jugador getJugadorAzul() {
        return jugadorAzul;
    }
    public Jugador getJugadorVerde() {
        return jugadorVerde;
    }
    public Jugador getJugadorOcre() {
        return jugadorOcre;
    }
    public boolean isPartidaIniciada() {
        return partidaIniciada;
    }
    public int getContadorDeJugadores() {
        return contadorDeJugadores;
    }
    public void setContadorDeJugadores(int contadorDeJugadores) {
        this.contadorDeJugadores = contadorDeJugadores;
    }
    public void setMovimientoEnEspera(boolean movimientoEnEspera) {
        this.movimientoEnEspera = movimientoEnEspera;
    }
    public boolean isMovimientoEnEspera() {
        return movimientoEnEspera;
    }
    public Registro getArchivoRegistro() {
        return archivoRegistro;
    }
    public PaletaColores getPaleta() {
        return paleta;
    }
    public Cola getCola() {
        return cola;
    }
    public JButton[] getCaminoRojo() {
        return caminoRojo;
    }
    public JButton[] getCaminoAzul() {
        return caminoAzul;
    }
    public JButton[] getCaminoVerde() {
        return caminoVerde;
    }
    public JButton[] getCaminoOcre() {
        return caminoOcre;
    }    
    private Color Color(int i, int i0, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
