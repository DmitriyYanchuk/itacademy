package it.academy.entity.base;

import it.academy.entity.base.interfaces.Identifier;

public class BaseEntity implements Identifier {

    private final Integer id;

    public BaseEntity(final Integer id) {
        this.id = id;
    }

    @Override
    public final Integer getId() {
        return id;
    }
}
