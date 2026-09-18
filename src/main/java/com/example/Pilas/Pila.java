package com.example.Pilas;

public class Pila <T> {
    private T [] pila;
    private int tope=-1;
    private int capacidad;

    public Pila(){
        pila=(T[])new Object [10];
    }
    public Pila(int capacidad){
        this.capacidad = capacidad;
        pila = (T[]) new Object[capacidad];
    }
    public int getTope(){
        return tope;
    }
    public boolean pilaLlena( ){
        return tope==pila.length -1;
    }
    public boolean pilaVacia(){
        return tope==-1;
    }
    public void push(T dato){
        if(pilaLlena()){
            System.out.println("Desbordamiento");
        }else{
            tope++;
            pila[tope]=dato;
        }
    }

    public T pop(){
        if(pilaVacia()){
            System.out.println("Sub desbordamiento");
            return null;
        }else{
            T dato=pila[tope];
            tope--;
            return dato;
        }
    }

    public static String invierteCadena(String cadena) {
        if (cadena == null) {
            return null;
        }
        Pila<Character> p = new Pila<>(cadena.length());
        String nuevaCadena = "";
        for (int i = 0; i < cadena.length(); i++) {
            p.push(cadena.charAt(i));
        }
        while (!p.pilaVacia()) {
            nuevaCadena += p.pop();
        }
        return nuevaCadena;
    }

    public int longitudPila(Pila<Integer> pila){
        int cont=0;
        Pila<Integer> pilaAux=new Pila<>();
        while(!pila.pilaVacia()){
            pilaAux.push(pila.pop());
            cont++;
        }
        for(int x=0;x<cont;x++){
            pila.push(pilaAux.pop());
        }

        return cont;
    }
}
