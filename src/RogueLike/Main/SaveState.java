package RogueLike.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//TODO: allow for multiple save files, but only one per run
public class SaveState {
	public static void saveGame() 
			throws IOException{
		FileOutputStream fos = new FileOutputStream("SAVE_FILE.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(World.INSTANCE);
		fos.close();
	}
	
	public static void loadGame() {
		
	}
}
