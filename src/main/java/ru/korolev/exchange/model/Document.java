package ru.korolev.exchange.model;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/**
 * A class for storing values from a database.
 * @version 1.0
 * @author Nikita Korolev
 */
public class Document {
    private int id;
    private String view;
    private String organization;
    private Date date;
    private String description;
    private String patient;
    private String state;
}
