package edu.duke.ece651.team7.client.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;

import edu.duke.ece651.team7.client.model.UserSession;
import edu.duke.ece651.team7.shared.*;

import javafx.scene.shape.Polygon;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.Node;

/**
 * The PlayGameController class is responsible for managing the game view, which
 * displays the current state of the game to the user, and allows the user to
 * interact with the game by clicking on territories and performing various
 * actions, such as moving, attacking, upgrading, researching, and committing
 * orders.
 * This class implements the RemoteClient and Initializable interfaces, and
 * extends the UnicastRemoteObject class to provide remote access to the game
 * server.
 */
public class PlayGameController extends UnicastRemoteObject implements RemoteClient, Initializable {

    /**
     * Returns the scene of the game view with the specified server.
     *
     * @param server the remote game server.
     * @return the scene of the game view.
     * @throws IOException if the FXML file cannot be loaded.
     */
    public static Scene getScene(RemoteGame server) throws IOException {
        URL xmlResource = PlayGameController.class.getResource("/fxml/play-game-page.fxml");
        FXMLLoader loader = new FXMLLoader(xmlResource);
        loader.setController(new PlayGameController(server));
        return new Scene(loader.load(), 1065, 522);
    }

    @FXML
    private Label playerName, food, techResource, techLevel;
    @FXML
    private Button moveButton, attackButton, upgradeButton, researchButton, shortCutButton;
    @FXML
    private SVGPath Midkemia, Narnia, Oz, Westeros, Gondor, Elantris, Scadrial, Roshar;
    @FXML
    private SVGPath Hogwarts, Mordor, Essos, Dorne, Highgarden, Aranthia, Galadria, Drakoria;
    @FXML
    private SVGPath Dragonstone, Winterfell, Helvoria, Pyke, Volantis, Pentos, Braavos, Oldtown;

    @FXML private ImageView playerImage;

    @FXML
    Pane paneGroup;

    private final RemoteGame server;
    private Property<GameMap> gameMap;
    private Property<Player> self;
    private Map<String, Color> colorMap;

    static HashMap<String, Tooltip> toolTipMap = new HashMap<>();

    /**
     * Constructs a new PlayGameController object with the specified remote game
     * server.
     * 
     * @param server the remote game server.
     * @throws RemoteException if there is a problem with the remote connection.
     */
    public PlayGameController(RemoteGame server) throws RemoteException {
        super();
        this.server = server;
        this.gameMap = new SimpleObjectProperty<>(server.getGameMap());
        this.self = new SimpleObjectProperty<>(server.getSelfStatus(UserSession.getInstance().getUsername()));
        this.colorMap = new HashMap<>();
        String response = server.tryRegisterClient(UserSession.getInstance().getUsername(), this);
        if (response != null) {
            throw new IllegalStateException(response);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColorMap();
        setPlayerInfo();
        setTerritoryColor();
        DisplayImage();
        setToolTipMap();
    }

    private void setToolTipMap(){
        for(Node node: paneGroup.getChildren()){
            if(node instanceof Polygon){
                Polygon pol = (Polygon) node;
                Tooltip tooltip = new Tooltip();
                tooltip.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");
                Font font = new Font("Wawati SC Regular",15);
                tooltip.setFont(font);
                Tooltip.install(pol, tooltip);
                toolTipMap.put(pol.getId(), tooltip);
            }
        }
    }

    private void setImageForPlayer(int playerIndex, String territoryName) {
        if (gameMap.getValue().getTerritoryByName(territoryName).getOwner().getName().equals(UserSession.getInstance().getUsername())) {
            Image image = new Image(getClass().getResourceAsStream(PLAYER_IMAGE_NAMES[playerIndex]));
            playerImage.setImage(image);
        }
    }

    private static final String[] PLAYER_IMAGE_NAMES = {
            "/image/player0.png",
            "/image/player1.png",
            "/image/player2.png",
            "/image/player3.png"
    };

    public void DisplayImage(){
        Set<String> playerSet = new TreeSet<>();
        for (Territory t : gameMap.getValue().getTerritories()) {
            playerSet.add(t.getOwner().getName());
        }
        int capacity = playerSet.size();

        if(capacity==2) {
            setImageForPlayer(0, "Narnia");
            setImageForPlayer(1, "Aranthia");
        }else if(capacity==3){
            setImageForPlayer(0,"Narnia" );
            setImageForPlayer(1,"Hogwarts" );
            setImageForPlayer(2,"Winterfell");
        }else{
            setImageForPlayer(0,"Narnia" );
            setImageForPlayer(1,"Hogwarts" );
            setImageForPlayer(2,"Aranthia");
            setImageForPlayer(3,"Dragonstone");
        }
    }


    /**
     * Updates the game map property.
     * 
     * @param gameMap the updated game map.
     * @throws RemoteException if there is a remote communication error.
     */
    @Override
    public void updateGameMap(GameMap gameMap) throws RemoteException {
        Platform.runLater(() -> {
            this.gameMap.setValue(gameMap);
            initColorMap();
            setTerritoryColor();
        });
    }

    /**
     * Updates the player property.
     * 
     * @param player the updated player.
     * @throws RemoteException if there is a remote communication error.
     */
    @Override
    public void updatePlayer(Player player) throws RemoteException {
        Platform.runLater(() -> {
            this.self.setValue(player);
            setPlayerInfo();
        });
    }

    /**
     * Displays a popup window with the specified message.
     * 
     * @param msg the message to display.
     * @throws RemoteException if there is a remote communication error.
     */
    @Override
    public void showPopupWindow(String msg) throws RemoteException {
        Platform.runLater(() -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Game Server");
            dialog.setContentText(msg);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.showAndWait();
        });
    }

    /**
     * 
     * This method finds the territory object with the given name in the player's
     * owned territories, if it doesn't exist in the owned territories, returns the
     * territory object with the given name from the game map.
     * 
     * @param terrName name of the territory to be searched
     * @return Territory object with the given name
     */
    public Territory findTerritory(String terrName) {
        for (Territory t : self.getValue().getTerritories()) {
            if (t.getName().equals(terrName)) {
                return t;
            }
        }
        return gameMap.getValue().getTerritoryByName(terrName);
    }

    @FXML
    public void mouseMovedTerritory(MouseEvent me){
        Node source = (Node) me.getSource();
        Territory selectedTerr = null;
        Tooltip tooltip = null;
        if(source instanceof Polygon){
            Polygon pol = (Polygon) source;
            selectedTerr = findTerritory(pol.getId());
            tooltip = toolTipMap.get(pol.getId());
        }else if(source instanceof Text){
            Text text = (Text) source;
            selectedTerr = findTerritory(text.getText());
            tooltip = toolTipMap.get(text.getText());
        }else{
            throw new IllegalArgumentException("Invalid source " + source + " for MouseEvent");
        }
        showToolTip(selectedTerr, tooltip, source);

    }

    private void showToolTip(Territory selectedTerr, Tooltip tooltip, Node source) {
        tooltip.setText("Territory name: "+ selectedTerr.getName()+"\n"+
                "Owner: "+selectedTerr.getOwner().getName()+" \n"+
                "Food: "+ String.valueOf(selectedTerr.produceFood().getAmount())+" \n"+
                "Tech: "+ String.valueOf(selectedTerr.produceTech().getAmount())+" \n"+
                "Units: \n"+
                "CIVILIAN  (Level 0): "+String.valueOf(selectedTerr.getUnitsNumberByLevel(Level.valueOfLabel(0)))+" \n"+
                "INFANTRY  (Level 1): "+String.valueOf(selectedTerr.getUnitsNumberByLevel(Level.valueOfLabel(1)))+" \n"+
                "CAVALRY   (Level 2): "+String.valueOf(selectedTerr.getUnitsNumberByLevel(Level.valueOfLabel(2)))+" \n"+
                "TROOPER   (Level 3): "+String.valueOf(selectedTerr.getUnitsNumberByLevel(Level.valueOfLabel(3)))+" \n"+
                "ARTILLERY (Level 4): "+String.valueOf(selectedTerr.getUnitsNumberByLevel(Level.valueOfLabel(4)))+" \n"+
                "AIRFORCE  (Level 5): "+String.valueOf(selectedTerr.getUnitsNumberByLevel(Level.valueOfLabel(5)))+" \n"+
                "ULTRON    (Level 6): "+String.valueOf(selectedTerr.getUnitsNumberByLevel(Level.valueOfLabel(6)))+" \n"
        );
        tooltip.show(source, source.localToScreen(source.getBoundsInLocal()).getMinX(), source.localToScreen(source.getBoundsInLocal()).getMaxY());
    }



    @FXML
    public void mouseLeavedTerritory(MouseEvent me){
        Object source = me.getSource();
        Territory selectedTerr = null;
        if(source instanceof Polygon){
            Polygon pol = (Polygon) source;
            toolTipMap.get(pol.getId()).hide();
        }else if(source instanceof Text){
            Text text = (Text) source;
            toolTipMap.get(text.getText()).hide();
        }else{
            throw new IllegalArgumentException("Invalid source " + source + " for ActionEvent");
        }


    }

    @FXML
    public void clickOnShortCut(ActionEvent event) throws IOException {
        Scene scene = moveButton.getScene();
        scene.setOnKeyPressed(e -> {
            if (new KeyCodeCombination(KeyCode.M, KeyCombination.META_DOWN).match(e)) {
                // Command + M pressed
                try {
                    showPopup(OrderMoveController.getScene(server));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (new KeyCodeCombination(KeyCode.A, KeyCombination.META_DOWN).match(e)) {
                // Command + A pressed
                try {
                    showPopup(OrderAttackController.getScene(server, gameMap.getValue()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (new KeyCodeCombination(KeyCode.U, KeyCombination.META_DOWN).match(e)) {
                // Command + U pressed
                try {
                    showPopup(OrderUpgradeController.getScene(server));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is called when a player clicks on the attack button. It opens the
     * OrderAttackController scene to allow the player to make attack orders.
     *
     * @param event the action event triggered by the click
     * @throws IOException if there is an error while opening the
     *                     OrderAttackController scene
     */
    @FXML
    public void clickOnAttack(ActionEvent event) throws IOException {
        showPopup(OrderAttackController.getScene(server, gameMap.getValue()));
    }


    /**
     * Event handler for the "Upgrade" button. Opens a new window to upgrade the
     * user's orders.
     *
     * @param event The ActionEvent triggered by clicking the "Upgrade" button.
     * @throws IOException If an input/output error occurs while opening the new
     *                     window.
     */
    @FXML
    public void clickOnUpgrade(ActionEvent event) throws IOException {
        showPopup(OrderUpgradeController.getScene(server));
    }

    /**
     * This method is called when a player clicks on the move button. It opens the
     * OrderMoveController scene to allow the player to make move orders.
     *
     * @param event the action event triggered by the click
     * @throws IOException if there is an error while opening the
     *                     OrderMoveController scene
     */
    @FXML
    public void clickOnMove(ActionEvent event) throws IOException {
        showPopup(OrderMoveController.getScene(server));
    }

    private void showPopup(Scene newScene) {
        Stage popupStage = new Stage();
        popupStage.setScene(newScene);
        popupStage.initOwner(playerName.getScene().getWindow());
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.showAndWait();
    }


    /**
     * Event handler for the "Research" button.
     * Attempts to research a new order for the current user.
     * 
     * @param event The ActionEvent triggered by clicking the "Research" button.
     * @throws RemoteException          If a remote method invocation error occurs
     *                                  while attempting to research the order.
     * @throws IllegalArgumentException If the server returns an error message.
     */
    @FXML
    public void clickOnResearch(ActionEvent event) throws RemoteException {
        String response = server.tryResearchOrder(UserSession.getInstance().getUsername());
        if (response != null) {
            throw new IllegalArgumentException(response);
        }
    }

    /**
     * Event handler for the "Commit" button.
     * Attempts to commit the current user's orders.
     * 
     * @param event The ActionEvent triggered by clicking the "Commit" button.
     * @throws RemoteException          If a remote method invocation error occurs
     *                                  while attempting to commit the orders.
     * @throws IllegalArgumentException If the server returns an error message.
     */
    @FXML
    public void clickOnCommit(ActionEvent event) throws RemoteException {
        String response = server.doCommitOrder(UserSession.getInstance().getUsername());
        if (response != null) {
            throw new IllegalArgumentException(response);
        }
    }

    /**
     * Initializes the color map used to display player information.
     * The map maps each player's name to a color used to display their information.
     */
    public void initColorMap() {
        colorMap.clear();
        Color customRed = Color.rgb(255, 109, 109);
        Color customGreen = Color.rgb(64, 191, 64);
        Color customBlue = Color.rgb(112, 209, 255);
        Color customOrange = Color.rgb(255, 159, 28);
        Color[] colorArr = { customRed, customGreen, customBlue, customOrange };

        Set<String> playerSet = new TreeSet<>();
        for (Territory t : gameMap.getValue().getTerritories()) {
            playerSet.add(t.getOwner().getName());
        }
        int idx = 0;
        for (String name : playerSet) {
            colorMap.put(name, colorArr[idx]);
            ++idx;
        }
    }

    /**
     * Sets the current user's information in the UI. This includes the user's name,
     * food and tech resources, and current tech level.
     */
    public void setPlayerInfo() {
        playerName.setText(UserSession.getInstance().getUsername());
        food.setText(String.valueOf(self.getValue().getFood().getAmount()));
        techResource.setText(String.valueOf(self.getValue().getTech().getAmount()));
        techLevel.setText(String.valueOf(self.getValue().getCurrentMaxLevel()) + " (level"
                + String.valueOf(self.getValue().getCurrentMaxLevel().label) + ")");

        playerName.setTextFill(colorMap.get(UserSession.getInstance().getUsername()));
        food.setTextFill(colorMap.get(UserSession.getInstance().getUsername()));
        techResource.setTextFill(colorMap.get(UserSession.getInstance().getUsername()));
        techLevel.setTextFill(colorMap.get(UserSession.getInstance().getUsername()));
    }

    /**
     * Sets the color of each territory based on the owner's color.
     * The color is retrieved from the colorMap that is initialized in the
     * constructor.
     */
    public void setTerritoryColor() {
        // Midkemia, Narnia, Oz, Westeros, Gondor, Elantris, Scadrial, Roshar;
        Midkemia.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Midkemia").getOwner().getName()));
        Narnia.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Narnia").getOwner().getName()));
        Oz.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Oz").getOwner().getName()));
        Westeros.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Westeros").getOwner().getName()));
        Gondor.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Gondor").getOwner().getName()));
        Elantris.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Elantris").getOwner().getName()));
        Scadrial.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Scadrial").getOwner().getName()));
        Roshar.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Roshar").getOwner().getName()));
        // Hogwarts, Mordor, Essos, Dorne, Highgarden, Aranthia, Galadria, Drakoria;
        Hogwarts.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Hogwarts").getOwner().getName()));
        Mordor.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Mordor").getOwner().getName()));
        Essos.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Essos").getOwner().getName()));
        Dorne.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Dorne").getOwner().getName()));
        Highgarden.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Highgarden").getOwner().getName()));
        Aranthia.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Aranthia").getOwner().getName()));
        Galadria.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Galadria").getOwner().getName()));
        Drakoria.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Drakoria").getOwner().getName()));
        // Dragonstone, Winterfell, Helvoria, Pyke, Volantis, Pentos, Braavos, Oldtown;
        Dragonstone
                .setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Dragonstone").getOwner().getName()));
        Winterfell.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Winterfell").getOwner().getName()));
        Helvoria.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Helvoria").getOwner().getName()));
        Pyke.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Pyke").getOwner().getName()));
        Volantis.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Volantis").getOwner().getName()));
        Pentos.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Pentos").getOwner().getName()));
        Braavos.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Braavos").getOwner().getName()));
        Oldtown.setFill(colorMap.get(gameMap.getValue().getTerritoryByName("Oldtown").getOwner().getName()));
    }
}
