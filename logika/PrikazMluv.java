package logika;



/*******************************************************************************
 * Třída PrikazMluv implementuje pro hru příkaz mluv.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    David Zamrazil
 * @version   1.00
 * @created    2017
 */
public class PrikazMluv implements IPrikaz
{
    private static final String NAZEV = "mluv";
    private static final String PARAMETRY = "postava";
    private static final String POPIS = " promluví si s postavou";
    private HerniPlan plan;
    

    /**
     *  Konstruktor tridy PrikazMluv
     */
    public PrikazMluv(HerniPlan plan)
    {
        this.plan = plan;
        
    }


    /**
     *Vykonává příkaz "mluv". Zkouší se hovořit se zadanou postavou. 
     *@param parametr - jmeno postavy, se kterou se ma komunikovat
     *@return proslov postavy, který se vypíše hráči
     */ 
    public String proved(String... parametry) { 
        if (parametry.length == 0) {
            return "S kým mám hovořit?";
        }
        String jmeno = parametry[0];
        Lokace aktualniLokace = plan.getAktualniLokace();
        Postava postava = aktualniLokace.najdiPostavu(jmeno);
        if (postava == null) {
            return "Ten tu není!";
        }
        /*if(postava.getJmeno().equals("kouzelnik")){
            batoh.vlozVec(new Vec("talisman"," Talisman pravého vidění."));
            return postava.getProslov() + "\n"+ "\n"+
            "Do batohu byla přidána věc: talisman";           
        }
        */
        else  {
            return postava.getMluv();
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