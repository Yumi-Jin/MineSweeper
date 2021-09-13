import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * Write a description of class GameDifficulty here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameDifficulty extends JFrame
{
    private JLabel messageLabel;
    private JPanel difficulty = new JPanel();
    public createTemplate ct;
    public Tile[] t;
    private JButton e = new JButton("easy");
    private JButton m = new JButton("medium");
    private JButton h = new JButton("hard");
    /**
     * Constructor for objects of class difficultyWindow
     */
    public GameDifficulty()
    {
        setTitle("Difficulty");
        setLayout(new GridLayout(1,3));

        add(difficulty);
        setVisible(true);
        difficulty.setSize(50, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messageLabel = new JLabel("What difficulty would you like to play with?");
        difficulty.add(messageLabel);

        e.addActionListener(new DifficultyListener());
        e.setName("easy");
        difficulty.add(e);

        m.addActionListener(new DifficultyListener());
        m.setName("medium");
        difficulty.add(m);

        h.addActionListener(new DifficultyListener());
        h.setName("hard");
        difficulty.add(h);

        pack();
    }
    public class DifficultyListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton x = (JButton)e.getSource();

            int bombs,r,c;
            //Each difficulty corresponds to the size of the template and number of bombs
            if (e.toString().indexOf("easy") >= 0)
            {
                bombs = 10;
                r = 9;
                c = 9;
            }
            else if (e.toString().indexOf("medium") >= 0)
            {
                bombs = 40;
                r = 16;
                c = 16;
            }
            else
            {
                bombs = 99;
                r = 16;
                c = 30;
            }
            t = new Tile[r*c];
            t = ct.genTemplate(bombs, r, c);
            
            GameWindow g = new GameWindow(t, bombs, r, c);

            setVisible(false);
        }
    }
}
