//* Soubor je ulozen v kodovani UTF-8.
 /* Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy PrikazVezmi představují všechny potřebné příkazy k možnosti sebrání
 * předmetu
 *
 * @author    David Zamrazil
 * @version   1.00.000
 * @created    2017
 */
public class PrikazProzkoumej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prozkoumej";
    private static final String PARAMETRY = "";
    private static final String POPIS = " vrátí aktuálí informace o poloze, předmětech a postavách";
    private HerniPlan hPlan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazProzkoumej(HerniPlan hPlan)
    {
        this.hPlan = hPlan;
    }

    
    
    /**
     * Metoda představuje zpracování příkazu pro sebrání předmětu.
     * Nejprve zkontroluje, zda byl zadán správně jeden název jako parametr,
     * ověří, zda v aktuální lokaci je předmět s tímto názvem, zda je přenositelný,
     * zda je v batohu místo a následně předmět odebere z lokace, zapíše do batohu.
     * 
     * @param Parametry pole prametrů zadaných na příkazové řádce
     * @return Vrací výsledek
     * 
     * String... možnost zadat více stringů, tj. 
     * proved("x","y","z");
     * 
     */
    
    @Override
    public String proved(String... parametry) {
        if(parametry.length > 1) {
            return "Nevím, co mám dělat";
        }
        
        
        //vypis předmětu
        if(parametry.length > 0) {
            String nazevPredmetu = parametry[0];
            Lokace aktLokace = hPlan.getAktualniLokace();
            
            if (aktLokace.obsahujePredmet(nazevPredmetu))
            {
                Predmet predmet1 = aktLokace.vezmiPredmet(nazevPredmetu);
                String popis = predmet1.getPopis();
                aktLokace.vlozPredmet(predmet1);
                return "Predmet " + nazevPredmetu + ": " + popis;
            }
            
            
            // if (hPlan.getBatoh()
            
            
            return "Predmet " + nazevPredmetu + " tady není";
        }
        
        

        
        Lokace aktLokace = hPlan.getAktualniLokace();
        return aktLokace.dlouhyPopis();

    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     * @return    nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
    
    /**
     *  Metoda vrací parametry (parametry které háč musí zadat, aby příkaz proběhnul správně).
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
    //== Nesoukromé metody (instancí i třídy) ======================================


    //== Soukromé metody (instancí i třídy) ========================================

}
