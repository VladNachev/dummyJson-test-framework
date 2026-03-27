package dummyjsontestsuite.dto;

public class RecipeRequestDTO {
    private final String name;
    private final String cuisine;
    private final String difficulty;

    public RecipeRequestDTO(String name, String cuisine, String difficulty) {
        this.name = name;
        this.cuisine = cuisine;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
