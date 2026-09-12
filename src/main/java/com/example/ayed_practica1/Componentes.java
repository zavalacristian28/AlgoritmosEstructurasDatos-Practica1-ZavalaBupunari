package com.example.ayed_practica1;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Componentes {

    public static Button crearBoton(String texto, double x, double y) {
        Button boton = new Button(texto);
        boton.setPrefWidth(200);
        boton.setPrefHeight(50);
        boton.setStyle("-fx-font-size: 16px;");
        boton.setLayoutX(x);
        boton.setLayoutY(y);
        boton.setStyle("-fx-font-size: 18px;"+"-fx-text-fill: white;"+"-fx-background-color: #145A42;");
        boton.setOnMouseEntered(e -> {
            boton.setStyle("-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-background-color: #D4AF37;");
        });
        //Esta es para que regrese a su base cuando ya no esta situado sobre el
        boton.setOnMouseExited(e -> {
            boton.setStyle("-fx-font-size: 18px;" + "-fx-text-fill: white;" + "-fx-background-color: #145A42;");
        });
        return boton;
    }//fin del metodo

    public static Label crearLabel40(String texto,double x,double y){
        Label label=new Label(texto);
        label.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;"+"-fx-text-fill: white;");
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    public static Label crearLabel20(String texto,double x,double y){
        Label label=new Label(texto);
        label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;"+"-fx-text-fill: white;");
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    public static Pane crearPanel(int x, int y, double width, double height){
        Pane pane=new Pane();
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        return pane;
    }

    public static TextField crearTextField(int x){
        TextField campo=new TextField();
        campo.setStyle("-fx-font-size: 16px;" + "-fx-prompt-text-fill: #777777;");
        campo.setPromptText("Nombre del jugador: "+(x+1));
        campo.setLayoutX(300);
        campo.setLayoutY(180 + (x * 60));
        campo.setPrefWidth(200);
        return campo;
    }
}
