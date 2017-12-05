/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Postava představují postavy vyskytující se v adventuře.
 *
 * @author    David Zamrazil
 * @version   1.00.000
 * @created    2017
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    
    private String jmeno;
    private String mluv;
    private String mluv2;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor nastaví jméno a řeč postav
     */
    public Postava(String jmeno, String mluv, String mluv2)
    {
        this.jmeno = jmeno;
        this.mluv = mluv;
        this.mluv2 = mluv2;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda vrací jméno postavy.
     * 
     * @return   String jméno postavy.
     */
    public String getJmeno() {
        return jmeno; 
    }


    /**
     * Metoda poskytuje první řeč postavy.
     * 
     * @return  String první řeč postavy.
     */
    public String getMluv() {
        return mluv;
    }
    
    /**
     * Metoda poskytuje druhý řeč postavy.
     * 
     * @return  String druhý řeč postavy.
     */
    public String getMluv2() {
        return mluv2;
    }
}
