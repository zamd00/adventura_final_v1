/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logika.HerniPlan;
import utils.Observer;
/**
 *
 * @author YAMRA
 */
public class PanelVychodu extends VBox implements Observer
{
     private HerniPlan plan;
    ListView<String> list;
    ObservableList<String> data;
    private TextArea centralText;
    private TextField zadejPrikazTextArea;

    public PanelVychodu(HerniPlan plan,  TextArea text,TextField field) {
       this.plan = plan;
       
       centralText = text;
        zadejPrikazTextArea = field;
       plan.registerObserver(this);
        init();
    }

    private void init() {
        list = new ListView<>();
        data = FXCollections.observableArrayList();
        list.setItems(data);
        list.setPrefWidth(100);
        
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                    if(!list.getSelectionModel().getSelectedItem().toString().equals("vychody:"))
                    {
                        String vstupniPrikaz = "jdi "+list.getSelectionModel().getSelectedItem().toString();
                        String odpovedHry = plan.getHra().zpracujPrikaz("jdi "+list.getSelectionModel().getSelectedItem().toString());

                        centralText.appendText("\n" + vstupniPrikaz + "\n");
                        centralText.appendText("\n" + odpovedHry + "\n");

                        if (plan.getHra().konecHry()) 
                        {
                        zadejPrikazTextArea.setEditable(false);
                        centralText.appendText(plan.getHra().vratEpilog());
                        }

                        plan.notifyAllObservers();
                    }
            }
        });

        String vychody = plan.getAktualniLokace().popisVychodu();

        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }
    
     public ListView<String> getList() {
        return list;
    }
     
     /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void nastaveniHernihoPlanu (HerniPlan plan){
        plan.deleteObserver(this);
        this.plan = plan;
        plan.registerObserver(this);
        this.update();
        
    }

     @Override
    public void update() {;
        data.clear();
        String vychody = plan.getAktualniLokace().popisVychodu();
        String[] oddeleneVychody = vychody.split(" ");
        for (int i = 1; i < oddeleneVychody.length; i++) {
            data.add(oddeleneVychody[i]);
        }
    }

}

