package dummyjsontestsuite.tests;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.dto.RecipeDTO;
import dummyjsontestsuite.dto.RecipeRequestDTO;
import dummyjsontestsuite.dto.RecipesResponseDTO;
import dummyjsontestsuite.enums.RecipeCatalog;
import dummyjsontestsuite.enums.ValidationMessages;
import dummyjsontestsuite.utils.DummyJsonRestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RecipeTests extends BaseTest {
    private static final RecipeDTO EXPECTED_RECIPE_DTO = RecipeCatalog.CLASSIC_MARGHERITA_PIZZA.getRecipe();
    private static final RecipeRequestDTO NEW_RECIPE = new RecipeRequestDTO(
            "BMW Pasta",
            "Italian",
            "Easy"
    );
    private static final RecipeRequestDTO UPDATED_RECIPE = new RecipeRequestDTO(
            "BMW Pasta Deluxe",
            "Italian",
            "Medium"
    );

    @Test
    public void getAllRecipesShouldMatchCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.RECIPES_ENDPOINT,
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        RecipesResponseDTO recipesResponse = response.as(RecipesResponseDTO.class);
        Assert.assertNotNull(recipesResponse.getRecipes(), ValidationMessages.RECIPES_LIST_SHOULD_BE_PRESENT.getMessage());
        Assert.assertFalse(recipesResponse.getRecipes().isEmpty(), ValidationMessages.RECIPES_LIST_SHOULD_NOT_BE_EMPTY.getMessage());

        RecipeDTO firstRecipe = recipesResponse.getRecipes().get(0);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(firstRecipe.getId(), EXPECTED_RECIPE_DTO.getId(), ValidationMessages.RECIPE_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getName(), EXPECTED_RECIPE_DTO.getName(), ValidationMessages.RECIPE_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getPrepTimeMinutes(), EXPECTED_RECIPE_DTO.getPrepTimeMinutes(), ValidationMessages.RECIPE_PREP_TIME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getCookTimeMinutes(), EXPECTED_RECIPE_DTO.getCookTimeMinutes(), ValidationMessages.RECIPE_COOK_TIME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getServings(), EXPECTED_RECIPE_DTO.getServings(), ValidationMessages.RECIPE_SERVINGS_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getDifficulty(), EXPECTED_RECIPE_DTO.getDifficulty(), ValidationMessages.RECIPE_DIFFICULTY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getCuisine(), EXPECTED_RECIPE_DTO.getCuisine(), ValidationMessages.RECIPE_CUISINE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getCaloriesPerServing(), EXPECTED_RECIPE_DTO.getCaloriesPerServing(), ValidationMessages.RECIPE_CALORIES_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getUserId(), EXPECTED_RECIPE_DTO.getUserId(), ValidationMessages.RECIPE_USER_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getRating(), EXPECTED_RECIPE_DTO.getRating(), DECIMAL_TOLERANCE, ValidationMessages.RECIPE_RATING_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstRecipe.getReviewCount(), EXPECTED_RECIPE_DTO.getReviewCount(), ValidationMessages.RECIPE_REVIEW_COUNT_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void getSingleRecipeShouldMatchCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.recipeByIdEndpoint(EXPECTED_RECIPE_DTO.getId()),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        RecipeDTO recipe = response.as(RecipeDTO.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(recipe.getId(), EXPECTED_RECIPE_DTO.getId(), ValidationMessages.RECIPE_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(recipe.getName(), EXPECTED_RECIPE_DTO.getName(), ValidationMessages.RECIPE_NAME_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(recipe.getCuisine(), EXPECTED_RECIPE_DTO.getCuisine(), ValidationMessages.RECIPE_CUISINE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(recipe.getDifficulty(), EXPECTED_RECIPE_DTO.getDifficulty(), ValidationMessages.RECIPE_DIFFICULTY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(recipe.getRating(), EXPECTED_RECIPE_DTO.getRating(), DECIMAL_TOLERANCE, ValidationMessages.RECIPE_RATING_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void searchForRecipeShouldReturnRelevantResults() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.recipeSearchEndpoint("Margherita"),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        RecipesResponseDTO recipesResponse = response.as(RecipesResponseDTO.class);
        Assert.assertFalse(recipesResponse.getRecipes().isEmpty(), ValidationMessages.RECIPES_LIST_SHOULD_NOT_BE_EMPTY.getMessage());
        Assert.assertTrue(
                recipesResponse.getRecipes().stream().anyMatch(recipe -> recipe.getName().equals(EXPECTED_RECIPE_DTO.getName())),
                ValidationMessages.RECIPE_NAME_SHOULD_MATCH.getMessage()
        );
    }

    @Test
    public void addNewRecipe() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.recipeAddEndpoint(),
                DummyJsonConfig.POST,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                NEW_RECIPE
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        RecipeDTO recipe = response.as(RecipeDTO.class);

        Assert.assertEquals(recipe.getName(), NEW_RECIPE.getName(), ValidationMessages.RECIPE_NAME_SHOULD_MATCH.getMessage());
        Assert.assertEquals(recipe.getCuisine(), NEW_RECIPE.getCuisine(), ValidationMessages.RECIPE_CUISINE_SHOULD_MATCH.getMessage());
        Assert.assertEquals(recipe.getDifficulty(), NEW_RECIPE.getDifficulty(), ValidationMessages.RECIPE_DIFFICULTY_SHOULD_MATCH.getMessage());
    }

    @Test
    public void updateExistingRecipe() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.recipeByIdEndpoint(EXPECTED_RECIPE_DTO.getId()),
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                UPDATED_RECIPE
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        RecipeDTO recipe = response.as(RecipeDTO.class);

        Assert.assertEquals(recipe.getName(), UPDATED_RECIPE.getName(), ValidationMessages.RECIPE_NAME_SHOULD_MATCH.getMessage());
        Assert.assertEquals(recipe.getCuisine(), UPDATED_RECIPE.getCuisine(), ValidationMessages.RECIPE_CUISINE_SHOULD_MATCH.getMessage());
        Assert.assertEquals(recipe.getDifficulty(), UPDATED_RECIPE.getDifficulty(), ValidationMessages.RECIPE_DIFFICULTY_SHOULD_MATCH.getMessage());
    }

    @Test
    public void deleteExistingRecipe() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.recipeByIdEndpoint(EXPECTED_RECIPE_DTO.getId()),
                DummyJsonConfig.DELETE,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        RecipeDTO recipe = response.as(RecipeDTO.class);

        Assert.assertEquals(recipe.getId(), EXPECTED_RECIPE_DTO.getId(), ValidationMessages.RECIPE_ID_SHOULD_MATCH.getMessage());
        Assert.assertEquals(recipe.getName(), EXPECTED_RECIPE_DTO.getName(), ValidationMessages.RECIPE_NAME_SHOULD_MATCH.getMessage());
        Assert.assertTrue(recipe.isDeleted(), ValidationMessages.RECIPE_SHOULD_BE_MARKED_AS_DELETED.getMessage());
    }
}
