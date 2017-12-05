/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 * @author YAMRA
 */
public class Mapa extends AnchorPane implements Observer{

    private IHra hra;
    private Circle tecka;
    private ImageView hero;
    
    public Mapa(IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    private void init(){
        ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.png"),700,700,false,false));
        hero = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/hero.png"),50,50,false,false));
        //tecka = new Circle(10, Paint.valueOf("red"));
        this.getChildren().addAll(obrazek, hero);
        update();
    }
    
    @Override
    public void update() {
        this.setTopAnchor(hero, hra.getHerniPlan().getAktualniLokace().getPosY());
        this.setLeftAnchor(hero, hra.getHerniPlan().getAktualniLokace().getPosX());
    }
    
      public void nastaveniHernihoPlanu (IHra hra){
        hra.getHerniPlan().deleteObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
}
