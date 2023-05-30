package com.grupo4.config;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.grupo4.models.Client;
import com.grupo4.models.Product;
import com.grupo4.models.Store;

// FORA A EQUIPE 4 MESMO SE DEUS FALAR COM VOCÊ PESSOALMENTE, NUNCA TOQUE NESSE CÓDIGO
public class DatabaseStorage {
    private static final String CLIENTS_DATABASE_PATH = "./src/main/java/com/grupo4/database/clients.json";
    private static final String PRODUCTS_DATABASE_PATH = "./src/main/java/com/grupo4/database/products/%s";
    private static final String STORE_DATABASE_PATH = "./src/main/java/com/grupo4/database/store.json";

    private static JSONArray initializationFiles(String path) {
        String jsonString;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(path)));
            return (JSONArray) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            System.err
                    .println(String.format("Ocorreu um erro na formatação do arquivo (messagem: %s)", e.getMessage()));
        } catch (IOException e) {
            System.err
                    .println(String.format("Ocorreu um erro na leitura do arquivo (messagem: %s)", e.getMessage()));
        }

        return null;
    }

    public static List<Client> creatingClientList() {
        JSONArray jsonList = initializationFiles(CLIENTS_DATABASE_PATH);

        if (jsonList == null) {
            return null;
        }

        List<Client> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            returnList.add(new Client((JSONObject) object));
        }

        return returnList;
    }

    public static void writtingClientFile(List<Client> clients) {
        JSONArray jsonArray = new JSONArray();
        for (Client client : clients) {
            jsonArray.add(client.transformToJsonObject());
        }

        try (FileWriter file = new FileWriter(CLIENTS_DATABASE_PATH)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Store> creatingStoreList() {
        JSONArray jsonList = initializationFiles(STORE_DATABASE_PATH);

        if (jsonList == null) {
            return null;
        }

        List<Store> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            returnList.add(new Store((JSONObject) object));
        }

        return returnList;
    }

    public static void writtingStoreFile(List<Store> stores) {
        JSONArray jsonArray = new JSONArray();
        for (Store store : stores) {
            jsonArray.add(store.transformToJsonObject());
        }

        try (FileWriter file = new FileWriter(STORE_DATABASE_PATH)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> creatingProductList(String store_id) {
        JSONArray jsonList = initializationFiles(String.format(PRODUCTS_DATABASE_PATH, store_id));

        if (jsonList == null) {
            return null;
        }

        List<Product> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            returnList.add(new Product((JSONObject) object));
        }

        return returnList;
    }

    public static void writtingProductFile(List<Product> products) {
        JSONArray jsonArray = new JSONArray();
        for (Product product : products) {
            jsonArray.add(product.transformToJsonObject());
        }

        try (FileWriter file = new FileWriter(STORE_DATABASE_PATH)) {
            // We can write any JSONArray or JSONObject instance to the file
            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
