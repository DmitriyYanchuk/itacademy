package it.academy.entity.transport;

import it.academy.entity.base.interfaces.Identifier;
import it.academy.entity.base.interfaces.Name;

public enum TransportType implements Identifier, Name {

    MOTORBIKE(1, "motorbike"),
    AUTOMOBILE(2, "automobile"),
    MINIBUS(3, "minibus");

    private final Integer id;
    private final String transportType;

    TransportType(final Integer id, final String transportType) {
        this.id = id;
        this.transportType = transportType;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return transportType;
    }
}
