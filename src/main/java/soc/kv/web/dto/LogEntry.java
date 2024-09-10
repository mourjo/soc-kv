package soc.kv.web.dto;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import soc.kv.entities.KeyValueLog;

public record LogEntry(String key, String value, String loggedAt) {

    public static LogEntry from(KeyValueLog entity) {
        return new LogEntry(entity.getKey(), entity.getValue(),
            ISO_DATE_TIME.format(entity.getLoggedAt()));
    }
}
