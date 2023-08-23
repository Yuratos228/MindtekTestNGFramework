package tests;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import dataProviders.BooksApiDataProvider;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ApiUtils;
import utilities.ConfigReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BooksApiTests {

    @Test(groups = {"api"})
    public void validatePostOrderBooksApiCall(){

        // This call gets all books
        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUrl"))
                .and().accept("application/json")
                .and().log().all()
                .when().get("/books");

        response.then().log().all();
        List<Integer> ids = response.body().jsonPath().getList("id");
        List<Boolean> available = response.body().jsonPath().getList("available");
        System.out.println(ids);

        // Checks if we have available books to order
        if (ids.size()!=0){

            Integer bookId = -1;
            for (int i = 0; i < available.size(); i++){
                if (available.get(i)){
                    bookId=ids.get(i);
                    break;
                }
            }

            String token = ApiUtils.getToken();
            String orderName = "Yuratos";
            // We make order with POST Order api call for available book
            String requestPayload = ApiUtils.getJson("Order");

            Map<String, Object> jsonMap = JsonFlattener.flattenAsMap(requestPayload);
            jsonMap.put("bookId", bookId);
            jsonMap.put("customerName", orderName);
//            requestPayload = JsonUnflattener.unflatten(jsonMap.toString());


            response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUrl"))
                    .and().header("Authorization", token)
                    .and().accept("application/json")
                    .and().contentType("application/json")
                    .and().body(jsonMap)
                    .and().log().all()
                    .when().post("/orders/");

            response.then().log().all();

            String orderId = response.body().jsonPath().getString("orderId");

            //Get Order API call
            response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUrl"))
                    .and().header("Authorization", token )
                    .and().accept("application/json")
                    .and().log().all()
                    .when().get("/orders/" + orderId);
            response.then().log().all()
                    .and().statusCode(200);

//            String actualName = response.body().jsonPath().getString("customerName");
//            Assert.assertEquals(orderName, actualName);
            response.then().body("customerName", Matchers.equalTo(orderName))
                    .and().body("id", Matchers.equalTo(orderId))
                    .and().body("bookId", Matchers.equalTo(bookId))
                    .and().header("Content-Type", Matchers.equalTo("application/json; charset=utf-8"))
                    .and().body("quantity", Matchers.greaterThan(0));


        }
    }

    @Test(groups = {"api"})
    public void validateQueryParamsForGetBookApi(){

        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUrl"))
                .and().accept("application/json")
                .and().queryParam("limit",3)
                .and().log().all()
                .when().get("/books");
        response.then().log().all();
        response.then().body("", Matchers.hasSize(3));
        List<Integer> ids = response.body().jsonPath().getList("id");
        Assert.assertEquals(3, ids.size());
    }

    @Test(groups = {"api"})
    public void validationQueryParameterTypeForGetBookApi(){
        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUrl"))
                .and().accept("application/json")
                .and().queryParam("type", "fiction")
                .and().log().all()
                .when().get("/books");
        response.then().log().all();

        response.then().body("type", Matchers.everyItem(Matchers.equalTo("fiction")));
        List<String> fictions = response.body().jsonPath().getList("type");
        for (String fiction: fictions){

            Assert.assertTrue(fiction.equalsIgnoreCase("fiction"));
        }
    }


    @Test(dataProvider = "query-params", dataProviderClass = BooksApiDataProvider.class, groups = {"api"})
    public void validateQueryParamsLimitTypeForGetBookApi(String type, int limit){

        Response response = given().baseUri(ConfigReader.getProperty("BooksAPIBaseUrl"))
                .and().accept("application/json")
                .and().queryParam("type", type)
                .and().queryParam("limit",limit)
                .and().log().all()
                .when().get("/books");
        response.then().log().all();

        response.then().body("", Matchers.hasSize(limit), "type", Matchers.everyItem(Matchers.equalTo(type)));



    }


}
