package ru.korolev.exchange.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    Optional<T> find(long id);
    void save(T model);
    List<T> findAll();
}
