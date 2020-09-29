package DAO;

import com.opencsv.CSVWriter;
import input.InputFromFile;
import utility.ConnectionType;
import model.PersonBean;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Diese Klasse erbt von der Schnittstelle und implementiert
 * Methoden zum Arbeiten mit Daten aus der File.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */
public final class PersonDAOWithFile implements SqlScript {


    InputFromFile inputFromFile;

    public PersonDAOWithFile() {
        inputFromFile= new InputFromFile();
    }

    /**
     *
     * Wählen Sie alle Personen aus der File aus
     *
     * @return  List PersonBean
     */
    @Override
    public List<PersonBean> select() {

        return  inputFromFile.makeListFromInput();

    }
    /**
     *  Alle Personen, die mit der übermittelten ID übereinstimmen,
     *  werden aus der File ausgewählt.
     *
     * @param id
     * @return List PersonBean
     */
    @Override
    public List<PersonBean> selectById(int id) {

        return select().stream()
                .filter(personBean -> personBean.getId().equals(String.valueOf(id)))
                .collect(Collectors.toList());
    }
    /**
     *  Alle Personen, die mit der übermittelten farbe übereinstimmen,
     *  werden aus der File ausgewählt.
     *
     * @param color
     * @return List PersonBean
     */
    @Override
    public List selectByColor(String color) {

       return select().stream()
                .filter(personBean -> personBean.getFarbe().equals(color))
                .collect(Collectors.toList());

       }
    /**
     * Eingabe von Personeninformationen in File
     * @param o PersonBean
     * @return int
     */
    @Override
    public int insert(Object o) {
       try (CSVWriter writer
                     = new CSVWriter ((Writer) (inputFromFile.getConnection(ConnectionType.FILE_WRITE)));){

            List<PersonBean> perList = new ArrayList<>();
            perList.add((PersonBean)o);


           PersonBean p= (PersonBean) o;

           String[] s= {p.getName(),p.getFamily(),p.getZipcode()+" "+p.getCityName(),p.getId()};

           writer.writeNext(s);


        } catch (Exception e) {
            e.printStackTrace();
         return 0;

        }
        return 1;
    }
}