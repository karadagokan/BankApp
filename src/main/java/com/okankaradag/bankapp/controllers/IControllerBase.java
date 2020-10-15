package com.okankaradag.bankapp.controllers;

import java.util.Optional;

import org.springframework.ui.Model;

public interface IControllerBase<TEntity> {

    String getAll(Model model);
    String update(TEntity tEntity);
    String delete(Integer id);
    Optional<TEntity> findById(int id);
}
