package com.example.ayed_practica1;

import com.example.Juego.Jugador;
import com.example.Carta.Mazo;
import com.example.Juego.Partida;
import javafx.application.Platform;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.ArrayList;


public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        Pane panel = new Pane();
        //Esta linea cambia el color del fondo, #0B3D2E es el color verde oscuro, simula la mesa
        panel.setStyle("-fx-background-color: #0B3D2E;");

        Label titulo =Componentes.crearLabel40("BLACKJACK",300,90);
        Scene escena = new Scene(panel, 800, 600);
        panel.getChildren().add(titulo);

        //subtitulo 21
        Label subtitulo=Componentes.crearLabel20("21",390,140);
        panel.getChildren().add(subtitulo);

        //Boton de nuevo juego
        Button botonNuevoJuego= Componentes.crearBoton("NUEVO JUEGO",300,180);
        panel.getChildren().add(botonNuevoJuego);

        //Boton para salir
        Button botonSalir=Componentes.crearBoton("Salir",300,280);
        panel.getChildren().add(botonSalir);
//========================================================
        botonNuevoJuego.setOnAction(e -> {
            Pane panelConfiguracion = new Pane();
            panelConfiguracion.setStyle("-fx-background-color: #0B3D2E;");
            Label tituloConfiguracion=Componentes.crearLabel40("NUEVA PARTIDA",280,80);
            panelConfiguracion.getChildren().add(tituloConfiguracion);
            Spinner<Integer> spinnerJugadores = new Spinner<>();
            //Este elemento permite seleccionar el rango de numeros permitidos
            SpinnerValueFactory<Integer> valoresJugadores =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
            spinnerJugadores.setValueFactory(valoresJugadores);
            //coordenadas del spinner
            spinnerJugadores.setLayoutX(350);
            spinnerJugadores.setLayoutY(210);
            panelConfiguracion.getChildren().add(spinnerJugadores);

            Label pregunta=Componentes.crearLabel20("Ingresa el numero de jugadores",280,150);
            panelConfiguracion.getChildren().add(pregunta);

            Button botonContinuar=Componentes.crearBoton("Continuar",300,280);
            panelConfiguracion.getChildren().add(botonContinuar);
            //========================================================
            botonContinuar.setOnAction(a -> {

                int numeroJugadores = spinnerJugadores.getValue();

                Pane panelNombres = new Pane();
                panelNombres.setStyle("-fx-background-color: #0B3D2E;");
                panelNombres.getChildren().add(tituloConfiguracion);
                ArrayList<TextField> campoNombres=new ArrayList<>();
                for(int x=0;x<numeroJugadores;x++) {
                    TextField campo=Componentes.crearTextField(x);
                    panelNombres.getChildren().add(campo);
                    campoNombres.add(campo);
                }
                Button botonContinuarPantallaNombre=Componentes.crearBoton("Continuar",300,480);
                botonContinuarPantallaNombre.setOnAction(i -> {

                    ArrayList<String> nombres = new ArrayList<>();
                    int c=1;
                    for (TextField campo : campoNombres) {
                        String nombre = campo.getText();
                        if(nombre.isEmpty()){
                            nombre="Jugador "+c;
                        }
                        nombres.add(nombre);
                        c++;
                    }
                    Mazo mazo = new Mazo();
                    Partida partida = new Partida(null, mazo);
                    partida.crearJugadores(nombres);
                    partida.asignarCartas();
                    JuegoGrafico juegoGrafico = new JuegoGrafico(partida,escena);
                    juegoGrafico.mostrarJuego();

                });

                panelNombres.getChildren().add(botonContinuarPantallaNombre);
                escena.setRoot(panelNombres);

            });
            escena.setRoot(panelConfiguracion);
        });

        botonSalir.setOnAction(e -> {
            Platform.exit();
        });

        stage.setTitle("Blackjack");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
