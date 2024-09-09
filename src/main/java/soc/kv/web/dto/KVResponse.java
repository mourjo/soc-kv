package soc.kv.web.dto;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import soc.kv.entities.KeyValue;

public record KVResponse(String id, String key, String value, String createdAt, String updatedAt) {

    public static KVResponse from(KeyValue entity) {
        return new KVResponse(
            Long.toString(entity.getId()),
            entity.getKey(),
            entity.getValue(),
            ISO_DATE_TIME.format(entity.getCreatedAt()),
            ISO_DATE_TIME.format(entity.getUpdatedAt())
        );
    }
}
