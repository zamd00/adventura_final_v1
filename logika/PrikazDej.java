package logika;



/*******************************************************************************
 *Třída PrikazDej implementuje pro hru příkaz dej.
 *Tato třída je součástí jednoduché textové hry.
 *
 * @author    David Zamrazil
 * @version   1.1
 * @created    2017
 */
public class PrikazDej implements IPrikaz
{
    private static final String NAZEV = "dej";
    private static final String PARAMETRY = "předmět postava";
    private static final String POPIS = " pokusí se dát přemět postavě";
    private HerniPlan plan;
    private Batoh batoh;
    public boolean konecHry = false;

    /***************************************************************************
     *  Konstruktor třídy PrikazDej
     */
    public PrikazDej(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
        
    }

    /**
     *  Vykonává příkaz "dej". Zkoušejí se dát různé předměty postavě. 
     *  Nejdřív ověřuje, zda se předmět nachází v batohu a zda postava
     *  je v prostoru.
     *  Pokud něco z toho nenajde, vrací příslušné chybové hlášení.
     *  
     *  V závislosti na okolnostech dostaneme různé výsledky.
     *@param parametry  - parametr jmeno předmětu, která se ma postavě dát a
     *                    jméno postavy, které se má věc dát.
     *@return zpráva, která se vypíše hráčovi
     */ 
    public String proved(String... parametry){
        if (parametry.length == 0) {
            return "Nezadal jsi postavu, které mám dát věc \n" + "ani věc, kterou mám dát.\n"+
                        "Příkaz by měl vypadat takto: dej věc postava";  
        }
        
        if (parametry.length == 1) {
            return "Nezadal jsi postavu, které mám dát věc \n" + "nebo věc, kterou mám dát.\n " +
                        "Příkaz by měl vypadat takto: dej věc postava"; 
        }
        Lokace aktualniLokace = plan.getAktualniLokace();
        String davanaVec = parametry[0];
        Predmet predmet = batoh.getPredmet(davanaVec);
        String jmeno = parametry[1];
        Postava postava = aktualniLokace.najdiPostavu(jmeno);
        if (predmet == null){
            return "Tohle v batohu není.\n" + "  Nebo si napsal jméno postavy první.\n " +
                        "  Příkaz by měl vybadat takto: dej věc postava"; 
        }
        
        if (postava == null){
            return "Tahle postava tu není.\n" + "  Nebo si napsal název věci druhý.\n " +
                        "  Příkaz by měl vybadat takto: dej věc postava"; 
        }

        if ( predmet.getNazev().equals("pivo")
            && (aktualniLokace.getNazev().equals("hluboky_les")) && (postava.getJmeno().equals("trpaslik")))
            {
            batoh.vyhodVec(davanaVec);
            plan.getAktualniLokace().vratSousedniLokaci("jeskyne").setViditelny(true);
            return postava.getMluv2();  
        }
        
        if ( predmet.getNazev().equals("lek")
            && (aktualniLokace.getNazev().equals("chaloupka")) && (postava.getJmeno().equals("zena")))
            {
            batoh.vyhodVec(davanaVec);
            plan.setVyhra(true);
            return postava.getMluv2() + "  počkej ty jsi to stihl? Můj lék! Jsem zachráněná, děkuji ti muži";  
        }
        
        if ( predmet.getNazev().equals("jed")
            && (aktualniLokace.getNazev().equals("chaloupka")) && (postava.getJmeno().equals("zena")))
            {
            batoh.vyhodVec(davanaVec);
            plan.setVyhra(false);
            return postava.getMluv2() + "  počkej ty jsi to stihl? Můj lék! Jsem zachránee eehehe he";  
        }
        
        if ( predmet.getNazev().equals("jahody")
            && (aktualniLokace.getNazev().equals("hluboky_les")) && (postava.getJmeno().equals("trpaslik")))
            {
            batoh.vyhodVec(davanaVec);
            plan.getAktualniLokace().vratSousedniLokaci("jeskyne").setViditelny(true);
            return postava.getMluv2();  
        }

        if (predmet.getNazev().equals("trava") && (aktualniLokace.getNazev().equals("vez"))
                && (postava.getJmeno().equals("kouzelnik"))){
            batoh.vyhodVec(davanaVec);
            
            return postava.getMluv2() + "\n"; 
        }
        else{
            return 
                "Zkontoluj název věci a jméno postavy, zda jsou správně. \n" +
                "Popřípadě jestli jsi ve správné místnosti. \n" +
                "Je taky možné, že postava předmět, kterou jí nabízíš, nechce";
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */

    public String getNazev()
    {
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