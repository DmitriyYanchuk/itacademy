package by.itacademy.place;

import by.itacademy.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "street", length = 100, nullable = false)
    private String street;

    @Column(name = "building_number", length = 10, nullable = false)
    private String buildingNumber;

    @OneToMany(mappedBy = "address")
    private List<School> schools;

    public Address(
            final Integer id,
            final String city,
            final String street,
            final String buildingNumber,
            final List<School> schools
    ) {
        super(id);
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.schools = schools;
    }

    public final String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public final String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public final String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(final String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public final List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }
}
