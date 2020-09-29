package input;

import utility.FarbeData;
import com.opencsv.CSVReader;
import model.PersonBean;
import utility.ConnectionType;
import utility.InitationClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse wird verwendet, um eine Verknüpfung
 * mit der Datei herzustellen und die in der Datei
 * gelesenen Informationen in eine Liste umzuwandeln.
 * Dies hilft uns, die Informationen in der Liste in
 * der Anwendung einfach zu verwenden.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */


public class InputFromFile implements IWorkWithDifferentInput {

    /**
     * Diese Methode liest Informationen aus einer Datei und fügt
     * sie in eine Liste von Personen ein.
     * Der wichtige Punkt bei dieser Methode ist, dass die vom
     * Benutzer bereitgestellte Datei auch eine schlechte
     * Datenmenge aufwies. Dies bedeutet, dass alle Daten
     * nicht in einem bestimmten Format vorliegen. Daher
     * haben wir versucht, diese falschen Daten aus unserer
     * Liste zu entfernen und nicht anzuzeigen.
     *
     * @return List PersonBean
     */

    @Override
    public List<PersonBean> makeListFromInput( ) {
         List<PersonBean> ls = new ArrayList<>();

        CSVReader reader=null;
        try {

            String[] nextLine;
            reader = new CSVReader((Reader) getConnection(ConnectionType.FILE_READ));
            while ((nextLine = reader.readNext()) != null) {
                PersonBean psn = new PersonBean();
                  if(nextLine.length==4) {
                    psn.setFamily(nextLine[0]);
                    psn.setName(nextLine[1]);
                    String[] sub = nextLine[2].split(" ");
                    if(sub.length>2) {
                        psn.setZipcode(sub[1]);
                        String conc = "";
                        for (int i = 2; i < sub.length; i++)
                            conc = conc + " " + sub[i];
                        psn.setCityName(conc);
                    }
                    else{
                        psn.setZipcode(sub[0]);
                        psn.setCityName(sub[1]);
                    }

                    psn.setId(nextLine[3].trim());

                    psn.setFarbe(FarbeData.farbeInformation.get(nextLine[3].trim()));

                    ls.add(psn);
                }
            }
        } catch (Exception e) {
          e.printStackTrace();
            System.out.println(" File is damage ");
        }
        return ls;


    }

    /**
     *Diese Methode wird für die Kommunikation mit der Datei verwendet.
     * Abhängig von der Art der Eingabe kann diese Verbindung zum
     * Schreiben in die Datei oder zum Lesen aus der Datei verwendet
     * werden. Abhängig von dieser Art der Eingabe ändert sich auch
     * die Art der Ausgabe.
     *
     * @param connectionType  Gibt die Art der Arbeit mit der Datei an, ob sie in
     *                        die Datei geschrieben oder gelesen werden soll.
     *
     * @return  Gibt den Objekttyp an, der aus der Datei gelesen oder in die
     * Datei geschrieben werden soll
     */

    @Override
    public Object getConnection(ConnectionType connectionType) {

        switch(connectionType){
            case FILE_READ:{
                InputStream is = PersonBean.class.getResourceAsStream("/"+InitationClass.file_name);
                Reader fr = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                return fr;
            }

            case FILE_WRITE:{
                String fileName = "src/main/resources/"+ InitationClass.file_name;
                try {
                    return new FileWriter(fileName, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }


}
