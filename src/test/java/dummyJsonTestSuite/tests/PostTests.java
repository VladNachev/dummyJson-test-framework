package dummyjsontestsuite.tests;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.dto.PostDTO;
import dummyjsontestsuite.dto.PostRequestDTO;
import dummyjsontestsuite.dto.PostsResponseDTO;
import dummyjsontestsuite.enums.PostCatalog;
import dummyjsontestsuite.enums.ValidationMessages;
import dummyjsontestsuite.utils.DummyJsonRestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PostTests extends BaseTest {
    private static final PostDTO EXPECTED_POST_DTO = PostCatalog.HIS_MOTHER_HAD_ALWAYS_TAUGHT_HIM.getPost();
    private static final PostDTO SEARCH_EXPECTED_POST_DTO = PostCatalog.THIS_IS_IMPORTANT_TO_REMEMBER.getPost();
    private static final PostRequestDTO NEW_POST = new PostRequestDTO(
            "BMW launches a new pencil",
            "BMW brings a new premium pencil to the office market.",
            5
    );
    private static final PostRequestDTO UPDATED_POST = new PostRequestDTO(
            "BMW launches an updated pencil",
            "BMW improves the premium pencil with a new design.",
            5
    );

    @Test
    public void getAllPostsShouldMatchCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.POSTS_ENDPOINT,
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        PostsResponseDTO postsResponse = response.as(PostsResponseDTO.class);
        Assert.assertNotNull(postsResponse.getPosts(), ValidationMessages.POSTS_LIST_SHOULD_BE_PRESENT.getMessage());
        Assert.assertFalse(postsResponse.getPosts().isEmpty(), ValidationMessages.POSTS_LIST_SHOULD_NOT_BE_EMPTY.getMessage());

        PostDTO firstPost = postsResponse.getPosts().get(0);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(firstPost.getId(), EXPECTED_POST_DTO.getId(), ValidationMessages.POST_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstPost.getTitle(), EXPECTED_POST_DTO.getTitle(), ValidationMessages.POST_TITLE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstPost.getBody(), EXPECTED_POST_DTO.getBody(), ValidationMessages.POST_BODY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstPost.getViews(), EXPECTED_POST_DTO.getViews(), ValidationMessages.POST_VIEWS_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstPost.getUserId(), EXPECTED_POST_DTO.getUserId(), ValidationMessages.POST_USER_ID_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void getSinglePostShouldMatchCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.postByIdEndpoint(EXPECTED_POST_DTO.getId()),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        PostDTO post = response.as(PostDTO.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(post.getId(), EXPECTED_POST_DTO.getId(), ValidationMessages.POST_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(post.getTitle(), EXPECTED_POST_DTO.getTitle(), ValidationMessages.POST_TITLE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(post.getBody(), EXPECTED_POST_DTO.getBody(), ValidationMessages.POST_BODY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(post.getViews(), EXPECTED_POST_DTO.getViews(), ValidationMessages.POST_VIEWS_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(post.getUserId(), EXPECTED_POST_DTO.getUserId(), ValidationMessages.POST_USER_ID_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void searchForPostShouldReturnRelevantResults() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.postSearchEndpoint("love"),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        PostsResponseDTO postsResponse = response.as(PostsResponseDTO.class);
        Assert.assertEquals(postsResponse.getPosts().get(0).getTitle(), SEARCH_EXPECTED_POST_DTO.getTitle(), ValidationMessages.POST_TITLE_SHOULD_MATCH.getMessage());
    }

    @Test
    public void addNewPost() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.postAddEndpoint(),
                DummyJsonConfig.POST,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                NEW_POST
        );

        DummyJsonRestUtils.assertStatusCode(response, 201);

        PostDTO post = response.as(PostDTO.class);

        Assert.assertEquals(post.getTitle(), NEW_POST.getTitle(), ValidationMessages.POST_TITLE_SHOULD_MATCH.getMessage());
        Assert.assertEquals(post.getBody(), NEW_POST.getBody(), ValidationMessages.POST_BODY_SHOULD_MATCH.getMessage());
        Assert.assertEquals(post.getUserId(), NEW_POST.getUserId(), ValidationMessages.POST_USER_ID_SHOULD_MATCH.getMessage());
    }

    @Test
    public void updateExistingPost() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.postByIdEndpoint(EXPECTED_POST_DTO.getId()),
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                UPDATED_POST
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        PostDTO post = response.as(PostDTO.class);

        Assert.assertEquals(post.getTitle(), UPDATED_POST.getTitle(), ValidationMessages.POST_TITLE_SHOULD_MATCH.getMessage());
        Assert.assertEquals(post.getBody(), UPDATED_POST.getBody(), ValidationMessages.POST_BODY_SHOULD_MATCH.getMessage());
        Assert.assertEquals(post.getUserId(), UPDATED_POST.getUserId(), ValidationMessages.POST_USER_ID_SHOULD_MATCH.getMessage());
    }

    @Test
    public void deleteExistingPost() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.postByIdEndpoint(EXPECTED_POST_DTO.getId()),
                DummyJsonConfig.DELETE,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        PostDTO post = response.as(PostDTO.class);

        Assert.assertEquals(post.getId(), EXPECTED_POST_DTO.getId(), ValidationMessages.POST_ID_SHOULD_MATCH.getMessage());
        Assert.assertEquals(post.getTitle(), EXPECTED_POST_DTO.getTitle(), ValidationMessages.POST_TITLE_SHOULD_MATCH.getMessage());
        Assert.assertTrue(post.isDeleted(), ValidationMessages.POST_SHOULD_BE_MARKED_AS_DELETED.getMessage());
    }
}
