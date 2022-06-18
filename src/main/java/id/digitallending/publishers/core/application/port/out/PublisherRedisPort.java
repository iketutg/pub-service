package id.digitallending.publishers.core.application.port.out;

public interface PublisherRedisPort {
    void sendMessage(String topic, Object message);
}
