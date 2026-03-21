package dummyJsonTestSuite.enums;

public enum ResponseMessages {
    LOGIN_SUCCESS("Login successful. Access token received."),
    LOGIN_FAILURE("Login failed. Invalid credentials or server error."),
    PRODUCT_CREATED("Product created successfully."),
    PRODUCT_CREATION_FAILED("Failed to create product. Check request data and server status."),
    PRODUCT_UPDATED("Product updated successfully."),
    PRODUCT_UPDATE_FAILED("Failed to update product. Check request data and server status."),
    PRODUCT_DELETED("Product deleted successfully."),
    PRODUCT_DELETION_FAILED("Failed to delete product. Check product ID and server status.");

    private final String message;

    ResponseMessages(String message) {
        this.message = message;
    }

    public String getResponseMessages() {
        return message;
    }
}
