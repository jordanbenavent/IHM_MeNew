package ihm.menew.webservice;

import java.util.ArrayList;

public class PointIngredients {

    private ArrayList<Ingredient> extendedIngredients;
    private int id;
    private boolean cheap;
    private String sourceUrl;
    private int readyInMinutes;

    public PointIngredients(ArrayList<Ingredient> extendedIngredients, int id, boolean cheap, String sourceUrl, int readyInMinutes) {
        this.extendedIngredients = extendedIngredients;
        this.id = id;
        this.cheap = cheap;
        this.sourceUrl = sourceUrl;
        this.readyInMinutes = readyInMinutes;
    }

    public ArrayList<Ingredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public ArrayList<String> ingredientsToArray(){
        ArrayList<String> ingredients = new ArrayList<>();
        for (Ingredient i : this.extendedIngredients){
            ingredients.add(i.getOriginal());
        }
        return ingredients;
    }

    @Override
    public String toString() {
        return "PointIngredients{" +
                "extendedIngredients=" + extendedIngredients +
                ", id=" + id +
                ", cheap=" + cheap +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", readyInMinutes=" + readyInMinutes +
                '}';
    }

    public void setExtendedIngredients(ArrayList<Ingredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }
}
