package be.intecbrussel.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Brewery {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String address;
    private int postcode;


}
