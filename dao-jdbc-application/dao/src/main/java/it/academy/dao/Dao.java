package it.academy.dao;

public interface Dao <T> {

    Integer create(T entity) throws DaoException;

    T read(Integer id) throws DaoException;

    Integer update(T entity) throws DaoException;

    Integer delete(T entity) throws DaoException;
}
