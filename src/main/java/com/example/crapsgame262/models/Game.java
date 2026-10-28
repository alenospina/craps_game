package com.example.crapsgame262.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Holds the state and rules for a player's Craps session. */
public class Game {
    private final Dice firstDie = new Dice();
    private final Dice secondDie = new Dice();
    private final List<Integer> rollHistory = new ArrayList<>();
    private boolean pointEstablished;
    private int point;
    private int lastRoll;
    private int firstDieValue;
    private int secondDieValue;
    private int rollsInRound;
    private int gamesWon;
    private int gamesLost;
    private String message = "Presiona Lanzar para comenzar.";

    /** Rolls both dice and applies the Craps rules to the current round. */
    public void roll() {
        firstDieValue = firstDie.roll();
        secondDieValue = secondDie.roll();
        lastRoll = firstDieValue + secondDieValue;
        rollHistory.add(lastRoll);
        rollsInRound++;

        if (!pointEstablished) {
            resolveComeOutRoll();
        } else if (lastRoll == point) {
            gamesWon++;
            finishRound("¡Ganaste! Salió el punto " + point + ".");
        } else if (lastRoll == 7) {
            gamesLost++;
            finishRound("Perdiste. Salió 7 antes del punto.");
        } else {
            message = "Sigue jugando: necesitas " + point + " y debes evitar 7.";
        }
    }

    private void resolveComeOutRoll() {
        if (lastRoll == 7 || lastRoll == 11) {
            gamesWon++;
            finishRound("¡Ganaste con " + lastRoll + " en el tiro inicial!");
        } else if (lastRoll == 2 || lastRoll == 3 || lastRoll == 12) {
            gamesLost++;
            finishRound("Perdiste con " + lastRoll + " en el tiro inicial.");
        } else {
            point = lastRoll;
            pointEstablished = true;
            message = "Punto establecido en " + point + ". Vuelve a lanzar.";
        }
    }

    private void finishRound(String result) {
        message = result;
        point = 0;
        pointEstablished = false;
        rollsInRound = 0;
    }

    public int getLastRoll() { return lastRoll; }
    public int getFirstDieValue() { return firstDieValue; }
    public int getSecondDieValue() { return secondDieValue; }
    public int getPoint() { return point; }
    public int getRollsInRound() { return rollsInRound; }
    public int getGamesWon() { return gamesWon; }
    public int getGamesLost() { return gamesLost; }
    public String getMessage() { return message; }
    public boolean isPointEstablished() { return pointEstablished; }

    /** Returns a read-only view of all dice totals rolled during this session. */
    public List<Integer> getRollHistory() {
        return Collections.unmodifiableList(rollHistory);
    }
}
