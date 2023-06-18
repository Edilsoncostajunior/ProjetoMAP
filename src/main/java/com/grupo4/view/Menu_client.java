package com.grupo4.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.grupo4.controllers.ClientController;
import com.grupo4.error.InexistentSelectOptionException;
import com.grupo4.error.InvalidInputException;
import com.grupo4.models.Client;

public class Menu_client implements Runnable {
    private boolean isRunning = true;
    private ClientController controller;
    private Scanner getScan;

    private List<String> options;
    private List<String> post;

    private Menu_client() {
        controller = ClientController.getInstance();
        options = Arrays.asList(
                "0 - mostrar todos os clients",
                "1 - mostrar cliente específico por id",
                "2 - criar novo cliente",
                "3 - atualizar cliente",
                "4 - deletar cliente",
                "5 - sair");

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

    public void option_get_all() {
        System.out.println("-----------  clientes ----------");

        List<Client> clients = controller.client_GET_ALL();

        if (clients.size() == 0) {
            System.out.println("Não existe clientes cadastrados!\n");
            return;
        }

        for (Client client : clients) {
            System.out.println(client.toString());
        }
        System.out.println("Pressione enter para continuar...");
        getScan.nextLine();
    }

    public void option_get_id() {
        boolean isRunningOption = true;

        if (controller.client_GET_ALL().size() == 0) {
            System.out.println("Não existe clientes cadastrados!\n");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id: ");

            int id = getScan.nextInt();
            getScan.nextLine();
           

            try {
                System.out.println( controller.client_GET_BY_ID("" + id));
               

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
        System.out.println("Pressione enter para continuar...");
        getScan.nextLine();
    }

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

            controller.client_POST(inputs.get("name"), inputs.get("document"), inputs.get("email"),
                    inputs.get("password"), inputs.get("street"), inputs.get("house_number"),
                    inputs.get("neighbourhood"), inputs.get("postal_code"), inputs.get("city"),
                    inputs.get("state"), inputs.get("country"));
            isRunningOption = false;
        }
    }

    public void option_update() {
        Map<String, String> inputs = new HashMap<>();
        boolean isRunningOption = true;
        boolean isRunningOptionId = true;

        if (controller.client_GET_ALL().size() == 0) {
            System.out.println("Não existe clientes cadastrados!\n");
            return;
        }

        while (isRunningOption) {
            while (isRunningOptionId) {
                System.out.print("Digite o numero do id: ");

                int id = getScan.nextInt();
                getScan.nextLine();

                try {
                    controller.client_GET_BY_ID("" + id);
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
                            
                            for (int index = 0; index < post.size(); index++) {
                                System.out.print("Digite o " + post.get(index) + ": ");
                                String input = getScan.nextLine();

                                if (input.length() > 3)
                                    inputs.put(post.get(index), input);
                                }
                        }
                    }
                }

                inputs.put("id", "" + id);
                isRunningOptionId = false;
            }

            controller.client_PATCH(inputs);
            isRunningOption = false;
        }
    }

    public void option_delete() {
        boolean isRunningOption = true;

        if (controller.client_GET_ALL().size() == 0) {
            System.out.println("Não existe clientes cadastrados!");
            return;
        }

        while (isRunningOption) {
            System.out.print("Digite o numero do id: ");

            int id = getScan.nextInt();
            getScan.nextLine();

            try {
                controller.client_DELETE("" + id);

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
                        break;
                    default:
                        throw new InexistentSelectOptionException();
                }
            } catch (InexistentSelectOptionException e) {
                System.err.println(e);
            }
        }

        isRunning = true;
    }

    public static Menu_client init() {
        return new Menu_client();
    }
}
