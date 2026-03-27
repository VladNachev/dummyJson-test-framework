package dummyjsontestsuite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDTO {
    private int id;
    private String title;
    private String body;
    private int views;
    private int userId;
    @JsonProperty("isDeleted")
    private boolean isDeleted;

    public PostDTO() {
    }

    public PostDTO(int id, String title, String body, int views, int userId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.views = views;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @JsonProperty("isDeleted")
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
