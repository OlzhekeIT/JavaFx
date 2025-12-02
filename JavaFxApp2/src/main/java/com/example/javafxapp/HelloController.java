package com.example.javafxapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;

public class HelloController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> categoryList;

    @FXML
    private FlowPane productGrid;

    @FXML
    private ListView<String> cartList;

    @FXML
    private Label totalLabel;


    private int totalSum = 0;


    private final UiShopService shopService = new UiShopService();

    @FXML
    public void initialize() {

        categoryList.setItems(FXCollections.observableArrayList(
                shopService.getCategories()
        ));


        categoryList.getSelectionModel().selectFirst();
        String firstCategory = categoryList.getSelectionModel().getSelectedItem();
        if (firstCategory != null) {
            showProductsFor(firstCategory);
        }


        categoryList.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        showProductsFor(newVal);
                    }
                });


        searchField.setOnAction(e -> filterProducts());

        updateTotal();
    }


    private void filterProducts() {
        String text = searchField.getText();
        if (text == null || text.isBlank()) {
            // Егер бос болса – ағымдағы категорияны қайта көрсетеміз
            String currentCat = categoryList.getSelectionModel().getSelectedItem();
            if (currentCat != null) showProductsFor(currentCat);
            return;
        }

        List<UiShopService.ProductItem> found = shopService.searchProducts(text);
        productGrid.getChildren().clear();
        for (UiShopService.ProductItem item : found) {
            addProductCard(item);
        }
    }


    private void showProductsFor(String categoryName) {
        productGrid.getChildren().clear();

        List<UiShopService.ProductItem> items =
                shopService.getProductsByCategory(categoryName);

        for (UiShopService.ProductItem item : items) {
            addProductCard(item);
        }
    }


    private void addProductCard(UiShopService.ProductItem product) {

        VBox box = new VBox(5);
        box.setPrefWidth(230);
        box.getStyleClass().add("product-card");

        URL imgUrl = HelloApplication.class.getResource(product.getImagePath());
        if (imgUrl != null) {
            ImageView imageView =
                    new ImageView(new Image(imgUrl.toExternalForm()));
            imageView.setFitWidth(180);
            imageView.setPreserveRatio(true);
            box.getChildren().add(imageView);
        } else {
            System.out.println("Image not found: " + product.getImagePath());
        }

        Label nameLabel = new Label(product.getName());
        nameLabel.setWrapText(true);
        nameLabel.getStyleClass().add("product-name");

        Text descText = new Text(product.getDescription());
        descText.setWrappingWidth(210);

        Label priceLabel = new Label(product.getPriceText());
        priceLabel.getStyleClass().add("product-price");

        Button addBtn = new Button("Add to cart");
        addBtn.getStyleClass().add("add-btn");
        addBtn.setOnAction(e -> {
            cartList.getItems().add(
                    product.getName() + " – " + product.getPriceText()
            );
            totalSum += product.getPriceValue();
            updateTotal();
        });

        box.getChildren().addAll(nameLabel, descText, priceLabel, addBtn);
        productGrid.getChildren().add(box);
    }


    private void updateTotal() {
        int count = (cartList.getItems() == null)
                ? 0
                : cartList.getItems().size();
        totalLabel.setText(count + " item(s) – " + totalSum + " ₸");
    }
}
