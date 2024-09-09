package soc.kv.web.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import soc.kv.web.dto.KVResponse;
import soc.kv.web.repository.KVRepository;

public class KVController {

    KVRepository repository = new KVRepository();

    public void putKeyValue(Context ctx) {
        String key = ctx.pathParam("key");
        String value = ctx.pathParam("value");

        repository.upsert(key, value);

        var savedKeyValue = repository.fetch(key);

        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

    public void getKeyGivenValue(Context ctx) {
        String key = ctx.pathParam("key");

        var savedKeyValue = repository.fetch(key);
        ctx.json(KVResponse.from(savedKeyValue));
        ctx.status(HttpStatus.OK);
    }

}
