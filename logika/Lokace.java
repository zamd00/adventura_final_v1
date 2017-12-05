/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Lokace - popisuje jednotlivé lokace (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Lokace" reprezentuje jedno místo (místnost, prostor, ...) ve scénáři hry.
 * Lokace může mít sousední lokace připojené přes východy. Pro každý východ
 * si lokace ukládá odkaz na sousedící lokace.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, David Zamrazil
 * @version    LS 2016/2017
 * @created    2017
 */
public class Lokace {

    private String nazev;   // název prostoru
    private String popis;   // popis prostoru
    private Set<Lokace> vychody;   // obsahuje sousední místnosti
    public boolean isViditelny = true;   // pokud prostor je vidět
    private Map<String, Predmet> predmety;
    private Map<String, Postava> seznamPostav; 

    private double posX;
    private double posY;
    /**
     * Vytvoření lokace se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param    nazev nazev lokace, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer
     * @param    popis Popis lokace
     */
    public Lokace(String nazev, String popis, double posX, double posY) {
        this.nazev = nazev;
        this.popis = popis;
        this.posX = posX;
        this.posY = posY;
        vychody = new HashSet<>();
        this.isViditelny = isViditelny;
        predmety = new HashMap<String, Predmet>();
        seznamPostav = new HashMap<String, Postava>();
    }

    /**
     * Definuje východ z lokace (sousední/vedlejsi lokace). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední lokace uvedena
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední lokace).
     * Druhé zadání stejné lokace tiše přepíše předchozí zadání (neobjeví
     * se žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param    vedlejsi lokace, která sousedi s aktualní lokací.
     *
     */
    public void setVychod(Lokace vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou lokací. Překrývá se metoda equals ze
     * třídy Object. Dvě lokace jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param     o object, který se má porovnávat s aktuálním
     * @return    hodnotu true, pokud má zadaná lokace stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Lokace)) {
            return false;    // pokud parametr není typu Lokace, vrátíme false
        }
        // přetypujeme parametr na typ Lokace 
        Lokace druha = (Lokace) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druha.nazev));       
    }


    /**
     * Metoda pro zjištění viditelnosti prostoru.
     * 
     * @return hodnota true pro viditelný, false pro neviditelný.
     */

    public boolean isViditelny() {
        return isViditelny;
    }
    
    /**
     * Metoda nastaví viditelnost prostoru.
     */

    public void setViditelny(boolean stav) {
        this.isViditelny = stav;
    }
    
    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název lokace (byl zadán při vytváření lokace jako parametr
     * konstruktoru)
     *
     * @return    název lokace
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis lokace, který může vypadat následovně: Jsi v
     * mistnosti/lokaci vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return    dlouhý popis lokace
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/lokaci " + popis + ".\n"
                + popisVychodu() + "\n"
                + getSeznamPostav() + "\n"
                + seznamPredmetu() + "\n";
    }
    
    private String seznamPredmetu()
    {
        String seznam = "  predmety:";
        
        for (String nazevPredmetu : predmety.keySet())
        {
            seznam += " " + nazevPredmetu;
        }
        
        return seznam;
    }
    
    
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return    popis východů - názvů sousedních lokací
     */
    public String popisVychodu() {
        String vracenyText = "vychody:";
        for (Lokace sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací lokaci, která sousedí s aktuální lokací a jejíž název je zadán
     * jako parametr. Pokud lokace s udaným jménem nesousedí s aktuální
     * lokací, vrací se hodnota null.
     *
     * @param     nazevSouseda Jméno sousední lokace (východu)
     * @return    lokace, která se nachází za příslušným východem, nebo hodnota null, pokud lokace zadaného jména není sousedem.
     */
    public Lokace vratSousedniLokaci(String nazevSouseda) {
        List<Lokace>hledaneLokace = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneLokace.isEmpty()){
            return null;
        }
        else {
            return hledaneLokace.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující lokace, se kterými ta lokace sousedí.
     * Takto získaný seznam sousedních lokací nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Lokace.
     *
     * @return    nemodifikovatelná kolekce lokací (východů), se kterými tato lokace sousedí.
     */
    public Collection<Lokace> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    public void vlozPredmet(Predmet predmet)
    {
        predmety.put(predmet.getNazev(), predmet);
    }

    public Predmet vezmiPredmet(String nazevPredmetu)
    {
        return predmety.remove(nazevPredmetu);
    }
    
    public boolean obsahujePredmet(String nazevPredmetu)
    {
        return predmety.containsKey(nazevPredmetu);
    }

    /**
     * Metoda vybere předmět z prostoru.
     * 
     * @return vrátí vybraný předmět.
     */
    
    public Predmet vyberPredmet(String jmeno) {
        Predmet vybirana = null;
        for (String nazev : predmety.keySet()) {
            if (nazev.equals(jmeno) & predmety.get(jmeno).isPrenositelny()) {
                vybirana = predmety.get(nazev);
                predmety.remove(nazev);
                break;
            }
        }
        return vybirana;
    }
    
    /**
     * Metoda vloží postavu do prostoru.
     */

    public void vlozPostavu(Postava postava)
    {
        seznamPostav.put(postava.getJmeno(), postava);
    }

    /**
     * Metoda najde postavu.
     */

    public Postava najdiPostavu(String jmeno)
    {
        return seznamPostav.get(jmeno);
    }
    
    /**
     * Metoda vrací seznam postav, které sa nacházejí v dané lokaci.
     * 
     * return   seznam postav.
     */

    public String getSeznamPostav() {
        String seznam = "  jsou zde tyto postavy: ";
        for (String jmenoPostav : seznamPostav.keySet()){
            seznam += jmenoPostav + " ";
        }
        return seznam;
      }  
        
    public double getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public double getPosY() {
        return posY;
    }
    
    public Map<String, Predmet> getObsahLokace()
    {
        return predmety;
    }
}
