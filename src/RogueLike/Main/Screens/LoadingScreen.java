package RogueLike.Main.Screens;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.SwingUtilities;

import RogueLike.Main.ExtendedAsciiPanel;
import RogueLike.Main.SaveState;
import RogueLike.Main.Managers.KeybindManager;

public class LoadingScreen implements Screen, Serializable {
	private boolean loadingStarted = false;
	private boolean loadingFinished = false;
	
	
	public LoadingScreen() {
		// On load, create the Loading UI.
	}

	private void loadWorld() {
		try {
			SaveState.loadGame();

			System.out.println("Finished Loading.");
			
			// Update UI to let the player know
			int center = ExtendedAsciiPanel.getHeightInCharacters()/2;
			loadingFinished = true;
			ExtendedAsciiPanel.clear();
			ExtendedAsciiPanel.writeCenter("Loading Finished. ", center);
			ExtendedAsciiPanel.writeCenter("Press [Enter] to continue", center + 1);
			ExtendedAsciiPanel.getInstance().repaint();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void displayOutput() {		

		ExtendedAsciiPanel.clear();
		ExtendedAsciiPanel.writeCenter("Loading...", ExtendedAsciiPanel.getHeightInCharacters()/2);
		ExtendedAsciiPanel.getInstance().repaint(0);
		if (!loadingStarted) {
			loadingStarted = true;
			SwingUtilities.invokeLater(new Runnable () {
				@Override
				public void run() {
					loadWorld();
					loadingFinished = true;
				}	
			});
		}
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		if (loadingFinished) {
			switch(key.getKeyCode()) {
				case KeybindManager.navigateMenuConfirm:
					return new GameplayScreen();
			}
		}
		return this;
	}

}
