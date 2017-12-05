/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.PanelBatoh;
import UI.Mapa;
import UI.MenuPole;
import UI.PanelLokace;
import UI.PanelVychodu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;

/**
 *
 * @author YAMRA
 */
public class Main extends Application {

    private Mapa mapa;
    private MenuPole menu;
    private IHra hra;
    private TextArea centerText;
    private Stage primaryStage;
    private PanelBatoh panelBatoh;
    private PanelVychodu panelVychodu;
    private PanelLokace panelLokace;
    
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(this);
        
        
        Label zadejPrikazLabel = new Label("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        TextField zadejPrikazTextField = new TextField("Sem zadej prikaz");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String zadanyPrikaz = zadejPrikazTextField.getText();
                String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
                centerText.appendText("\n" + zadanyPrikaz + "\n");
                centerText.appendText("\n" + odpoved + "\n");
                zadejPrikazTextField.setText("");
                if(hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                }
                
            }
        });
        
        centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false);
        
        Label lBatoh = new Label("Batoh");
        lBatoh.setFont(Font.font("Arial", FontWeight.BOLD, 16));          
        
        Label lVeci = new Label("Věci v místnosti");
        lVeci.setFont(Font.font("Arial", FontWeight.BOLD, 16));   
        
        Label lVychod = new Label("Východy");
        lVychod.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        
        BorderPane borderPane = new BorderPane();
        BorderPane pravyPanel = new BorderPane();
        
        
        panelBatoh = new PanelBatoh(hra,centerText);
        panelVychodu = new PanelVychodu(hra.getHerniPlan(),centerText,zadejPrikazTextField);   
        panelLokace = new PanelLokace(hra,centerText);
        
        FlowPane l1 = new FlowPane();
        FlowPane l2 = new FlowPane();
        FlowPane l3 = new FlowPane();
        
        l1.setPrefWidth(100);
        l2.setPrefWidth(100);
        l3.setPrefWidth(100);
        
        l1.setAlignment(Pos.CENTER);
        l2.setAlignment(Pos.CENTER);
        l3.setAlignment(Pos.CENTER);
        
        l1.getChildren().addAll(lBatoh,panelBatoh.getList());
        l2.getChildren().addAll(lVeci,panelLokace.getList());
        l3.getChildren().addAll(lVychod,panelVychodu.getList());

       
        pravyPanel.setBottom(l1);
        pravyPanel.setCenter(l3);
        pravyPanel.setRight(l2);
        
        borderPane.setRight(pravyPanel);

        
        borderPane.setCenter(centerText);
        
        //panel prikaz
        borderPane.setBottom(dolniPanel);
        //obrazek s mapou
        borderPane.setLeft(mapa);
        //menu adventury
        borderPane.setTop(menu);
        
        hra.getHerniPlan().notifyAllObservers();
        
        Scene scene = new Scene(borderPane, 1550, 900);

        primaryStage.setTitle("Moje Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) { 
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }

    public void novaHra() {
        start(primaryStage);
    }
    

    /**
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
}
