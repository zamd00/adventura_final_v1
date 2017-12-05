/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *
 * @author YAMRA
 */
public class MenuPole extends MenuBar{
    
    private Main main;
    
    public MenuPole(Main main){
        this.main = main;
        init();
    }
    
    private void init(){
        Menu menuSoubor = new Menu("Adventura");
        
        MenuItem itemNovaHra = new MenuItem("Nová hra");
//        MenuItem itemNovaHra = new MenuItem(new Image("Nová hra", new ImageView(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        itemNovaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        
        MenuItem itemKonec = new MenuItem("Konec");
        itemKonec.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));
        
        Menu menuHelp = new Menu("Help");
        MenuItem itemNapoveda = new MenuItem("Nápověda");
        MenuItem itemOProgramu = new MenuItem("O programu");
        
        menuSoubor.getItems().addAll(itemNovaHra, itemKonec);
        menuHelp.getItems().addAll(itemOProgramu, itemNapoveda);
        
        this.getMenus().addAll(menuSoubor, menuHelp);
        
        itemOProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("O Adventure");
                alert.setHeaderText("Toto je má adventura - ZS 2017/2018");
                alert.setContentText("Grafická verze adventury");
                alert.initOwner(main.getPrimaryStage());
                alert.showAndWait();
            }
        });
        
        itemNapoveda.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Nápověda");
                WebView webview = new WebView();
                webview.getEngine().load(Main.class.getResource("/zdroje/napoveda.htm").toExternalForm());
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            }
        });
        
        itemKonec.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        itemNovaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                main.novaHra();
                
            }
        });
        
    }
    
}
