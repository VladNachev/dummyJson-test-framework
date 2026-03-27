package dummyjsontestsuite.config;

import java.util.Map;

public final class DummyJsonConfig {

    // Base URL
    public static final String BASE_URL = "https://dummyjson.com";

    // Endpoints
    public static final String LOGIN_ENDPOINT = "/auth/login";
    public static final String PRODUCTS_ENDPOINT = "/products";
    public static final String POSTS_ENDPOINT = "/posts";
    public static final String RECIPES_ENDPOINT = "/recipes";
    public static final String USERS_ENDPOINT = "/users";
    public static final String COMMENTS_ENDPOINT = "/comments";

    // Verbs
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    // Auth constants
    public static final String USERNAME = "emilys";
    public static final String PASSWORD = "emilyspass";

    private DummyJsonConfig() {
    }

    // Headers
    public static Map<String, String> jsonHeaders() {
        return Map.of(
                "Content-Type", "application/json",
                "Accept", "application/json"
        );
    }

    public static Map<String, String> authorizedJsonHeaders(String accessToken) {
        return Map.of(
                "Content-Type", "application/json",
                "Accept", "application/json",
                "Authorization", "Bearer " + accessToken
        );
    }

    public static String productByIdEndpoint(int id) {
        return PRODUCTS_ENDPOINT + "/" + id;
    }

    public static String productSearchEndpoint(String query) {
        return PRODUCTS_ENDPOINT + "/search?q=" + query;
    }

    public static String productAddEndpoint() {
        return PRODUCTS_ENDPOINT + "/add";
    }

    public static String userByIdEndpoint(int id) {
        return USERS_ENDPOINT + "/" + id;
    }

    public static String userSearchEndpoint(String query) {
        return USERS_ENDPOINT + "/search?q=" + query;
    }

    public static String userAddEndpoint() {
        return USERS_ENDPOINT + "/add";
    }

    public static String postByIdEndpoint(int id) {
        return POSTS_ENDPOINT + "/" + id;
    }

    public static String postSearchEndpoint(String query) {
        return POSTS_ENDPOINT + "/search?q=" + query;
    }

    public static String postAddEndpoint() {
        return POSTS_ENDPOINT + "/add";
    }

    public static String recipeByIdEndpoint(int id) {
        return RECIPES_ENDPOINT + "/" + id;
    }

    public static String recipeSearchEndpoint(String query) {
        return RECIPES_ENDPOINT + "/search?q=" + query;
    }

    public static String recipeAddEndpoint() {
        return RECIPES_ENDPOINT + "/add";
    }

    public static String commentByIdEndpoint(int id) {
        return COMMENTS_ENDPOINT + "/" + id;
    }

    public static String commentsByPostIdEndpoint(int postId) {
        return COMMENTS_ENDPOINT + "/post/" + postId;
    }

    public static String commentAddEndpoint() {
        return COMMENTS_ENDPOINT + "/add";
    }
}
