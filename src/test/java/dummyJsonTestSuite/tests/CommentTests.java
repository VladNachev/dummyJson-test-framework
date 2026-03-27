package dummyjsontestsuite.tests;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.dto.CommentDTO;
import dummyjsontestsuite.dto.CommentRequestDTO;
import dummyjsontestsuite.dto.CommentsResponseDTO;
import dummyjsontestsuite.enums.CommentCatalog;
import dummyjsontestsuite.enums.ValidationMessages;
import dummyjsontestsuite.utils.DummyJsonRestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CommentTests extends BaseTest {
    private static final CommentDTO EXPECTED_COMMENT_DTO = CommentCatalog.AWESOME_THINKING.getComment();
    private static final CommentDTO EXPECTED_POST_COMMENT_DTO = CommentCatalog.GROWTH_COMMENT.getComment();
    private static final CommentRequestDTO NEW_COMMENT = new CommentRequestDTO(
            "What has become with movie industry? Nothing, but special effects, were is interesting storyline?",
            3,
            5
    );
    private static final CommentRequestDTO UPDATED_COMMENT = new CommentRequestDTO(
            "My goodness, artists were so talented in the past and today... Damn...",
            3,
            5
    );

    @Test
    public void getAllCommentsShouldMatchCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.COMMENTS_ENDPOINT,
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        CommentsResponseDTO commentsResponse = response.as(CommentsResponseDTO.class);
        Assert.assertNotNull(commentsResponse.getComments(), ValidationMessages.COMMENTS_LIST_SHOULD_BE_PRESENT.getMessage());
        Assert.assertFalse(commentsResponse.getComments().isEmpty(), ValidationMessages.COMMENTS_LIST_SHOULD_NOT_BE_EMPTY.getMessage());

        CommentDTO firstComment = commentsResponse.getComments().get(0);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(firstComment.getId(), EXPECTED_COMMENT_DTO.getId(), ValidationMessages.COMMENT_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstComment.getBody(), EXPECTED_COMMENT_DTO.getBody(), ValidationMessages.COMMENT_BODY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstComment.getPostId(), EXPECTED_COMMENT_DTO.getPostId(), ValidationMessages.COMMENT_POST_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstComment.getLikes(), EXPECTED_COMMENT_DTO.getLikes(), ValidationMessages.COMMENT_LIKES_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstComment.getUser().getId(), EXPECTED_COMMENT_DTO.getUser().getId(), ValidationMessages.COMMENT_USER_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstComment.getUser().getUsername(), EXPECTED_COMMENT_DTO.getUser().getUsername(), ValidationMessages.COMMENT_USERNAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstComment.getUser().getFullName(), EXPECTED_COMMENT_DTO.getUser().getFullName(), ValidationMessages.COMMENT_FULL_NAME_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void getSingleCommentShouldMatchCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.commentByIdEndpoint(EXPECTED_COMMENT_DTO.getId()),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        CommentDTO comment = response.as(CommentDTO.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(comment.getId(), EXPECTED_COMMENT_DTO.getId(), ValidationMessages.COMMENT_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(comment.getBody(), EXPECTED_COMMENT_DTO.getBody(), ValidationMessages.COMMENT_BODY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(comment.getPostId(), EXPECTED_COMMENT_DTO.getPostId(), ValidationMessages.COMMENT_POST_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(comment.getLikes(), EXPECTED_COMMENT_DTO.getLikes(), ValidationMessages.COMMENT_LIKES_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(comment.getUser().getUsername(), EXPECTED_COMMENT_DTO.getUser().getUsername(), ValidationMessages.COMMENT_USERNAME_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void getCommentsByPostIdShouldReturnRelevantResults() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.commentsByPostIdEndpoint(EXPECTED_POST_COMMENT_DTO.getPostId()),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        CommentsResponseDTO commentsResponse = response.as(CommentsResponseDTO.class);
        Assert.assertEquals(commentsResponse.getComments().size(), 1, ValidationMessages.COMMENT_EXACTLY_ONE_COMMENT_RETURNED.getMessage());
        Assert.assertEquals(commentsResponse.getComments().get(0).getBody(), EXPECTED_POST_COMMENT_DTO.getBody(), ValidationMessages.COMMENT_BODY_SHOULD_MATCH.getMessage());
    }

    @Test
    public void addNewComment() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.commentAddEndpoint(),
                DummyJsonConfig.POST,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                NEW_COMMENT
        );

        DummyJsonRestUtils.assertStatusCode(response, 201);

        CommentDTO comment = response.as(CommentDTO.class);

        Assert.assertEquals(comment.getBody(), NEW_COMMENT.getBody(), ValidationMessages.COMMENT_BODY_SHOULD_MATCH.getMessage());
        Assert.assertEquals(comment.getPostId(), NEW_COMMENT.getPostId(), ValidationMessages.COMMENT_POST_ID_SHOULD_MATCH.getMessage());
    }

    @Test
    public void updateExistingComment() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.commentByIdEndpoint(EXPECTED_COMMENT_DTO.getId()),
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                UPDATED_COMMENT
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        CommentDTO comment = response.as(CommentDTO.class);

        Assert.assertEquals(comment.getBody(), UPDATED_COMMENT.getBody(), ValidationMessages.COMMENT_BODY_SHOULD_MATCH.getMessage());
        Assert.assertEquals(comment.getPostId(), UPDATED_COMMENT.getPostId(), ValidationMessages.COMMENT_POST_ID_SHOULD_MATCH.getMessage());
    }

    @Test
    public void deleteExistingComment() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.commentByIdEndpoint(EXPECTED_COMMENT_DTO.getId()),
                DummyJsonConfig.DELETE,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        CommentDTO comment = response.as(CommentDTO.class);

        Assert.assertEquals(comment.getId(), EXPECTED_COMMENT_DTO.getId(), ValidationMessages.COMMENT_ID_SHOULD_MATCH.getMessage());
        Assert.assertEquals(comment.getBody(), EXPECTED_COMMENT_DTO.getBody(), ValidationMessages.COMMENT_BODY_SHOULD_MATCH.getMessage());
        Assert.assertTrue(comment.isDeleted(), ValidationMessages.COMMENT_SHOULD_BE_MARKED_AS_DELETED.getMessage());
    }
}
