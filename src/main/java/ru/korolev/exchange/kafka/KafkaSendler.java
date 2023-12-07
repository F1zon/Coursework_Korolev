package ru.korolev.exchange.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.korolev.exchange.dao.DocumentDao;
import ru.korolev.exchange.model.Document;

@Component
@Slf4j
/**
 * The class that handles receiving and sending messages.
 *
 * @author Nikita Korolev
 * @version 1.0
 */
public class KafkaSendler {
    private KafkaTemplate<String, String> template;
    @Autowired
    public KafkaSendler(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    /**
     * The method checks the correctness of the data entered by the user.
     * @param id int id User requests.
     * @param dao {@link DocumentDao}
     * @return {@link Document} Verified model Document with updated status.
     * @throws InterruptedException Error handler.
     */
    public Document sendMassage(int id, DocumentDao dao) throws InterruptedException {
        log.info("Sending : {}", id);
        log.info("-----------------------------");
        String answer = "Adopted";

        Document document = dao.show(id);
        if (document.getPatient().isEmpty()) answer = "Rejected";
        if (document.getDescription().isEmpty()) answer = "Rejected";
        if (document.getOrganization().isEmpty()) answer = "Rejected";
        if (document.getView().isEmpty()) answer = "Rejected";
        if (document.getDate().toString().isEmpty()) answer = "Rejected";
        if (!document.getState().equals("NEW")) answer = "Rejected";
        document.setState(answer);

        return document;
    }
}
