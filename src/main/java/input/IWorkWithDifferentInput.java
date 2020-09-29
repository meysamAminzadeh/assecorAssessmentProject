package input;

import utility.ConnectionType;

import java.util.List;

/**
 * Diese Schnittstelle wird verwendet, um die Polymorphismusfunktion
 * zu verwenden, um eine Vielzahl von Eingaben in diesem Programm
 * (Datei, Datenbank usw.) zu verwenden.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */
public interface IWorkWithDifferentInput<E> {
     List makeListFromInput() ;
     E getConnection( ConnectionType connectionType);
}
