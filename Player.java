
/**
 * Write a description of class TicTacToePlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private String name;
    private char pawn;
    private int score;
   

    
     /**
     * Constructor for objects of class TicTacToePlayer
     */
    public Player()
    {
        // initialise instance variables
        name="";
        pawn=' ';
        score=0;
        
    }
    
    
    /**
     * Constructor for objects of class TicTacToePlayer
     */
    public Player(String name, char pawn,int score)
    {
        // initialise instance variables
        this.name=name;
        this.pawn=pawn;
        this.score=score;
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getName()
    {
        
        return name;
        
    }
    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public char getPawn()
    {
        
        return pawn;
        
    }
    
    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int getScore()
    {
        
        return score;
        
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setName(String name)
    {
        
        this.name=name;
        
    }
    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void setPawn(char pawn)
    {
        
        this.pawn=pawn;
        
    }
    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  none
     * @return    void
     */
    public void setScore(int score)
    {
        
        this.score=score;
        
    }
    
     /**
     * An example of a method - replace this comment with your own
     *
     * @param  none
     * @return    void
     */
    public void updateScore(int score2)
    {
        
        this.score=this.score+score2;
        
    }
}
