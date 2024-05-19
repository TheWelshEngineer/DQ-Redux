package RogueLike.Main.Screens;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Utils.NotificationHistory;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ActionLogScreen implements Screen {
    private final NotificationHistory notificationHistory;
    private final int currentTurn;
    private final List<TerminalText> logLines;
    private int startLine;
    private final int maxLinesToDisplay = 36;

    public ActionLogScreen(NotificationHistory notificationHistory, int currentTurn) {
        this.notificationHistory = notificationHistory;
        this.currentTurn = currentTurn;
        this.logLines = getLines();
        this.startLine = 0;
    }

    public void displayOutput(ExtendedAsciiPanel terminal) {
        terminal.clear();
        Screen.generateBorders(terminal);
        terminal.writeCenter("== Action Log ==", 1);

        int topY = 3;
        int bottomPadding = 3;

        if (logLines.isEmpty()) {
            terminal.writeCenter(String.format("No actions in the past %d turns.", notificationHistory.getTurnLimit()), 3);
        }
        else {
            int x = 8;
            int arrowsX = x + 12;

            if (startLine > 0) {
                terminal.write('^', arrowsX, topY-1, ExtendedAsciiPanel.brightWhite);
            }
            if (startLine + maxLinesToDisplay < logLines.size()) {
                terminal.write('v', arrowsX, topY + maxLinesToDisplay, ExtendedAsciiPanel.brightWhite);
            }

            // write out the log lines
            for (int yOffset = 0; yOffset < maxLinesToDisplay; yOffset++) {
                // index should never be negative as startLine should never be negative
                int index = yOffset + startLine;
                if (index >= logLines.size()) break;
                terminal.write(logLines.get(index), x, topY+yOffset);
            }
        }

        terminal.writeCenter("-- [ESCAPE]: Back --", topY + maxLinesToDisplay + bottomPadding);
    }

    private List<TerminalText> getLines() {
        ArrayList<TerminalText> lines = new ArrayList<>();
        for (int turn: notificationHistory.storedTurns()) {
            lines.add(new TerminalText(formatTurn(turn), ExtendedAsciiPanel.brightWhite, null));
            for (String message: notificationHistory.getNotificationsOnTurn(turn)) {
                lines.add(new TerminalText(String.format("  %s", message), null, null));
            }
        }
        return lines;
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch(key.getKeyCode()) {
            case KeybindManager.navigateMenuBack: return null;
            case KeybindManager.navigateMenuUp:
                if (startLine > 0) startLine--;
                break;
            case KeybindManager.navigateMenuDown:
                if (startLine + maxLinesToDisplay < logLines.size()) startLine++;
                break;
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
