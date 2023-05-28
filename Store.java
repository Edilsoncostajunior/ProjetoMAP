import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Store {
    private String name;
    private String email;
    private String password;
    private String cpfCnpj;
    private Address address;

    public Store(String name, String email, String password, String cpfCnpj, Address address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCpfCnpj() {
        return diferencaCpfCnpj();
        //Talvez aqui mude...
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String diferencaCpfCnpj() {

        if (cpfCnpj.length() == 11) {
            return cpfCnpj.substring(0, 3) + "." + cpfCnpj.substring(3, 6) + "." + cpfCnpj.substring(6, 9) + "-" + cpfCnpj.substring(9);
        }

        if (cpfCnpj.length() == 14) {
            return cpfCnpj.substring(0, 2) + "." + cpfCnpj.substring(2, 5) + "." + cpfCnpj.substring(5, 8) + "/" + cpfCnpj.substring(8, 11) + "-" + cpfCnpj.substring(11);
        }

        return cpfCnpj;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store [name=" + name + ", email=" + email + ", password=" + password + ", cpfCnpj=" + diferencaCpfCnpj()
                + ", address=" + address + "]";
    }

    public static String storeToJson(Store store) {
        JSONObject jsonObject = new JSONObject();
        JSONObject addressJson = new JSONObject();
        JSONArray addressArray = new JSONArray();

        jsonObject.put("name", store.getName());
        jsonObject.put("email", store.getEmail());
        jsonObject.put("password", store.getPassword());
        jsonObject.put("cpfCnpj", store.getCpfCnpj());

        addressJson.put("street", store.getAddress().getStreet());
        addressJson.put("house_number", store.getAddress().getHouse_number());
        addressJson.put("neighbourhood", store.getAddress().getNeighbourhood());
        addressJson.put("postal_code", store.getAddress().getPostal_code());
        addressJson.put("city", store.getAddress().getCity());
        addressJson.put("state", store.getAddress().getState());
        addressArray.add(addressJson);

        jsonObject.put("address", addressArray);

        return jsonObject.toJSONString();
    }

    public static void write(Store store){
        try (FileWriter fileWriter = new FileWriter("store.json")) {
            fileWriter.write(storeToJson(store));
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("Erro ao salvar o JSON no arquivo: store.json");
            e.printStackTrace();
        }
    }
}
