package com.example.kaspifx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private ListView<ProductCategory> categoryList;

    @FXML
    private ListView<Product> productList;

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label specsLabel;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Button addToCartButton;

    @FXML
    private Label cartTotalLabel;

    // Барлық категория / тауар тізімдері
    private final ObservableList<ProductCategory> categories = FXCollections.observableArrayList();
    private final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private final ObservableList<Product> productsByCategory = FXCollections.observableArrayList();

    // Корзина
    private double cartTotal = 0.0;
    private final Map<Product, Integer> cart = new LinkedHashMap<>();

    @FXML
    public void initialize() {
        addToCartButton.setDisable(true);

        initDemoData(); // әзірге БД-сыз нұсқа

        categoryList.setItems(categories);
        productList.setItems(productsByCategory);

        // Категория таңдалғанда
        categoryList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                filterProductsByCategory(newVal);
                clearProductDetails();
                if (!productsByCategory.isEmpty()) {
                    productList.getSelectionModel().selectFirst();
                }
            }
        });

        // Тауар таңдалғанда
        productList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                showProductDetails(newVal);
                addToCartButton.setDisable(false);
            } else {
                clearProductDetails();
                addToCartButton.setDisable(true);
            }
        });

        // Алғашқы категорияны таңдау
        if (!categories.isEmpty()) {
            categoryList.getSelectionModel().selectFirst();
        }

        updateCartTotalLabel();
    }

    private void initDemoData() {
        ProductCategory phones = new ProductCategory(1, "Смартфондар");
        ProductCategory tvs = new ProductCategory(2, "Теледидарлар");
        ProductCategory laptops = new ProductCategory(3, "Ноутбуктар");

        categories.addAll(phones, tvs, laptops);

        allProducts.addAll(
                new Product("iPhone 15", 600000, "128 GB, OLED, 6.1\"", "Жаңа модель, супер камера", phones),
                new Product("Samsung Galaxy S24", 500000, "256 GB, AMOLED, 6.5\"", "Жылдам процессор, жақсы батарея", phones),
                new Product("LG OLED 55\"", 700000, "4K, OLED, Smart TV", "Керемет сурет сапасы", tvs),
                new Product("Samsung QLED 50\"", 550000, "4K, QLED, Smart TV", "Жарқын экран, түрлі режимдер", tvs),
                new Product("MacBook Air M2", 750000, "8 GB RAM, 256 GB SSD", "Жеңіл, батареясы ұзаққа шыдайды", laptops),
                new Product("HP Pavilion 15", 420000, "16 GB RAM, 512 GB SSD", "Оқу және жұмысқа ыңғайлы", laptops)
        );
    }

    private void filterProductsByCategory(ProductCategory category) {
        productsByCategory.setAll(
                allProducts.filtered(p -> p.getCategory().equals(category))
        );
    }

    private void showProductDetails(Product product) {
        nameLabel.setText(product.getName());
        priceLabel.setText(String.format("%,.0f ₸", product.getPrice()));
        specsLabel.setText(product.getSpecs());
        descriptionArea.setText(product.getDescription());
    }

    private void clearProductDetails() {
        nameLabel.setText("");
        priceLabel.setText("");
        specsLabel.setText("");
        descriptionArea.clear();
    }

    private void updateCartTotalLabel() {
        int itemsCount = cart.values().stream().mapToInt(Integer::intValue).sum();
        cartTotalLabel.setText(
                String.format("Корзина: %d тауар, %,.0f ₸", itemsCount, cartTotal)
        );
    }

    @FXML
    private void onAddToCart() {
        Product selected = productList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            cart.merge(selected, 1, Integer::sum);  // quantity++
            cartTotal += selected.getPrice();
            updateCartTotalLabel();
        }
    }
}

