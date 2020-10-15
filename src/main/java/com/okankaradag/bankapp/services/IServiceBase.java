package com.okankaradag.bankapp.services;

import java.util.List;
import java.util.Optional;

public interface IServiceBase<TEntity> {

    List<TEntity> getAll();

    Optional<TEntity> findById(Integer id);

    void save(TEntity tEntity);

    void delete(Integer id);
}
