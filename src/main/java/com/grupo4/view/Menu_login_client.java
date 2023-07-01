package com.grupo4.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.grupo4.config.DatabaseStorage;
import com.grupo4.controllers.CartController;
import com.grupo4.controllers.HistoryController;
import com.grupo4.controllers.ProductController;
import com.grupo4.error.InexistentSelectOptionException;
import com.grupo4.models.Store;

public class Menu_login_client implements Runnable {
    private boolean isRunning = true;
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
                "2 - Verificar se há prêmio",
                "3 - sair");
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
                Menu_cart_store.init(loginInfo, idLoja).run();
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

    public void pontuacao(){
        int contador = historyController.product_GET_ALL().size();

        String contString1 = "" + contador;
        String contString2 = "" + (int) contador/10 + "0";

        if (contString1.equals(contString2)){

            ProductController productController = ProductController.getInstance(historyController.product_GET_BY_ID("0").getStore_id());

            productController.decreaseProduct(historyController.product_GET_BY_ID("0").getProduct_id(), 1);

            CartController cartController = CartController.getInstance(loginInfo.get("id"), historyController.product_GET_BY_ID("0").getStore_id());

            cartController.PutInTheCart(productController.product_GET_BY_NAME(historyController.product_GET_BY_ID("0").getProduct_name()), 1);

            cartController.buyProducts();

            System.out.println("Parabéns, Você ganhou:\n");
            System.out.println(productController.product_GET_BY_NAME(historyController.product_GET_BY_ID("0").getProduct_name()).getName());
        }

        else{

            System.out.println("Você ainda não acumulou pontos suficientes para ganhar algum prêmio :(");
        }
    }

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
                        this.pontuacao();
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
