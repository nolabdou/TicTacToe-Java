/**
 * Write a description of class TicTacToeGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class TicTacToe
{
        public static void main(String[] args)
        {
             
            
                     
             Scanner input=new Scanner(System.in);
             
             
             
             System.out.println("\n  Welcome to Abdias' Tic-Tac-Toe Game!\n");   
         
             System.out.println("  Please enter a Name for one of the two players");       
             String name1=input.nextLine();
             clearScreen();
             System.out.println("  Please enter a Name for the second player."); 
             String name2=input.nextLine();
             
             //create player objects
             Player player1= new Player(name1,'X',0);
             Player player2= new Player();
             
             player2.setName(name2);
             player2.setPawn('O');
             
             // Declaration and definition of the Board Array
             char gameBoard[]= new char [9];
             resetBoard(gameBoard); // sets all the values of the gameboard to ' ' 
             
             
             int validPosition []={0,1,2,3,4,5,6,7,8}; // Position the players are allowed to play in 
             char validCommand []={'i','p','b','e'}; // Valid commands
             
             int previousgameCount=0; // Keeps track of the amount of game has been played : to be used by the TurnIssuer method
             String previousWinner="";// Receives the previous winner's name : to be used by the TurnIssuer method
             
             String turnArray[]=turnIssuer(player1,player2,previousgameCount,previousWinner);
             // Array with player's names arranged secquentially based on the turns that were issued to them
             
             int providedPosition; // receives the position that the current player attempts to play in
             
             char menuCommand; // receives the user's choice from the main menu commands
             char currentpawn; // receives the active pawn based on the player's turn
             
            
             int i=0; // keeps track of the the idexes for turnArray Array
             
             
             clearScreen(); 
             do
             {
                
                 printMenu();
                 menuCommand=Character.toLowerCase(input.next().charAt(0));
                 clearScreen(); 
                 switch(menuCommand)
                 {
                    
                    case 'i': 
                    clearScreen();
                    printGameInstruction();
                    break;
                
                    
                    case 'p':  
                                       
                    clearScreen();
                       
                    
                    do
                    {
                       
                        do{
                        
                       
                        clearScreen();
                        printGameBoard(gameBoard);
                        System.out.println("\n  It is "+turnArray[i]+"'s Turn.");
                        
                        
                        
                        if(player1.getName().equals(turnArray[i]))
                        {
                            currentpawn=player1.getPawn();
                        }else
                        {
                            currentpawn=player2.getPawn();
                        }
                          System.out.println("  Using pawn: [ "+currentpawn+" ]");

                          providedPosition=positionParser(input.nextLine());
                          clearScreen();
                        
                    
                        
                                              
                        
                        
                       }while(providedPosition<0 || isTaken(gameBoard,providedPosition)==true);
                       
                       gameBoard[providedPosition]=currentpawn; // if the position is valid and not taken the pawn is added at the chosen position
                       
                       //This if statement prevents the variable "i" from exceeding the amount of indexes in turnArray 
                       if (i<=7)
                       {
                           i++;
                       
                       }
                       
                           // if a player wins or the board is full the loop breaks                  
                    }while(isFull(gameBoard)==0 && (wins(player1,gameBoard)==0) && (wins(player2,gameBoard)==0));
                    
                    
                    //everytime the loop breaks this value is incremented
                    previousgameCount++;
                    
                    
                    /*
                        Test of the reasons that might have broken the loop
                        Winner's name is displayed
                        Winner's score is updated
                        previousWinner is set
                        or a tie is declared
                    */
                    if(wins(player1,gameBoard)==1)
                        {
                            System.out.println("  "+player1.getName()+" won this game!\n"); 
                            printGameBoard(gameBoard);
                            player1.updateScore(1); 
                            previousWinner=player1.getName(); 
                            
                           
                        }else if(wins(player2,gameBoard)==1)
                        {
                            System.out.println("  "+player2.getName()+" won this game!\n");
                            printGameBoard(gameBoard);
                            player2.updateScore(1);
                            previousWinner=player2.getName();
                            
                            
                        }else
                        {
                            System.out.println("  This Game is a Tie!");

                            previousWinner="";
                            
                        }
                    i=0; // Loop controll variable is reset to zero
                    resetBoard(gameBoard); // board's values are reset
                    break;// exit the inner loop
                
           
                    case 'm':
                    
                    clearScreen();
                    printMenu();
                    break;
                
                
                    case 's': 
                    clearScreen();
                    printScoreBoard(player1.getName(), player1.getScore(),player2.getName(), player2.getScore());
                    break;
                
                    
                    
                    case 'e':  
                    clearScreen();
                    System.out.println("  Goodbye! See you next time ...");
                    System.exit(0); // The user stops the program's execution
                    break;
                
                 }
                
                
                
                
             }while(menuCommand!='e');
                       
             
             
        }
    
        
        
        public static void printGameBoard(char gameBoard[])
        {

          System.out.println("           A                B                C");
          System.out.println("    _______________  _______________  _______________");
          System.out.println("   |                |                |               |");
          System.out.println("  1|       "+gameBoard[0]+"        |       "+gameBoard[1]+"        |       "+gameBoard[2]+"       |");
          System.out.println("   |                |                |               |");
          System.out.println("    _______________  _______________  _______________");
          System.out.println("   |                |                |               |");
          System.out.println("  2|       "+gameBoard[3]+"        |       "+gameBoard[4]+"        |       "+gameBoard[5]+"       |");
          System.out.println("   |                |                |               |");
          System.out.println("    _______________  _______________  _______________");
          System.out.println("   |                |                |               |");
          System.out.println("  3|       "+gameBoard[6]+"        |       "+gameBoard[7]+"        |       "+gameBoard[8]+"       |");
          System.out.println("   |                |                |               |");
          System.out.println("    _______________  _______________  _______________");
        }
        
         public static void printScoreBoard(String playerName1, int player1Score, String playerName2, int player2Score)
        {
          
          System.out.println(" ________________________________________");
          System.out.println(" |                                      |");
          System.out.println(" |              Score Board             |");
          System.out.println(" |                                      |");
          System.out.println(" |______________________________________|");
         
          System.out.println("  Player1: "+playerName1+"            Score: "+player1Score+"       \n");
       
          System.out.println("  Player2: "+playerName2+"            Score: "+player2Score+"       \n");
       
        }
       
        public static int positionParser(String command)
        {
              if(command.equals("a1") || command.equals("1a") || command.equals("1"))
              {
                    return 0;
              }
              else if(command.equals("b1") || command.equals("1b") || command.equals("2"))
              {
                    return 1;
              }
              else if(command.equals("c1") || command.equals("1c") || command.equals("3"))
              {
                    return 2;
              }
              else if(command.equals("a2") || command.equals("2a") || command.equals("4"))
              {
                    return 3;
              }
              else if(command.equals("b2") || command.equals("2b") || command.equals("5"))
              {
                    return 4;
              }
              else if(command.equals("c2") || command.equals("2c") || command.equals("6"))
              {
                    return 5;
              }
              else if(command.equals("a3") || command.equals("3a") || command.equals("7"))
              {
                    return 6;
              }
              else if(command.equals("b3") || command.equals("3b") || command.equals("8"))
              {
                    return 7;
              }
              else if(command.equals("c3") || command.equals("3c") || command.equals("9"))
              {
                    return 8;
              }
              
              else
              {
                    return -1; // -1 means an invalid command was entered by the user
             }  
        }
            
        public static void clearScreen()
        {
            
            for(int line=0; line<100; line++)
            {
                    System.out.println("\u000c"); 
            }
            
        }
            
        public static void printGameInstruction()
        {
             System.out.println("  Enter: "); 
             System.out.println("  A1, 1A  or 1 for the 1st square");
             System.out.println("  B1, 1B or 2 for the 2nd square");
             System.out.println("  C1, 1C or 3 for the 3th square");
             
             System.out.println("  A2, 2A or 4 for the 4th square");
             System.out.println("  B2, 2B or 5 for the 5th square");
             System.out.println("  C2, 2C or 6 for the 6th square");
             
             System.out.println("  A3, 3A or 7 for the 7th square");
             System.out.println("  B3, 3B or 8 for the 8th square");
             System.out.println("  C3, 3C or 9 for the 9th square\n");
             
             System.out.println("  The commands are not case sensitive. "); 
        }
            

        public static void printMenu()
        {
             System.out.println("  ************************************ "); 
             System.out.println("  \n              Main Menu \n"); 
             System.out.println("  ************************************ "); 
             System.out.println("  Enter : "); 
             System.out.println("  I to print intructions ");
             System.out.println("  P to play Game");
             System.out.println("  S to display Score Board");
             System.out.println("  E to exit the application");
        }
        
        
        
        
        
        public static int wins(Player player, char gameBoard[])
        {
            char playerpawn=player.getPawn();
            if(
            (playerpawn==gameBoard[0] && playerpawn==gameBoard[1] && playerpawn==gameBoard[2]) 
            || 
            (playerpawn==gameBoard[3] && playerpawn==gameBoard[4] && playerpawn==gameBoard[5]) 
            
            || 
            (playerpawn==gameBoard[6] && playerpawn==gameBoard[7] && playerpawn==gameBoard[8]) 
            
            || 
            (playerpawn==gameBoard[0] && playerpawn==gameBoard[3] && playerpawn==gameBoard[6]) 
            
            || 
            (playerpawn==gameBoard[1] && playerpawn==gameBoard[4] && playerpawn==gameBoard[7]) 
            || 
            (playerpawn==gameBoard[2] && playerpawn==gameBoard[5] && playerpawn==gameBoard[8]) 
            
            || 
            (playerpawn==gameBoard[0] && playerpawn==gameBoard[4] && playerpawn==gameBoard[8]) 
            || 
            (playerpawn==gameBoard[2] && playerpawn==gameBoard[4] && playerpawn==gameBoard[6]) 
            
            )
            {
                return 1;
            }else
            {
                return 0;
            }
        }
        
        
        
        
        public static String[] turnIssuer(Player player1,Player player2,int previousgameCount, String previousWinner)
           {
            String turnArray []=new String[9];
            
            if(previousgameCount>0)
            {
                if(previousWinner.equals(player1.getName()))
                {
                    turnArray[0]=player1.getName();
                    for(int i=1;i<=8;i++)
                    {
                        if(i%2==0)
                        {
                            turnArray[i]=player1.getName();
                        }
                        else
                        {
                            turnArray[i]=player2.getName();
                        }
                    }
                
                
                }else
                {
                
                    turnArray[0]=player2.getName();
                    for(int i=1;i<=8;i++)
                    {
                        if(i%2==0)
                        {
                            turnArray[i]=player2.getName();
                        }
                        else
                        {
                            turnArray[i]=player1.getName();
                        }
                    }
                
                }     
            }
            else
            {
                Random random= new Random();
                int placer= random.nextInt(10-1);
                if(placer%2==0)
                {
                    turnArray[0]=player2.getName();
                    for(int i=1;i<=8;i++)
                    {
                        if(i%2==0)
                        {
                            turnArray[i]=player2.getName();
                        }
                        else
                        {
                        turnArray[i]=player1.getName();
                        
                        }
                    }
                    
                }
                else
                {
                
                    turnArray[0]=player1.getName();
                    for(int i=1;i<=8;i++)
                    {
                        if(i%2==0)
                        {
                            turnArray[i]=player1.getName();
                        }
                        else
                        {
                            turnArray[i]=player2.getName();
                        }
                    }
                
                }
            
            
            
            
            }
            
            return turnArray;
        }
    
        public static boolean isTaken( char gameBoard[],int position)
        {
                
            if(gameBoard[position]!=' ')
            {
                return true;
            }else
            {
                return false;
            }
                
            
        }
        
   
        
        public static int isFull(char gameBoard[])
        {
            if(gameBoard[0]==' '|| (gameBoard[1]==' ') || (gameBoard[2]==' ') || (gameBoard[3]==' ') || (gameBoard[4]==' ') || (gameBoard[5]==' ') || (gameBoard[6]==' ') || (gameBoard[7]==' ') ||(gameBoard[8]==' '))
            {
                return 0;
            }else
            { 
                return 1;
            
            }
        
        
            
         }
        
        public static void resetBoard(char gameBoard[])
        {
        
         for(int i=0;i<=gameBoard.length-1;i++)
             {
                        gameBoard[i]=' ';
             } 
        
        }
        
}