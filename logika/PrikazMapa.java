package logika;

import javax.swing.*;


/*******************************************************************************
 * Třída PrikazMapa implementuje pro hru příkaz mapa.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    David Zamrazil
 * @version   1.00.00
 * @created    2017
 */
public class PrikazMapa implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "mapa";
    private static final String PARAMETRY = "";
    private static final String POPIS = " zobrazí jednoduchou mapu hry";

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor - Není potřeba.
    
    /**
     * Metoda zobrazí hráči plánek hry.
     *
     * @param parametry - parametr se pro tento příkaz žádný nepoužívá
     * @return zpráva, kterou vypíše hra hráči.
     */ 
    @Override
    public String proved(String... parametry){
        final ImageIcon icon = new ImageIcon(".\\plan.png");
        JOptionPane.showMessageDialog(null,"","Mapa", JOptionPane.INFORMATION_MESSAGE, icon);    
        return "Legendární mapa světa, snad ti pomůže na cestě";
    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    
    /**
     *  Metoda vrací parametry (parametry které hráč musí zadat, aby příkaz proběhnul správně).
     *  
     *  @ return parametry prikazu
     */
    @Override
    public String getParametry() {
        return PARAMETRY;
    }
    
    /**
     *  Metoda vrací popis příkazu
     *  
     *  @ return parametry prikazu
     */
    public String getPopis() {
        return POPIS;
    }

}
