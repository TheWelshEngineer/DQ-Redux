package RogueLike.Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//TODO: allow for multiple save files, but only one per run
public class SaveState {
	public static void saveGame() 
			throws IOException{
		FileOutputStream fos = new FileOutputStream("SAVE_FILE.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(World.getInstance());
		fos.close();
	}
	
	public static void loadGame() 
			throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("SAVE_FILE.bin");
        ObjectInputStream in = new ObjectInputStream(file);
        
        World loadedWorld = (World)in.readObject();
        
        in.close();
        file.close();
        
        World.loadInstance(loadedWorld);
	}
}
