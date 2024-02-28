package com.example.practicacincojavafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.InterruptedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ListView<String> lvFiguras = new ListView<>();

    @FXML
    private MenuButton SelecionColor;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField tfAlto;

    @FXML
    private TextField tfAncho;

    @FXML
    private TextField tfLado;

    @FXML
    private TextField tfPosX;

    @FXML
    private TextField tfPosY;

    @FXML
    private TextField tfRadio;
    @FXML
    private Button btnPintar;
    @FXML
    private Button btnMas;
    private ObservableList<String> listaFiguras;
    int figura=0;
    Color color=Color.BLACK;
    GraphicsContext gc;
    boolean sumarFig=false;

    @FXML
    void selectColor(ActionEvent event) {




    }

    @FXML
    void SumarPoligonos(ActionEvent event) {
        sumarFig=!sumarFig;
        if(sumarFig) btnMas.setStyle("-fx-background-color: #82E0AA;");
        else  btnMas.setStyle("-fx-background-color: #D6DBDF;");

    }

    @FXML
    void Pintar(ActionEvent event) {
        if(!sumarFig){
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            PintarBorde();
        }

        gc.setStroke(color);
        if(figura==1){

            gc.strokeRect(Double.parseDouble(tfPosX.getText()),Double.parseDouble(tfPosY.getText()),Double.parseDouble(tfAncho.getText()),Double.parseDouble(tfAlto.getText()));
        }
        if(figura==2){
            //calculo para realizar un triangulo Isosceles
            //1 vertice las posiciones X e Y
            //2 vertice X+lado e Y-lado
            //3 vertice X+lado e Y
            double[] xPoints = {Double.parseDouble(tfPosX.getText()), Double.parseDouble(tfPosX.getText())+Double.parseDouble(tfLado.getText()), Double.parseDouble(tfPosX.getText())+Double.parseDouble(tfLado.getText())*2};
            double[] yPoints = {Double.parseDouble(tfPosY.getText()),  Double.parseDouble(tfPosY.getText())-Double.parseDouble(tfLado.getText()),  Double.parseDouble(tfPosY.getText())};
            gc.strokePolygon(xPoints, yPoints, 3);


        }
        if(figura==3){
            gc.strokeOval(Double.parseDouble(tfPosX.getText()),Double.parseDouble(tfPosY.getText()),Double.parseDouble(tfRadio.getText()),Double.parseDouble(tfRadio.getText()));



        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tfAncho.setDisable(true);
        tfAlto.setDisable(true);
        tfRadio.setDisable(true);
        tfLado.setDisable(true);
        listaFiguras = FXCollections.observableArrayList("Cuadrado", "Triangulo", "Circulo");
        lvFiguras.setItems(listaFiguras);
        lvFiguras.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String figAntigua, String figNueva) {
                System.out.println(lvFiguras.getSelectionModel().getSelectedIndex());
                if(figNueva.equals("Cuadrado")){
                    tfAncho.setDisable(false);
                    tfAlto.setDisable(false);
                    tfRadio.setDisable(true);
                    tfLado.setDisable(true);
                    figura=1;
                }
                if(figNueva.equals("Triangulo")){
                    tfAncho.setDisable(true);
                    tfAlto.setDisable(true);
                    tfRadio.setDisable(true);
                    tfLado.setDisable(false);
                    figura=2;
                }
                if(figNueva.equals("Circulo")){
                    tfAncho.setDisable(true);
                    tfAlto.setDisable(true);
                    tfRadio.setDisable(false);
                    tfLado.setDisable(true);
                    figura=3;
                }
            }
        });


        // Crear elementos de menú
        MenuItem menuItem1 = new MenuItem("Rojo");
        MenuItem menuItem2 = new MenuItem("Verde");
        MenuItem menuItem3 = new MenuItem("Azul");
        MenuItem menuItem4 = new MenuItem("Amarillo");
        SelecionColor.getItems().addAll(menuItem1,menuItem2,menuItem3,menuItem4);
        menuItem1.setOnAction(e->{color=Color.RED; });
        menuItem2.setOnAction(e->{color=Color.GREEN; });
        menuItem3.setOnAction(e->{color=Color.BLUE; });
        menuItem4.setOnAction(e->{color=Color.YELLOW; });



        gc=canvas.getGraphicsContext2D();
        PintarBorde();




    }

    private void PintarBorde() {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5.0);
        gc.strokeRect(0,0,canvas.getWidth(),canvas.getHeight());
    }

}



