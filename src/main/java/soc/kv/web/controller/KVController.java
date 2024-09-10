package soc.kv.web.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import soc.kv.web.dto.KVResponse;
import soc.kv.web.service.KVService;

public class KVController {

    KVService service = new KVService();

    public void putKeyValue(Context ctx) {
        String key = ctx.pathParam("key");
        String value = ctx.pathParam("value");

        var savedKeyValue = service.set(key, value);

        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

    public void getKeyGivenValue(Context ctx) {
        String key = ctx.pathParam("key");
        var savedKeyValue = service.get(key);
        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

}
