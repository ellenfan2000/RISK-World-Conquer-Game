/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.team7.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import edu.duke.ece651.team7.client.Model.loginModel;
import edu.duke.ece651.team7.client.Controller.gameBeginController;

public class App extends Application {
  public static void main(String[] args) {
    // if (args.length == 2) {
    //   try {
    //     Client client = new Client(args[0], Integer.parseInt(args[1]),
    //         new BufferedReader(new InputStreamReader(System.in)), System.out);
    //     client.start();
    //   } catch (Exception e) {
    //     System.err.println("Exception: " + e);
    //   }
    // } else {
    //   System.err.println("Usage: client <host> <port>");
    // }

    System.out.println("GUI begin running.");
    launch(args);
  }

  @Override
  public void start(Stage window){
    // showStartView(primaryStage);
    try {
      // load start view fxml
       URL xmlResource = getClass().getResource("/ui/gameStart.fxml");
       FXMLLoader loader = new FXMLLoader(xmlResource);



    //   // use loader’s setControllerFactory to specify how to create controllers.
       HashMap<Class<?>,Object> controllers = new HashMap<>();
       controllers.put(gameBeginController.class, new gameBeginController(new loginModel(),window));
       loader.setControllerFactory((c) -> {
         return controllers.get(c);
     });
       GridPane gp = loader.load();

      // create scene and load css
      Scene scene = new Scene(gp, 640, 480);
      URL cssResource = getClass().getResource("/ui/buttons.css");
      scene.getStylesheets().add(cssResource.toString());

      window.setScene(scene);
      window.show();

    } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load UI: " + e.getMessage());

    }
  }

  // private void showStartView(Stage window) throws IOException{
  //   try {
  //   // load start view fxml
  //   URL xmlResource = getClass().getResource("/ui/gameBegin.xml");
  //   FXMLLoader loader = new FXMLLoader(xmlResource);

  //   // use loader’s setControllerFactory to specify how to create controllers.
  //   HashMap<Class<?>,Object> controllers = new HashMap<>();
  //   controllers.put(gameBeginController.class, new gameBeginController(window));
  //   loader.setControllerFactory((c) -> {
  //     return controllers.get(c);
  // });
  //   GridPane gp = loader.load();

  //   // create scene and load css
  //   Scene scene = new Scene(gp, 640, 480);
  //   URL cssResource = getClass().getResource("/ui/buttons.css");
  //   scene.getStylesheets().add(cssResource.toString());

  //   // stage.setScene(scene);
  //   // SceneCollector.startView = scene;

  //   window.setScene(scene);
  //   window.show();

  //       } catch (IOException e) {
  //           System.err.println("Failed to load UI: " + e.getMessage());

  //       }

  // }

}
