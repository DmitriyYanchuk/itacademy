package it.academy.entity.transport.impl;

import it.academy.entity.client.Client;
import it.academy.entity.base.BaseEntity;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;

public class Transport extends BaseEntity {

    private final Model model;
    private final TransportType transportType;
    private final Client client;

    public Transport(
            final Integer id,
            final Model model,
            final TransportType transportType,
            final Client client
    ) {
        super(id);
        this.model = model;
        this.transportType = transportType;
        this.client = client;
    }

    public final Model getModel() {
        return model;
    }

    public final TransportType getTransportType() {
        return transportType;
    }

    public final Client getClient() {
        return client;
    }
}
