package it.academy.client;

public class Client {

    private final String firstName;

    private final String lastName;

    public Client(final String firstName, final String lastName) {
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
