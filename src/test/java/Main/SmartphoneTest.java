package Main;
import Smartphoneverwaltung.Smartphone;
import org.junit.jupiter.api.Test;

public class SmartphoneTest {
    public Smartphone testHandy = new Smartphone("iPhone 15 Pro", "Apple", "iOS", 1199.00, true);

    @Test
    void checkeSmartphone() {
        //Prüfen ob unser Konstruktor und die Getter richtig arbeiten
        assert testHandy.getModell().equals("iPhone 15 Pro");
        assert testHandy.getHersteller().equals("Apple");
        assert testHandy.getBetriebssystem().equals("iOS");
        assert testHandy.getPreis() == 1199.00;
        assert testHandy.istAufLager() == true;

        if (testHandy.getModell().equals("iPhone 15 Pro") && testHandy.getHersteller().equals("Apple")
                && testHandy.getBetriebssystem().equals("iOS") && testHandy.getPreis() == 1199.00
                && testHandy.istAufLager() == true) {
            System.out.println("Der Test war erfolgreich");
        } else {
            System.out.println("Der Test ist fehlgeschlagen");
        }
    }
}
