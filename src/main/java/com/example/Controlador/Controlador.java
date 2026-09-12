package com.example.Controlador;
import com.example.Carta.Mazo;
import com.example.Juego.Partida;
import  com.example.Vista.Vista;

import static javafx.application.Application.launch;

public class Controlador {
    private  Vista vista;

    public  Controlador(Vista vista){
        this.vista=vista;
    }


    public void obtenerModoJuego(){
        int op;
        op=vista.menuInicial();
        switch (op){
            case 1: juegoEnTerminal();
            break;
            case 2: vista.mensajeDespedida();
            break;
        }
    }

    public void juegoEnTerminal(){
        Mazo mazo = new Mazo();
        Partida partida = new Partida(vista, mazo);
        partida.partidaJuego();

    }

}
