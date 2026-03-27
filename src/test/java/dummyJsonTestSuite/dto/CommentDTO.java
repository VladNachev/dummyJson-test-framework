package dummyjsontestsuite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDTO {
    private int id;
    private String body;
    private int postId;
    private int likes;
    private CommentUserDTO user;
    @JsonProperty("isDeleted")
    private boolean isDeleted;

    public CommentDTO() {
    }

    public CommentDTO(int id, String body, int postId, int likes, CommentUserDTO user) {
        this.id = id;
        this.body = body;
        this.postId = postId;
        this.likes = likes;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public CommentUserDTO getUser() {
        return user;
    }

    public void setUser(CommentUserDTO user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @JsonProperty("isDeleted")
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
