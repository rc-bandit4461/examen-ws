package enset.bdcc.webservices.compteoperationsservice.service;


import com.fasterxml.jackson.databind.JsonNode;
import enset.bdcc.webservices.compteoperationsservice.entities.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaProducerService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Operation operation) {
        log.info("Operation id: " + operation.getId() + ", type" + operation.getType());
//        log.info("Sending submission='{}' to topic='{}'", submission.toString(), topic);
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", operation.getId());
//        map.put("date", operation.getDate());
//        map.put("montant", operation.getMontant());
//        map.put("type", operation.getType());
//        map.put("compteId", operation.getCompte().getId());
        kafkaTemplate.send(topic, operation);
    }
}
