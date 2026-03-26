package dummyjsontestsuite.tests;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.dto.UserDTO;
import dummyjsontestsuite.enums.ValidationMessages;
import dummyjsontestsuite.enums.UsersList;
import dummyjsontestsuite.utils.DummyJsonRestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class UserTests extends BaseTest {
    private static final UsersList TEST_USER = UsersList.EMILY_JOHNSON;
    private static final UserDTO TEST_USER_DTO = TEST_USER.getUser();

    @Test
    public void getAllUsersShouldMatchList() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.USERS_ENDPOINT,
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        List<UserDTO> users = response.jsonPath().getList("users", UserDTO.class);
        Assert.assertNotNull(users, ValidationMessages.USERS_LIST_SHOULD_BE_PRESENT.getMessage());
        Assert.assertFalse(users.isEmpty(), ValidationMessages.USERS_LIST_SHOULD_NOT_BE_EMPTY.getMessage());

        UserDTO firstUser = users.get(0);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(firstUser.getFirstName(), TEST_USER_DTO.getFirstName(), ValidationMessages.USER_FIRST_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getLastName(), TEST_USER_DTO.getLastName(), ValidationMessages.USER_LAST_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getMaidenName(), TEST_USER_DTO.getMaidenName(), ValidationMessages.USER_MAIDEN_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getAge(), TEST_USER_DTO.getAge(), ValidationMessages.USER_AGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getGender(), TEST_USER_DTO.getGender(), ValidationMessages.USER_GENDER_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getEmail(), TEST_USER_DTO.getEmail(), ValidationMessages.USER_EMAIL_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getPhone(), TEST_USER_DTO.getPhone(), ValidationMessages.USER_PHONE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getUsername(), TEST_USER_DTO.getUsername(), ValidationMessages.USER_USERNAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getPassword(), TEST_USER_DTO.getPassword(), ValidationMessages.USER_PASSWORD_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getBirthDate(), TEST_USER_DTO.getBirthDate(), ValidationMessages.USER_BIRTH_DATE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getImage(), TEST_USER_DTO.getImage(), ValidationMessages.USER_IMAGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getBloodGroup(), TEST_USER_DTO.getBloodGroup(), ValidationMessages.USER_BLOOD_GROUP_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getHeight(), TEST_USER_DTO.getHeight(), DECIMAL_TOLERANCE, ValidationMessages.USER_HEIGHT_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getWeight(), TEST_USER_DTO.getWeight(), DECIMAL_TOLERANCE, ValidationMessages.USER_WEIGHT_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getEyeColor(), TEST_USER_DTO.getEyeColor(), ValidationMessages.USER_EYE_COLOR_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstUser.getRole(), TEST_USER_DTO.getRole(), ValidationMessages.USER_ROLE_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

}
