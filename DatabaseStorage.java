import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DatabaseStorage {
    private static final String CLIENTS_DATABASE_PATH = "./database/clients.json";
    private static final String PRODUCTS_DATABASE_PATH = "./database/products.json";
    // private static final String STORE_DATABASE_PATH = "";

    private List<Clients> clients;
    private List<Products> store;
    // private List<Store> products;

    public static DatabaseStorage db;

    // THIS IS THE START OF THE CODE, PUT EVERY CHANGE INSIDE THE TRY-CATCH
    public DatabaseStorage() {
        try {
            clients = CreatingClientList();
            store = CreateingProductStore();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    public static synchronized DatabaseStorage getInstance() {
        if (db == null) {
            db = new DatabaseStorage();
        }

        return db;
    }

    // FACADE FUNCTIONS
    // - Clients
    public Boolean client_LOGIN(String email, String password) {
        return clients.stream().anyMatch(value -> value.getEmail().equals(email)
                && value.getPassword().equals(password));
    }

    public List<Clients> client_GET_ALL() {
        return clients;
    }

    public Clients client_GET_BY_ID(String id) {
        return clients.stream().filter(value -> value.getId().equals(id)).findFirst().get();
    }

    public Clients client_GET_BY_NAME(String name) {
        return clients.stream().filter(value -> value.getName().equals(name)).findFirst().get();
    }

    public Clients client_GET_BY_CPF(String cpf) {
        return clients.stream().filter(value -> value.getCpf().equals(cpf)).findFirst().get();
    }

    public String client_PATCH(String cpf) {
        return "";
    }

    public String client_POST(Clients clients) {
        return "";
    }

    public String client_DELETE(String id) {
        return "";
    }

    // CONFIGURATION FUNCTIONS

    // GET DATABASE INFORMATION FROM JSON FILE, PUT THE PATH IN THE FINAL VARIABLE
    // UPSIDE
    private JSONArray initializationFiles(String path) throws IOException, ParseException {
        String jsonString = new String(Files.readAllBytes(Paths.get(path)));
        return (JSONArray) new JSONParser().parse(jsonString);
    }

    private List<Clients> CreatingClientList() throws IOException, ParseException {
        JSONArray jsonList = initializationFiles(CLIENTS_DATABASE_PATH);
        List<Clients> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            // CREATE A CONSTRUCTOR THAT RECEIVES JSONOBJECT ITS MORE SIMPLE THAN DO IT IN
            // HERE
            returnList.add(new Clients((JSONObject) object));
        }

        returnList.stream().forEach(System.out::println);

        return returnList;
    }

    private List<Products> CreateingProductStore() throws IOException, ParseException {
        JSONArray jsonList = initializationFiles(CLIENTS_DATABASE_PATH);
        List<Products> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            // CREATE A CONSTRUCTOR THAT RECEIVES JSONOBJECT ITS MORE SIMPLE THAN DO IT IN
            // HERE
            returnList.add(new Products((JSONObject) object));
        }

        returnList.stream().forEach(System.out::println);

        return returnList;
    }

}
