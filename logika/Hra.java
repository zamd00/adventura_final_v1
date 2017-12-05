/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída logiky aplikace. Třída vytváří instanci třídy HerniPlan, která inicializuje
 * mistnosti hry a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry. Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, David Zamrazil
 * @version    LS 2016/2017
 * @created    2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    public boolean konecHry = false;
    private Batoh batoh;
    private Lokace lokace;
    
    private String textEpilogu = "Dík, že jste si zahráli.  Ahoj.";

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan(this);
        batoh = new Batoh();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUkazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazVyhod(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
		platnePrikazy.vlozPrikaz(new PrikazMapa());
		platnePrikazy.vlozPrikaz(new PrikazHraj(herniPlan));
		
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Toto je příběh o hledání léku pro vaši nemocnou ženu, \n" +
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniLokace().dlouhyPopis();
    }
    
    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jste si zahráli.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param    radek  text, který zadal uživatel jako příkaz do hry.
     * @return   vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        
        if (herniPlan.isVyhra()){
            konecHry = true;
            textKVypsani = "\n Vyléčil jsi ženu! Gratuluji!";
        }
        
         if (herniPlan.isProhra()){
            konecHry = true;
            textKVypsani = "\nJelikož si nebyl schopen získat lék,\n" +
                            "tak ti umřela žena, tak snad příště.";
        }
        

        return textKVypsani;
    }
    
    
    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *  
     * @param    konecHry hodnota false = konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     * @return    odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
    /**
     * Metoda vrací odkaz na inventář
     *
     * @return     inventář (batoh)
     */ 
    public Batoh getBatoh(){
        return batoh;
     } 
     
    public Lokace getLokace(){
        return lokace;
     }
}

