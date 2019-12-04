package config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonConfig
{

    static JsonParser jsonParser = null;
    static String path = System.getProperty("user.dir");
    static FileReader reader = null;

    public static void main(String[] args) {
        System.out.println(getValue("user1","username")) ;
    }

    public static String getValue(String set, String key)
    {
        jsonParser = new JsonParser();

        try {
            reader = new FileReader(path + "/src/configFiles/config.json");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    //Read JSON file
        JsonObject obj = (JsonObject) jsonParser.parse(reader);

    // Put all values of jsonObject gui to variable gui
//        JsonObject gui = (JsonObject) obj.get("gui");
        JsonObject fileSet = (JsonObject) obj.get(set);

    // Get value of "browser" key and save to JsonElement
        JsonElement keyValue = fileSet.get(key);
    // Last step is saving value as string
        String json = keyValue.getAsString();

        return json;
    }
}
