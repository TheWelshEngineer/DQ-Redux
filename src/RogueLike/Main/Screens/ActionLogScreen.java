package RogueLike.Main.Screens;

import RogueLike.Main.Creatures.Creature;
import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Utils.NotificationHistory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ActionLogScreen implements Screen {
    private final NotificationHistory notificationHistory;
    private final int currentTurn;

    public ActionLogScreen(NotificationHistory notificationHistory, int currentTurn) {
        this.notificationHistory = notificationHistory;
        this.currentTurn = currentTurn;
    }

    public void displayOutput(ExtendedAsciiPanel terminal) {
        terminal.clear();
        Screen.generateBorders(terminal);
        terminal.writeCenter("== Action Log ==", 1);

        int y = 3;
        int maxY = 38 - 3;
        int x = 8;

        for (int turn: notificationHistory.storedTurns()) {
            terminal.write(formatTurn(turn), x, y, ExtendedAsciiPanel.brightWhite);
            y++;
            if (y > maxY) break;
            for (String message: notificationHistory.getNotificationsOnTurn(turn)) {
                terminal.write(String.format("  %s", message), x, y); // indent a little bit
                y++;
                if (y > maxY) break;
            }
            if (y > maxY) break;
        }

        terminal.writeCenter("-- [ESCAPE]: Back --", 38);
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch(key.getKeyCode()) {
            case KeybindManager.navigateMenuBack: return null;
        }
        return this;
    }

    private String formatTurn(int turn) {
        if (turn == currentTurn) {
            return "[This Turn]";
        }
        else if (turn == currentTurn - 1) {
            return "[Last Turn]";
        }
        else {
            return String.format("[%d Turns Ago]", currentTurn-turn);
        }
    }
}
