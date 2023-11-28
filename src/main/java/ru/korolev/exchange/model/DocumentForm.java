package ru.korolev.exchange.model;

import lombok.Data;

import java.sql.Date;

/**
 * Класс реализует колонки таблицы {@code document}
 * для заполнению по запросу POST
 */
@Data
public class DocumentForm {
    private long id;
    private String view;
    private String organization;
    private Date date;
    private String description;
    private String patient;
    private String status;
}
