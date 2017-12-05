/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import UI.Mapa;
import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;
import utils.ObserverZmenyBatoh;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, David Zamrazil
 * @version    LS 2016/2017
 * @created    2017
 */
public class HerniPlan implements Subject{
    private static final String NAZEV_VITEZNE_LOKACE = "chaloupka";
    
    private Lokace aktualniLokace;
    private boolean vyhra = false;
    private boolean prohra = false; 
    private Hra hra;
    
    private List<Observer> listObserveru = new ArrayList<Observer>();
    private List<ObserverZmenyBatoh> seznamPozorovateluBatoh;
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public HerniPlan(Hra hra) {
        zalozLokaceHry();
        seznamPozorovateluBatoh = new ArrayList<>();
        this.hra = hra;
    }

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví chaloupka.
     */
    private void zalozLokaceHry() {
        // vytvářejí se jednotlivé lokace
        Lokace chaloupka = new Lokace(NAZEV_VITEZNE_LOKACE, "chaloupka, ve které bydlíš se svoji ženou",500,50);
        Lokace louka = new Lokace("louka","rozsáhlá pláň, kde podle legendy tančí rusalka, ráda hrající kostky \n     Pozn. Použij příkaz hraj",75,50);
        Lokace les = new Lokace("les","les s jahodami, malinami a pramenem vody",300,200);
        Lokace hlubokyLes = new Lokace("hluboky_les","temný les, ve kterém lze potkat trpaslíka",350,300);
        Lokace jeskyne = new Lokace("jeskyne","stará jeskyně na jejiž stěně je vytesáno: Zde padl za vlast největší kouzelník Belzebub",550,300);
        Lokace hlubokaJeskyne = new Lokace("hluboka_jeskyne", ". V málo známých místech, se často něco nachází",525,425);
        Lokace vez = new Lokace("vez","zde sídlí jediný prastarý kouzelník Čarovous",200,500);
        Lokace sklepeni = new Lokace("sklepeni", "podzemní sklep věže, zde se nachází truhla s lékem",100,600);
        
        //Predmety
        Predmet pivo = new Predmet("pivo", " Láhev polotmavého ležáku", true, "pivo");
        chaloupka.vlozPredmet(pivo);
        
        Predmet trava = new Predmet("trava"," Tajemná bylina obsahující magické účinky",true,"trava");
        louka.vlozPredmet(trava);
        
        Predmet jahody = new Predmet("jahody"," Štavnaté jahůdky, plné kouzelné chuti",true,"jahody");
        louka.vlozPredmet(jahody);
        
        Predmet pamatnik = new Predmet("pamatnik"," V těchto jeskyních padl za vlast velký bojovník Belzebub.",false,"pamatnik");
        jeskyne.vlozPredmet(pamatnik);
        
        Predmet klic = new Predmet("klic"," Prastarý klíč, co asi otvírá?",true,"klic");
        hlubokaJeskyne.vlozPredmet(klic);
        Predmet mrtvola = new Predmet("mrtvola"," Již jen pozůstatky těla, komu patřili?",false,"mrtvola");
        hlubokaJeskyne.vlozPredmet(mrtvola);
        
        //Predmet lek = new Predmet("lek", " Všemocný lék", false);
        //sklepeni.vlozPredmet(lek);
        
        Predmet lek = new Predmet("lek", " Toto je lék pro uzdravení své ženy", true,"lek");
        sklepeni.vlozPredmet(lek);
        Predmet jed = new Predmet("jed", " Alternativní řešení našeho problému", true,"jed");
        sklepeni.vlozPredmet(jed);
        
        Predmet stul = new Predmet("stul", " Kozelníků trojúhelníkový stůl", false,"stul");
        vez.vlozPredmet(stul);
        Predmet hrnec = new Predmet("hrnec", " Hrnec na vaření značky Tescona.", false,"hrnec");
        vez.vlozPredmet(hrnec);
        Predmet zidle = new Predmet("zidle", " Stará dvojnohá trojnožka.", false,"zidle");
        vez.vlozPredmet(zidle);
        Predmet knihy = new Predmet("knihy", " Staré knihy a komiksy.", false,"knihy");
        vez.vlozPredmet(knihy);
        // přiřazují se průchody mezi lokacemi (sousedící lokace)
        chaloupka.setVychod(louka);
        chaloupka.setVychod(les);
        louka.setVychod(chaloupka);
        louka.setVychod(les);
        les.setVychod(louka);
        les.setVychod(chaloupka);
        les.setVychod(hlubokyLes);
        hlubokyLes.setVychod(les);
        hlubokyLes.setVychod(jeskyne);
        jeskyne.setVychod(hlubokyLes);
        jeskyne.setVychod(hlubokaJeskyne);
        hlubokaJeskyne.setVychod(jeskyne);
        hlubokyLes.setVychod(vez);
        vez.setVychod(hlubokyLes);
        vez.setVychod(sklepeni);
        sklepeni.setVychod(vez);
            //viditelnost vybraných lokací
        jeskyne.setViditelny(false);
        sklepeni.setViditelny(false);
        
        chaloupka.vlozPostavu(new Postava("zena", "ŽENA: Prosím, potřebuji lék co nejdříve!","ŽENA: Spěchej prosím!"));
        louka.vlozPostavu(new Postava("rusalka", "RUSALKA: Zdravím cizinče, vidím tvůj osud, \n" + "        pouzij 'hraj' a třeba vyhraješ návod", 
                                                    "RUSALKA: Rady už máš, teď jdi"));
        hlubokyLes.vlozPostavu(new Postava("trpaslik", "TRPASLÍK: Poslyš člověče,\n" + 
                                                       "          neměl bys něco do bachoru?\n", 
                                                       "TRPASLÍK: No to je šmak, díky ti, jinak támhle se, \n" +
                                                       "          nachazí vchod do jeskyně"));
        vez.vlozPostavu(new Postava("kouzelnik", "KOUZELNÍK: Vítej cizinče, vím co chceš, viděl jsem budoucnost, \n" +
                                                  "           musíš mi přinést trávu z louky a pak ti poradím, \n" +
                                                  "           jak můžeš získat lék pro svoji ženu",
                                                  "KOUZELNÍK: Děkuji za travičku, teď musíš mít klíč, \n" +
                                                  "           aby ses dostal do sklepení, ve které je\n" +
                                                  "           to co hledáš pro tvoji ženu"));
                                                    
        aktualniLokace = chaloupka;  // hra začíná v domečku       
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    
    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    public Hra getHra() {
        return hra;
    }

    
    
    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     */
    public void setAktualniLokace(Lokace lokace) {
       aktualniLokace = lokace;
       notifyAllObservers();
    }

    
    public boolean isVyhra() {
        return vyhra;
    }
    
    public void setVyhra(boolean stav) {
        this.vyhra = stav;
    }
    
    public boolean isProhra() {
        return prohra;
    }
    
    public void setProhra(boolean stav) {
        this.prohra = stav;
    }

    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
    
    
}
