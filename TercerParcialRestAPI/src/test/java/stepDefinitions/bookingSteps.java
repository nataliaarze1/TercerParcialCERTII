package stepDefinitions;
import constants.BookingEndPoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import utils.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsMapContaining.hasKey;

public class bookingSteps {

    Response response;

    @Given("I perform a GET call the booking endpoint")
    public void getEmployees()
    {
        response = Request.get(BookingEndPoints.GET_BOOKING);

    }
    @Then("I verify that the status code is {int}")
    public void verifyStatusCode(int statusCode)
    {
        response.then().assertThat().statusCode(statusCode);

    }
    @And("verify that the body does not have size {int}")
    public void verifyResponseSize(int size){
        response.then().assertThat().body("size()", not(size));
    }

    @Given("I perform a GET call to the bookings endpoint with id {string}")
    public void getBookingById(String id)
    {
        response = Request.getById(BookingEndPoints.GET_BOOKINGbyID, id);
    }
    @And("The booking has the firstname field")
    public void verifyBookingName()
    {
        response.then().assertThat().body("size()", not(0));
        response.then().assertThat().body("$", hasKey("firstname"));
    }
    @And("The booking has the lastname field")
    public void verifyLastName()
    {
        response.then().assertThat().body("size()", not(0));
        response.then().assertThat().body("$", hasKey("lastname"));
    }
    @And("The booking has the total price field")
    public void verifyTotalPriceBooking()
    {
        response.then().assertThat().body("size()", not(0));
        response.then().assertThat().body("$", hasKey("totalprice"));
    }
    @And("The booking has the deposit paid field")
    public void verifyDepositPaidBooking() {
        response.then().assertThat().body("size()", not(0));
        response.then().assertThat().body("$", hasKey("depositpaid"));
    }


    @Given("I perform a POST call to the create booking endpoint with the following data")
    public void postBooking(DataTable bookingInfo)
    {
        response = Request.post(BookingEndPoints.POST_BOOKING,bookingInfo);
    }


    @Given("I perform a DELETE call to the bookings endpoint with id {string}")
    public void deleteBooking(String id)
    {
        response = Request.deleteById(BookingEndPoints.DELETE_BOOKING,id);
    }
    @And("The response message must be {string}")
    public void verifyMessage(String message)
    {
       Assertions.assertTrue(Request.verifyDeleteMessage(message));
    }

    @Given("I perform a PUT call to the booking endpoint with id {string}")
    public void putBooking(String id)
    {
        response = Request.putBooking(BookingEndPoints.PUT_BOOKING, id);
    }

}
