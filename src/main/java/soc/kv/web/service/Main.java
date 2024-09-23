package soc.kv.web.service;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        var keyValueStore = new HashMap<String, String>();

        keyValueStore.put("workshop-name", "Graduate Bootcamp");
        var workshopName = keyValueStore.get("workshop-name");

        System.out.println("Name of the workshop is" + workshopName);
    }
}
