package RogueLike.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import RogueLike.Main.Managers.ConfigManager;
import RogueLike.Main.Managers.KeybindManager;
import RogueLike.Main.Screens.Screen;
import RogueLike.Main.Screens.TitleScreen;
//import asciiPanel.AsciiFont;


public class applicationMain extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1060623638149583738L;
	private Screen screen;
	//
	//private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//
	public applicationMain(){
        super();
        ConfigManager.init(); // Loads and applies configuration options
        //
        //System.setProperty("sun.java2d.uiScale", "1.0");
        //System.setProperty("sun.java2d.uiScale.enabled", "false");
        //
        try {
			setIconImage(ImageIO.read(applicationMain.class.getResource("dwarfquesticon_temp.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
        //app.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DwarfQuest");
        setResizable(false);
        setVisible(true);
        //
        /*setPreferredSize(new Dimension(1080, 800));*/
        //int screenX = (int) screenSize.width;
        //int screenY = (int) screenSize.height;
        //setPreferredSize(new Dimension(screenX, screenY));
        //System.setProperty("sun.java2d.dpiaware", "true");
        //Dsun.java2d.uiScale.enabled=false
        //-Dsun.java2d.dpiaware=true
        //120, 48
        ExtendedAsciiPanel.getInstance().newExtendedAsciiPanelSettings(120, 48, new String[] {"cp437.png","codePage_437_K.png"});
        //
        //System.out.println("Width");
        //System.out.println(ExtendedAsciiPanel.getCharWidth());
        //System.out.println("Height");
        //System.out.println(ExtendedAsciiPanel.getCharHeight());
        //
        add(ExtendedAsciiPanel.getInstance());
        pack();
        screen = new TitleScreen();
        addKeyListener(this);
        repaint();
    }

    public void repaint(){
    	ExtendedAsciiPanel.clear();
        screen.displayOutput();
        super.repaint(10);
    }

    public void keyPressed(KeyEvent e) {
        e.setKeyCode(KeybindManager.applyKeymap(e.getKeyCode()));
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    @SuppressWarnings("unused")
	public static void main(String[] args) {
        applicationMain app = new applicationMain();
    }
}
