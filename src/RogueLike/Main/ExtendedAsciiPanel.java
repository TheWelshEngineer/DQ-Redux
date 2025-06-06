package RogueLike.Main;

import RogueLike.Main.Screens.TerminalChar;
import RogueLike.Main.Screens.TerminalText;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This simulates a code page 437 ASCII terminal display.
 * @author Trystan Spangler
 * @author Kathryn U.
 */
public class ExtendedAsciiPanel extends JPanel {
	private static final long serialVersionUID = -4167851861147593092L;

	/**
     * The color black (pure black).
     */
    public static Color black = new Color(0, 0, 0);

    /**
     * The color red.
     */
    public static Color red = new Color(128, 0, 0);

    /**
     * The color green.
     */
    public static Color green = new Color(0, 128, 0);

    /**
     * The color yellow.
     */
    public static Color yellow = new Color(128, 128, 0);

    /**
     * The color blue.
     */
    public static Color blue = new Color(0, 0, 128);

    /**
     * The color magenta.
     */
    public static Color magenta = new Color(128, 0, 128);

    /**
     * The color cyan.
     */
    public static Color cyan = new Color(0, 128, 128);

    /**
     * The color white (light gray).
     */
    public static Color white = new Color(192, 192, 192);

    /**
     * A brighter black (dark gray).
     */
    public static Color brightBlack = new Color(128, 128, 128);

    /**
     * A brighter red.
     */
    public static Color brightRed = new Color(255, 0, 0);

    /**
     * A brighter green.
     */
    public static Color brightGreen = new Color(0, 255, 0);

    /**
     * A brighter yellow.
     */
    public static Color brightYellow = new Color(255, 255, 0);

    /**
     * A brighter blue.
     */
    public static Color brightBlue = new Color(0, 0, 255);

    /**
     * A brighter magenta.
     */
    public static Color brightMagenta = new Color(255, 0, 255);

    /**
     * A brighter cyan.
     */
    public static Color brightCyan = new Color(0, 255, 255);
    
    /**
     * A brighter white (pure white).
     */
    public static Color brightWhite = new Color(255, 255, 255);
    
    //Extra Colours
    public static Color orange = new Color(235, 131, 52);
	public static Color lime = new Color(169, 227, 34);
	public static Color pink = new Color(227, 34, 111);
	public static Color brightPink = new Color(255, 138, 185);
	public static Color cobalt = new Color(41, 84, 115);
	public static Color brown = new Color(138, 111, 85);
	public static Color mimic = new Color(138, 89, 85);
	public static Color paper = new Color(207, 186, 134);
	public static Color gold = new Color(237, 175, 19);
	public static Color invisible = new Color(35, 35, 35);
	public static Color water = new Color(119, 178, 199);
	public static Color trap = new Color(255, 255, 5);
	public static Color paralyzed = new Color(255, 242, 102);
	public static Color lilac = new Color(207, 130, 255);
	public static Color apple = new Color(139, 191, 96);
	public static Color smoke = new Color(87, 81, 77);
	public static Color copper = new Color(163, 95, 46);
	public static Color kathryn = new Color(135, 41, 89);
	public static Color seafoam = new Color(69, 130, 95);
	public static Color paleRed = new Color(140, 56, 66);
	public static Color wheatgrass = new Color(232, 216, 111);
	
	public static char getGlyphFromPage(int glyphID, int page) {
		return (char) (glyphID + (256*(page-1)));
	}

	private Image offscreenBuffer;
	private Graphics offscreenGraphics;
    private int widthInCharacters;
    private int heightInCharacters;
    private int charWidth = 9;
    private int charHeight = 16;
    private static Color defaultBackgroundColor;
    private static Color defaultForegroundColor;
    private int cursorX;
    private int cursorY;
    private BufferedImage glyphSprite;
    private BufferedImage[] glyphs;
    private char[][] chars;
    private Color[][] backgroundColors;
    private Color[][] foregroundColors;
    private char[][] oldChars;
    private Color[][] oldBackgroundColors;
    private Color[][] oldForegroundColors;

    /**
     * Gets the height, in pixels, of a character.
     * @return
     */
    public int getCharHeight() {
        return charHeight;
    }

    /**
     * Gets the width, in pixels, of a character.
     * @return
     */
    public int getCharWidth() {
        return charWidth;
    }

    /**
     * Gets the height in characters.
     * A standard terminal is 24 characters high.
     * @return
     */
    public int getHeightInCharacters() {
        return heightInCharacters;
    }

    /**
     * Gets the width in characters.
     * A standard terminal is 80 characters wide.
     * @return
     */
    public int getWidthInCharacters() {
        return widthInCharacters;
    }

    /**
     * Gets the distance from the left new text will be written to.
     * @return
     */
    public int getCursorX() {
        return cursorX;
    }

    /**
     * Sets the distance from the left new text will be written to.
     * This should be equal to or greater than 0 and less than the the width in characters.
     * @param cursorX the distance from the left new text should be written to
     */
    public void setCursorX(int cursorX) {
        if (cursorX < 0 || cursorX >= widthInCharacters)
            throw new IllegalArgumentException("cursorX " + cursorX + " must be within range [0," + widthInCharacters + ")." );

        this.cursorX = cursorX;
    }

    /**
     * Gets the distance from the top new text will be written to.
     * @return
     */
    public int getCursorY() {
        return cursorY;
    }

    /**
     * Sets the distance from the top new text will be written to.
     * This should be equal to or greater than 0 and less than the the height in characters.
     * @param cursorY the distance from the top new text should be written to
     */
    public void setCursorY(int cursorY) {
        if (cursorY < 0 || cursorY >= heightInCharacters)
            throw new IllegalArgumentException("cursorY " + cursorY + " must be within range [0," + heightInCharacters + ")." );

        this.cursorY = cursorY;
    }

    /**
     * Sets the x and y position of where new text will be written to. The origin (0,0) is the upper left corner.
     * The x should be equal to or greater than 0 and less than the the width in characters.
     * The y should be equal to or greater than 0 and less than the the height in characters.
     * @param x the distance from the left new text should be written to
     * @param y the distance from the top new text should be written to
     */
    public void setCursorPosition(int x, int y) {
        setCursorX(x);
        setCursorY(y);
    }

    /**
     * Gets the default background color that is used when writing new text.
     * @return
     */
    public static Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
    }

    /**
     * Sets the default background color that is used when writing new text.
     * @param defaultBackgroundColor
     */
    public void setDefaultBackgroundColor(Color defaultBackgroundColor) {
        if (defaultBackgroundColor == null)
            throw new NullPointerException("defaultBackgroundColor must not be null.");

        ExtendedAsciiPanel.defaultBackgroundColor = defaultBackgroundColor;
    }

    /**
     * Gets the default foreground color that is used when writing new text.
     * @return
     */
    public static Color getDefaultForegroundColor() {
        return defaultForegroundColor;
    }

    /**
     * Sets the default foreground color that is used when writing new text.
     * @param defaultForegroundColor
     */
    public void setDefaultForegroundColor(Color defaultForegroundColor) {
        if (defaultForegroundColor == null)
            throw new NullPointerException("defaultForegroundColor must not be null.");

        ExtendedAsciiPanel.defaultForegroundColor = defaultForegroundColor;
    }

    /**
     * Class constructor.
     * Default size is 80x24.
     */
    public ExtendedAsciiPanel() {
        this(80, 24, new String[]{"res/cp437.png"});
    }

    /**
     * Class constructor specifying the width and height in characters.
     * @param width
     * @param height
     */
    public ExtendedAsciiPanel(int width, int height, String[] sources) {
        super();

        if (width < 1)
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );

        if (height < 1)
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );

        widthInCharacters = width;
        heightInCharacters = height;
        setPreferredSize(new Dimension(charWidth * widthInCharacters, charHeight * heightInCharacters));

        defaultBackgroundColor = black;
        defaultForegroundColor = white;

        chars = new char[widthInCharacters][heightInCharacters];
        backgroundColors = new Color[widthInCharacters][heightInCharacters];
        foregroundColors = new Color[widthInCharacters][heightInCharacters];

        oldChars = new char[widthInCharacters][heightInCharacters];
        oldBackgroundColors = new Color[widthInCharacters][heightInCharacters];
        oldForegroundColors = new Color[widthInCharacters][heightInCharacters];

        
        
        loadGlyphs(sources);
        
        ExtendedAsciiPanel.this.clear();
    }
    
    @Override
    public void update(Graphics g) {
         paint(g); 
    } 

    @Override
    public void paint(Graphics g) {
        if (g == null)
            throw new NullPointerException();
        
        if (offscreenBuffer == null){
            offscreenBuffer = createImage(this.getWidth(), this.getHeight()); 
            offscreenGraphics = offscreenBuffer.getGraphics();
        }
        
        for (int x = 0; x < widthInCharacters; x++) {
            for (int y = 0; y < heightInCharacters; y++) {
            	if (oldBackgroundColors[x][y] == backgroundColors[x][y]
            	 && oldForegroundColors[x][y] == foregroundColors[x][y]
            	 && oldChars[x][y] == chars[x][y])
            		continue;
            	
                Color bg = backgroundColors[x][y];
                Color fg = foregroundColors[x][y];

                LookupOp op = setColors(bg, fg);
                BufferedImage img = op.filter(glyphs[chars[x][y]], null);
                offscreenGraphics.drawImage(img, x * charWidth, y * charHeight, null);
                
                oldBackgroundColors[x][y] = backgroundColors[x][y];
        	    oldForegroundColors[x][y] = foregroundColors[x][y];
        	    oldChars[x][y] = chars[x][y];
            }
        }
        
        g.drawImage(offscreenBuffer,0,0,this);
    }
    
    private void loadGlyphs(String[] sources) {
    	int sourceCount = sources.length;
    	glyphs = new BufferedImage[256*sourceCount];
    	for(int j = 0; j < sourceCount; j++) {
    		System.out.println("Attempting to load: "+sources[j]);
    		try {
                glyphSprite = ImageIO.read(ExtendedAsciiPanel.class.getResource(sources[j]));
                System.out.println("Loading: "+sources[j]);
            } catch (IOException e) {
                System.err.println("loadGlyphs(): " + e.getMessage());
            }

            for (int i = 0; i < 256; i++) {
                int sx = (i % 32) * charWidth + 8;
                int sy = (i / 32) * charHeight + 8;

                glyphs[i+(256*j)] = new BufferedImage(charWidth, charHeight, BufferedImage.TYPE_INT_ARGB);
                glyphs[i+(256*j)].getGraphics().drawImage(glyphSprite, 0, 0, charWidth, charHeight, sx, sy, sx + charWidth, sy + charHeight, null);
            }
    	}
        
    }

    private LookupOp setColors(Color bgColor, Color fgColor) {
        byte[] a = new byte[256];
        byte[] r = new byte[256];
        byte[] g = new byte[256];
        byte[] b = new byte[256];

        byte bgr = (byte) (bgColor.getRed());
        byte bgg = (byte) (bgColor.getGreen());
        byte bgb = (byte) (bgColor.getBlue());

        byte fgr = (byte) (fgColor.getRed());
        byte fgg = (byte) (fgColor.getGreen());
        byte fgb = (byte) (fgColor.getBlue());

        for (int i = 0; i < 256; i++) {
            if (i == 0) {
                a[i] = (byte) 255;
                r[i] = bgr;
                g[i] = bgg;
                b[i] = bgb;
            } else {
                a[i] = (byte) 255;
                r[i] = fgr;
                g[i] = fgg;
                b[i] = fgb;
            }
        }

        byte[][] table = {r, g, b, a};
        return new LookupOp(new ByteLookupTable(0, table), null);
    }

    /**
     * Clear the entire screen to whatever the default background color is.
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel clear() {
        return clear(' ', 0, 0, widthInCharacters, heightInCharacters, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Clear the entire screen with the specified character and whatever the default foreground and background colors are.
     * @param character  the character to write
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel clear(char character) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        return clear(character, 0, 0, widthInCharacters, heightInCharacters, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Clear the entire screen with the specified character and whatever the specified foreground and background colors are.
     * @param character  the character to write
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel clear(char character, Color foreground, Color background) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        return clear(character, 0, 0, widthInCharacters, heightInCharacters, foreground, background);
    }

    /**
     * Clear the section of the screen with the specified character and whatever the default foreground and background colors are.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param width      the height of the section to clear
     * @param height     the width of the section to clear
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel clear(char character, int x, int y, int width, int height) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")." );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")." );

        if (width < 1)
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );

        if (height < 1)
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );

        if (x + width > widthInCharacters)
            throw new IllegalArgumentException("x + width " + (x + width) + " must be less than " + (widthInCharacters + 1) + "." );

        if (y + height > heightInCharacters)
            throw new IllegalArgumentException("y + height " + (y + height) + " must be less than " + (heightInCharacters + 1) + "." );


        return clear(character, x, y, width, height, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Clear the section of the screen with the specified character and whatever the specified foreground and background colors are.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param width      the height of the section to clear
     * @param height     the width of the section to clear
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel clear(char character, int x, int y, int width, int height, Color foreground, Color background) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")" );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        if (width < 1)
            throw new IllegalArgumentException("width " + width + " must be greater than 0." );

        if (height < 1)
            throw new IllegalArgumentException("height " + height + " must be greater than 0." );

        if (x + width > widthInCharacters)
            throw new IllegalArgumentException("x + width " + (x + width) + " must be less than " + (widthInCharacters + 1) + "." );

        if (y + height > heightInCharacters)
            throw new IllegalArgumentException("y + height " + (y + height) + " must be less than " + (heightInCharacters + 1) + "." );

        for (int xo = x; xo < x + width; xo++) {
            for (int yo = y; yo < y + height; yo++) {
                write(character, xo, yo, foreground, background);
            }
        }
        return this;
    }

    /**
     * Write a character to the cursor's position.
     * This updates the cursor's position.
     * @param character  the character to write
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(char character) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        return write(character, cursorX, cursorY, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Write a character to the cursor's position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param character  the character to write
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(char character, Color foreground) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        return write(character, cursorX, cursorY, foreground, defaultBackgroundColor);
    }

    /**
     * Write a character to the cursor's position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param character  the character to write
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(char character, Color foreground, Color background) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        return write(character, cursorX, cursorY, foreground, background);
    }

    /**
     * Write a character to the specified position.
     * This updates the cursor's position.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(char character, int x, int y) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")" );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        return write(character, x, y, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Write a character to the specified position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(char character, int x, int y, Color foreground) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")" );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        return write(character, x, y, foreground, defaultBackgroundColor);
    }

    /**
     * Write a character to the specified position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param character  the character to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(char character, int x, int y, Color foreground, Color background) {
        if (character < 0 || character >= glyphs.length)
            throw new IllegalArgumentException("character " + character + " must be within range [0," + glyphs.length + "]." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")" );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        if (foreground == null) foreground = defaultForegroundColor;
        if (background == null) background = defaultBackgroundColor;

        chars[x][y] = character;
        foregroundColors[x][y] = foreground;
        backgroundColors[x][y] = background;
        cursorX = x + 1;
        cursorY = y;
        return this;
    }

    /**
     * Write a string to the cursor's position.
     * This updates the cursor's position.
     * @param string     the string to write
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(String string) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (cursorX + string.length() >= widthInCharacters)
            throw new IllegalArgumentException("cursorX + string.length() " + (cursorX + string.length()) + " must be less than " + widthInCharacters + "." );

        return write(string, cursorX, cursorY, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Write a string to the cursor's position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param string     the string to write
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(String string, Color foreground) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (cursorX + string.length() >= widthInCharacters)
            throw new IllegalArgumentException("cursorX + string.length() " + (cursorX + string.length()) + " must be less than " + widthInCharacters + "." );

        return write(string, cursorX, cursorY, foreground, defaultBackgroundColor);
    }

    /**
     * Write a string to the cursor's position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param string     the string to write
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(String string, Color foreground, Color background) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (cursorX + string.length() >= widthInCharacters)
            throw new IllegalArgumentException("cursorX + string.length() " + (cursorX + string.length()) + " must be less than " + widthInCharacters + "." );

        return write(string, cursorX, cursorY, foreground, background);
    }

    /**
     * Write a string to the specified position.
     * This updates the cursor's position.
     * @param string     the string to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(String string, int x, int y) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (x + string.length() >= widthInCharacters)
            throw new IllegalArgumentException("x + string.length() " + (x + string.length()) + " must be less than " + widthInCharacters + "." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")" );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        return write(string, x, y, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Write a string to the specified position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param string     the string to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(String string, int x, int y, Color foreground) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (x + string.length() >= widthInCharacters)
            throw new IllegalArgumentException("x + string.length() " + (x + string.length()) + " must be less than " + widthInCharacters + "." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")" );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        return write(string, x, y, foreground, defaultBackgroundColor);
    }

    /**
     * Write a TerminalText to the specified position.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param text       the TerminalText to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @return new Y offset if needed.
     */
    public Integer write(TerminalText text, int x, int y) {
        for (TerminalChar c : text) {
        	if (x >= widthInCharacters) {
        		x = 5;
        		y++;
        	}
            write(c.glyph, x++, y, c.foreground, c.background);
        }
        return y;
    }

    /**
     * Write a string to the specified position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param string     the string to write
     * @param x          the distance from the left to begin writing from
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel write(String string, int x, int y, Color foreground, Color background) {
        if (string == null)
            throw new NullPointerException("string must not be null." );

        if (x + string.length() >= widthInCharacters)
            throw new IllegalArgumentException("x + string.length() " + (x + string.length()) + " must be less than " + widthInCharacters + "." );

        if (x < 0 || x >= widthInCharacters)
            throw new IllegalArgumentException("x " + x + " must be within range [0," + widthInCharacters + ")." );

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")." );

        if (foreground == null)
            foreground = defaultForegroundColor;

        if (background == null)
            background = defaultBackgroundColor;

        for (int i = 0; i < string.length(); i++) {

            write(string.charAt(i), x + i, y, foreground, background);
        }
        return this;
    }

    /**
     * Write a string to the center of the panel at the specified y position.
     * This updates the cursor's position.
     * @param string     the string to write
     * @param y          the distance from the top to begin writing from
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel writeCenter(String string, int y) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (string.length() >= widthInCharacters)
            throw new IllegalArgumentException("string.length() " + string.length() + " must be less than " + widthInCharacters + "." );

        int x = (widthInCharacters - string.length()) / 2;

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        return write(string, x, y, defaultForegroundColor, defaultBackgroundColor);
    }

    /**
     * Write a string to the center of the panel at the specified y position with the specified foreground color.
     * This updates the cursor's position but not the default foreground color.
     * @param string     the string to write
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel writeCenter(String string, int y, Color foreground) {
        if (string == null)
            throw new NullPointerException("string must not be null" );

        if (string.length() >= widthInCharacters)
            throw new IllegalArgumentException("string.length() " + string.length() + " must be less than " + widthInCharacters + "." );

        int x = (widthInCharacters - string.length()) / 2;

        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")" );

        return write(string, x, y, foreground, defaultBackgroundColor);
    }

    /**
     * Write a string to the center of the panel at the specified y position with the specified foreground and background colors.
     * This updates the cursor's position but not the default foreground or background colors.
     * @param string     the string to write
     * @param y          the distance from the top to begin writing from
     * @param foreground the foreground color or null to use the default
     * @param background the background color or null to use the default
     * @return this for convenient chaining of method calls
     */
    public ExtendedAsciiPanel writeCenter(String string, int y, Color foreground, Color background) {
        if (string == null)
            throw new NullPointerException("string must not be null." );

        if (string.length() >= widthInCharacters)
            throw new IllegalArgumentException("string.length() " + string.length() + " must be less than " + widthInCharacters + "." );

        int x = (widthInCharacters - string.length()) / 2;
        
        if (y < 0 || y >= heightInCharacters)
            throw new IllegalArgumentException("y " + y + " must be within range [0," + heightInCharacters + ")." );

        if (foreground == null)
            foreground = defaultForegroundColor;

        if (background == null)
            background = defaultBackgroundColor;

        for (int i = 0; i < string.length(); i++) {
            write(string.charAt(i), x + i, y, foreground, background);
        }
        return this;
    }

    /**
     * Write a multiline string to the terminal at the specified position.
     * @param string The multiline string to write, with `\n` denoting newlines.
     * @param x the distance from the left to begin writing each line from.
     * @param y the distance from the top to begin writing from.
     * @return this, for convenient chaining of method calls.
     */
    public ExtendedAsciiPanel writeMultiline(String string, int x, int y) {
        for (String line: string.split("\n")) {
            write(line, x, y++);
        }

        return this;
    }

    /**
     * Write a multiline string to the terminal at the specified position, centered horizontally.
     *
     * @param string The multiline string to write, with `\n` denoting newlines.
     * @param y the distance from the top to begin writing from.
     * @return this, for convenient chaining of method calls.
     */
    public ExtendedAsciiPanel writeCenterMultiline(String string, int y) {
        for (String line : string.split("\n")) {
            writeCenter(line, y++);
        }

        return this;
    }
}
