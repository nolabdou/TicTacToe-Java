/**
 * This program allows two players to take turn on a Tic-Tac-Toe board using their respective pawn symbol
 * Ends the current game when a player wins or when the board is full
 * Keeps tracks of every player's wins
 * 
 * The last winner plays first
 * When there's no previous winner one is randomly chosen to start the next game
 * 
 *
 * @author (Abdias Noel)
 * @version (June 25, 2021)
 */
import java.util.*;
public class TicTacToe
{
       public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("\n  Welcome to Abdias' Tic-Tac-Toe Game!\n");

        System.out.println("  Please enter the name of the first player.");
        String name1 = input.nextLine();
        clearScreen();

        System.out.println("  Please enter the name of the second player.");
        String name2 = input.nextLine();
        clearScreen();
        //create player objects
        Player player1 = new Player(name1, 'X', 0); // Player class instantiated using the explicit constructor

        Player player2 = new Player(); // Player class instantiated using the implemented default constructor 

        player2.setName(name2);
        player2.setPawn('O');

        // Declaration and definition of the Board Array
        char gameBoard[] = new char[9];
        resetBoard(gameBoard); // sets all the values of the gameboard to ' ' 

        int previousgameCount = 0; // Keeps track of the amount of game has been played : to be used by the TurnIssuer method
        String previousWinner = "N/A"; // Receives the previous winner's name when there is one: to be used by the TurnIssuer method

        int providedPosition; // receives the position that the current player attempts to play in

        char menuCommand; // receives the user's choice from the main menu commands
        char exitOption;
        String filePassword="";
        char currentpawn; // receives the active pawn based on the player's turn
        //Abdi file= new abdi();
        int i = 0; // keeps track of the the idexes for turnArray Array

        do {

            printMenu();
            menuCommand = Character.toLowerCase(input.next().charAt(0)); // converts the input character to its lowercase
            clearScreen();
            switch (menuCommand) {

                case 'i':
                    clearScreen();
                    printGameInstruction();
                    break;

                case 'p':

                    clearScreen();

                    // Array with player's names arranged secquentially based on the turns that were issued to them
                    String turnArray[] = turnIssuer(player1.getName(), player2.getName(), previousgameCount, previousWinner);
                    do {

                        do {

                            clearScreen();
                            printGameBoard(gameBoard);
                            System.out.println("\n  It is your turn " + turnArray[i] + "!");

                            if (player1.getName().equals(turnArray[i])) {
                                currentpawn = player1.getPawn();
                            } else {
                                currentpawn = player2.getPawn();
                            }
                            System.out.println("  You're " + currentpawn + "'s");

                            providedPosition = positionParser(input.nextLine());
                            clearScreen();

                        } while (providedPosition<0 || isTaken(gameBoard, providedPosition) == true);

                        gameBoard[providedPosition] = currentpawn; // if the position is valid and not taken the pawn is added at the chosen position

                        //This if statement prevents the variable "i" from exceeding the amount of indexes in turnArray 
                        if (i<= 7) {
                            i++;

                        }

                        // if a player wins or the board is full the loop breaks                  
                    } while (isFull(gameBoard) == 0 && (wins(player1, gameBoard) == 0) && (wins(player2, gameBoard) == 0));

                    //everytime the loop breaks this value is incremented
                    previousgameCount++;

                    /*
                        Test of the reasons that might have broken the loop
                        Winner's name is displayed
                        Winner's score is updated
                        previousWinner is set
                        or a tie is declared
                    */
                    if (wins(player1, gameBoard) == 1) {
                        System.out.println("  " + player1.getName() + " won this game!\n");
                        printMiniGameBoard(gameBoard);
                        player1.updateScore();
                        previousWinner = player1.getName();

                    } else if (wins(player2, gameBoard) == 1) {
                        System.out.println("  " + player2.getName() + " won this game!\n");
                        printMiniGameBoard(gameBoard);
                        player2.updateScore();
                        previousWinner = player2.getName();

                    } else {
                        System.out.println("  This Game is a Tie!");
                        printMiniGameBoard(gameBoard);
                        previousWinner = "N/A";

                    }
                    i = 0; // Loop controll variable is reset to zero
                    resetBoard(gameBoard); // board's values are reset
                    break; // exit the inner loop

                    
                case 'l':
                            System.out.println("  Loading game configuration is coming soon...");
                    break;
                    
                    
                case 'm':

                    clearScreen();
                    printMenu();
                    break;

                case 's':
                    clearScreen();
                    printScoreBoard(player1.getName(), player1.getScore(), player2.getName(), player2.getScore(), previousgameCount, previousWinner);
                    break;

                case 'e':
                    clearScreen();
                    do
                    {
                        System.out.println("  Enter Y below to save your game's configuration and N to exit the game without saving.");
                        exitOption = Character.toLowerCase(input.next().charAt(0));
                        clearScreen();
                        switch (exitOption) 
                        {
                            case 'y':
                                System.out.println("  Please Provide a password to lock your configuration file");
                                
                                break;
                                
                            case 'n':
                                
                                System.out.println("  Goodbye! See you next time ...");
                                System.exit(0); // The user stops the program's execution
                                break;
                        
                        }
                    }while(exitOption!='y' || exitOption!='n');
                    
                    break;

            }

        } while (menuCommand != 'e');

    }
    
        
        
        /**
     * printGameBoard - Displays the Tic-Tac-Toe board in all of its states
     *
     * @param     Char Array
     * @return    void
     */
    public static void printGameBoard(char gameBoard[]) {

        System.out.println("           A                B                C");
        System.out.println("    _______________  _______________  _______________");
        System.out.println("   |                |                |               |");
        System.out.println("  1|       " + gameBoard[0] + "        |       " + gameBoard[1] + "        |       " + gameBoard[2] + "       |");
        System.out.println("   |                |                |               |");
        System.out.println("    _______________  _______________  _______________");
        System.out.println("   |                |                |               |");
        System.out.println("  2|       " + gameBoard[3] + "        |       " + gameBoard[4] + "        |       " + gameBoard[5] + "       |");
        System.out.println("   |                |                |               |");
        System.out.println("    _______________  _______________  _______________");
        System.out.println("   |                |                |               |");
        System.out.println("  3|       " + gameBoard[6] + "        |       " + gameBoard[7] + "        |       " + gameBoard[8] + "       |");
        System.out.println("   |                |                |               |");
        System.out.println("    _______________  _______________  _______________");
    }
        
        /**
     * printMiniGameBoard - Displays a miniature version of the Tic-Tac-Toe board in all of its states
     *
     * @param     Char Array
     * @return    void
     */
    public static void printMiniGameBoard(char gameBoard[]) {

        System.out.println("       A       B         C");
        System.out.println("    _______  _______  _______");
        System.out.println("   |        |        |       |");
        System.out.println("  1|   " + gameBoard[0] + "    |   " + gameBoard[1] + "    |   " + gameBoard[2] + "   |");
        System.out.println("   |        |        |       |");
        System.out.println("    _______  _______  _______");
        System.out.println("   |        |        |       |");
        System.out.println("  2|   " + gameBoard[3] + "    |   " + gameBoard[4] + "    |   " + gameBoard[5] + "   |");
        System.out.println("   |        |        |       |");
        System.out.println("    _______  _______  _______");
        System.out.println("   |        |        |       |");
        System.out.println("  3|   " + gameBoard[6] + "    |   " + gameBoard[7] + "    |   " + gameBoard[8] + "   |");
        System.out.println("   |        |        |       |");
        System.out.println("    _______  _______  _______\n");
    }
        
       /**
     * printScoreBoard - Displays the Score Board, the game count and the last winner
     *
     * @param     String,int, String,int,String
     * @return    void
     */

    public static void printScoreBoard(String playerName1, int player1Score, String playerName2, int player2Score, int gameCount, String winner) {

        System.out.println(" ________________________________________");
        System.out.println(" |                                      |");
        System.out.println(" |              Score Board             |");
        System.out.println(" |                                      |");
        System.out.println(" |______________________________________|");

        System.out.println("  Player1: " + playerName1 + "            Score: " + player1Score + "       \n");

        System.out.println("  Player2: " + playerName2 + "            Score: " + player2Score + "       \n");
        System.out.println(" ----------------------------------------");
        System.out.println("  Game Count : " + gameCount);
        System.out.println("  Last Winner: " + winner);

    }
       
        /**
     * positionParser - Interprets the chosen position if valid or else it return -1
     *
     * @param     String
     * @return    int
     */

    public static int positionParser(String command) {
        if (command.equals("a1") || command.equals("1a") || command.equals("1")) {
            return 0;
        } else if (command.equals("b1") || command.equals("1b") || command.equals("2")) {
            return 1;
        } else if (command.equals("c1") || command.equals("1c") || command.equals("3")) {
            return 2;
        } else if (command.equals("a2") || command.equals("2a") || command.equals("4")) {
            return 3;
        } else if (command.equals("b2") || command.equals("2b") || command.equals("5")) {
            return 4;
        } else if (command.equals("c2") || command.equals("2c") || command.equals("6")) {
            return 5;
        } else if (command.equals("a3") || command.equals("3a") || command.equals("7")) {
            return 6;
        } else if (command.equals("b3") || command.equals("3b") || command.equals("8")) {
            return 7;
        } else if (command.equals("c3") || command.equals("3c") || command.equals("9")) {
            return 8;
        } else {
            return -1; // -1 means an invalid position or command was entered by the user
        }
    }
            
       /**
     * clearScreen - Enters about 100 empty lines to clear out the terminal
     *
     * @param     none
     * @return    void
     */
    public static void clearScreen() {

        for (int line = 0; line<100; line++) {
            System.out.println("\u000c");
        }

    }
            
      /**
     * printGameInstruction - Displays instructions on the commands to play in any position on the board
     *
     * @param     none
     * @return    void
     */
    public static void printGameInstruction() {
        System.out.println("  Enter: ");
        System.out.println("  A1, 1A or 1 for the 1st square");
        System.out.println("  B1, 1B or 2 for the 2nd square");
        System.out.println("  C1, 1C or 3 for the 3th square");

        System.out.println("  A2, 2A or 4 for the 4th square");
        System.out.println("  B2, 2B or 5 for the 5th square");
        System.out.println("  C2, 2C or 6 for the 6th square");

        System.out.println("  A3, 3A or 7 for the 7th square");
        System.out.println("  B3, 3B or 8 for the 8th square");
        System.out.println("  C3, 3C or 9 for the 9th square\n");

        System.out.println("  The commands are not case sensitive. \n");
    }
            

        /**
     * printMenu - Displays the main menu
     *
     * @param     none
     * @return    void
     */
    public static void printMenu() {
        System.out.println("  ************************************ ");
        System.out.println("  \n              Main Menu \n");
        System.out.println("  ************************************ ");
        System.out.println("  Enter : ");
        System.out.println("  I to print intructions ");
        System.out.println("  L to load s previous game Setting ");
        System.out.println("  P to play Game");
        System.out.println("  S to display Score Board");
        System.out.println("  E to exit the application");
    }
        
        
        
        
        
          /**
     * wins - Tests the pawn positions of a player to determine if he/she wins the current game or not
     * it returns 1 if the player wins, or else it returns 0
     *
     * @param     Player, char[] (gameBoard)
     * @return    int : 
     */

    public static int wins(Player player, char gameBoard[]) {
        char playerpawn = player.getPawn();
        if (
            (playerpawn == gameBoard[0] && playerpawn == gameBoard[1] && playerpawn == gameBoard[2]) ||
            (playerpawn == gameBoard[3] && playerpawn == gameBoard[4] && playerpawn == gameBoard[5])

            ||
            (playerpawn == gameBoard[6] && playerpawn == gameBoard[7] && playerpawn == gameBoard[8])

            ||
            (playerpawn == gameBoard[0] && playerpawn == gameBoard[3] && playerpawn == gameBoard[6])

            ||
            (playerpawn == gameBoard[1] && playerpawn == gameBoard[4] && playerpawn == gameBoard[7]) ||
            (playerpawn == gameBoard[2] && playerpawn == gameBoard[5] && playerpawn == gameBoard[8])

            ||
            (playerpawn == gameBoard[0] && playerpawn == gameBoard[4] && playerpawn == gameBoard[8]) ||
            (playerpawn == gameBoard[2] && playerpawn == gameBoard[4] && playerpawn == gameBoard[6])

        ) {
            return 1;
        } else {
            return 0;
        }
    }
        
        
        
        
    /**
     * turnIssuer - Issues the play turns based on the last winner and the game count at first.
     * When it cannot rely on the previous variable it picks one of the two players randomly
     *
     * @param     String,String, int, String
     * @return    String[]
     */

    public static String[] turnIssuer(String name1, String name2, int previousgameCount, String previousWinner) {
        String turnArray[] = new String[9];

        if (previousgameCount > 0) {
            if (previousWinner.equals(name1)) {
                turnArray[0] = name1;
                for (int i = 1; i<= 8; i++) {
                    if (i % 2 == 0) {
                        turnArray[i] = name1;
                    } else {
                        turnArray[i] = name2;
                    }
                }

            } else if (previousWinner.equals(name2)) {

                turnArray[0] = name2;
                for (int i = 1; i<= 8; i++) {
                    if (i % 2 == 0) {
                        turnArray[i] = name2;
                    } else {
                        turnArray[i] = name1;
                    }
                }

            } else {
                Random random = new Random();
                int place = random.nextInt(65 - 4);
                if (place % 2 == 0) {
                    turnArray[0] = name2;
                    for (int i = 1; i<= 8; i++) {
                        if (i % 2 == 0) {
                            turnArray[i] = name2;
                        } else {
                            turnArray[i] = name1;

                        }
                    }

                } else {

                    turnArray[0] = name1;
                    for (int i = 1; i<= 8; i++) {
                        if (i % 2 == 0) {
                            turnArray[i] = name1;
                        } else {
                            turnArray[i] = name2;
                        }
                    }

                }

            }
        } else {
            Random random = new Random();
            int placer = random.nextInt(10 - 1);
            if (placer % 2 == 0) {
                turnArray[0] = name2;
                for (int i = 1; i<= 8; i++) {
                    if (i % 2 == 0) {
                        turnArray[i] = name2;
                    } else {
                        turnArray[i] = name1;

                    }
                }

            } else {

                turnArray[0] = name1;
                for (int i = 1; i<= 8; i++) {
                    if (i % 2 == 0) {
                        turnArray[i] = name1;
                    } else {
                        turnArray[i] = name2;
                    }
                }

            }

        }

        return turnArray;
    }
    
     /**
     * isTaken - Verifies if the wanted position is not already occupied by another pawn
     * it returns true if the wanted position is already taken and false if it is not, thus available
     * 
     * @param     char[] (gameBoard), int (wanted position)
     * @return    Boolean
     */
    public static boolean isTaken(char gameBoard[], int position) {

        if (gameBoard[position] != ' ') {
            return true;
        } else {
            return false;
        }

    }
   
        
        /**
     * isFull - Verifies if any of the board's positions are available
     * it returns 1 if there are no position available
     * and it returns 0 if there is at least one available position
     *
     * @param     char[] (gameBoard)
     * @return    int
     */
    public static int isFull(char gameBoard[]) {
        if (gameBoard[0] == ' ' ||
            (gameBoard[1] == ' ') ||
            (gameBoard[2] == ' ') ||
            (gameBoard[3] == ' ') ||
            (gameBoard[4] == ' ') ||
            (gameBoard[5] == ' ') ||
            (gameBoard[6] == ' ') ||
            (gameBoard[7] == ' ') ||
            (gameBoard[8] == ' ')) {
            return 0;
        } else {
            return 1;

        }

    }
        
         
     /**
     * resetBoard - Sets all the indexes of the gameBoard to the space character
     *
     * @param  char[] (gameBoard)
     * @return    void
     */
        public static void resetBoard(char gameBoard[])
        {
        
         for(int i=0;i<=gameBoard.length-1;i++)
             {
                        gameBoard[i]=' ';
             } 
        
        }
        
}