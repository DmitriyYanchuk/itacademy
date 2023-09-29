package it.academy.transport.impl;

import it.academy.client.Client;
import it.academy.transport.Model;
import it.academy.transport.TransportType;

public class Transport {

    private final Model model;
    private final TransportType transportType;
    private final Client client;


    public Transport(final Model model, final TransportType transportType, final Client client) {
        this.model = model;
        this.transportType = transportType;
        this.client = client;
    }

    @Override
    public final String toString() {
        return "<< Model = " + model.getModel() + "; " +
                "Transport Type = " + transportType.toString() + "; " +
                "Client first name = " + client.getFirstName() + "; " +
                "Client last name = " + client.getLastName() + " >>";
    }
}
