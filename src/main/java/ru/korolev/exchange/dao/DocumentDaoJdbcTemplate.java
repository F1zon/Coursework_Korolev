package ru.korolev.exchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.korolev.exchange.model.DocumentModel;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.*;

/**
 * Класс обрабатывает доавление информации в таблицу
 * и отправку иформации на {@code view}
 */
@Component
public class DocumentDaoJdbcTemplate implements DocumentDao{
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String SQL_BY_ID = "select * from document where id = :id";
    private final String SQL_BY_INSERT = "insert into document(view, organization," +
            "date, discription, patient, status)" +
            "values (:view, :organization, :date, :description, :patient, :status)";

    @Autowired
    public DocumentDaoJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Optional<DocumentModel> find(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<DocumentModel> list = namedParameterJdbcTemplate.query(SQL_BY_ID, params, documenRowMapper);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        if (list.size() > 1) {
            throw new RuntimeException("not 1 row!");
        }

        return  Optional.of(list.get(0));
    }

    private final RowMapper<DocumentModel> documenRowMapper = (ResultSet rs, int i) ->
            DocumentModel.builder()
                    .id(rs.getInt("id"))
                    .view(rs.getString("view"))
                    .organization(rs.getString("organization"))
                    .date(rs.getDate("date"))
                    .description(rs.getString("description"))
                    .patient(rs.getString("patient"))
                    .status(rs.getString("status"))
                    .build();

    @Override
    public void save(DocumentModel model) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", model.getId());
        params.put("view", model.getView());
        params.put("organization", model.getOrganization());
        params.put("date", model.getDate());
        params.put("description", model.getDescription());
        params.put("patient", model.getPatient());
        params.put("status", model.getStatus());
        namedParameterJdbcTemplate.update(SQL_BY_INSERT, params);
    }

    @Override
    public List<DocumentModel> findAll() {
        return null;
    }

    @Override
    public List<DocumentModel> findAllByStatus(String status) {
        return null;
    }
}
