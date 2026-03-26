package dummyjsontestsuite.dto;

public class ProductDTO {
    private final String title;
    private final String description;
    private final String category;
    private final double price;
    private final double discountPercentage;
    private final double rating;
    private final int stock;
    private final String brand;

    public ProductDTO(
            String title,
            String description,
            String category,
            double price,
            double discountPercentage,
            double rating,
            int stock,
            String brand
    ) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
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

    public double getPrice() {
        return price;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public int getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }
}
