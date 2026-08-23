package com.example.Juego;
import com.example.Carta.Carta;

import java.util.*;

public class Jugador {
    private String nombre;
    private boolean estadoJuego;
    private ArrayList<Carta> mano;

    public Jugador(){
        nombre=" ";
        estadoJuego=false;
    }

    public Jugador(String nombre, boolean estadoJuego){
        this.nombre=nombre;
        this.estadoJuego=estadoJuego;
        mano= new ArrayList<Carta>();
    }

    public String getNombre(){
        return  nombre;
    }
    public boolean isEstadoJuego(){
        return estadoJuego;
    }
    public ArrayList<Carta> getMano(){
        return mano;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setEstadoJuego(boolean estadoJuego){
        this.estadoJuego=estadoJuego;
    }
}
