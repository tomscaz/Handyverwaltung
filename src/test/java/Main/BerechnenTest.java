package Main;
import Smartphoneverwaltung.Smartphone;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BerechnenTest {
    @Test
    void checkeBerechnung() {
        //Prüfen ob unsere Rechenmethode richtig arbeitet
        ArrayList<Smartphone> testListe = new ArrayList<>();
        testListe.add(new Smartphone("iPhone 15", "Apple", "iOS", 500.00, true));
        testListe.add(new Smartphone("Galaxy S24", "Samsung", "Android", 300.00, true));
        testListe.add(new Smartphone("Pixel 8", "Google", "Android", 200.00, false));

        double testergebnis = Smartphone.berechneGesamtwert(testListe);

        assertEquals(1000.00, testergebnis);

        if (testergebnis == 1000.00) {
            System.out.println("Der Test war erfolgreich!");
        } else {
            System.out.println("Der Test ist fehlgeschlagen!");
        }
    }
}
