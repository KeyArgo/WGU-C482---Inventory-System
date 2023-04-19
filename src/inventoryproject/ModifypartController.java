/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryproject;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Argo
 */
public class ModifypartController implements Initializable {

    @FXML
    private RadioButton inhouseRB;
    @FXML
    private RadioButton outsourcedRB;
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField invText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField maxText;
    @FXML
    private TextField minText;
    @FXML
    private Part Part = null;
    @FXML
    private Part selectedPart;
    @FXML
    private ToggleGroup type;
    @FXML
    private TextField machineIDText;
    @FXML
    private Label dynamicLBL;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        int dynamicInt = 0;
        System.out.println(getClass().getName() + " initialized");
        Part = MainController.getHandOff();

        idText.setText(Integer.toString(Part.getID()));
        nameText.setText(Part.getName());
        minText.setText(Integer.toString(Part.getMin()));
        maxText.setText(Integer.toString(Part.getMax()));
        invText.setText(Integer.toString(Part.getStock()));
        priceText.setText(Double.toString(Part.getPrice()));

        if (Part instanceof InHouse) {
            inhouseRB.setSelected(true);
            InHouse ih = (InHouse) Part;
            machineIDText.setText(Integer.toString(ih.getMachineID()));
        }

        if (Part instanceof Outsourced) {
            outsourcedRB.setSelected(true);
            Outsourced os = (Outsourced) Part;
            machineIDText.setText(os.getCompanyName());
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
        String dynamic = machineIDText.getText();
        idInt = Integer.valueOf(id);
        invInt = Integer.valueOf(inv);
        minInt = Integer.valueOf(min);
        maxInt = Integer.valueOf(max);
        priceDbl = Double.valueOf(price);

        Inventory.deletePart(Part);
        if (inhouseRB.isSelected() == true) {
            dynamicInt = Integer.valueOf(dynamic);
            Part in = (new InHouse(idInt, name, priceDbl, invInt, minInt, maxInt, dynamicInt));
            Inventory.addPart(in);
            System.out.println(dynamic);
        }

        if (outsourcedRB.isSelected() == true) {
            Part out = (new Outsourced(idInt, name, priceDbl, invInt, minInt, maxInt, dynamic));
            Inventory.addPart(out);
            System.out.println(dynamic);
        }
        Stage stage = (Stage) inhouseRB.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("Cancel");
        alertConfirm.setHeaderText("Are you sure you would to cancel?");
        Optional<ButtonType> result = alertConfirm.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) inhouseRB.getScene().getWindow();
            stage.close();
        } else {
        }
    }

    @FXML
    private void onInhouseRB(ActionEvent event) {
        System.out.println("inhouse");
        dynamicLBL.setText("Machine ID");
        //System.out.println(test);
    }

    @FXML
    private void onOutsourcedRB(ActionEvent event) {
        System.out.println("outsourced");
        dynamicLBL.setText("Company Name");
        //System.out.println(test);
    }

}
