package com.example.design2javafx;

import com.example.design2javafx.HorrorCharacters.HorrorCharacter;
import com.example.design2javafx.HorrorCharacters.Vulnerability;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SecondSceneController implements Initializable {
    @FXML
    public Spinner<HorrorCharacter> spinner1;
    @FXML
    public Spinner<Vulnerability> spinner2;
    @FXML
    public Button btnVulnerability;
    @FXML
    public Accordion accordion;

    public ArrayList<HorrorCharacter> horrorCharacters = new ArrayList<>();
    public Map<HorrorCharacter, VBox> hcBox = new HashMap<>();

    public Backend b = new Backend();
    public String binFileName = "HorrorCharBin.bin";


    public void addVulnerability()
    {
        HorrorCharacter hc = spinner1.getValue();
        Vulnerability v = spinner2.getValue();
        if (hc != null && v != null)
        {
            ArrayList<Vulnerability> vulnerabilities = hc.getVulnerabilities();
            vulnerabilities.add(v);
            hc.setVulnerabilities(vulnerabilities);
            spinner1.getValueFactory().setValue(hc);
            VBox vBox = hcBox.get(hc);
            if (vBox != null) {
                vBox.getChildren().add(new Label(v.name()));
            }
        }
    }


    public TitledPane makePane(HorrorCharacter horrorCharacter) {
        VBox vBox = new VBox(2);
        ArrayList<Label> vulnerabilityLabels = new ArrayList<>();
        if (horrorCharacter.getVulnerabilities() != null) {
            for (Vulnerability v : horrorCharacter.getVulnerabilities()) {
                Label vulnerabilityLabel = new Label(v.name());
                vulnerabilityLabels.add(vulnerabilityLabel);
            }
        }
        vBox.getChildren().addAll(vulnerabilityLabels);
        hcBox.put(horrorCharacter, vBox);
        return new TitledPane(horrorCharacter.getName(), vBox);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        horrorCharacters = AppState.hcs1;
        ObservableList<HorrorCharacter> observableList = FXCollections.observableArrayList(horrorCharacters);
        SpinnerValueFactory<HorrorCharacter> factory = new SpinnerValueFactory.ListSpinnerValueFactory<>(observableList);
        spinner1.setValueFactory(factory);

        ObservableList<Vulnerability> observableList2 = FXCollections.observableArrayList(Vulnerability.values());
        SpinnerValueFactory<Vulnerability> factory2 = new SpinnerValueFactory.ListSpinnerValueFactory<>(observableList2);
        spinner2.setValueFactory(factory2);
        for (HorrorCharacter hc : horrorCharacters)
        {
            accordion.getPanes().add(makePane(hc));
        }
    }

    public void Back() throws IOException {
        b.BinaryExport(binFileName, horrorCharacters);
        AppState.setRoot("hello-view");
    }
}
