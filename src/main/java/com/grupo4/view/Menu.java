package com.grupo4.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.grupo4.controllers.ClientController;
import com.grupo4.controllers.StoreController;
import com.grupo4.error.InexistentSelectOptionException;

public class Menu implements Runnable {
    Scanner getScan = new Scanner(System.in);

    private StoreController storeController;
    private ClientController clientController;

    private final String ANSI_COLOR_MENU = "\u001B[33m";
    private final String ANSI_COLOR_MENU_OPTIONS = "\u001B[30m";

    private boolean isRunning = true;

    private List<String> options;

    private Menu() {
        storeController = StoreController.getInstance();
        clientController = ClientController.getInstance();

        options = Arrays.asList(
                "0 - Cadastrar loja",
                "1 - cadastrar cliente");
    }

    public static Menu initMenu() {
        return new Menu();
    }

    @Override
    public void run() {
        boolean needRegister = true;
        while (isRunning) {
            System.out.println(ANSI_COLOR_MENU + "BEM VINDO AO MENU");
            System.out.println(ANSI_COLOR_MENU_OPTIONS);

            System.out.println("\u001B[30m");
            System.out.print("Digite o login: ");
            String login = getScan.next();
            getScan.nextLine();

            System.out.println("\u001B[30m");
            System.out.print("Digite o senha: ");
            String senha = getScan.next();
            getScan.nextLine();

            Map<String, String> loginData = clientController.client_LOGIN(login, senha);
            if (loginData != null) {
                Menu_login_client.init(loginData).run();
                return;
            }

            loginData = storeController.store_LOGIN(login, senha);
            if (loginData != null) {
                Menu_login_store.init(loginData).run();
                return;
            }

            while (needRegister) {
                System.out.print("Deseja se cadastrar? (S ou N) ");
                String choice = getScan.nextLine();
                System.out.println();

                if (choice.equals("N")) {
                    needRegister = false;
                } else if (choice.equals("S")) {
                    try {
                        options.forEach(System.out::println);

                        System.out.print("\nDigite sua opção: ");

                        int option_select = getScan.nextInt();
                        getScan.nextLine();
                        switch (option_select) {
                            case 0:
                                Menu_store.init().run();
                                break;
                            case 1:
                                Menu_client.init().run();
                                break;
                            default:
                                throw new InexistentSelectOptionException();
                        }
                    } catch (InexistentSelectOptionException e) {
                        System.err.println(e);
                    }
                }
            }
        }
    }
}
