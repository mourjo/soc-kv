package soc.kv.web.dto;

import java.util.List;
import soc.kv.entities.KeyValueLog;

public record HistoryResponse(List<LogEntry> entries) {

    public static HistoryResponse from(List<KeyValueLog> entities) {
        return new HistoryResponse(
            entities.stream()
                .map(LogEntry::from)
                .toList()
        );
    }
}
