package utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse speichert die Informationen zu Farben
 * und deren IDs auf einer Map, um sie im Programm
 * einfach verwenden zu können. Dieser Abschnitt ist
 * für den Fall gedacht, dass wir eine Datei oder
 * Konsole verwenden möchten. Bei Verwendung von
 * Oracle wird eine Tabelle für Farben bereitgestellt.
 *
 * @author Meysam Aminzadeh on 8/4/2020.
 */
public final class FarbeData {

    private FarbeData(){}

    public static Map<String, String> farbeInformation = createData();

    /**
     * Diese Methode fügt Informationen zu Farben und deren IDs auf eine Map.
     *
     * @return Map<String, String
     */
    private static Map<String, String> createData() {
        farbeInformation= new HashMap<>();
        farbeInformation.put("1", "blau");
        farbeInformation.put("2", "grün");
        farbeInformation.put("3", "violett");
        farbeInformation.put("4", "rot");
        farbeInformation.put("5", "gelb");
        farbeInformation.put("6", "türkis");
        farbeInformation.put("7", "weiß");


        return farbeInformation;

    }




}
