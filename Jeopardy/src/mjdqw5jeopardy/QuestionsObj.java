/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.util.ArrayList;

/**
 *
 * @author dillahuntym
 */
public class QuestionsObj {
    
    public String qString; // question string 
    
    public String qType;  // 1 = true false | 2 = multiple choice | 3 = filll in the blank
    
    public String qAnswer; // question answer
    
    public String qCategory;  // sports, music ...
    
    public Integer qDifficulty; //1 = 100, 2= 200
    
    public Boolean isUsed = false;
    
    public String answerChoice1;
    public String answerChoice2;
    public String answerChoice3;

    public ArrayList<QuestionsObj> questionsArr = new ArrayList<>();
    
    // QuestionsObj music = new QuestionsObj(
    
    
    public QuestionsObj(String qString, String qType, String qCategory, Integer qDifficulty, Boolean isUsed, String qAnswer, String answerChoice1, String answerChoice2, String answerChoice3){
        // manually assign qAnswer
        this.qString = qString;
        this.qType = qType;
        this.qAnswer = qAnswer;
        
        this.qCategory = qCategory;
        this.qDifficulty = qDifficulty;
        this.isUsed = false;
        this.answerChoice1 = answerChoice1;
        this.answerChoice2 = answerChoice2;
        this.answerChoice3 = answerChoice3;
    }

    
    
    
    
    
    
    
}




