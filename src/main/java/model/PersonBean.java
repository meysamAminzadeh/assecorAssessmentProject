package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *  Das Modell bezieht sich auf die vom Benutzer bereitgestellte
 *  Datei. Diese Klasse ist eigentlich eine Tabelle in der
 *  Oracle-Datenbank.
 *
 * @author Meysam Aminzadeh on 8/6/2020.
 */
@XmlRootElement
public final class PersonBean {

    private String id;
    private String name;
    private String family;
    private String zipcode;
    private String cityName;
    private String farbe;

    public PersonBean() {
    }

    public PersonBean(String farbe, String cityName, String zipcode, String family, String name, String id) {
        this.farbe = farbe;
        this.cityName = cityName;
        this.zipcode = zipcode;
        this.family = family;
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }


}
