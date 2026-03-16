package dummyJsonTestSuite.auth;

import java.util.Map;

public final class DummyJsonConfig {
    public static final String BASE_URL = "https://dummyjson.com";
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
