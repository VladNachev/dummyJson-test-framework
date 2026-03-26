package dummyjsontestsuite.tests;

import dummyjsontestsuite.config.DummyJsonConfig;
import dummyjsontestsuite.dto.ProductRequestDTO;
import dummyjsontestsuite.dto.ProductDTO;
import dummyjsontestsuite.dto.ProductsResponseDTO;
import dummyjsontestsuite.enums.ProductCatalog;
import dummyjsontestsuite.enums.ValidationMessages;
import dummyjsontestsuite.utils.DummyJsonRestUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

        ProductsResponseDTO productsResponse = response.as(ProductsResponseDTO.class);
        Assert.assertNotNull(productsResponse.getProducts(), ValidationMessages.PRODUCTS_LIST_SHOULD_BE_PRESENT.getMessage());
        Assert.assertFalse(productsResponse.getProducts().isEmpty(), ValidationMessages.PRODUCTS_LIST_SHOULD_NOT_BE_EMPTY.getMessage());

        ProductDTO firstProduct = productsResponse.getProducts().get(0);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(firstProduct.getId(), EXPECTED_PRODUCT_DTO.getId(), ValidationMessages.PRODUCT_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getTitle(), EXPECTED_PRODUCT_DTO.getTitle(), ValidationMessages.PRODUCT_TITLE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getDescription(), EXPECTED_PRODUCT_DTO.getDescription(), ValidationMessages.PRODUCT_DESCRIPTION_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getCategory(), EXPECTED_PRODUCT_DTO.getCategory(), ValidationMessages.PRODUCT_CATEGORY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getPrice(), EXPECTED_PRODUCT_DTO.getPrice(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_PRICE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getDiscountPercentage(), EXPECTED_PRODUCT_DTO.getDiscountPercentage(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_DISCOUNT_PERCENTAGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getRating(), EXPECTED_PRODUCT_DTO.getRating(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_RATING_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getStock(), EXPECTED_PRODUCT_DTO.getStock(), ValidationMessages.PRODUCT_STOCK_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getBrand(), EXPECTED_PRODUCT_DTO.getBrand(), ValidationMessages.PRODUCT_BRAND_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(firstProduct.getSku(), EXPECTED_PRODUCT_DTO.getSku(), ValidationMessages.PRODUCT_SKU_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void getSingleProductShouldMatchProductCatalog() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.productByIdEndpoint(EXPECTED_PRODUCT.getId()),
                "GET",
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);


        ProductDTO productDTO = response.as(ProductDTO.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(productDTO.getId(), EXPECTED_PRODUCT_DTO.getId(), ValidationMessages.PRODUCT_ID_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getTitle(), EXPECTED_PRODUCT_DTO.getTitle(), ValidationMessages.PRODUCT_TITLE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getDescription(), EXPECTED_PRODUCT_DTO.getDescription(), ValidationMessages.PRODUCT_DESCRIPTION_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getCategory(), EXPECTED_PRODUCT_DTO.getCategory(), ValidationMessages.PRODUCT_CATEGORY_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getPrice(), EXPECTED_PRODUCT_DTO.getPrice(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_PRICE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getDiscountPercentage(), EXPECTED_PRODUCT_DTO.getDiscountPercentage(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_DISCOUNT_PERCENTAGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getRating(), EXPECTED_PRODUCT_DTO.getRating(), DECIMAL_TOLERANCE, ValidationMessages.PRODUCT_RATING_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getStock(), EXPECTED_PRODUCT_DTO.getStock(), ValidationMessages.PRODUCT_STOCK_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getBrand(), EXPECTED_PRODUCT_DTO.getBrand(), ValidationMessages.PRODUCT_BRAND_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(productDTO.getSku(), EXPECTED_PRODUCT_DTO.getSku(), ValidationMessages.PRODUCT_SKU_SHOULD_MATCH.getMessage());

        softAssert.assertAll();
    }

    @Test
    public void searchForProductShouldReturnRelevantResults() {

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.productSearchEndpoint("Palette"),
                DummyJsonConfig.GET,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        ProductsResponseDTO productsResponse = response.as(ProductsResponseDTO.class);
        Assert.assertEquals(productsResponse.getProducts().size(), 1, ValidationMessages.PRODUCT_EXACTLY_ONE_PRODUCT_RETURNED.getMessage());
        Assert.assertEquals(productsResponse.getProducts().get(0).getTitle(), SEARCH_EXPECTED_PRODUCT_DTO.getTitle(), ValidationMessages.PRODUCT_TITLE_SHOULD_MATCH.getMessage());
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
                DummyJsonConfig.productAddEndpoint(),
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
                DummyJsonConfig.productByIdEndpoint(EXPECTED_PRODUCT.getId()),
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
                DummyJsonConfig.productByIdEndpoint(EXPECTED_PRODUCT.getId()),
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
        Assert.assertTrue(response.jsonPath().getBoolean("isDeleted")); // should return true
    }

    @Test
    public void deleteNonExistingProductShouldReturnNotFound() {
        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                DummyJsonConfig.productByIdEndpoint(999),
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
                DummyJsonConfig.productByIdEndpoint(999),
                DummyJsonConfig.PUT,
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                updateProduct
        );

        DummyJsonRestUtils.assertStatusCode(response, 404);

        Assert.assertEquals(response.jsonPath().getString("message"), ValidationMessages.PRODUCT_NOT_FOUND.getMessage());
    }

}
