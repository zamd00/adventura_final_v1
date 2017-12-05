/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.IHra;
import logika.Predmet;
import main.Main;
import utils.Observer;

/**
 *
 * @author YAMRA
 */
public class PanelLokace implements Observer{
   private IHra hra; 
    ListView<Object> list;
   ObservableList<Object> data;
    private TextArea centralText;

    public PanelLokace(IHra hra,TextArea centralText){
    this.hra = hra;
    hra.getHerniPlan().registerObserver(this);
    this.centralText    =   centralText;
       init();
    }

     private void init() {
     list = new ListView<>();
     data = FXCollections.observableArrayList();
     list.setItems(data);
     list.setPrefWidth(200);
     
     list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                    int index = list.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Predmet> seznam;
                    seznam = hra.getHerniPlan().getAktualniLokace().getObsahLokace();
                    
                    String nazev = "";
                    int pomocna = 0;
                    for (String x : seznam.keySet()) 
                    {
                        System.out.println(x);
                       if(pomocna == index)
                       {
                           nazev = x;
                       }
                       pomocna++;
                    }

                    if(!nazev.equals(""))
                    {
                    String vstupniPrikaz = "vezmi "+nazev;
                    String odpovedHry = hra.getHerniPlan().getHra().zpracujPrikaz("vezmi "+nazev);

                
                    centralText.appendText("\n" + vstupniPrikaz + "\n");
                    centralText.appendText("\n" + odpovedHry + "\n");
               
                    hra.getHerniPlan().notifyAllObservers();
                    }
            }
        });   
    
 }
    
     public ListView<Object> getList() {
        return list;
    }
     
     /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */

     
   @Override
    public void update() {
                
        Map<String, Predmet> seznam;
        seznam = hra.getHerniPlan().getAktualniLokace().getObsahLokace();
        data.clear();
        for (String x : seznam.keySet()) {
            Predmet pomocna = seznam.get(x);
            ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/"+pomocna.getImage()), 100, 100, false, false));
            data.add(obrazek);
        }
       
        
    }
    
    public void nastaveniHernihoPlanu (IHra hra){
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
        
    }

}
