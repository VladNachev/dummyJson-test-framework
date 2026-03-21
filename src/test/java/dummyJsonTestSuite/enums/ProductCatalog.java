package dummyJsonTestSuite.enums;

import dummyJsonTestSuite.dto.ProductDTO;

public enum ProductCatalog {
    ESSENCE_MASCARA_LASH_PRINCESS(
            1,
            "RCH45Q1A",
            new ProductDTO(
                    "Essence Mascara Lash Princess",
                    "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                    "beauty",
                    9.99,
                    7.17,
                    4.94,
                    5,
                    "Essence"
            )
    ),
    EYESHADOW_PALETTE_WITH_MIRROR(
            2,
            "BEA-GLA-EYE-002",
            new ProductDTO(
                    "Eyeshadow Palette with Mirror",
                    "The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it's convenient for on-the-go makeup application.",
                    "beauty",
                    19.99,
                    18.19,
                    2.86,
                    34,
                    "Glamour Beauty"
            )
    ),
    POWDER_CANISTER(
            3,
            "BEA-VEL-POW-003",
            new ProductDTO(
                    "Powder Canister",
                    "The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.",
                    "beauty",
                    14.99,
                    9.84,
                    4.64,
                    89,
                    "Velvet Touch"
            )
    );

    private final int id;
    private final String sku;
    private final ProductDTO product;

    ProductCatalog(int id, String sku, ProductDTO product) {
        this.id = id;
        this.sku = sku;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public ProductDTO getProduct() {
        return product;
    }
}
