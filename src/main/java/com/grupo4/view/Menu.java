package com.grupo4.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.grupo4.enums.OptionMenuEnum;

public class Menu implements Runnable {
    private Menu_client menu_client;
    private Menu_product menu_product;
    private Menu_store menu_store;

    private final String ANSI_COLOR_MENU = "\u001B[33m";
    private final String ANSI_COLOR_MENU_OPTIONS = "\u001B[30m";

    private List<String> options;

    private boolean isRunning = true;

    private Menu() {
        menu_client = Menu_client.init();
        menu_product = Menu_product.init();
        menu_store = Menu_store.init();

        options = Arrays.asList(
                "0 - CRUD => STORE",
                "1 - CRUD => PRODUCTS",
                "2 - CRUD => CLIENTS",
                "3 - SAIR DO SISTEMA");
    }

    public static Menu initMenu() {
        return new Menu();
    }

    @Override
    public void run() {
        Scanner getScan = new Scanner(System.in);

        while (isRunning) {
            System.out.println(ANSI_COLOR_MENU + "BEM VINDO AO MENU");

            // MUDA A COR DO MENU
            System.out.println(ANSI_COLOR_MENU_OPTIONS);
            this.options.stream().forEach(System.out::println);

            System.out.println("\u001B[30m");
            System.out.print("Digite o numero: ");

            try {
                OptionMenuEnum option_select = OptionMenuEnum.getValue(getScan.nextInt());
                getScan.nextLine();

                switch (option_select) {
                    case STORE:
                        menu_store.run();
                        break;
                    case PRODUCT:
                        menu_product.run();
                        break;
                    case CLIENT:
                        menu_client.run();
                        break;
                    case EXIT:
                        isRunning = false;
                        break;
                }
            } catch (Exception e) {
                System.err.println("Opção selecionada inexistente");
            }
        }

        getScan.close();
    }
}
