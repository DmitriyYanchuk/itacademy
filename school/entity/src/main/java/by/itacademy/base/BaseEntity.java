package by.itacademy.base;

import by.itacademy.base.interfaces.Identifier;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class BaseEntity implements Identifier {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;

    public BaseEntity(final Integer id) {
        this.id = id;
    }


    @Override
    public final Integer getId() {
        return id;
    }
}
