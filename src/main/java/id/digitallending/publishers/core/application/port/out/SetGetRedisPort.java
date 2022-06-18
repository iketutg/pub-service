package id.digitallending.publishers.core.application.port.out;

public interface SetGetRedisPort {
    void setData(String key, int haskey, Object value);
    void setDataTtl(String key, int haskey, Object value, long ttlSecond);
    Object getData(String key, int hashkey);
}

