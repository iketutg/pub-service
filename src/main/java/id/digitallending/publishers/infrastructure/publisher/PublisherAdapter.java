package id.digitallending.publishers.infrastructure.publisher;

import id.digitallending.publishers.core.application.port.out.PublisherRedisPort;
import id.digitallending.publishers.infrastructure.publisher.dto.SpanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublisherAdapter implements PublisherRedisPort {
    @Autowired
    private PubMessage pubMessage;


    @Override
    public void sendMessage(String topic, Object message) {
        log.info("Send Message : Topic={} , Message={}",topic,message);
        pubMessage.SendMessage(topic,message);

    }
}
