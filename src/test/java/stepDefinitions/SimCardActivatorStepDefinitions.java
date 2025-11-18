package stepDefinitions;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class SimCardActivatorStepDefinitions {

    @Autowired
    private TestRestTemplate restTemplate;

    private String iccid;
    private ResponseEntity<String> response;

    @Given("I have the ICCID {string}")
    public void i_have_the_iccid(String iccid) {
        this.iccid = iccid;
    }

    @When("I submit an activation request")
    public void i_submit_an_activation_request() {

        String url = "http://localhost:8080/activate";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonBody = "{ \"iccid\": \"" + iccid + "\" }";

        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        response = restTemplate.postForEntity(url, entity, String.class);
    }

    @Then("the activation should be successful for id {int}")
    public void activation_success(int id) {

        assertEquals(200, response.getStatusCodeValue());

        String url = "http://localhost:8080/query/" + id;

        ResponseEntity<String> dbResponse =
                restTemplate.getForEntity(url, String.class);

        assertTrue(dbResponse.getBody().contains("SUCCESS"));
    }

    @Then("the activation should fail for id {int}")
    public void activation_fail(int id) {

        assertEquals(200, response.getStatusCodeValue());

        String url = "http://localhost:8080/query/" + id;

        ResponseEntity<String> dbResponse =
                restTemplate.getForEntity(url, String.class);

        assertTrue(dbResponse.getBody().contains("FAILED"));
    }
}
