/**
 * Tile object that stores the value of a single tile
 * Each tile object has a value of either a bomb, empty tile, or a number tile
 * As long as the tile isn't revealed in the game window, it could be a flag tile 
 * (Checker for it not being revealed is in other classes)
 *
 * @author Yumi Jin
 * @version 6-5-18
 */
public class Tile
{
    public int type = 0;
    public boolean isFlagged = false;
    private String[] typeButtons =
    {   "blank.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png",
        "7.png", "8.png", "bombclicked.png"};
    //9 = bomb, 0 = empty, 1-8 = numbers
    /**
     * Returns the type of the current tile
     *
     * @return type - the type of the tile, 0 for blank, 1-8 for it's corresponding number and 9 for a bomb
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Sets the type of the current tile
     * Used to set up the template rather than in game
     *
     * @param y - the value it is going to be assigned
     */
    public void setType(int y)
    {
        type = y;
    }
    
    /**
     * Returns the name of the image as a string of a Tile type
     *
     * @return typeButtons[type] - the name of the image that is corresponding to the type of Tile
     */
    public String getImage()
    {
        return typeButtons[type];
    }
    
    /**
     * Method setFlag
     *
     * @param b A parameter
     */
    public void setFlag(boolean b)
    {
        isFlagged = b;
    }
    
    public boolean checkFlag()
    {
        return isFlagged;
    }
}
