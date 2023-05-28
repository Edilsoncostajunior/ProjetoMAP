import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Products {

    private String brand;
    private String description;
    private String category; 
    private String name;
    private double value;
    private int quantity;

    public Products(String brand, String description, String category, String name, double value, int quantity) {
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
    }


    public Products() {
    }


    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public double getvalue() {
        return value;
    }
    public void setvalue(double value) {
        this.value = value;
    }
    public int getquantity() {
        return quantity;
    }
    public void setquantity(int quantity) {
        this.quantity = quantity;
    }
    public String getbrand() {
        return brand;
    }
    public void setbrand(String brand) {
        this.brand = brand;
    }
    public String getdescription() {
        return description;
    }
    public void setdescription(String description) {
        this.description = description;
    }
    public String getcategory() {
        return category;
    }
    public void setcategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"brand\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(brand) + "\"");
        sb.append(",");
        sb.append("\"description\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(description) + "\"");
        sb.append(",");
        sb.append("\"category\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(category) + "\"");
        sb.append(",");
        sb.append("\"name\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(name) + "\"");
        sb.append(",");
        sb.append("\"value\"");
        sb.append(":");
        sb.append(value);
        sb.append(",");
        sb.append("\"quatidade\"");
        sb.append(":");
        sb.append(quantity);
        sb.append("}");
        return sb.toString();
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
