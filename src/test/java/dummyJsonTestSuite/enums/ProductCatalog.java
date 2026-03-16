package dummyJsonTestSuite.enums;

public enum ProductCatalog {
    ESSENCE_MASCARA_LASH_PRINCESS(
            1,
            "Essence Mascara Lash Princess",
            "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
            "beauty",
            "Essence",
            "BEA-ESS-ESS-001"
    ),
    EYESHADOW_PALETTE_WITH_MIRROR(
            2,
            "Eyeshadow Palette with Mirror",
            "The Eyeshadow Palette with Mirror offers a versatile range of eyeshadow shades for creating stunning eye looks. With a built-in mirror, it's convenient for on-the-go makeup application.",
            "beauty",
            "Glamour Beauty",
            "BEA-GLA-EYE-002"
    ),
    POWDER_CANISTER(
            3,
            "Powder Canister",
            "The Powder Canister is a finely milled setting powder designed to set makeup and control shine. With a lightweight and translucent formula, it provides a smooth and matte finish.",
            "beauty",
            "Velvet Touch",
            "BEA-VEL-POW-003"
    );

    private final int id;
    private final String title;
    private final String description;
    private final String category;
    private final String brand;
    private final String sku;

    ProductCatalog(int id, String title, String description, String category, String brand, String sku) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.sku = sku;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getSku() {
        return sku;
    }
}
