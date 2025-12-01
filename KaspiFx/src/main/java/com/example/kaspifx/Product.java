package com.example.kaspifx;

public class Product {
    private final String name;
    private final double price;
    private final String specs;
    private final String description;
    private final ProductCategory category;

    public Product(String name, double price, String specs, String description, ProductCategory category) {
        this.name = name;
        this.price = price;
        this.specs = specs;
        this.description = description;
        this.category = category;
    }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public String getSpecs() { return specs; }

    public String getDescription() { return description; }

    public ProductCategory getCategory() { return category; }

    @Override
    public String toString() {
        return name; // productList ішінде атауы ғана шығу үшін
    }
}

