package com.grupo4.view;

import java.util.Map;
import java.util.Scanner;

import com.grupo4.controllers.ClientController;
import com.grupo4.controllers.StoreController;

public class Menu implements Runnable {
    Scanner getScan = new Scanner(System.in);

    private StoreController storeController;
    private ClientController clientController;

    private final String ANSI_COLOR_MENU = "\u001B[33m";
    private final String ANSI_COLOR_MENU_OPTIONS = "\u001B[30m";

    private boolean isRunning = true;

    private Menu() {
        storeController = StoreController.getInstance();
        clientController = ClientController.getInstance();
    }

    public static Menu initMenu() {
        return new Menu();
    }

    @Override
    public void run() {

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
        }
    }
}
