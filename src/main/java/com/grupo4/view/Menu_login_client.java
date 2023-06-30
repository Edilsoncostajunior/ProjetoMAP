package com.grupo4.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.controllers.HistoryController;
import com.grupo4.error.InexistentSelectOptionException;
import com.grupo4.models.Store;
import com.grupo4.models.CartProduct;

public class Menu_login_client implements Runnable {
    private boolean isRunning = true;
    private int points;
    private Scanner getScan;

    private HistoryController historyController;

    private Map<String, String> loginInfo;
    private List<String> options;

    private Menu_login_client(Map<String, String> loginInfo) {
        this.loginInfo = loginInfo;
        historyController = HistoryController.getInstance(loginInfo.get("id"));

        options = Arrays.asList(
                "0 - escolher loja",
                "1 - histórico de compras",
                "2 - sair");

        // options = Arrays.asList(
        //         "0 - escolher loja",
        //         "1 - histórico de compras",
        //         "2 - Consultar pontos",
        //         "3 - sair");
    }

    private void selectStore() {
        boolean isOK = true;
        List<Store> stores = DatabaseStorage.creatingStoreList();

        System.out.println("Lista de lojas: ");
        for (Store store : stores) {
            System.out.println(store.getId() + " - " + store.getName());
        }

        while (isOK) {
            System.out.println("\u001B[33m");
            System.out.print("Digite o id da loja: ");
            String idLoja = getScan.nextInt() + "";
            getScan.nextLine();

            if (stores.stream().anyMatch(value -> value.getId().equals(idLoja))) {
                Menu_cart_store.init(idLoja, loginInfo.get("id")).run();
                isOK = !isOK;
            }
        }
    }

    private void openHistory() {
        System.out.println("___________________COMPRAS________________________");
        historyController.product_GET_ALL().forEach(System.out::println);

        System.out.println("Pressione enter para continuar...");
        getScan.nextLine();
    }

    private float pontuacao(){
        List<CartProduct> listaDeProdutos = historyController.product_GET_ALL();

        int contador = 0;

        for (CartProduct product: listaDeProdutos){
            contador += 1;
        }

        return contador;
    }

    private void consultarPontuacao(){
        System.out.println("Você possui " + pontuacao() + " pontos");
    }

    /*
    private void productEvaluation() {
        // A ideia , até então , está sendo criar um arquivo json para armazenar a nota e o comentário do cliente
        int nota = 0;
        String comentario = "";
        openHistory();

        try {
            System.out.println("Digite o id do produto que deseja avaliar");
            String productId = getScan.next();

            historyController.product_GET_BY_ID(productId).getId();

            System.out.println("Dê uma nota para esse produto");
            nota = getScan.nextInt();

            System.out.println("Deixe um comentário para esse produto");
            comentario = getScan.next();

            String arquivo = "./src/main/java/com/grupo4/database/clients/" + loginInfo.get("id") + "/" + productId + "avaliacao.json";

            File file = new File(arquivo);
            file.createNewFile();

            JSONObject jsonAvaliacao = new JSONObject();
            jsonAvaliacao.put("store", historyController.product_GET_BY_ID(productId).getStore_id());
            jsonAvaliacao.put("nota", nota);
            jsonAvaliacao.put("comentario", comentario);


            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(jsonAvaliacao.toJSONString());

            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo JSON");
            }

        } catch (Exception e) {
            System.out.println("O id informado não corresponde a nenhuma compra");
        }
    }
*/
    @Override
    public void run() {
        getScan = new Scanner(System.in);
        while (isRunning) {
            System.out.println("MENU CLIENTE");
            options.stream().forEach(System.out::println);

            System.out.println("\u001B[33m");
            System.out.print("Digite o numero: ");

            try {
                int option_select = getScan.nextInt();
                getScan.nextLine();

                switch (option_select) {
                    case 0:
                        this.selectStore();
                        break;
                    case 1:
                        this.openHistory();
                        break;
                    case 2:
                        this.consultarPontuacao();
                        break;
                    case 3:
                        isRunning = false;
                        break;
                    default:
                        throw new InexistentSelectOptionException();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        isRunning = true;
    }

    public static Menu_login_client init(Map<String, String> loginInfo) {
        return new Menu_login_client(loginInfo);
    }
}
