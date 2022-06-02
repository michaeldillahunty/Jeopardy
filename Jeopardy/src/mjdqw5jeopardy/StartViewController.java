/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author dillahuntym
 */
public class StartViewController extends Switchable implements Initializable {

    @FXML
    private Button startScreenExitBtn;
    @FXML
    private Button startScreenRulesBtn;
    @FXML
    private Button startAboutBtn;
    
    
    @FXML
    private SplitMenuButton gameModeSelect;
    @FXML
    private MenuItem gamemode1;
    @FXML
    private MenuItem gamemode2;
    
    
    private Alert emptyPlayerBox = new Alert(Alert.AlertType.WARNING);
    
    StartViewModel startView; 
    @FXML
    private Button viewScore;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        
    }       

    @FXML
    private void gameModeSelectAction(ActionEvent event) {
        gameModeSelect = new SplitMenuButton();  
                
    }
    
    @FXML
    private void gamemode1Action(ActionEvent event) {
        
        System.out.println("Gamemode 1 - Trivia SELECTED");
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText("What should we call you? ");
        
        Optional<String> nameInput = tid.showAndWait();
        
        if(nameInput.isPresent()){
            playerName = nameInput.get();
            System.out.println("Hello "+playerName+". Let's play Trivia");
            //Switchable.playerName = playerName;
            Switchable.switchTo("GameView");

        } 
        
    }
    
    @FXML
    private void gamemode2Action(ActionEvent event) {
        System.out.println("Gamemode 2 - Jeopardy SELECTED");

    /* reference for 2 input dialog box
        https://stackoverflow.com/questions/31556373/javafx-dialog-with-2-input-fields
    */
        
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Multiplayer Login");
        ButtonType loginButtonType = new ButtonType("Continue", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        
        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setVgap(15);
        gp.setPadding(new Insets(20, 100, 15, 15));
        
        TextField player1Text = new TextField();
        player1Text.setPromptText("Player 1");
        TextField player2Text = new TextField();
        player2Text.setPromptText("Player 2");

        gp.add(player1Text, 1, 0);
        gp.add(new Label("Player 1:"),0,0);
        gp.add(new Label("Player 2:"), 2, 0);
        gp.add(player2Text, 3, 0);

        dialog.getDialogPane().setContent(gp);
        
        emptyPlayerBox.setTitle("ERROR");
        emptyPlayerBox.setHeaderText("Enter at least 1 character in each box to continue");
        emptyPlayerBox.setContentText("Press OK to continue");
        
        Alert p2Empty = new Alert(Alert.AlertType.WARNING);

        Optional<Pair<String, String>> multiUserInput = dialog.showAndWait();
        if(multiUserInput.isPresent() && player1Text.getText().isEmpty() == false && player2Text.getText().isEmpty() == false){   
            Switchable.switchTo("GameView"); // MAKE THIS SWITCH TO MULTIPLAYER GAME SCREEN
            System.out.println("Successfully logged in");
        } else if (player1Text.getText().trim().isEmpty() && player2Text.getText().trim().isEmpty()){
            System.out.println("LOGIN FAILED");
        } else if (player1Text.getText().trim().isEmpty() && player2Text.getText().trim().isEmpty() == false){
            System.out.println("PLAYER 1 EMPTY");
            emptyPlayerBox.showAndWait();
        } else if (player1Text.getText().trim().isEmpty() == false && player2Text.getText().trim().isEmpty()){
            System.out.println("PLAYER 2 EMPTY");
            emptyPlayerBox.showAndWait();
        }
        
        
        
    }
    


    @FXML
    private void handleMainExit(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.WARNING);
        exitAlert.setTitle("Exit Game");
        exitAlert.setHeaderText("You're about to exit JEOPARDY");
        exitAlert.setContentText("Are you sure you want to exit?");
        if(exitAlert.showAndWait().get() == ButtonType.OK){
            Stage stage = (Stage) startScreenExitBtn.getScene().getWindow();
            stage.close();
        } else if(exitAlert.showAndWait().get() == ButtonType.CANCEL) {
            event.consume();
        }
    }

    @FXML
    private void handleRulesButton(ActionEvent event) {
        Alert rulesAlert = new Alert(AlertType.INFORMATION);
        rulesAlert.setTitle("Jeopardy Rules");
        rulesAlert.setHeaderText("How to play: ");
        rulesAlert.setContentText("(1) When you press start, input your name and press ok\n"
                + "(2) There are 4 categories and each has 5 levels ($100 being the lowest, $500 being the highest\n"
                + "(3) The higher the category, the tougher the question will be\n"
                + "(4) If you get the question RIGHT, you will get the selected number of points\n"
                + "(5) If you get the question WRONG, you will not get the points, however there will be no deduction\n"
                + "(6) There is an optional timer at the bottom of the game screen if you choose to time yourself\n"
                + "(7) You can quit at any time... ADD MORE");

        rulesAlert.show();
 
    }

    @FXML
    private void handleStartAbout(ActionEvent event) {
        System.out.println("About button clicked");
        Switchable.switchTo("AboutView");
    }

    
    
    
    
    @FXML
    private void viewScoreBtn(ActionEvent event) throws Exception {
        Alert viewScoreAlert = new Alert(Alert.AlertType.NONE);
        String tableString = ""; 
        ArrayList<String> getTableContent;
        viewScoreAlert.getDialogPane().setMinWidth(300);
        viewScoreAlert.getDialogPane().setMinHeight(500);
        viewScoreAlert.setTitle("Scores");
        
        
        
        String ssd = "";
        boolean isWorking;
        
        DataBaseConnection dbConnect = new DataBaseConnection();
        Connection connect = null;
        connect = dbConnect.getConnection(); 
        if (dbConnect.getConnection() == null){
//            System.out.println("DBfailed");
        } else {
//            System.out.println("success");
            ssd = dbConnect.constructUploadQueryString("test", 60);
            isWorking = dbConnect.uploadingNameScore(ssd);
        }
        getTableContent = dbConnect.viewTable(connect);
//        System.out.println("table content"+getTableContent.get(0));
        int i = 0;
        while(getTableContent.size() > i){
//            System.out.println("Table String 2: "+tableString);
            tableString = tableString+getTableContent.get(i)+"\n";
            i++;
        }
//        System.out.println("Table String"+tableString);
        viewScoreAlert.setContentText(tableString);
        
        viewScoreAlert.showAndWait();
    }

    

   

    
    
}
