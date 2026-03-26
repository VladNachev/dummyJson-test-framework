package dummyjsontestsuite.dto;

public class ProductRequestDTO {
    private final String title;
    private final String description;
    private final double price;

    public ProductRequestDTO(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
