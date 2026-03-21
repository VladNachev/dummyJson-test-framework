package dummyJsonTestSuite.products;

import dummyJsonTestSuite.auth.DummyJsonRestUtils;
import dummyJsonTestSuite.auth.TokenProvider;
import dummyJsonTestSuite.config.DummyJsonConfig;
import dummyJsonTestSuite.dto.ProductRequestDTO;
import dummyJsonTestSuite.dto.ProductDTO;
import dummyJsonTestSuite.enums.ProductCatalog;
import dummyJsonTestSuite.enums.ValidationMessages;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class ProductTests {
    private String accessToken = TokenProvider.getAccessToken();
    private static final ProductCatalog EXPECTED_PRODUCT = ProductCatalog.ESSENCE_MASCARA_LASH_PRINCESS;
    private static final ProductDTO EXPECTED_PRODUCT_DTO = EXPECTED_PRODUCT.getProduct();

    @Test
    public void getAllProductsShouldReturnNonEmptyProductList() {
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
        softAssert.assertEquals(response.jsonPath().getDouble("price"), EXPECTED_PRODUCT_DTO.getPrice(), ValidationMessages.PRODUCT_PRICE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getDouble("discountPercentage"), EXPECTED_PRODUCT_DTO.getDiscountPercentage(), ValidationMessages.PRODUCT_DISCOUNT_PERCENTAGE_SHOULD_MATCH.getMessage());
        softAssert.assertEquals(response.jsonPath().getDouble("rating"), EXPECTED_PRODUCT_DTO.getRating(), ValidationMessages.PRODUCT_RATING_SHOULD_MATCH.getMessage());
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
        Assert.assertEquals(products.get(0).get("title"), EXPECTED_PRODUCT_DTO.getTitle());
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


}
