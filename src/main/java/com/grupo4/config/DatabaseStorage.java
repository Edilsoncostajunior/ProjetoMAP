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

import com.grupo4.error.NullReadableValuesToWriteException;
import com.grupo4.models.Client;
import com.grupo4.models.Product;
import com.grupo4.models.Store;

public class DatabaseStorage {
    private static final String CLIENTS_DATABASE_PATH = "./src/main/java/com/grupo4/database/clients.json";
    private static final String PRODUCTS_DATABASE_PATH = "./src/main/java/com/grupo4/database/Product.json";
    private static final String STORE_DATABASE_PATH = "./src/main/java/com/grupo4/database/store.json";

    private static JSONArray initializationFiles(String path) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(path)));

            return (JSONArray) new JSONParser().parse(jsonString);
        } catch (ParseException e) {
            System.err
                    .println(String.format("Ocorreu um erro na formatação do arquivo (messagem: %s)", e.getMessage()));
            return new JSONArray();
        } catch (IOException e) {
            System.err
                    .println(String.format("Ocorreu um erro na leitura do arquivo (messagem: %s)", e.getMessage()));
            return null;

        }

    }

    public static List<Client> creatingClientList() {
        JSONArray jsonList = initializationFiles(CLIENTS_DATABASE_PATH);

        if (jsonList == null)
            return null;
        if (jsonList.isEmpty())
            return new ArrayList<Client>();

        List<Client> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            returnList.add(new Client((JSONObject) object));
        }

        return returnList;
    }

    public static void writtingClientFile(List<Client> clients) {
        try {
            if (clients == null)
                throw new NullReadableValuesToWriteException();

            JSONArray jsonArray = new JSONArray();

            for (Client client : clients) {
                jsonArray.add(client.transformToJsonObject());
            }

            FileWriter file = new FileWriter(CLIENTS_DATABASE_PATH);

            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullReadableValuesToWriteException e) {
            e.printStackTrace();
        }
    }

    public static List<Store> creatingStoreList() {
        JSONArray jsonList = initializationFiles(STORE_DATABASE_PATH);

        if (jsonList == null)
            return null;
        if (jsonList.isEmpty())
            return new ArrayList<Store>();

        List<Store> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            returnList.add(new Store((JSONObject) object));
        }

        return returnList;
    }

    public static void writtingStoreFile(List<Store> stores) {
        try {
            if (stores == null)
                throw new NullReadableValuesToWriteException();

            JSONArray jsonArray = new JSONArray();
            for (Store store : stores) {
                jsonArray.add(store.transformToJsonObject());
            }

            FileWriter file = new FileWriter(STORE_DATABASE_PATH);

            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullReadableValuesToWriteException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> creatingProductList() {
        JSONArray jsonList = initializationFiles(PRODUCTS_DATABASE_PATH);

        if (jsonList == null)
            return null;
        if (jsonList.isEmpty())
            return new ArrayList<Product>();

        List<Product> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            returnList.add(new Product((JSONObject) object));
        }

        return returnList;
    }

    public static void writtingProductFile(List<Product> products) {
        try {
            if (products == null)
                throw new NullReadableValuesToWriteException();

            JSONArray jsonArray = new JSONArray();
            for (Product product : products) {
                jsonArray.add(product.transformToJsonObject());
            }

            FileWriter file = new FileWriter(PRODUCTS_DATABASE_PATH);

            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullReadableValuesToWriteException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> creatingStoreProductList(String arquivo) {
        JSONArray jsonList = initializationFiles(arquivo);

        if (jsonList == null)
            return null;
        if (jsonList.isEmpty())
            return new ArrayList<Product>();

        List<Product> returnList = new ArrayList<>();

        for (Object object : jsonList) {
            returnList.add(new Product((JSONObject) object));
        }

        return returnList;
    }

    public static void writtingStoreProductFile(List<Product> products, String arquivo) {
        try {
            if (products == null)
                throw new NullReadableValuesToWriteException();

            JSONArray jsonArray = new JSONArray();
            for (Product product : products) {
                jsonArray.add(product.transformToJsonObject());
            }

            FileWriter file = new FileWriter(arquivo);

            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullReadableValuesToWriteException e) {
            e.printStackTrace();
        }
    }
}
