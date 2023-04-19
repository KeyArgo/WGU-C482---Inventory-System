/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Argo
 * 
 * 
 */
public class Inventory {
    
    
    //private static variables
    private static int currentPartID;
    private static int currentProductID;
    
    //ObservableLists
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partID) {
        for (Part P : allParts) {
            if (partID == P.getID()) {
                return P;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productID) {
                for (Product P : allProducts) {
            if (productID == P.getID()) {
                return P;
            }
        }
        return null;
    }

    public static int getCurrentPartID() {
        currentPartID++;
        return currentPartID;
    }
    public static int getCurrentProductID(){
        currentProductID++;
        return currentProductID;
    }
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> pList=FXCollections.observableArrayList();
        for (Part P : allParts){
            if (P.getName().contains(partName)){
                pList.add(P);
            }
        }
        return pList;
    }
    
        public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> pList=FXCollections.observableArrayList();
        for (Product P : allProducts){
            if (P.getName().contains(productName)){
                pList.add(P);
            }
        }
        return pList;
    }

    public static void updatePart(int index, Part part){
        allParts.set(index, part);
    }
    public static void updateProduct(int index, Product product){
        allProducts.set(index, product);
    }
    public static boolean deletePart(Part selectedPart){
         return allParts.remove (selectedPart);
    }
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove (selectedProduct);
    }
    
    //getters
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }    
    
    
}
