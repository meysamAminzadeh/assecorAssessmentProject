package input;

import utility.ConnectionType;
import utility.InitationClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Diese Klasse funktioniert mit der Oracle-Datenbank.
 * Diese Klasse wird verwendet, um eine Verbindung zur
 * Oracle-Datenbank herzustellen und eine Liste mit
 * Informationen zu erstellen.
 *
 * @author Meysam Aminzadeh on 8/8/2020.
 */
public class InputFromOracleDB implements IWorkWithDifferentInput {

    /**
     * Diese Methode ist in eine Liste eingebettet, um die von
     * der Oracle Bank gelesenen Daten zu konvertieren. Sie wird
     * von ihrer höheren Klasse geerbt, da wir die Daten beim
     * Lesen von Daten aus der direkten Bank in eine Liste
     * konvertiert haben - in der DAO-Klasse - und daher diese
     * Methode verwenden müssen war nicht in dieser Klasse.
     *
     * @return Liste der Person
     */
    @Override
    public List makeListFromInput() {
        return null;
    }

    /**
     * Diese Methode dient zur Kommunikation mit einer Datenbank.
     *
     * @param connectionType
     * @return connection
     */
    @Override
    public Object getConnection(ConnectionType connectionType) {
        Connection connection=null;
        try {
            Class.forName(InitationClass.db_driver);
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.
                        getConnection(InitationClass.db_connection,
                                InitationClass.db_user, InitationClass.db_pass);
                connection.setAutoCommit(false);
            }


        } catch (Exception e) {
            System.out.println("connection is null ");
          e.printStackTrace();
        }
        return connection;
    }
}
