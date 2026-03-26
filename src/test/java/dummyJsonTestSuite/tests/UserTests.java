package dummyjsontestsuite.tests;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.dto.UserDTO;
import dummyjsontestsuite.dto.UserRequestDTO;
import dummyjsontestsuite.dto.UsersResponseDTO;
import dummyjsontestsuite.enums.ValidationMessages;
import dummyjsontestsuite.enums.UsersList;
import dummyjsontestsuite.utils.DummyJsonRestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserTests extends BaseTest {
    private static final UsersList TEST_USER = UsersList.EMILY_JOHNSON;
    private static final UserDTO TEST_USER_DTO = TEST_USER.getUser();
    private static final UserRequestDTO updateUserDTO = new UserRequestDTO(
            "EmilyUpdated",
            "JohnsonUpdated",
            "maidenNameUpdated",
            30,
            TEST_USER_DTO.getGender(),
            TEST_USER_DTO.getEmail(),
            TEST_USER_DTO.getPhone(),
            TEST_USER_DTO.getUsername(),
            TEST_USER_DTO.getPassword(),
            TEST_USER_DTO.getBirthDate(),
            TEST_USER_DTO.getImage(),
            TEST_USER_DTO.getBloodGroup(),
            TEST_USER_DTO.getHeight(),
            TEST_USER_DTO.getWeight(),
            TEST_USER_DTO.getEyeColor(),
            TEST_USER_DTO.getRole()
    );
    private static final UserRequestDTO newUserDTO = new UserRequestDTO(
            "Test",
            "User",
            "",
            25,
            "",
            "",
            "",
            "testuser",
            "testpass",
            "",
            "",
            "",
            0,
            0,
            "",
            "user"
    );

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

        UsersResponseDTO usersResponse = response.as(UsersResponseDTO.class);
        Assert.assertNotNull(usersResponse.getUsers(), ValidationMessages.USERS_LIST_SHOULD_BE_PRESENT.getMessage());
        Assert.assertFalse(usersResponse.getUsers().isEmpty(), ValidationMessages.USERS_LIST_SHOULD_NOT_BE_EMPTY.getMessage());

        UserDTO firstUser = usersResponse.getUsers().get(0);
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

    @Test
    public void searchForUserByUsernameShouldReturnRelevantResults() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.userSearchEndpoint(TEST_USER_DTO.getUsername()),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        UsersResponseDTO usersResponse = response.as(UsersResponseDTO.class);
        Assert.assertEquals(usersResponse.getUsers().size(), 1, ValidationMessages.USER_EXACTLY_ONE_USER_RETURNED.getMessage());
        Assert.assertEquals(usersResponse.getUsers().get(0).getUsername(), TEST_USER_DTO.getUsername(), ValidationMessages.USER_USERNAME_SHOULD_MATCH.getMessage());
    }

    @Test
    public void addNewUser() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.userAddEndpoint(),
                DummyJsonConfig.POST,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                newUserDTO
        );

        DummyJsonRestUtils.assertStatusCode(response, 201);

        UserDTO user = response.as(UserDTO.class);

        Assert.assertEquals(user.getFirstName(), newUserDTO.getFirstName(), ValidationMessages.USER_FIRST_NAME_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getLastName(), newUserDTO.getLastName(), ValidationMessages.USER_LAST_NAME_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getAge(), newUserDTO.getAge(), ValidationMessages.USER_AGE_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getRole(), newUserDTO.getRole(), ValidationMessages.USER_ROLE_SHOULD_MATCH.getMessage());
    }

    @Test
    public void updateExistingUser() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.userByIdEndpoint(TEST_USER.getId()),
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                updateUserDTO
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        UserDTO user = response.as(UserDTO.class);

        Assert.assertEquals(user.getFirstName(), updateUserDTO.getFirstName(), ValidationMessages.USER_FIRST_NAME_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getLastName(), updateUserDTO.getLastName(), ValidationMessages.USER_LAST_NAME_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getMaidenName(), updateUserDTO.getMaidenName(), ValidationMessages.USER_MAIDEN_NAME_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getAge(), updateUserDTO.getAge(), ValidationMessages.USER_AGE_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getUsername(), updateUserDTO.getUsername(), ValidationMessages.USER_USERNAME_SHOULD_MATCH.getMessage());
    }

    @Test
    public void updateNonExistingUserShouldReturnNotFound() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.userByIdEndpoint(999), // non-existing user ID
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                updateUserDTO
        );

        DummyJsonRestUtils.assertStatusCode(response, 404);
    }

    @Test
    public void deleteExistingUser() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.userByIdEndpoint(TEST_USER.getId()),
                DummyJsonConfig.DELETE,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        UserDTO user = response.as(UserDTO.class);

        Assert.assertEquals(user.getId(), TEST_USER.getId(), ValidationMessages.USER_ID_SHOULD_MATCH.getMessage());
        Assert.assertEquals(user.getFirstName(), TEST_USER_DTO.getFirstName(), ValidationMessages.USER_FIRST_NAME_SHOULD_MATCH.getMessage());
        Assert.assertTrue(user.isDeleted(), ValidationMessages.USER_SHOULD_BE_MARKED_AS_DELETED.getMessage());
    }

    @Test
    public void getSingleUserByIdShouldMatchUserList() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.userByIdEndpoint(TEST_USER.getId()),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        UserDTO user = response.as(UserDTO.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(user.getId(), TEST_USER.getId(), ValidationMessages.USER_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getFirstName(), TEST_USER_DTO.getFirstName(), ValidationMessages.USER_FIRST_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getLastName(), TEST_USER_DTO.getLastName(), ValidationMessages.USER_LAST_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getMaidenName(), TEST_USER_DTO.getMaidenName(), ValidationMessages.USER_MAIDEN_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getAge(), TEST_USER_DTO.getAge(), ValidationMessages.USER_AGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getGender(), TEST_USER_DTO.getGender(), ValidationMessages.USER_GENDER_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getEmail(), TEST_USER_DTO.getEmail(), ValidationMessages.USER_EMAIL_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getPhone(), TEST_USER_DTO.getPhone(), ValidationMessages.USER_PHONE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getUsername(), TEST_USER_DTO.getUsername(), ValidationMessages.USER_USERNAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getPassword(), TEST_USER_DTO.getPassword(), ValidationMessages.USER_PASSWORD_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getBirthDate(), TEST_USER_DTO.getBirthDate(), ValidationMessages.USER_BIRTH_DATE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getImage(), TEST_USER_DTO.getImage(), ValidationMessages.USER_IMAGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getBloodGroup(), TEST_USER_DTO.getBloodGroup(), ValidationMessages.USER_BLOOD_GROUP_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getHeight(), TEST_USER_DTO.getHeight(), DECIMAL_TOLERANCE, ValidationMessages.USER_HEIGHT_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getWeight(), TEST_USER_DTO.getWeight(), DECIMAL_TOLERANCE, ValidationMessages.USER_WEIGHT_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getEyeColor(), TEST_USER_DTO.getEyeColor(), ValidationMessages.USER_EYE_COLOR_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(user.getRole(), TEST_USER_DTO.getRole(), ValidationMessages.USER_ROLE_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

}
