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
public class DocumentDao {
    private final JdbcTemplate jdbcTemplate;
    private final KafkaSendler sendler;
    private int USER_ID = 1;

    @Autowired
    public DocumentDao(JdbcTemplate jdbcTemplate, KafkaSendler sendler) {
        this.jdbcTemplate = jdbcTemplate;
        this.sendler = sendler;
    }

    public List<Document> index() {
        return jdbcTemplate.query("select * from docs", new BeanPropertyRowMapper<>(Document.class));
    }

    public Document show(int id) {
        return jdbcTemplate.query("select * from docs where id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Document.class)).stream().findAny().orElse(null);
    }

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
