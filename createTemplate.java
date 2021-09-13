import java.util.*;
/**
 * Holds methods used to generate a random unique template each game
 *
 * @author Yumi Jin
 * @version 6-5-18
 */
public class createTemplate
{
    public Tile[] tiles;

    public Tile[] genTemplate(int bomb, int row, int col)
    {
        int r = row;
        int c = col;
        int bombs = bomb;

        //Making tile values into bombs and the numbers surrounding each bomb
        //Don't need to worry about having the assign the rest of the tiles as blank since all tiles are automatically assigned as it
        tiles = new Tile[r*c];

        for (int t = 0; t < tiles.length; t++)
            tiles[t] = new Tile();
        
        int[] bombLoc = genBombs(bombs, r, c); //Grabs randomly generated unique coordinates of all bombs
        ArrayList<Integer> bombRad = new ArrayList<Integer>(); //Used to store coordinates of Tiles in the radius of those bombs
        
        //Sets the coordinates from the bombLoc array into actual Bombs in the Tiles array
        for (int i = 0; i < bombs; i++)
        {
            tiles[bombLoc[i]].setType(9);//Set the Tile as a bomb from the bombLoc coordinates
        }

        //Generates the number tiles surrounding each bomb Tile
        for (int p = 0; p < bombs; p++)
        {
            //getting the coordinates of the Tiles in the radius of each bomb Tile
            bombRad = getRadius(bombLoc[p], r, c);
            
            //Loops for each empty Tile found next to the bomb
            for (int a = 0; a < bombRad.size(); a++)
            {
                ArrayList<Integer> blankRad = getRadius(bombRad.get(a), r, c);
                int b = 0;
                //if the tile from the bomb Tile's radius is not a bomb...
                if (tiles[bombRad.get(a)].getType() != 9)
                {
                    //Counts the number of bombs surrounding that Tile
                    for (int t = 0; t < blankRad.size(); t++)
                    {
                        if (blankRad.get(t) == 9)
                            b++;
                    }
                    //Sets the non-bomb tile to a number tile corresponding to the number of bombs surrounding it
                    tiles[bombRad.get(a)].setType(b);
                }
            }
        }
        
        return tiles;
    }

    /**
     * Will generate unique random coordinates for each bomb and store them in an array
     * 
     * @param  number of bombs, and dimensions of tiles 
     * @return the 2d array filled with bombs coordinates
     */
    public int[] genBombs(int bombs, int r, int c)
    {
        //int array to store the coordinates of each bomb
        int[] bombLoc = new int[bombs];
        //Random number that will range from 0 to (r*c -1)
        int random = 0;
        //variable used to make sure they're all unique
        boolean rand = false;

        //Loops for number of bombs
        for(int i = 0; i < bombs; i++)
        {
            //Keeps looping until the random number generated is not the same as any other
            while(!rand)
            {
                rand = true;
                //generates random num from 0 to (r*c - 1)
                random = (int)(Math.random() * ((r*c) - 1));
                //Checks every single element in the bombLoc array to compare to random
                //if atleast one element is equal, the while loop will continue to loop until they're all unique
                for (int x = 0; x < bombs; x++)
                {
                    if (random == bombLoc[x])
                    {
                        rand = false;
                    }
                }
            }
            //Sets the bomb to the coordination random has created
            bombLoc[i] = random;
            rand = false;
        }
        return bombLoc;
    }

    /**
     * Will return each Tile coordinates that are in the radius of the tile
     *
     * @param coordinates - Ranging from 0 to (r*c - 1), holds the value of a single number that represents 
     * a single Tile/Button
     * @param r - the number of rows in the template
     * @param c - the number of columns in the template
     * 
     * @return ArrayList<Integer> types - the coordinates of each Tile surrounding the coordinates given
     */
    public ArrayList<Integer> getRadius(int coordinates, int r, int c)
    {
        ArrayList<Integer> types = new ArrayList<Integer>();
        int row = coordinates/c;
        int col = coordinates%c;

        if (row == 0) //if tile is at the top
        {
            types.add(coordinates + c);
            if (col == 0) //if tile is in top left corner
            {
                types.add(coordinates + 1);
                types.add(coordinates + 1 + c);
            }
            else if (col == c - 1) //if tile is in top right corner
            {
                types.add(coordinates - 1);
                types.add(coordinates - 1 + c);
            }
            else //if tile is not in a corner
            {
                types.add(coordinates + 1);
                types.add(coordinates - 1);
                types.add(coordinates + 1 + c);
                types.add(coordinates - 1 + c);
            }
        }
        else if (row == r - 1) //if tile is at the bottom
        {
            types.add(coordinates - c);
            if(col == 0) //if tile is in bottom left corner
            {   
                types.add(coordinates + 1);
                types.add(coordinates + 1 - c);    
            }
            else if (col == c - 1) //if tile is in bottom right corner
            {
                types.add(coordinates - 1);
                types.add(coordinates - 1 - c);
            }
            else //if tile is not in a corner
            {
                types.add(coordinates + 1);
                types.add(coordinates - 1);
                types.add(coordinates + 1 - c);
                types.add(coordinates - 1 - c);
            }
        }
        else //if tile is not at the top, bottom or in the corners
        {
            types.add(coordinates + c);
            types.add(coordinates - c);
            if (col == 0) //if tile is at the left
            {
                types.add(coordinates + 1);
                types.add(coordinates + 1 - c);
                types.add(coordinates + 1 + c);
            }
            else if (col == c - 1) //if tile is at the right
            {
                types.add(coordinates - 1);
                types.add(coordinates - 1 - c);
                types.add(coordinates - 1 + c);
            }
            else //if tile is in the center
            {
                types.add(coordinates + 1 + c);
                types.add(coordinates - 1 + c);
                types.add(coordinates + 1);
                types.add(coordinates - 1);
                types.add(coordinates + 1 - c);
                types.add(coordinates - 1 - c);
            }
        }
        return types;
    }

}
