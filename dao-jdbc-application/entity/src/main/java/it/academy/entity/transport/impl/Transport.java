package it.academy.entity.transport.impl;

import it.academy.entity.client.Client;
import it.academy.entity.base.BaseEntity;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transport transport = (Transport) o;
        return model == transport.model && transportType == transport.transportType && Objects.equals(client, transport.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, transportType, client);
    }
}
