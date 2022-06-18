package id.digitallending.publishers.infrastructure.redis;

import id.digitallending.publishers.core.application.port.out.SetGetRedisPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataRepositoryAdapter implements SetGetRedisPort {

    private final DataRepositoryService dataRepositoryService;

    @Override
    public void setData(String key, int haskey, Object value) {
        dataRepositoryService.saveData(key,haskey,value);
    }

    @Override
    public void setDataTtl(String key, int haskey, Object value, long ttlSecond) {
        dataRepositoryService.saveDataTtl(key,haskey,value,ttlSecond);
    }

    @Override
    public Object getData(String key, int hashkey) {
        return dataRepositoryService.getData(key,hashkey);
    }
}
