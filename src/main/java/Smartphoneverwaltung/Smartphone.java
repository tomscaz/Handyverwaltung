package Smartphoneverwaltung;

public class Smartphone{

//Alle 3 Datentypen
    private String modell;
    private String hersteller;
    private String betriebssystem;
    private double preis;
    private boolean aufLager;

//Konstruktor:
    public Smartphone(String modell, String hersteller, String betriebssystem, double preis, boolean aufLager){
        this.modell = modell;
        this.hersteller = hersteller;
        this.betriebssystem = betriebssystem;
        this.preis = preis;
        this.aufLager = aufLager;
    }

//für unseren Test
    public static double berechneGesamtwert(java.util.List<Smartphone> liste) {
        double summe = 0;
        for (Smartphone s : liste) {
            summe += s.getPreis();
        }
        return summe;
    }

//Unsere Getter:
    public String getModell(){ return modell; }
    public String getHersteller(){ return hersteller; }
    public String getBetriebssystem(){ return betriebssystem; }
    public double getPreis() { return preis; }
    public boolean istAufLager(){ return aufLager; }

//Formatierte Ausagbe in der Liste
    @Override
    public String toString(){
        String lagerStatus = aufLager ? "Auf Lager" : "Nicht verfügbar"; //elegantes if else
        return modell + " ( Hersteller: " + hersteller + ", OS: " + betriebssystem + ", Preis: " + preis + "€ , " + lagerStatus + " )";
    }
}
