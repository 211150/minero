package com.mina.minero.controller;

import com.mina.minero.models.Diamante;
import com.mina.minero.models.Esmeralda;

import com.mina.minero.models.Vector;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class HelloController implements Observer {
    @FXML
    private Button btnFinalizarOnMouseCliked;

    @FXML
    private Button btnIniciarOnMouseCliked;

    @FXML
    private ImageView imgDiamante;

    @FXML
    private ImageView imgRuby;

    @FXML
    private ImageView imgEsmeralda;

    @FXML
    private ImageView imgFondo;

    @FXML
    private ImageView imgMinero;
    private boolean movimiento = false;
    private Random random;
    private Diamante diamante;
    private Esmeralda esmeralda;



    @FXML
    void btnFinalizarOnMouseCliked(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void btnIniciarOnMouseCliked(MouseEvent event) {
        diamante = new Diamante();
        diamante.set_Position(new Vector(1,620,370));
        diamante.addObserver(this);
        new Thread(diamante).start();
        movimiento = true;

        esmeralda = new Esmeralda();
        esmeralda.set_Position(new Vector(1,620,370));
        esmeralda.addObserver(this);
        new Thread(esmeralda).start();
        movimiento = true;



        FadeTransition fadeTransition = new FadeTransition(Duration.millis(50), btnIniciarOnMouseCliked);
        fadeTransition.setByValue(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(e ->{
            btnIniciarOnMouseCliked.setManaged(false);
            btnIniciarOnMouseCliked.setVisible(false);
        });
    }

    @FXML
    void characterOnKeyPressed(KeyEvent event) {
        if(movimiento == true){
            if (event.getCode() == KeyCode.D){
                System.out.println("Movimiento de tecla D");
                if(imgMinero.getLayoutX()>620){
                    imgMinero.setLayoutX(imgMinero.getLayoutX());
                }else {
                    imgMinero.setLayoutX(imgMinero.getLayoutX()+15);
                }
            }
            if (event.getCode() == KeyCode.A){
                System.out.println("Movimiento de tecla A");
                if(imgMinero.getLayoutX()<0){
                    imgMinero.setLayoutX(imgMinero.getLayoutX());
                }else {
                    imgMinero.setLayoutX(imgMinero.getLayoutX()-15);
                }
            }
            if(event.getCode() == KeyCode.W){
                System.out.println("Movimiento de tecla W");
                if(imgMinero.getLayoutY()<0){
                    imgMinero.setLayoutY(imgMinero.getLayoutY());
                }else {
                    imgMinero.setLayoutY(imgMinero.getLayoutY()-15);
                }
            }
            if (event.getCode() == KeyCode.S){
                System.out.println("Movimiento de tecla S");
                if(imgMinero.getLayoutY()>370){
                    imgMinero.setLayoutY(imgMinero.getLayoutY());
                }else {
                    imgMinero.setLayoutY(imgMinero.getLayoutY()+15);
                }
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        random = new Random(System.currentTimeMillis());
        Vector vector = (Vector) arg;
        if(imgMinero.getBoundsInParent().intersects(imgDiamante.getBoundsInParent())){
            System.out.println("El minero encontro un diamante");
            this.diamante.setEstado(false);
            this.imgDiamante.setVisible(false);
            if(!diamante.isEstado()){
                int vectX = random.nextInt(600);
                int vectY = random.nextInt(300);
                this.diamante.setEstado(true);
                diamante.set_Position(new Vector(1, vectX,vectY));
                this.imgDiamante.setVisible(true);
                imgDiamante.setLayoutX(vectX);
                imgDiamante.setLayoutY(vectY);
            }
        }

        if(imgMinero.getBoundsInParent().intersects(imgEsmeralda.getBoundsInParent())){
            System.out.println("El minero encontro una Esmeralda");
            this.esmeralda.setEstado(false);
            this.imgEsmeralda.setVisible(false);
            if(!esmeralda.isEstado()){
                int vectX = random.nextInt(600);
                int vectY = random.nextInt(300);
                this.esmeralda.setEstado(true);
                esmeralda.set_Position(new Vector(1, vectX,vectY));
                this.imgEsmeralda.setVisible(true);
                imgEsmeralda.setLayoutX(vectX);
                imgEsmeralda.setLayoutY(vectY);
            }
        }

    }
}