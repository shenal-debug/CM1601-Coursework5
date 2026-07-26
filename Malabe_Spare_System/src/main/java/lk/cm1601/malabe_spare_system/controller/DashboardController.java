package lk.cm1601.malabe_spare_system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        System.out.println("Add Part button clicked!");
    }

}