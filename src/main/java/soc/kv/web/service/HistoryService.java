package soc.kv.web.service;

import java.util.Comparator;
import java.util.List;
import soc.kv.entities.KeyValueLog;
import soc.kv.web.repository.HistoryRepository;

public class HistoryService {
    private HistoryRepository repository = new HistoryRepository();

    public void saveHistory(String key, String value) {
        repository.appendLog(key, value);
    }

    public List<KeyValueLog> getHistory(String key) {
        return repository
            .readLog(key)
            .stream()
            .sorted(Comparator.comparing(KeyValueLog::getLoggedAt).reversed())
            .toList();
    }
}
