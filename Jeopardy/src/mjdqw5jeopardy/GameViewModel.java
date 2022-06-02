/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javax.swing.event.ChangeListener;
import java.util.stream.Stream;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
/**
 *
 * @author Michael Dillahunty
 */
public class GameViewModel extends JeopardyAbstractModel implements TimelineInterface{
        
    private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SS");

    private Integer score = 0; 

    protected Alert mcAlert;
    
    public ArrayList<QuestionsObj> questionsArr = new ArrayList<>();
    // (String question, Integer QuestionType, String Categor, Integer Difficulty, boolean isUsed)
    private Alert incorrectAlert = new Alert(Alert.AlertType.ERROR);
    private Alert correctAlert = new Alert(Alert.AlertType.CONFIRMATION);
    
    public GameViewModel(){
        /* 13 question objects */
        /* format           = (QUESTION, QUESTION TYPE, CATEGORY, DIFFICULTY, FALSE, REAL ANSWER, QUESTION OPTION 1, QUESTION OPTION 2, QUESTION OPTION 3 */
//        QuestionsObj sports1 = new QuestionsObj("How many teams are in the NFL?", "MC","Sports",1, false, "24", "12", "24", "36");
//        QuestionsObj sports2 = new QuestionsObj("Question", "Type","Sports","Difficulty", false, "Answer", "Ans1", "Ans2", "Ans3");
        QuestionsObj sports3 = new QuestionsObj("How old is Zlatan Ibrahmimovich?", "MC", "Sports", 3, false, "40", "32", "30", "38");
//        QuestionsObj sports4 = new QuestionsObj("Question", "Type","Category","Difficulty", false, "Answer", "Ans1", "Ans2", "Ans3")
//        QuestionsObj sports5 = new QuestionsObj("Question", "Type","Category","Difficulty", false, "Answer", "Ans1", "Ans2", "Ans3")

        QuestionsObj music2 = new QuestionsObj("What year did Snoop Dogg start his music career?", "MC", "Music", 2, false, "1992", "1997", "1989", "2000");
        QuestionsObj music3 = new QuestionsObj("The band 'Queen' had how many members?", "FB", "Music", 3, false, "4", "3", "5", "6");
        QuestionsObj music4 = new QuestionsObj("What is the most famous song by Aerosmith", "MC", "Music", 4, false, "I Don't Wanna\nMiss a Thing", "Dream on", "Walk This Way", "Sweet Emotion");
        QuestionsObj music5 = new QuestionsObj("Which of the following isn't a famous music festival?", "MC", "Music", 5, false, "Fyre Festival", "Lollapalooza", "Coachella", "Burning Man");
        
        QuestionsObj movies1 = new QuestionsObj("(True or False)\nThere are 9 movies in the StarWars Saga", "TF", "Movies", 1, false, "True", "False", "", ""); 
        QuestionsObj movies2 = new QuestionsObj("What is the highest grossing film of all time, with $2.8 billion", "MC", "Movies", 2, false, "Avatar", "Avengers: Endgame", "StarWars:\nThe Force Awakens", "Avengers: Infinity War");
        QuestionsObj movies3 = new QuestionsObj("Which movie won an Oscar for 'Best Animated Picture'", "MC", "Movies", 3, false, "Toy Story 4", "Klaus", "How to Train Your\nDragon:\nThe Hidden World", "Missing Link");
        QuestionsObj movies4 = new QuestionsObj("For what movie did Tom Hanks score his first Academy Award nomination?", "MC", "Movies", 4, false, "Big", "Splash", "Nothing in Common", "The Money Pit");
        QuestionsObj movies5 = new QuestionsObj("What year did Leonardo DiCaprio win his first(and only) Oscar?", "FB", "Movies", 5, false, "2016", "", "", "");
        
        QuestionsObj tech1 = new QuestionsObj("Who is the founder of Apple?", "MC", "Tech", 1, false, "Steve Jobs", "Bill Gates", "Steve Wozniak", "Elon Musk");
        QuestionsObj tech2 = new QuestionsObj("(True or False)\nSamsung was the first company to make a touchscreen phone", "TF", "Tech", 2, false, "False", "True", "random answer", "random answer"); // false -> IBM was
        QuestionsObj tech3 = new QuestionsObj("What year was the first Mac made?", "FB", "Tech", 3, false, "1984", "", "", "");
        QuestionsObj tech4 = new QuestionsObj("An Object is an ____ of a Class", "FB", "Tech", 4, false, "instance", "", "", "");
        QuestionsObj tech5 = new QuestionsObj("How much did the computer that got the U.S. to the moon weight (Apollo Guidance Computer)", "MC", "Tech", 5, false, "70lbs", "30lbs", "105lbs", "120lbs");

        questionsArr.add(music2);
        questionsArr.add(music3);
        questionsArr.add(music4);
        questionsArr.add(music5);
        questionsArr.add(movies1);
        questionsArr.add(movies2);
        questionsArr.add(movies3);
        questionsArr.add(movies4);
        questionsArr.add(movies5);
        questionsArr.add(tech1);
        questionsArr.add(tech2);
        questionsArr.add(tech3);
        questionsArr.add(tech4);
        questionsArr.add(tech5);
    }
    
    @Override 
    public void update(){
        secondsElapsed += tickTimeInSeconds;
        
        String digitalTimer = sdf.format(secondsElapsed*1000);
        firePropertyChange("TimePlayedID", "", digitalTimer);
    
    }

    public ArrayList<QuestionsObj> getQuestions(){
        return questionsArr;
    }

    public boolean questionsAlert(QuestionsObj alertObj){
            /* multiple choice */
//        System.out.println("start if block");
        if(alertObj.qType.equals("MC")){
            System.out.println("MC if");
            Alert mcAlert = new Alert(Alert.AlertType.INFORMATION);
            mcAlert.getDialogPane().setMinWidth(700.0);
            mcAlert.setTitle(alertObj.qCategory+"["+alertObj.qDifficulty+"]");
            mcAlert.setHeaderText(alertObj.qString);
            mcAlert.setContentText("Select one option");
            ButtonType mcOption1 = new ButtonType(alertObj.answerChoice1);
            ButtonType mcOption2 = new ButtonType(alertObj.answerChoice2);
            ButtonType mcOption3 = new ButtonType(alertObj.answerChoice3);
            ButtonType mcOptionCorrect = new ButtonType(alertObj.qAnswer);
            mcAlert.getButtonTypes().setAll(mcOption1, mcOption2, mcOption3, mcOptionCorrect);
            
            Alert mcCorrect = new Alert(Alert.AlertType.INFORMATION);
            mcCorrect.setTitle(alertObj.qCategory+"["+alertObj.qDifficulty+"]");
            mcCorrect.setHeaderText("Correct!\n"
                    + "You've added ["+alertObj.qDifficulty+"] to your score!");
            mcCorrect.setContentText("Press OK to continue");
            
            Alert mcIncorrect = new Alert(Alert.AlertType.ERROR);
            mcIncorrect.setTitle("Sports ["+alertObj.qDifficulty+"]");
            mcIncorrect.setHeaderText("Incorrect!\n"
                    + "No points awarded.");
            mcIncorrect.setContentText("Press OK to continue");
            
            Optional<ButtonType> mcResult = mcAlert.showAndWait();
            
            if(mcResult.get() == mcOption1 || mcResult.get() == mcOption2 || mcResult.get() == mcOption3){
                mcIncorrect.show();
//                return false;

            } else if (mcResult.get() == mcOptionCorrect){
                score += (alertObj.qDifficulty)*100;
                firePropertyChange("AddScore", "", score);
                mcCorrect.show();
            }
            
//            return false;
            
          /* true false */
        } else if (alertObj.qType.equals("TF")){
//            System.out.println("success");
            Alert tfAlert = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType trueBtn = new ButtonType(alertObj.qAnswer);
            ButtonType falseBtn = new ButtonType(alertObj.answerChoice1);
//            ButtonType trueBtn = new ButtonType("True");
//            ButtonType falseBtn = new ButtonType("False");
            tfAlert.setTitle(alertObj.qCategory);
            tfAlert.setHeaderText(null);
            tfAlert.setContentText(alertObj.qString);
            tfAlert.getButtonTypes().setAll(trueBtn, falseBtn);
            System.out.println("test 2");
            Optional<ButtonType> tfResult = tfAlert.showAndWait();
            System.out.println("test 3");
            if(tfResult.get() == falseBtn){
                Alert incorrectAlert = new Alert(Alert.AlertType.ERROR);
                incorrectAlert.setTitle(alertObj.qCategory+"["+alertObj.qDifficulty+"]");
                incorrectAlert.setHeaderText("Incorrect!\nNo points will be awarded");
                incorrectAlert.setContentText("Press OK to continue");
                incorrectAlert.showAndWait();
                return false;
            } else {
                score += (alertObj.qDifficulty)*100;
                firePropertyChange("AddScore", "", score);
                Alert correctAlert = new Alert(Alert.AlertType.CONFIRMATION);
                correctAlert.setTitle(""+alertObj.qCategory+"["+alertObj.qDifficulty+"]");
                correctAlert.setHeaderText("Correct!\nYou've added ["+alertObj.qDifficulty+"] to your score!");
                correctAlert.setContentText("Press OK to continue");
                correctAlert.showAndWait();
                
                return true;
                
            }
            /* fill in the blank */
        } else if (alertObj.qType.equals("FB")){
            System.out.println("FB else if");
            TextInputDialog fbAlert = new TextInputDialog();
            fbAlert.setTitle(alertObj.qCategory+"["+alertObj.qDifficulty+"]");
            fbAlert.setHeaderText(alertObj.qString);
            fbAlert.setContentText("Enter your answer as an Integer or a lowercase String with no spaces\n"
                    + "(i.e. 2020)\n"
                    + "(i.e. [hello])");
            // correct answer = 2016
            Optional<String> fbResult = fbAlert.showAndWait();
            if(fbResult.isPresent()){
                String userInputAnswer = "";
                userInputAnswer = fbResult.get();
//                System.out.println("You said: "+userInputAnswer);
                    
                if (!(userInputAnswer.equals(alertObj.qAnswer)) || userInputAnswer.isEmpty()){
                    incorrectAlert.setTitle(alertObj.qCategory+"["+alertObj.qDifficulty+"]");
                    incorrectAlert.setHeaderText("Incorrect!\n No points will be awarded.");
                    incorrectAlert.setContentText("Press OK to continue");
                    incorrectAlert.showAndWait();
                    return false;
                } else if (userInputAnswer.equals(alertObj.qAnswer)) {
                    correctAlert.setTitle(alertObj.qCategory+"["+alertObj.qDifficulty+"]");
                    correctAlert.setHeaderText("Correct!\n You've added ["+alertObj.qDifficulty+"] to your score!");
                    correctAlert.setContentText("Press OK to continue");
                    correctAlert.showAndWait();
                    score += (alertObj.qDifficulty)*100;
                    firePropertyChange("AddScore", "", score);
                    return true;
                }  
            }     
        }
//        System.out.println("end if block");
        return false;
    }

    public QuestionsObj findQuestionsObj(ArrayList<QuestionsObj> questionsArr, String buttonCategory, Integer buttonDifficulty){
        // https://beginnersbook.com/2013/12/how-to-loop-arraylist-in-java/
        int count = 0;
        while(questionsArr.size() > count){
//            System.out.println(questionsArr.get(count));
            if(buttonCategory != questionsArr.get(count).qCategory || buttonDifficulty != questionsArr.get(count).qDifficulty || questionsArr.get(count).isUsed == true){
                count++;
            } else {
                QuestionsObj testObj = new QuestionsObj(questionsArr.get(count).qString, questionsArr.get(count).qType, questionsArr.get(count).qCategory, questionsArr.get(count).qDifficulty, questionsArr.get(count).isUsed, questionsArr.get(count).qAnswer, questionsArr.get(count).answerChoice1, questionsArr.get(count).answerChoice2, questionsArr.get(count).answerChoice3);
                questionsArr.get(count).isUsed = true;
                return testObj;
                
            }
           
        }       
        QuestionsObj holderObj = new QuestionsObj("failed", "failed", "failed", 0, null, "failed", "failed", "failed", "failed");
        return holderObj;
    }
    
    public Integer getScore(){
        return this.score;
    }
    
    
    
        
    //    public void displayNewQuestion(){ // (String question, String QuestionType, String Category, Integer Difficulty, boolean isUsed)
//    /*
//        QuestionTypes:
//        TF = True/False
//        MC = MultipleChoice
//        FB = Fill In the Blank
//    */
//    
//    
//    
//    System.out.println("Array Size: "+questionsArr.size());
//
////    Alert alertQObj = createAlertFromObj(questionsArr, "Music", 1);
////    
////    alertQObj.setContentText(questionsArr.get(1).qAnswer);
////    alertQObj.show();
//    
//    
////    firePropertyChange("PassAlertTest", "", alertQObj);
//    
//    
//    System.out.println("TESTING 1");
//    
////    QuestionsObj testReturnObj;
////    testReturnObj = createAlertFromObj(questionsArr, "Music", 2);
////    QuestionsObj testReturnObj2;
////    testReturnObj2 = createAlertFromObj(questionsArr, "Music", 3);
//    
//    System.out.println("TESTING 2");
////    System.out.println("\nQuestion: "+alertQObj.qString+"\nType: "+alertQObj.qType+"\nCategory: "+alertQObj.qCategory+"\nDifficulty: "+alertQObj.qDifficulty+"\nisUsed: "+alertQObj.isUsed);
////    System.out.println("\nQuestion: "+testReturnObj2.qString+"\nType: "+testReturnObj2.qType+"\nCategory: "+testReturnObj2.qCategory+"\nDifficulty: "+testReturnObj2.qDifficulty+"\nisUsed: "+testReturnObj2.isUsed);
//    }
    
    
    
}
