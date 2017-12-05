package logika;



/*******************************************************************************
 * Třída PrikazUkazBatoh implementuje pro hru příkaz ukaž batoh.
 * vypíše veci, které jsou momentálně v batohu
 *
 * @author    David Zamrazil
 * @version   1.00
 * @created    2017
 */
public class PrikazUkazBatoh implements IPrikaz {

    private static final String NAZEV = "ukaz";
    private static final String PARAMETRY = "";
    private static final String POPIS = " ukáže obsah batohu";
    private Batoh batoh;

    /**
     * Konstruktor tridy PrikazUkazBatoh
     */
    public PrikazUkazBatoh(Batoh batoh)
    {
        this.batoh = batoh;
    }

    /**
     * Metoda vraci seznam věcí, kterě jsou v batohu 
     * 
     * @param parametry - parametr se pro tento příkaz žádný nepoužívá
     * 
     * @return obsah batohu
     */
    public String proved(String... parametry) {
        return batoh.nazvyVeci();    
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
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
