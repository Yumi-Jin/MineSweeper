import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * Gets User Input to determine if user wants to look at the tutorial or play the game immidiently
 *
 * @author Yumi Jin
 * @version 6-5-18
 */
public class DirectionsWindow extends JFrame
{
    private JLabel messageLabel;
    private JPanel directions = new JPanel();
    private JButton tut = new JButton("tutorial");
    private JButton game = new JButton("game");

    private GameDifficulty g;
    private TutorialWindow t;

    /**
     * Constructor for objects of class DirectionsWindow
     */
    public DirectionsWindow()
    {
        setTitle("Directions");
        setLayout(new GridLayout(1,3));

        add(directions);
        setVisible(true);
        directions.setSize(50, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        messageLabel = new JLabel("Would you like the see the tutorial or start to play the game?");
        directions.add(messageLabel);

        tut.setName("tutorial");
        tut.addActionListener(new start());
        tut.setPreferredSize(new Dimension (100,24));
        directions.add(tut);

        game.addActionListener(new start());
        game.setPreferredSize(new Dimension (100,24));
        directions.add(game);

        pack();
    }

    public class start implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.toString().indexOf("tutorial") >= 0)
                t = new TutorialWindow();
            else
                g = new GameDifficulty();
            setVisible(false);
        }
    }
}