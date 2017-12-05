package logika;

import javax.swing.*;
import java.util.Random;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz hadej.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     David Zamrazil
 *@version    1.000
 *@created    2017
 */
class PrikazHraj implements IPrikaz {
    private static final String NAZEV = "hraj";
    private static final String PARAMETRY = "";
    private static final String POPIS = " začne hrát hru";
    private HerniPlan plan;
    private Random nahoda;
    private boolean poprve;
    private boolean podruhe;

    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param nahoda náhodná funkce pro generování nahodných čísel
    *  @poprve atribut s logickou hodnotou, určující zda se hraje poprvé nebo podruhé
    *  @podruhe atribut s logickou hodnotou, určující zda se hraje poprvé nebo podruhé
    */    
    public PrikazHraj (HerniPlan plan) {
        this.plan = plan;  
        nahoda = new Random(); // náhodná funkce
        poprve = false; //eviduje zda už se jednou hrálo
        podruhe = false; // eviduje zda už se dvakrat hrálo
        
    }

    /**
     *  Tato metoda provádí příkaz "hraj". a slouží k hrání kostek s rusalkou,
     *  pokud hráč vyhraje rusalka mu dá rady k uspěšnému vyhrání hry<br>
     *  -hráč může hrát kostky s rusalkou pouze dvakrát díky atributům poprve a podruhe,
     *  a s ostatními postavy ani jednou (jako zpětnou odpověď stringový řetězec, že osoba nehraje).<br>
     *  -čísla na kostkách jsou pseudonahodne generovana a to v rozsahu (0 až 5) + 1, resp. 1 až 6 <br>
     *  aby bylo možno hrát s ježibabou kostky musí být splněny následující podmínky<br>
     *  - musí být zadán parametr<br>
     *  - parametr musí být jméno osoby nacházející se v aktuální místnosti 
     *  
     *  
     *@param parametry - jako parametr hráč zadá osobu, s ketrou chce hrát 
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo tak ....
            return "S kým mám hrát?";
        }
        int cislo1 = 6; // hračova kostka
        cislo1 =  nahoda.nextInt(cislo1)+1; //hráč hází
        int cislo2 = 6; // kostka rusalky
        cislo2 =  nahoda.nextInt(cislo2)+1;        // baba hází
        
        String clovek = parametry[0];
        Postava osoba = plan.getAktualniLokace().najdiPostavu(clovek);
        if (osoba == null) {
            return "nikdo takový tu není";
        } 
        else  if (osoba.getJmeno() == "trpaslik") { //trpaslik kostky nehraje...
            return "Trpaslik: \"Já si s tebou nezahraju kostky, mě to nebaví, promiň\"";
        }
        else  if (osoba.getJmeno() == "zena") { //zena kostky nehraje...
            return "Zena: \"Já tu umírám a ty si chceš hrát?\"";
        }  
        else  if (osoba.getJmeno() == "kouzelnik") { //kouzelnik kostky nehraje...
            return "Kouzelnik: \"Ďábelké kameny, koukej to dát pryč!\"";
        }  
        else  if (osoba.getJmeno() == "rusalka") {
            if (poprve == false && podruhe == false) {
                if (cislo1>cislo2) {
                    
                    poprve  = false;
                    podruhe = true;
                    return "\"Já: \"Tak hrajeme, začni vílo\" \n \" Rusalka: \"Dobrá\" \n (Rusalka hazí kostkou) \n Rusalka: \"áá padla mi "+cislo2+", ted se ukaž ty\" \n (Házíš kostkou) \n Já: \"hurá mám "+cislo1+" vyhrál jsem!!!\" \n Rusalka: \"Gratuluji, nechť je po tvém, má rada zní takto, \n musíš vyrazit, klíč od léku se nachází hluboko v jeskyních, a natrhej si tu trávu pro kouzelníka! \"";                                
                    //v tomto případě jsi vyhral, a obdržel radu jak získat lék...
                } 
                else if (cislo2>cislo1) {
                    poprve = true;
                    return "\"Já: \"Tak hrajeme, začni vílo\" \n \" Rusalka: \"Dobrá\" \n (Rusalka hazí kostkou) \n Rusalka: \"áá padla mi "+cislo2+", ted se ukaž ty\" \n (Házíš kostkou) \n Já: \"Achjo, mám "+cislo1+"\" \n Rusalka: \"Hurá vyhrála jsem, ale nebuď smutný máš ještě jeden pokus\""; 
                    //vtomto případě vyhrala rusalka...
                } 
                else if (cislo2==cislo1) {    
                   return "\"Já: \"Tak hrajeme, začni vílo\" \n \" Rusalka: \"Dobrá\" \n (Rusalka hazí kostkou) \n Rusalka: \"áá padla mi "+cislo2+", ted se ukaž ty\" \n (Házíš kostkou) \n Já: \"Mně padla taky "+cislo1+"\" \n Rusalka: \"Tak se to nepočítá a hrajem ještě jednou\"";
                   // oba hodili stejně hraje se znovu...
               }
            } 
            else if(poprve == true && podruhe == false) {
                if (cislo1>cislo2) {
                    
                    poprve  = false;
                    podruhe = true;
                    return "\"Já: \"Tak hrajeme, začni vílo\" \n \" Rusalka: \"Dobrá\" \n (Rusalka hazí kostkou) \n Rusalka: \"áá padla mi "+cislo2+", ted se ukaž ty\" \n (Házíš kostkou) \n Já: \"hurá mám "+cislo1+" vyhrál jsem!!!\" \n Rusalka: \"Gratuluji, nechť je po tvém, má rada zní takto, \n musíš vyrazit, klíč od léku se nachází hluboko v jeskyních, a natrhej si tu trávu pro kouzelníka! \"";                                
                    //v tomto případě jsi vyhral, a obdržel radu jak získat lék...
                } 
                else if (cislo2>cislo1) {
                    poprve = true;
                    return "\"Já: \"Tak hrajeme, začni vílo\" \n \" Rusalka: \"Dobrá\" \n (Rusalka hazí kostkou) \n Rusalka: \"áá padla mi "+cislo2+", ted se ukaž ty\" \n (Házíš kostkou) \n Já: \"Achjo, mám "+cislo1+"\" \n Rusalka: \"Hurá vyhrála jsem, a teď jdi pryč! \n Pozn. Rusalka má radá pozornot, zkus si ještě hrát.\""; 
                    //vtomto případě vyhrala rusalka...
                       } 
                       else if (cislo2==cislo1) {    
                    return "\"Já: \"Tak hrajeme, začni vílo\" \n \" Rusalka: \"Dobrá\" \n (Rusalka hazí kostkou) \n Rusalka: \"áá padla mi "+cislo2+", ted se ukaž ty\" \n (Házíš kostkou) \n Já: \"Mně padla taky "+cislo1+"\" \n Rusalka: \"Tak se to nepočítá a hrajem ještě jednou\"";
            
                       }
                }
                else if(poprve == true && podruhe == true) {
                    return "Rusalka: \"I když si dvakrát prohrál, poradím ti: Musíš vyrazit, klíč od léku se nachází hluboko v jeskyních, a natrhej si tu trávu pro kouzelníka!\"";
                    // Dvakrát prohrál už nemůže hrát znovu
                } 
                else if (poprve == false && podruhe == true) {
                    return "Rusalka: \"Musíš vyrazit, klíč od léku se nachází hluboko v jeskyních, a natrhej si tu trávu pro kouzelníka!\"";
                    // Vyhrál jsi v kostkách, a už nemůžeš hrát znovu (v případě že vyhrál napoprvé) 
            }    
            
        }
        return null;
    }
     
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
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
