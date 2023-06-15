package com.grupo4.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.grupo4.controllers.Store_ProductController;
import com.grupo4.error.InexistentSelectOptionException;
import com.grupo4.error.InvalidInputException;
import com.grupo4.models.Product;
import com.grupo4.view.interfaceModel.Menu_options;

public class Menu_store_product implements Menu_options, Runnable {
    private boolean isRunning = true;
    private Store_ProductController controller;
    private Scanner getScan;

    private List<String> options;
    private List<String> post;

    private Menu_store_product(String arquivo) {
        controller = new Store_ProductController(arquivo);
        options = Arrays.asList(
                "0 - mostrar todos os produtos",
                "1 - mostrar produtos específico por id",
                "2 - criar novo produtos",
                "3 - atualizar produtos",
                "4 - deletar produtos",
                "5 - sair");

        post = Arrays.asList(
                "name", "brand", "description", "category", "price", "quantity");
    }

    @Override
    public void option_get_all() {
        System.out.println("-----------  produtos ----------");

        List<Product> products = controller.product_GET_ALL();

        if (products.size() == 0) {
            System.out.println("Não existe produtos cadastrados!\n");
            return;
        }

        for (Product product : products) {
            System.out.println(product.toString());
        }
    }

    @Override
    public void option_get_id() {
        boolean isRunningOption = true;

        if (controller.product_GET_ALL().size() == 0) {
            System.out.println("Não existe produtos cadastrados!\n");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id: ");

            int id = getScan.nextInt();
            getScan.nextLine();

            try {
                controller.product_GET_BY_ID("" + id);

            } catch (NoSuchElementException e) {
                System.out.println("Id não encontrado!");

                boolean isRunningForContinue = true;
                while (isRunningForContinue) {
                    System.out.print("Deseja continuar (S ou N): ");

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

    @Override
    public void option_post() {
        Map<String, String> inputs = new HashMap<>();
        boolean isRunningOption = true;

        while (isRunningOption) {
            for (int index = 0; index < post.size();) {
                try {
                    System.out.print("Digite o " + post.get(index) + ": ");
                    String input = getScan.nextLine();

                    if (input.length() == 0)
                        throw new InvalidInputException();

                    inputs.put(post.get(index), input);
                    index++;
                } catch (InvalidInputException e) {
                    System.err.println(e);
                }
            }

            controller.product_POST(inputs.get("brand"), inputs.get("description"),
                    inputs.get("category"), inputs.get("name"), Double.parseDouble(inputs.get("price")),
                    Integer.parseInt(inputs.get("quantity")));
            isRunningOption = false;
        }
    }

    @Override
    public void option_update() {
        Map<String, String> inputs = new HashMap<>();
        boolean isRunningOption = true;
        boolean isRunningOptionId = true;

        if (controller.product_GET_ALL().size() == 0) {
            System.out.println("Não existe produtos cadastrados!\n");
            return;
        }

        while (isRunningOption) {
            while (isRunningOptionId) {
                System.out.print("Digite o numero do id: ");

                int id = getScan.nextInt();
                getScan.nextLine();

                try {
                    controller.product_GET_BY_ID("" + id);
                } catch (NoSuchElementException e) {
                    System.out.println("Id não encontrado!");

                    boolean isRunningForContinue = true;
                    while (isRunningForContinue) {
                        System.out.print("Deseja continuar (S ou N): ");

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

                inputs.put("id", "" + id);
                isRunningOptionId = false;
            }

            for (int index = 0; index < post.size(); index++) {
                System.out.print("Digite o " + post.get(index) + ": ");
                String input = getScan.nextLine();

                if (input.length() > 0)
                    inputs.put(post.get(index), input);
            }

            controller.product_PATCH(inputs);
            isRunningOption = false;
        }
    }

    @Override
    public void option_delete() {
        boolean isRunningOption = true;

        if (controller.product_GET_ALL().size() == 0) {
            System.out.println("Não existe produtos cadastrados!");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id: ");

            int id = getScan.nextInt();
            getScan.nextLine();

            try {
                controller.product_DELETE("" + id);

            } catch (NoSuchElementException e) {
                System.out.println("Id não encontrado para ser deletado!");
            }

            boolean isRunningForContinue = true;
            while (isRunningForContinue) {
                System.out.print("Deseja continuar (S ou N): ");

                String again = getScan.next();
                getScan.nextLine();

                if (again.equals("S")) {
                    isRunningForContinue = false;
                    isRunningOption = false;
                } else if (again.equals("N")) {
                    isRunningForContinue = false;
                }
            }
        }
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
                        option_get_all();
                        break;
                    case 1:
                        option_get_id();
                        break;
                    case 2:
                        option_post();
                        break;
                    case 3:
                        option_update();
                        break;
                    case 4:
                        option_delete();
                        break;
                    case 5:
                        isRunning = false;
                    default:
                        throw new InexistentSelectOptionException();
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        isRunning = true;
    }

    public static Menu_store_product init(String arquivo) {
        return new Menu_store_product(arquivo);
    }

}
