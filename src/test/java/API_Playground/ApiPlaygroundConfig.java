package API_Playground;

import java.util.Map;

public final class ApiPlaygroundConfig {
    public static final String BASE_URL = "https://testing-ap-iground-dnd1r1gih-vidko-videvs-projects.vercel.app/api";
    public static final String STANDARD_USER_EMAIL = "user@test.com";
    public static final String STANDARD_USER_PASSWORD = "User123!";

    private ApiPlaygroundConfig() {
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
