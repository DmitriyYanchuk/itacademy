package it.academy.entity.transport;

import it.academy.entity.base.interfaces.Identifier;
import it.academy.entity.base.interfaces.Name;

public enum Model implements Identifier, Name {

    BMW_520D(1, "BMW 520d"),
    MERCEDES_BENZ_VIANO(2, "Mercedes-Benz Viano"),
    KAWASAKI_ZZR(3, "Kawasaki ZZR"),
    PORSCHE_PANAMERA(4, "Porsche Panamera"),
    AUDI_A7(5, "Audi A7"),
    VOLKSWAGEN_T6(6, "Volkswagen T6"),
    SUZUKI_GSX_S1000(7, "Suzuki GSX-S1000"),
    YAMAHA_FJR1300ES(8, "Yamaha FJR1300ES"),
    FORD_TRANSIT(9, "Ford Transit");

    private final Integer id;
    private final String model;


    Model(final Integer id, final String model) {
        this.id = id;
        this.model = model;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return model;
    }
}
