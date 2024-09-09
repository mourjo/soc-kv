package soc.kv.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import soc.kv.web.Launcher;
import soc.kv.web.dto.KVResponse;

class KVControllerTest {

    final static JavalinJackson jackson = new JavalinJackson();
    final Javalin app = Launcher.buildApp();

    @SneakyThrows
    public static KVResponse toKVResponse(Response response) {
        var typeRef = new TypeReference<KVResponse>() {
        };
        return jackson.fromJsonString(response.body().string(), typeRef.getType());
    }

    @Test
    void crudKV() {
        JavalinTest.test(app, (server, client) -> {
            var creationResponse = client.put("/current_location/amsterdam");
            Assertions.assertEquals(200, creationResponse.code());
            var creationResponseBody = toKVResponse(creationResponse);
            Assertions.assertEquals("current_location", creationResponseBody.key());
            Assertions.assertEquals("amsterdam", creationResponseBody.value());

            var updationResponse = client.put("/current_location/paris");
            Assertions.assertEquals(200, updationResponse.code());
            var updationResponseBody = toKVResponse(updationResponse);
            Assertions.assertEquals("current_location", updationResponseBody.key());
            Assertions.assertEquals("paris", updationResponseBody.value());

            var fetchResponse = client.get("/current_location");
            Assertions.assertEquals(200, fetchResponse.code());
            var fetchResponseBody = toKVResponse(fetchResponse);
            Assertions.assertEquals(updationResponseBody, fetchResponseBody);
        });
    }

}
