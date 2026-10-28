package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.AlertBox;
import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;
    private final Game game = new Game();
    private final AlertBox alertBox = new AlertBox();

    @FXML private ImageView dice1ImageView;
    @FXML private ImageView dice2ImageView;
    @FXML private Label nicknameLabel;
    @FXML private Label rollLabel;
    @FXML private Label pointLabel;
    @FXML private Label gamesWonLabel;
    @FXML private Label gamesLostLabel;
    @FXML private TextArea gameLogTextArea;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(currentPlayer == null ? "Jugador" : currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        game.roll();
        dice1ImageView.setImage(new Image(getClass().getResourceAsStream(
                "/com/example/crapsgame262/images/dices/dice" + game.getFirstDieValue() + ".png")));
        dice2ImageView.setImage(new Image(getClass().getResourceAsStream(
                "/com/example/crapsgame262/images/dices/dice" + game.getSecondDieValue() + ".png")));

        rollLabel.setText(String.valueOf(game.getLastRoll()));
        pointLabel.setText(game.getPoint() == 0 ? "—" : String.valueOf(game.getPoint()));
        gamesWonLabel.setText(String.valueOf(game.getGamesWon()));
        gamesLostLabel.setText(String.valueOf(game.getGamesLost()));
        gameLogTextArea.setText(game.getMessage());
    }

    @FXML
    void onHelpClicked(MouseEvent event) {
        alertBox.showAlertBox(
                "Ayuda - Craps",
                "Reglas del juego",
                "1. En el primer tiro, 7 u 11 significa victoria.\n"
                        + "2. Si sale 2, 3 o 12 en el primer tiro, pierdes.\n"
                        + "3. Cualquier otro resultado establece el punto.\n"
                        + "4. Sigue lanzando: repite el punto para ganar; saca 7 para perder."
        );
    }
}
