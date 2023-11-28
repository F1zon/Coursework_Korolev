package ru.korolev.exchange.dao;

import ru.korolev.exchange.model.DocumentModel;

import java.util.List;

public interface DocumentDao extends CrudDao<DocumentModel>{
    List<DocumentModel> findAllByStatus(String status);
}
