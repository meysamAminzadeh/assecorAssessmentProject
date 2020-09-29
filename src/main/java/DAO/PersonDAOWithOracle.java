package DAO;

import input.InputFromOracleDB;
import model.PersonBean;
import utility.ConnectionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse erbt von der Schnittstelle und implementiert
 * Methoden zum Arbeiten mit Daten aus der Oracle-Datenbank.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */
public class PersonDAOWithOracle implements SqlScript{

    Connection connection;
    PreparedStatement statement;
    InputFromOracleDB inputFromOracleDB;

    public PersonDAOWithOracle() {
        inputFromOracleDB= new InputFromOracleDB();
       try {

           connection= (Connection) inputFromOracleDB.getConnection(ConnectionType.ORACLE);
        } catch (Exception e) {
        }
    }

    /**
     *
     * Wählen Sie alle Personen aus der Datenbank aus
     *
     * @return List PersonBean
     */

    @Override
    public List<PersonBean> select() {
        List<PersonBean> ls = new ArrayList<>();
        try {


            statement = connection.prepareStatement("select * from PERSON p , COLOR c where p.id=c.id");

        ResultSet result = statement.executeQuery();


        while (result.next()) {
            PersonBean psn = new PersonBean();
            psn.setId(result.getString("id"));
            psn.setName(result.getString("name"));
            psn.setFamily(result.getString("family"));
            psn.setCityName(result.getString("city_name"));
            psn.setZipcode(result.getString("zipcode"));
            psn.setFarbe(result.getString("color"));
            ls.add(psn);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    /**
     *  Alle Personen, die mit der übermittelten ID übereinstimmen,
     *  werden aus der Datenbank ausgewählt.
     *
     * @param id
     * @return List PersonBean
     */
    @Override
    public List<PersonBean> selectById(int id) {
        List<PersonBean> ls = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from PERSON p , COLOR c " +
                                                      "where p.id=c.id and p.id = ?");

        statement.setString(1, String.valueOf(id));
        ResultSet result = statement.executeQuery();
           while (result.next()) {
                PersonBean psn = new PersonBean();
                psn.setId(result.getString("id"));
                psn.setName(result.getString("name"));
                psn.setFamily(result.getString("family"));
                psn.setCityName(result.getString("city_name"));
                psn.setZipcode(result.getString("zipcode"));
                psn.setFarbe(result.getString("color"));
                ls.add(psn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }
    /**
     *  Alle Personen, die mit der übermittelten farbe übereinstimmen,
     *  werden aus der Datenbank ausgewählt.
     *
     * @param color
     * @return List PersonBean
     */
    @Override
    public List<PersonBean> selectByColor(String color) {
        List<PersonBean> ls = new ArrayList<>();
        try {

            statement = connection.prepareStatement("select * from PERSON p , COLOR c " +
                                                      "where p.id=c.id and c.color= ?");

            statement.setString(1, color);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                PersonBean psn = new PersonBean();
                psn.setId(result.getString("id"));
                psn.setName(result.getString("name"));
                psn.setFamily(result.getString("family"));
                psn.setCityName(result.getString("city_name"));
                psn.setZipcode(result.getString("zipcode"));
                psn.setFarbe(result.getString("color"));
                ls.add(psn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    /**
     * Eingabe von Personeninformationen in die Datenbank
     * @param o  PersonBean
     * @return int
     */
    @Override
    public int insert(Object o) {
        int id = 0;
        PersonBean personBean =(PersonBean) o;
        try {

            int pk_id = generateID("personsequence");

            if (connection == null || connection.isClosed()) {
                 inputFromOracleDB.getConnection(ConnectionType.ORACLE);
            }
            statement = connection.
                    prepareStatement("insert into person " +
                            "(person_id, id, name , family ,city_name ,zipcode  ) values (?,?,?,?,?,?) ");
            statement.setInt(1, pk_id);
            statement.setString(2,personBean.getId());
            statement.setString(3, personBean.getName());
            statement.setString(4, personBean.getFamily());
            statement.setString(5, personBean.getCityName());
            statement.setString(6, personBean.getZipcode());

            id = statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return id;


    }

    /**
     * Rufen Sie die neueste ID in der Personentabelle ab, um einen neuen Datensatz festzulegen
     *
     * @param sequenceName Empfängt die in der Datenbank entworfene Sequenzzeichenfolge als Eingabe
     * @return int
     */
    public int generateID(String sequenceName) {
        int id = 0;
        try {
            String query = "SELECT " + sequenceName + ".NEXTVAL FROM dual";


            if (connection == null || connection.isClosed()) {
                inputFromOracleDB.getConnection(ConnectionType.ORACLE);
            }

            statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            if (rs != null && rs.next()) {
                id = rs.getInt(1);
                rs.close();
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
}


