package edu.duke.ece651.team7.client.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UpgradeController implements Initializable {
    @FXML
    TextField terrUpgradeFrom, numberUnitsUpgraded,unitCurrentLevel, unitUpgradedLevel;
    @FXML
    ListView<String> upgradeList;

    private final Stage window;
    private ObservableList<String> list;

    public UpgradeController(Stage window){this.window = window;}

    @FXML
    public void clickUpgradeButton(){
        String record = "Choose "+ numberUnitsUpgraded.getText()+" level: "+unitCurrentLevel.getText()+" units from Territory: "+terrUpgradeFrom.getText() + " , upgrade to level: "+unitUpgradedLevel.getText();

        list.add(record);
        terrUpgradeFrom.clear();
        numberUnitsUpgraded.clear();
        unitCurrentLevel.clear();
        unitUpgradedLevel.clear();
    }

    @FXML
    public void clickFinishButton() throws IOException {

        window.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableArrayList();
        upgradeList.setItems(list);
    }

}