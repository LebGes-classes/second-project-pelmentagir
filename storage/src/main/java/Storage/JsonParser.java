package Storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonParser {
    public static List<Dealer> parseJson(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray dealers = jsonObject.getAsJsonArray("dealer");
        List<Dealer> dealerList = new ArrayList<>();

        for (JsonElement dealerElement : dealers) {
            JsonObject dealerObject = dealerElement.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : dealerObject.entrySet()) {
                String dealerName = entry.getKey();
                JsonArray productsArray = entry.getValue().getAsJsonArray();
                List<Product> products = new ArrayList<>();

                for (JsonElement productElement : productsArray) {
                    JsonObject productObject = productElement.getAsJsonObject();
                    String productName = productObject.get("название").getAsString();
                    int productPrice = productObject.get("цена").getAsInt();
                    int productSize = productObject.get("размер").getAsInt();
                    products.add(new Product(productName, productPrice, productSize));
                }

                Dealer dealer = new Dealer(dealerName, products);
                dealerList.add(dealer);
            }
        }

        return dealerList;
    }
}
