package RogueLike.Main.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import RogueLike.Main.Screens.TerminalText;

public class NotificationHistory implements Serializable{
    private static final long serialVersionUID = 477706614282475570L;
	private final HashMap<Integer, List<TerminalText>> messagesByTurn;
    private int maxLength;

    public NotificationHistory(int maxLength) {
        this.maxLength = maxLength;
        messagesByTurn = new HashMap<>();
    }


	//TODO: Safely remove and move to TerminalText
    public void addNotification(String message, Integer turn) {
    	TerminalText messageTerminal = new TerminalText(message, null, null);
        if (messagesByTurn.containsKey(turn)) {
            messagesByTurn.get(turn).add(messageTerminal);
        }
        else {
        	List<TerminalText> messages = new ArrayList<>();
            messages.add(messageTerminal);
            messagesByTurn.put(turn, messages);
        }
    }
    
    public void addNotification(TerminalText message, Integer turn) {
        if (messagesByTurn.containsKey(turn)) {
            messagesByTurn.get(turn).add(message);
        }
        else {
        	List<TerminalText> messages = new ArrayList<>();
            messages.add(message);
            messagesByTurn.put(turn, messages);
        }
    }

    public List<TerminalText> getNotificationsOnTurn(Integer turn) {
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

    public List<Integer> storedTurns() {
        return (messagesByTurn.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
    }

    public int getTurnLimit() {
        return maxLength;
    }
}
