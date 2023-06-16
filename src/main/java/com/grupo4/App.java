package com.grupo4;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import com.grupo4.config.DatabaseStorage;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        // Menu.initMenu().run();
        DatabaseStorage.creatingCartList("1", "1");
        DatabaseStorage.creatingHistoryList("1");
    }
}
