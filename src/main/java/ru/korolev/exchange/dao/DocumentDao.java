package ru.korolev.exchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.korolev.exchange.kafka.KafkaSendler;
import ru.korolev.exchange.model.Document;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

@Component
/**
 * Description of the Data Access Object between the database and the system.
 * @author Nikita Korolev
 * @version 1.0
 */
public class DocumentDao {
    private final JdbcTemplate jdbcTemplate;
    private final KafkaSendler sendler;
    private int USER_ID = 0;

    @Autowired
    public DocumentDao(JdbcTemplate jdbcTemplate, KafkaSendler sendler) {
        this.jdbcTemplate = jdbcTemplate;
        this.sendler = sendler;
    }

    /**
     * The method queries all rows from
     * the database and returns a list of {@link Document} models.
     * @return List<Document> All row db
     */
    public List<Document> index() {
        return jdbcTemplate.query("select * from docs", new BeanPropertyRowMapper<>(Document.class));
    }

    /**
     * The method requests a specific
     * string from the database and returns the finished {@link Document} model.
     * @param id int - id rows in the database.
     * @return Document.class - Assembled model Document from the database.
     */
    public Document show(int id) {
        return jdbcTemplate.query("select * from docs where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Document.class)).stream().findAny().orElse(null);
    }

    /**
     * The method saves the passed model {@link Document}.
     * After adding it to the database, the method passes
     * the transferred model for verification to Kafka
     * and writes a new record to the database with the received status.
     * @param document {@link Document} User-filled model Document.
     * @throws InterruptedException Error handler.
     */
    public void save(Document document) throws InterruptedException {
        jdbcTemplate.update("insert into docs values (?, ?, ?, ?, ?, ? ,?)",
                ++USER_ID ,document.getView(), document.getOrganization(), LocalDateTime.now(), document.getDescription(),
                document.getPatient(), "NEW");

        Thread.sleep(3000);
        document = sendler.sendMassage(USER_ID, DocumentDao.this);
        jdbcTemplate.update("insert into docs values (?, ?, ?, ?, ?, ? ,?)",
                USER_ID ,document.getView(), document.getOrganization(), LocalDateTime.now(), document.getDescription(),
                document.getPatient(), document.getState());
    }
}
