package soc.kv.web.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.List;
import soc.kv.entities.KeyValue;
import soc.kv.web.dto.HistoryResponse;
import soc.kv.web.dto.KVResponse;
import soc.kv.web.dto.SearchResponse;
import soc.kv.web.service.CRUDService;
import soc.kv.web.service.HistoryService;
import soc.kv.web.service.SearchService;

public class KVController {

    private static final CRUDService crudService = new CRUDService();
    private static final HistoryService historyService = new HistoryService();
    private static final SearchService searchService = new SearchService();

    public static void configureRoutes(Javalin apiDefinition) {
        apiDefinition
            .put("/{key}/{value}", KVController::setKeyValue)
            .get("/search", KVController::search)
            .get("/{key}", KVController::getValue)
            .get("/{key}/history", KVController::getValueHistory);
    }

    public static void setKeyValue(Context ctx) {
        String key = ctx.pathParam("key");
        String value = ctx.pathParam("value");

        var savedKeyValue = crudService.set(key, value);

        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

    public static void getValue(Context ctx) {
        String key = ctx.pathParam("key");
        var savedKeyValue = crudService.get(key);
        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

    public static void getValueHistory(Context ctx) {
        String key = ctx.pathParam("key");
        var log = historyService.getHistory(key);

        ctx.json(HistoryResponse.from(log));
        ctx.status(HttpStatus.OK);
    }

    public static void search(Context ctx) {
        String phrase = ctx.queryParam("q");
        List<KeyValue> matchingEntries = searchService.findByPhrase(phrase)
            .stream()
            .map(crudService::get)
            .toList();

        ctx.json(SearchResponse.from(phrase, matchingEntries));
        ctx.status(HttpStatus.OK);
    }
}
