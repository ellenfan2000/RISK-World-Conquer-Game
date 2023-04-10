/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.team7.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import edu.duke.ece651.team7.client.controller.ErrorReporter;
import edu.duke.ece651.team7.client.controller.LoginSignupController;

public class App extends Application {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    // System.out.println("GUI begin running.");
    logger.info("GUI client running");
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      Thread.setDefaultUncaughtExceptionHandler(new ErrorReporter());
      Scene scene = LoginSignupController.getScene();
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("Failed to load UI: " + e.getMessage());
    }
  }

}
