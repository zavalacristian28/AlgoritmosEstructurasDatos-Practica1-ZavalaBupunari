package com.example.Vista;

import com.example.Carta.Carta;
import com.example.Juego.Jugador;
import com.example.Pilas.Pila;

import java.util.*;
public class Vista {

    private  Scanner sc=new Scanner(System.in);

    //metodo que permite elegir que se va a hacer
    public int menuInicial(){
        int op;
        System.out.println("JUEGO DEL 21\n");
        System.out.println("Elije de que manera quieres jugar:\n ");
        System.out.println(" -1.- En terminal\n");
        System.out.println(" -2. Salir del programa\n");
        op=sc.nextInt();
        while(op<1 || op>2){
            System.out.println("Elije una opcion valida:\n ");
            op=sc.nextInt();
        }
        return op;
    }

    //metodo que permite elegir el movimiento
    public int elejirMovimiento(Jugador j){
        int op;
        System.out.println(j.getNombre() + " Elije que movimiento quieres hacer:\n");
        System.out.println(" -1. Tomar otra carta\n");
        System.out.println(" -2. Plantar cartas\n");
        op=sc.nextInt();
        while(op<1 || op>2){
            System.out.println("Elije una opcion valida:\n ");
            op=sc.nextInt();
        }
        return op;
    }

    public String registrarNombre(){
        String nombre;
        sc.nextLine();
        System.out.println("Ingresa tu nombre:\n ");
        nombre=sc.nextLine();
        return nombre;
    }

    public void mensajeDespedida(){
        System.out.println("Saliendo del programa\n");
    }

    public void mostrarCartas( Jugador jd, int n) {
        if(n == 1){
            System.out.println(jd.getNombre() + " estas son tus cartas: ");
            for (Carta c : jd.getMano()) {
                System.out.println(c);
            }
            System.out.println("\nValor de tu mano: " + jd.getSumaMano());
        }else {
            System.out.println("Cartas del dealer:");
            for (Carta c : jd.getMano()) {
                System.out.println(c);
            }
        }
    }//fin del metodo

    //Metodo actualizado para uso con pilas
    public void mostrarCartas(Pila<Carta> pilaC, Jugador jd, int n) {
        Pila<Carta> pila=new Pila<>();
        if(n == 1){
            System.out.println(jd.getNombre() + " estas son tus cartas: ");
            while(!jd.getManoCartas().pilaVacia()){
                Carta c=jd.getManoCartas().pop();
                System.out.println(c);
                pila.push(c);
            }
            System.out.println("\nValor de tu mano: " + jd.getSumaMano());
        }else {
            System.out.println("Cartas del dealer:");
            while(!jd.getManoCartas().pilaVacia()){
                Carta c=jd.getManoCartas().pop();
                System.out.println(c);
                pila.push(c);
            }
        }
        while(!pila.pilaVacia()){
            jd.getManoCartas().push(pila.pop());
        }
    }//fin del metodo

    public int solicitarNumJugadores(){
        System.out.println("Ingresa cuantos jugadores quieres que juegue (1-4 jugadores): \n");
        int op=sc.nextInt();
        while(op<1 || op>4){
            System.out.println("Ingresa un valor valido dentro de 1-4\n");
            op=sc.nextInt();
        }
        return op;
    }

    public void mensajeTurnoDealer(){
        System.out.println("\n======================");
        System.out.println("Turno del Dealer");
        System.out.println("======================");
    }

}
