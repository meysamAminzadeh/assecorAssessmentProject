package utility;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *Diese Klasse dient dazu, die vom Benutzer in die
 * Eigenschaftendatei eingegebenen Informationen einmal
 * im Programm zu lesen und im gesamten Programm zu verwenden.
 *
 * @author Meysam Aminzadeh
 * on 8/6/2020.
 */
public final class InitationClass {

   public static String persistance_Instance;
   public static String file_name;
   public static String db_user;
   public static String db_pass;
   public static String db_driver;
   public static String db_connection;

    private InitationClass() { }


    /**
     * Diese Methode fügt Informationen aus einer vom Benutzer
     * erstellten Eigenschaftendatei in statische Felder ein,
     * um sie im gesamten Programm zu verwenden.
     */
    public static void readFromProperties(){

        try (InputStream is = InitationClass.class.getResourceAsStream("/config.properties");){
            Properties prop = new Properties();
            prop.load(is);
            persistance_Instance=prop.getProperty("persistance_Instance");
            file_name=prop.getProperty("file_name");
            db_user=prop.getProperty("db_user");
            db_pass=prop.getProperty("db_pass");
            db_driver=prop.getProperty("db_driver");
            db_connection=prop.getProperty("db_connection");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
