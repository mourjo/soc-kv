package soc.kv.web.service;

import java.util.List;
import soc.kv.entities.KeyValue;
import soc.kv.entities.KeyValueLog;
import soc.kv.web.repository.KVRepository;

public class CRUDService {

    private final KVRepository repository = new KVRepository();
    private final HistoryService historyService = new HistoryService();

    public KeyValue set(String key, String value) {
        var currentKeyValue = repository.fetch(key);
        boolean updated = updateIfChanged(currentKeyValue, key, value);
        if (updated) {
            return repository.fetch(key);
        }
        return currentKeyValue;
    }

    private boolean updateIfChanged(KeyValue currentKeyValue, String key, String value) {
        boolean mutated = currentKeyValue == null || !currentKeyValue.getValue().equals(value);

        if (mutated) {
            repository.upsert(key, value);
            historyService.saveHistory(key, value);
        }

        return mutated;
    }

    public KeyValue get(String key) {
        return repository.fetch(key);
    }

    public List<KeyValueLog> getHistory(String key) {
        return historyService.getHistory(key);
    }
}
