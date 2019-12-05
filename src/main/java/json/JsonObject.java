package json;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private StringBuilder json = new StringBuilder("{");
    private HashMap<String, Json> dict = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair: jsonPairs) {
            add(pair);
        }
    }

    @Override
    public String toJson() {
        for (String key: dict.keySet()) {
            json.append("'").append(key).append("': ").append(dict.get(key).toJson()).append(", ");
        }
        if (json.toString().length() > 1) {
            json.delete(json.length() - 2, json.length());
        }
        json.append("}");
        return json.toString();
    }

    public void add(JsonPair jsonPair) {
        dict.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return dict.get(name);
    }

    public JsonObject projection(String... names) {
        ArrayList<JsonPair> list = new ArrayList<>();
        for (String name: names) {
            list.add(new JsonPair(name, dict.get(name)));
        }
//        return new JsonObject(list);
    }

    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject(
                new JsonPair("name", new JsonString("Anna")),
                new JsonPair("age", new JsonNumber(18)),
                new JsonPair("status", new JsonNull()),
                new JsonPair("age", new JsonNumber(20))
        );

        String expectedJSON = "{'name': 'Anna', 'age': 20, 'status': null}";

        System.out.println(jsonObject.toJson());
    }
}
