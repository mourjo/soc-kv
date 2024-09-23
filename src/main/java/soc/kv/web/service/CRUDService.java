package soc.kv.web.service;

import soc.kv.entities.KeyValue;
import soc.kv.web.repository.KVRepository;

public class CRUDService {

    private final KVRepository repository = new KVRepository();
    private final HistoryService historyService = new HistoryService();
    private final SearchService searchService = new SearchService();

    public KeyValue set(String key, String value) {
        var currentKeyValue = repository.fetch(key);
        boolean updated = updateIfChanged(currentKeyValue, key, value);
        if (updated) {
            historyService.saveHistory(key, value);
            searchService.index(key, value);
            return repository.fetch(key);
        }
        return currentKeyValue;
    }

    private boolean updateIfChanged(KeyValue current, String k, String v) {
        boolean mutated = current == null || !current.getValue().equals(v);

        if (mutated) {
            repository.upsert(k, v);
        }

        return mutated;
    }

    public KeyValue get(String key) {
        //
        return repository.fetch(key);
    }
}
