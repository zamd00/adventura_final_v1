/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída implementující toto rozhraní bude ve hře zpracovávat jeden konkrétní příkaz.
 * Toto rozhraní je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Jan Riha, David Zamrazil
 * @version    LS 2016/2017
 * @created    2017
 */
interface IPrikaz {
	
	/**
     * Metoda pro provedení příkazu ve hře.
     * Počet parametrů je závislý na konkrétním příkazu,
     * např. příkazy konec a napoveda nemají parametry
     * příkazy jdi, seber, polož mají jeden parametr
     * příkaz pouzij může mít dva parametry.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    public String proved(String... parametry);
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return    nazev prikazu
     */
	public String getNazev();
	
	/**
     *  Metoda vrací parametry (parametry které hráč musí zadat, aby příkaz proběhnul správně)
     *  
     *  @ return parametry prikazu
     */
    public String getParametry();
    
    /**
     *  Metoda vrací popis příkazu
     *  
     *  @ return parametry prikazu
     */
    public String getPopis();
}
