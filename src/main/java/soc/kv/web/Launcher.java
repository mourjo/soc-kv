package soc.kv.web;

import io.javalin.Javalin;
import soc.kv.common.Environment;
import soc.kv.web.controller.KVController;

public class Launcher {

    public static void main(String[] args) {
        buildApp().start(Environment.getServerPort());
    }

    public static Javalin buildApp() {
        Javalin app = Javalin.create();
        KVController.configureRoutes(app);
        return app;
    }
}
