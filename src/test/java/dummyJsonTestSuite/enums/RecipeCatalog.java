package dummyjsontestsuite.enums;

import dummyjsontestsuite.dto.RecipeDTO;

public enum RecipeCatalog {
    CLASSIC_MARGHERITA_PIZZA(
            new RecipeDTO(
                    1,
                    "Classic Margherita Pizza",
                    20,
                    15,
                    4,
                    "Easy",
                    "Italian",
                    300,
                    166,
                    "https://cdn.dummyjson.com/recipe-images/1.webp",
                    4.6,
                    98
            )
    );

    private final RecipeDTO recipe;

    RecipeCatalog(RecipeDTO recipe) {
        this.recipe = recipe;
    }

    public RecipeDTO getRecipe() {
        return recipe;
    }
}
