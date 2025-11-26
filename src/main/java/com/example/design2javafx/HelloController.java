package com.example.design2javafx;

import com.example.design2javafx.HorrorCharacters.HorrorCharacter;
import com.example.design2javafx.HorrorCharacters.Vampire;
import com.example.design2javafx.HorrorCharacters.Werewolf;
import com.example.design2javafx.HorrorCharacters.Zombie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public Backend b = new Backend();
    public String binFileName = "HorrorCharBin.bin";
    public File binFile = new File(binFileName);
    public ArrayList<HorrorCharacter> hcs = new ArrayList<>();
    @FXML
    public ComboBox<String> cbxCharType;
    @FXML
    public ListView<String> ltvEditNames;
    public ObservableList<String> list;
    @FXML
    public TextField txtNameChar;
    @FXML
    public TextField txtHP;
    @FXML
    public TextField txtPW;
    @FXML
    public Button btnCreateChar;
    @FXML
    public Button btnDeleteChar;
    @FXML
    public TextField txtDelete;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        b.createFile(binFile);
        ObservableList<String> options = FXCollections.observableArrayList("Vampire", "Werewolf", "Zombie");
        cbxCharType.setItems(options);
        ltvEditNames.setEditable(true);
        ltvEditNames.setCellFactory(TextFieldListCell.forListView());
        ltvEditNames.setOnEditCommit(event -> {
            int charIndex = event.getIndex();
            String newName = event.getNewValue();
            UpdateCharacterName(charIndex, newName);
        });
        loadListView();
    }

    @FXML
    public void CreateCharacter()
    {
        if (txtHP.getText().isEmpty() || txtPW.getText().isEmpty() || txtNameChar.getText().isEmpty() || cbxCharType.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Information");
            alert.setContentText("Please fill out all required information");
            alert.setHeaderText("Missing Information");
            alert.showAndWait();
            return;
        }
        switch (cbxCharType.getValue())
        {
            case "Vampire":
                Vampire vampire = new Vampire(txtNameChar.getText(), Integer.parseInt(txtHP.getText()), Vampire.accessVampireVulnerabilities(), Integer.parseInt(txtPW.getText()), false, false, 1);
                hcs.add(vampire);
                break;
            case "Werewolf":
                Werewolf werewolf = new Werewolf(txtNameChar.getText(), Integer.parseInt(txtHP.getText()), Werewolf.accessWerewolfVulnerabilities(), Integer.parseInt(txtPW.getText()), false, 1);
                hcs.add(werewolf);
                break;
            case "Zombie":
                Zombie zombie = new Zombie(txtNameChar.getText(), Integer.parseInt(txtHP.getText()), Zombie.accessZombieVulnerabilities(), Integer.parseInt(txtPW.getText()), false);
                hcs.add(zombie);
                break;
        }
        AppState.hcs1 = hcs;
        b.BinaryExport(binFileName, hcs);
        loadListView();
    }

    @FXML
    public void DeleteCharacter()
    {
        int index = Integer.parseInt(txtDelete.getText());
        hcs.remove(index);
        AppState.hcs1 = hcs;
        b.BinaryExport(binFileName, hcs);
        loadListView();
    }

    @FXML
    public void UpdateCharacterName(int index, String newName)
    {
        list.set(index, newName);
        hcs.get(index).setName(newName);
        AppState.hcs1 = hcs;
        b.BinaryExport(binFileName, hcs);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Updated");
        alert.setContentText("Updated Character Name");
        alert.setHeaderText("Updated Name");
        alert.showAndWait();
    }

    public void loadListView() {
        try {
            b.BinaryImport(binFileName, hcs);
            AppState.hcs1 = hcs;
            ArrayList<String> tempList = new ArrayList<>();
            for (HorrorCharacter hc : hcs)
            {
                tempList.add(hc.getName());
            }
            list = FXCollections.observableArrayList(tempList);
            ltvEditNames.setItems(list);
            ltvEditNames.setEditable(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void Next() throws IOException {
        AppState.setRoot("secondScene");
    }

}