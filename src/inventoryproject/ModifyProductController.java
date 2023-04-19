/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryproject;

import static inventoryproject.MainController.isNumber;
import javafx.collections.FXCollections;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Argo
 */
public class ModifyProductController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private TextField idText;
    @FXML
    private TextField maxText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField invText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField minText;
    private Product selectedProduct = null;
    @FXML
    private TableColumn<?, ?> productPartID;
    @FXML
    private TableColumn<?, ?> productPartName;
    @FXML
    private TableColumn<?, ?> productPartInventory;
    @FXML
    private TableColumn<?, ?> productPartPrice;
    @FXML
    private TableColumn<?, ?> partID;
    @FXML
    private TableColumn<?, ?> partName;
    @FXML
    private TableColumn<?, ?> partInventory;
    @FXML
    private TableColumn<?, ?> partPrice;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableView<Part> associatedPartsTable;
    @FXML
    private TextField partSearchText;
    @FXML
    private Label resultsLBL;
    @FXML
    private Button partSearchButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addButton;
    private ObservableList<Part> bottomTableList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(getClass().getName() + " initialized");

        partsTable.setItems(Inventory.getAllParts());

        selectedProduct = MainController.getProdHandOff();
        partID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productPartID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        productPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        idText.setText(Integer.toString(selectedProduct.getID()));
        nameText.setText(selectedProduct.getName());
        minText.setText(Integer.toString(selectedProduct.getMin()));
        maxText.setText(Integer.toString(selectedProduct.getMax()));
        invText.setText(Integer.toString(selectedProduct.getStock()));
        priceText.setText(Double.toString(selectedProduct.getPrice()));
        associatedPartsTable.setItems(bottomTableList);
        for (Part P : selectedProduct.getAllAssociatedParts()) {
            bottomTableList.add(P);
        }

    }

    @FXML
    private void onSave(ActionEvent event) {

        int idInt = 0;
        int invInt = 0;
        int minInt = 0;
        int maxInt = 0;
        int dynamicInt = 0;
        double priceDbl = 0.0;

        String id = idText.getText();
        String name = nameText.getText();
        String inv = invText.getText();
        String price = priceText.getText();
        String max = maxText.getText();
        String min = minText.getText();
        idInt = Integer.valueOf(id);
        invInt = Integer.valueOf(inv);
        minInt = Integer.valueOf(min);
        maxInt = Integer.valueOf(max);
        priceDbl = Double.valueOf(price);

        Inventory.deleteProduct(selectedProduct);
        Product prod = new Product(idInt, name, priceDbl, invInt, minInt, maxInt);

        if (bottomTableList.isEmpty() == false) {
            for (Part P : bottomTableList) {
                prod.addAssociatedPart(P);
            }
            Inventory.addProduct(prod);
            System.out.println("Product " + prod + " Added");
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("No Associated Part Selected");
            alert.setContentText("You must add at least one associated part!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("Cancel");
        alertConfirm.setHeaderText("Are you sure you would to cancel?");
        Optional<ButtonType> result = alertConfirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } else {
        }
    }

    @FXML
    private void onDelete(ActionEvent event) {
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("Delete");
        alertConfirm.setHeaderText("Are you sure you would like to delete this associated part?");
        Optional<ButtonType> result = alertConfirm.showAndWait();

        Part DEL = (Part) associatedPartsTable.getSelectionModel().getSelectedItem();
        if (DEL == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("No Assoicated Part Selected");
            alert.showAndWait();
        }
        if (result.get() == ButtonType.OK) {
            if (DEL != null) {
                bottomTableList.remove(DEL);
            }
        }
    }

    @FXML
    private void onAdd(ActionEvent event) {

        System.out.println("Add Associated Part Clicked");
        Part MV = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (MV == null) {
            return;
        }
        bottomTableList.add(MV);

    }

    @FXML
    private void onPartSearch(ActionEvent event) {
        String q = partSearchText.getText();

        ObservableList<Part> part = searchPart(q);

        partsTable.setItems(part);
        if (part.size() == 1) {
            resultsLBL.setText("1 PART RETURNED");
        } else if (part.size() > 1) {
            resultsLBL.setText(Integer.toString(part.size()) + " PARTS RETURNED");
            partSearchText.setText("");
        }
    }

    private ObservableList<Part> searchPart(String partialName) {
        ObservableList<Part> partResults = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part pt : allParts) {
            if (pt.getName().contains(partialName)) {
                partResults.add(pt);
            } else if (isNumber(partialName)) {
                int id = Integer.parseInt(partialName);
                if (pt.getID() == id) {
                    partResults.add(pt);
                }
            }
        }
        return partResults;
    }
}
