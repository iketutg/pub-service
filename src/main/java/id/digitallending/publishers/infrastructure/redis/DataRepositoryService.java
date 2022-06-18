package id.digitallending.publishers.infrastructure.redis;

public interface DataRepositoryService {
    void saveData(String key,int hashkey,  Object value);
    void saveDataTtl(String key,int hashkey,  Object value, long ttlSecond);
    Object getData(String key, int hashkey);
    void publish(String message);
}
