package factory;

import DAO.PersonDAOWithFile;
import DAO.PersonDAOWithOracle;

/**
 * Diese Klasse verwendet ein Factory-Muster und wird
 * basierend auf der Art der Eingabe in eine Objektmethode
 * der gewünschten Klasse erstellt und verwendet.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */
public class FactoryClass<E> {

    /**
     * Diese Methode wurde verwendet, um das Objekt der gewünschten Klasse zu erstellen
     * @param inputName Erhält eine Zeichenfolge
     * @return Gibt ein Objekt zurück
     */
    public E makeInstance(String inputName) {

        switch (inputName) {

            case "file": {
                 return (E) new PersonDAOWithFile();
            }
            case "oracle": {
                return (E) new PersonDAOWithOracle();
            }

        }

    return null;
    }

}
