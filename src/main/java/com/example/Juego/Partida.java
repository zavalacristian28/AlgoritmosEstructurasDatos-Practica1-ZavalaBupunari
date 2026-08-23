package com.example.Juego;
import com.example.Carta.Carta;
import com.example.Carta.Mazo;
import com.example.Vista.Vista;
import com.example.Juego.Jugador;
import com.example.Carta.CartaInglesa;
import java.util.*;

public class Partida {
    private Vista vista;
    private Jugador j1;
    private Jugador dealer;
    private Mazo mazo;

    public Partida(Vista vista,Mazo mazo){
        this.vista=vista;
        this.mazo=mazo;
    }

    public void crearJugadores(){
        String nombre;
        nombre= vista.registrarNombre();
        j1= new Jugador(nombre,false);
        dealer= new Jugador("Dealer",false);
    }

   public void asignarCartas(){
        mazo= new Mazo();
        for(int x=0;x<2;x++){
            j1.getMano().add(mazo.obtenerUnaCarta());
            dealer.getMano().add(mazo.obtenerUnaCarta());
        }
   }

   public void partidaJuego(){
        crearJugadores();
        asignarCartas();
   }

    public boolean comprobar21(ArrayList<Carta> manoJ){


    }

    public void comprobarGanador(ArrayList<Carta> manoJ, ArrayList<Carta> manoD){

    }
}

