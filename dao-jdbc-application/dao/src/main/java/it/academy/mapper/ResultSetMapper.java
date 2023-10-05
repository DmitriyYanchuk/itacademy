package it.academy.mapper;

import java.sql.ResultSet;

public interface ResultSetMapper <T> {

    T map(ResultSet resultSet) throws ResultSetMapperException;
}
