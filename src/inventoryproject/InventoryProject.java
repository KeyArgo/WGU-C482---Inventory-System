/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Argo
 */
public class InventoryProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/inventoryproject/main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Part Q = (new InHouse(1000, "Desktop RAM", 100.90, 50, 1, 1, 564));
        Inventory.addPart (Q);
        Part P = (new InHouse(1001, "Laptop RAM", 89.90, 50, 1, 1, 564));
        Inventory.addPart (P);
        Part O = (new InHouse(1002, "Motherboard", 111.90, 50, 1, 1, 568));
        Inventory.addPart (O);
        Part N = (new Outsourced(1003, "Desktop HardDrive", 111.90, 50, 1, 1, "ABC"));
        Inventory.addPart (N);
        Part M = (new Outsourced(1004, "Laptop HardDrive", 98.95, 50, 1, 1, "ABC"));
        Inventory.addPart (M);
        
        Product A = new Product(1003, "Desktop", 1442.90, 5, 1, 1);
        Product B = new Product(1004, "Laptop", 1299.90, 5, 1, 1);
        Inventory.addProduct (A);
        Inventory.addProduct (B);
        A.addAssociatedPart (Q);
        A.addAssociatedPart (O);
        A.addAssociatedPart (N);
        B.addAssociatedPart (M);
        A.addAssociatedPart (P);
        */
        launch(args);
        
    } 
}
