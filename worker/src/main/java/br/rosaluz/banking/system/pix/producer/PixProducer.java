package br.rosaluz.banking.system.pix.producer;

import br.rosaluz.banking.system.pix.model.Pix;
import br.rosaluz.banking.system.pix.producer.dto.PixMessageDTO;
import br.rosaluz.banking.system.pix.producer.dto.converter.PixToPixMessageDTO;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PixProducer {

    private static final Logger logger = LoggerFactory.getLogger(PixProducer.class);
    private final String topic;
    private final KafkaTemplate<String, PixMessageDTO> kafkaTemplate;

    public PixProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, PixMessageDTO> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Pix pix){
        var pixMessageDTO = PixToPixMessageDTO.convert(pix);
        kafkaTemplate.send(topic, pixMessageDTO).addCallback(
                success -> logger.info("Messagem send" + success.getProducerRecord().value()),
                failure -> logger.info("Message failure" + failure.getMessage())
        );
    }
}
