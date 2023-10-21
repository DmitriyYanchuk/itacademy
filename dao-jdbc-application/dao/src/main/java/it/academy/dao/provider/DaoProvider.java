package it.academy.dao.provider;

import it.academy.dao.Dao;
import it.academy.entity.base.BaseEntity;

public interface DaoProvider {

    <E extends BaseEntity, D extends Dao<E>> D provide(Class<E> entityClass);


}
