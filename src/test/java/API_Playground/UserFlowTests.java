package API_Playground;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class UserFlowTests {

    @Test
    public void registerShouldCreateNewUser() {
        String uniqueEmail = "test+" + UUID.randomUUID() + "@test.com";
        String requestBody = """
                {
                  "email": "%s",
                  "password": "User123!",
                  "full_name": "Ivan Ivanov"
                }
                """.formatted(uniqueEmail);

        Response response = RestUtils.sendRequest(
                ApiPlaygroundConfig.BASE_URL,
                "/auth/register",
                "POST",
                ApiPlaygroundConfig.jsonHeaders(),
                requestBody);

        RestUtils.assertStatusCode(response, 201);
        Assert.assertFalse(response.asString().isBlank(), "Registration response should not be empty.");
    }
}
