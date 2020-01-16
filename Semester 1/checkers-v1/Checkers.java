import java.util.Scanner;

/*
 * Created by Brady McIntosh
 * in the fall of 2017
 * to play a game of checkers
 * and confuse his professor.
 */

public class Checkers {
	
	char p1 = 'x'; //character representing player 1
	char p2 = 'o'; //character representing player 2
	char playerCurrent;
	boolean boardActive = false; //true if the board is active
	boolean pieceActive = false; //true if a piece is selected
	int pieceX = -1; //x address of the selected piece
	int pieceY = -1; //y address of the selected piece
	int score1; //score for player 1
	int score2; //score for player 2
	
	Scanner scanner = new Scanner(System.in);
	
	static class Piece {
		char team;
		boolean king;
		public Piece(char team, boolean king) {
			this.team = team;
			this.king = king;
		}
	}
	
	static Piece [][] board = new Piece[8][8]; //an 8x8 array of Pieces
	//note: array address works as [row][col], or [y][x]
	

	public static void main(String [] args) {
		
		Checkers c = new Checkers();
		
		System.out.println("\n  = = = = = = = = = =\n"+
		                   "  = C H E C K E R S =\n"+
		                   "  = W I T H   T H E =\n"+
		                   "  = B R A D S T E R =\n"+
		                   "  = = = = = = = = = =\n");
		
		
		System.out.println("Type 'begin' to start playing.\n"+
		                   "Type 'help' for more options.");
		
		c.parseInput();	
		
	}
	
	//the main program loop, accepts user input
	private void parseInput() {
		
		String input = scanner.nextLine();
		
		if (input.equals("begin")) {
			if (!boardActive) {
				beginGame(); //initializes the game board
				drawBoard(); //draws the board
				System.out.println("Enter the location of a piece to select it.");
				System.out.println("Type \"help\" for more options.");
			} else {
				System.out.println("The game has already begun!");
			}
			
		//if input is 2 characters
		} else if(input.length()==2) {
			//and the board is active
			if (boardActive) {
				//and no piece is selected
				if (!pieceActive) {
					selectPiece(input);
				//and a piece is selected
				} else {
					movePiece(input);
				}
			//and the board is not active
			} else {
				System.out.println("There are no pieces on the board right now.");
			}
			
		} else { switch(input) {
			case "help" :
				listOptions();
				break;
			case "reset" :
				resetGame();
				break;
			case "redraw" :
				drawBoard();
				break;
			case "end" :
				endGame(checkPiecesNumber());
				break;
			case "exit" :
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			case "deselect" :
				deselect();
				System.out.println("The current piece has been deselected.");
				break;
			case "endturn" :
				deselect();
				endTurn();
				drawBoard();
				break;
			case "dbs" :
				debugSwitch();
				break;
			case "dbv" :
				debugVariables();
				break;
			case "dbd" :
				debugDelete();
				break;
			case "dbm" :
				debugMove();
				break;
			case "dbh" :
				debugHelp();
				break;
			default:
				System.out.println("That is not a valid input.");
				break;
			}
		}
		
		if(boardActive) {
			//if player 2 has no pieces left
			if(checkPiecesNumber() == p1) {
				endGame(p1);
			//if player 2 has no pieces left
			} else if (checkPiecesNumber() == p2) {
				endGame(p2);
			}
		}
		
		parseInput(); //calls itself at the end of every loop
		
		// if(input.equals("begin")&&!boardActive) {
			// beginGame();
			// drawBoard();		
		// } else if((input.length()==2)&&boardActive) {
			// selectPiece(input);
		// } else if((input.length()==2)&&boardActive&&pieceActive) {
			// movePiece(input);
		// } else if(input.equals("help")) {
			// listOptions();
		// } else if(input.equals("reset")&&boardActive) {
			// resetGame();			
		// } else if(input.equals("exit")||input.equals("quit")) {
			// System.out.println("Goodbye!");
			// System.exit(0);			
		// } else {
			// System.out.println("That is not a valid input.");
		// }
		
		// parseInput(); //calls itself at the end of every loop
		
	}
	
	//draws the game board and prints some information
	private void drawBoard() {
		
		//makes room
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
							"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		//draw the 2d array board
		
		//if the current player is x
		if(playerCurrent == p1) {
			System.out.println("\n    a b c d e f g h  ");
			System.out.println("  = = = = = = = = = =");			
			for(int row = 0; row < 8; row++) {
				System.out.print((row+1)+" = ");
				for(int col = 0; col < 8; col++) {
					Piece piece = board[row][col];
					// if there is a piece
					if(piece != null) {
						System.out.print(piece.king? (char)(piece.team - 'a' + 'A')
										+" " : piece.team +" ");
					}
					// if there is no piece
					else {
						System.out.print("- ");
					}
				}
				System.out.println("=");
			}
		//if the current player is o
		} else {
			System.out.println("\n    h g f e d c b a  ");
			System.out.println("  = = = = = = = = = =");
			for(int row = 7; row > -1; row--) {
				System.out.print((row+1)+" = ");
				for(int col = 7; col > -1; col--) {
					Piece piece = board[row][col];
					// if there is a piece
					if(piece != null) {
						System.out.print(piece.king? (char)(piece.team - 'a' + 'A')
							+" " : piece.team +" ");
					}
					// if there is no piece
					else {
						System.out.print("- ");
					}
				}
				System.out.println("=");
			}
		}
		System.out.println("  = = = = = = = = = =\n");
		
		//print some useful info
		System.out.println("You are playing as team "+playerCurrent+".\n");
		
		if (pieceActive) {
			Piece piece = board[pieceY][pieceX];
			String address = ""+(char)(pieceX+'a')+(pieceY+1);
			if (piece.king) {
				System.out.printf("Kinged %c at %s selected.\n",piece.team,address);
			} else {
				System.out.printf("Normal %c at %s selected.\n",piece.team,address);
			}
		} else {
			System.out.println("No piece is selected right now.");
		}
		
	}
	
	//lists all valid commands
	private void listOptions() {
		
		//makes room
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
							"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		System.out.println("\n================== = //Navigation// = =================\n");
		System.out.println("\"begin\" --------------------------------- begin playing");
		System.out.println("\"reset\" ----- clear game and return to the start screen");
		System.out.println("\"redraw\" ------------------------ redraw the game board");
		System.out.println("\"end\" ----------- end the current game and print scores");
		System.out.println("\"exit\" -------------- end the game and exit the program");
		System.out.println("\"help\" ---------------- you already know what this does");
		System.out.println("\n=======================================================\n");
		
		System.out.println("\n=================== = //Gameplay// = ==================\n");
		System.out.println("\"a1\" (no piece selected) --------- select a piece at a1");
		System.out.println("\"a1\" (w/ piece selected) ------ move select piece to a1");
		System.out.println("\"deselect\" --------------- clear selection (pointless?)");
		System.out.println("\"endturn\" ------------------------ end the current turn");
		System.out.println("\"switchteam\" --------- change x's to o's and o's to x's");
		System.out.println("\n=======================================================");
		
		System.out.println("\nJust type \"redraw\" to return to the board.");
	}
	
	//sets up pieces and activates board
	private void beginGame() {
		
		//game is now active
		boardActive = true;
		
		//player's char is set to p1
		playerCurrent = p1;
		
		//sets scores to 0
		score1 = 0;
		score2 = 0;
		
		// initialize pieces
		board[0][0] = new Piece(p2, false); board[0][2] = new Piece(p2, false);
		board[0][4] = new Piece(p2, false); board[0][6] = new Piece(p2, false);
		board[1][1] = new Piece(p2, false); board[1][3] = new Piece(p2, false);
		board[1][5] = new Piece(p2, false); board[1][7] = new Piece(p2, false);
		board[2][0] = new Piece(p2, false); board[2][2] = new Piece(p2, false);
		board[2][4] = new Piece(p2, false); board[2][6] = new Piece(p2, false);
		
		board[5][1] = new Piece(p1, false); board[5][3] = new Piece(p1, false);
		board[5][5] = new Piece(p1, false); board[5][7] = new Piece(p1, false);
		board[6][0] = new Piece(p1, false); board[6][2] = new Piece(p1, false);
		board[6][4] = new Piece(p1, false); board[6][6] = new Piece(p1, false);
		board[7][1] = new Piece(p1, false); board[7][3] = new Piece(p1, false);
		board[7][5] = new Piece(p1, false); board[7][7] = new Piece(p1, false);
	}
	
	//clears board, deactivates game and returns to start screen
	private void resetGame() {
		
		System.out.println("resetGame() was called. Nothing happened!");
	}
	
	//clears board and prints scores, same as the win/lose screen
	private void endGame(char pWin) {
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
							"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		
		System.out.println("Game over!");
		System.out.println("\nPlayer 1 score: "+score1);
		System.out.println("Player 2 score: "+score2+"\n");
		
		if(pWin==p1) {
			System.out.println("Player 1 wins...");
		} else if(pWin==p2) {
			System.out.println("Player 2 wins...");
		} else {
			System.out.println("Looks like there's no winner this time...");
		}
		System.out.println("But what's important is that you had fun!");
		
		System.out.println("endGame() was called. Nothing happened!");
	}
	
	//deselects the current piece
	private void deselect() {
		
		pieceActive = false; //true if a piece is selected
		pieceX = -1; //x address of the selected piece
		pieceY = -1; //y address of the selected piece
	}
	
	//ends the current turn and cycles to the next, flipping the board
	private void endTurn() {
		
		playerCurrent = (playerCurrent==p1? p2 : p1);
	}
	
	//selects a piece at the input address
	private void selectPiece(String input) {
		
		int i = input.charAt(0)-'a';
		int j = input.charAt(1)-'1';
		
		// System.out.printf("selection = %d,%d\n",i,j); //DEBUG
		
		
		//determines the validity of the input address
		if((i+'a' >= 'a'+0 && i+'a' <= 'h'+0) && (j+1 >= 1 && j+1 <= 8)) {
			Piece piece = board[j][i];
			//if there is a piece
			if(piece != null) {
				//if the current player owns that piece
				if(piece.team == playerCurrent) {
					//System.out.println(piece.team+"/"+piece.king); //DEBUG
					//if the piece is a king
					if(piece.king == true) {
						System.out.printf("Kinged %c at %s selected.\n",piece.team,input);
					//if the piece is not a king
					} else {
						System.out.printf("Normal %c at %s selected.\n",piece.team,input);
					}
					pieceActive = true;
					pieceX = i;
					pieceY = j;
				//if the current player does not own that piece
				} else {
					System.out.println("You cannot select pieces owned by your opponent.");
				}
			//if there is no piece
			} else {
				System.out.println("There is no piece at that address.");
			}
		//if the board address does not exist
		} else {
			System.out.println("That is not a valid board address.");
		}
	}

	// public static final int ROW_OFFSET = 97; // 
	
	private void movePiece(String input) {
		//TO DO
		//restrict down movement for non-kings
		//auto-end turns
		//skip auto-end while chain-jumping
		
		
		int i = input.charAt(0)-'a';
		int j = input.charAt(1)-'1';
		
		int jumpi = i==pieceX+2? pieceX+1 : pieceX-1;
		int jumpj = j==pieceY+2? pieceY+1 : pieceY-1;
		
		// System.out.printf("selection = %d,%d\n",i,j); //DEBUG
		
		//check for valid address, same as selectPiece
		if((i+'a' >= 'a'+0 && i+'a' <= 'h'+0) && (j+1 >= 1 && j+1 <= 8)) {
			//if target address is 2 spaces away diagonally
			if((i==pieceX+2 || i==pieceX-2) && (j==pieceY+2 || j==pieceY-2)) {
				//creating temporary pieces
				Piece pieceT = board[j][i];
				Piece pieceJ = board[jumpj][jumpi];
				//if the target square is empty
				if(pieceT==null) {
					//if the jumped square is not empty
					if(pieceJ!=null) {
						//if the jumped square is occupied by an enemy piece
						if(pieceJ.team!=playerCurrent) {
							if(playerCurrent==p1) {score1++;} else {score2++;}
							board[j][i] = board[pieceY][pieceX];
							board[pieceY][pieceX] = null;
							board[jumpj][jumpi] = null;
							deselect();
							setKing(i,j);
							drawBoard();
							System.out.println("Jump successful!");
						} else {
							System.out.println("You cannot jump over your own pieces.");
						}						
					} else {
						System.out.println("You must jump over a piece.");
					}
				} else {
					System.out.println("That board address is occupied.");
				}
				
			//if target address is diagonally adjacent
			} else if((i==pieceX+1 || i==pieceX-1) && (j==pieceY+1 || j==pieceY-1)) {
				//creating a temporary piece
				Piece pieceT = board[j][i];
				//if there is no piece there
				if(pieceT == null) {
					board[j][i] = board[pieceY][pieceX];
					board[pieceY][pieceX] = null;
					deselect();
					setKing(i,j);
					drawBoard();
					System.out.println("Move successful!");
				} else {
					System.out.println("That board address is occupied.");
				}
			} else {
				System.out.println("You can only make legal moves.");
			}
		} else {
			System.out.println("That is not a valid board address.");
		}
	}
	
	//converts normals to kings if applicable
	private void setKing(int i, int j) {
		
		Piece piece = board[j][i];
		
		if(playerCurrent == p1) {
			if(j==0 && piece.king==false) {
				piece.king = true;
				board[j][i] = piece;
				score1++;
			}
		} else {
			if(j==7 && piece.king==false) {
				piece.king = true;
				board[j][i] = piece;
				score2++;
			}
		}
	}
	
	//returns a player char if they have no pieces, returns something else if not
	private char checkPiecesNumber() {
		
		int p1count = 0;
		int p2count = 0;
		
		//runs through each board square and counts how many pieces each team has
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				Piece piece = board[row][col];
				if (piece != null) {
					if(piece.team==p1) {p1count++;} else {p2count++;}
				}
			}
		}
		
		//if one or both players have 0 pieces
		if(p1count==0 || p2count==0) {
			//return the one that has 0 pieces, p2 first if both
			return p1count==0? p2: p1;
		//if both players have >0 pieces
		} else {
			//else, return non-player-char value
			return (char)(p1+p2);
		}
	}
	
	//lists all system variables
	private void debugVariables() {
		
		System.out.println("\nplayer 1 character: "+p1);
		System.out.println("player 2 character: "+p2);
		System.out.println("current player: "+playerCurrent);
		System.out.println("boardActive? "+boardActive);
		System.out.println("pieceActive? "+pieceActive);
		System.out.println("pieceX: "+pieceX);
		System.out.println("pieceY: "+pieceY);
		System.out.println("score1: "+score1);
		System.out.println("score2: "+score2);
	}
	
	//delete pieces, regardless of game rules
	private void debugDelete() {
		
		System.out.println("debugDelete() has been called. Type 'e' to exit");
		
		String input = new String();
		
		while(!input.equals("e")) {
			input = scanner.nextLine();
			
			if(input.length()==2) {
				int i = input.charAt(0)-'a';
				int j = input.charAt(1)-'1';
				if((i+'a' >= 'a'+0 && i+'a' <= 'h'+0) && (j+1 >= 1 && j+1 <= 8)) {
					Piece piece = board[j][i];
					if(piece != null) {
						board[j][i] = null;
						System.out.println(input+" successfully deleted");
					} else {
						System.out.println("empty address");
					}
				} else {
					System.out.println("invalid address");
				}
			} else if(!input.equals("e")) {
				System.out.println("board address required");
			}
		}
		drawBoard();
		System.out.println("debugDelete() has ended.");
	}
	
	//move pieces, regardless of game rules
	private void debugMove() {
		
		System.out.println("debugMove() has been called. Type 'e' to exit");
		
		String input = new String();
		
		do {
			
			input = scanner.nextLine();
			
			if(input.length() == 2) {
				int i = input.charAt(0)-'a';
				int j = input.charAt(1)-'1';
			
				if((i+'a' >= 'a'+0 && i+'a' <= 'h'+0) && (j+1 >= 1 && j+1 <= 8)) {
					Piece piece = board[j][i];
					if(piece != null) {
						System.out.println(input+"selected.");
						pieceActive = true;
						pieceX = i;
						pieceY = j;
					} else {
						System.out.println("Empty address.");
					}
				} else {
					System.out.println("Invalid address.");
				}
			} else if(!input.equals("e")) {
				System.out.println("Address required.");
			}
			
			boolean moveFail = true;
			
			while(pieceActive&&moveFail&&!input.equals("e")) {
				input = scanner.nextLine();
				if(input.length() == 2) {
					int i = input.charAt(0)-'a';
					int j = input.charAt(1)-'1';
					if((i+'a' >= 'a'+0 && i+'a' <= 'h'+0) && (j+1 >= 1 && j+1 <= 8)) {
						Piece pieceT = board[j][i];
						if(pieceT == null) {
							moveFail = false;
							board[j][i] = board[pieceY][pieceX];
							board[pieceY][pieceX] = null;
							deselect();
							drawBoard();
							System.out.println("Move successful!");
							System.out.println("debugMove() is active. Type 'e' to exit");
						} else {
							System.out.println("Occupied address.");
						}
					} else {
						System.out.println("Invalid address.");
					}
				} else if (!input.equals("e")) {
					System.out.println("Address required.");
				}
			}
			
		} while(!input.equals("e"));
		
		pieceActive = false;
		pieceX = -1;
		pieceY = -1;
		
		System.out.println("debugMove() has ended.");
	}
	
	//changes properties of select pieces (ie king, team)
	private void debugSwitch() {
		
		System.out.println("debugSwitch() has been called. Type 'e' to exit.");
		
		String input = new String();
		
		while(!input.equals("e")) {
			
			input = scanner.nextLine();
			
			if(input.length() == 2) {
				int i = input.charAt(0)-'a';
				int j = input.charAt(1)-'1';
				if((i+'a' >= 'a'+0 && i+'a' <= 'h'+0) && (j+1 >= 1 && j+1 <= 8)) {
					Piece piece = board[j][i];
					if(piece != null) {
						System.out.println(input+"selected.");
						pieceActive = true;
						pieceX = i;
						pieceY = j;
					} else {
						System.out.println("Empty address.");
					}
				} else {
					System.out.println("Invalid address.");
				}
			}
			
			while(pieceActive&&!input.equals("e")) {
				
				input = scanner.nextLine();
				Piece piece = board[pieceY][pieceX];
				
				if(!input.equals("e")) {
					switch(input) {
						case "king" :
							piece.king = piece.king? false: true;
							board[pieceY][pieceX] = piece;
							pieceActive = false;
							drawBoard();
							System.out.println(input+" is now a king.");
							System.out.println("debugSwitch() is active. Type 'e' to exit");
							break;
						case "team" :
							piece.team = piece.team==p1? p2: p1;
							board[pieceY][pieceX] = piece;
							pieceActive = false;
							drawBoard();
							System.out.println(input+" is now on team"+piece.team+".");
							System.out.println("debugSwitch() is active. Type 'e' to exit");
							break;
						default :
							System.out.println("Invalid command");
							break;
					}
				}
			}
		}
		
		pieceActive = false;
		pieceX = -1;
		pieceY = -1;
		
		System.out.println("debugSwitch() has ended.");
	}
	
	private void debugHelp() {
		
		System.out.println("debugHelp() was called. Nothing happened!");
	}
}
