/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryproject;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 *
 * @author Argo
 */
public class MainController implements Initializable {

    @FXML
    private TextField partSearchText;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<?, ?> partID;
    @FXML
    private TableColumn<?, ?> partName;
    @FXML
    private TableColumn<?, ?> partInventory;
    @FXML
    private TableColumn<?, ?> partPrice;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<?, ?> productID;
    @FXML
    private TableColumn<?, ?> productName;
    @FXML
    private TableColumn<?, ?> productInventory;
    @FXML
    private TableColumn<?, ?> productPrice;
    @FXML
    private TextField productSearchText;
    @FXML
    public Label resultsLBL;

    private static Part handOff = null;

    public static Part getHandOff() {
        return handOff;
    }

    private static Product prodHandOff = null;

    public static Product getProdHandOff() {
        return prodHandOff;
    }
    @FXML
    private Button partSearchButton;
    @FXML
    private Button productSearchButton;
    @FXML
    private Button partAddButton;
    @FXML
    private Button partModifyButton;
    @FXML
    private Button partDeleteButton;
    @FXML
    private Button productAddButton;
    @FXML
    private Button productModifyButton;
    @FXML
    private Button productDeleteButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partsTable.setItems(Inventory.getAllParts());
        productsTable.setItems(Inventory.getAllProducts());

        partID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        productID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Part> parts = Inventory.getAllParts();
        partsTable.setItems(parts);

        ObservableList<Product> products = Inventory.getAllProducts();
        productsTable.setItems(products);

    }

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException isString) {
            return false;
        }
    }

    @FXML
    private void onModifyPart(ActionEvent event) throws IOException {
        System.out.println("modifyPart");
        handOff = partsTable.getSelectionModel().getSelectedItem();
        if (handOff == null) {
            return;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/inventoryproject/modifyPart.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onDeletePart(ActionEvent event) {
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("Delete");
        alertConfirm.setHeaderText("Are you sure you would like to delete this part?");
        Optional<ButtonType> result = alertConfirm.showAndWait();
        if (partsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("No Part Selected");
            alert.showAndWait();
        }
        if (result.get() == ButtonType.OK) {
            if (partsTable.getSelectionModel().getSelectedItem() != null) {
                partsTable.getItems().removeAll(partsTable.getSelectionModel().getSelectedItem());
            }

        }
    }

    @FXML
    private void onAddPart(ActionEvent event) throws IOException {
        System.out.println("addPart");
        Parent root = FXMLLoader.load(getClass().getResource("/inventoryproject/addPart.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ObservableList<Part> parts = Inventory.getAllParts();
        partsTable.setItems(parts);
        ObservableList<Product> products = Inventory.getAllProducts();
        productsTable.setItems(products);
    }

    @FXML
    private void onModifyProduct(ActionEvent event) throws IOException {
        System.out.println("modifyPart");
        prodHandOff = productsTable.getSelectionModel().getSelectedItem();

        if (prodHandOff == null) {
            return;
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/inventoryproject/modifyProduct.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDeleteProduct(ActionEvent event) {
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("Delete");
        alertConfirm.setHeaderText("Are you sure you would like to delete this product?");
        Optional<ButtonType> result = alertConfirm.showAndWait();
        if (productsTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("No Part Selected");
            alert.showAndWait();
        }
        if (result.get() == ButtonType.OK) {
            if (productsTable.getSelectionModel().getSelectedItem() != null) {
                productsTable.getItems().removeAll(productsTable.getSelectionModel().getSelectedItem());
            }

        }
    }

    @FXML
    private void onAddProduct(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/inventoryproject/addProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        ObservableList<Part> parts = Inventory.getAllParts();
        partsTable.setItems(parts);
        ObservableList<Product> products = Inventory.getAllProducts();
        productsTable.setItems(products);
    }

    @FXML
    private void onExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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

    private ObservableList<Product> searchProduct(String partialName) {
        ObservableList<Product> productResults = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product pd : allProducts) {
            if (pd.getName().contains(partialName)) {
                productResults.add(pd);
            }
        }
        return productResults;
    }

    @FXML
    private void onProductSearch(ActionEvent event) {
        String q = productSearchText.getText();
        ObservableList<Product> product = searchProduct(q);
        productsTable.setItems(product);
        if (product.size() == 1) {
            resultsLBL.setText("1 PRODUCT RETURNED");
        } else if (product.size() > 1) {
            resultsLBL.setText(Integer.toString(product.size()) + " PRODUCTS RETURNED");
            partSearchText.setText("");
        }
        productSearchText.setText("");
    }
}
