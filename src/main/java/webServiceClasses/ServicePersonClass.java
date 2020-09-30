package webServiceClasses;

import DAO.SqlScript;
import factory.FactoryClass;
import utility.InitationClass;
import model.PersonBean;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Dies ist die Hauptklasse des Webdienstes. In dieser Klasse
 * wird die vom Benutzer gesendete URL überprüft und die entsprechenden
 * Methoden aufgerufen.Diese Klasse soll das Verhalten ändern, indem der
 * Eingabewert aus der vom Benutzer geschriebenen Konfigurationsdatei
 * übernommen und an die Factory-Klasse gesendet wird. Das heißt, abhängig
 * von der Art der Eingabe kann der Benutzer eine Verbindung zur zugehörigen
 * DAO-Klasse herstellen.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */

@Path("/person")
public class ServicePersonClass {
    FactoryClass factoryClass;
    List<PersonBean> personBeanList;

    public ServicePersonClass() {
        factoryClass = new FactoryClass();
        personBeanList = new ArrayList<>();
        InitationClass.readFromProperties();
    }


    /**
     * Diese Methode wird durch Senden der URL / Personen aufgerufen und gibt
     * die Liste der Personen zurück.
     *
     * @return Liste der Personen
     */
    @GET
    @Produces("application/json")
    @Path("/persons")
    public List<PersonBean> getAllPersonJson() {
        try {
            personBeanList = ((SqlScript) factoryClass.makeInstance(InitationClass.persistance_Instance))
                    .select();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personBeanList;
    }
    /**
     * Diese Methode wird durch Senden der URL /persons/{id} aufgerufen und gibt
     * die Liste der Personen zurück.Der Grund dafür, dass der Rückgabetyp dieser
     * Methode eine Liste von Personen ist, besteht darin, dass die vom Client
     * angegebene Datei dieselben IDs hat und möglicherweise mehrere identische
     * IDs in der Datei angegeben sind.
     *
     * @return Liste der Personen
     */

    @GET
    @Produces("application/json")
    @Path("/persons/{id}")
    public List<PersonBean> getPesonById(@PathParam("id") int personId) {
        try {
            personBeanList = ((SqlScript) factoryClass.makeInstance(InitationClass.persistance_Instance))
                    .selectById(personId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personBeanList;
    }

    /**
     * Diese Methode wird durch Senden der URL /persons/color/{color} aufgerufen und gibt
     * die Liste der Personen zurück.Der Grund dafür, dass der Rückgabetyp dieser
     * Methode eine Liste von Personen ist, besteht darin, dass die vom Client
     * angegebene Datei dieselben farbe hat und möglicherweise mehrere identische
     * farbe in der Datei angegeben sind.
     *
     * @return Liste der Personen
     */
    @GET
    @Produces("application/json")
    @Path("/persons/color/{color}")
    public List<PersonBean> getPesonByColor(@PathParam("color") String color) {
        try {
            personBeanList = ((SqlScript) factoryClass.makeInstance(InitationClass.persistance_Instance))
                    .selectByColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personBeanList;
    }

    /**
     *
     * Diese Methode dient zum Einfügen von Personeninformationen mithilfe der
     * Post-Methode in die Datei oder von Oracle mithilfe der URL / insertPerson
     *
     * @param personBean
     * @return
     */

    @POST
    @Path("/insertPerson")
    @Consumes("application/json")
    public Response insertUser2(PersonBean personBean) {
        String success = "NOT";
        try {
            int personId = ((SqlScript) factoryClass.makeInstance(InitationClass.persistance_Instance))
                    .insert(personBean);
            if (personId == 1) {
                success = "Successful";

                return Response.status(200).entity(success).build();
            } else {
                return Response.status(400).entity(success).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}







