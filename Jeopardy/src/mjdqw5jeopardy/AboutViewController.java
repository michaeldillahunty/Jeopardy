/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author dillahuntym
 */
public class AboutViewController extends Switchable implements Initializable, PropertyChangeListener {

    @FXML
    private Button aboutBackBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aboutBackAction(ActionEvent event) {
        Switchable.switchTo("StartView");
    }
    
    public void propertyChange(PropertyChangeEvent evt){
       
    }
    
}
