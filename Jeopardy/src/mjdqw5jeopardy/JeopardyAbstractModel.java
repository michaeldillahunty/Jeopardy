/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mjdqw5jeopardy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 *
 * @author dillahuntym
 */
public abstract class JeopardyAbstractModel {
    
    protected PropertyChangeSupport propertyChangeSupport;
    protected Timeline timeline;
    protected KeyFrame keyFrame;
    protected double tickTimeInSeconds = 0.01;
    protected double secondsElapsed = 0.0;


    
    
    public JeopardyAbstractModel(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        setupTimer();
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    
    public void setupTimer(){
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds *1000), (ActionEvent event) -> {
            System.out.println("");
           update();
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    /* methods for time played */
    public boolean isRunning(){
         if (timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
    
    public void start(){
        timeline.play();
    }
    
    public void stop(){
        timeline.pause();
    }
    

   
    

    
    public abstract void update();

       
    
    
}
