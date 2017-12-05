/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha
 * @version    LS 2016/2017
 * @created    2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private static final String PARAMETRY = "lokace";
    private static final String POPIS = " přejde do vedlejšího prostoru";
    private HerniPlan plan;
    private Batoh batoh;

   /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

   /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadané lokace. Pokud lokace
     * existuje, vstoupí se do nového lokace. Pokud zadaná sousední lokace
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param     parametry jako parametr obsahuje jméno lokace (východu), do kterého se má jít.
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední lokace), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        
        // zkoušíme přejít do sousedního prostoru
        Lokace aktualniLokace = plan.getAktualniLokace();
        Lokace sousedniLokace = plan.getAktualniLokace().vratSousedniLokaci(smer);

        if (sousedniLokace == null) {
            return "Tam se odsud jít nedá!" + "\n";
        }
        
        if(batoh.nazvyVeci().contains("klic") && aktualniLokace.getNazev().equals("vez")) {
            plan.getAktualniLokace().vratSousedniLokaci("sklepeni").setViditelny(true);
        }
        
        if (sousedniLokace.isViditelny) {
            plan.setAktualniLokace(sousedniLokace);
            return sousedniLokace.dlouhyPopis();
        }
        else {        
            return "Podle báje vím, že to tu někde je, ale nevím, \n" +
            "kudy se tam mohu dostat. Zkus  od někoho vyzjistit \n" +
            " kudy by se tam dalo dostat." + "\n";
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
     *  Metoda vrací parametry (parametry které hráč musí zadat, aby příkaz proběhnul správně).
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
}
