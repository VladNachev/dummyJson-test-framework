package dummyjsontestsuite.enums;

public enum ValidationMessages {
    ACCESS_TOKEN_SHOULD_BE_RETURNED("Access token should be returned."),
    ACCESS_TOKEN_SHOULD_NOT_BE_BLANK("Access token should not be blank."),
    LOGIN_RESPONSE_SHOULD_CONTAIN_ACCESS_TOKEN("Login response should contain accessToken."),
    PRODUCTS_LIST_SHOULD_BE_PRESENT("Products list should be present in the response."),
    PRODUCTS_LIST_SHOULD_NOT_BE_EMPTY("Products list should not be empty."),
    PRODUCT_ID_SHOULD_MATCH("Product id should match."),
    PRODUCT_TITLE_SHOULD_MATCH("Product title should match."),
    PRODUCT_DESCRIPTION_SHOULD_MATCH("Product description should match."),
    PRODUCT_CATEGORY_SHOULD_MATCH("Product category should match."),
    PRODUCT_PRICE_SHOULD_MATCH("Product price should match."),
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
