package Pokemons;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class API1Test {

   private static String apiUrl;

    @Test
    public void firstTest() {
        apiUrl = "https://pokeapi.co/api/v2/pokemon";

        Response response = given()
                .get(apiUrl);

        response.then()
                .statusCode(200)
                .body("count", Matchers.equalTo(1302),
                        "results.name", Matchers.hasItems("bulbasaur", "ivysaur"));

        System.out.println("Ответ: " + response.getBody().asString());

        System.out.println("Запрос успешно выполнен.");
    }

    @Test
    public void secondTest() {
        apiUrl = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20";

        Response response = given()
                .get(apiUrl);

        response.then()
                .statusCode(200)
                .body("next", Matchers.equalTo("https://pokeapi.co/api/v2/pokemon?offset=40&limit=20"),
                        "results.name", Matchers.hasItems("spearow", "wigglytuff"));

        System.out.println("Ответ: " + response.getBody().asPrettyString());
    }
}
