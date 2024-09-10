package soc.kv.web.dto;

import java.util.List;
import soc.kv.entities.KeyValue;

public record SearchResponse(String query, List<KVResponse> matches) {

    public static SearchResponse from(String query, List<KeyValue> entities) {
        return new SearchResponse(
            query,
            entities
                .stream()
                .map(KVResponse::from)
                .toList()
        );
    }
}
