package com.example.ayed_practica1;
import com.example.Carta.Carta;
import com.example.Juego.Partida;
import com.example.Juego.Jugador;
import com.example.Pilas.Pila;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import static javafx.application.Application.launch;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.awt.*;


public class JuegoGrafico {
    private Partida partida;
    private Scene escena;
    private Pane panelJuego;
    private int jugadorActual = 0;
    private Pane panelJugador;
    private Pane panelDealer;
    private Pane panelResultados;


    public JuegoGrafico(Partida partida, Scene escena) {
        this.partida = partida;
        this.escena = escena;
    }

    public void mostrarJuego() {
        panelJuego = new Pane();
        panelJuego.setStyle("-fx-background-color: #0B3D2E;");
        Label tituloJuego = Componentes.crearLabel40("Partida de blackjack", 280, 50);

        panelJuego.getChildren().add(tituloJuego);
        panelJugador = Componentes.crearPanel(0, 300, 1000, 300);

        panelJuego.getChildren().add(panelJugador);
        //Datos del dealer
        Label textoDealer = Componentes.crearLabel20(partida.getDealer().getNombre(), 370, 100);
        //textoDealer.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;" + "-fx-text-fill: white;");
        panelJuego.getChildren().add(textoDealer);
        panelDealer = Componentes.crearPanel(0, 120, 1000, 180);
        panelJuego.getChildren().add(panelDealer);
        double posicionX = 300;


        Pila<Carta> pilaDeCartas=new Pila<>();
        while(!partida.getDealer().getManoCartas().pilaVacia()){
            Carta c=partida.getDealer().getManoCartas().pop();
            crearCartaVisual(c,posicionX,20,panelDealer);
            pilaDeCartas.push(c);
            posicionX+=90;
        }
        while(!pilaDeCartas.pilaVacia()){
            Carta c=pilaDeCartas.pop();
            partida.getDealer().getManoCartas().push(c);
        }

/*
        for (Carta carta : partida.getDealer().getMano()) {
            crearCartaVisual(carta, posicionX, 20, panelDealer);
            posicionX += 90;
        }*/
        mostrarJugadorActual();
        escena.setRoot(panelJuego);
    }

    private void crearCartaVisual(Carta carta, double x, double y, Pane panel) {
        Rectangle cartaVisual = new Rectangle(80, 120);
        cartaVisual.setFill(Color.WHITE);//esta funcion pinta el rectangulo
        cartaVisual.setStroke(Color.BLACK);//esto genera un borde
        cartaVisual.setStrokeWidth(5);//esta funcion le da un grosor al borde
        cartaVisual.setLayoutX(x);
        cartaVisual.setLayoutY(y);
        panel.getChildren().add(cartaVisual);
        if (carta.isFaceup()) {
            Label valorCarta = new Label(carta.toString());
            valorCarta.setStyle("-fx-font-size: 24px;" + "-fx-font-weight: bold;");
            valorCarta.setLayoutX(x + 25);
            valorCarta.setLayoutY(y + 40);
            panel.getChildren().add(valorCarta);
        } else {
            cartaVisual.setFill(Color.DARKBLUE);
        }
    }//fin del metodo

    private void mostrarJugadorActual() {
        panelJugador.getChildren().clear();
        Jugador jugador = partida.getJugadores().get(jugadorActual);
        Label nombreJugador = Componentes.crearLabel20(jugador.getNombre(), 100, 20);
        partida.sumarManoJugadores(jugador.getManoCartas(),jugador);
        Label valorMano=Componentes.crearLabel20("Valor de la mano: " + jugador.getSumaMano(),300,20);
        panelJugador.getChildren().add(valorMano);
        panelJugador.getChildren().add(nombreJugador);
        double posicionJugadorX = 100;

        Pila<Carta> pilaCartasJ=new Pila<>();
        while(!jugador.getManoCartas().pilaVacia()){
            Carta c=jugador.getManoCartas().pop();
            crearCartaVisual(c,posicionJugadorX,60,panelJugador);
            pilaCartasJ.push(c);
            posicionJugadorX+=90;
        }
        while(!pilaCartasJ.pilaVacia()){
            jugador.getManoCartas().push(pilaCartasJ.pop());
        }
        /*
        for (Carta carta : jugador.getMano()) {
            crearCartaVisual(carta, posicionJugadorX, 60, panelJugador);
            posicionJugadorX += 90;
        }*/
        Button botonTomarCarta = Componentes.crearBoton("Tomar carta", 150, 210);
        Button botonPlantarse = Componentes.crearBoton("Plantarse", 370, 210);

        panelJugador.getChildren().add(botonTomarCarta);
        panelJugador.getChildren().add(botonPlantarse);

        botonTomarCarta.setOnAction(e -> {
            if (jugador.getSumaMano() < 21) {
                //partida.sumaManoJugadores(jugador.getManoCartas(),jugador);
                partida.sumarManoJugadores(jugador.getManoCartas(), jugador);
                Carta nuevaCarta = partida.getMazo().obtenerUnaCarta();
                nuevaCarta.makeFaceUp();
                jugador.getManoCartas().push(nuevaCarta);
                //jugador.getMano().add(nuevaCarta);
                partida.sumarManoJugadores(jugador.getManoCartas(), jugador);
                //partida.sumarValoresManoJugador(jugador.getMano(), jugador);
                valorMano.setText("Valor de la mano: " + jugador.getSumaMano());
                //double posicionXX = 100 + ((jugador.getMano().size() - 1) * 90);
                //double posicionXX = 100 + ((jugador.getManoCartas().getTope() - 1) * 90);
                double posicionXX = 100 + (jugador.getManoCartas().getTope() * 90);
                crearCartaVisual(nuevaCarta, posicionXX, 60, panelJugador);
            }else{
                botonTomarCarta.setDisable(true);
            }

        });

        botonPlantarse.setOnAction(e -> {
            //panelJugador.getChildren().clear();
            jugadorActual++;
            if (jugadorActual < partida.getJugadores().size()) {
                mostrarJugadorActual();
            } else {
                mostrarTurnoDealer();
            }
        });
    }

    private void mostrarTurnoDealer() {

        Pila<Carta> pilaCartasDealerAux=new Pila<>();

        while(!partida.getDealer().getManoCartas().pilaVacia()){
            Carta c=partida.getDealer().getManoCartas().pop();
            c.makeFaceUp();
            pilaCartasDealerAux.push(c);
        }
        while(!pilaCartasDealerAux.pilaVacia()){
            partida.getDealer().getManoCartas().push(pilaCartasDealerAux.pop());
        }
        /*
        for (Carta carta : partida.getDealer().getMano()) {
            carta.makeFaceUp();
        }*/
        int valorDealer = 0;
        partida.sumarManoJugadores(partida.getDealer().getManoCartas(),partida.getDealer());
        //partida.sumarValoresManoJugador(partida.getDealer().getMano(), partida.getDealer());
        System.out.println("Valor del dealer: " + partida.getDealer().getSumaMano());

        System.out.println(partida.getDealer().getMano());

        //System.out.println("Valor del dealer: " + valorDealer);
        partida.dealerMenor17(partida.getDealer().getMano(), partida.getDealer());
        panelDealer.getChildren().clear();
        double posicionX = 300;

        while(!partida.getDealer().getManoCartas().pilaVacia()){
            Carta c=partida.getDealer().getManoCartas().pop();
            crearCartaVisual(c,posicionX,20,panelDealer);
            pilaCartasDealerAux.push(c);
            posicionX+=90;
        }

        while(!pilaCartasDealerAux.pilaVacia()){
            partida.getDealer().getManoCartas().push(pilaCartasDealerAux.pop());
        }
        /*
        for (Carta carta : partida.getDealer().getMano()) {
            crearCartaVisual(carta, posicionX, 20, panelDealer);
            posicionX += 90;
        }*/
        System.out.println("Valor final del dealer: " + partida.getDealer().getSumaMano());
        System.out.println(partida.getDealer().getMano());
        mostrarResultados();
    }

    private void mostrarResultados() {
        panelResultados = new Pane();
        panelResultados.setStyle("-fx-background-color: #0B3D2E;");
        Label tituloResultados = Componentes.crearLabel40("Resultados", 310, 50);
        panelResultados.getChildren().add(tituloResultados);
        double posicionY = 120;

        for (Jugador jugador : partida.getJugadores()) {
            partida.sumarManoJugadores(jugador.getManoCartas(),jugador);
            partida.sumarManoJugadores(partida.getDealer().getManoCartas(),partida.getDealer());
            //partida.sumarValoresManoJugador(jugador.getMano(),jugador);
            //partida.sumarValoresManoJugador(partida.getDealer().getMano(),partida.getDealer());
            String cad = partida.obtenerCadenaEstado(jugador, partida.getDealer());
            Label nombreJugador = Componentes.crearLabel20(jugador.getNombre(), 150, posicionY);
            panelResultados.getChildren().add(nombreJugador);

            partida.sumarManoJugadores(jugador.getManoCartas(),jugador);
            //partida.sumarValoresManoJugador(jugador.getMano(), jugador);
            Label valor = Componentes.crearLabel20("Puntos: " + jugador.getSumaMano(), 350, posicionY);
            //Este label es el que indica si gano, perdio o empato
            Label estado = Componentes.crearLabel20(cad, 500, posicionY);
            panelResultados.getChildren().add(valor);
            panelResultados.getChildren().add(estado);
            posicionY += 50;
        }
        partida.sumarManoJugadores(partida.getDealer().getManoCartas(),partida.getDealer());
        //partida.sumarValoresManoJugador(partida.getDealer().getMano(), partida.getDealer());
        Label resultadoDealer = Componentes.crearLabel20("Dealer", 150, posicionY + 20);
        Label valorDealer=Componentes.crearLabel20("Puntos: "+partida.getDealer().getSumaMano(),350,posicionY+20);
        panelResultados.getChildren().add(resultadoDealer);
        panelResultados.getChildren().add(valorDealer);
        Button botonVolverAJugar=Componentes.crearBoton("Salir",310,450);
        panelResultados.getChildren().add(botonVolverAJugar);

        botonVolverAJugar.setOnAction(e -> {
            Platform.exit();
        });
        escena.setRoot(panelResultados);
    }

}
