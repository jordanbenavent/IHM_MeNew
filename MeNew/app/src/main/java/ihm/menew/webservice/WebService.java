package ihm.menew.webservice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebService {

    // Base de la requete
    private String url = "https://api.spoonacular.com/recipes/complexSearch?query=";
    private String apiKey = "ed12456ca0e74018a8b271cd04644885";
    private int numberOfRes = 2;

    Gson gson;

    public WebService() {
        gson = new Gson();
    }


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

    public Point makeRequest(String req) throws IOException {
        return gson.fromJson(run(req), Point.class);
    }

}
