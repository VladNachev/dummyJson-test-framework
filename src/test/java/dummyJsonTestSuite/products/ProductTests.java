package dummyJsonTestSuite.products;

import dummyJsonTestSuite.auth.DummyJsonConfig;
import dummyJsonTestSuite.auth.DummyJsonRestUtils;
import dummyJsonTestSuite.auth.TokenProvider;
import dummyJsonTestSuite.enums.ProductCatalog;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ProductTests {
    private static final ProductCatalog EXPECTED_PRODUCT = ProductCatalog.ESSENCE_MASCARA_LASH_PRINCESS;

    @Test
    public void getAllProductsShouldReturnNonEmptyProductList() {
        String accessToken = TokenProvider.getAccessToken();

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                "/products",
                "GET",
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        List<Map<String, Object>> products = response.jsonPath().getList("products");
        Assert.assertNotNull(products, "Products list should be present in the response.");
        Assert.assertFalse(products.isEmpty(), "Products list should not be empty.");
    }

    @Test
    public void getSingleProductShouldMatchProductCatalog() {
        String accessToken = TokenProvider.getAccessToken();

        Response response = DummyJsonRestUtils.sendRequest(
                DummyJsonConfig.BASE_URL,
                "/products/" + EXPECTED_PRODUCT.getId(),
                "GET",
                DummyJsonConfig.authorizedJsonHeaders(accessToken),
                null
        );

        DummyJsonRestUtils.assertStatusCode(response, 200);

        Assert.assertEquals(response.jsonPath().getInt("id"), EXPECTED_PRODUCT.getId(), "Product id should match.");
        Assert.assertEquals(response.jsonPath().getString("title"), EXPECTED_PRODUCT.getTitle(), "Product title should match.");
        Assert.assertEquals(response.jsonPath().getString("description"), EXPECTED_PRODUCT.getDescription(), "Product description should match.");
        Assert.assertEquals(response.jsonPath().getString("category"), EXPECTED_PRODUCT.getCategory(), "Product category should match.");
        Assert.assertEquals(response.jsonPath().getString("brand"), EXPECTED_PRODUCT.getBrand(), "Product brand should match.");
        Assert.assertEquals(response.jsonPath().getString("sku"), EXPECTED_PRODUCT.getSku(), "Product SKU should match.");
    }
}
