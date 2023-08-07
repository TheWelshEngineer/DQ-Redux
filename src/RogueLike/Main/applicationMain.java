package RogueLike.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import RogueLike.Main.Screens.Screen;
import RogueLike.Main.Screens.StartScreen;
import asciiPanel.AsciiPanel;
//import asciiPanel.AsciiFont;

//chapter 19 next

public class applicationMain extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1060623638149583738L;
	
	private AsciiPanel terminal;
	private Screen screen;
	//
	//private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//
	public applicationMain(){
        super();
        //
        //System.setProperty("sun.java2d.uiScale", "1.0");
        //System.setProperty("sun.java2d.uiScale.enabled", "false");
        //
        try {
			setIconImage(ImageIO.read(new File("res/dwarfquesticon_temp.png")));
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
        terminal = new AsciiPanel(120, 48/*, AsciiFont.CP437_9x16*/);
        //
        //System.out.println("Width");
        //System.out.println(terminal.getCharWidth());
        //System.out.println("Height");
        //System.out.println(terminal.getCharHeight());
        //
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }

    public void repaint(){
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
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
