package bankingApp.SpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Address(String streetName, int streetNumber, String extension, String zipcode, String city, String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.extension = extension;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }
}