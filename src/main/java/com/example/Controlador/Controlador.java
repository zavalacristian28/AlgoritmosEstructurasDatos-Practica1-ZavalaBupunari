package com.example.Controlador;
import  com.example.Vista.Vista;

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
            case 2: juegoGrafico();
            break;
            case 3: vista.mensajeDespedida();
            break;
        }
    }

    public void juegoEnTerminal(){

    }

    public void juegoGrafico(){

    }
}
