package Smartphoneverwaltung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Handyverwaltung extends JFrame {

    private JPanel panelHaupt;
    private JTextField modellFeld;
    private JComboBox herstellerBox;
    private JComboBox osBox;
    private JTextField preisFeld;
    private JCheckBox lagerCheck;
    private JButton speichernButton;
    private JComboBox filterBox;
    private JButton auswertenButton;
    private JList liste;
    private JLabel ausgabeLabel;
    private JButton auswahlLoeschenButton;
    private JButton allesLoeschenButton;

    //Hier speichern wir alle angelegten Smartphones
    private ArrayList<Smartphone> alleHandys = new ArrayList<>();

    //für unsere Filterlogik damit das richtige angezeigt wird und aus der ArrayList geholt wird
    private DefaultListModel<Smartphone> anzeige = new DefaultListModel<>();

    public Handyverwaltung() {
        setContentPane(panelHaupt);
        setTitle("Smartphone-Verwaltung — Tom & Moritz");
        setSize(700, 620);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        //Auswahlmoeglichkeiten für Eingabe
        herstellerBox.addItem("Apple");
        herstellerBox.addItem("Samsung");
        herstellerBox.addItem("Google");
        herstellerBox.addItem("Xiaomi");
        herstellerBox.addItem("Huawei");

        osBox.addItem("iOS");
        osBox.addItem("Android");
        osBox.addItem("HarmonyOS");

        //Filterboxen
        filterBox.addItem("Alle");
        filterBox.addItem("Apple");
        filterBox.addItem("Samsung");
        filterBox.addItem("Google");
        filterBox.addItem("Xiaomi");
        filterBox.addItem("Huawei");

        liste.setModel(anzeige);

        initObjekte();
        aktualisiereAnzeige();
        auswerten();

        //Buttons mit unseren Methoden verknüpft
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smartphoneHinzufuegen();
            }
        });
        auswertenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                auswerten();
            }
        });
        auswahlLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                auswahlEntfernen();
            }
        });
        allesLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allesLoeschen();
            }
        });
        filterBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aktualisiereAnzeige();
            }
        });
    }


    //Erstellt aus den Eingaben ein neues Smartphone und speichert die in der Liste
    private void smartphoneHinzufuegen() {
        String modell = modellFeld.getText().trim();

        //Exception Handling für Modellnamen
        if (modell.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte gib einen Modellnamen ein!", "Fehler", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //Exception Handling für den Preis
        try {
            //Komma durch Punkt ersetzen damit bspw. 799,99 auch funktioniert
            double preis = Double.parseDouble(preisFeld.getText().trim().replace(",", "."));

            if (preis < 0) {
                JOptionPane.showMessageDialog(this, "Der Preis darf nicht negativ sein!", "Fehler", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String hersteller = (String) herstellerBox.getSelectedItem();
            String os = (String) osBox.getSelectedItem();
            boolean aufLager = lagerCheck.isSelected();

            //Neues Objekt erzeugen und in der Liste speichern
            Smartphone neu = new Smartphone(modell, hersteller, os, preis, aufLager);
            alleHandys.add(neu);

            //Eingabefelder wieder leeren
            modellFeld.setText("");
            preisFeld.setText("");
            lagerCheck.setSelected(false);

            aktualisiereAnzeige();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte einen gültigen Preis eingeben (z.B. 799.99)!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }


    //Unsere Filterlogik
    private void aktualisiereAnzeige() {
        anzeige.clear();

        String gewaehlterHersteller = (String) filterBox.getSelectedItem();

        //Nur passende Smartphones anzeigen (Filter nach Hersteller)
        for (Smartphone s : alleHandys) {
            if (gewaehlterHersteller.equals("Alle") || s.getHersteller().equals(gewaehlterHersteller)) {
                anzeige.addElement(s);
            }
        }
    }


    //Berechnet Gesamtwert und Durchschnittspreis aller Smartphones und zeigt sie an
    private void auswerten() {
        if (alleHandys.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Es sind keine Smartphones vorhanden.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        double gesamtwert = Smartphone.berechneGesamtwert(alleHandys);
        double durchschnitt = gesamtwert / alleHandys.size();

        //schön formatiert ausgeben
        ausgabeLabel.setText("Anzahl: " + alleHandys.size()
                + "   |   Gesamtwert: " + String.format("%.2f", gesamtwert) + " €"
                + "   |   Durchschnitt: " + String.format("%.2f", durchschnitt) + " €");
    }


    //Entfernt das in der Liste ausgewählte Smartphone
    private void auswahlEntfernen() {
        Smartphone ausgewaehlt = (Smartphone) liste.getSelectedValue();
        if (ausgewaehlt == null) {
            JOptionPane.showMessageDialog(this, "Bitte zuerst ein Smartphone auswählen!", "Fehler", JOptionPane.WARNING_MESSAGE);
            return;
        }
        alleHandys.remove(ausgewaehlt);
        aktualisiereAnzeige();
    }


    //Löscht alle Smartphones aus der Liste
    private void allesLoeschen() {
        alleHandys.clear();
        aktualisiereAnzeige();
        ausgabeLabel.setText("Auswertung: -");
    }


    public void initObjekte() {
        alleHandys.add(new Smartphone("iPhone 15 Pro", "Apple", "iOS", 1199.00, true));
        alleHandys.add(new Smartphone("Galaxy S25+", "Samsung", "Android", 899.00, true));
        alleHandys.add(new Smartphone("Pixel 8", "Google", "Android", 699.00, false));
        alleHandys.add(new Smartphone("Redmi Note 13", "Xiaomi", "Android", 299.00, true));
        alleHandys.add(new Smartphone("P60 Pro", "Huawei", "HarmonyOS", 1099.00, false));
    }



    public static void main(String[] args) {
            new Handyverwaltung();
            JOptionPane.showMessageDialog(null,
                    "Willkommen in der Smartphone-Verwaltung!\n"
                            + "Hier kannst du Handys anlegen, sortieren und schauen wie viel alle insgesamt Wert sind.",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            // \n ist ein neuer Absatz
        }
    }
