package ihm.menew.factories;

import java.io.IOException;

import ihm.menew.webservice.Point;
import ihm.menew.webservice.PointIngredients;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebServiceIngredientFactory extends AbstractWebServiceFactory {

    private String url = "https://api.spoonacular.com/recipes/";
    private String urlEnd = "/information?includeNutrition=false";

    public WebServiceIngredientFactory() {
        super();
    }

    @Override
    public String run(String request) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String completeReq = url + request + urlEnd + "&apiKey=" + apiKey;

        Request req = new Request.Builder()
                .url(completeReq)
                .build();

        try (Response response = client.newCall(req).execute()) {
            return response.body().string();
        }

    }

    public PointIngredients makeRequestIngredients(String request) throws IOException{
        return gson.fromJson(run(request), PointIngredients.class);
    }

    @Override
    Point makeRequest(String request) throws IOException {
        return null;
    }
}
