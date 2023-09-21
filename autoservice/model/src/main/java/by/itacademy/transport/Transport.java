package by.itacademy.transport;

import by.itacademy.annotation.Parameter;

import java.util.Objects;

public class Transport {

    @Parameter(pattern = "^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$")
    private final String model;
    private final String type;

    public Transport(final String type, final String model) {
        this.model = model;
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Transport transport = (Transport) o;
        return model.equals(transport.model) && type.equals(transport.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, type);
    }
}
