package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    public static String getToken(){
        Random random = new Random();
        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUrl"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "   \"clientName\": \"Yuratos\",\n" +
                        "   \"clientEmail\": \"number"+random.nextInt(10000)+".one@gmail.com\"\n" +
                        "}")
                .when().post("/api-clients/");
       String token = "Bearer " + response.body().jsonPath().getString("accessToken");

       return token;
    }

    public static String getJson(String name){
        String path = "C:\\Users\\UVRH01\\IdeaProjects\\MindtekTestNGFramework\\src\\test\\resources\\testdata\\"+ name +".json";

        String data = "";

        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader(path);
            data = jsonParser.parse(reader).toString();

        } catch (Exception e) {
            System.out.println("something happened with jsonParser");
        }

        return data;
    }


    public static String modifyJson(String name, Map<String, Object> modifications){
        String originalJson = getJson(name);


        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(originalJson);

            for (Map.Entry<String, Object> entry: modifications.entrySet()){
                ((ObjectNode) jsonNode).putPOJO(entry.getKey(), entry.getValue());
            }

            return objectMapper.writeValueAsString(jsonNode);

        } catch (Exception e) {
            System.out.println("Error modifying JSON: " + e.getMessage());
            return originalJson;
        }
    }
}
