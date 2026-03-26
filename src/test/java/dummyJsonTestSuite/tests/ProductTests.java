package dummyjsontestsuite.tests;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.dto.ProductRequestDTO;
import dummyjsontestsuite.dto.ProductDTO;
import dummyjsontestsuite.enums.ProductCatalog;
import dummyjsontestsuite.enums.ValidationMessages;
import dummyjsontestsuite.utils.DummyJsonRestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class ProductTests extends BaseTest {
    private static final ProductCatalog EXPECTED_PRODUCT = ProductCatalog.ESSENCE_MASCARA_LASH_PRINCESS;
    private static final ProductDTO EXPECTED_PRODUCT_DTO = EXPECTED_PRODUCT.getProduct();
    private static final ProductCatalog SEARCH_EXPECTED_PRODUCT = ProductCatalog.EYESHADOW_PALETTE_WITH_MIRROR;
    private static final ProductDTO SEARCH_EXPECTED_PRODUCT_DTO = SEARCH_EXPECTED_PRODUCT.getProduct();

    @Test
    public void getAllProductsShouldMatchCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT,
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        List<Map<String, Object>> products = response.jsonPath().getList("products");
        Assert.assertNotNull(products, ValidationMessages.PRODUCTS_LIST_SHOULD_BE_PRESENT.getMessage());
        Assert.assertFalse(products.isEmpty(), ValidationMessages.PRODUCTS_LIST_SHOULD_NOT_BE_EMPTY.getMessage());

        Map<String, Object> firstProduct = products.get(0);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(((Number) firstProduct.get("id")).intValue(), EXPECTED_PRODUCT.getId(), ValidationMessages.PRODUCT_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.get("title"), EXPECTED_PRODUCT_DTO.getTitle(), ValidationMessages.PRODUCT_TITLE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.get("description"), EXPECTED_PRODUCT_DTO.getDescription(), ValidationMessages.PRODUCT_DESCRIPTION_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.get("category"), EXPECTED_PRODUCT_DTO.getCategory(), ValidationMessages.PRODUCT_CATEGORY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(((Number) firstProduct.get("price")).doubleValue(), EXPECTED_PRODUCT_DTO.getPrice(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_PRICE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(((Number) firstProduct.get("discountPercentage")).doubleValue(), EXPECTED_PRODUCT_DTO.getDiscountPercentage(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_DISCOUNT_PERCENTAGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(((Number) firstProduct.get("rating")).doubleValue(), EXPECTED_PRODUCT_DTO.getRating(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_RATING_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(((Number) firstProduct.get("stock")).intValue(), EXPECTED_PRODUCT_DTO.getStock(), ValidationMessages.PRODUCT_STOCK_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.get("brand"), EXPECTED_PRODUCT_DTO.getBrand(), ValidationMessages.PRODUCT_BRAND_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.get("sku"), EXPECTED_PRODUCT.getSku(), ValidationMessages.PRODUCT_SKU_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void getSingleProductShouldMatchProductCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT + "/" + EXPECTED_PRODUCT.getId(),
                "GET",
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.jsonPath().getInt("id"), EXPECTED_PRODUCT.getId(), ValidationMessages.PRODUCT_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getString("title"), EXPECTED_PRODUCT_DTO.getTitle(), ValidationMessages.PRODUCT_TITLE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getString("description"), EXPECTED_PRODUCT_DTO.getDescription(), ValidationMessages.PRODUCT_DESCRIPTION_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getString("category"), EXPECTED_PRODUCT_DTO.getCategory(), ValidationMessages.PRODUCT_CATEGORY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getDouble("price"), EXPECTED_PRODUCT_DTO.getPrice(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_PRICE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getDouble("discountPercentage"), EXPECTED_PRODUCT_DTO.getDiscountPercentage(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_DISCOUNT_PERCENTAGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getDouble("rating"), EXPECTED_PRODUCT_DTO.getRating(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_RATING_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getInt("stock"), EXPECTED_PRODUCT_DTO.getStock(), ValidationMessages.PRODUCT_STOCK_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getString("brand"), EXPECTED_PRODUCT_DTO.getBrand(), ValidationMessages.PRODUCT_BRAND_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getString("sku"), EXPECTED_PRODUCT.getSku(), ValidationMessages.PRODUCT_SKU_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void searchForProductShouldReturnRelevantResults() {

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT + "/search?q=Palette",
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        List<Map<String, Object>> products = response.jsonPath().getList("products");
        Assert.assertEquals(products.size(), 1, ValidationMessages.PRODUCT_EXACTLY_ONE_PRODUCT_RETURNED.getMessage());
        Assert.assertEquals(products.get(0).get("title"), SEARCH_EXPECTED_PRODUCT_DTO.getTitle());
    }

    @Test
    public void addNewProduct() {
        ProductRequestDTO newProduct = new ProductRequestDTO(
                "BMW Pencil",
                "A high-quality pencil made by BMW.",
                12.99
        );

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT + "/add",
                DummyJsonConfig.POST,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                newProduct
        );

        DummyJsonRestUtils.assertStatusCode(response, 201); // 201 - Created

        Assert.assertEquals(response.jsonPath().getString("title"), newProduct.getTitle());
        Assert.assertEquals(response.jsonPath().getString("description"), newProduct.getDescription());
    }

    @Test
    public void updateExistingProduct() {
        ProductRequestDTO updateProduct = new ProductRequestDTO(

                "Essence Monarch Mistress",
                "Night-out lashes",
                11.99
        );

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT + "/" + EXPECTED_PRODUCT.getId(),
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                updateProduct
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getString("title"), updateProduct.getTitle());
        Assert.assertEquals(response.jsonPath().getString("description"), updateProduct.getDescription());
        Assert.assertEquals(response.jsonPath().getString("price"), String.valueOf(updateProduct.getPrice()));

    }

    @Test
    public void deleteExistingProduct() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT + "/" + EXPECTED_PRODUCT.getId(),
                DummyJsonConfig.DELETE,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        /*
        Deleting a product will not delete it into the server.
        It will simulate a DELETE request and will return deleted product with isDeleted key set to true
         */
        DummyJsonRestUtils.assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getInt("id"), EXPECTED_PRODUCT.getId());
        Assert.assertEquals(response.jsonPath().getString("title"), EXPECTED_PRODUCT_DTO.getTitle());
        Assert.assertTrue(response.jsonPath().getBoolean("isDeleted"), "true");
    }

    @Test
    public void deleteNonExistingProductShouldReturnNotFound() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT + "/999",
                DummyJsonConfig.DELETE,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 404);

        Assert.assertEquals(response.jsonPath().getString("message"), ValidationMessages.PRODUCT_NOT_FOUND.getMessage());
    }

    @Test
    public void updateNonExistingProductShouldReturnNotFound() {
        ProductRequestDTO updateProduct = new ProductRequestDTO(
                "Non-existing Product",
                "This product does not exist.",
                9.99
        );

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.PRODUCTS_ENDPOINT + "/999",
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                updateProduct
        );

        DummyJsonRestUtils.assertStatusCode(response, 404);

        Assert.assertEquals(response.jsonPath().getString("message"), ValidationMessages.PRODUCT_NOT_FOUND.getMessage());
    }

}
