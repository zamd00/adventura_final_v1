package logika;
import java.util.*;
import utils.SubjektZmenyBatoh;
import utils.ObserverZmenyBatoh;

/*******************************************************************************
 * Třída Batoh představuje inventář s omezenou kapacitou
 * Umožňuje manipulaci s věcmi.
 *
 * @author    David Zamrazil    
 * @version   1.00
 * @created    2017
 * 
 */
public class Batoh
{
    //== Datové atributy (statické i instancí)======================================
    
    private Map <String, Predmet> seznamVeci;   //Seznam věcí v batohu.
    private static final int KAPACITA = 3 ;   //Maximální počet věcí v batohu.
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor.
     */
    public Batoh()
    {
       seznamVeci = new HashMap<String, Predmet>();
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Metoda, která najde věc.
     *  
     *  @param  vec  Parametrem je věc, kterou chceme získat.
     */     
    public Predmet getPredmet(String nazevVeci) {
        return seznamVeci.get(nazevVeci);
    }
    
    /**
     *  Metoda zjistí, zda se věc vejde do batohu.
     *
     *  return   Vrátí true, pokud se vejde do batohu.
     */
    public boolean jeMistoVBatohu()
    {
        return (seznamVeci.size() < KAPACITA);
    }
    
    /**
     * Metoda přidá věc do batohu, pokud v něm je místo.
     * 
     * @param vec věc, která se má přidat do batohu.
     * 
     * return true, pokud se věc podaří přidat do batohu.
     */

    public boolean vlozVec (Predmet predmet)
    {
        if(jeMistoVBatohu() && (predmet.isPrenositelny())) {
            seznamVeci.put(predmet.getNazev(), predmet);
            return true;
        }
        return false;
    }
    
    
    
     /**
     * Metoda odebere věc z batohu.
     * 
     * @param  vec  Parametrem je věc, která sa má odebrat z batohu.
     * 
     * return   Vrátí jméno vyhozené věci, pokud se ji podařilo vyhodit.
     */   
    public Predmet vyhodVec(String jmeno){
        Predmet vyhozenaVec = null;
        if (seznamVeci.containsKey(jmeno)) {
            vyhozenaVec = seznamVeci.get(jmeno);
            seznamVeci.remove(jmeno);
        }
        return vyhozenaVec;  
    } 
    
     /**
     *  Metoda zjišťuje, zda se daná věc vyskytuje v batohu.
     *  
     *  @param  vec  Parametrem je věc, na kterou se ptáme.
     */   
    public boolean obsahujeVec (String jmeno) {
        if (this.seznamVeci.containsKey(jmeno)) {
            return true;
        }
        return false;
    }    

    /**
     *  Metoda zjistí obsah batohu.
     *  Jednotlivé věci jsou odděleny mezerou.
     *  
     *  return seznam věcí v batohu.
     */   
    public String nazvyVeci() {
        String nazvy = "V batohu máš: ";
        for (String jmenoVeci : seznamVeci.keySet()){
            nazvy += jmenoVeci + " ";
        }
        return nazvy;
    }
    
    public Map<String, Predmet> getObsahBatoh()
    {
        return seznamVeci;
    }
    
}
