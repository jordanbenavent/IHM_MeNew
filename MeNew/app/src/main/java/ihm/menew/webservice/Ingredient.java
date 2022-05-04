package ihm.menew.webservice;

public class Ingredient {

    private String original;
    private int amount;


    public Ingredient(String original, int amount) {
        this.original = original;
        this.amount = amount;
    }


    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
