import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ParseJSONResponseUsingRESTASSURED {

    public static Response doGetRequest(String endpoint){
        RestAssured.defaultParser = Parser.JSON;

        return given().headers("Content-Type", ContentType.JSON,"Accept",ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
    }

    public static void main(String[] args){
        Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");

//        //Get size of json
//        List<String> jsonReponse = response.jsonPath().get("$");
//        System.out.println("Size of this Json: "+ jsonReponse.size());
//
//        //Get list name
//        String name = response.jsonPath().getString("name");
//        System.out.println("List name of this Json: "+ name);
//
//        //Get 1 name
//        String name2 = response.jsonPath().getString("name[0]");
//        System.out.println("List name of this Json: "+ name2);
//
//        //Using List to get name
//        List<String> nameList = response.jsonPath().getList("name");
//        System.out.println("List name wiht index = 0: "+nameList.get(0));

        //Get By Map nếu có đúng 1 mảng
        Map<String, String> company = response.jsonPath().getMap("company[0]");
        System.out.println("Name into company: " +company.get("name"));

        //Get By Map băng List
        List<Map<String, String>> company2 = response.jsonPath().getList("company");
        System.out.println("Name into company2: " +company2.get(0).get("name"));
    }
}
