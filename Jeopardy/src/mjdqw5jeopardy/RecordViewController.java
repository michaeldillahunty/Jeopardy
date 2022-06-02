/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dillahuntym
 */
public class RecordViewController extends Switchable implements Initializable, Serializable {

    @FXML
    private Text soloFinalScoreText;
    @FXML
    private Text soloQCorrectText;
    @FXML
    private Text soloFinalTimeText;
    @FXML
    private Button soloExitBtn;
    @FXML
    private Button saveScoreBtn;
    @FXML
    private Button openScoreBtn;

    
    RecordViewModel recordModel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void soloExitAction(ActionEvent event) {
        Alert soloExitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        soloExitAlert.setTitle("Exit Game");
        soloExitAlert.setHeaderText("Thanks for playing Jeopardy!");
        soloExitAlert.setContentText("Ready to quit?");
        soloExitAlert.showAndWait();
        if(soloExitAlert.showAndWait().get() == ButtonType.OK){
            Stage stage = (Stage) soloExitBtn.getScene().getWindow();
            stage.close();
        } else if(soloExitAlert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        }
    }

    
}
