import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class testesdoJson {

    private static final String CLIENTS_DATABASE_PATH = "produto.json";
    public static <E> void main(String[] args) throws IOException, ParseException {

        Map obj1 = new LinkedHashMap();


        Products produto = new Products("jsonText", "jsonText", "jsonText", "jsonText", 0, 0);
        Products produto2 = new Products("jsonText", "jsonText", "jsonText", "jsonText", 0, 0);
        Products produto3 = new Products("jsonText", "jsonText", "jsonText", "jsonText", 0, 0);

        String jsonText;
        JSONArray array = new JSONArray();


        System.out.println("\nHashMap\n");
        obj1 = convert();

        System.out.println(obj1);
        //obj1.put("produto",produto);
        //obj1.put("produto2",produto2);
        //obj1.put("produto3",produto3);


        //array.add(obj1);
        //jsonText = JSONValue.toJSONString(array);
        //System.out.println("\nEncode a JSON Object - Preserving Order");
        //System.out.print(obj1);


        //write(array);

    }

    public static void write(JSONArray array){
        String jsonText;
        jsonText = JSONValue.toJSONString(array);
        try(FileWriter file = new FileWriter("produto.json")) {
            file.write(jsonText);
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
          }
    }

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


}
