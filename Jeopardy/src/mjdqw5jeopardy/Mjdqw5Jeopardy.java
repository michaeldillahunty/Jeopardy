/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
References used during this project:
https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
https://stackoverflow.com/questions/30790946/javafx-alert-dialog-cancel-button
https://www.youtube.com/watch?v=MCHiSds_nr0
https://stackoverflow.com/questions/32424915/how-to-get-selected-radio-button-from-togglegroup
https://stackoverflow.com/questions/47067480/how-to-make-variable-change-upon-pressing-on-the-radiobutton-in-javafx](https://stackoverflow.com/questions/47067480/how-to-make-variable-change-upon-pressing-on-the-radiobutton-in-javafx)
https://www.codegrepper.com/code-examples/java/how+to+add+a+listener+to+a+toggle+group+radio+buttons+javafx
https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.ToggleGroup
https://www.programcreek.com/java-api-examples/?api=javafx.scene.control.ToggleGroup
https://mariadb.com/products/enterprise/xpand/
https://stackoverflow.com/questions/27737268/java-sockets-read-write-stream-in-two-different-ways-one-by-one
https://stackoverflow.com/questions/70293862/correctly-adding-and-accessing-objects-in-an-arraylist-java?noredirect=1#comment124277137_70293862
https://stackoverflow.com/questions/18226288/json-jsonobject-optstring-returns-string-null







*/



/**
 *
 * @author dillahuntym
 */
public class Mjdqw5Jeopardy extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
//        String ssd = "";
//        boolean isWorking;
//        
//        
//        DataBaseConnection dbConnect = new DataBaseConnection();
//        Connection connect = null;
//        connect = dbConnect.getConnection(); 
//        if (dbConnect.getConnection() == null){
//            System.out.println("DBfailed");
//        } else {
//            System.out.println("success");
//            ssd = dbConnect.constructUploadQueryString("Jackson", 69);
//            isWorking = dbConnect.uploadingNameScore(ssd);
//        }
//        dbConnect.viewTable(connect);
//        
        // this code is not mine, it was taken from Professor Wergeles SwitcherExample Lecture code
        HBox root = new HBox();
        root.setPrefSize(1000, 1000);
        root.setAlignment(Pos.CENTER);
        Text failedMessage = new Text("Application failed to launch properly");
        failedMessage.setFont(Font.font(STYLESHEET_MODENA, 32));
        root.getChildren().add(failedMessage);
        
        Scene scene = new Scene(root);
        Switchable.scene = scene; 
        stage.setTitle("Jeopardy");
        Switchable.switchTo("StartView");
        
        stage.setScene(scene);
        stage.show();
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
