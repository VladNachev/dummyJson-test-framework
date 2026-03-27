package dummyjsontestsuite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDTO {
    private int id;
    private String name;
    private int prepTimeMinutes;
    private int cookTimeMinutes;
    private int servings;
    private String difficulty;
    private String cuisine;
    private int caloriesPerServing;
    private int userId;
    private String image;
    private double rating;
    private int reviewCount;
    @JsonProperty("isDeleted")
    private boolean isDeleted;

    public RecipeDTO() {
    }

    public RecipeDTO(
            int id,
            String name,
            int prepTimeMinutes,
            int cookTimeMinutes,
            int servings,
            String difficulty,
            String cuisine,
            int caloriesPerServing,
            int userId,
            String image,
            double rating,
            int reviewCount
    ) {
        this.id = id;
        this.name = name;
        this.prepTimeMinutes = prepTimeMinutes;
        this.cookTimeMinutes = cookTimeMinutes;
        this.servings = servings;
        this.difficulty = difficulty;
        this.cuisine = cuisine;
        this.caloriesPerServing = caloriesPerServing;
        this.userId = userId;
        this.image = image;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public void setPrepTimeMinutes(int prepTimeMinutes) {
        this.prepTimeMinutes = prepTimeMinutes;
    }

    public int getCookTimeMinutes() {
        return cookTimeMinutes;
    }

    public void setCookTimeMinutes(int cookTimeMinutes) {
        this.cookTimeMinutes = cookTimeMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getCaloriesPerServing() {
        return caloriesPerServing;
    }

    public void setCaloriesPerServing(int caloriesPerServing) {
        this.caloriesPerServing = caloriesPerServing;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    @JsonProperty("isDeleted")
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
