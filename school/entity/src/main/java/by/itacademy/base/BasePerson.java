package by.itacademy.base;

import by.itacademy.base.interfaces.PersonalData;
import jakarta.persistence.Column;

public class BasePerson extends BaseEntity implements PersonalData {

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    public BasePerson(final Integer id, final String firstName, final String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public final String getFirstName() {
        return firstName;
    }

    @Override
    public final String getLastName() {
        return lastName;
    }

    @Override
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}
