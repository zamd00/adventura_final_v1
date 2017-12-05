package logika;



/*******************************************************************************
 * Třída PrikazVyhod implementuje pro hru príkaz vyhod.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    David Zamrazil
 * @version   1.0
 * @created    2017
 */
public class PrikazVyhod implements IPrikaz  {

    private static final String NAZEV ="vyhod";
    private static final String PARAMETRY = "věc";
    private static final String POPIS = " vyhodí věc z batohu";
    private HerniPlan plan;
    private Batoh batoh;

    /**
     *  Konstruktor třídy
     */    
    public PrikazVyhod(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }


    /**
     *  Vykonává příkaz "vyhod". Zkouší se vyhodit zadaný předmět z batohu. Pokuď
     *  je požadovaný předmět v batohu tak sa vloží do aktuální místnosti. Když
     *  požadovaný předmět batoh neobsahuje vypíše sa chybové hlášení.
     *
     *@param parametr - jmeno veci, kterou chceme vyhodit
     *@return zpráva, která se vypíše hráči
     */     
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Když zadáš, co mám vyhodit, tak to půjde lépe";
        }

        String jmeno = parametry[0];
        Predmet predmet = batoh.vyhodVec(jmeno);
        if (predmet == null) {
            return "Nemůžeš vyhodit z batohu něco, co tam nemáš!";
        }
        else {
            plan.getAktualniLokace().vlozPredmet(predmet);
            plan.notifyAllObservers();
            return "Vyhodil si " + jmeno;
        }   
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