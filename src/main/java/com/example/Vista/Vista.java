package com.example.Vista;

import java.util.*;
public class Vista {

    private  Scanner sc=new Scanner(System.in);

    public int menuInicial(){
        int op;
        System.out.println("JUEGO DEL 21\n");
        System.out.println("Elije de que manera quieres jugar:\n ");
        System.out.println(" -1.- En terminal\n");
        System.out.println(" -2. Con interfaz grafica\n");
        System.out.println(" -3. Salir del programa\n");
        op=sc.nextInt();
        while(op<1 || op>3){
            System.out.println("Elije una opcion valida:\n ");
            op=sc.nextInt();
        }

        return op;
    }

    public int elejirMovimiento(){
        int op;

        System.out.println("Elije que movimiento quieres hacer:\n ");
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
        System.out.println("Ingresa tu nombre:\n ");
        nombre=sc.nextLine();
        return nombre;
    }

    public void mensajeDespedida(){
        System.out.println("Saliendo del programa\n");
    }

    public int elegirCartaAsComo1o11(){
        int op;
        System.out.println("Tienes una carta as, elije si quieres que tenga valor de 1 o de 11:\n ");
        System.out.println(" -1. Teclea 1 para que valga 1:\n ");
        System.out.println(" -2. Teclea 11 para que valga 11:\n ");
        op= sc.nextInt();
        while(op!=1 && op!=11){
            System.out.println("Elije el numero 1 o el 11:\n ");
            op=sc.nextInt();
        }
        return op;
    }

}
