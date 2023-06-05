package com.grupo4;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import com.grupo4.view.Menu;


public class App {
    public static void main(String[] args) throws IOException, ParseException {
        Menu.initMenu().run();
    }
}
