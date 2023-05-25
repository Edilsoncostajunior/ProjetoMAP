import org.json.simple.JSONObject;

public class Address {
    private String id;
    private String street;
    private String house_number;
    private String neighbourhood;
    private String postal_code;
    private String city;
    private String state;

    public Address(JSONObject json) {
        this.id = (String) json.get("id");
        this.street = (String) json.get("street");
        this.house_number = (String) json.get("house_number");
        this.neighbourhood = (String) json.get("neighbourhood");
        this.postal_code = (String) json.get("postal_code");
        this.city = (String) json.get("city");
        this.state = (String) json.get("state");
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address [street=" + street + ", house_number=" + house_number + ", neighbourhood=" + neighbourhood
                + ", postal_code=" + postal_code + ", city=" + city + ", state=" + state + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}