package dummyJsonTestSuite.config;

import java.util.Map;

public final class DummyJsonConfig {

    // URLs
    public static final String BASE_URL = "https://dummyjson.com";

    // endpoints
    public static final String LOGIN_ENDPOINT = "/auth/login";
    public static final String PRODUCTS_ENDPOINT = "/products";

    // verbs
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETTE";

    // credentials
    public static final String USERNAME = "emilys";
    public static final String PASSWORD = "emilyspass";

    private DummyJsonConfig() {
    }

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
}
