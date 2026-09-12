package com.example.ayed_practica1;

import com.example.Controlador.Controlador;
import com.example.Vista.Vista;
import javafx.application.Application;
import com.example.Carta.Mazo;
import com.example.Carta.CartaInglesa;
public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);
        controlador.obtenerModoJuego();

    }
}
