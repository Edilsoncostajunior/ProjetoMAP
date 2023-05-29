import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return this.cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store [name=" + name + ", email=" + email + ", password=" + password + ", cpfCnpj=" + cpfCnpj
                + ", address=" + address + "]";
    }

    public static JSONObject storeToJson(Store store) {
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

        return jsonObject;
    }


    public static List<Store> read() throws IOException, ParseException {
        List<Store> storeList = new ArrayList<>();
        try (FileReader fileReader = new FileReader("store.json")) {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(fileReader);
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONObject addressObj = (JSONObject) ((JSONArray) jsonObject.get("address")).get(0);
                storeList.add(new Store(
                        (String) jsonObject.get("name"),
                        (String) jsonObject.get("email"),
                        (String) jsonObject.get("password"),
                        (String) jsonObject.get("cpfCnpj"),
                        new Address(
                                (String) addressObj.get("id"),
                                (String) addressObj.get("street"),
                                (String) addressObj.get("house_number"),
                                (String) addressObj.get("neighbourhood"),
                                (String) addressObj.get("postal_code"),
                                (String) addressObj.get("city"),
                                (String) addressObj.get("state"))
                ));
            }
        } catch (ParseException e) {
            System.err.println("Erro ao ler o arquivo JSON: store.json");
            e.printStackTrace();
        }
        return storeList;
    }

    public void write(Store store) {
        List<Store> storeList = new ArrayList<>();
        try {
            storeList = read();
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao ler o arquivo JSON: store.json");
            e.printStackTrace();
        }

        if (store.isUniqueCpfCnpj(storeList)) {
            storeList.add(store);
        } else {
            System.out.println("Cpf ou Cnpj corresponde a uma store existente.");
        }

        try (FileWriter fileWriter = new FileWriter("store.json")) {
            JSONArray jsonArray = new JSONArray();
            for (Store storeObj : storeList) {
                jsonArray.add(storeToJson(storeObj));
            }
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
            System.out.println("Arquivo JSON store.json existe");
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o JSON store.json");
            e.printStackTrace();
        }
    }


    public void update(String storeName, Store updatedStore) {
        List<Store> storeList = new ArrayList<>();
        try {
            storeList = read();
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao ler o arquivo JSON store.json");
            e.printStackTrace();
        }

        if (!updatedStore.isUniqueCpfCnpj(storeList)) {
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i).getName().equals(storeName)) {
                    storeList.set(i, updatedStore);
                    break;
                }
            }

            try (FileWriter fileWriter = new FileWriter("store.json")) {
                JSONArray jsonArray = new JSONArray();
                for (Store storeObj : storeList) {
                    jsonArray.add(storeToJson(storeObj));
                }
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
                System.out.println("Store atualizada no arquivo store.json");
            } catch (IOException e) {
                System.err.println("Erro ao atualizar o JSON no arquivo store.json");
                e.printStackTrace();
            }
        } else{
            System.out.println("Cpf ou Cnpj não corresponde a uma store existente");
        }
    }

    public static void remove(String storeName, String cpfCnpj) {
        List<Store> storeList = new ArrayList<>();
        try {
            storeList = read();
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao ler o arquivo JSON store.json");
            e.printStackTrace();
        }

        if (isStoreDeletable(storeName, cpfCnpj)) {
            storeList.removeIf(store -> store.getName().equals(storeName));

            try (FileWriter fileWriter = new FileWriter("store.json")) {
                JSONArray jsonArray = new JSONArray();
                for (Store storeObj : storeList) {
                    jsonArray.add(storeToJson(storeObj));
                }
                fileWriter.write(jsonArray.toJSONString());
                fileWriter.flush();
                System.out.println("JSON atualizado no arquivo store.json");
            } catch (IOException e) {
                System.err.println("Erro ao atualizar o JSON no arquivo store.json");
                e.printStackTrace();
            }
        } else {
            System.out.println("Nome ou Cpf/Cnpj não corresponde a uma store existente.");
        }
    }

    public boolean isUniqueCpfCnpj(List<Store> storeList) {
        for (Store store : storeList) {
            if (this.cpfCnpj.equals(store.getCpfCnpj())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStoreDeletable(String storeName, String cpfCnpj) {
        List<Store> storeList = new ArrayList<>();
        try {
            storeList = read();
        } catch (IOException | ParseException e) {
            System.err.println("Erro ao ler o arquivo JSON: store.json");
            e.printStackTrace();
        }

        for (Store store : storeList) {
            if (store.getName().equals(storeName) && store.getCpfCnpj().equals(cpfCnpj)) {
                return true;
            }
        }
        return false;
    }
}
