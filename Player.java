/**
 * Class Player helps create Player objects that will interract on the Tic-Tac-Toe Board using their respective pawns
 *
 * @author (Abdias)
 * @version (June 25, 2021)
 */
public class Player {
    // instance variables - replace the example below with your own
    private String name;
    private char pawn;
    private int score;

    /**
     * Constructor for objects of class Player with default values
     */
    public Player() {
        // initialise instance variables
        name = "";
        pawn = ' ';
        score = 0;

    }

    /**
     * Constructor for objects of class Player with provided values
     */
    public Player(String name, char pawn, int score) {
        // initialise instance variables
        this.name = name;
        this.pawn = pawn;
        this.score = score;

    }

    /**
     * getName - returns the value of the name property
     *
     * @param     non
     * @return    String
     */
    public String getName() {

        return name;

    }

    /**
     * getPawn - Returns the value of the pawn property
     *
     * @param     none
     * @return    char
     */
    public char getPawn() {

        return pawn;

    }

    /**
     * getScore - returns the value of the score porperty
     *
     * @param     none
     * @return    int
     */
    public int getScore() {

        return score;

    }
        
    /**
     * setName - sets the provided value as new value for the name property
     *
     * @param     String
     * @return    void
     */
        public void setName(String name) {

        this.name = name;

    }

    /**
     * setPawn - sets the provided value as new value for the pawn property
     *
     * @param     char
     * @return    void
     */
    public void setPawn(char pawn) {

        this.pawn = pawn;
 
    }

    /**
     * setScore - sets the provided value as new value for the score property
     *
     * @param     int
     * @return    void
     */
    public void setScore(int score) {

        this.score = score;

    }

    /**
     * updateScore - increments a Player object's current score by 1
     *
     * @param     String
     * @return    void
     */
    public void updateScore() {

        this.score = this.score + 1;

    }
}