import org.json.simple.JSONObject;

public class Clients {
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private Address address;

    public Clients(JSONObject json) {
        this.id = (String) json.get("id");
        this.name = (String) json.get("name");
        this.cpf = (String) json.get("cpf");
        this.email = (String) json.get("email");
        this.password = (String) json.get("password");
        this.address = new Address((JSONObject) json.get("address"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Clients [name=" + name + ", cpf=" + cpf + ", email=" + email + ", password=" + password + ", address="
                + address + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}