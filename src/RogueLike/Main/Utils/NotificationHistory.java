package RogueLike.Main.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationHistory {
    private final HashMap<Integer, ArrayList<String>> messagesByTurn;
    private int maxLength;

    public NotificationHistory(int maxLength) {
        this.maxLength = maxLength;
        messagesByTurn = new HashMap<>();
    }

    public void addNotification(String message, Integer turn) {
        if (messagesByTurn.containsKey(turn)) {
            messagesByTurn.get(turn).add(message);
        }
        else {
            ArrayList<String> messages = new ArrayList<>();
            messages.add(message);
            messagesByTurn.put(turn, messages);
        }
    }

    public ArrayList<String> getNotificationsOnTurn(Integer turn) {
        return messagesByTurn.getOrDefault(turn, new ArrayList<>());
    }

    public void clearOldMessages(int currentTurn) {
        // do this in two passes bc modifying the set you're iterating over is scary
        int minimumTurn = currentTurn - maxLength;
        ArrayList<Integer> turnsToRemove = new ArrayList<>();

        for (Integer turnNumber: messagesByTurn.keySet()) {
            if (turnNumber < minimumTurn) {
                turnsToRemove.add(turnNumber);
            }
        }
        for (Integer turnToRemove: turnsToRemove) {
            messagesByTurn.remove(turnToRemove);
        }
    }
}
