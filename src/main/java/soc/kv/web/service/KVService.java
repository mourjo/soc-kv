package soc.kv.web.service;

import soc.kv.entities.KeyValue;
import soc.kv.web.repository.KVRepository;

public class KVService {

    KVRepository repository = new KVRepository();

    public KeyValue set(String key, String value) {
        repository.upsert(key, value);
        return repository.fetch(key);
    }

    public KeyValue get(String key) {
        return repository.fetch(key);
    }
}
