package ru.korolev.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Класс для хранения значений колонок таблицы {@code document}
 */
@Data
@Builder
public class DocumentModel {
    private long id;
    private String view;
    private String organization;
    private Date date;
    private String description;
    private String patient;
    private String status;
}
