package logika;

/*******************************************************************************
 * Instance třídy PrikazVezmi představují všechny potřebné příkazy k možnosti sebrání
 * předmetu
 *
 * @author    David Zamrazil
 * @version   1.00.000
 * @created    2017
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    private static final String PARAMETRY = "předmet";
    private static final String POPIS = " pokusí se vzít předmět v místnosti";
    private HerniPlan hPlan;
    private Batoh batoh;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazVezmi(HerniPlan hPlan, Batoh batoh)
    {
        this.hPlan = hPlan;
        this.batoh = batoh;
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
        if(parametry.length < 1) {
            return "Nevím, co mám sebrat";
        }
        
        if(parametry.length > 1) {
            return "Bohužel, nedokáži sebrat více předmětů";
        }
        
        String nazevPredmetu = parametry[0];
        Lokace aktLokace = hPlan.getAktualniLokace();
        
        if (aktLokace.obsahujePredmet(nazevPredmetu)){
            Predmet odebirana = aktLokace.vyberPredmet(nazevPredmetu);
            if (odebirana == null || !odebirana.isPrenositelny()) {  
                return "Toto sebrat nemůžeš.";
            }
            if (batoh.vlozVec(odebirana) && odebirana.isPrenositelny()) {
                                hPlan.notifyAllObservers();
                return odebirana.getPopis() + "\n" + "Sebral jsi předmět: " + odebirana.getNazev() + 
                            " a vložil sis tento předmět do batohu.";
                                      }                             
            else {
                aktLokace.vlozPredmet(odebirana);
                                hPlan.notifyAllObservers();
                return "Batoh již máš plný.";
            }
            
        }
        else {
            return "Tak to fakt neseberu!";
        }
        
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
