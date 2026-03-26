package dummyjsontestsuite.enums;

public enum ValidationMessages {
    ACCESS_TOKEN_SHOULD_BE_RETURNED("Access token should be returned."),
    ACCESS_TOKEN_SHOULD_NOT_BE_BLANK("Access token should not be blank."),
    LOGIN_RESPONSE_SHOULD_CONTAIN_ACCESS_TOKEN("Login response should contain accessToken."),
    USERS_LIST_SHOULD_BE_PRESENT("Users list should be present in the response."),
    USERS_LIST_SHOULD_NOT_BE_EMPTY("Users list should not be empty."),
    USER_FIRST_NAME_SHOULD_MATCH("User first name should match."),
    USER_LAST_NAME_SHOULD_MATCH("User last name should match."),
    USER_MAIDEN_NAME_SHOULD_MATCH("User maiden name should match."),
    USER_AGE_SHOULD_MATCH("User age should match."),
    USER_GENDER_SHOULD_MATCH("User gender should match."),
    USER_EMAIL_SHOULD_MATCH("User email should match."),
    USER_PHONE_SHOULD_MATCH("User phone should match."),
    USER_USERNAME_SHOULD_MATCH("User username should match."),
    USER_PASSWORD_SHOULD_MATCH("User password should match."),
    USER_BIRTH_DATE_SHOULD_MATCH("User birth date should match."),
    USER_IMAGE_SHOULD_MATCH("User image should match."),
    USER_BLOOD_GROUP_SHOULD_MATCH("User blood group should match."),
    USER_HEIGHT_SHOULD_MATCH("User height should match."),
    USER_WEIGHT_SHOULD_MATCH("User weight should match."),
    USER_EYE_COLOR_SHOULD_MATCH("User eye color should match."),
    USER_ROLE_SHOULD_MATCH("User role should match."),
    PRODUCTS_LIST_SHOULD_BE_PRESENT("Products list should be present in the response."),
    PRODUCTS_LIST_SHOULD_NOT_BE_EMPTY("Products list should not be empty."),
    PRODUCT_ID_SHOULD_MATCH("Product id should match."),
    PRODUCT_TITLE_SHOULD_MATCH("Product title should match."),
    PRODUCT_DESCRIPTION_SHOULD_MATCH("Product description should match."),
    PRODUCT_CATEGORY_SHOULD_MATCH("Product category should match."),
    PRODUCT_PRICE_SHOULD_MATCH("Product price should match."),
    PRODUCT_NOT_FOUND("Product with id '999' not found"),
    PRODUCT_DISCOUNT_PERCENTAGE_SHOULD_MATCH("Product discount percentage should match."),
    PRODUCT_RATING_SHOULD_MATCH("Product rating should match."),
    PRODUCT_STOCK_SHOULD_MATCH("Product stock should match."),
    PRODUCT_BRAND_SHOULD_MATCH("Product brand should match."),
    PRODUCT_SKU_SHOULD_MATCH("Product SKU should match."),
    PRODUCT_EXACTLY_ONE_PRODUCT_RETURNED("Expected exactly one product in search results.");

    private final String message;

    ValidationMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
