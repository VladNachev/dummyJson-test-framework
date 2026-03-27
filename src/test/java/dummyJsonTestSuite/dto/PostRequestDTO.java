package dummyjsontestsuite.dto;

public class PostRequestDTO {
    private final String title;
    private final String body;
    private final int userId;

    public PostRequestDTO(String title, String body, int userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getUserId() {
        return userId;
    }
}
