package myProjectTest;

import model.PersonBean;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import webServiceClasses.ServicePersonClass;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


/**
 * Diese Klasse wurde zum Testen in zwei Oracle oder File geschrieben.
 * @author Meysam Aminzadeh
 * on 8/6/2020.
 */
public class PersonTest extends JerseyTest {

    /**
     * Jersy-Test-Konfigurationsmethode
     * @return Application
     */
    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        forceSet(TestProperties.CONTAINER_PORT, "0");
        return new ResourceConfig(ServicePersonClass.class);
    }

    /**
     * Dies ist ein Test, um alle Personendatensätze abzurufen
     */
    @Test
    public void fetchAllPerson() {


        Response output = target("person/persons").request().get();
        assertEquals("should return status 200", 200, output.getStatus());
        assertNotNull("Should return list", output.getEntity());
    }
    /**
     * Dies ist ein Test, um einen Personendatensatz anhand der ID zu erhalten
     */
    @Test
    public void FetchPersonById(){
        Response output = target("person/persons/1").request().get();
        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return Person", output.getEntity());
    }
    /**
     * DiDies ist ein Test, um einen Personendatensatz nach Farbe zu erhalten
     */
    @Test
    public void FetchPersonByColor(){
        Response output = target("person/persons/color/blau").request().get();
        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return Person", output.getEntity());
    }
    /**
     * Dies ist ein Fehlertest, um einen Personendatensatz anhand der Fehler-ID abzurufen
     */
    @Test
    public void fetchByFail_DoesNotHaveId(){
        Response output = target("person/persons/no-id-digit").request().get();
        assertEquals("Should return status 404", 404, output.getStatus());
    }
    /**
     * Dies ist ein Test zum Einfügen einer Person in File for Oracle
     */
  @Test
    public void insertNewPerson(){
        PersonBean personBean = new PersonBean(" blau"," DortMund"," 6414"," Aminzadeh"," Meysam"," 1");
        Response output = target("person/insertPerson")
                .request()
                .post(Entity.entity(personBean, MediaType.APPLICATION_JSON));

        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return notification", output.getEntity());
    }

  /*  @Test
    public void updatePerson(){
        PersonBean personBean = new PersonBean(" grün"," DortMund"," 6414"," Aminzadeh"," Meysam"," 1");
        Response output = target("person/upadatePerson")
                .request()
                .put(Entity.entity(personBean, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 204", 204, output.getStatus());
    }

    @Test
    public void deletePerson(){
        Response output = target("person/deletePerson/1").request().delete();
        assertEquals("Should return status 204", 204, output.getStatus());
    }

*/


}
