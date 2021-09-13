import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
/**
 * Have it be optional for player to view
 * Explains the rules of minesweeper as well as some tricks to get them started
 *
 * @author Yumi Jin
 * @version 6-5-18
 */
public class TutorialWindow extends JFrame
{
    private String[] tutorialImages =
        {   "blank.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png",
            "7.png", "8.png", "bombclicked.png"};

    private GameDifficulty g;
    private TutorialWindow t;
    private int clicks = 0;

    /**
     * Constructor for objects of class DirectionsWindow
     */
    public TutorialWindow()
    {
        ImagePanel panel = new ImagePanel(new ImageIcon(tutorialImages[0]).getImage());
        
        JFrame frame = new JFrame();
        frame.setTitle("Tutorial");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        
        frame.addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent me) { }

                public void mouseReleased(MouseEvent me) { }

                public void mouseEntered(MouseEvent me) { }

                public void mouseExited(MouseEvent me) { }

                public void mouseClicked(MouseEvent me) 
                { 
                    if(me.getButton() == MouseEvent.BUTTON1) //If the click on the screen is a left click...
                    {
                        panel.setVisible(false);
                        ImagePanel panel = new ImagePanel(new ImageIcon(tutorialImages[clicks]).getImage());
                        frame.getContentPane().add(panel);
                        frame.pack();
                        frame.setVisible(true);
                        clicks++;
                    }
                    if(me.getButton() == MouseEvent.BUTTON3) //If the click on the screen is a right click...
                    {
                        panel.setVisible(false);
                        clicks--;
                        ImagePanel panel = new ImagePanel(new ImageIcon(tutorialImages[clicks]).getImage());
                        frame.getContentPane().add(panel);
                        frame.pack();
                        frame.setVisible(true);
                    }
                }
            });

        pack();
    }

    class ImagePanel extends JPanel 
    {
        private Image img;

        public ImagePanel(String img) 
        {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img) 
        {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }

    }
}
