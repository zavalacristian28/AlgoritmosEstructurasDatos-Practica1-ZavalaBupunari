package com.example.Juego;
import com.example.Carta.Carta;
import com.example.Pilas.Pila;

import java.util.*;

public class Jugador {
    private String nombre;
    private boolean estadoJuego;
    private ArrayList<Carta> mano;
    private Pila<Carta> manoCartas;
    private int sumaMano; //este aributo guarda el valor total actualizable de la mano
    //constructor vacio
    public Jugador(){
        nombre=" ";
        estadoJuego=false;
        mano = new ArrayList<Carta>();
        sumaMano=0;
    }

    //Cambio para la practica 2
    //Constructor actualizado con la inicializacion de la pila de cartas
    public Jugador(String nombre, boolean estadoJuego,int sumaMano){
        this.nombre=nombre;
        this.estadoJuego=estadoJuego;
        mano= new ArrayList<Carta>();
        manoCartas=new Pila<>();
        this.sumaMano=sumaMano;
    }

    //metodos getters
    public String getNombre(){
        return  nombre;
    }
    public boolean isEstadoJuego(){
        return estadoJuego;
    }
    public ArrayList<Carta> getMano(){
        return mano;
    }
    public int getSumaMano(){return sumaMano;}

    //se añade el metodo get para la pila de cartas
    public Pila<Carta> getManoCartas(){
        return manoCartas;
    }

    //metodos setters
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setEstadoJuego(boolean estadoJuego){
        this.estadoJuego=estadoJuego;
    }
    public void setSumaMano(int sumaMano){this.sumaMano=sumaMano;}

}
