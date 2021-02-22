package steps;

import api.EndPoints;
import api.RequestPaths;
import entities.Order;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import util.Helper;

import static util.Helper.*;

public class OrderSteps {
    private final EndPoints endPoints;
    private Response response;
    private Order order;
    private int petId;

    public OrderSteps(EndPoints endPoints) {
        this.endPoints = endPoints;
    }

    @Given("find available pets")
    public void findPetsBySeachCriteria() {
        endPoints.searchForPet(RequestPaths.findPetsPath(), "available");
    }

    @Given("a pet that is {string} in the pet store")
    public void findPetInStore(String status) {
        if ("available".equals(status)) {
            petId = 123456;
        } else {
            petId = 120;
        }
    }

    @Given("user places an order to be delivered {string}")
    public void placeOrderWithDeliveryDate(String deliveryDate) {
        order = createOrder(petId, 1, deliveryDate);
        response = endPoints.postOrder(order);
    }

    @Given("user retrieves the order")
    public void getOrder() {
        response = endPoints.getOrder(RequestPaths.getOrderPath(), order.getId());
    }

    @Given("user deletes the order")
    public void deleteOrder() {
        response = endPoints.deleteOrder(RequestPaths.deleteOrderPath(), order.getId());
    }


    @Then("the http response code is {string}")
    public void theExpectedHttpResponseCode(String expectedResponseCode) {
        Assert.assertEquals(Integer.parseInt(expectedResponseCode), response.getStatusCode());
    }

}
