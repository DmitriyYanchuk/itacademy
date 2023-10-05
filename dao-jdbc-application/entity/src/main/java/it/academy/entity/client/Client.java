package it.academy.entity.client;

import it.academy.entity.base.BaseEntity;

public class Client extends BaseEntity {

    private final String firstName;
    private final String lastName;

    public Client(final Integer id, final String firstName, final String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final String getLastName() {
        return lastName;
    }
}
