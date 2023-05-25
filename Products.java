import org.json.simple.JSONObject;

public class Products {

    private String brand;
    private String description;
    private String category; //ENUM?
    private String name;
    private double value;
    private int quantity;

    public Products(JSONObject json) {
        this.name = (String) json.get("name");
        this.value = (Double) json.get("value");
        this.quantity = (int) json.get("quantity");
        this.brand = (String) json.get("brand");
        this.description = (String) json.get("description");
        this.category = (String) json.get("category");
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public double getvalue() {
        return value;
    }
    public void setvalue(double value) {
        this.value = value;
    }
    public int getquantity() {
        return quantity;
    }
    public void setquantity(int quantity) {
        this.quantity = quantity;
    }
    public String getbrand() {
        return brand;
    }
    public void setbrand(String brand) {
        this.brand = brand;
    }
    public String getdescription() {
        return description;
    }
    public void setdescription(String description) {
        this.description = description;
    }
    public String getcategory() {
        return category;
    }
    public void setcategory(String category) {
        this.category = category;
    }


}
