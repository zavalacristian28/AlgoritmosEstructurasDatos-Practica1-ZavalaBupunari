package com.example.ayed_practica1;

import javafx.application.Application;
import com.example.Carta.Mazo;
import com.example.Carta.CartaInglesa;
public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
        Mazo mazo = new Mazo();
        for (CartaInglesa carta : mazo.getCartas()) {
            carta.makeFaceUp();
        }
        System.out.println(mazo);
    }

}
