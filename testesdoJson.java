import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class testesdoJson {

    private static final String CLIENTS_DATABASE_PATH = "produto.json";
    private static Map obj1 = new LinkedHashMap();
    public static <E> void main(String[] args) throws IOException, ParseException {


        Products produto = new Products("jsonText", "jsonText", "jsonText", "jsonText", 0, 0);
        Products produto2 = new Products("jsonText", "jsonText", "jsonText", "jsonText", 0, 0);
        Products produto3 = new Products("jsonText", "jsonText", "jsonText", "jsonText", 0, 0);

        obj1 = convert();
       
        inserirProduto();
        
        //obj1.put("produto",produto);
        //obj1.put("produto2",produto2);
        //obj1.put("produto3",produto3);


        //array.add(obj1);
        //jsonText = JSONValue.toJSONString(array);
        //System.out.println("\nEncode a JSON Object - Preserving Order");
        //System.out.print(obj1);


        //write(array);

    }

    public static void write(){
        String jsonText;

        JSONArray array = new JSONArray();
        array.add(obj1);
        jsonText = JSONValue.toJSONString(array);
        try(FileWriter file = new FileWriter("produto.json")) {
            file.write(jsonText);
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
          }
    }

    //convertendo de Json para Map
    private static Map convert(){

        Map<String, Object> map = new LinkedHashMap<>();
        JSONArray JSONArray = read();

        for (Object object : JSONArray) {

            JSONObject jsonObject = (JSONObject) object;
            Map<String, Object> objMap = new LinkedHashMap<>();

            for (Object entry : jsonObject.entrySet()) {

                Map.Entry<?, ?> entryItem = (Map.Entry<?, ?>) entry;
                objMap.put(entryItem.getKey().toString(), entryItem.getValue());
                
            }
            map.putAll(objMap);
        }
        return map;

    }

    //Lendo json
    public static JSONArray read(){
        JSONArray Json = new JSONArray();
        JSONParser parser = new JSONParser();

        try {
            Json = (JSONArray) parser.parse(new FileReader("produto.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            } catch (ParseException e) {
            e.printStackTrace();
            }
        return Json;
    }


    public static void getProduto(String id){

        if(id != null){
             System.out.println(obj1.get(id));
        }else{
            System.out.println("Produto não existe");
        }
    }
    
    public static void removerProduto(String id){

        if(id != null){
            System.out.println(obj1.remove(id)); 
            write();
       }else{
           System.out.println("Produto já foi removido");
       }  
    }

    public static void inserirProduto(){

        Scanner sc = new Scanner(System.in);

        String name;
        int quantity;
        String brand;
        double value;
        String category;
        String description;
        boolean done = false;

        do{
            System.out.println("Digite o nome do produto: ");
            name = sc.nextLine();
        }while(!(name.matches("[a-zA-Z]+")));
        
        do {
            System.out.println("Digite a marca: ");
            brand = sc.nextLine();
        } while (!(brand.matches("[a-zA-Z]+")));
        
        do {
            System.out.println("Digite a categoria: ");
            category = sc.nextLine();
        } while (!(category.matches("[a-zA-Z]+")));
        
        
        do {
            System.out.println("Digite a descição: ");
            description = sc.nextLine();
        } while (!(description.matches("[a-zA-Z]+")));

        do {
            try {
                System.out.println("Digite a quantidade: ");
                quantity = sc.nextInt();
                done = true;
            } catch (InputMismatchException e) {
               sc.nextLine();
            }
        } while (done==false);


        //checar se aceita double mesmo
        do {
            try {
                System.out.println("Digite o valor: ");
                value = sc.nextDouble();
                done = false;
            } catch (InputMismatchException e) {
               sc.nextLine();
            }
        } while (done==true);
        
    }

    
}
