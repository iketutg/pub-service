package id.digitallending.publishers.infrastructure.publisher;

public interface PubMessage {
    void SendMessage(String topic, Object message);
}
