package dummyjsontestsuite.dto;

public class CommentRequestDTO {
    private final String body;
    private final int postId;
    private final int userId;

    public CommentRequestDTO(String body, int postId, int userId) {
        this.body = body;
        this.postId = postId;
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }
}
