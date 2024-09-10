package soc.kv.web.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import soc.kv.web.dto.HistoryResponse;
import soc.kv.web.dto.KVResponse;
import soc.kv.web.service.CRUDService;
import soc.kv.web.service.HistoryService;

public class KVController {

    private final CRUDService crudService = new CRUDService();
    private final HistoryService historyService = new HistoryService();

    public void setKeyValue(Context ctx) {
        String key = ctx.pathParam("key");
        String value = ctx.pathParam("value");

        var savedKeyValue = crudService.set(key, value);

        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

    public void getValue(Context ctx) {
        String key = ctx.pathParam("key");
        var savedKeyValue = crudService.get(key);
        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

    public void getValueHistory(Context ctx) {
        String key = ctx.pathParam("key");
        var log = historyService.getHistory(key);

        ctx.json(HistoryResponse.from(log));
        ctx.status(HttpStatus.OK);
    }
}
