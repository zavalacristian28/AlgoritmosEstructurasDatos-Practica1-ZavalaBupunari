package com.example.Juego;
import com.example.Carta.Carta;
import com.example.Carta.Mazo;
import com.example.Vista.Vista;
import java.util.ArrayList;

public class Partida {
    private Vista vista;
    private ArrayList<Jugador> jugadores;
    private Jugador dealer;
    private Mazo mazo;

    //Constructor usado para el juego en terminal
    public Partida(Vista vista, Mazo mazo) {
        this.vista = vista;
        this.mazo = mazo;
        jugadores = new ArrayList<Jugador>();
    }
    //Constructor usado para el  juego grafico
    public Partida( Mazo mazo) {
        this.mazo = mazo;
        jugadores = new ArrayList<Jugador>();
    }
    //Metodos getters
    public Mazo getMazo(){
        return mazo;
    }
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public Jugador getDealer() {
        return dealer;
    }

    //Este metodo fue para el juego grafico
    public void crearJugadores(ArrayList<String> nombres) {
        jugadores.clear();
        for (String nombre : nombres) {
            Jugador jugador = new Jugador(nombre, false,0);
            jugadores.add(jugador);
        }
        dealer = new Jugador("Dealer", false,0);
    }

    //Este metodo fue para el juego en terminal
    public void crearJugadores(){
        int numJugadores = vista.solicitarNumJugadores();
        for(int i= 0; i < numJugadores; i++){
            String nombre= vista.registrarNombre();
            Jugador jugador= new Jugador(nombre, false,0);
            jugadores.add(jugador);
        }
        dealer= new Jugador("Dealer", false,0);
    }

    public void asignarCartas() {
        mazo = new Mazo();
        //Primer ciclo recorre cada uno de los jugadores creados
        for (Jugador jugador : jugadores) {
        //El segundo recorre 2 veces para asignar dos cartas a cada jugador
            for (int x=0;x<2;x++) {
                Carta carta= mazo.obtenerUnaCarta();
                //makeFaceUp es para hacerlas visibles para los jugadores
                carta.makeFaceUp();
                jugador.getMano().add(carta);
            }
        }
        // Repartir 2 cartas al dealer
        for (int x=0;x< 2; x++) {
            Carta carta= mazo.obtenerUnaCarta();
            //Por reglas de juego, el dealer debe tener una sola carta visible hasta que
            //todos los jugadores se planten
            if(x==0){
                carta.makeFaceDown();
            }else{
                carta.makeFaceUp();
            }
            dealer.getMano().add(carta);
        }
    }//fin del metodo

    public void partidaJuego() {
        crearJugadores();
        asignarCartas();

        for (Jugador jugador : jugadores) {
            System.out.println("\nTurno de " + jugador.getNombre());
            sumarValoresManoJugador(jugador.getMano(),jugador);
            boolean jugadorPlantado= false;

            //Este ciclo while es el que se encargara se gestionar los movimientos del jugador actual
            //controladdo por la variable booleana que se actualizara dependiendo del caso
            while(!jugadorPlantado) {
                vista.mostrarCartas(jugador,1);
                // Si tiene 21 se planta automáticamente
                if(jugador.getSumaMano()== 21){
                    System.out.println("Tienes 21");
                    jugadorPlantado= true;
                    break;
                }

                // Si se paso de 21 termina su turno
                if (jugador.getSumaMano()> 21) {
                    System.out.println("Te pasaste de 21.");
                    jugadorPlantado= true;
                    break;
                }
                int elegirMov = vista.elejirMovimiento(jugador);
                if (elegirMov==1){
                    Carta nuevaCarta = mazo.obtenerUnaCarta();
                    nuevaCarta.makeFaceUp();
                    jugador.getMano().add(nuevaCarta);
                    sumarValoresManoJugador(jugador.getMano(),jugador);
                } else{
                    System.out.println(jugador.getNombre() + " se planta.");
                    //plantarcartas(jugador.getMano());
                    jugadorPlantado = true;
                }
            }
        }
        //a partir de aqui comienza la logica del dealer
        vista.mensajeTurnoDealer();
        //Este ciclo voltea las cartas del dealer
        for (Carta c : dealer.getMano()) {
            c.makeFaceUp();
        }

        //Aqui se muestran las cartas del dealer
        vista.mostrarCartas(dealer,2);
        sumarManoDealer(dealer.getMano(),dealer);

        System.out.println("Valor inicial del dealer: " + dealer.getSumaMano());
        dealerMenor17(dealer.getMano(), dealer);
        System.out.println("\nCartas finales del dealer:");
        vista.mostrarCartas(dealer,2);
        sumarManoDealer(dealer.getMano(),dealer);
        System.out.println("Valor final del dealer: "+ dealer.getSumaMano());

        for (Jugador jugador : jugadores) {
            comprobarGanador(jugador,dealer);
        }
    }

    public void sumarValoresManoJugador(ArrayList<Carta> manoJ,Jugador j) {
        int suma =0;
        int cantidadAses=0;
        for (Carta c : manoJ) {
            int valor = c.getValor();
            //Las cartas J, Q y K valen 10.
            if (valor == 11 || valor == 12 || valor == 13) {
                suma += 10;
            }
            // El As se cuenta inicialmente como 1.
            else if (valor == 14) {
                suma += c.getValorBajo();
                cantidadAses++;
            }
            else{
                suma += valor;
            }
        }
        //Aqui se comprueba cual valor es mejor para el valor del as, originalmente vale 1, y puede
        //valer 11 tambien, por lo que si a la suma se le suman los 10 restantes y el resultado es menor a 21,
        //el as se toma como 11 entonces
        for (int x=0;x<cantidadAses;x++) {
            if (suma + 10 <=21){
                suma +=10;
            }
        }
        j.setSumaMano(suma);
    }

    public void sumarManoDealer(ArrayList<Carta> manoD,Jugador dealer) {
        int suma =0;
        int cantidadAses =0;
        for (Carta c : manoD) {
            int valor = c.getValor();
            if (valor == 11 || valor == 12 || valor == 13) {
                suma += 10;
            }
            else if (valor == 14) {
                suma += c.getValorBajo();
                cantidadAses++;
            }
            else {
                suma += valor;
            }
        }
        for (int x=0;x<cantidadAses; x++) {
            if (suma + 10 <= 21) {
                suma += 10;
            }
        }
        dealer.setSumaMano(suma);
    }

    //Compara la mano del jugador con la del dealer
    public void comprobarGanador(Jugador j,Jugador d){
        int sumaJ = j.getSumaMano();
        int sumaD = d.getSumaMano();
        System.out.println("\nResultado de " + j.getNombre() + ":");
        String cad=obtenerCadenaEstado(j,d);
        System.out.println(cad);
    }

    public String obtenerCadenaEstado(Jugador j,Jugador d){
        String cad="";
        int sumaJugador=j.getSumaMano();
        int sumaDealer=d.getSumaMano();
        if (sumaJugador > 21 && sumaDealer > 21) {
            // 3 = empate
            cad="Empate";
        }
    else if (sumaJugador > 21) {
            // 2 = gana la casa
            cad= "Gana la casa";
        }
        else if (sumaDealer > 21) {
            // 1 = gana el jugador
            cad= "Gana el jugador";
        }
        else if (sumaJugador > sumaDealer) {
            // 1 = gana el jugador
            cad= "Gana el jugador";
        }
        else if (sumaJugador < sumaDealer) {
            // 2 = gana la casa
            cad= "Gana la casa";
        }
        else {
            // 3 = empate
            cad= "Empate";
        }
        return cad;
    }

    public void dealerMenor17(ArrayList<Carta> manoD, Jugador d) {
        while (d.getSumaMano() <17){
            Carta nuevaCarta= mazo.obtenerUnaCarta();
            nuevaCarta.makeFaceUp();
            d.getMano().add(nuevaCarta);
            sumarManoDealer(d.getMano(),d);
        }
    }

}
