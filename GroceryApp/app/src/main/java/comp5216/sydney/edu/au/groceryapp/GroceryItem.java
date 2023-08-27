package comp5216.sydney.edu.au.groceryapp;

public class GroceryItem {
    private String itemName;
    private String date;

    public GroceryItem(String itemName, String date) {
        this.itemName = itemName;
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return itemName + " - " + date;
    }
}