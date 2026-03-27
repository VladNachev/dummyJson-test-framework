package dummyjsontestsuite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipesResponseDTO {
    private List<RecipeDTO> recipes;

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }
}
