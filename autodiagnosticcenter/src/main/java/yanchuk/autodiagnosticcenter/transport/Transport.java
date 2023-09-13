package yanchuk.autodiagnosticcenter.transport;

import yanchuk.autodiagnosticcenter.annotations.Parameter;

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
}
