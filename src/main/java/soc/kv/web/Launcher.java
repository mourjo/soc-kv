package soc.kv.web;

import io.javalin.Javalin;
import soc.kv.common.Environment;
import soc.kv.web.controller.KVController;

public class Launcher {

    public static void main(String[] args) {
        buildApp().start(Environment.getServerPort());
    }

    public static Javalin buildApp() {
        final KVController controller = new KVController();

        return Javalin.create()
            .put("/{key}/{value}", controller::putKeyValue)
            .get("/{key}", controller::getKeyGivenValue);
    }
}
