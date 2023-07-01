package com.grupo4.view;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.grupo4.controllers.CartController;
import com.grupo4.controllers.HistoryController;
import com.grupo4.controllers.ProductController;
import com.grupo4.error.InexistentSelectOptionException;
import com.grupo4.models.CartProduct;
import com.grupo4.models.Product;

public class Menu_cart_store implements Runnable {
    private Scanner getScan = new Scanner(System.in);

    private boolean isRunning = true;

    private CartController controller;
    private HistoryController historyController;
    private ProductController productController;

    private List<String> options;

    private Menu_cart_store(String id_store, String id_client) {
        controller = CartController.getInstance(id_client, id_store);
        productController = ProductController.getInstance(id_store);

        options = Arrays.asList(
                "0 - mostrar todos os produtos da loja",
                "1 - mostrar todos os produtos do carrinho",
                "2 - adicionar produtos ao carrinho",
                "3 - deletar produtos do carrinho",
                "4 - comprar item do carrinho",
                "5 - sair");
    }

    public void option_get_all() {
        System.out.println("----------- produtos ----------");

        List<Product> products = productController.product_GET_ALL();

        if (products.size() == 0) {
            System.out.println("Não existe produtos na loja!\n");
            return;
        }

        for (Product product : products) {
            System.out.println("______________________________________________");
            System.out.println(product.toString());
            System.out.println("______________________________________________");
        }

        System.out.println("Pressione enter para continuar...");
        getScan.nextLine();
    }


    public void option_get_cart_all() {
        System.out.println("----------- produtos ----------");

        List<CartProduct> products = controller.product_GET_ALL();

        if (products.size() == 0) {
            System.out.println("Não existe produtos no carrinho!\n");
            return;
        }

        for (CartProduct product : products) {
            System.out.println("______________________________________________");
            //System.out.println(product.toString());
            System.out.println(product.getId());
            System.out.println("______________________________________________");
        }

        System.out.println("Pressione enter para continuar...");
        getScan.nextLine();
    }


    public void option_put_in_cart() {
        boolean isRunningOption = true;

        while (isRunningOption) {
            Boolean isRunningForContinue = true;
            String id = "";
            Product product = null;

            while (product == null && !id.equals("-1")) {
                System.out.print("Digite o numero do id do produto: ");

                id = getScan.nextInt() + "";
                getScan.nextLine();

                product = productController.product_GET_BY_ID(id);
            }

            if (!id.equals("-1")) {
                System.out.print("Digite a quantidade que deseja selecionar: ");

                int quantity = getScan.nextInt();
                getScan.nextLine();

                controller.PutInTheCart(product, quantity);
                System.out.println("PRODUTO ADICIONADO COM SUCESSO");

                while (isRunningForContinue) {
                    System.out.print("Deseja Adicionar mais um? (S ou N): ");

                    String again = getScan.next();
                    getScan.nextLine();

                    if (again.equals("N")) {
                        isRunningForContinue = false;
                        isRunningOption = false;
                    } else if (again.equals("S")) {
                        isRunningForContinue = false;
                    }
                }
            }
        }
    }

    public void option_delete() {
        boolean isRunningOption = true;

        if (controller.product_GET_ALL().size() == 0) {
            System.out.println("Não existe produtos no carrinho!");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id: ");

            int id = getScan.nextInt();
            getScan.nextLine();

            try {
                controller.TakeOfFromTheCart("" + id);

            } catch (NoSuchElementException e) {
                System.out.println("Id não encontrado para ser deletado!");
            }

            boolean isRunningForContinue = true;
            while (isRunningForContinue) {
                System.out.print("Deseja remover mais uma (S ou N): ");

                String again = getScan.next();
                getScan.nextLine();

                if (again.equals("N")) {
                    isRunningForContinue = false;
                    isRunningOption = false;
                } else if (again.equals("S")) {
                    isRunningForContinue = false;
                }
            }
        }
    }

    public void buy_products() {
        controller.buyProducts();
    }

    @Override
    public void run() {
        getScan = new Scanner(System.in);
        while (isRunning) {
            System.out.println("MENU PRODUTO");

            options.stream().forEach(System.out::println);

            System.out.println("\u001B[33m");
            System.out.print("Digite o numero: ");

            try {
                int option_select = getScan.nextInt();
                getScan.nextLine();

                switch (option_select) {
                    case 0:
                        this.option_get_all();
                        break;
                    case 1:
                        this.option_get_cart_all();
                        break;
                    case 2:
                        this.option_put_in_cart();
                        break;
                    case 3:
                        this.option_delete();
                        break;
                    case 4:
                        this.buy_products();
                        break;
                    case 5:
                        this.isRunning = false;
                    default:
                        throw new InexistentSelectOptionException();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        isRunning = true;
    }

    public static Menu_cart_store init(String id_store, String id_client) {
        return new Menu_cart_store(id_store, id_client);
    }
}
