/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package uiText;
import java.io.*;
import java.util.Scanner;
import logika.IHra;

/**
 * Class TextoveRozhrani
 *
 * Toto je třída uživatelského rozhraní aplikace Adventura. Třída vytváří instanci třídy Hra,
 * která představuje logiku aplikace. Čte vstup zadaný uživatelem a předává tento řetězec logice
 * a vypisuje odpověď logiky na konzoli.
 *
 * @author     Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2016/2017
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     * Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     * příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     * hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        // Základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }

    public void hrajZeSouboru(String nazevSouboru) {
        System.out.println("Nacitam prikazy ze souboru " + nazevSouboru + "\n");
        
        
        // Základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.
        try(BufferedReader ctec = new BufferedReader(new FileReader(nazevSouboru)))
        {
            System.out.println(hra.vratUvitani());
            
            String radek = prectiString();
            while (radek != null)
            {
                System.out.println("* " + nazevSouboru + " *");
                radek = ctec.readLine();
            }
            System.out.println(hra.vratEpilog());
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Soubor " + nazevSouboru + " nelze přečíst");
        }
        catch (IOException e)
        {
            System.out.println("Soubor " + nazevSouboru + " nelze přečíst");
        }
        
        
        
    }
    
    
    
    /**
     * Metoda přečte příkaz z příkazového řádku
     *
     * @return    vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
