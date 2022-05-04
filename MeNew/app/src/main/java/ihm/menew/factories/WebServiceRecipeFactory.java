package ihm.menew.factories;

import java.io.IOException;

import ihm.menew.webservice.Point;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebServiceRecipeFactory extends AbstractWebServiceFactory{

    // Base de la requete
    private String url = "https://api.spoonacular.com/recipes/complexSearch?query=";

    public WebServiceRecipeFactory(){
        super();
    }

    @Override
    public String run(String request) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String completeReq = url + request + "&number="+numberOfRes+ "&apiKey="+apiKey;

        Request req = new Request.Builder()
                .url(completeReq)
                .build();

        try (Response response = client.newCall(req).execute()) {
            return response.body().string();
        }
    }

    @Override
    public Point makeRequest(String request) throws IOException {
        return gson.fromJson(run(request), Point.class);
    }
}
