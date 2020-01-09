package bankingApp.SpringBoot.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="adressId")
    private int addressId;
    private String streetName;
    private int streetNumber;
    private String extension;
    private String zipcode;
    private String city;
    private String country;
    @OneToOne
    private Company company;
    @OneToOne
    private RetailUser retailUser;

    public Address() {
    }

    public Address(String streetName, int streetNumber, String extension, String zipcode, String city, String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public Address(String streetName, int streetNumber, String extension, String zipcode, String city, String country, Company company, RetailUser retailUser) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.company = company;
        this.retailUser = retailUser;
    }


    public Address(String streetName, int streetNumber, String extension,  String zipcode, String city, String country, Company company) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.company = company;
    }

    public Address(String streetName, int streetNumber, String extension,  String zipcode, String city, String country, RetailUser retailUser) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.retailUser = retailUser;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

//    public Company getCompany() {
//        return company;
//    }
//
//    public RetailUser getRetailUser() {
//        return retailUser;
//    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }


    public void setCompany(Company company) {
        this.company = company;
    }

    public void setRetailUser(RetailUser retailUser) {
        this.retailUser = retailUser;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}