package com.example.javafxapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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

    @FXML
    public void initialize() {
        // 🔹 Категорияларды фейк қылып өзіміз қосамыз
        categoryList.setItems(FXCollections.observableArrayList(
                "Laptops",
                "Smartphones",
                "Accessories"
        ));

        // Бірінші категорияны таңдай саламыз
        categoryList.getSelectionModel().selectFirst();
        showProductsFor("Laptops");

        // Категория ауысқан сайын — орталықтағы товарлар ауысады
        categoryList.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    if (newVal != null) showProductsFor(newVal);
                });

        updateTotal();
    }

    private void showProductsFor(String categoryName) {
        productGrid.getChildren().clear();

        // Категория бойынша фейк товарлар
        if (categoryName.equals("Laptops")) {
            addProductCard("MacBook Air M2", "Lightweight laptop for study and work", "550 000 ₸");
            addProductCard("ASUS TUF Gaming", "Powerful laptop for gaming", "480 000 ₸");
        } else if (categoryName.equals("Smartphones")) {
            addProductCard("iPhone 15", "New generation smartphone", "600 000 ₸");
            addProductCard("Samsung Galaxy S24", "Flagship Android phone", "550 000 ₸");
        } else if (categoryName.equals("Accessories")) {
            addProductCard("Logitech Mouse", "Wireless mouse", "15 000 ₸");
            addProductCard("Bluetooth Headphones", "Noise cancelling", "35 000 ₸");
        }
    }

    private void addProductCard(String name, String description, String priceText) {
        VBox box = new VBox(5);
        box.setPrefWidth(220);
        box.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #dddddd;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 10;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5,0,0,2);"
        );

        Label nameLabel = new Label(name);
        nameLabel.setWrapText(true);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");

        Text descText = new Text(description);
        descText.setWrappingWidth(200);

        Label priceLabel = new Label(priceText);
        priceLabel.setStyle("-fx-text-fill: #ff4b32; -fx-font-size: 14; -fx-font-weight: bold;");

        Button addBtn = new Button("Add to cart");
        addBtn.setOnAction(e -> {
            cartList.getItems().add(name + " – " + priceText);
            updateTotal();
        });

        box.getChildren().addAll(nameLabel, descText, priceLabel, addBtn);
        productGrid.getChildren().add(box);
    }

    private void updateTotal() {
        int count = cartList.getItems() == null ? 0 : cartList.getItems().size();
        totalLabel.setText(count + " item(s)");
    }
}
