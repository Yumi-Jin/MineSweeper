import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import java.util.*;
/**
 * Display for the actual Game
 * Includes methods used to play the game
 *
 * @author Yumi Jin
 * @version 6-5-18
 */
public class GameWindow extends JFrame
{
    private JPanel headerPanel;
    private JPanel template;
    private JLabel messageLabel;
    private JTextField hello;
    public int c;
    public int r;
    public int flags = 0;
    public int bombs;

    public ArrayList<Integer> shown;
    public createTemplate t = new createTemplate();

    public Tile[] tiles;
    public JButton[] buttons;

    private final int windowWidth = 24 * r;
    private final int windowHeight = 24 * c;
    /**
     * Constructor for objects of class Driver
     */
    public GameWindow(Tile[] t, int bomb, int row, int col)
    {
        setTitle("Mine Sweeper");
        bombs = bomb;
        tiles = t;
        r = row;
        c = col;

        JMenuBar b = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        b.add(menu);

        JMenuItem item = new JMenuItem("New Game");
        
        item.addActionListener(new RestartGame());
        
        menu.add(item);

        setLayout(new BorderLayout());

        add(b, BorderLayout.NORTH);

        setSize(windowWidth, windowHeight);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildTemplate();
        add(template);
        setResizable(false);
        pack();
        setVisible(true);

    }

    /**
     * Creates all the JButtons used for the game
     * Each represents a Tile object, and have their own values and types that will soon be assigned
     *
     */
    private void buildTemplate()
    {
        template = new JPanel();
        buttons = new JButton[r*c];
        //Creates a GridLayout of r*c for all the buttons
        template.setLayout(new GridLayout(r,c,0,0));
        //Generating every single button, setting them to a specific size of the button images to make them look like Minesweeper buttons
        //Adding a left click action (Action Listener) and a right click action (Mouse Listener)
        //The names are the most important part of the buttons, it will be used as a coordinate to determine which Tile goes to which Button
        for(int x = 0; x < r*c; x++)
        {
            buttons[x] = new JButton();
            buttons[x].setPreferredSize(new Dimension (24,24));
            buttons[x].addActionListener(new TileButtonListener());
            buttons[x].setIcon(new ImageIcon("button.png"));
            buttons[x].setName(x + "");

            template.add(buttons[x]);
            //Setting y as x to use in the Mouse Listener
            int y = x;
            buttons[x].addMouseListener(new MouseAdapter()   
                {
                    public void mousePressed(MouseEvent me) { }

                    public void mouseReleased(MouseEvent me) { }

                    public void mouseEntered(MouseEvent me) { }

                    public void mouseExited(MouseEvent me) { }

                    public void mouseClicked(MouseEvent me) 
                    { 
                        //The flag counter is necessary to determine if the user wins or not
                        if(me.getButton() == MouseEvent.BUTTON3) //If the click on the button is a right click...
                        {
                            if (buttons[y].toString().indexOf("=button.png") >= 0)//If it's a button
                            {
                                //Sets the button as a flag tile in the Tile object and displays it
                                tiles[y].setFlag(true);
                                flags++;
                                System.out.println(flags);
                                buttons[y].setIcon(new ImageIcon("flagbutton.png"));
                            }
                            else if (buttons[y].toString().indexOf("=flagbutton.png") >= 0)//If it's a flag tile
                            {
                                //Unflags the button in the Tile object and displays it
                                tiles[y].setFlag(false);
                                flags--;
                                System.out.println(flags);
                                buttons[y].setIcon(new ImageIcon("button.png"));
                            }
                            if (flags == bombs)
                            {
                                gameOver();
                            }
                        }
                    }
                });

        }
    }

    public void gameOver()
    {

    }

    public ArrayList<Integer> chainBlank(int coordinates)
    {
        boolean dupe = false;

        //Going to be filled with Tiles in the radius of a blank Tile
        ArrayList<Integer> list = t.getRadius(coordinates, r, c);;
        //Going to be full of coordinates that are going to be displayed to the user
        shown = new ArrayList<Integer>();

        //For each tile in the radius of the blank tile...
        for (int i : list)
        {
            //Compares each coordinates from the radius to each in shown, checks if there are duplicates
            for (int x : shown)
            {
                if (i == x)
                    dupe = true;
            }

            //if there are no duplicates, the coordinates get added to shown
            if (!dupe)
            {
                shown.add(i);
                //If the Tile associated with the coordinates is a blank tile, the entire method will be called again
                if (tiles[i].getType() == 0)
                    shown.addAll(chainBlank(i));
            }
            dupe = false;
        }

        return shown;
    }

    public class RestartGame implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            GameDifficulty gd = new GameDifficulty();
            setVisible(false);
        }
    }

    public class TileButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton x = (JButton)e.getSource();
            int i = Integer.parseInt(e.toString().substring(90));

            if (!tiles[i].checkFlag()) //If tile is not flagged
            {
                if (tiles[i].getType() == 9) //If the tile is a hidden bomb tile
                {
                    x.setIcon(new ImageIcon(tiles[i].getImage()));
                    //game over method somewhere that will reveal all bomb tiles
                }
                else if (tiles[i].getType() == 0) //If the tile is a blank tile (visible or hidden)
                {
                    ArrayList<Integer> show = chainBlank(i);
                    //Reveals all tile that was surrounding the blank tiles and the blank tiles
                    for (int s: show)
                    {
                        buttons[s].setIcon(new ImageIcon(tiles[s].getImage()));
                    }
                }
                else if (x.toString().indexOf("button.png") >= 0) //If the tile is a hidden number tile
                {
                    x.setIcon(new ImageIcon(tiles[i].getImage()));
                }
                else //If the tile is a visible number tile
                {
                    ArrayList<Integer> list = t.getRadius(i, r, c);
                    int rflag = 0;
                    int b = 0;
                    ArrayList<Integer> n = new ArrayList<Integer>();

                    //For each Tile in the radius of the visible number tile..
                    for (int l: list)
                    {
                        //Counts up both the number of flags and bombs (doesn't matter if flag is on correct bomb)
                        //Stores the coordinates of the flags as well
                        if (tiles[l].checkFlag())
                        {
                            rflag++;
                            n.add(l);
                        }
                        if (tiles[l].getType() == 9)
                            b++;
                    }

                    //If there are correct number of flags for the number tile...
                    if (rflag == b)
                    {
                        //For each Tile in the radius of the visible number tile...
                        for (int l: list)
                        //All the non-flagged tile in the radius will be shown to the user
                            if (!Arrays.asList(n).contains(l))
                                buttons[l].setIcon(new ImageIcon(tiles[l].getImage()));
                    }
                }
            }
        }
    }
}