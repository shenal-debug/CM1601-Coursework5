package lk.cm1601.malabe_spare_system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import lk.cm1601.malabe_spare_system.model.Part;

import lk.cm1601.malabe_spare_system.filehandler.InventoryFileHandler;

public class DashboardController {

    @FXML
    private TextField txtPartCode;

    @FXML
    private TextField txtPartName;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtDate;

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private TableView<?> tableParts;

    @FXML
    public void initialize() {

        System.out.println("Dashboard Loaded");

        cmbCategory.getItems().addAll(
                "Engine",
                "Electrical",
                "Body",
                "Accessories",
                "Suspension"
        );
    }

    @FXML
    private void handleAddPart() {

        if (txtPartCode.getText().isEmpty() ||
                txtPartName.getText().isEmpty() ||
                txtBrand.getText().isEmpty() ||
                txtPrice.getText().isEmpty() ||
                txtQuantity.getText().isEmpty() ||
                cmbCategory.getValue() == null ||
                txtDate.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields.");
            alert.showAndWait();
            return;
        }

        Part part = new Part(
                txtPartCode.getText(),
                txtPartName.getText(),
                txtBrand.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQuantity.getText()),
                cmbCategory.getValue(),
                txtDate.getText(),
                ""
        );

        InventoryFileHandler fileHandler = new InventoryFileHandler();
        fileHandler.savePart(part);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Part object created successfully.");
        alert.showAndWait();
    }

    @FXML
    private void handleLowStock() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Low Stock");
        alert.setHeaderText(null);
        alert.setContentText("Low Stock feature will be implemented in the next commit.");
        alert.showAndWait();

    }

    @FXML
    private void handleDealerSelection() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dealer Selection");
        alert.setHeaderText(null);
        alert.setContentText("Dealer Selection feature will be implemented in the next commit.");
        alert.showAndWait();

    }

    @FXML
    private void handlePointOfSale() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Point of Sale");
        alert.setHeaderText(null);
        alert.setContentText("Point of Sale feature will be implemented in the next commit.");
        alert.showAndWait();

    }

}