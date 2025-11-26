package com.example.design2javafx;

import com.example.design2javafx.HorrorCharacters.HorrorCharacter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AppState {
    public static ArrayList<HorrorCharacter> hcs1;
    public static Stage stage;

    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        stage.setScene(new Scene(root));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppState.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
