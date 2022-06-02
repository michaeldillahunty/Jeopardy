/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
/**
 * FXML Controller class
 *
 * @author dillahuntym
 * 
 */


/*
    SINGLE PLAYER GAME VIEW
*/

public class GameViewController extends Switchable implements Initializable, PropertyChangeListener {

    @FXML
    private Button gameBackBtn;
    @FXML
    private Button gameExitBtn;
    
    /* sports buttons */
    @FXML
    private Button sportsOneBtn;
    @FXML
    private Button sportsTwoBtn;
    @FXML
    private Button sportsThreeBtn;
    @FXML
    private Button sportsFourBtn;
    @FXML
    private Button sportsFiveBtn;
    
    /* music buttons */
    @FXML
    private Button musicOneBtn;
    @FXML
    private Button musicTwoBtn;
    @FXML
    private Button musicThreeBtn;
    @FXML
    private Button musicFourBtn;
    @FXML
    private Button musicFiveBtn;
    
    
    @FXML
    private Button finishGameBtn;
    
    @FXML
    private Button moviesOneBtn;
    @FXML
    private Button moviesTwoBtn;
    @FXML
    private Button moviesThreeBtn;
    @FXML
    private Button moviesFourBtn;
    @FXML
    private Button moviesFiveBtn;
    
    
    @FXML
    private Button techOneBtn;
    @FXML
    private Button techTwoBtn;
    @FXML
    private Button techThreeBtn;
    @FXML
    private Button techFourBtn;
    @FXML
    private Button techFiveBtn;
    
    @FXML
    private Button saveScoreBtn;
    
    @FXML
    private Text timePlayedText;
    @FXML
    private Text currentUserNameText;
    @FXML
    private Text scoreText;
    @FXML
    private Button timerStartBtn;
    
   
    
    private String userInputAnswer = "";
    private int userInputAnsInt; // convert user answer from string to int Sports $200

    
    private final Integer score100 = 100;
    private final Integer score200 = 200;
//    private final Integer score300 = 300;
//    private final Integer score400 = 400;
//    private final Integer score500 = 500;

    private Integer questionScoreVal;
    private String currentScore; // maybe Integer?
    
    private Alert incorrectAlert = new Alert(AlertType.ERROR);
    private Alert correctAlert = new Alert(AlertType.INFORMATION);
    private ButtonType trueButton = new ButtonType("True");
    private ButtonType falseButton = new ButtonType("False");
 
//    private ToggleGroup sportsC3Group;
//    private RadioButton sportsC3rb1, sportsC3rb2, sportsC3rb3, sportsC3rb4;
    private String sportsC3Answer; 
    private Alert sportsC4Alert;
    
    private Alert multiChoice1Ans = new Alert(Alert.AlertType.CONFIRMATION);
    private ButtonType mcOption1;
    private ButtonType mcOption2;
    private ButtonType mcOption3; 
    private ButtonType mcOption4;
    
    private ArrayList<QuestionsObj> questionsArr = new ArrayList<>();
    
    
    
    private ToggleGroup tg;
    
    
    
    GameViewModel gameModel;
//    JeopardyViewModel multiplayerModel;     
//    public ArrayList<QuestionsObj> questionsArr;
//    Questions questions;
    
    private Alert testAlertController;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("playername: "+ playerName);
        currentUserNameText.setText(playerName);
        gameModel = new GameViewModel();
        gameModel.setupTimer();
        gameModel.addPropertyChangeListener(this);
        timePlayedText.setText("00:00.00");
//        display.setText(display.getText()+"new text, with its value : + 65);
        
        currentScore = scoreText.getText();
        System.out.println("Current Score: "+currentScore);
        questionsArr = gameModel.getQuestions();
    }  

    
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        
        if(evt.getPropertyName().equals("PlayerNameID")){
            currentUserNameText.setText(evt.getNewValue().toString());  
        }       
        
        if(evt.getPropertyName().equals("TimePlayedID")){ 
            timePlayedText.setText(evt.getNewValue().toString());
        }
        
   
        
        if(evt.getPropertyName().equals("AddScore")){
            scoreText.setText(evt.getNewValue().toString());
        }

    }
   

    
    /* 
        FXML CONTROLS
    */
    
    @FXML
    private void handleTimerStart(ActionEvent event) {
        if(gameModel.isRunning()){
            gameModel.stop();
            timerStartBtn.setText("Start Timer");
            timerStartBtn.setStyle("-fx-background-color:#1fdb12");

        } else {
            gameModel.start();
            timerStartBtn.setText("Pause Timer");
            timerStartBtn.setStyle("-fx-background-color:#db1212");
        }
    }
    
    
    @FXML
    private void handleGameBack(ActionEvent event) {
        System.out.println("Game Screen BACK Pressed");
        Switchable.switchTo("StartView");
        
    }

    @FXML
    private void handleGameExit(ActionEvent event) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit Game");
        
        
    }
    
    @FXML
    private void gameFinishAction(ActionEvent event) {
        Switchable.switchTo("RecordView");
    }
    

    @FXML
    private void sportsOneAction(ActionEvent event) {
//        Switchable.switchTo("SportsCategoryOneView");
        /* Multiple choice, 1 correct answer*/
        questionScoreVal = score100; 
        
        Alert sportsC1 = new Alert(AlertType.CONFIRMATION); // sportsC1 = sports category 1
        sportsC1.setTitle("Sports [$100]");
        sportsC1.setHeaderText("How many teams are in the NFL (National Football Leage)?");
        sportsC1.setContentText("Select one option");
        mcOption1 = new ButtonType("12");
        mcOption2 = new ButtonType("24");
        mcOption3 = new ButtonType("36");
        sportsC1.getButtonTypes().setAll(mcOption1, mcOption2, mcOption3);
        
        Alert sportsC1Correct = new Alert(AlertType.INFORMATION);
        sportsC1Correct.setTitle("Sports [$100]");
        sportsC1Correct.setHeaderText("Correct!\n"
                + "You've added [$100] to your score!");
        sportsC1Correct.setContentText("Press OK to continue");
        
        Alert sportsC1Wrong = new Alert(AlertType.ERROR);
        sportsC1Wrong.setTitle("Sports [$100]");
        sportsC1Wrong.setHeaderText("Incorrect!\n"
                + "No points awarded.");
        sportsC1Wrong.setContentText("Press OK to continue");
        
        
        
        Optional<ButtonType> sportsC1Result = sportsC1.showAndWait();
        if(sportsC1Result.get() == mcOption1 || sportsC1Result.get() == mcOption2){
            System.out.println("INCORRECT!\n"
                    + "The CORRECT answer is: 36");
            sportsOneBtn.setDisable(true);
            sportsC1Wrong.showAndWait();
            
        } else if (sportsC1Result.get() == mcOption3){
            System.out.println("Correct! ");
            sportsOneBtn.setDisable(true);
            // add $100 to <score>
            sportsC1Correct.showAndWait();
            scoreText.setText("$"+score100);
            System.out.println("Current Score: "+currentScore);
        }

    }

    @FXML
    private void sportsTwoAction(ActionEvent event) { // FILL IN THE BLANK 
        TextInputDialog sportsC2Tid = new TextInputDialog();
        sportsC2Tid.setTitle("Sports ["+score200+"]");
        sportsC2Tid.setHeaderText("In what year were the Las Vegas Golden Knights added to the NHL?");
        sportsC2Tid.setContentText("Please enter your answer as an integer with no spaces (i.e. 2020)");
        // correct answer = 2016
        Optional<String> sportsC2Result = sportsC2Tid.showAndWait();
        if(sportsC2Result.isPresent()){
            userInputAnswer = sportsC2Result.get();
            userInputAnsInt = Integer.parseInt(userInputAnswer);        
            System.out.println("You said: "+userInputAnswer);
            
            if (userInputAnsInt != 2016 || userInputAnswer.isEmpty()){
                System.out.println("INCORRECT!");
                sportsTwoBtn.setDisable(true);
                incorrectAlert.setTitle("Sports ["+score200+"]");
                incorrectAlert.setHeaderText("Incorrect!\n No points will be awarded.");
                incorrectAlert.setContentText("Press OK to continue");
                incorrectAlert.showAndWait();
            } else if (userInputAnsInt == 2016) {
                System.out.println("Correct!");
                sportsTwoBtn.setDisable(true);
                correctAlert.setTitle("Sports [$200]");
                correctAlert.setHeaderText("Correct!\n You've added [$200] to your score!");
                correctAlert.setContentText("Press OK to continue");
                correctAlert.showAndWait();
            }  
        }     
    }

    @FXML
    private void sportsThreeAction(ActionEvent event) {
        
        Stage s = new Stage();
        s.setTitle("Sports [$300]"); 
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_CENTER);
        root.setVgap(10);
        root.setHgap(10);
        root.setPadding(new Insets(15, 15, 15, 15));
        VBox vb = new VBox();
        root.getChildren().add(vb);
        Label s3QuestionLabel = new Label("Who won the 2019 Stanley Cup?");
        s3QuestionLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        Button confirmButton = new Button("Confirm");
        
        tg = new ToggleGroup();
        
        RadioButton r1 = new RadioButton("San Jose Sharks");
        RadioButton r2 = new RadioButton("St. Louis Blues");
        RadioButton r3 = new RadioButton("Washington Capitals");
        RadioButton r4 = new RadioButton("New York Rangers");
        
        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);
        r3.setToggleGroup(tg);
        r4.setToggleGroup(tg);
        
        root.getChildren().add(s3QuestionLabel);
        root.add(r1, 0, 1);
        root.add(r2, 0, 2);
        root.add(r3, 0, 3);
        root.add(r4, 0, 4);
        root.add(confirmButton, 0, 5);
        Scene sc = new Scene(root, 420, 200);
 
        s.setScene(sc);
        s.showAndWait();
    }

    @FXML
    private void sportsFourAction(ActionEvent event) {
        /* true false */
        sportsC4Alert = new Alert(Alert.AlertType.CONFIRMATION);
        sportsC4Alert.setTitle("Sports [$400]");
        sportsC4Alert.setHeaderText(null);
        sportsC4Alert.setContentText("Lionel Messi has won 7 Ballon d'Or Trophies in his carrer");
        sportsC4Alert.getButtonTypes().setAll(trueButton, falseButton);

        Optional<ButtonType> sportsC4Result = sportsC4Alert.showAndWait();
        if(sportsC4Result.get() == falseButton){
            incorrectAlert.setTitle("Sports [$400]");
            incorrectAlert.setHeaderText("Incorrect!\nNo points will be awarded");
            incorrectAlert.setContentText("Press OK to continue");
            incorrectAlert.showAndWait();
            sportsFourBtn.setDisable(true);

        } else {
            correctAlert.setTitle("Sports [$400]");
            correctAlert.setHeaderText("Correct!\nYou've added [$400] to your score!");
            correctAlert.setContentText("Press OK to continue");
            correctAlert.showAndWait();
            sportsFourBtn.setDisable(true);
        }
        
    }

    @FXML
    private void sportsFiveAction(ActionEvent event) {
        multiChoice1Ans.setTitle("Sports [$500]");
        multiChoice1Ans.setHeaderText("What is the average height of a professional NBA player?");
        multiChoice1Ans.setContentText("Select one");
        mcOption1 = new ButtonType("6ft 10in");
        mcOption2 = new ButtonType("7ft 2in");
        mcOption3 = new ButtonType("7ft");
        mcOption4 = new ButtonType("6ft 6in");
        multiChoice1Ans.getButtonTypes().setAll(mcOption1, mcOption2, mcOption3, mcOption4);
        
        Optional<ButtonType> sportsC5Result = multiChoice1Ans.showAndWait();

        if(sportsC5Result.get() == mcOption1 || sportsC5Result.get() == mcOption2 || sportsC5Result.get() == mcOption3){
            incorrectAlert.setTitle("Sports [$500]");
            incorrectAlert.setHeaderText("Incorrect!\nNo points will be awarded");            
            incorrectAlert.setContentText("Press OK to continue");
            sportsFiveBtn.setDisable(true);
            incorrectAlert.showAndWait();
        } else if(sportsC5Result.get() == mcOption4){            
            correctAlert.setTitle("Sports [$500]");
            correctAlert.setHeaderText("Correct!\nYou've added [$500] to your score!");
            correctAlert.setContentText("Press OK to continue");
            sportsFiveBtn.setDisable(true);
            correctAlert.showAndWait();
        }
        
    }

    @FXML
    private void musicOneAction(ActionEvent event) {
        multiChoice1Ans.setTitle("Music [$100]");
        multiChoice1Ans.setHeaderText("Who wrote the song 'Black Dog'?");
        multiChoice1Ans.setContentText("Select one");
        mcOption1 = new ButtonType("Queen");
        mcOption2 = new ButtonType("Def Leppard");
        mcOption3 = new ButtonType("Led Zeppelin");
        mcOption4 = new ButtonType("AC/DC");
        multiChoice1Ans.getButtonTypes().setAll(mcOption1, mcOption2, mcOption3, mcOption4);
        Optional<ButtonType> musicC1Result = multiChoice1Ans.showAndWait();
        if(musicC1Result.get() == mcOption1 || musicC1Result.get() == mcOption2 || musicC1Result.get() == mcOption4){
            incorrectAlert.setTitle("Music [$100]");
            incorrectAlert.setHeaderText("Incorrect!\nNo points will be awarded");
            incorrectAlert.setContentText("Press OK to continue");
            musicOneBtn.setDisable(true);
            incorrectAlert.show();
        } else if (musicC1Result.get() == mcOption3){
            correctAlert.setTitle("Music [$100]");
            correctAlert.setHeaderText("Correct!\n["+score100+"] will be added to your score");
            correctAlert.setContentText("Press OK to continue");
            musicOneBtn.setDisable(true);
            correctAlert.show();
        }
    }
    

    @FXML
    private void musicTwoAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Music", 2);
        gameModel.questionsAlert(tempObj);
        musicTwoBtn.setDisable(true);
    }

    @FXML
    private void musicThreeAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Music", 3);
        gameModel.questionsAlert(tempObj);
        musicThreeBtn.setDisable(true);
    }

    @FXML
    private void musicFourAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Music", 4);
        gameModel.questionsAlert(tempObj);
        musicFourBtn.setDisable(true);
    }

    @FXML
    private void musicFiveAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Music", 5);
        gameModel.questionsAlert(tempObj);
        musicFiveBtn.setDisable(true);
    }

    @FXML
    private void moviesOneAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Movies", 1);
        gameModel.questionsAlert(tempObj);
        moviesOneBtn.setDisable(true);
    }

    @FXML
    private void moviesTwoAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Movies", 2);
        gameModel.questionsAlert(tempObj);
        moviesTwoBtn.setDisable(true);
    }

    @FXML
    private void moviesThreeAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Movies", 3);
        gameModel.questionsAlert(tempObj);
        moviesThreeBtn.setDisable(true);
    }

    @FXML
    private void moviesFourAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Movies", 4);
        gameModel.questionsAlert(tempObj);
        moviesFourBtn.setDisable(true);
    }

    @FXML
    private void moviesFiveAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Movies", 5);
        gameModel.questionsAlert(tempObj);
        moviesFiveBtn.setDisable(true);
    }

    @FXML
    private void techBtnAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Tech", 1);
        gameModel.questionsAlert(tempObj);
        techOneBtn.setDisable(true);
    }

    @FXML
    private void techTwoAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Tech", 2);
        gameModel.questionsAlert(tempObj);
        techTwoBtn.setDisable(true);
    }

    @FXML
    private void techThreeAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Tech", 3);
        gameModel.questionsAlert(tempObj);
        techThreeBtn.setDisable(true);
    }

    @FXML
    private void techFourAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Tech", 4);
        gameModel.questionsAlert(tempObj);
        techFourBtn.setDisable(true);
    }

    @FXML
    private void techFiveAction(ActionEvent event) {
        QuestionsObj tempObj;
        tempObj = gameModel.findQuestionsObj(questionsArr, "Tech", 5);
        gameModel.questionsAlert(tempObj);
        techFiveBtn.setDisable(true);
    }

    @FXML
    private void saveScoreAction(ActionEvent event) {
        String queryString;
        DataBaseConnection dbConnectionsSave = new DataBaseConnection();
        queryString = dbConnectionsSave.constructUploadQueryString(playerName, gameModel.getScore());
        dbConnectionsSave.uploadingNameScore(queryString);
    }

    

    
    
}
