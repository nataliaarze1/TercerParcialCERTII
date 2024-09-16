package utils;
import constants.BookingEndPoints;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Request {

    public static String authenticate() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";


        Map<String, String> authPayload = new HashMap<>();
        authPayload.put("username", "admin");
        authPayload.put("password", "password123");

        Response authResponse = given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .when()
                .post("/auth");


        authResponse.then().assertThat().statusCode(200).body("token", notNullValue());

        // Extraer el token de la respuesta
        String token = authResponse.jsonPath().getString("token");
        assertNotNull("Token no debería ser null", token);

        // Imprimir el token en la consola para depuración
        System.out.println("Token generado: " + token);
        return token;

    }

    public static Response get(String endpoint)
    {
        RestAssured.baseURI = BookingEndPoints.BASE_URL;
        Response response = RestAssured.when().get(endpoint);

        response.then().log().body();

        return response;
    }

    public static Response getById(String endpoint, String id){
        RestAssured.baseURI = BookingEndPoints.BASE_URL;
        Response response = RestAssured
                .given().pathParam("id", id)
                .when().get(endpoint);
        response.then().log().body();
        return response;
    }


    public static Response post(String endpoint, DataTable table) {

        RestAssured.baseURI = BookingEndPoints.BASE_URL;

        Map<String, String> authPayload = new HashMap<>();
        authPayload.put("username", "admin");
        authPayload.put("password", "password123");

        Response authResponse = given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .when()
                .post("/auth");


        authResponse.then().assertThat().statusCode(200).body("token", notNullValue());

        // Extraer el token de la respuesta
        String token = authResponse.jsonPath().getString("token");
        assertNotNull("Token no debería ser null", token);

        // Imprimir el token en la consola para depuración
        System.out.println("Token generado: " + token);


        List<Map<String, String>> data = table.asMaps(String.class, String.class);

        Map<String, Object> bookingData = new HashMap<>();
        bookingData.put("firstname", data.get(0).get("firstname"));
        bookingData.put("lastname", data.get(0).get("lastname"));
        bookingData.put("totalprice", Integer.parseInt(data.get(0).get("totalprice")));
        bookingData.put("depositpaid", Boolean.parseBoolean(data.get(0).get("depositpaid")));

        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", data.get(0).get("checkin"));
        bookingDates.put("checkout", data.get(0).get("checkout"));
        bookingData.put("bookingdates", bookingDates);

        bookingData.put("additionalneeds", data.get(0).get("additionalneeds"));

        Response postResponse = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingData)
                .when()
                .post("/booking");


        postResponse.then().assertThat().statusCode(200).body("bookingid", notNullValue());
        postResponse.then().log().body();
        return postResponse;
    }

    public static Response deleteById(String endpoint ,String id) {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";


        Map<String, String> authPayload = new HashMap<>();
        authPayload.put("username", "admin");
        authPayload.put("password", "password123");

        Response authResponse = given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .when()
                .post("/auth");

        authResponse.then().assertThat().statusCode(200).body("token", notNullValue());


        String token = authResponse.jsonPath().getString("token");
        assertNotNull("Token no debería ser null", token);

        System.out.println("Token generado: " + token);


        Response deleteResponse = given()
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete(endpoint);
        deleteResponse.then().assertThat().statusCode(201);
        deleteResponse.then().log().body();

        return deleteResponse;
    }

    public static boolean verifyDeleteMessage(String message)
    {
       return message.equals("created");

    }

    public static Response putBooking(String endpoint, String id)
    {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        Map<String, String> authPayload = new HashMap<>();
        authPayload.put("username", "admin");
        authPayload.put("password", "password123");

        Response authResponse = given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .when()
                .post("/auth");

        authResponse.then().assertThat().statusCode(200).body("token", notNullValue());


        String token = authResponse.jsonPath().getString("token");
        assertNotNull("Token no debería ser null", token);


        System.out.println("Token generado: " + token);

        Map<String, Object> updatedBookingDetails = new HashMap<>();
        updatedBookingDetails.put("firstname", "Natalia");
        updatedBookingDetails.put("lastname", "Arze");
        updatedBookingDetails.put("totalprice", 200);
        updatedBookingDetails.put("depositpaid", false);

        Map<String, String> updatedBookingDates = new HashMap<>();
        updatedBookingDates.put("checkin", "2023-10-01");
        updatedBookingDates.put("checkout", "2023-10-10");

        updatedBookingDetails.put("bookingdates", updatedBookingDates);
        updatedBookingDetails.put("additionalneeds", "Lunch");


        Response putResponse = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .pathParam("id", id)   // Especificar el id del booking a actualizar
                .body(updatedBookingDetails)
                .when()
                .put(endpoint);


        putResponse.then().assertThat().statusCode(200)
                .body("firstname", equalTo("Natalia"))
                .body("lastname", equalTo("Arze"))
                .body("totalprice", equalTo(200))
                .body("depositpaid", equalTo(false))
                .body("bookingdates.checkin", equalTo("2023-10-01"))
                .body("bookingdates.checkout", equalTo("2023-10-10"))
                .body("additionalneeds", equalTo("Lunch"));

        putResponse.then().log().body();

        return putResponse;
    }
}
