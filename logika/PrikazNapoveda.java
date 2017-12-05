/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha
 * @version    LS 2016/2017
 * @created    2017
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private static final String PARAMETRY = "";
    private static final String POPIS = " zobrazí nápovědu";
    private SeznamPrikazu platnePrikazy;

   /**
    * Konstruktor třídy
    *
    * @param    platnePrikazy seznam příkazů, které je možné ve hře použít, aby je nápověda mohla zobrazit uživateli.
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @return    napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je dovést lék pro vaši ženu,\n"
        + "lék ti dá kouzelník z věže za lesem.\n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }

     /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
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
