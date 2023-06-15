package com.grupo4.view;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.grupo4.controllers.StoreController;
//import com.grupo4.controllers.Store_ProductController;
import com.grupo4.error.InexistentSelectOptionException;
import com.grupo4.error.InvalidInputException;
import com.grupo4.error.NullReadableValuesToWriteException;
import com.grupo4.models.Store;
import com.grupo4.view.interfaceModel.Menu_options;

public class Menu_store implements Menu_options, Runnable {
    private boolean isRunning = true;
    private StoreController controller;
    private Menu_store_product menu;
    // private Menu_product menu_product;
    // private Store_ProductController controller1;
    private Scanner getScan;

    private List<String> options;
    private List<String> post;

    private Menu_store() {
        controller = new StoreController();
        // menu_product = Menu_product.init();
        // menu = Menu_store_product()
        // controller1 = new Store_ProductController();
        options = Arrays.asList(
                "0 - mostrar todos as lojas",
                "1 - mostrar loja específica por id",
                "2 - criar nova loja",
                "3 - atualizar loja",
                "4 - deletar loja",
                "5 - produtos da loja",
                "6 - sair");

        post = Arrays.asList(
                "name",
                "document",
                "email",
                "password",
                "street",
                "house_number",
                "neighbourhood",
                "postal_code",
                "city",
                "state",
                "country");
    }

    @Override
    public void option_get_all() {
        System.out.println("-----------  lojas ----------");

        List<Store> stores = controller.read();

        if (stores.size() == 0) {
            System.out.println("Não existe lojas cadastradas!\n");
            return;
        }

        for (Store store : stores) {
            System.out.println(store.toString());
        }
    }

    @Override
    public void option_get_id() {
        boolean isRunningOption = true;

        if (controller.read().size() == 0) {
            System.out.println("Não existe lojas cadastradas!\n");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id: ");

            int id = getScan.nextInt();
            getScan.nextLine();

            try {
                System.out.println(controller.read_by_id("" + id).toString());
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
            isRunningOption = false;
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

                    if (input.length() <= 3)
                        throw new InvalidInputException();

                    inputs.put(post.get(index), input);
                    index++;
                } catch (InvalidInputException e) {
                    System.err.println(e);
                }
            }

            controller.write(inputs.get("name"), inputs.get("document"), inputs.get("email"),
                    inputs.get("password"), inputs.get("street"), inputs.get("house_number"),
                    inputs.get("neighbourhood"), inputs.get("postal_code"), inputs.get("city"),
                    inputs.get("state"), inputs.get("country"));
            isRunningOption = false;
        }
    }

    @Override
    public void option_update() {
        Map<String, String> inputs = new HashMap<>();
        boolean isRunningOption = true;
        boolean isRunningOptionId = true;

        if (controller.read().size() == 0) {
            System.out.println("Não existe lojas cadastradas!\n");
            return;
        }

        while (isRunningOption) {
            while (isRunningOptionId) {
                System.out.print("Digite o numero do id: ");

                int id = getScan.nextInt();
                getScan.nextLine();

                try {
                    controller.read_by_id("" + id);
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

                if (input.length() > 3)
                    inputs.put(post.get(index), input);
            }

            try {
                controller.update(inputs);
                isRunningOption = false;

            } catch (NullReadableValuesToWriteException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void option_delete() {
        boolean isRunningOption = true;

        if (controller.read().size() == 0) {
            System.out.println("Não existe lojas cadastradas!");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id: ");

            int id = getScan.nextInt();
            getScan.nextLine();

            try {
                controller.remove("" + id);
                isRunningOption = false;

            } catch (NoSuchElementException e) {
                System.out.println("Id não encontrado para ser deletado!");

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
    }

    @Override
    public void run() {
        getScan = new Scanner(System.in);
        while (isRunning) {
            System.out.println("MENU LOJA");

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
                        get_id_for_store_products();
                        break;

                    case 6:
                        isRunning = false;
                    default:
                        throw new InexistentSelectOptionException();
                }
            } catch (InexistentSelectOptionException e) {
                System.err.println(e);
            }
        }

        isRunning = true;
    }

    // Organizar em outros arquivos esses códigos depois...

    public void get_id_for_store_products() {
        boolean isRunningOption = true;

        if (controller.read().size() == 0) {
            System.out.println("Não existe lojas cadastradas!\n");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id da loja: ");

            int id = getScan.nextInt();
            getScan.nextLine();

            try {
                System.out.println(controller.read_by_id("" + id).toString());
                get_file_products_store(id);
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
            isRunningOption = false;
        }
    }

    public void get_file_products_store(int id) {

        File arquivo = new File("./src/main/java/com/grupo4/controllers/Store_Products/" + id + ".json");
        String auxArquivo = arquivo.getAbsolutePath();

        try {
            if (arquivo.exists()) {
                System.out.println("arquivo existe");
            } else {
                arquivo.createNewFile();
            }

            Menu_store_product.init(arquivo.getAbsolutePath()).run();
        } catch (NoSuchElementException | IOException e) {
            // tratamento de erro
        }

    }

    /*
     * -----------------------------------------------------------------------------
     * ---------------
     */

    public static Menu_store init() {
        return new Menu_store();
    }
}
