package logika;

/*******************************************************************************
 * Třída Predmet představuje každý předmět ve hře.
 *
 * @author    David Zamrazil
 * @version   1.00
 *@created    2017
 */
public class Predmet
{
    private String nazev;
    private String popis;
    private boolean prenositelny;
    private String image;
    /***************************************************************************
     *  Konstruktor vzniku předmětu s možností nastavení přenositelnosti.
     */
    public Predmet(String nazev, String popis, boolean prenositelny, String image)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
        this.image = image + ".png";
    }
    
    /***************************************************************************
     *  Konstruktor vzniku předmětu bez možnosti nastavení přenositelnosti.
     */
    public Predmet(String nazev, String popis, String image)
    {
        this(nazev, popis, true, image);
    }
    
    /**
     * Metoda vrací jméno věci.
     * 
     * @return String jméno věci.
     */
    public String getNazev()
    {
        return nazev;
    }
    
    /**
     * Metoda vrací popis věci.
     * 
     * @return String popis věci.
     */
    public String getPopis()
    {
        return popis;
    }
    
    /**
     * Metoda zjišťuje, zda je věc přenositelná.
     * 
     * @return vrací true, pokud se věc dá přenést.
     */
    public boolean isPrenositelny()
    {
        return prenositelny;
    }
    
    /**
     * Metoda nastaví popis věci.
     */
    public void setPopis(String popis)
    {
        this.popis = popis;
    }
    
    /**
     * Metoda nastaví přenositelnost věci.
     */
    public void setPrenositelny(boolean prenositelny)
    {
        this.prenositelny = prenositelny;
    }
    
    /**
     * Metoda přepisuje metodu toString(), a vrací String řetezec plus název věci.
     */
    @Override
    public String toString()
    {
        return "Predmet: " + nazev;
    }

    public String getImage() {
        return image;
    }
}
