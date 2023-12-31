package yanchuk.autodiagnosticcenter.transport;

import yanchuk.autodiagnosticcenter.annotations.Parameter;

import java.util.Objects;

public class Transport {

    @Parameter(pattern = "^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$")
    private final String model;
    private final String type;

    public Transport (final String type, final String model) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transport transport = (Transport) o;
        return model.equals(transport.model) && type.equals(transport.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, type);
    }
}
