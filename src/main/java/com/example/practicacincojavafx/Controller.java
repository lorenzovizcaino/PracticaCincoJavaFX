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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.InterruptedIOException;
import java.net.URL;
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
    private ObservableList<String> listaFiguras;

    @FXML
    void selectColor(ActionEvent event) {

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
                if(figNueva.equals("Cuadrado")){
                    tfAncho.setDisable(false);
                    tfAlto.setDisable(false);
                    tfRadio.setDisable(true);
                    tfLado.setDisable(true);
                }
                if(figNueva.equals("Triangulo")){
                    tfAncho.setDisable(true);
                    tfAlto.setDisable(true);
                    tfRadio.setDisable(true);
                    tfLado.setDisable(false);
                }
                if(figNueva.equals("Circulo")){
                    tfAncho.setDisable(true);
                    tfAlto.setDisable(true);
                    tfRadio.setDisable(false);
                    tfLado.setDisable(true);
                }
            }
        });
    }

}



