package ihm.menew.factories;

import com.google.gson.Gson;

import java.io.IOException;

import ihm.menew.webservice.Point;

public abstract class AbstractWebServiceFactory {
    protected final String apiKey = "ed12456ca0e74018a8b271cd04644885";
    protected int numberOfRes = 5;
    protected Gson gson;

    public AbstractWebServiceFactory(){
        this.gson = new Gson();
    }

    abstract String run(String request) throws IOException;
    abstract Point makeRequest(String request) throws IOException;

}
