package lk.cm1601.malabe_spare_system.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.cm1601.malabe_spare_system.filehandler.InventoryFileHandler;
import lk.cm1601.malabe_spare_system.model.Part;
import lk.cm1601.malabe_spare_system.filehandler.AuditLogHandler;

public class PosController {

    @FXML
    private TableView<Part> tableInventory;

    @FXML
    private TableColumn<Part, String> colCode;

    @FXML
    private TableColumn<Part, String> colName;

    @FXML
    private TableColumn<Part, Double> colPrice;

    @FXML
    private TableColumn<Part, Integer> colQuantity;

    @FXML
    private TableColumn<Part, String> colCategory;

    @FXML
    private TextField txtQuantity;

    @FXML
    private Label lblTotal;

    private double total = 0;

    private Part cartPart = null;

    private int cartQuantity = 0;

    private int cartItems = 0;

    @FXML
    public void initialize() {

        colCode.setCellValueFactory(new PropertyValueFactory<>("partCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("partName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        InventoryFileHandler fileHandler = new InventoryFileHandler();

        ObservableList<Part> partList =
                FXCollections.observableArrayList(fileHandler.getAllParts());

        tableInventory.setItems(partList);

    }

    @FXML
    private void handleAddToCart() {

        Part selectedPart = tableInventory.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Point Of Sale");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part.");
            alert.showAndWait();
            return;

        }

        if (txtQuantity.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Point Of Sale");
            alert.setHeaderText(null);
            alert.setContentText("Please enter quantity.");
            alert.showAndWait();
            return;

        }

        int qty = Integer.parseInt(txtQuantity.getText());

        if (qty > selectedPart.getQuantity()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Point Of Sale");
            alert.setHeaderText(null);
            alert.setContentText("Not enough stock available.");
            alert.showAndWait();
            return;

        }

        double subTotal = qty * selectedPart.getPrice();

        String message = "Item added to cart.";

        if (qty >= 5) {

            subTotal = subTotal * 0.90;

            message = "Item added to cart.\n\nBulk Discount Applied (10%).";

        }

        total += subTotal;

        cartPart = selectedPart;
        cartQuantity = qty;

        cartItems++;

        lblTotal.setText(String.format("%.2f", total));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cart");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    @FXML
    private void handleCheckout() {

        if (cartPart == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Checkout");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty.");
            alert.showAndWait();
            return;

        }

        int remainingStock = cartPart.getQuantity() - cartQuantity;

        Part updatedPart = new Part(

                cartPart.getPartCode(),
                cartPart.getPartName(),
                cartPart.getBrand(),
                cartPart.getPrice(),
                remainingStock,
                cartPart.getCategory(),
                cartPart.getDate(),
                cartPart.getImage()

        );

        InventoryFileHandler fileHandler = new InventoryFileHandler();

        fileHandler.updatePart(updatedPart);

        AuditLogHandler auditLogHandler = new AuditLogHandler();

        auditLogHandler.writeLog(
                "CHECKOUT",
                cartPart.getPartCode() +
                        " - Qty: " + cartQuantity +
                        " - Total: Rs. " + String.format("%.2f", total)
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checkout");
        alert.setHeaderText("Purchase Successful");
        alert.setContentText("Total Amount : Rs. " + String.format("%.2f", total));
        alert.showAndWait();

        tableInventory.setItems(
                FXCollections.observableArrayList(fileHandler.getAllParts())
        );

        total = 0;
        cartItems = 0;
        cartPart = null;
        cartQuantity = 0;

        lblTotal.setText("0.00");
        txtQuantity.clear();
        tableInventory.getSelectionModel().clearSelection();

    }

}