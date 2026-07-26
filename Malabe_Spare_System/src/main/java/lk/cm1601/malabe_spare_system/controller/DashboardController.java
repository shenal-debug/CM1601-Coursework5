package lk.cm1601.malabe_spare_system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import lk.cm1601.malabe_spare_system.model.Part;

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

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Part object created successfully.");
        alert.showAndWait();
    }

}